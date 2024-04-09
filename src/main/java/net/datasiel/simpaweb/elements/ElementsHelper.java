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

package net.datasiel.simpaweb.elements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.fields.TextField;
import com.manydesigns.elements.fields.search.SelectSearchField;
import com.manydesigns.elements.fields.search.TextSearchField;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.forms.SearchForm;
import com.manydesigns.elements.forms.SearchFormBuilder;
import com.manydesigns.elements.options.DefaultSelectionProvider;
import com.manydesigns.elements.options.SearchDisplayMode;
import com.manydesigns.elements.options.SelectionProvider;

import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.VOrgStrut;
import net.datasiel.simpaweb.db.vo.VDecFormatoFileDocVO;
import net.datasiel.simpaweb.db.vo.VDecRegistroUnitaDocVO;

public class ElementsHelper {

    /***************************************************************
     * 
     * INIZIO PARTE CREAZIONE FORM
     * 
     ***************************************************************/

    static public Form buildIdFascicolo(String prefix) {
        Form form = null;
        form = new FormBuilder(ParFascicolo.class).configPrefix(prefix).configFields("idfascicolo")
                .configMode(Mode.HIDDEN).build();
        return form;
    }

    public static Form buildFascicolo(String titoloFieldset, String prefix, Mode mode) {
        Form form = null;
        form = new FormBuilder(ParFascicolo.class).configPrefix(prefix).configFieldSetNames(titoloFieldset)
                .configFields("classifica", "identificativo", "oggetto", "idsottofascicolo", "oggettosottofascicolo")
                .configMode(mode).build();
        ((TextField) form.findFieldByPropertyName("idsottofascicolo")).setLabel("Identificativo sottofascicolo");
        ((TextField) form.findFieldByPropertyName("oggettosottofascicolo")).setLabel("Oggetto sottofascicolo");
        return form;
    }

    public static Element buildCollegamento(String titoloFieldset, DefaultSelectionProvider selRegistroUnitaDoc,
            String prefix, Mode mode) {
        Form form = null;
        form = new FormBuilder(ParCollegamento.class).configPrefix(prefix).configFieldSetNames(titoloFieldset)
                .configFields("anno", "numero", "idRegistroUnitaDoc", "descrizione")
                .configSelectionProvider(selRegistroUnitaDoc, "idRegistroUnitaDoc").configMode(mode).build();
        return form;
    }

    public static Element buildEnteStruttura(DefaultSelectionProvider selEnteStruttura, boolean soloUnaStruttura,
            String prefix, Mode mode) {
        Form form = new FormBuilder(VOrgStrut.class).configPrefix(prefix).configFieldSetNames("")
                .configFields("idStrut").configSelectionProvider(selEnteStruttura, "idStrut").configMode(mode).build();
        form.findFieldByPropertyName("idStrut").setLabel("Ente - Struttura");
        if (soloUnaStruttura) {
            form.findFieldByPropertyName("idStrut").setUpdatable(false);
        }
        return form;
    }

    public static Element buildSearchFormVersamenti(SelectionProvider selTipoUnitaDoc) {
        SearchForm searchForm = new SearchFormBuilder(ParUnitadoc.class).configPrefix("datiUnitaDoc_")
                .configFields("idTipoUnitaDoc", "idRegistroUnitaDoc", "anno", "numero")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc").build();
        ((SelectSearchField) searchForm.get(0)).setDisplayMode(SearchDisplayMode.DROPDOWN);
        ((SelectSearchField) searchForm.get(1)).setDisplayMode(SearchDisplayMode.DROPDOWN);
        ((TextSearchField) searchForm.get(3)).setShowMatchMode(false);

        return searchForm;
    }

    /***************************************************************
     * 
     * FINE PARTE CREAZIONE FORM
     * 
     ***************************************************************/

    /***************************************************************
     * 
     * INIZIO PARTE CREAZIONE SELECTION PROVIDER
     * 
     ***************************************************************/

    public static DefaultSelectionProvider getTipiRegUniDoc(Long idStruttura, Long idUser, Connection con)
            throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append(
                "SELECT r.ID_REGISTRO_UNITA_DOC id_reg, r.CD_REGISTRO_UNITA_DOC ||' - '|| r.DS_REGISTRO_UNITA_DOC nome_reg, u.ID_TIPO_UNITA_DOC id_unidoc, u.NM_TIPO_UNITA_DOC nome_unidoc ");
        prepQuery.append("FROM V_DEC_TIPO_UNITA_DOC_AMMESSO a ");
        prepQuery.append("JOIN V_DEC_REGISTRO_UNITA_DOC r ");
        prepQuery.append("ON r.ID_REGISTRO_UNITA_DOC = a.ID_REGISTRO_UNITA_DOC ");
        prepQuery.append("JOIN V_DEC_TIPO_UNITA_DOC u ");
        prepQuery.append("ON (u.ID_TIPO_UNITA_DOC = a.ID_TIPO_UNITA_DOC AND r.ID_USER_IAM = u.ID_USER_IAM) ");
        prepQuery.append("WHERE u.ID_USER_IAM = ? AND u.ID_STRUT = ? ");
        prepQuery.append("ORDER BY nome_unidoc ");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idUser);
        pst.setLong(2, idStruttura);
        DefaultSelectionProvider selProv = new DefaultSelectionProvider("tipologiaUniDoc", 2);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Long idReg = rs.getLong("id_reg");
            String nomeReg = rs.getString("nome_reg");
            Long idUniDoc = rs.getLong("id_unidoc");
            String nomeUniDoc = rs.getString("nome_unidoc");
            Object[] values = { idUniDoc, idReg };
            String[] labels = { nomeUniDoc, nomeReg };
            selProv.appendRow(values, labels, true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return selProv;
    }

    public static DefaultSelectionProvider getRegistriUnitaDoc(Long idStruttura, Long idUser, Connection con)
            throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT ID_REGISTRO_UNITA_DOC, DS_REGISTRO_UNITA_DOC ");
        prepQuery.append("FROM V_DEC_REGISTRO_UNITA_DOC ");
        prepQuery.append("WHERE ID_STRUT = ? AND ID_USER_IAM = ?");

        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idStruttura);
        pst.setLong(2, idUser);

        DefaultSelectionProvider selRegistroUD = new DefaultSelectionProvider("registriUD");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            VDecRegistroUnitaDocVO registroUD = new VDecRegistroUnitaDocVO();
            registroUD.setIdRegistroUnitaDoc((rs.getLong("ID_REGISTRO_UNITA_DOC")));
            registroUD.setDsRegistroUnitaDoc(rs.getString("DS_REGISTRO_UNITA_DOC"));
            selRegistroUD.appendRow(registroUD.getIdRegistroUnitaDoc(), registroUD.getDsRegistroUnitaDoc(), true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);
        return selRegistroUD;
    }

    public static DefaultSelectionProvider getFormatoFileAmmessoPerComponente(Long idStrut, Connection con)
            throws SQLException {
        DefaultSelectionProvider returnList = new DefaultSelectionProvider("formatiFile");
        List<VDecFormatoFileDocVO> listaFormati = null;
        listaFormati = VDecFormatoFileDocVO.getFormatiAmmessi(idStrut, con);
        for (VDecFormatoFileDocVO vDecFormatoFileAmmessoVO : listaFormati) {
            Long codice = vDecFormatoFileAmmessoVO.getIdFormatoFileDoc();
            String descrizione = String.format("%s (%s)", vDecFormatoFileAmmessoVO.getDsFormato(),
                    vDecFormatoFileAmmessoVO.getNmFormato());
            returnList.appendRow(codice, descrizione, true);
        }

        return returnList;
    }

    public static DefaultSelectionProvider getCDVersione(Long idStruttura, String tiEntitaSacer, Long idTipoDoc,
            Connection con) throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT DISTINCT CD_VERSIONE_XSD id, CD_VERSIONE_XSD descrizione ");
        prepQuery.append("FROM V_DEC_XSD_DATI_SPEC ");
        prepQuery.append("WHERE ID_STRUT = ? AND TI_ENTITA_SACER = ? AND ID_TIPO_DOC = ? ");
        prepQuery.append("ORDER BY CD_VERSIONE_XSD DESC ");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idStruttura);
        pst.setString(2, tiEntitaSacer);
        if (idTipoDoc != null)
            pst.setLong(3, idTipoDoc);
        DefaultSelectionProvider selCDVersione = new DefaultSelectionProvider("cdVersioneXSD");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String codice = rs.getString("id");
            String descrizione = rs.getString("descrizione");
            selCDVersione.appendRow(codice, descrizione, true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return selCDVersione;

    }

    public static List<String> getOptionsXSDVersione(Long idStruttura, String tiEntitaSacer, Long idTipoDoc,
            Connection con) throws SQLException {
        ArrayList<String> lista = new ArrayList<>();
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT DISTINCT CD_VERSIONE_XSD id, CD_VERSIONE_XSD descrizione ");
        prepQuery.append("FROM V_DEC_XSD_DATI_SPEC ");
        prepQuery.append("WHERE ID_STRUT = ? AND TI_ENTITA_SACER = ? AND ID_TIPO_DOC = ? ");
        prepQuery.append("ORDER BY CD_VERSIONE_XSD DESC ");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idStruttura);
        pst.setString(2, tiEntitaSacer);
        if (idTipoDoc != null)
            pst.setLong(3, idTipoDoc);
        DefaultSelectionProvider selCDVersione = new DefaultSelectionProvider("cdVersioneXSD");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String codice = rs.getString("id");
            lista.add(codice);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return lista;

    }

    public static DefaultSelectionProvider getCDVersioneUniDoc(Long idStruttura, String tiEntitaSacer,
            Long idTipoUnitaDoc, Connection con) throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT DISTINCT CD_VERSIONE_XSD id, CD_VERSIONE_XSD descrizione ");
        prepQuery.append("FROM V_DEC_XSD_DATI_SPEC ");
        prepQuery.append("WHERE ID_STRUT = ? AND TI_ENTITA_SACER = ? AND ID_TIPO_UNITA_DOC = ? ");
        prepQuery.append("ORDER BY CD_VERSIONE_XSD DESC ");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idStruttura);
        pst.setString(2, tiEntitaSacer);
        if (idTipoUnitaDoc != null)
            pst.setLong(3, idTipoUnitaDoc);
        DefaultSelectionProvider selCDVersione = new DefaultSelectionProvider("cdVersioneXSD");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String codice = rs.getString("id");
            String descrizione = rs.getString("descrizione");
            selCDVersione.appendRow(codice, descrizione, true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return selCDVersione;
    }

    public static DefaultSelectionProvider getTipoStruttura(Long idStruttura, Connection con) throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT ID_TIPO_STRUT_DOC id, NM_TIPO_STRUT_DOC descrizione ");
        prepQuery.append("FROM V_DEC_TIPO_STRUT_DOC ");
        prepQuery.append("WHERE DT_SOPPRES = TO_DATE('31/12/2444','DD/MM/YYYY') AND ID_STRUT = ?");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idStruttura);
        DefaultSelectionProvider selTipoStruttura = new DefaultSelectionProvider("tipoStruttura");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Long codice = rs.getLong("id");
            String descrizione = rs.getString("descrizione");
            selTipoStruttura.appendRow(codice, descrizione, true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return selTipoStruttura;

    }

    public static String getNomeTipoStrutturaFromId(Long idTipoStruttura, Connection con) throws SQLException {
        String descrizione = null;
        StringBuilder prepQuery = new StringBuilder();
        prepQuery.append("SELECT NM_TIPO_STRUT_DOC descrizione ");
        prepQuery.append("FROM V_DEC_TIPO_STRUT_DOC ");
        prepQuery.append("WHERE ID_TIPO_STRUT_DOC = ?");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        pst.setLong(1, idTipoStruttura);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            descrizione = rs.getString("descrizione");
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return descrizione;

    }

    public static DefaultSelectionProvider getFormatiFile(Long idStrut, Connection con) throws SQLException {
        StringBuilder prepQuery = new StringBuilder();
        /*
         * SELECT ID_FORMATO_FILE_DOC, NM_FORMATO_FILE_DOC||' - '||DS_FORMATO_FILE_DOC, ID_TIPO_COMP_DOC,
         * NM_TIPO_COMP_DOC FROM V_DEC_TIPO_COMP_DOC WHERE ID_STRUT=41 ORDER BY 4,2;
         * prepQuery.append(" SELECT R.ID_FORMATO_FILE_DOC ID_FORMATO, ");
         * prepQuery.append(" R.NM_FORMATO_FILE_DOC||' - '|| R.DS_FORMATO_FILE_DOC NOME_FORMATO, ");
         * prepQuery.append(" U.ID_TIPO_COMP_DOC ID_TIPO_COMP_DOC, ");
         * prepQuery.append(" U.NM_TIPO_COMP_DOC NOME_TIPO_COMP_DOC ");
         * prepQuery.append(" FROM V_DEC_FORMATO_FILE_AMMESSO A ");
         * prepQuery.append(" JOIN V_DEC_FORMATO_FILE_DOC R ON R.ID_FORMATO_FILE_DOC = A.ID_FORMATO_FILE_DOC ");
         * prepQuery.append(" JOIN V_DEC_TIPO_COMP_DOC U ON U.ID_TIPO_COMP_DOC = A.ID_TIPO_COMP_DOC "); if
         * (idTipoStrutDoc!=null) prepQuery.append(" WHERE U.ID_TIPO_STRUT_DOC =?");
         * prepQuery.append(" ORDER BY 	NOME_TIPO_COMP_DOC, NOME_FORMATO");
         */
        prepQuery.append(
                "SELECT ID_FORMATO_FILE_DOC ID_FORMATO, NM_FORMATO_FILE_DOC||' - '||DS_FORMATO_FILE_DOC NOME_FORMATO, ID_TIPO_COMP_DOC, NM_TIPO_COMP_DOC ");
        prepQuery.append("FROM V_DEC_TIPO_COMP_DOC ");
        prepQuery.append("WHERE ID_STRUT = ? ");
        prepQuery.append("ORDER BY 4,2");
        PreparedStatement pst = con.prepareStatement(prepQuery.toString());
        if (idStrut != null) {
            pst.setLong(1, idStrut);
        }
        DefaultSelectionProvider selProv = new DefaultSelectionProvider("formatiFile", 2);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Long idFormato = rs.getLong("ID_FORMATO");
            String nomeFormato = rs.getString("NOME_FORMATO");
            Long idTipoCompDoc = rs.getLong("ID_TIPO_COMP_DOC");
            String nomeTipoCompDoc = rs.getString("NM_TIPO_COMP_DOC");
            Object[] values = { idTipoCompDoc, idFormato };
            String[] labels = { nomeTipoCompDoc, nomeFormato };
            selProv.appendRow(values, labels, true);
        }
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);

        return selProv;
    }

    /***************************************************************
     * 
     * FINE PARTE CREAZIONE SELECTION PROVIDER
     * 
     ***************************************************************/

}
