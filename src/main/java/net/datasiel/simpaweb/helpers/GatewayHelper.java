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

package net.datasiel.simpaweb.helpers;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.messages.SessionMessages;

import net.datasiel.par.beans.CamiciaFascicolo;
import net.datasiel.par.beans.Chiave;
import net.datasiel.par.beans.Configurazione;
import net.datasiel.par.beans.Configurazione.TipoConservazione;
import net.datasiel.par.beans.DatiFiscali;
import net.datasiel.par.beans.DatiSpecifici;
import net.datasiel.par.beans.DatiUnitaDocumentaria;
import net.datasiel.par.beans.Documento;
import net.datasiel.par.beans.DocumentoCollegato;
import net.datasiel.par.beans.FileAllegato;
import net.datasiel.par.beans.FileAllegatoBase.TipoSupporto;
import net.datasiel.par.beans.Intestazione;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.db.EnumTipoDocumento;
import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParCollegamentoVO;
import net.datasiel.simpaweb.db.vo.ParComponenteVO;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;
import net.datasiel.simpaweb.db.vo.ParDocumentoVO;
import net.datasiel.simpaweb.db.vo.ParFascicoloVO;
import net.datasiel.simpaweb.db.vo.VDecAttribDatiSpecVO;
import net.datasiel.simpaweb.db.vo.VDecFormatoFileDocVO;
import net.datasiel.simpaweb.db.vo.VDecRegistroUnitaDocVO;
import net.datasiel.simpaweb.db.vo.VDecTipoCompDocVO;
import net.datasiel.simpaweb.db.vo.VDecTipoDocVO;
import net.datasiel.simpaweb.db.vo.VDecTipoUnitaDocVO;
import net.datasiel.simpaweb.db.vo.VDecXsdDatiSpecVO;
import net.datasiel.simpaweb.db.vo.VOrgAmbienteVO;
import net.datasiel.simpaweb.db.vo.VOrgEnteVO;
import net.datasiel.simpaweb.db.vo.VOrgStrutVO;

/**
 * Metodi per la preparazione degli oggetti del sacerGateway.
 *
 * @author reisoli
 *
 */
public class GatewayHelper {

    private static final String COMPONENTI_NON_INSERITI = "Verifica fallita: %s senza file allegati.";
    private static final String TIPOLOGIA_DOCUMENTO_NON_IMPOSTATA = "Verifica fallita: %s senza tipologia.";

    public GatewayHelper() {
        // TODO Auto-generated constructor stub
    }

    private final Logger log = LoggerFactory.getLogger(GatewayHelper.class);

    /**
     * Prepara i dati per l'invocazione del servizio di versamento.
     *
     * @param datiUnitaDoc
     * @param simulaDb
     * @param connection
     * @param username
     * 
     * @return null se mancano dei dati essenziali (e aggiunge il messaggio d'errore in sessione).
     * 
     * @throws SQLException
     * @throws IOException
     */
    public DatiUnitaDocumentaria preparaUd(ParUnitadoc datiUnitaDoc, boolean simulaDb, Connection connection,
            String userIdVersatore, String utenteLoggato) throws SQLException, IOException {

        DatiUnitaDocumentaria udDaVerificare = new DatiUnitaDocumentaria();
        udDaVerificare.setOggetto(datiUnitaDoc.getOggetto());
        if (datiUnitaDoc.getData() != null) {
            udDaVerificare.setData(new DateTime(datiUnitaDoc.getData().getTime()));
        }

        Configurazione configurazione = new Configurazione();
        configurazione.setSimulaSalvataggioDatiInDB(simulaDb);
        configurazione.setForzaAccettazione(datiUnitaDoc.getFlgforzaaccettazione());
        configurazione.setForzaCollegamento(datiUnitaDoc.getFlgforzacollegamento());
        configurazione.setForzaConservazione(datiUnitaDoc.getFlgforzaconservazione());
        String tipoconservazione = datiUnitaDoc.getTipoconservazione();

        TipoConservazione versAnt = TipoConservazione.VERSAMENTO_ANTICIPATO;
        TipoConservazione fiscale = TipoConservazione.FISCALE;
        configurazione.setTipoConservazione("F".equalsIgnoreCase(tipoconservazione) ? fiscale : versAnt);
        udDaVerificare.setConfigurazione(configurazione);
        Intestazione intestazione = new Intestazione();
        Long idStrut = datiUnitaDoc.getIdStrut();
        // Dall'ente di appartenenza ricavare l'utente versatore.
        intestazione.setVersione(Constants.VERSIONE_VERSAMENTO);
        intestazione.setAmbiente(getAmbiente(idStrut, connection));
        intestazione.setEnte(getEnte(idStrut, connection));
        intestazione.setStruttura(getStruttura(idStrut, connection));
        intestazione.setTipologiaUnitaDocumentaria(
                getTipoUD(datiUnitaDoc.getIdTipoUnitaDoc(), datiUnitaDoc.getIdutente(), connection));

        Chiave chiaveUD = new Chiave(datiUnitaDoc.getNumero(), datiUnitaDoc.getAnno(),
                getTipoRegistro(datiUnitaDoc.getIdRegistroUnitaDoc(), datiUnitaDoc.getIdutente(), connection));
        intestazione.setChiave(chiaveUD);

        intestazione.setUserId(userIdVersatore);
        intestazione.setUtente(utenteLoggato);
        udDaVerificare.setIntestazione(intestazione);

        Long idunitadoc = datiUnitaDoc.getIdunitadoc();
        udDaVerificare.setFascicoloPrincipale(getFascicoloPrincipale(idunitadoc, connection));

        udDaVerificare.setFascicoliSecondari(getFascicoliSecondari(idunitadoc, connection));
        udDaVerificare.setDocumentiCollegati(getCollegamenti(idunitadoc, datiUnitaDoc.getIdutente(), connection));

        Documento docPrincipale = getDocPrincipale(datiUnitaDoc, connection);
        if (docPrincipale == null) {
            return null;
        } else {
            udDaVerificare.setDocumentoPrincipale(docPrincipale);
        }
        udDaVerificare.setAllegati(getAllegati(datiUnitaDoc, connection));

        udDaVerificare.setAnnessi(getAnnessi(datiUnitaDoc, connection));

        udDaVerificare.setAnnotazioni(getAnnotazioni(datiUnitaDoc, connection));

        DatiSpecifici datiSpec = null;
        try {
            datiSpec = getDatiSpecifici(idunitadoc, datiUnitaDoc.getIdStrut(), datiUnitaDoc.getIdTipoUnitaDoc(),
                    "UNI_DOC", datiUnitaDoc.getCdVersioneXSD(), connection);
            if (datiSpec != null && (datiSpec.getDocDatiSpecifici() != null)) {
                udDaVerificare.setDatiSpecifici(datiSpec);
            }
        } catch (JDOMException e) {
            log.error(ExceptionUtils.getRootCauseMessage(e), e);
        }

        return udDaVerificare;
    }

    private DatiSpecifici getDatiSpecifici(Long identificativo, Long idstrut, Long idTipoUnitaDoc,
            String tipoEntitaSacer, String cdVersioneXsd, Connection connection)
            throws SQLException, JDOMException, IOException {
        DatiSpecifici retValue = new DatiSpecifici();

        // Verificare se ci devono essere dati specifici
        // e recuperare attributi e xsd
        ParDatispecificiVO datiSpecDAO = new ParDatispecificiVO();
        ParDatispecifici ds = null;
        // Recupero il record padre dei dati specifici a seconda che sia un unita' documentaria o un documento
        if ("UNI_DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            ds = datiSpecDAO.getDatiSpecUnitaDoc(identificativo, tipoEntitaSacer, connection);
        } else if ("DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            ds = datiSpecDAO.getDatiSpecDoc(identificativo, tipoEntitaSacer, connection);
        }
        if (ds == null) {
            log.debug(String.format("Dati specifici non trovati per  idStrut %s, tipoEntitaSacer %s, idtipounitadoc %s",
                    idstrut, tipoEntitaSacer, idTipoUnitaDoc));
            return null;
        }
        Long idDatiSpecifici = ds.getIddatispecifici();

        VDecAttribDatiSpecVO vdecAttribDAO = new VDecAttribDatiSpecVO();
        Map<String, String> mappaDati = vdecAttribDAO.getMapOfValoriSpecifici(idDatiSpecifici, idstrut, tipoEntitaSacer,
                idTipoUnitaDoc, cdVersioneXsd, connection);
        VDecXsdDatiSpecVO xsdDatiSpec = VDecXsdDatiSpecVO.getXSDDatiSpecifici(idTipoUnitaDoc, tipoEntitaSacer,
                cdVersioneXsd, connection);
        List<Element> listaElementiXsd = null;
        if (xsdDatiSpec != null) {
            listaElementiXsd = getListaElementi(xsdDatiSpec.getStrXsdDatiSpec());
            // Non c'è xsd e quindi niente dati specifici

        }

        if (listaElementiXsd == null) {
            log.debug("Lista elementi xsd nulla");
        } else {
            log.debug(String.format("Trovata lista di elementi xsd con %d elementi", listaElementiXsd.size()));

            Document xmlDocDatiSpecifici = new Document();
            Element root = new Element("DatiSpecifici");
            xmlDocDatiSpecifici.setRootElement(root);
            Element versione = new Element("VersioneDatiSpecifici");
            versione.setText(xsdDatiSpec.getCdVersioneXsd());
            root.addContent(versione);
            for (Element tag : listaElementiXsd) {
                String nomeTag = tag.getAttributeValue("name");
                if (nomeTag != null && mappaDati.containsKey(nomeTag)) {
                    Element nodoDaAggiungere = new Element(nomeTag);
                    String valore = mappaDati.get(nomeTag);
                    nodoDaAggiungere.setText(valore);
                    root.addContent(nodoDaAggiungere);

                }
            }
            XMLOutputter xout = new XMLOutputter();
            xout.output(xmlDocDatiSpecifici, System.out);
            DOMOutputter outputter = new DOMOutputter();
            log.debug(xmlDocDatiSpecifici.toString());
            try {
                retValue.setDocDatiSpecifici(outputter.output(xmlDocDatiSpecifici));
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                log.error("Errore nella generazione della stringa xml", e);
            }
        }
        return retValue;
    }

    private List<Element> getListaElementi(String strXsdDatiSpec) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        Document docXsd = sb.build(new StringReader(strXsdDatiSpec));
        Element root = docXsd.getRootElement();
        root.setNamespace(Namespace.getNamespace("xs", root.getNamespaceURI()));
        // Con questa query non si trova niente:
        // valuta tutti gli elementi presenti su XSD
        String xpathExpr = "//xs:element";
        XPathExpression<Element> elementPath = XPathFactory.instance().compile(xpathExpr, Filters.element(), null,
                Namespace.getNamespace("xs", root.getNamespaceURI()));
        return elementPath.evaluate(docXsd);
    }

    private List<Documento> getAnnotazioni(ParUnitadoc datiUnitaDoc, Connection connection)
            throws SQLException, IOException {
        ParDocumentoVO parDocDAO = new ParDocumentoVO();
        List<ParDocumento> allegati = parDocDAO.getListadocumenti(datiUnitaDoc.getIdunitadoc(),
                EnumTipoDocumento.ANNO.name(), connection);
        List<Documento> retRows = new ArrayList<>();
        for (ParDocumento parDocumento : allegati) {
            retRows.add(preparaDocumento(datiUnitaDoc, parDocumento, EnumTipoDocumento.ANNO, datiUnitaDoc.getIdutente(),
                    connection));
        }
        return retRows;
    }

    private List<Documento> getAnnessi(ParUnitadoc datiUnitaDoc, Connection connection)
            throws SQLException, IOException {
        ParDocumentoVO parDocDAO = new ParDocumentoVO();
        List<ParDocumento> allegati = parDocDAO.getListadocumenti(datiUnitaDoc.getIdunitadoc(),
                EnumTipoDocumento.ANNE.name(), connection);
        List<Documento> retRows = new ArrayList<>();
        for (ParDocumento parDocumento : allegati) {
            retRows.add(preparaDocumento(datiUnitaDoc, parDocumento, EnumTipoDocumento.ANNE, datiUnitaDoc.getIdutente(),
                    connection));
        }
        return retRows;
    }

    private List<Documento> getAllegati(ParUnitadoc datiUnitaDoc, Connection connection)
            throws SQLException, IOException {
        ParDocumentoVO parDocDAO = new ParDocumentoVO();
        List<ParDocumento> allegati = parDocDAO.getListadocumenti(datiUnitaDoc.getIdunitadoc(),
                EnumTipoDocumento.ALLE.name(), connection);
        List<Documento> retRows = new ArrayList<>();
        for (ParDocumento parDocumento : allegati) {
            retRows.add(preparaDocumento(datiUnitaDoc, parDocumento, EnumTipoDocumento.ALLE, datiUnitaDoc.getIdutente(),
                    connection));
        }
        return retRows;
    }

    private Documento getDocPrincipale(ParUnitadoc datiUnitaDoc, Connection connection)
            throws SQLException, IOException {
        ParDocumentoVO parDocDAO = new ParDocumentoVO();
        ParDocumento datiDocPrincipale = parDocDAO.getDocPrincipale(datiUnitaDoc.getIdunitadoc(), connection);
        if (datiDocPrincipale != null) {
            return preparaDocumento(datiUnitaDoc, datiDocPrincipale, EnumTipoDocumento.PRINC,
                    datiUnitaDoc.getIdutente(), connection);
        } else {
            SessionMessages.addErrorMessage("L'unità documentaria è priva di un documento principale.");
            return null;
        }
    }

    /**
     * @param datiUnitaDoc
     * @param datiDocumento
     * @param tipoDoc
     *            "PRINC", "ALLE", "ANNE", "ANNO"
     * @param datiDocumento
     * @param connection
     * 
     * @return
     * 
     * @throws SQLException
     * @throws IOException
     */
    protected Documento preparaDocumento(ParUnitadoc datiUnitaDoc, ParDocumento datiDocumento,
            EnumTipoDocumento tipoDoc, Long idUserIam, Connection connection) throws SQLException, IOException {
        Documento docPrincipale = new Documento();
        docPrincipale.setIdDocumento(datiDocumento.getIddocumento().toString());
        docPrincipale.setProfiloAutoreDoc(datiDocumento.getProfiloautoredoc());
        docPrincipale.setProfiloDescrizioneDoc(datiDocumento.getProfilodescrizionedoc());
        VDecTipoDocVO tipoDocDAO = new VDecTipoDocVO();
        Long idTipoDocumento = datiDocumento.getIdTipoDoc();
        if (idTipoDocumento == null) {
            SessionMessages
                    .addErrorMessage(String.format(TIPOLOGIA_DOCUMENTO_NON_IMPOSTATA, tipoDoc.getValoreLeggibile()));
            return null;
        }
        String nmTipoDoc = tipoDocDAO.getNMTipoDoc(idTipoDocumento, idUserIam, connection);
        if (nmTipoDoc == null) {
            SessionMessages
                    .addErrorMessage(String.format(TIPOLOGIA_DOCUMENTO_NON_IMPOSTATA, tipoDoc.getValoreLeggibile()));
            return null;
        }
        docPrincipale.setTipoDocumento(nmTipoDoc);

        if (!isDatiFiscaliVuoto(datiDocumento)) {
            DatiFiscali datiFiscali = new DatiFiscali();
            datiFiscali.setCF(datiDocumento.getDfcodfiscale());
            datiFiscali.setCognome(datiDocumento.getDfcognome());
            datiFiscali.setNome(datiDocumento.getDfnome());

            Date dfdtemissione = datiDocumento.getDfdtemissione();
            if (dfdtemissione != null) {
                datiFiscali.setDataEmissione(new DateTime(dfdtemissione.getTime()));
            }
            Date dfdttermineemissione = datiDocumento.getDfdttermineemissione();
            if (dfdttermineemissione != null) {
                datiFiscali.setDataTermineEmissione(new DateTime(dfdttermineemissione.getTime()));
            }
            datiFiscali.setDenominazione(datiDocumento.getDfdenominazione());
            Long dfprogressivo = datiDocumento.getDfprogressivo();
            if (dfprogressivo != null) {
                datiFiscali.setNumeroProgressivo(dfprogressivo);
            }

            datiFiscali.setPeriodoFiscale(datiDocumento.getDfperiodo());
            datiFiscali.setPIVA(datiDocumento.getDfpiva());
            datiFiscali.setRegistro(datiDocumento.getDfregistro());
            docPrincipale.setDatiFiscali(datiFiscali);
        }

        // //Non impostiamo la struttura, sarà quella di default
        List<FileAllegato> componenti = getComponenti(datiDocumento.getIddocumento(), datiUnitaDoc.getIdutente(),
                connection);
        // Se null ci sono errori nei dati inseriti. Il messaggio è già
        // stato impostato
        if (componenti == null || componenti.isEmpty()) {
            SessionMessages.addErrorMessage(String.format(COMPONENTI_NON_INSERITI, tipoDoc.getValoreLeggibile()));
            return null;
        }
        docPrincipale.setComponenti(componenti);
        String tipoStruttura = "DocumentoGenerico";
        docPrincipale.setTipoStruttura(tipoStruttura);
        DatiSpecifici datiSpec = null;
        try {
            datiSpec = getDatiSpecifici(datiDocumento.getIddocumento(), datiUnitaDoc.getIdStrut(),
                    datiDocumento.getIdTipoDoc(), "DOC", datiDocumento.getCdVersioneXSD(), connection);
            if (datiSpec != null && (datiSpec.getDocDatiSpecifici() != null)) {
                docPrincipale.setDatiSpecifici(datiSpec);
            }
        } catch (JDOMException e) {
            log.error(ExceptionUtils.getRootCauseMessage(e), e);
        }

        return docPrincipale;
    }

    private boolean isDatiFiscaliVuoto(ParDocumento datiDocPrincipale) {
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfcodfiscale())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfcognome())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfdenominazione())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfnome())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfperiodo())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfpiva())) {
            return false;
        }
        if (StringUtils.isNotEmpty(datiDocPrincipale.getDfregistro())) {
            return false;
        }
        if (datiDocPrincipale.getDfdtemissione() != null) {
            return false;
        }
        if (datiDocPrincipale.getDfdttermineemissione() != null) {
            return false;
        }
        if (datiDocPrincipale.getDfprogressivo() != null) {
            return false;
        }

        return true;
    }

    private List<FileAllegato> getComponenti(Long iddocumento, Long idUtente, Connection connection)
            throws SQLException {

        ParComponenteVO parCompDAO = new ParComponenteVO();
        List<ParComponente> componenti = parCompDAO.getComponenti(iddocumento, connection);

        List<FileAllegato> retRows = new ArrayList<>();
        int ordinePresentazione = 1;
        for (ParComponente parComponente : componenti) {
            FileAllegato allegato = new FileAllegato();
            String nomeFile = "";
            String hashFile = null;
            // TODO quando chiarito che riferimento e file sono tipi diversi e
            // sarà gestito il tipo inserire opportuna gestione
            Long riftiporegistro = parComponente.getRiftiporegistro();
            String rifnumero = parComponente.getRifnumero();
            Long rifanno = parComponente.getRifanno();
            // TODO individuare diversamente il componente di tipo riferimento
            if (riftiporegistro != null && rifnumero != null && rifanno != null) {
                // Componente di tipo riferimento
                // Controllare se compilazione completa
                Chiave chiave = new Chiave(rifnumero, rifanno,
                        VDecRegistroUnitaDocVO.getCdRegistroUnitaDoc(riftiporegistro, idUtente, connection));
                allegato.setRiferimento(chiave);
            } else {

                String codallegato = parComponente.getCodallegato();
                if (StringUtils.isEmpty(codallegato)) {
                    log.info("Componente senza blob allegato.");
                    return null;
                }

                // Componente di tipo FILE
                // Impostare oggetto file. Per risparmiare memoria si potrebbe
                // in realtà tramandare
                // il codice Elements, o il path e andare a leggere
                // effettivamente i file solo
                // al momento della costruzione del request (facendo in modo) di
                // tenerne in memoria
                // solo uno
                allegato.setCodAllegato(codallegato);
                allegato.setUtilizzoDataFirmaPerRifTemp(parComponente.getFlgFirmaPerRifTemp());
                DateTime dataRiferimentoTemp = parComponente.getDataRifTemp() == null ? null
                        : new DateTime(parComponente.getDataRifTemp().getTime());
                allegato.setRiferimentoTemporale(dataRiferimentoTemp);
                allegato.setDescrizioneRiferimentoTemporale(parComponente.getDescRifTemp());

                try {
                    Long idFormatoFileDoc = parComponente.getIdFormatoFileDoc();
                    VDecFormatoFileDocVO vDecFormatoFileDocVO = new VDecFormatoFileDocVO();
                    String formatoFileVersato = vDecFormatoFileDocVO.decodeFormato(idFormatoFileDoc, connection);
                    allegato.setFormatoFileVersato(formatoFileVersato);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    throw new SQLException(
                            "Errore nel recupero del formato file versato di " + parComponente.getNome());
                }

                nomeFile = parComponente.getNome();
                hashFile = parComponente.getDsHashFileVers();
            }

            // TODO andrà gestito, per il momento è schiantato sull'unico valore
            // presente in db
            Long idtipoCompdoc = parComponente.getIdTipoCompDoc();
            if (idtipoCompdoc != null && idtipoCompdoc > 0) {
                VDecTipoCompDocVO vDecTipoCompDocVO = new VDecTipoCompDocVO();
                String tipoComponente = vDecTipoCompDocVO.getNmTipoCompDoc(idtipoCompdoc, connection);
                allegato.setTipoComponente(tipoComponente);
            } else {
                allegato.setTipoComponente("");
            }

            allegato.setTipoSupportoComponente(TipoSupporto.FILE);
            allegato.setNomeComponente(nomeFile);
            allegato.setOrdinePresentazione(ordinePresentazione);
            allegato.setHashVersato(hashFile);

            retRows.add(allegato);

            ordinePresentazione++;
        }

        return retRows;
    }

    private List<DocumentoCollegato> getCollegamenti(Long idunitadoc, Long idUser, Connection connection)
            throws SQLException {
        ParCollegamentoVO parCollDAO = new ParCollegamentoVO();
        List<ParCollegamento> collegamenti = parCollDAO.getCollegamenti(idunitadoc, connection);
        List<DocumentoCollegato> retRows = new ArrayList<>();
        for (ParCollegamento parCollegamento : collegamenti) {
            String numero = parCollegamento.getNumero();
            Long anno = parCollegamento.getAnno();
            Long idRegistroUnitaDoc = parCollegamento.getIdRegistroUnitaDoc();
            boolean isNumeroOrAnnoOrIdRegistroUnitaDocValid = StringUtils.isNotBlank(numero)
                    | (anno != null & anno.longValue() > 0)
                    | (idRegistroUnitaDoc != null & idRegistroUnitaDoc.longValue() > 0);
            if (isNumeroOrAnnoOrIdRegistroUnitaDocValid) {
                Chiave chiave = new Chiave(parCollegamento.getNumero(), parCollegamento.getAnno(),
                        parCollegamento.getIdRegistroUnitaDoc() == null ? null : VDecRegistroUnitaDocVO
                                .getCdRegistroUnitaDoc(parCollegamento.getIdRegistroUnitaDoc(), idUser, connection));
                DocumentoCollegato collegamento = new DocumentoCollegato(chiave, parCollegamento.getDescrizione());
                retRows.add(collegamento);
            }
        }
        return retRows;
    }

    private List<CamiciaFascicolo> getFascicoliSecondari(Long idunitadoc, Connection connection) throws SQLException {
        List<CamiciaFascicolo> retRows = new ArrayList<>();
        ParFascicoloVO fascicoloDAO = new ParFascicoloVO();
        List<ParFascicolo> secondari = fascicoloDAO.getSecondari(idunitadoc, connection);
        for (ParFascicolo parFascicolo : secondari) {
            CamiciaFascicolo camiciaFascicolo = new CamiciaFascicolo();
            camiciaFascicolo.setClassifica(parFascicolo.getClassifica());
            camiciaFascicolo.setFascicolo(
                    camiciaFascicolo.new Fascicolo(parFascicolo.getIdentificativo(), parFascicolo.getOggetto()));
            camiciaFascicolo.setSottoFascicolo(camiciaFascicolo.new Fascicolo(parFascicolo.getIdsottofascicolo(),
                    parFascicolo.getOggettosottofascicolo()));

            retRows.add(camiciaFascicolo);
        }
        return retRows;
    }

    private CamiciaFascicolo getFascicoloPrincipale(Long idunitadoc, Connection connection) throws SQLException {
        CamiciaFascicolo camiciaFascicolo = new CamiciaFascicolo();
        ParFascicoloVO fascicoloDAO = new ParFascicoloVO();
        ParFascicolo principale = fascicoloDAO.getFascicoloPrincipale(idunitadoc, connection);
        if (principale != null) {
            camiciaFascicolo.setClassifica(principale.getClassifica());
            camiciaFascicolo.setFascicolo(
                    camiciaFascicolo.new Fascicolo(principale.getIdentificativo(), principale.getOggetto()));
            camiciaFascicolo.setSottoFascicolo(camiciaFascicolo.new Fascicolo(principale.getIdsottofascicolo(),
                    principale.getOggettosottofascicolo()));
            return camiciaFascicolo;
        } else {
            return null;
        }

    }

    private String getTipoRegistro(Long idRegistroUnitaDoc, Long idUser, Connection connection) throws SQLException {
        return VDecRegistroUnitaDocVO.getCdRegistroUnitaDoc(idRegistroUnitaDoc, idUser, connection);
    }

    private String getTipoUD(Long idTipoUnitaDoc, Long idUser, Connection connection) throws SQLException {
        return VDecTipoUnitaDocVO.getNomeTipoUnitaDoc(idTipoUnitaDoc, idUser, connection);
    }

    private String getStruttura(Long idStrut, Connection connection) throws SQLException {
        return VOrgStrutVO.getNomeStruttura(idStrut, connection);
    }

    private String getEnte(Long idStrut, Connection connection) throws SQLException {
        return VOrgEnteVO.getNomeEnte(idStrut, connection);
    }

    private String getAmbiente(Long idStrut, Connection connection) throws SQLException {
        return VOrgAmbienteVO.getNomeAmbiente(idStrut, connection);
    }
}
