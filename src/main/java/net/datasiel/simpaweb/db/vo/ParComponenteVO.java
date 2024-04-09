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
 * Par_componente
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.datasiel.simpaweb.db.dao.ParComponenteDAO;
import net.datasiel.simpaweb.db.pojo.ParComponente;

public class ParComponenteVO extends ParComponenteDAO {

    private static final long serialVersionUID = 1L;

    public ParComponenteVO() {
        super();
    }

    public List<ParComponente> getComponenti(Long iddocumento, Connection connection) throws SQLException {

        return retrieveByIddocumentoOrderByIdComp(iddocumento, connection);
    }
}
