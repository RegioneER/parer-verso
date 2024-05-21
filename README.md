# Verso (Versamento Online)

Fonte template redazione documento:  https://www.makeareadme.com/.


# Descrizione

Il client di versamento manuale VersO (Versamento Online) è un modulo che ParER mette a disposizione degli Enti produttori per svolgere operazioni routinarie sul sistema. Poiché utilizza un’interfaccia web, non richiede l’installazione di alcun software sulla stazione di lavoro dell’Utente. 

# Installazione

Requisiti minimi per installazione: 

- Sistema operativo : consigliato Linux server (in alternativa compatibilità con Windows server);
- Java versione 8 (OpenJDK / Oracle);
- JBoss 7 EAP;
- Oracle DB (versione consigliata 19c).

## Instalazione JDK 

Consigliata adozione della OpenJDK alla versione 8, guida all'installazione https://openjdk.org/install/.

## Setup application server (Jboss 7)

Richiesta l'esecuzione delle seguenti guide secondo l'ordine riportato di seguito: 

1. guida per la configurazione **base** di [guida 1](src/docs/JBoss7_configurazione_generale.md);
2. guida con le configurazioni **specifiche** per il contesto applicativo **VERSO**  di [guida 2](src/docs/JBoss7_configurazione_verso.md).

### Deploy su JBoss 7

Di seguito le indicazioni per il rilascio su application server JBoss7: 

1. generazione dell'artifact attraverso tool maven, eseguire il seguente comando: 

   ```bash
   mvn package
   ```
   
2. viene generato l'artifact .war all'interno del modulo target (e.g. verso-1.8.0.war)
3. deploy dell'ear generato allo step 1 su JBoss 7 (vedi configurazione [setup JBoss7](#setup-application-server-jboss-7))


## Predisposizione database

L'applicazione utilizza come DBMS di riferimento Oracle DB (https://www.oracle.com/it/database/) alla versione, consigliata, **19c**. Per l'installazione e la configurazione fare riferimento alle guide ufficiali.

Per la creazione del modello E-R consultare il seguente [README.md](https://github.com/RegioneER/parer-db-init/blob/master/README.md) (progetto di riferimento https://github.com/RegioneER/parer-db-init).


# Utilizzo

Il suo utilizzo tipico è il versamento di Unità documentarie per le quali non esiste un sistema interfacciato con Sacer. VersO viene richiamato tramite interfaccia web, si autentica sull’IdP di ParER o su SPID, utilizzando in ogni caso logiche di profilazione del Sistema, ed effettua il versamento dei SIP tramite interazione guidata con l’operatore del Produttore. 

Tale modulo semplifica le operazioni di versamento manuale da parte del Produttore, automatizzando la generazione dell’Indice del SIP ed effettuando un test completo della correttezza del versamento prima di eseguire il versamento stesso. Inoltre, mantiene il log dei versamenti effettuati e consente di interrompere temporaneamente l’operazione (p.e per raccogliere informazioni necessarie per completarlo), riprendendola successivamente, indipendentemente dalla scadenza della sessione web. 


La schermata di accesso del sistema si compone di una sezione dedicata alla ricerca dei versamenti effettuati e di una sezione per la gestione dei nuovi versamenti. 


<img src="src/docs/img/home_verso.png"> 

La maschera di gestione di un versamento guida l'utente nel processo di creazione del pacchetto di versamento SIP. 

<img src="src/docs/img/managev_verso.png"> 

# Requisiti e librerie utilizzate

Requisiti minimi per installazione: 

- Sistema operativo : consigliato Linux server (in alternativa compatibilità con Windows server)
- Java versione 17 (OpenJDK / Oracle)
- Kubernetes / Docker : se rilasciato attraverso container oppure si esegue una build del progetto attraverso il profilo maven **uber-jar** per ottenere il JAR eseguibile (vedi paragrafi precendeti)

## Librerie utilizzate

|  GroupId | ArtifactId  | Version  | Type   |  Licenses |
|---|---|---|---|---|
|com.manydesigns |elements |4.1.beta6 |jar |GNU LESSER GENERAL PUBLIC LICENSE, Version 3||
|com.manydesigns |elements-extras |4.0.8 |jar |-|
|commons-collections |commons-collections |3.2.2 |jar |Apache License, Version 2.0|
|commons-dbutils |commons-dbutils |1.7 |jar |Apache License, Version 2.0|
|it.eng.parer |spagofat-core |5.12.0 |jar |-|
|it.eng.parer |spagofat-middle |5.12.0 |jar |-|
|it.eng.parer |spagofat-si-client |5.12.0 |jar |-|
|it.eng.parer |spagofat-si-util |5.12.0 |jar |-|
|jaxen |jaxen |1.2.0 |jar |BSD License 2.0|
|net.datasiel |basewebapp |1.0.beta0 |jar |-|
|net.sourceforge.stripes |stripes |1.5.8 |jar |The Apache Software License, Version 2.0|
|ognl |ognl |3.3.4 |jar |The Apache Software License, Version 2.0|
|org.apache.httpcomponents |httpmime |4.5.14 |jar |Apache License, Version 2.0|
|org.jdom |jdom2 |2.0.6.1 |jar |Similar to Apache License but with the acknowledgment clause removed
|org.reactivestreams |reactive-streams |1.0.3.redhat-00003 |jar |-|
|org.springframework |spring-aop |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-context |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-context-support |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-core |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-tx |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-web |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework |spring-webmvc |5.3.30 |jar |Apache License, Version 2.0|
|org.springframework.security |spring-security-config |5.8.8 |jar |Apache License, Version 2.0|
|org.springframework.security |spring-security-core |5.8.8 |jar |Apache License, Version 2.0|
|org.springframework.security |spring-security-web |5.8.8 |jar |Apache License, Version 2.0|
|org.springframework.security.extensions |spring-security-saml2-core |1.0.10.RELEASE |jar |The Apache Software License, Version 2.0|
|xalan |xalan |2.7.2 |jar |The Apache Software License, Version 2.0|
|xerces |xercesImpl |2.12.0 |jar |The Apache Software License, Version 2.0|


# Supporto

Mantainer del progetto è [Engineering Ingegneria Informatica S.p.A.](https://www.eng.it/).

# Contributi

Se interessati a crontribuire alla crescita del progetto potete scrivere all'indirizzo email <a href="mailto:areasviluppoparer@regione.emilia-romagna.it">areasviluppoparer@regione.emilia-romagna.it</a>.

# Credits

Progetto di proprietà di [Regione Emilia-Romagna](https://www.regione.emilia-romagna.it/) sviluppato a cura di [Engineering Ingegneria Informatica S.p.A.](https://www.eng.it/).

# Licenza

Questo progetto è rilasciato sotto licenza GNU Affero General Public License v3.0 or later ([LICENSE.txt](LICENSE.txt)).

# Appendice

Link ad eventuali pagine istituzionali relative al progetto o al contesto di utilizzo; 

* Sito ParER: https://poloarchivistico.regione.emilia-romagna.it/ 
* Manuale utente https://poloarchivistico.regione.emilia-romagna.it/documentazione/verso-manuale-utente
* Manuale di conservazione: https://poloarchivistico.regione.emilia-romagna.it/documentazione/documenti_open/manualeconservazione_v5-0.pdf/@@download/file/ManualeConservazione_v2.0.pdf 
