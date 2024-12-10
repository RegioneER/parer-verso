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

/**
 *
 */
package net.datasiel.simpaweb.actionbeans.crudud;

import java.sql.Connection;
import java.sql.SQLException;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;

import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.vo.ParDocumentoVO;
import net.datasiel.webapp.InfoToolbar;
import net.datasiel.webapp.crud.RigaModel;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/editdoc/{idrecord}")
@InfoToolbar(titolo = "Documento principale", accelerator = "D", breadcrumbs = "Home")
public class DocPrincipale extends Documenti {

    @Override
    protected void inizializzaDocPrincipale(Long idrecord, Connection connection) throws SQLException {
        ParDocumentoVO parDocVO = new ParDocumentoVO();
        ParDocumentoVO datiDocPrincipale = parDocVO.getDocPrincipale(idrecord, connection);
        if (datiDocPrincipale == null) {
            // Non è ancora stato oinserito un documento principale...
            // Lo inseriamo
            datiDocPrincipale = new ParDocumentoVO();
            datiDocPrincipale.setIdunitadoc(idrecord);
            datiDocPrincipale.setTipologia(getTipoTab());
            datiDocPrincipale.setIddocumento(DbUtil.getSequenceValue("PAR_SEQ_IDCOMPONENTE", connection));
            datiDocPrincipale.setPgm("DocPrincipale");
            datiDocPrincipale.setId(0L);
            datiDocPrincipale.setFlgstato(0L);
            parDocVO.insertPrepared(datiDocPrincipale, getConnection());

        }
    }

    @Override
    protected String getTipoTab() {
        return "PRINC";
    }

    @Override
    public String getTitoloFieldsetRiga() {
        return "documento";
    }

    @Override
    public void deleteRiga(int indiceRiga) throws Exception {
        // Non usata: riga singola non eliminabile
    }

    @Override
    public void insertRigaModel(RigaModel rigaModel) throws Exception {
        // Vuoto: nuove righe non inseribili
    }

    @Override
    public RigaModel createRigaModel() {
        // Vuoto non c'è inserimento di nuove righe
        return null;
    }

    @Override
    public Resolution createRiga() {
        // TODO Auto-generated method stub
        return super.createRiga();
    }

    @Override
    public Resolution delete() {
        // TODO Auto-generated method stub
        return super.delete();
    }

    @Override
    public Element prepareElDettaglio2(String prefix, Mode mode) {
        // TODO Auto-generated method stub

        return super.prepareElDettaglio2(prefix, mode);
    }

    @Override
    public boolean dettaglio2Validate(Object dettaglio) {
        // TODO Auto-generated method stub
        return super.dettaglio2Validate(dettaglio);
    }

    @Override
    public void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception {
        // TODO Auto-generated method stub
        super.insertDettaglio2Model(indiceRiga, dettaglio);
    }

    @Override
    public Object createDettaglio2Model() {
        // TODO Auto-generated method stub
        return super.createDettaglio2Model();
    }

    @Override
    public void deleteDettaglio2Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        // TODO Auto-generated method stub
        super.deleteDettaglio2Model(indiceRiga2, indiceDettaglio);
    }

    @Override
    protected EnumEntitaDatiSpecifici getEntitaDatiSpecifici() {
        // TODO Auto-generated method stub
        return EnumEntitaDatiSpecifici.DOC;
    }

    @Override
    public String getCreaDettaglio1Title() {
        // TODO Auto-generated method stub
        return "Inserimento Componente";
    }

    @Override
    public String getCreaDettaglio2Title() {
        // TODO Auto-generated method stub
        return "Inserimento Dati Specifici";
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Verso: Documento Principale";
    }

}
