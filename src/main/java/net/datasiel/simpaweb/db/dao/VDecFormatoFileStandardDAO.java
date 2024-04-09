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

package net.datasiel.simpaweb.db.dao;

/**
 * VDecFormatoFileStandard
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.datasiel.simpaweb.db.pojo.VDecFormatoFileStandard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VDecFormatoFileStandardDAO extends VDecFormatoFileStandard {

    private final Logger log = LoggerFactory.getLogger(VDecFormatoFileStandardDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idFormatoFileStandard", "nmFormatoFileStandard", "dsFormatoFileStandard",
                "cdVersione", "dsCopyright", "nmMimetypeFile", "tiEsitoContrFormato", "flFormatoConcat" };
    }

    public VDecFormatoFileStandardDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecFormatoFileStandard obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_FORMATO_FILE_STANDARD") == null) {
            obj.setIdFormatoFileStandard(null);
        } else {
            obj.setIdFormatoFileStandard(r.getLong("ID_FORMATO_FILE_STANDARD"));
        }
        obj.setNmFormatoFileStandard(r.getString("NM_FORMATO_FILE_STANDARD"));
        obj.setDsFormatoFileStandard(r.getString("DS_FORMATO_FILE_STANDARD"));
        obj.setCdVersione(r.getString("CD_VERSIONE"));
        obj.setDsCopyright(r.getString("DS_COPYRIGHT"));
        obj.setNmMimetypeFile(r.getString("NM_MIMETYPE_FILE"));
        obj.setTiEsitoContrFormato(r.getString("TI_ESITO_CONTR_FORMATO"));
        obj.setFlFormatoConcat(r.getString("FL_FORMATO_CONCAT"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecFormatoFileStandard obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_FORMATO_FILE_STANDARD ( ID_FORMATO_FILE_STANDARD,NM_FORMATO_FILE_STANDARD,DS_FORMATO_FILE_STANDARD,CD_VERSIONE,DS_COPYRIGHT,NM_MIMETYPE_FILE,TI_ESITO_CONTR_FORMATO,FL_FORMATO_CONCAT ) values (? ,? ,? ,? ,? ,? ,? ,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdFormatoFileStandard() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdFormatoFileStandard());
        }
        pst.setString(indice++, obj.getNmFormatoFileStandard());
        pst.setString(indice++, obj.getDsFormatoFileStandard());
        pst.setString(indice++, obj.getCdVersione());
        pst.setString(indice++, obj.getDsCopyright());
        pst.setString(indice++, obj.getNmMimetypeFile());
        pst.setString(indice++, obj.getTiEsitoContrFormato());
        pst.setString(indice++, obj.getFlFormatoConcat());

        try {
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + prepQuery);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }
}
