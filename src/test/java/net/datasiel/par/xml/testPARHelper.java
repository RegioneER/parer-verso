/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package net.datasiel.par.xml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import junit.framework.Assert;
import net.datasiel.par.beans.CamiciaFascicolo;
import net.datasiel.par.beans.CamiciaFascicolo.Fascicolo;
import net.datasiel.par.beans.Chiave;
import net.datasiel.par.beans.Configurazione;
import net.datasiel.par.beans.DatiFiscali;
import net.datasiel.par.beans.DatiSpecifici;
import net.datasiel.par.beans.DatiUnitaDocAggAllegati;
import net.datasiel.par.beans.DatiUnitaDocAggAllegati.TipoDocumento;
import net.datasiel.par.beans.DatiUnitaDocumentaria;
import net.datasiel.par.beans.Documento;
import net.datasiel.par.beans.FileAllegato;
import net.datasiel.par.beans.Intestazione;
import net.datasiel.par.jaxb.datispecifici.DatiSpecificiType;
import net.datasiel.par.jaxb.datispecifici.ObjectFactory;
import net.datasiel.simpaweb.common.Constants;

/**
 * Attenzione: questi test non possono funzionare. In particolare {@link PARHelper#collectCompositeConfiguration()}
 * cerca un file che è al percorso sbagliato. Marcato tutto come @Ignore
 *
 * @author Liguria?
 */
@Ignore
public class testPARHelper {

    private DatiUnitaDocumentaria datiUDTest = new DatiUnitaDocumentaria();

    @Before
    public void creaDatiTest() {
        DatiUnitaDocumentaria datiVersamento = new DatiUnitaDocumentaria();
        Intestazione datiIntestazione = new Intestazione();
        datiIntestazione.setVersione(Constants.VERSIONE_VERSAMENTO);
        datiIntestazione.setEnte("ente Regione");
        datiIntestazione.setStruttura("AOO Regione");
        datiIntestazione.setUserId("Versatore RL");
        datiIntestazione.setAmbiente("ambiente TEST");
        Chiave datiChiave = new Chiave("36", 2013L, "GENERICO");
        datiIntestazione.setChiave(datiChiave);
        datiIntestazione.setTipologiaUnitaDocumentaria("Documento");
        datiVersamento.setIntestazione(datiIntestazione);

        Configurazione datiConf = new Configurazione();
        // datiConf.setTipoConservazione(TipoConservazione.VERSAMENTO_ANTICIPATO);
        datiConf.setForzaAccettazione(true);
        datiConf.setForzaConservazione(true);
        datiConf.setForzaCollegamento(true);
        datiConf.setSimulaSalvataggioDatiInDB(true);

        datiVersamento.setConfigurazione(datiConf);

        CamiciaFascicolo datiFascicoloPrinc = new CamiciaFascicolo();
        datiFascicoloPrinc.setClassifica("1.1.0");
        Fascicolo fascicoloPrinc = datiFascicoloPrinc.new Fascicolo("1.0.0/2013/1",
                "Pratica di prova EPSILON versata applicativamente");
        datiFascicoloPrinc.setFascicolo(fascicoloPrinc);
        datiVersamento.setFascicoloPrincipale(datiFascicoloPrinc);

        datiVersamento.setOggetto("Informazioni di prova di tipo epsilon");
        DateTime dataProfiloUnitaDoc = new DateTime(2013, 3, 1, 0, 0, 0, 0);
        datiVersamento.setData(dataProfiloUnitaDoc);

        Documento docPrincipale = new Documento();
        docPrincipale.setIdDocumento("1");
        docPrincipale.setTipoDocumento("generico");
        docPrincipale.setProfiloDescrizioneDoc("Questa è una pratica di prova di tipo epsilon");
        docPrincipale.setProfiloAutoreDoc("Livia Rossa");
        FileAllegato allegato = new FileAllegato();
        allegato.setOrdinePresentazione(1);
        File fileVersato = new File("testFiles\\VersamentoEpsilon.xml");
        allegato.setFileVersato(fileVersato);
        DateTime dataRiferimentoTemporale = new DateTime();
        allegato.setRiferimentoTemporale(dataRiferimentoTemporale);
        allegato.setFormatoFileVersato("XML");
        allegato.setHashVersato("hash");
        allegato.setIdComponenteVersato("idcomp");

        List<FileAllegato> listaFile = new ArrayList<FileAllegato>();
        listaFile.add(allegato);
        docPrincipale.setComponenti(listaFile);

        docPrincipale.setDatiFiscali(getDatiFiscali());
        datiVersamento.setDocumentoPrincipale(docPrincipale);

        // ALLEGATI
        List<Documento> allegati = new ArrayList<Documento>();
        for (int i = 0; i < 3; i++) {
            Documento docAllegato = new Documento();
            docAllegato.setIdDocumento("1/" + i);
            docAllegato.setTipoDocumento("generico");
            docAllegato.setProfiloDescrizioneDoc("Allegato #" + i);
            docAllegato.setProfiloAutoreDoc("Livia Rossa");
            FileAllegato fileAllegato = new FileAllegato();
            fileAllegato.setOrdinePresentazione(1);
            fileAllegato.setRiferimentoTemporale(dataRiferimentoTemporale);
            fileAllegato.setFileVersato(new File("testFiles\\VersamentoEpsilon.xml"));

            List<FileAllegato> listaFileAllegati = new ArrayList<FileAllegato>();
            listaFileAllegati.add(fileAllegato);
            docAllegato.setComponenti(listaFileAllegati);
            docAllegato.setDatiFiscali(getDatiFiscali());
            allegati.add(docAllegato);
        }
        datiVersamento.setAllegati(allegati);

        // ANNOTAZIONI
        List<Documento> annotazioni = new ArrayList<Documento>();
        for (int i = 0; i < 3; i++) {
            Documento docAnnotazione = new Documento();
            docAnnotazione.setIdDocumento("1/" + i);
            docAnnotazione.setTipoDocumento("generico");
            docAnnotazione.setProfiloDescrizioneDoc("Annotazione #" + i);
            docAnnotazione.setProfiloAutoreDoc("Livia Rossa");
            FileAllegato fileAnnotazione = new FileAllegato();
            fileAnnotazione.setOrdinePresentazione(1);
            fileAnnotazione.setRiferimentoTemporale(dataRiferimentoTemporale);
            fileAnnotazione.setFileVersato(new File("testFiles\\VersamentoEpsilon.xml"));

            List<FileAllegato> listaFileAnnessi = new ArrayList<FileAllegato>();
            listaFileAnnessi.add(fileAnnotazione);
            docAnnotazione.setComponenti(listaFileAnnessi);
            docAnnotazione.setDatiFiscali(getDatiFiscali());
            annotazioni.add(docAnnotazione);
        }
        datiVersamento.setAnnotazioni(annotazioni);

        // ANNESSI
        List<Documento> annessi = new ArrayList<Documento>();
        for (int i = 0; i < 3; i++) {
            Documento docAnnesso = new Documento();
            docAnnesso.setIdDocumento("1/" + i);
            docAnnesso.setTipoDocumento("generico");
            docAnnesso.setProfiloDescrizioneDoc("Annesso #" + i);
            docAnnesso.setProfiloAutoreDoc("Livia Rossa");
            FileAllegato fileAnnesso = new FileAllegato();
            fileAnnesso.setOrdinePresentazione(1);
            fileAnnesso.setRiferimentoTemporale(dataRiferimentoTemporale);
            fileAnnesso.setFileVersato(new File("testFiles\\VersamentoEpsilon.xml"));

            List<FileAllegato> listaFileAnnessi = new ArrayList<FileAllegato>();
            listaFileAnnessi.add(fileAnnesso);
            docAnnesso.setComponenti(listaFileAnnessi);
            docAnnesso.setDatiFiscali(getDatiFiscali());
            annessi.add(docAnnesso);
        }
        datiVersamento.setAnnessi(annessi);

        /**
         * Creazione sezione dati specifici. Per come è definito xs:any non è possibile usare String per rappresentare
         * l'xml generico. Dovrà essere DOM o un oggett JAXB Vedi
         * http://jaxb.java.net/2.2.5/docs/ch03.html#compiling-xml-schema-mapping-of-xs-any
         */
        /*
         * String datiSpecXML =
         * "<Dati><ComponenteNew vers=\"2.12\"><IDNew>ID1</IDNew><OrdineNew>12</OrdineNew></ComponenteNew></Dati>";
         * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); DocumentBuilder documentBuilder =
         * dbf.newDocumentBuilder(); InputStream is = new ByteArrayInputStream(datiSpecXML.getBytes("UTF-8")); Document
         * document = documentBuilder.parse(is);
         */
        Document doc = null;
        try {
            ObjectFactory domObjFactory = new ObjectFactory();
            net.datasiel.par.jaxb.datispecifici.DatiSpecificiType datiDomanda = domObjFactory.createDatiSpecificiType();
            datiDomanda.setContributoRichiesto("20000");
            datiDomanda.setDenominazioneBando("Bando ???? prova");
            datiDomanda.setImportoTotaleLordo("345000");
            datiDomanda.setRagioneSociale("Datasiel");
            DateTime dataPres = new DateTime();

            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar xmldata = datatypeFactory.newXMLGregorianCalendarDate(dataPres.getYear(),
                    dataPres.getMonthOfYear(), dataPres.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED);

            datiDomanda.setDataPresentazione(xmldata);

            // Marshal di datiDomanda su un generico Node (Document) per fare s? che creaDatiSpecifici
            // spacchetti il Document e per ogni elemento invochi datiSpecifici.getAny().add(elemento)

            JAXBContext jc = JAXBContext.newInstance(DatiSpecificiType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "Cp1252");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
            m.marshal(new JAXBElement<DatiSpecificiType>(new QName("", "DatiSpecifici"), DatiSpecificiType.class, null,
                    datiDomanda), doc);
        } catch (Exception e) {
            System.err.print("Errore nella creazione dei dati specifici.");
            System.err.println(ExceptionUtils.getRootCause(e));
        }

        // AL posto di datiDomanda ci dovr? essere il Document creato dall?unmarshall
        // TODO a questo punto DatiSpecifici non avr? object ma Document
        DatiSpecifici datiSpec = new DatiSpecifici("1.0", doc);

        // datiSpec.setDatiSpecifici(domObjFactory.createDatiSpecifici(datiDomanda));
        // datiSpec.setDatiSpecifici(new JAXBElement(new QName("", "DatiSpecifici"),
        // net.datasiel.par.jaxb.datispecifici.DatiSpecificiType.class, null, datiDomanda));
        datiVersamento.setDatiSpecifici(datiSpec);

        datiUDTest = datiVersamento;
    }

    @Test
    public void testInvocaVersamentoSync() {
        try {
            PARHelper ph = new PARHelper("Cp1252");

            String esito = ph.invocaVersamentoSync(datiUDTest);
            Assert.assertNotNull("Invocazione fallita", esito);

            System.out.println(esito);

        } catch (IllegalCharsetNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testCreaRequestVersamento() {

        try {
            PARHelper vh = new PARHelper("Cp1252");
            String versamentoXML = vh.creaRequestVersamento(datiUDTest);
            System.out.println(versamentoXML);
            // TODO testare versamentoXML (se c'è)
        } catch (Exception e) {
            System.err.print("Errore nella creazione xml");
            Assert.fail("test fallito");
            System.exit(1);
        }
    }

    @Test
    public void testCreaRequestAggiuntaAllegati() throws Exception {
        DatiUnitaDocAggAllegati datiAggiunta = new DatiUnitaDocAggAllegati();

        Intestazione datiIntestazione = new Intestazione();
        datiIntestazione.setVersione(Constants.VERSIONE_VERSAMENTO);
        datiIntestazione.setEnte("ente Regione");
        datiIntestazione.setStruttura("AOO Regione");
        datiIntestazione.setUserId("Versatore RL");
        datiIntestazione.setUtente("Cognome_N");
        datiIntestazione.setAmbiente("ambiente TEST");
        Chiave datiChiave = new Chiave("36", 2013L, "GENERICO");
        datiIntestazione.setChiave(datiChiave);
        datiAggiunta.setIntestazione(datiIntestazione);

        Configurazione datiConf = new Configurazione();
        // datiConf.setTipoConservazione(TipoConservazione.VERSAMENTO_ANTICIPATO);
        datiConf.setForzaAccettazione(true);
        datiConf.setForzaConservazione(true);
        datiConf.setSimulaSalvataggioDatiInDB(true);
        datiAggiunta.setConfigurazione(datiConf);

        Documento documentoAggiunta = new Documento();
        documentoAggiunta.setIdDocumento("1");
        documentoAggiunta.setTipoDocumento("generico");
        documentoAggiunta.setProfiloDescrizioneDoc("Questa ? una pratica di prova di tipo epsilon");
        documentoAggiunta.setProfiloAutoreDoc("Livia Rossa");
        FileAllegato allegato = new FileAllegato();
        allegato.setOrdinePresentazione(1);
        DateTime dataRiferimentoTemporale = new DateTime();
        allegato.setRiferimentoTemporale(dataRiferimentoTemporale);
        List<FileAllegato> listaFile = new ArrayList<FileAllegato>();
        listaFile.add(allegato);
        documentoAggiunta.setComponenti(listaFile);

        documentoAggiunta.setDatiFiscali(getDatiFiscali());

        datiAggiunta.setDatiDocumento(documentoAggiunta);
        datiAggiunta.setTipoDocumento(TipoDocumento.ALLEGATO);

        PARHelper vh = new PARHelper("Cp1252");
        String aggiuntaXML = vh.creaRequestAggiuntaAllegati(datiAggiunta);
        System.out.println(aggiuntaXML);
    }

    /**
     * @throws DatatypeConfigurationException
     */
    protected DatiFiscali getDatiFiscali() {
        DatiFiscali datiFiscali = new DatiFiscali();
        datiFiscali.setDenominazione("prova_denominazione");
        datiFiscali.setNome("prova_nome");
        datiFiscali.setCognome("prova_cognome");
        datiFiscali.setCF("SCMRRT45A01F205K");
        datiFiscali.setPIVA("02994540108");
        datiFiscali.setDataEmissione(new DateTime());
        datiFiscali.setNumeroProgressivo(1L);
        datiFiscali.setRegistro("prova_registro");
        datiFiscali.setPeriodoFiscale("prova_periodo_fiscale");
        datiFiscali.setDataTermineEmissione(new DateTime());
        return datiFiscali;
    }

    @Test(expected = IllegalCharsetNameException.class)
    public void testCostruttoreEncodingNonsupportato()
            throws UnsupportedCharsetException, JAXBException, IllegalCharsetNameException, ConfigurationException {
        PARHelper ph = new PARHelper("encoding non esistente");
    }

    @Test(expected = IllegalCharsetNameException.class)
    public void testSetterEncodingNonsupportato()
            throws UnsupportedCharsetException, JAXBException, ConfigurationException {
        PARHelper ph = new PARHelper();
        ph.setEncoding("encoding non supportato");
    }

    @Test
    public void testCongruenzaNumeroAnnotazioni() throws ConfigurationException {
        if (datiUDTest.getAnnotazioni() == null) {
            Assert.assertEquals(0, datiUDTest.getNumeroAnnotazioni());
        } else {
            Assert.assertEquals(datiUDTest.getNumeroAnnotazioni(), datiUDTest.getAnnotazioni().size());

        }
    }

    @Test
    public void testCongruenzaNumeroAnnessi() throws ConfigurationException {

        if (datiUDTest.getAnnessi() == null) {
            Assert.assertEquals(0, datiUDTest.getNumeroAnnessi());
        } else {
            Assert.assertEquals(datiUDTest.getNumeroAnnessi(), datiUDTest.getAnnessi().size());

        }
    }

    @Test
    public void testCongruenzaNumeroAllegati() throws ConfigurationException {

        if (datiUDTest.getAllegati() == null) {
            Assert.assertEquals(0, datiUDTest.getNumeroAllegati());
        } else {
            Assert.assertEquals(datiUDTest.getNumeroAllegati(), datiUDTest.getAllegati().size());

        }
    }

    @Test
    public void leggiXsd() throws SAXException, JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document jdomDocument = builder.build(
                new File("D:\\JunoWks\\sacerGateway\\src\\net\\datasiel\\par\\resources\\esempioXsdDatiSpecifici.xsd"));
        Element root = jdomDocument.getRootElement();
        root.setNamespace(Namespace.getNamespace("xs", root.getNamespaceURI()));
        // Con questa query non si trova niente:
        // String xpathExpr = "/xs:schema/xs:complexType/xs:sequence/xs:element/@name=\"DenominazioneBando\"";
        String xpathExpr = "/xs:schema/xs:complexType/xs:sequence/xs:element";
        XPathExpression<Element> elementPath = XPathFactory.instance().compile(xpathExpr, Filters.element(), null,
                Namespace.getNamespace("xs", root.getNamespaceURI()));
        List<Element> elementList = elementPath.evaluate(jdomDocument);
        for (Element element : elementList) {
            if (!"VersioneDatiSpecifici".equals(element.getAttributeValue("name"))) {

                System.out.println(element.getAttributeValue("type"));
            }
        }

    }

}
