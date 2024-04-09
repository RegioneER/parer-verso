---
title: "Configurazione Verso"
---
# Configurazione Jboss EAP 7.3

## Versioni 

| Versione | Verso  | Modifiche  |
| -------- | ---------- | ---------- |
| 1.0.0    | x.y.z | Versione iniziale del documento  |

## Datasource

### Console web

`Configuration > Connector > datasources`

Scegliere **DATASOURCES** e premere 

`Add`

Si apre un wizard in 3 passaggi
1. Aggiungere gli attributi del datasource: Nome=**VersoPool** e JNDI=**java:/jboss/datasources/ClientVersDs**;
2. Selezionare il driver **ojdbc8** (predisposto durante la configurazione generale di Jboss);
3. Impostare gli attributi della connessione, ad esempio *URL*.

#### JBoss CLI

```bash
data-source add --name=VersoPool --jndi-name=java:/jboss/datasources/ClientVersDs --connection-url=jdbc:oracle:thin:@hostname:1521/SERVICE.ente.regione.emr.it --user-name=SACER_VERSO --password=xxx --driver-name=ojdbc8 --max-pool-size=64 --jta=true --spy=true --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter --stale-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleStaleConnectionChecker --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker --statistics-enabled=true --use-ccm=true --use-fast-fail=true --validate-on-match=true --flush-strategy=FailingConnectionOnly --background-validation=false --min-pool-size=8 --enabled=true
```

## Key Store 

È necessario mettere il keystore in formato JKS in una cartella accessibile all'IDP e poi configurare la system properties verso-jks-path con il path al file.

## System properties

### Console web

`Configuration > System properties`

impostare le seguenti properties

Chiave | Valore di esempio | Descrizione
--- | --- | ---
verso-key-manager-pass | <password_jks_verso> | Chiave del Java Key Store utilizzato per ottenere la chiave privata del circolo di fiducia dell’IDP.
verso-timeout-metadata | 10000 | Timeout in secondi per la ricezione dei metadati dall’IDP.
verso-temp-file | /var/tmp/tmp-verso-federation.xml | Percorso assoluto del file xml che rappresenta l’applicazione all’interno del circolo di fiducia.
verso-sp-identity-id | https://parer-snap.ente.regione.emr.it/verso | Identità dell’applicazione all’interno del circolo di fiducia.
verso-refresh-check-interval | 600000 | Intervallo di tempo in secondi utilizzato per ricontattare l’IDP per eventuali variazioni sulla configurazione del circolo di fiducia.
verso-jks-path | /opt/jboss-eap/certs/verso.jks | Percorso assoluto del Java Key Store dell’applicazione.
verso-store-key-name | sacer_clientvers | Alias del certificato dell’applicazione all’interno del Java Key Store.

### JBoss CLI

```bash 
/system-property=verso-key-manager-pass:add(value="jeebaevohfatii4AibaidiuTag9baeya")
/system-property=verso-timeout-metadata:add(value="10000")
/system-property=verso-temp-file:add(value="/var/tmp/tmp-verso-federation.xml")
/system-property=verso-sp-identity-id:add(value="https://parer-snap.ente.regione.emr.it/verso")
/system-property=verso-refresh-check-interval:add(value="600000")
/system-property=verso-store-key-name:add(value="verso")
/system-property=verso-jks-path:add(value="/opt/jboss-eap/certs/verso.jks")
```
## Logging profile

### Profilo Verso

#### JBoss CLI

```bash
/subsystem=logging/logging-profile=VERSO:add()
/subsystem=logging/logging-profile=VERSO/periodic-rotating-file-handler=verso_handler:add(level=INFO,formatter="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n",file={path="verso.log",relative-to="jboss.server.log.dir"},suffix=".yyyy-MM-dd",append=true)
/subsystem=logging/logging-profile=VERSO/root-logger=ROOT:add(level=INFO,handlers=[verso_handler])
/subsystem=logging/logging-profile=VERSO/size-rotating-file-handler=verso_tx_connection_handler:add(level=DEBUG,formatter="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n",file={path="verso_conn_handler.log",relative-to="jboss.server.log.dir"},append=true,max-backup-index=1,rotate-size="256m")
/subsystem=logging/logging-profile=VERSO/logger=org.jboss.jca.core.connectionmanager.listener.TxConnectionListener:add(level=DEBUG,handlers=[verso_tx_connection_handler])
/subsystem=logging/logging-profile=VERSO/logger=org.springframework:add(level=ERROR,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=org.opensaml:add(level=ERROR,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=net.datasiel:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=net.datasiel:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=net.datasiel.webapp:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=org.apache.mybatis:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=com.manydesigns:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=net.sourceforge.stripes:add(level=INFO,use-parent-handlers=true)
/subsystem=logging/logging-profile=VERSO/logger=displaytag.filter.ResponseOverrideFilter:add(level=INFO,use-parent-handlers=true)
```


```xml
<logging-profiles>
    <!-- ... -->
    <logging-profile name="VERSO">
        <periodic-rotating-file-handler name="verso_handler">
            <level name="INFO"/>
            <formatter>
                <pattern-formatter pattern="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
            </formatter>
            <file relative-to="jboss.server.log.dir" path="verso.log"/>
            <suffix value=".yyyy-MM-dd"/>
            <append value="true"/>
        </periodic-rotating-file-handler>
        <periodic-size-rotating-file-handler name="verso_tx_connection_handler" autoflush="true">
            <level name="DEBUG"/>
            <formatter>
                <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
            </formatter>
            <file relative-to="jboss.server.log.dir" path="verso_conn_handler.log"/>
            <append value="true"/>
            <max-backup-index value="1">
            <rotate-size value="256m"/>
        </periodic-size-rotating-file-handler>
        <logger category="org.springframework" use-parent-handlers="true">
            <level name="ERROR"/>
        </logger>
        <logger category="org.opensaml" use-parent-handlers="true">
            <level name="ERROR"/>
        </logger>
        <logger category="net.datasiel" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="net.datasiel.webapp" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="org.apache.mybatis" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="com.manydesigns" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="net.sourceforge.stripes" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="displaytag.filter.ResponseOverrideFilter" use-parent-handlers="true">
            <level name="INFO"/>
        </logger>
        <logger category="org.jboss.jca.core.connectionmanager.listener.TxConnectionListener" use-parent-handlers="false">
            <level name="DEBUG"/>
            <handlers>
                <handler name="verso_tx_connection_handler"/>
            </handlers>
        </logger>
        <!-- ... -->
        <root-logger>
            <level name="INFO"/>
            <handlers>
                <handler name="verso_handler"/>
            </handlers>
        </root-logger>
    </logging-profile>
    <!-- ... -->
</logging-profiles>
```
