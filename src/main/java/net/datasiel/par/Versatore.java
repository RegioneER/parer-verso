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

package net.datasiel.par;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import net.datasiel.par.beans.DatiUnitaDocumentaria;
import net.datasiel.par.jaxb.versamento.UnitaDocumentaria;
//import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import net.datasiel.simpaweb.common.Constants;

@Deprecated
public class Versatore {

    static String user = "Versatore RL";
    static String password = "almeno8Car#";
    static Long numeroChiave = 36L;

    public void testUpload(String myUploadUrl, DatiUnitaDocumentaria uniDoc) throws Exception {
        // crea una nuova istanza di HttpClient, predisponendo la chiamata del metodo POST
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // Inizializza la request come multipart, nella modalità browser compatible che
            // consente di inviare i dati come campi di una form web
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            // aggiunge alla request il campo testuale VERSIONE
            reqEntity.addPart("VERSIONE", new StringBody(Constants.VERSIONE_VERSAMENTO));
            // aggiunge alla request il campo testuale LOGINNAME
            reqEntity.addPart("LOGINNAME ", new StringBody(user));
            // aggiunge alla request il campo testuale PASSWORD
            reqEntity.addPart("PASSWORD ", new StringBody(password));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testUpload(String myUploadUrl, String filename) throws Exception {

        // crea una nuova istanza di HttpClient, predisponendo la chiamata del metodo POST
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost(myUploadUrl);
            // Inizializza la request come multipart, nella modalità browser compatible che
            // consente di inviare i dati come campi di una form web
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            // aggiunge alla request il campo testuale VERSIONE
            reqEntity.addPart("VERSIONE", new StringBody(Constants.VERSIONE_VERSAMENTO));
            // aggiunge alla request il campo testuale LOGINNAME
            reqEntity.addPart("LOGINNAME ", new StringBody(user));
            // aggiunge alla request il campo testuale PASSWORD
            reqEntity.addPart("PASSWORD ", new StringBody(password));
            /*
             * LS: 18 novembre 2016 la riga commentata qui sotto utilizza la libreria "vietata"
             * com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl; Ho sostituito la chiamata con quella
             * standard
             */
            // DocumentBuilderFactory documentBuilderFactory = new DocumentBuilderFactoryImpl();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            File xmlFile = new File("D:\\progetti\\PoloArchivisticoRegionale\\VersamentoEpsilon.xml");
            String xmlSip = FileUtils.readFileToString(xmlFile, "UTF-8");
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = null;

            InputSource inputSource = new InputSource(IOUtils.toInputStream(xmlSip, "UTF-8"));

            doc = documentBuilder.parse(inputSource);
            String encoding = "UTF-8";
            JAXBContext jaxbContext = JAXBContext.newInstance("net.datasiel.par.jaxb.versamento");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // JAXBElement<UnitaDocumentaria> jaxbElement =
            // (JAXBElement<UnitaDocumentaria>)unmarshaller.unmarshal(doc);
            UnitaDocumentaria unitaDoc = (UnitaDocumentaria) unmarshaller.unmarshal(doc);
            numeroChiave++;
            unitaDoc.getIntestazione().getChiave().setNumero(numeroChiave.toString());

            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.encoding", encoding);
            marshaller.marshal(unitaDoc, writer);
            // Al momento di scrivere forzo l'encoding a ISO per non convertire i caratteri
            // non UTF-8 in due caratteri (UTF-8 dell UTF-8)
            encoding = "UTF-8";
            String versamentoXml = new String(writer.getBuffer().toString().getBytes(encoding));
            writer.close();

            /*
             */
            // aggiunge alla request il campo testuale XMLSIP, con il documento XML dei metadati
            reqEntity.addPart("XMLSIP", new StringBody(versamentoXml));
            // crea un nuovo FileBody, leggendo i dati dal file system, indicandone il MIME Type
            FileBody bin = new FileBody(new File(filename), "binary/octet-stream");
            // aggiunge alla request il il campo binario(file), con il file appena caricato.
            // il nome del campo _deve_ coincidere con uno degli ID indicati nell'XML
            reqEntity.addPart("ID1", bin);

            // imposta la chiamata del metodo POST con i dati appena caricati
            httppost.setEntity(reqEntity);
            System.out.println("eseguo la richiesta... " + httppost.getRequestLine());
            // invoca il web service
            HttpResponse response = httpclient.execute(httppost);
            // recupera la risposta
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String page = EntityUtils.toString(resEntity);
                System.out.println("Risposta :" + page);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String urlServizio = "http://sacers.datasiel.net:8080/sacer/VersamentoSync";
        String filename = "D:\\progetti\\PoloArchivisticoRegionale\\epsilon.pdf";
        try {
            testUpload(urlServizio, filename);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
