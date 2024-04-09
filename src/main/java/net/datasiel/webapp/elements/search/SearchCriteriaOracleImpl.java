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

package net.datasiel.webapp.elements.search;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import com.manydesigns.elements.fields.search.Criteria;
import com.manydesigns.elements.fields.search.TextMatchMode;
import com.manydesigns.elements.reflection.PropertyAccessor;

import net.datasiel.webapp.elements.annotations.ColumnName;

public class SearchCriteriaOracleImpl implements Criteria {
    private StringBuilder strWhereCondition = new StringBuilder();
    private Collection<Object> lstParametri = new ArrayList<>();
    private String tablePrefix = "";
    private boolean caseSensitive = false;
    private StringBuilder strWhereValorizzata = new StringBuilder();
    private static FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 
     */
    private void appendCondition(String condizione) {
        if (strWhereCondition.length() > 0) {
            strWhereCondition.append(" and ");
        }
        strWhereCondition.append(condizione);
    }

    private void appendCondition(String condizione, Boolean isOr) {
        String operator = " and ";
        if (isOr) {
            operator = " or ";
        }
        if (strWhereCondition.length() > 0) {
            strWhereCondition.append(operator);
        }
        strWhereCondition.append(condizione);
    }

    private void appendConditionValorizzata(String condizione) {
        if (strWhereValorizzata.length() > 0) {
            strWhereValorizzata.append(" and ");
        }
        strWhereValorizzata.append(condizione);
    }

    private void appendConditionValorizzata(String condizione, Boolean isOr) {
        String operator = " and ";
        if (isOr) {
            operator = " or ";
        }
        if (strWhereValorizzata.length() > 0) {
            strWhereValorizzata.append(operator);
        }
        strWhereValorizzata.append(condizione);
    }

    public SearchCriteriaOracleImpl() {
    }

    public SearchCriteriaOracleImpl(String tablePrefix) {
        if (tablePrefix.endsWith(".")) {
            this.tablePrefix = tablePrefix;
        } else {
            this.tablePrefix = tablePrefix + ".";
        }
    }

    /**
     * @param tablePrefix
     *            prefisso assegnato alla tavola nella clausola form
     * @param caseSensitive
     *            true se la query deve essere case sensitive
     */
    public SearchCriteriaOracleImpl(String tablePrefix, Boolean caseSensitive) {
        if (tablePrefix.endsWith(".")) {
            this.tablePrefix = tablePrefix;
        } else {
            this.tablePrefix = tablePrefix + ".";
        }
        this.caseSensitive = caseSensitive;
    }

    public Criteria between(PropertyAccessor accessor, Object min, Object max) {
        appendCondition(String.format(" %s%s  between ? and ? ", tablePrefix, getColumnName(accessor)));
        lstParametri.add(min);
        lstParametri.add(max);

        appendConditionValorizzata(String.format(" %s%s  between %s and %s ", tablePrefix, getColumnName(accessor),
                formatObject(min), formatObject(max)));

        return this;
    }

    public Criteria eq(PropertyAccessor accessor, Object value) {
        appendCondition(String.format(" %s%s = ? ", tablePrefix, getColumnName(accessor)));
        lstParametri.add(value);

        appendConditionValorizzata(
                String.format(" %s%s = %s ", tablePrefix, getColumnName(accessor), formatObject(value)));

        return this;
    }

    public Criteria ge(PropertyAccessor accessor, Object value) {
        appendCondition(String.format(" %s%s  >= ? ", tablePrefix, getColumnName(accessor)));
        lstParametri.add(value);

        appendConditionValorizzata(
                String.format(" %s%s >= %s ", tablePrefix, getColumnName(accessor), formatObject(value)));

        return this;
    }

    public Criteria gt(PropertyAccessor accessor, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    public Criteria ilike(PropertyAccessor accessor, String value, TextMatchMode textMatchMode) {

        String strOperatore = "like";
        String pattern;
        switch (textMatchMode) {
        case CONTAINS:
            pattern = "%" + value + "%";
            break;
        case STARTS_WITH:
            pattern = value + "%";
            break;
        case ENDS_WITH:
            pattern = "%" + value;
            break;
        case EQUALS:
            // Valutare se cambiare anche la condizione per evitare problemi di prestazioni
            // dato che con like non verrebbero usati eventuali indici
            strOperatore = "=";
            pattern = value;
            break;
        default:
            throw new Error("Tipo match sconosciuto");
        }

        lstParametri.add(pattern);

        if (isCaseSensitive()) {
            appendCondition(String.format(" %s%s %s ? ", tablePrefix, getColumnName(accessor), strOperatore));
            appendConditionValorizzata(String.format(" %s%s %s %s ", tablePrefix, getColumnName(accessor), strOperatore,
                    formatObject(value)));
        } else {
            appendCondition(
                    String.format(" lower(%s%s) %s lower(?) ", tablePrefix, getColumnName(accessor), strOperatore));
            appendConditionValorizzata(String.format(" lower(%s%s) %s lower(%s) ", tablePrefix, getColumnName(accessor),
                    strOperatore, formatObject(value)));
        }

        return this;
    }

    public Criteria isNotNull(PropertyAccessor accessor) {
        appendCondition(String.format(" %s%s is not null ", tablePrefix, getColumnName(accessor)));
        appendConditionValorizzata(String.format(" %s%s is not null ", tablePrefix, getColumnName(accessor)));
        return this;
    }

    public Criteria isNull(PropertyAccessor accessor) {
        appendCondition(String.format(" %s%s is null ", tablePrefix, getColumnName(accessor)));
        appendConditionValorizzata(String.format(" %s%s is null ", tablePrefix, getColumnName(accessor)));
        return this;
    }

    public Criteria le(PropertyAccessor accessor, Object value) {
        appendCondition(String.format(" %s%s  <= ? ", tablePrefix, getColumnName(accessor)));
        lstParametri.add(value);
        appendConditionValorizzata(
                String.format(" %s%s <= %s ", tablePrefix, getColumnName(accessor), formatObject(value)));
        return this;
    }

    public Criteria in(PropertyAccessor accessor, Object[] parametri) {
        if (parametri == null) {
            return this;
        }
        StringBuffer segnaposti = new StringBuffer();
        StringBuffer valori = new StringBuffer();
        if (parametri.length == 1) {
            lstParametri.add(parametri[0]);

            appendCondition(String.format(" %s%s = ? ", tablePrefix, getColumnName(accessor)));
            appendConditionValorizzata(
                    String.format(" %s%s = %s ", tablePrefix, getColumnName(accessor), formatObject(parametri[0])));
            return this;

        } else {
            for (Object elemento : parametri) {
                lstParametri.add(elemento);
                if (segnaposti.length() > 0) {
                    segnaposti.append(" ,? ");
                    valori.append(String.format(" , %s ", formatObject(elemento)));
                } else {
                    segnaposti.append(" ? ");
                    valori.append(String.format(" %s ", formatObject(elemento)));
                }
            }
            appendCondition(String.format(" %s%s IN (%s) ", tablePrefix, getColumnName(accessor), segnaposti));
            appendConditionValorizzata(String.format(" ( %s%s IN (%s)", tablePrefix, getColumnName(accessor), valori));
            return this;
        }
    }

    /*
     * Restituisce il nome reale della colonna della tabella anzichè quello desunto dal nome della relativa proprietà
     * all'interno del pojo qualora i due nomi fossero differenti (ad es. ID_TIPO_UNITA_DOC tradotto da TableGen come
     * idTipoUnitaDoc). Il nome della colonna deve essere specificato tramite l'annotation ColumnName.
     */
    private String getColumnName(PropertyAccessor accessor) {
        String columnName = accessor.getName();
        ColumnName columnNameAnnotation = accessor
                .getAnnotation(net.datasiel.webapp.elements.annotations.ColumnName.class);
        if (columnNameAnnotation != null && StringUtils.isNotEmpty(columnNameAnnotation.value())) {
            columnName = columnNameAnnotation.value();
        }
        return columnName;
    }

    public Criteria like(PropertyAccessor accessor, String value, TextMatchMode textMatchMode) {
        // TODO Auto-generated method stub
        return null;
    }

    public Criteria lt(PropertyAccessor accessor, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    public Criteria ne(PropertyAccessor accessor, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    public StringBuilder getStrWhereCondition() {
        return strWhereCondition;
    }

    public void setStrWhereCondition(StringBuilder strWhereCondition) {
        this.strWhereCondition = strWhereCondition;
    }

    public Collection<Object> getLstParametri() {
        return lstParametri;
    }

    public void setLstParametri(Collection<Object> lstParametri) {
        this.lstParametri = lstParametri;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    private static String formatObject(Object o) {

        if (o == null) {
            return "null";
        }

        if (o instanceof String) {
            return formatString((String) o);
        } else if (o instanceof java.util.Date) {
            return formatTimeStamp((java.util.Date) o);
        } else {
            return o.toString();
        }

    }

    public static String formatString(String st) {
        String stString = "null";
        if (st != null) {
            st = st.replaceAll("'", "''");
            stString = "'" + st + "'";
        }

        return stString;
    }

    public static String formatTimeStamp(java.sql.Date ts) {
        String tsString = "null";
        if (ts != null) {
            tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
        }

        return tsString;
    }

    /**
     * 
     * @param ts
     * 
     * @return
     */
    public static String formatTimeStamp(Timestamp ts) {
        String tsString = "null";
        if (ts != null) {
            tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
        }

        return tsString;
    }

    public static String formatTimeStamp(java.util.Date ts) {
        String tsString = "null";
        if (ts != null) {
            tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
        }

        return tsString;
    }

    public StringBuilder getStrWhereValorizzata() {
        return strWhereValorizzata;
    }

    public void setStrWhereValorizzata(StringBuilder strWhereValorizzata) {
        this.strWhereValorizzata = strWhereValorizzata;
    }

    @Override
    public Criteria orderBy(PropertyAccessor accessor, String direction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrderBy getOrderBy() {
        // TODO Auto-generated method stub
        return null;
    }

}
