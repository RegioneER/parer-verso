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
 * VDecXsdDatiSpec
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecXsdDatiSpec;

public class VDecXsdDatiSpecDAO extends VDecXsdDatiSpec {

    private final Logger log = LoggerFactory.getLogger(VDecXsdDatiSpecDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idXsdDatiSpec", "idStrut", "tiUsoXsd", "tiEntitaSacer", "idTipoUnitaDoc",
                "idTipoDoc", "idTipoCompDoc", "nmSistemaMigraz", "cdVersioneXsd", "dtIstituz", "dtSoppres" };
    }

    public VDecXsdDatiSpecDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecXsdDatiSpec obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_XSD_DATI_SPEC") == null) {
            obj.setIdXsdDatiSpec(null);
        } else {
            obj.setIdXsdDatiSpec(r.getLong("ID_XSD_DATI_SPEC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setTiUsoXsd(r.getString("TI_USO_XSD"));
        obj.setTiEntitaSacer(r.getString("TI_ENTITA_SACER"));
        if (r.getObject("ID_TIPO_UNITA_DOC") == null) {
            obj.setIdTipoUnitaDoc(null);
        } else {
            obj.setIdTipoUnitaDoc(r.getLong("ID_TIPO_UNITA_DOC"));
        }
        if (r.getObject("ID_TIPO_DOC") == null) {
            obj.setIdTipoDoc(null);
        } else {
            obj.setIdTipoDoc(r.getLong("ID_TIPO_DOC"));
        }
        if (r.getObject("ID_TIPO_COMP_DOC") == null) {
            obj.setIdTipoCompDoc(null);
        } else {
            obj.setIdTipoCompDoc(r.getLong("ID_TIPO_COMP_DOC"));
        }
        obj.setNmSistemaMigraz(r.getString("NM_SISTEMA_MIGRAZ"));
        obj.setCdVersioneXsd(r.getString("CD_VERSIONE_XSD"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecXsdDatiSpec obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_XSD_DATI_SPEC ( ID_XSD_DATI_SPEC,ID_STRUT,TI_USO_XSD,TI_ENTITA_SACER,ID_TIPO_UNITA_DOC,ID_TIPO_DOC,ID_TIPO_COMP_DOC,NM_SISTEMA_MIGRAZ,CD_VERSIONE_XSD,DT_ISTITUZ,DT_SOPPRES ) values (? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdXsdDatiSpec() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdXsdDatiSpec());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getTiUsoXsd());
            pst.setString(indice++, obj.getTiEntitaSacer());
            if (obj.getIdTipoUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoUnitaDoc());
            }
            if (obj.getIdTipoDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoDoc());
            }
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
            }
            pst.setString(indice++, obj.getNmSistemaMigraz());
            pst.setString(indice++, obj.getCdVersioneXsd());
            if (obj.getDtIstituz() != null) {
                pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getDtIstituz()).getTime()));
            } else {
                pst.setObject(indice++, null);
            }
            if (obj.getDtSoppres() != null) {
                pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getDtSoppres()).getTime()));
            } else {
                pst.setObject(indice++, null);
            }
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }
}
