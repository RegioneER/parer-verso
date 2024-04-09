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

package net.datasiel.simpaweb.db.vo;

/**
 * V_dec_xsd_dati_spec
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.VDecXsdDatiSpecDAO;

public class VDecXsdDatiSpecVO extends VDecXsdDatiSpecDAO {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(VDecXsdDatiSpecVO.class);

    private String strXsdDatiSpec;

    public VDecXsdDatiSpecVO() {
        super();
    }

    /**
     * Controlla se devono essere gestiti dati specifici di unità documentaria per la tipologia indicata (passata anche
     * l'idStruttura anche se non sarebbe necessario)
     *
     * @param idStrut
     *            Identificativo della struttura
     * @param idTipoUnitaDoc
     *            Identificativo della tipologia di UD
     * @param con
     *            Connessione al db
     * 
     * @return True se devono essere gestiti dati specifici di UD, false altrimenti.
     * 
     * @throws SQLException
     */
    public static boolean sonoPresentiDatiSpecificiUD(Long idStrut, Long idTipoUnitaDoc, Connection con)
            throws SQLException {
        boolean result = false;
        String tipoEntitaSacer = "UNI_DOC";
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) ");
        sb.append("from V_DEC_XSD_DATI_SPEC ");
        sb.append("where ID_STRUT = ? ");
        sb.append("and TI_ENTITA_SACER = ? ");
        sb.append("and ID_TIPO_UNITA_DOC = ? ");

        PreparedStatement pst = con.prepareStatement(sb.toString());
        ResultSet rs = null;
        int numeroRecord = 0;
        try {
            pst.setLong(1, idStrut);
            pst.setString(2, tipoEntitaSacer);
            pst.setLong(3, idTipoUnitaDoc);
            rs = pst.executeQuery();
            if (rs.next()) {
                numeroRecord = rs.getInt(1);
                result = numeroRecord > 0;
            }
            //

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);

        }

        return result;
    }

    public static VDecXsdDatiSpecVO getXSDDatiSpecifici(Long idTipo, String tipoEntitaSacer, String cdVersioneXsd,
            Connection connection) throws SQLException {
        // TODO Auto-generated method stub

        StringBuffer sb = new StringBuffer();
        sb.append("	select V.CD_VERSIONE_XSD, V.BL_XSD ");
        sb.append("	from V_DEC_XSD_DATI_SPEC V ");
        sb.append("	WHERE V.TI_ENTITA_SACER = ? ");//
        sb.append("	and V.CD_VERSIONE_XSD = ? ");//
        if ("UNI_DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("	and V.ID_TIPO_UNITA_DOC = ? ");
        } else if ("DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("	and V.ID_TIPO_DOC = ? ");
        }

        java.sql.PreparedStatement st = connection.prepareStatement(sb.toString());
        st.setString(1, tipoEntitaSacer);
        st.setString(2, cdVersioneXsd);
        st.setLong(3, idTipo);
        ResultSet r = null;
        VDecXsdDatiSpecVO obj = null;
        try {
            log.info(sb + " - [" + tipoEntitaSacer + "," + cdVersioneXsd + "," + idTipo + "]");
            r = st.executeQuery();
            if (r.next()) {
                obj = new VDecXsdDatiSpecVO();
                obj.setCdVersioneXsd(r.getString("CD_VERSIONE_XSD"));
                Clob tmpXsd = r.getClob("BL_XSD");
                if (tmpXsd != null) {
                    obj.setStrXsdDatiSpec(tmpXsd.getSubString(1, (int) tmpXsd.length()));
                } else {
                    obj.setStrXsdDatiSpec(null);
                }
            }
            return obj;
        } finally {
            DbUtils.closeQuietly(r);
            DbUtils.closeQuietly(st);
        }
    }

    /**
     * @return the strXsdDatiSpec
     */
    public String getStrXsdDatiSpec() {
        return strXsdDatiSpec;
    }

    /**
     * @param strXsdDatiSpec
     *            the strXsdDatiSpec to set
     */
    public void setStrXsdDatiSpec(String strXsdDatiSpec) {
        this.strXsdDatiSpec = strXsdDatiSpec;
    }

}
