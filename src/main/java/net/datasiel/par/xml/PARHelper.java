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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.eng.exceptions.ErrorCategory.VersoErrorCategory;
import it.eng.exceptions.VersoException;
import it.eng.parer.simparer.common.SpringContext;
import net.datasiel.par.beans.CamiciaFascicolo;
import net.datasiel.par.beans.CamiciaFascicolo.Fascicolo;
import net.datasiel.par.beans.Chiave;
import net.datasiel.par.beans.Configurazione;
import net.datasiel.par.beans.Configurazione.TipoConservazione;
import net.datasiel.par.beans.DatiFiscali;
import net.datasiel.par.beans.DatiSpecifici;
import net.datasiel.par.beans.DatiUnitaDocAggAllegati;
import net.datasiel.par.beans.DatiUnitaDocAggAllegati.TipoDocumento;
import net.datasiel.par.beans.DatiUnitaDocumentaria;
import net.datasiel.par.beans.Documento;
import net.datasiel.par.beans.DocumentoCollegato;
import net.datasiel.par.beans.FileAllegato;
import net.datasiel.par.beans.FileAllegatoBase;
import net.datasiel.par.beans.FileAllegatoBase.TipoSupporto;
import net.datasiel.par.beans.Intestazione;
import net.datasiel.par.jaxb.versamento.CamiciaFascicoloType;
import net.datasiel.par.jaxb.versamento.ChiaveType;
import net.datasiel.par.jaxb.versamento.ComponenteType;
import net.datasiel.par.jaxb.versamento.ConfigAggAllType;
import net.datasiel.par.jaxb.versamento.ConfigType;
import net.datasiel.par.jaxb.versamento.DatiFiscaliType;
import net.datasiel.par.jaxb.versamento.DatiSpecificiType;
import net.datasiel.par.jaxb.versamento.DocumentoCollegatoType;
import net.datasiel.par.jaxb.versamento.DocumentoType;
import net.datasiel.par.jaxb.versamento.FascicoloType;
import net.datasiel.par.jaxb.versamento.IntestazioneAggAllType;
import net.datasiel.par.jaxb.versamento.IntestazioneType;
import net.datasiel.par.jaxb.versamento.ObjectFactory;
import net.datasiel.par.jaxb.versamento.ProfiloArchivisticoType;
import net.datasiel.par.jaxb.versamento.ProfiloArchivisticoType.FascicoliSecondari;
import net.datasiel.par.jaxb.versamento.ProfiloDocumentoType;
import net.datasiel.par.jaxb.versamento.ProfiloUnitaDocumentariaType;
import net.datasiel.par.jaxb.versamento.SottoComponenteType;
import net.datasiel.par.jaxb.versamento.StrutturaType;
import net.datasiel.par.jaxb.versamento.StrutturaType.Componenti;
import net.datasiel.par.jaxb.versamento.TipoConservazioneType;
import net.datasiel.par.jaxb.versamento.TipoSupportoType;
import net.datasiel.par.jaxb.versamento.UnitaDocAggAllegati;
import net.datasiel.par.jaxb.versamento.UnitaDocumentaria;
import net.datasiel.par.jaxb.versamento.UnitaDocumentaria.Allegati;
import net.datasiel.par.jaxb.versamento.UnitaDocumentaria.Annessi;
import net.datasiel.par.jaxb.versamento.UnitaDocumentaria.Annotazioni;
import net.datasiel.par.jaxb.versamento.VersatoreType;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.db.dao.ParComponenteDAO;

/**
 * Classe di utilità per la generazione del file xml secondo la specifica richiesta dal servizio di versamento. Consente
 * di generare l'xml sia per il servizio di versamento di una nuova unita documentaria sia per il servizio di aggiunta
 * documenti a unità documentarie già versate.
 * 
 * @author reisoli
 * 
 */
public class PARHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1241721718022885875L;
    private static final Logger log = LoggerFactory.getLogger(PARHelper.class);

    private static DocumentBuilderFactory documentBuilderFactory;
    static {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
    }
    private transient JAXBContext jaxbContext = null;
    private String encoding;
    private transient CompositeConfiguration cc;

    private String versione;
    private String loginname;
    private String endpoint;

    /**
     * Costruttore da usare nelle situazioni in cui non ci sono dati specifici da aggiungere. Imposta l'encoding a UTF-8
     * 
     * @throws JAXBException
     * @throws ConfigurationException
     */
    public PARHelper() throws JAXBException, ConfigurationException {
        this(StandardCharsets.UTF_8.name());
    }

    /**
     * Costruttore che consente di impostare l'encoding.
     * 
     * @param strEncoding
     *            Encoding dei dati che verranno inviati.
     * 
     * @throws JAXBException
     */
    public PARHelper(String strEncoding) throws JAXBException, IllegalCharsetNameException {
        jaxbContext = JAXBContext.newInstance(UnitaDocumentaria.class);
        if (Charset.isSupported(strEncoding)) {
            encoding = strEncoding;
        }
    }

    /**
     * Invoca il servizio VersamentoSync restituendo l'xml della risposta.
     * 
     * @param unitaDoc
     *            Dati unità documentaria da versare.
     * 
     * @return L'xml della risposta in formato stringa
     * 
     * @throws Exception
     */
    public String invocaVersamentoSync(DatiUnitaDocumentaria unitaDoc) throws Exception {

        String strXml = creaRequestVersamento(unitaDoc);
        // TODO sostituire costanti con proprietà lette da config
        String userName = "Versatore RL";
        String password = "perlomeno8Car#";
        String uploadUrl = "https://par-test.regione.liguria.it/sacer/VersamentoSync";

        InputStream is = null;
        // new lib
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(uploadUrl);

            // new lib
            // Inizializza la request come multipart, nella modalità browser compatible che
            // consente di inviare i dati come campi di una form web
            MultipartEntityBuilder mpEntityBuilder = MultipartEntityBuilder.create();
            mpEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("VERSIONE", new StringBody(versione, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("LOGINNAME ", new StringBody(userName, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale PASSWORD
            mpEntityBuilder.addPart("PASSWORD ", new StringBody(password, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale XMLSIP, con il documento XML dei metadati
            mpEntityBuilder.addPart("XMLSIP", new StringBody(strXml, ContentType.TEXT_XML));

            log.info("Xml Richiesta: {}", strXml);
            log.debug("Aggiunta allegati alla richiesta");

            Map<String, File> fileDaAllegare = new HashMap<>();
            List<FileAllegato> lista = unitaDoc.getDocumentoPrincipale().getComponenti();
            for (FileAllegato fileAllegato : lista) {
                fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
            }
            if (unitaDoc.getAllegati() != null && !unitaDoc.getAllegati().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAllegati();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }
            if (unitaDoc.getAnnessi() != null && !unitaDoc.getAnnessi().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnessi();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }
            if (unitaDoc.getAnnotazioni() != null && !unitaDoc.getAnnotazioni().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnotazioni();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }

            Set<String> ids = fileDaAllegare.keySet();
            int filesCount = 0;
            for (String string : ids) {
                is = new FileInputStream(fileDaAllegare.get(string));

                InputStreamBody bin = new InputStreamBody(is, "file1");
                // aggiunge alla request il il campo binario(file), con il file appena caricato.
                // il nome del campo _deve_ coincidere con uno degli ID indicati nell'XML
                mpEntityBuilder.addPart(string, bin);
                filesCount++;
            }

            log.info("Aggiunti {} file alla richiesta", filesCount);
            httppost.setEntity(mpEntityBuilder.build());
            log.info("eseguo la richiesta... {}", httppost.getRequestLine());
            // invoca il web service
            HttpResponse response = httpclient.execute(httppost);
            // recupera la risposta
            HttpEntity resEntity = response.getEntity();
            String page = "";
            if (resEntity != null) {
                page = EntityUtils.toString(resEntity);
                log.info("Risposta : {}", page);
            }
            return page;
        } catch (Exception e) {
            throw new VersoException(e, VersoErrorCategory.INTERNAL_ERROR);
        } finally {
            if (is != null) {
                is.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
    }

    public String invocaVersamentoSync(DatiUnitaDocumentaria unitaDoc, String uploadUrl,
            List<Map<String, String>> listaCookie) throws Exception {

        PARHelper parHelper = new PARHelper(StandardCharsets.UTF_8.name());
        String strXml = parHelper.creaRequestVersamento(unitaDoc);
        // TODO sostituire costanti con proprietà lette da config
        String userName = "CFFILSEPAR";
        String password = "password";

        InputStream is = null;
        // new lib
        CloseableHttpClient httpclient = null;
        try {
            // OLD HttpClient httpclient = new DefaultHttpClient();
            httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(uploadUrl);

            // new lib
            // Inizializza la request come multipart, nella modalità browser compatible che
            // consente di inviare i dati come campi di una form web
            MultipartEntityBuilder mpEntityBuilder = MultipartEntityBuilder.create();
            mpEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("VERSIONE", new StringBody(versione, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("LOGINNAME ", new StringBody(userName, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale PASSWORD
            mpEntityBuilder.addPart("PASSWORD ", new StringBody(password, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale XMLSIP, con il documento XML dei metadati
            mpEntityBuilder.addPart("XMLSIP", new StringBody(strXml, ContentType.TEXT_XML));

            log.info("Xml Richiesta: {}", strXml);
            log.debug("Aggiunta allegati alla richiesta");

            Map<String, File> fileDaAllegare = new HashMap<>();
            List<FileAllegato> lista = unitaDoc.getDocumentoPrincipale().getComponenti();
            for (FileAllegato fileAllegato : lista) {
                fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
            }
            if (unitaDoc.getAllegati() != null && !unitaDoc.getAllegati().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAllegati();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }
            if (unitaDoc.getAnnessi() != null && !unitaDoc.getAnnessi().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnessi();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }
            if (unitaDoc.getAnnotazioni() != null && !unitaDoc.getAnnotazioni().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnotazioni();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        fileDaAllegare.put(fileAllegato.getId(), fileAllegato.getFileVersato());
                    }
                }
            }

            Set<String> ids = fileDaAllegare.keySet();
            int filesCount = 0;

            for (String string : ids) {

                is = new FileInputStream(fileDaAllegare.get(string));

                InputStreamBody bin = new InputStreamBody(is, "file1");

                // aggiunge alla request il il campo binario(file), con il file
                // appena caricato.
                // il nome del campo _deve_ coincidere con uno degli ID indicati
                // nell'XML
                mpEntityBuilder.addPart(string, bin);
                filesCount++;
            }

            log.info("Aggiunti {} file alla richiesta", filesCount);
            httppost.setEntity(mpEntityBuilder.build());
            log.info("eseguo la richiesta... {}", httppost.getRequestLine());

            // istanzio un context http con i cookie impostati da NAM
            CookieStore cookieStore = new BasicCookieStore();
            for (Map<String, String> cookieMap : listaCookie) {
                BasicClientCookie cookie = new BasicClientCookie(cookieMap.get("name"), cookieMap.get("value"));
                cookieStore.addCookie(cookie);
            }
            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

            // invoca il web service
            HttpResponse response = httpclient.execute(httppost, localContext);
            // recupera la risposta
            HttpEntity resEntity = response.getEntity();
            String page = "";
            if (resEntity != null) {
                page = EntityUtils.toString(resEntity);
                log.info("Risposta : {}", page);
            }
            return page;
        } catch (Exception e) {
            throw new VersoException(e, VersoErrorCategory.INTERNAL_ERROR);
        } finally {
            if (is != null) {
                is.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
    }

    // versione con autenticazione
    public String invocaVersamentoSync(String username, String password, DatiUnitaDocumentaria unitaDoc, URI uploadUrl,
            List<Map<String, String>> listaCookie, String strXml)
            throws AuthenticationException, IllegalCharsetNameException, IOException, VersoException {
        if (username == null || password == null) {
            throw new AuthenticationException("username e/o password non valorizzati");
        }

        String usernameWebService = username;
        versione = Constants.VERSIONE_VERSAMENTO;

        log.info("Stringa xml versamento : {}", strXml);
        String headerValue = username + ":" + password;
        String headerValueBase64 = Base64.encodeBase64String(headerValue.getBytes());

        InputStream is = null;
        // new lib
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(uploadUrl);

            httppost.addHeader("Authorization", "Basic " + headerValueBase64);

            // new lib
            // Inizializza la request come multipart, nella modalità browser compatible che
            // consente di inviare i dati come campi di una form web
            MultipartEntityBuilder mpEntityBuilder = MultipartEntityBuilder.create();
            mpEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("VERSIONE", new StringBody(versione, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale LOGINNAME
            mpEntityBuilder.addPart("LOGINNAME ", new StringBody(usernameWebService, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale PASSWORD
            mpEntityBuilder.addPart("PASSWORD ", new StringBody(password, ContentType.TEXT_PLAIN));
            // aggiunge alla request il campo testuale XMLSIP, con il documento XML dei
            // metadati
            mpEntityBuilder.addPart("XMLSIP", new StringBody(strXml, ContentType.TEXT_XML));

            log.debug("Xml Richiesta: {}", strXml);
            log.debug("Aggiunta allegati alla richiesta");
            HashMap<String, String> mappaFile = new HashMap<>();
            List<FileAllegato> lista = unitaDoc.getDocumentoPrincipale().getComponenti();
            for (FileAllegato fileAllegato : lista) {
                mappaFile.put(fileAllegato.getId(), fileAllegato.getCodAllegato());
            }
            if (unitaDoc.getAllegati() != null && !unitaDoc.getAllegati().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAllegati();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        mappaFile.put(fileAllegato.getId(), fileAllegato.getCodAllegato());
                    }
                }
            }
            if (unitaDoc.getAnnessi() != null && !unitaDoc.getAnnessi().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnessi();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        mappaFile.put(fileAllegato.getId(), fileAllegato.getCodAllegato());
                    }
                }
            }
            if (unitaDoc.getAnnotazioni() != null && !unitaDoc.getAnnotazioni().isEmpty()) {
                List<Documento> allegati = unitaDoc.getAnnotazioni();
                for (Documento documento : allegati) {
                    lista = documento.getComponenti();
                    for (FileAllegato fileAllegato : lista) {
                        mappaFile.put(fileAllegato.getId(), fileAllegato.getCodAllegato());
                    }
                }
            }

            int filesCount = 0;
            Set<String> chiavi = mappaFile.keySet();

            for (String id : chiavi) {
                String codAllegato = mappaFile.get(id);
                is = getBlobInputStreamForIdComponente(codAllegato);

                InputStreamBody bin = new InputStreamBody(is, "file1");

                // aggiunge alla request il il campo binario(file), con il file
                // appena caricato.
                // il nome del campo _deve_ coincidere con uno degli ID indicati
                // nello XML
                mpEntityBuilder.addPart(id, bin);
                filesCount++;
            }

            log.info("Aggiunti {} file alla richiesta", filesCount);
            httppost.setEntity(mpEntityBuilder.build());
            log.info("eseguo la richiesta... {}", httppost.getRequestLine());

            // istanzio un context http con i cookie impostati da NAM
            CookieStore cookieStore = new BasicCookieStore();
            if (listaCookie != null) {
                for (Map<String, String> cookieMap : listaCookie) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieMap.get("name"), cookieMap.get("value"));
                    cookieStore.addCookie(cookie);
                }
            }
            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

            // invoca il web service
            HttpResponse response = httpclient.execute(httppost, localContext);
            // recupera la risposta
            HttpEntity resEntity = response.getEntity();
            String page = "";
            if (resEntity != null) {
                page = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);
                log.info("Risposta : {}", page);
            }
            return page;
        } catch (Exception e) {
            throw new VersoException(e, VersoErrorCategory.INTERNAL_ERROR);
        } finally {
            if (is != null) {
                is.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
    }

    /**
     * Crea la stringa xml della richiesta
     * 
     * @param unitaDoc
     *            Bean contenente i dati da usare per la costruzione della richiesta
     * 
     * @return
     * 
     * @throws Exception
     */
    public String creaRequestVersamento(DatiUnitaDocumentaria unitaDoc) throws Exception {

        log.info("Inizio creazione xml per versamento.");
        log.info("Encoding usato {}", encoding);
        Marshaller marshaller = jaxbContext.createMarshaller();
        ObjectFactory objectFactory = new ObjectFactory();
        UnitaDocumentaria documento = creaUnitaDocumentaria(unitaDoc, objectFactory);
        StringWriter writer = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(documento, writer);
        String versamento = new String(writer.getBuffer().toString().getBytes(encoding));
        writer.close();

        return versamento;
    }

    /**
     * Crea la stringa xml della richiesta
     * 
     * @param datiUnitaDocAll
     *            Bean contenente i dati da usare per la costruzione della richiesta
     * 
     * @return
     * 
     * @throws Exception
     */
    public String creaRequestAggiuntaAllegati(DatiUnitaDocAggAllegati datiUnitaDocAll) throws Exception {
        log.info("Inizio creazione xml per aggiunta allegati.");
        log.info("Encoding usato {}", encoding);
        Marshaller marshaller = jaxbContext.createMarshaller();
        ObjectFactory objectFactory = new ObjectFactory();
        UnitaDocAggAllegati documento = creaUnitaDocAggAllegati(datiUnitaDocAll, objectFactory);
        StringWriter writer = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(documento, writer);
        String aggiuntaAllegati = new String(writer.getBuffer().toString().getBytes(encoding));
        writer.close();
        return aggiuntaAllegati;
    }

    private UnitaDocAggAllegati creaUnitaDocAggAllegati(DatiUnitaDocAggAllegati datiUnitaDocAll,
            ObjectFactory objectFactory) throws DatatypeConfigurationException, JAXBException {
        UnitaDocAggAllegati unitaDocAll = objectFactory.createUnitaDocAggAllegati();
        Intestazione intestazione = datiUnitaDocAll.getIntestazione();
        if (intestazione != null) {
            IntestazioneAggAllType intestazioneAggAll = creaIntestazioneAggAll(intestazione, objectFactory);
            unitaDocAll.setIntestazione(intestazioneAggAll);
        }
        Configurazione configurazione = datiUnitaDocAll.getConfigurazione();
        if (configurazione != null) {
            ConfigAggAllType configurazioneAggAll = creaConfigurazioneAggAll(configurazione, objectFactory);
            unitaDocAll.setConfigurazione(configurazioneAggAll);
        }
        Documento datiDocumento = datiUnitaDocAll.getDatiDocumento();
        TipoDocumento tipoDocumento = datiUnitaDocAll.getTipoDocumento();
        if (tipoDocumento != null && datiDocumento != null) {
            DocumentoType documentoChoice = creaDocumento(datiDocumento, objectFactory);
            switch (tipoDocumento) {
            case ALLEGATO:
                unitaDocAll.setAllegato(documentoChoice);
                break;
            case ANNESSO:
                unitaDocAll.setAnnesso(documentoChoice);
                break;
            case ANNOTAZIONE:
                unitaDocAll.setAnnotazione(documentoChoice);
                break;
            }
        }
        return unitaDocAll;
    }

    private ConfigAggAllType creaConfigurazioneAggAll(Configurazione configurazione, ObjectFactory objectFactory) {
        ConfigAggAllType config = objectFactory.createConfigAggAllType();
        config.setForzaAccettazione(configurazione.isForzaAccettazione());
        config.setForzaConservazione(configurazione.isForzaConservazione());
        config.setSimulaSalvataggioDatiInDB(configurazione.isSimulaSalvataggioDatiInDB());
        config.setSistemaDiMigrazione(configurazione.getSistemaDiMigrazione());
        TipoConservazione tipoConserva = configurazione.getTipoConservazione();
        if (tipoConserva != null) {
            TipoConservazioneType tipoConservazione;
            if (tipoConserva == TipoConservazione.MIGRAZIONE) {
                tipoConservazione = TipoConservazioneType.MIGRAZIONE;
            } else if (tipoConserva == TipoConservazione.FISCALE) {
                tipoConservazione = TipoConservazioneType.FISCALE;
            } else {
                tipoConservazione = TipoConservazioneType.VERSAMENTO_ANTICIPATO;
            }
            config.setTipoConservazione(tipoConservazione);
        }
        return config;
    }

    private IntestazioneAggAllType creaIntestazioneAggAll(Intestazione datiIntestazione, ObjectFactory objectFactory) {
        IntestazioneAggAllType intestazione = objectFactory.createIntestazioneAggAllType();
        intestazione.setVersione(datiIntestazione.getVersione());
        VersatoreType versatore = creaVersatore(datiIntestazione, objectFactory);
        intestazione.setVersatore(versatore);
        ChiaveType chiave = creaChiave(datiIntestazione.getChiave(), objectFactory);
        intestazione.setChiave(chiave);
        return intestazione;
    }

    private UnitaDocumentaria creaUnitaDocumentaria(DatiUnitaDocumentaria datiUnitaDoc, ObjectFactory objectFactory)
            throws DatatypeConfigurationException, JAXBException {
        UnitaDocumentaria unitaDoc = objectFactory.createUnitaDocumentaria();
        if (datiUnitaDoc.getIntestazione() != null) {
            IntestazioneType intestazione = creaIntestazione(datiUnitaDoc.getIntestazione(), objectFactory);
            unitaDoc.setIntestazione(intestazione);
        }
        if (datiUnitaDoc.getConfigurazione() != null) {
            ConfigType configurazione = creaConfigurazione(datiUnitaDoc.getConfigurazione(), objectFactory);
            unitaDoc.setConfigurazione(configurazione);
        }

        if (datiUnitaDoc.getFascicoloPrincipale() != null) {
            ProfiloArchivisticoType profiloArchivistico = creaProfiloArchivistico(datiUnitaDoc.getFascicoloPrincipale(),
                    datiUnitaDoc.getFascicoliSecondari(), objectFactory);
            unitaDoc.setProfiloArchivistico(profiloArchivistico);
        }

        if (datiUnitaDoc.getOggetto() != null) {
            ProfiloUnitaDocumentariaType profiloUnitaDoc = creaProfiloUnitaDoc(datiUnitaDoc, objectFactory);
            unitaDoc.setProfiloUnitaDocumentaria(profiloUnitaDoc);

        }

        if (datiUnitaDoc.getDatiSpecifici() != null) {
            JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(datiUnitaDoc.getDatiSpecifici(),
                    objectFactory);
            unitaDoc.setDatiSpecifici(datiSpecifici);

        }
        if (datiUnitaDoc.getDatiSpecificiMigrazione() != null) {
            JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(datiUnitaDoc.getDatiSpecificiMigrazione(),
                    objectFactory);
            unitaDoc.setDatiSpecificiMigrazione(datiSpecifici);
        }

        unitaDoc.setNumeroAllegati(datiUnitaDoc.getNumeroAllegati());
        unitaDoc.setNumeroAnnessi(datiUnitaDoc.getNumeroAnnessi());
        unitaDoc.setNumeroAnnotazioni(datiUnitaDoc.getNumeroAnnotazioni());

        if (datiUnitaDoc.getDocumentoPrincipale() != null) {
            DocumentoType docPrincipale = creaDocumento(datiUnitaDoc.getDocumentoPrincipale(), objectFactory);

            unitaDoc.setDocumentoPrincipale(docPrincipale);
        }

        if (datiUnitaDoc.getAllegati() != null && !datiUnitaDoc.getAllegati().isEmpty()) {
            Allegati allegati = creaListaAllegati(datiUnitaDoc.getAllegati(), objectFactory);
            unitaDoc.setAllegati(allegati);
        }

        if (datiUnitaDoc.getAnnessi() != null && !datiUnitaDoc.getAnnessi().isEmpty()) {
            Annessi annessi = creaListaAnnessi(datiUnitaDoc.getAnnessi(), objectFactory);
            unitaDoc.setAnnessi(annessi);
        }

        if (datiUnitaDoc.getAnnotazioni() != null && !datiUnitaDoc.getAnnotazioni().isEmpty()) {
            Annotazioni annotazioni = creaListaAnnotazioni(datiUnitaDoc.getAnnotazioni(), objectFactory);
            unitaDoc.setAnnotazioni(annotazioni);
        }
        if (datiUnitaDoc.getDocumentiCollegati() != null && !datiUnitaDoc.getDocumentiCollegati().isEmpty()) {

            unitaDoc.setDocumentiCollegati(creaDocumentiCollegati(datiUnitaDoc.getDocumentiCollegati(), objectFactory));

        }

        return unitaDoc;
    }

    private Allegati creaListaAllegati(List<Documento> allegati, ObjectFactory objectFactory)
            throws DatatypeConfigurationException, JAXBException {
        Allegati returnValue = objectFactory.createUnitaDocumentariaAllegati();
        if (returnValue.getAllegato() == null)
            return null;
        returnValue.getAllegato().addAll(creaListaDocument(allegati, objectFactory));
        return returnValue;
    }

    private Annessi creaListaAnnessi(List<Documento> annessi, ObjectFactory objectFactory)
            throws DatatypeConfigurationException, JAXBException {
        Annessi returnValue = objectFactory.createUnitaDocumentariaAnnessi();
        returnValue.getAnnesso().addAll(creaListaDocument(annessi, objectFactory));
        return returnValue;
    }

    private Annotazioni creaListaAnnotazioni(List<Documento> annotazioni, ObjectFactory objectFactory)
            throws DatatypeConfigurationException, JAXBException {
        Annotazioni returnValue = objectFactory.createUnitaDocumentariaAnnotazioni();
        returnValue.getAnnotazione().addAll(creaListaDocument(annotazioni, objectFactory));
        return returnValue;
    }

    private Collection<DocumentoType> creaListaDocument(List<Documento> allegati, ObjectFactory objectFactory)
            throws DatatypeConfigurationException, JAXBException {
        Collection<DocumentoType> listaDoc = new ArrayList<DocumentoType>();
        for (Documento documento : allegati) {
            DocumentoType doc = creaDocumento(documento, objectFactory);
            listaDoc.add(doc);
        }
        return listaDoc;
    }

    private DocumentoCollegatoType creaDocumentiCollegati(List<DocumentoCollegato> parCollegamenti,
            ObjectFactory objectFactory) throws JAXBException {
        DocumentoCollegatoType docs = objectFactory.createDocumentoCollegatoType();
        List<net.datasiel.par.jaxb.versamento.DocumentoCollegatoType.DocumentoCollegato> lista = creaListaDocumentiCollegati(
                parCollegamenti);
        docs.getDocumentoCollegato().addAll(lista);
        return docs;

    }

    private List<net.datasiel.par.jaxb.versamento.DocumentoCollegatoType.DocumentoCollegato> creaListaDocumentiCollegati(
            List<DocumentoCollegato> documentiCollegati) {
        List<net.datasiel.par.jaxb.versamento.DocumentoCollegatoType.DocumentoCollegato> lista = new ArrayList<>();
        for (DocumentoCollegato documentoCollegato : documentiCollegati) {
            net.datasiel.par.jaxb.versamento.DocumentoCollegatoType.DocumentoCollegato doc = new net.datasiel.par.jaxb.versamento.DocumentoCollegatoType.DocumentoCollegato();
            ChiaveType chiaveType = creaChiaveType(documentoCollegato.getChiaveCollegamento());
            doc.setChiaveCollegamento(chiaveType);
            doc.setDescrizioneCollegamento(documentoCollegato.getDescrizioneCollegamento());
            lista.add(doc);
        }
        return lista;
    }

    private ChiaveType creaChiaveType(Chiave chiaveCollegamento) {
        ChiaveType chiaveType = new ChiaveType();
        chiaveType.setAnno(chiaveCollegamento.getAnno().intValue());
        chiaveType.setNumero(chiaveCollegamento.getNumero());
        chiaveType.setTipoRegistro(chiaveCollegamento.getTipoRegistro());

        return chiaveType;
    }

    private JAXBElement<DatiSpecificiType> creaDatiSpecifici(DatiSpecifici datiSpecifici, ObjectFactory objectFactory) {

        DatiSpecificiType datiSpec = objectFactory.createDatiSpecificiType();
        datiSpec.setVersioneDatiSpecifici(datiSpecifici.getVersioneDatiSpecifici());

        // Estrarre i figli di DatiSpecifici
        Document docDatiSpecifici = datiSpecifici.getDocDatiSpecifici();
        // Il primo elemento è
        Element elementoDatiSpecifici = docDatiSpecifici.getDocumentElement();

        NodeList elementi = elementoDatiSpecifici.getChildNodes();
        for (int i = 0; i < elementi.getLength(); i++) {
            Node currentNode = elementi.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                datiSpec.getAny().add(currentNode);
                if (log.isDebugEnabled()) {
                    log.debug("Aggiungo elemento {}", currentNode.getLocalName());
                }
            }
        }
        return objectFactory.createUnitaDocumentariaDatiSpecifici(datiSpec);
    }

    private DocumentoType creaDocumento(Documento datiDoc, ObjectFactory objectFactory)
            throws DatatypeConfigurationException {
        DocumentoType doc = objectFactory.createDocumentoType();

        doc.setIDDocumento(datiDoc.getIdDocumento());
        doc.setTipoDocumento(datiDoc.getTipoDocumento());
        if (datiDoc.getProfiloAutoreDoc() != null || datiDoc.getProfiloDescrizioneDoc() != null) {
            ProfiloDocumentoType profiloDocPrincipale = objectFactory.createProfiloDocumentoType();
            profiloDocPrincipale.setAutore(datiDoc.getProfiloAutoreDoc());
            profiloDocPrincipale.setDescrizione(datiDoc.getProfiloDescrizioneDoc());
            doc.setProfiloDocumento(profiloDocPrincipale);
        }
        if (datiDoc.getDatiSpecifici() != null) {
            JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(datiDoc.getDatiSpecifici(), objectFactory);
            doc.setDatiSpecifici(datiSpecifici);
        }
        if (datiDoc.getDatiSpecificiMigrazione() != null) {
            JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(datiDoc.getDatiSpecificiMigrazione(),
                    objectFactory);
            doc.setDatiSpecificiMigrazione(datiSpecifici);

        }
        if (datiDoc.getDatiFiscali() != null) {
            DatiFiscaliType datiFiscali = creaDatiFiscali(datiDoc.getDatiFiscali(), objectFactory);
            doc.setDatiFiscali(datiFiscali);
        }

        log.debug("Inizio gestione componenti struttura originale");

        StrutturaType strutturaOriginale = objectFactory.createStrutturaType();
        strutturaOriginale.setTipoStruttura(datiDoc.getTipoStruttura());

        Componenti componenti = objectFactory.createStrutturaTypeComponenti();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        List<FileAllegato> fileDaAllegare = datiDoc.getComponenti();
        for (FileAllegato fileAllegato : fileDaAllegare) {
            ComponenteType componente = objectFactory.createComponenteType();
            componente.setID(fileAllegato.getId());
            componente.setOrdinePresentazione(fileAllegato.getOrdinePresentazione());
            componente.setTipoComponente(fileAllegato.getTipoComponente());

            TipoSupporto tipoSupporto = fileAllegato.getTipoSupportoComponente();
            if (tipoSupporto != null) {
                if (tipoSupporto.equals(TipoSupporto.METADATI)) {
                    componente.setTipoSupportoComponente(TipoSupportoType.METADATI);
                } else if (tipoSupporto.equals(TipoSupporto.RIFERIMENTO)) {
                    componente.setTipoSupportoComponente(TipoSupportoType.RIFERIMENTO);
                } else {
                    componente.setTipoSupportoComponente(TipoSupportoType.FILE);
                }
            }
            ChiaveType chiaveRiferimento = objectFactory.createChiaveType();
            Chiave riferimento = fileAllegato.getRiferimento();
            if (riferimento != null) {
                chiaveRiferimento.setAnno(riferimento.getAnno().intValue());
                chiaveRiferimento.setNumero(riferimento.getNumero());
                chiaveRiferimento.setTipoRegistro(riferimento.getTipoRegistro());
                componente.setRiferimento(chiaveRiferimento);
            }
            componente.setTipoRappresentazioneComponente(fileAllegato.getTipoRappresentazioneComponente());
            componente.setNomeComponente(fileAllegato.getNomeComponente());
            componente.setFormatoFileVersato(fileAllegato.getFormatoFileVersato());
            componente.setHashVersato(fileAllegato.getHashVersato());
            componente.setUrnVersato(fileAllegato.getUrnVersato());
            componente.setIDComponenteVersato(fileAllegato.getIdComponenteVersato());

            if (fileAllegato.getDatiSpecifici() != null) {
                JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(fileAllegato.getDatiSpecifici(),
                        objectFactory);
                componente.setDatiSpecifici(datiSpecifici);
            }
            if (fileAllegato.getDatiSpecificiMigrazione() != null) {
                JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(
                        fileAllegato.getDatiSpecificiMigrazione(), objectFactory);
                componente.setDatiSpecificiMigrazione(datiSpecifici);
            }

            componente.setUtilizzoDataFirmaPerRifTemp(fileAllegato.isUtilizzoDataFirmaPerRifTemp());

            XMLGregorianCalendar data = dateConversion2XsdDateTime(fileAllegato.getRiferimentoTemporale(),
                    datatypeFactory);
            componente.setRiferimentoTemporale(data);
            componente.setDescrizioneRiferimentoTemporale(fileAllegato.getDescrizioneRiferimentoTemporale());

            List<FileAllegatoBase> sottoComponenti = fileAllegato.getSottoComponenti();
            if (sottoComponenti != null) {
                log.debug("Ci sono sotto componenti da aggiungere");
                ComponenteType.SottoComponenti elementoSottoComponenti = objectFactory
                        .createComponenteTypeSottoComponenti();
                for (FileAllegatoBase fileAllegatoBase : sottoComponenti) {
                    SottoComponenteType sottoComponente = objectFactory.createSottoComponenteType();
                    sottoComponente.setID(fileAllegatoBase.getId());
                    sottoComponente.setOrdinePresentazione(fileAllegatoBase.getOrdinePresentazione());
                    sottoComponente.setTipoComponente(fileAllegatoBase.getTipoComponente());
                    TipoSupporto tipoSupportoSottoComponente = fileAllegatoBase.getTipoSupportoComponente();
                    if (tipoSupportoSottoComponente.equals(TipoSupporto.METADATI)) {
                        sottoComponente.setTipoSupportoComponente(TipoSupportoType.METADATI);
                    } else if (tipoSupporto.equals(TipoSupporto.RIFERIMENTO)) {
                        sottoComponente.setTipoSupportoComponente(TipoSupportoType.RIFERIMENTO);
                    } else {
                        sottoComponente.setTipoSupportoComponente(TipoSupportoType.FILE);
                    }
                    ChiaveType chiaveRifSottoComp = objectFactory.createChiaveType();
                    Chiave riferimentoSottoComp = fileAllegatoBase.getRiferimento();
                    if (riferimentoSottoComp != null) {
                        chiaveRifSottoComp.setAnno(riferimentoSottoComp.getAnno().intValue());
                        chiaveRifSottoComp.setNumero(riferimentoSottoComp.getNumero());
                        chiaveRifSottoComp.setTipoRegistro(riferimentoSottoComp.getTipoRegistro());
                    }
                    sottoComponente.setRiferimento(chiaveRifSottoComp);
                    sottoComponente.setNomeComponente(fileAllegatoBase.getNomeComponente());
                    sottoComponente.setFormatoFileVersato(fileAllegatoBase.getFormatoFileVersato());
                    sottoComponente.setUrnVersato(fileAllegatoBase.getUrnVersato());
                    sottoComponente.setIDComponenteVersato(fileAllegatoBase.getIdComponenteVersato());

                    if (fileAllegatoBase.getDatiSpecifici() != null) {
                        JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(
                                fileAllegatoBase.getDatiSpecifici(), objectFactory);
                        sottoComponente.setDatiSpecifici(datiSpecifici);
                    }

                    if (fileAllegatoBase.getDatiSpecificiMigrazione() != null) {
                        JAXBElement<DatiSpecificiType> datiSpecifici = creaDatiSpecifici(
                                fileAllegatoBase.getDatiSpecificiMigrazione(), objectFactory);
                        sottoComponente.setDatiSpecificiMigrazione(datiSpecifici);
                    }
                    elementoSottoComponenti.getSottoComponente().add(sottoComponente);
                }
                componente.setSottoComponenti(elementoSottoComponenti);
            }
            log.debug("Fine ciclo su sotto componenti da allegare");

            componenti.getComponente().add(componente);
        }
        log.debug("Fine ciclo su file da allegare");

        strutturaOriginale.setComponenti(componenti);
        doc.setStrutturaOriginale(strutturaOriginale);
        return doc;
    }

    private DatiFiscaliType creaDatiFiscali(DatiFiscali datiFiscali, ObjectFactory objectFactory)
            throws DatatypeConfigurationException {
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        DatiFiscaliType datiFiscaliType = objectFactory.createDatiFiscaliType();
        datiFiscaliType.setDenominazione(datiFiscali.getDenominazione());
        datiFiscaliType.setNome(datiFiscali.getNome());
        datiFiscaliType.setCognome(datiFiscali.getCognome());
        datiFiscaliType.setCF(datiFiscali.getCF());
        datiFiscaliType.setPIVA(datiFiscali.getPIVA());
        datiFiscaliType.setDataEmissione(dateConversion2XsdDate(datiFiscali.getDataEmissione(), datatypeFactory));

        Long numeroProgressivo = datiFiscali.getNumeroProgressivo();
        if (numeroProgressivo != null) {
            datiFiscaliType.setNumeroProgressivo(numeroProgressivo);
        }

        datiFiscaliType.setRegistro(datiFiscali.getRegistro());
        datiFiscaliType.setPeriodoFiscale(datiFiscali.getPeriodoFiscale());
        datiFiscaliType.setDataTermineEmissione(
                dateConversion2XsdDate(datiFiscali.getDataTermineEmissione(), datatypeFactory));
        return datiFiscaliType;
    }

    private ProfiloUnitaDocumentariaType creaProfiloUnitaDoc(DatiUnitaDocumentaria datiUnitaDoc,
            ObjectFactory objectFactory) throws DatatypeConfigurationException {
        ProfiloUnitaDocumentariaType profiloUnitaDoc = objectFactory.createProfiloUnitaDocumentariaType();
        profiloUnitaDoc.setOggetto(datiUnitaDoc.getOggetto());

        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar data = dateConversion2XsdDate(datiUnitaDoc.getData(), datatypeFactory);
        profiloUnitaDoc.setData(data);

        return profiloUnitaDoc;
    }

    private ProfiloArchivisticoType creaProfiloArchivistico(CamiciaFascicolo datiFascicoloPrincipale,
            List<CamiciaFascicolo> datiFascicoliSecondari, ObjectFactory objectFactory) {

        ProfiloArchivisticoType profiloArch = objectFactory.createProfiloArchivisticoType();

        CamiciaFascicoloType camiciaFascicoloPrincipale = objectFactory.createCamiciaFascicoloType();
        camiciaFascicoloPrincipale.setClassifica(datiFascicoloPrincipale.getClassifica());
        if (datiFascicoloPrincipale.getFascicolo() != null) {
            FascicoloType fascicolo = creaFascicolo(datiFascicoloPrincipale.getFascicolo(), objectFactory);
            camiciaFascicoloPrincipale.setFascicolo(fascicolo);
        }

        if (datiFascicoloPrincipale.getSottoFascicolo() != null) {
            FascicoloType sottoFascicolo = creaFascicolo(datiFascicoloPrincipale.getSottoFascicolo(), objectFactory);
            camiciaFascicoloPrincipale.setSottoFascicolo(sottoFascicolo);
        }
        profiloArch.setFascicoloPrincipale(camiciaFascicoloPrincipale);

        if (datiFascicoliSecondari != null && !datiFascicoliSecondari.isEmpty()) {
            FascicoliSecondari listaFascicoliSecondari = objectFactory
                    .createProfiloArchivisticoTypeFascicoliSecondari();
            for (CamiciaFascicolo datiCamiciaFascicolo : datiFascicoliSecondari) {
                CamiciaFascicoloType camiciaFascicoloSecondario = objectFactory.createCamiciaFascicoloType();
                camiciaFascicoloSecondario.setClassifica(datiCamiciaFascicolo.getClassifica());
                Fascicolo fascicolo = datiCamiciaFascicolo.getFascicolo();
                if (fascicolo != null) {
                    camiciaFascicoloSecondario.setFascicolo(creaFascicolo(fascicolo, objectFactory));
                }
                Fascicolo sottoFascicolo = datiCamiciaFascicolo.getSottoFascicolo();
                if (sottoFascicolo != null) {
                    camiciaFascicoloSecondario.setSottoFascicolo(creaFascicolo(sottoFascicolo, objectFactory));
                }
                listaFascicoliSecondari.getFascicoloSecondario().add(camiciaFascicoloSecondario);
            }
            profiloArch.setFascicoliSecondari(listaFascicoliSecondari);
        }

        return profiloArch;
    }

    private FascicoloType creaFascicolo(Fascicolo datiFascicolo, ObjectFactory objectFactory) {
        FascicoloType fascicolo = objectFactory.createFascicoloType();
        String identificativo = datiFascicolo.getIdentificativo();
        String oggetto = datiFascicolo.getOggetto();
        if (StringUtils.isNotEmpty(oggetto) && StringUtils.isNotEmpty(identificativo)) {
            fascicolo.setIdentificativo(identificativo);
            fascicolo.setOggetto(oggetto);
            return fascicolo;
        } else {
            return null;
        }
    }

    private ConfigType creaConfigurazione(Configurazione configurazione, ObjectFactory objectFactory) {
        ConfigType config = objectFactory.createConfigType();
        config.setForzaAccettazione(configurazione.isForzaAccettazione());
        config.setForzaCollegamento(configurazione.isForzaCollegamento());
        config.setForzaConservazione(configurazione.isForzaConservazione());
        config.setSimulaSalvataggioDatiInDB(configurazione.isSimulaSalvataggioDatiInDB());
        config.setSistemaDiMigrazione(configurazione.getSistemaDiMigrazione());
        TipoConservazione tipoConserva = configurazione.getTipoConservazione();
        if (tipoConserva != null) {
            TipoConservazioneType tipoConservazione;
            if (tipoConserva == TipoConservazione.MIGRAZIONE) {
                tipoConservazione = TipoConservazioneType.MIGRAZIONE;
            } else if (tipoConserva == TipoConservazione.FISCALE) {
                tipoConservazione = TipoConservazioneType.FISCALE;
            } else {
                tipoConservazione = TipoConservazioneType.VERSAMENTO_ANTICIPATO;
            }
            config.setTipoConservazione(tipoConservazione);
        }
        return config;
    }

    private IntestazioneType creaIntestazione(Intestazione datiIntestazione, ObjectFactory objectFactory) {
        IntestazioneType intestazione = objectFactory.createIntestazioneType();
        intestazione.setVersione(datiIntestazione.getVersione());
        VersatoreType versatore = creaVersatore(datiIntestazione, objectFactory);
        intestazione.setVersatore(versatore);

        ChiaveType chiave = creaChiave(datiIntestazione.getChiave(), objectFactory);
        intestazione.setChiave(chiave);

        intestazione.setTipologiaUnitaDocumentaria(datiIntestazione.getTipologiaUnitaDocumentaria());

        return intestazione;
    }

    private ChiaveType creaChiave(Chiave datiChiave, ObjectFactory objectFactory) {
        ChiaveType chiave = objectFactory.createChiaveType();
        if (datiChiave.getAnno() != null) {
            chiave.setAnno(datiChiave.getAnno().intValue());
        }
        chiave.setNumero(datiChiave.getNumero());
        chiave.setTipoRegistro(datiChiave.getTipoRegistro());
        return chiave;
    }

    private VersatoreType creaVersatore(Intestazione datiIntestazione, ObjectFactory objectFactory) {
        VersatoreType versatore = objectFactory.createVersatoreType();
        versatore.setAmbiente(datiIntestazione.getAmbiente());
        versatore.setEnte(datiIntestazione.getEnte());
        versatore.setStruttura(datiIntestazione.getStruttura());
        versatore.setUserID(datiIntestazione.getUserId());
        versatore.setUtente(datiIntestazione.getUtente());
        return versatore;
    }

    /**
     * Restituisce un XMLGregorianCalendare che risulterà, nell'xml, in una data nel formato YYYY-MM-DD
     * 
     * @param date
     *            La data da convertire
     * @param datatypeFactory
     *            DatatypeFactory da usare per creare l'oggetto XMLGregorianCalendar
     * 
     * @return Oggetto XMLGregorianCalendar corrispondente alla data ricevuta
     */
    private XMLGregorianCalendar dateConversion2XsdDateTime(DateTime date, DatatypeFactory datatypeFactory) {
        if (date != null) {
            GregorianCalendar dataC = date.toGregorianCalendar();
            return datatypeFactory.newXMLGregorianCalendar(dataC);
        } else {
            return null;
        }
    }

    private XMLGregorianCalendar dateConversion2XsdDate(DateTime date, DatatypeFactory datatypeFactory) {
        if (date != null) {
            return datatypeFactory.newXMLGregorianCalendarDate(date.getYear(), date.getMonthOfYear(),
                    date.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED);
        } else {
            return null;
        }
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        if (Charset.isSupported(encoding)) {
            this.encoding = encoding;
        }
    }

    public String getProperty(String key) {
        String value = null;
        try {
            value = cc.getProperty(key).toString();
        } catch (Exception e) {
            log.error("Errore nella lettura del proprietà con chiave '{}'", key);
        }
        return value;
    }

    public String getVersione() {
        return versione;
    }

    public String getLoginname() {
        return loginname;
    }

    public String getEndpoint() {
        return endpoint;
    }

    private InputStream getBlobInputStreamForIdComponente(String codAllegato) {
        Connection conn = null;
        InputStream ret = null;
        if (codAllegato != null) {
            DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
            if (datasource != null) {
                try {
                    conn = datasource.getConnection();
                    ParComponenteDAO parCompDao = new ParComponenteDAO();
                    ret = parCompDao.getBlobWhereByCodAllegato(codAllegato, conn);
                } catch (SQLException e) {
                    log.error("Generic error", e);
                } finally {
                    if (conn != null)
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            log.error("Generic error", e);
                        }
                }

            }
        }
        return ret;
    }
}
