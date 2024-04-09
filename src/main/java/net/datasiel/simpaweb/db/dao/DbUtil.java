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

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);

    private static boolean isOracle = true;
    private boolean nomiEsatti = false;
    private boolean trimString = false;
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Costruttore di default. Imposta la proprietà nomiEsatti e trimString a false.
     *
     */
    public DbUtil() {
        this(false, false);
    }

    /**
     * Costruttore che consente di inizializzare le proprietà nomiEsatti e trimString
     */
    public DbUtil(boolean nomiEsatti, boolean trimString) {
        super();
        this.nomiEsatti = nomiEsatti;
        this.trimString = trimString;
    }

    /**
     * Restituisce il valore successivo del sequence rappresentato dalla parametro sequenceName
     *
     * @param sequenceName
     *            il nome del sequence
     * @param con
     * 
     * @return
     * 
     * @throws SQLException
     */
    public static long getSequenceValue(String sequenceName, Connection con) throws SQLException {
        try {

            long nextValue = -1;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");
            rs.next();
            nextValue = rs.getLong(1);

            log.debug("next " + sequenceName + " value is " + nextValue);

            rs.close();
            st.close();

            return nextValue;

        } catch (SQLException e) {
            log.error("unable to retrive nextval from the sequence " + sequenceName + ". reason: " + e.getMessage());
            throw (e);
        }
    }

    /**
     * Crea un oggetto di tipo beanType e lo popola con il contenuto del record corrente associato al parametreo rs di
     * tipo java.sql.ResultSet. Se il parametro allString è true, trasforma in java.lang.String tutti i valori del
     * record, altrimenti utilizza la naturale conversione dei tipi SQL in tipi JAVA. Utilizza il metodo getSetterName
     * per ottenere attraverso il nome di una colonna il nome del metodo Set da invocare sull'oggetto bean. Se il metodo
     * non esiste il valore della colonna per il record corrente viene ignorato e si passa al successivo.
     *
     * @param rs
     *            L'oggetto che punta al record
     * @param beanType
     *            Tipo dell'oggetto da creare e popolare
     * @param allString
     * 
     * @return L'oggetto di tipo beanType che rappresenta il record corrente
     * 
     * @throws Exception
     */
    public Object createResultBean(ResultSet rs, Class<?> beanType, boolean allString) throws Exception {
        Object bean = beanType.newInstance();
        populateResultBean(rs, bean, allString);
        return bean;
    }

    /**
     * Crea un oggetto di tipo beanType e lo popola con il contenuto del record corrente associato al parametreo rs di
     * tipo java.sql.ResultSet. Equivalente a <code>createResultBean(rs,beanType,false)</code> Utilizza il metodo
     * getSetterName per ottenere attraverso il nome di una colonna il nome del metodo Set da invocare sull'oggetto
     * bean. Se il metodo non esiste il valore della colonna per il record corrente viene ignorato e si passa al
     * successivo.
     *
     * @param rs
     *            L'oggetto che punta al record
     * @param beanType
     *            Tipo dell'oggetto da creare e popolare
     * 
     * @return L'oggetto di tipo beanType che rappresenta il record corrente
     * 
     * @throws Exception
     */
    public Object createResultBean(ResultSet rs, Class<?> beanType) throws Exception {
        Object bean = beanType.newInstance();
        populateResultBean(rs, bean, false);
        return bean;
    }

    /**
     * Popola la lista rappresentata dal parametro list, con oggetti che rappresentano i record contenuti nel parametro
     * rs. Utilizza per ogni record il metodo populateResultBean.
     *
     * @param rs
     *            L'oggetto risultato di una qualche query
     * @param list
     *            La lista da popolare
     * @param objType
     *            Il tipo di oggetti che la collectio deve contenere
     * @param allStrings
     *            Informazione da passare a populateResultBean
     * 
     * @throws Exception
     */
    public void populateResultList(ResultSet rs, List<Object> list, Class<?> beanType, boolean allString)
            throws Exception {
        while (rs.next()) {
            Object bean = createResultBean(rs, beanType, allString);
            list.add(bean);
        }
    }
    /*
     * public void populateResultBean(ResultSet rs, Object bean, boolean allString) throws Exception { Class clazz =
     * bean.getClass(); ResultSetMetaData metaData = rs.getMetaData(); String colName = ""; String methodName = ""; int
     * colsNum = metaData.getColumnCount(); for(int i=1 ; i<=colsNum ; i++) { try { colName = metaData.getColumnName(i);
     * methodName = createSetName(colName); Object val = rs.getObject(i); if(allString) { Class[] paramsTypes =
     * {String.class}; Object[] args = new Object[1];
     * 
     * if(val != null) { args[0] = trimString ? val.toString().trim() : val.toString();
     * log.debug("try to find method '"+methodName+"("+String.class.getName()+")'"); Method method =
     * clazz.getMethod(methodName,paramsTypes); method.invoke(bean,args); } } else if(val != null) { Class[] paramsTypes
     * = {val.getClass()}; Object[] args = new Object[1]; args[0] = trimString && val instanceof String ?
     * ((String)val).trim() : val; log.debug("try to find method '"+methodName+"("+val.getClass().getName()+")'");
     * Method method = clazz.getMethod(methodName,paramsTypes); method.invoke(bean,args); } } catch (Exception e) {
     * log.debug("unable to set the bean property '"+colName+"' in "+clazz.getName()+" class."); } } }
     */

    /**
     * Popola l'oggetto bean con il contenuto del record corrente associato al parametreo rs di tipo java.sql.ResultSet.
     * Se il parametro allString è true, trasforma in java.lang.String tutti i valori del record, altrimenti utilizza la
     * naturale conversione dei tipi SQL in tipi JAVA. Utilizza il metodo getSetterName per ottenere attraverso il nome
     * di una colonna il nome del metodo Set da invocare sull'oggetto bean. Se il metodo non esiste il valore della
     * colonna per il record corrente viene ignorato e si passa al successivo.
     *
     * @param rs
     *            L'oggetto che punta al record
     * @param bean
     *            L'oggetto da popolare
     * @param allStrings
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public void populateResultBean(ResultSet rs, Object bean, boolean allString) throws Exception {
        Class<? extends Object> clazz = bean.getClass();
        ResultSetMetaData metaData = rs.getMetaData();
        String colName = null;
        String methodName = null;
        int colsNum = metaData.getColumnCount();

        if (allString) {
            if (trimString) {
                for (int i = 1; i <= colsNum; i++) {
                    try {
                        colName = metaData.getColumnName(i);
                        methodName = createSetName(colName);
                        Object val = rs.getObject(i);
                        if (val != null) {
                            Class[] paramsTypes = { String.class };
                            Object[] args = { val.toString().trim() };
                            Method method = clazz.getMethod(methodName, paramsTypes);
                            method.invoke(bean, args);
                        }
                    } catch (Exception e) {
                        log.warn("Generic error", e);
                    }
                }
            } else {
                for (int i = 1; i <= colsNum; i++) {
                    try {
                        colName = metaData.getColumnName(i);
                        methodName = createSetName(colName);
                        Object val = rs.getObject(i);
                        if (val != null) {
                            Class[] paramsTypes = { String.class };
                            Object[] args = { val.toString() };
                            Method method = clazz.getMethod(methodName, paramsTypes);
                            method.invoke(bean, args);
                        }
                    } catch (Exception e) {
                        log.warn("Generic error", e);
                    }
                }
            }
        } else {
            if (trimString) {
                for (int i = 1; i <= colsNum; i++) {
                    try {
                        colName = metaData.getColumnName(i);
                        methodName = createSetName(colName);
                        Object val = rs.getObject(i);
                        if (val != null) {
                            Class[] paramsTypes = { val.getClass() };
                            Object[] args = { val instanceof String ? ((String) val).trim() : val };
                            Method method = clazz.getMethod(methodName, paramsTypes);
                            method.invoke(bean, args);
                        }
                    } catch (Exception e) {
                        log.warn("Generic error", e);
                    }
                }
            } else {
                for (int i = 1; i <= colsNum; i++) {
                    try {
                        colName = metaData.getColumnName(i);
                        methodName = createSetName(colName);
                        Object val = rs.getObject(i);
                        if (val != null) {
                            Class[] paramsTypes = { val.getClass() };
                            Object[] args = { val };
                            Method method = clazz.getMethod(methodName, paramsTypes);
                            method.invoke(bean, args);
                        }
                    } catch (Exception e) {
                        log.warn("Generic error", e);
                    }
                }
            }
        }
    }

    /**
     *
     * @param p
     * 
     * @return
     */
    private String firstUp(String p) {
        return (p.substring(0, 1).toUpperCase() + p.substring(1, p.length()).toLowerCase());
    }

    /**
     * Restituisce il nome di un metodo Set a partire dal parametro fieldName.
     * <p>
     * <ul>
     * <li>Se la proprietà nomiEsatti == true concatena la stringa "set" a fieldName dopo aver eseguito l'uppercase sul
     * carattere alla posizione 0 di fieldName</li>
     * <li>Se la proprietà nomiEsatti == false concatena la stringa "set" a fieldName dopo aver eseguito il lowercase su
     * fieldName e successivamente l'uppercase sul carattere alla posizione 0 di fieldName. Elimina eventuali underscore
     * ('_') per usare metodi java like.</li>
     * </p>
     *
     * @param fieldName
     *            Stringa da cui generare il nome del setter
     */
    public String createSetName(String fieldName) {
        if (nomiEsatti) {
            return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        } else {
            String setName = new String();
            StringTokenizer tokenizer = new StringTokenizer(fieldName, "_");
            while (tokenizer.hasMoreTokens()) {
                setName += firstUp(tokenizer.nextToken());
            }
            return "set" + setName;
        }
    }

    /**
     * Restituisce il nome di un metodo Get a partire dal parametro fieldName.
     * <p>
     * <ul>
     * <li>Se la proprietà nomiEsatti == true concatena la stringa "set" a fieldName dopo aver eseguito l'uppercase sul
     * carattere alla posizione 0 di fieldName</li>
     * <li>Se la proprietà nomiEsatti == false concatena la stringa "set" a fieldName dopo aver eseguito il lowercase su
     * fieldName e successivamente l'uppercase sul carattere alla posizione 0 di fieldName. Elimina eventuali underscore
     * ('_') per usare metodi java like.</li>
     * </p>
     *
     * @param fieldName
     *            Stringa da cui generare il nome del setter
     */
    public String createGetName(String fieldName) {
        if (nomiEsatti) {
            return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        } else {
            String setName = new String();
            StringTokenizer tokenizer = new StringTokenizer(fieldName, "_");
            while (tokenizer.hasMoreTokens()) {
                setName += firstUp(tokenizer.nextToken());
            }
            return "get" + setName;
        }
    }

    public boolean isAllBlankOrNull(Object pojo, String[] fieldNames) throws Exception {
        for (int i = 0; i < fieldNames.length; i++) {
            String getName = createGetName(fieldNames[i]);
            Class<? extends Object> clazz = pojo.getClass();
            Method m = clazz.getMethod(getName, new Class[0]);
            Object o = m.invoke(pojo, new Object[0]);
            if (o != null && !"".equals(o.toString().trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Restituisce il valore della proprietà nomiEsatti
     *
     * @return
     */
    public boolean isNomiEsatti() {
        return nomiEsatti;
    }

    /**
     * Imposta il valore della proprietà nomiEsatti
     *
     * @param nomiEsatti
     */
    public void setNomiEsatti(boolean nomiEsatti) {
        this.nomiEsatti = nomiEsatti;
    }

    /**
     * Restituisce il valore della proprietà trimString
     *
     * @return
     */
    public boolean isTrimString() {
        return trimString;
    }

    /**
     * Imposta il valore della proprietà trimString
     *
     * @param trimString
     */
    public void setTrimString(boolean trimString) {
        this.trimString = trimString;
    }

    /**
     *
     * @param ts
     * 
     * @return
     */
    public static String formatTimeStamp(Date ts) {
        String tsString = "null";
        if (ts != null) {
            if (isOracle) {
                tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
            } else {
                tsString = "\'" + ts.toString() + "\'";
            }
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
            if (isOracle) {
                tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
            } else {
                tsString = "\'" + ts.toString() + "\'";
            }
        }

        return tsString;
    }

    /**
     *
     * @param ts
     * 
     * @return
     */
    public static String formatTimeStamp(java.util.Date ts) {
        String tsString = "null";
        if (ts != null) {
            if (isOracle) {
                tsString = "to_date('" + df.format(ts) + "','YYYY-MM-DD HH24:MI:SS')";
            } else {
                tsString = "\'" + ts.toString() + "\'";
            }
        }

        return tsString;
    }

    /**
     * Convenience method for string enclosure. Mainly for turning nulls into "null"s.
     */
    public static String formatString(String st) {
        String stString = "null";
        if (st != null) {
            st = st.replaceAll("'", "''");
            stString = "'" + st + "'";
        }

        return stString;
    }

    public static boolean areEquals(int v1, int v2) {
        return v1 == v2;
    }

    public static boolean areEquals(char v1, char v2) {
        return v1 == v2;
    }

    public static boolean areEquals(long v1, long v2) {
        return v1 == v2;
    }

    public static boolean areEquals(double v1, double v2) {
        return v1 == v2;
    }

    public static boolean areEquals(boolean v1, boolean v2) {
        return v1 == v2;
    }

    public static boolean areEquals(short v1, short v2) {
        return v1 == v2;
    }

    public static boolean areEquals(float v1, float v2) {
        return v1 == v2;
    }

    public static boolean areEquals(byte v1, byte v2) {
        return v1 == v2;
    }

    public static boolean areEquals(Object v1, Object v2) {
        if (v1 == null && v2 == null) {
            return true;
        } else if (v1 == null || v2 == null) {
            return false;
        } else {
            return v1.equals(v2);
        }
    }

    public static boolean areEquals(String v1, String v2) {
        if (v1 != null ^ v2 != null) {
            return "".equals(v1) || "".equals(v2);
        } else if (v1 == null && v2 == null) {
            return true;
        } else {
            return v1.equals(v2);
        }
    }
}
