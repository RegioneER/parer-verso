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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.eng.parer.simparer.common;

/**
 *
 * @author fioravanti_f
 */
import static it.eng.spagoCore.configuration.ConfigProperties.StandardProperty.SERVER_NAME_PROPERTY;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.spagoCore.configuration.ConfigSingleton;

@SuppressWarnings("rawtypes")
public class AppServerInstance {

    private static final Logger log = LoggerFactory.getLogger(AppServerInstance.class);

    public String getAddress() {
        try {
            InetAddress address = this.getMyHostAddress();
            String servername = null;
            servername = address.getCanonicalHostName();
            String systemProperty = ConfigSingleton.getInstance().getStringValue(SERVER_NAME_PROPERTY.name());
            String instance = System.getProperty(systemProperty);

            if (instance != null) {
                servername += "/" + instance;
            }
            return servername;
        } catch (UnknownHostException ex) {
            log.error("Inizializzazione AppServerInstance fallita! ", ex);
            throw new RuntimeException(ex);
        }
    }

    enum AddressTypes {
        // l'ordine di scelta dovrebbe essere quello che segue:
        // priorità ai nomi host, poi gli ipv4, poi gli ipv6.
        // nell'elenco sono presenti anche i due tipi di indirizzi di loopback
        // così che nel caso pessimo sia reso preferibilmente
        // l'indirizzo IPV4. (nel caso di questi due ultimi tipi di
        // indirizzo il nome host non è critico)
        // tendenzialmente è meglio un site local di un ip pubblico.
        // per capire se un ip ha un nome, verifico se questo è diverso dalla
        // rappresentazione in stringa delll'ip. (non molto bello, in effetti)
        SITE_LOCAL_WITH_NAME, NON_SITE_LOCAL_WITH_NAME, SITE_LOCAL_WITHOUT_NAME_IPV4, NON_SITE_LOCAL_WITHOUT_NAME_IPV4,
        SITE_LOCAL_WITHOUT_NAME_IPV6, NON_SITE_LOCAL_WITHOUT_NAME_IPV6, LOOPBACK_IPV4, LOOPBACK_IPV6
    }

    private InetAddress getMyHostAddress() throws UnknownHostException {
        try {
            Map<AddressTypes, InetAddress> map = new HashMap<>();

            // Nota se falliscono tutti questi tentativi la macchina è
            // probabilmente disconnessa dalla rete
            //
            // Scorri su tutte le interfacce di rete
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // Scorri su tutti gli indirizzi IP associati ad una scheda di rete
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    log.debug("Verifico l'indirizzo " + inetAddr.getHostAddress());
                    AddressTypes tipo = decodeAddrType(inetAddr);
                    // se è già presente un indirizzo di questo tipo (per esempio se
                    // ci sono più schede di rete fisiche) lo sovrascrivo. Di fatto prendo
                    // l'ultimo che leggo.
                    map.put(tipo, inetAddr);
                }
            }

            // l'enum viene letto nell'ordine in cui è stato dichiarato,
            // garantendo la preferenza nella scelta del tipo di indirizzo reso
            for (AddressTypes at : AddressTypes.values()) {
                if (map.get(at) != null) {
                    log.info("Ho selezionato l'indirizzo di tipo " + at.name());
                    return map.get(at);
                }
            }

            // A questo punto, non siamo riusciti a determinare un indirizzo plausibile
            // Ripieghiamo usando l'API del JDK sperando che il risultato non sia
            // del tutto inutile (sotto Linux la cosa è frequente)
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("Il metodo JDK InetAddress.getLocalHost() ha reso null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Impossibile determinare un indirizzo per la macchina: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    private AddressTypes decodeAddrType(InetAddress inetAddress) {
        AddressTypes tipo;
        if (!inetAddress.isLoopbackAddress()) {
            if (inetAddress.isSiteLocalAddress()) {
                if (isAddressWithHostName(inetAddress)) {
                    tipo = AddressTypes.SITE_LOCAL_WITH_NAME;
                } else if (inetAddress instanceof Inet4Address) {
                    tipo = AddressTypes.SITE_LOCAL_WITHOUT_NAME_IPV4;
                } else {
                    tipo = AddressTypes.SITE_LOCAL_WITHOUT_NAME_IPV6;
                }
            } else {
                if (isAddressWithHostName(inetAddress)) {
                    tipo = AddressTypes.NON_SITE_LOCAL_WITH_NAME;
                } else if (inetAddress instanceof Inet4Address) {
                    tipo = AddressTypes.NON_SITE_LOCAL_WITHOUT_NAME_IPV4;
                } else {
                    tipo = AddressTypes.NON_SITE_LOCAL_WITHOUT_NAME_IPV6;
                }
            }
            log.debug("E' un indirizzo " + tipo.name());
        } else {
            // non mi interessa se questo indirizzo di loopback ha un nome:
            // nella maggior parte dei casi si chiama "localhost".
            // questa discriminazione viene fatta solo per dare
            // priorità nella selezione all'indirizzio ipv4
            // rispetto a quello ipv6
            if (inetAddress instanceof Inet4Address) {
                tipo = AddressTypes.LOOPBACK_IPV4;
            } else {
                tipo = AddressTypes.LOOPBACK_IPV6;
            }
            log.debug("E' un indirizzo di loopback di tipo " + tipo.name());
        }
        return tipo;
    }

    private boolean isAddressWithHostName(InetAddress inetAddress) {
        if (inetAddress.getHostName().equals(inetAddress.getHostAddress())) {
            return false;
        }
        return true;
    }

}
