
## 3.4.0 (14-01-2026)

### Novità: 1
- [#37767](https://parermine.regione.emilia-romagna.it/issues/37767)  Ottimizzazioni e refactor codice per recupero debito tecnico (SonarQube)

## 3.3.0 (31-03-2025)

### Bugfix: 1
- [#37612](https://parermine.regione.emilia-romagna.it/issues/37612) Correzione per la mancata chiusura dei file e protocollo NFS

## 3.2.0 (13-02-2025)

### Novità: 2
- [#35552](https://parermine.regione.emilia-romagna.it/issues/35552) Configurazione modulo jaxp-jdk per JBoss 7 per JDK 11
- [#34004](https://parermine.regione.emilia-romagna.it/issues/34004) Migrazione alle nuove dipendenze / pattern legate a xecers, xalan, jaxb, ecc

## 3.1.0 (10-12-2024)

### Novità: 2
- [#33952](https://parermine.regione.emilia-romagna.it/issues/33952) Aggiornamento librerie obsolete 2024
- [#33129](https://parermine.regione.emilia-romagna.it/issues/33129) Aggiornamento alle ultimi versioni librerie jakarata-ee8 per jboss 7.4

## 3.0.0 (19-08-2024)

### Novità: 1
- [#30803](https://parermine.regione.emilia-romagna.it/issues/30803) Aggiornamento a Java 11

## 2.0.0 (12-02-2024)

### Novità: 1
- [#30798](https://parermine.regione.emilia-romagna.it/issues/30798) Aggiornamento a Spring 5 

## 1.8.1 (08-01-2024)

### Bugfix: 1
- [#31010](https://parermine.regione.emilia-romagna.it/issues/31010) Correzione per errata gestione db connection su endpoint con informazioni sulla versione

## 1.8.0 (20-12-2023)

### Novità: 3
- [#30852](https://parermine.regione.emilia-romagna.it/issues/30852) Creazione endpoint con informazioni sulla versione
- [#29432](https://parermine.regione.emilia-romagna.it/issues/29432) Integrazione con SPID professionale
- [#25486](https://parermine.regione.emilia-romagna.it/issues/25486) Protezione url di upload e download di file con token anti CSRF

## 1.7.0 (03-08-2023)

### Novità: 1
- [#29665](https://parermine.regione.emilia-romagna.it/issues/29665) Aggiornamento librerie obsolete 2023

## 1.6.0 (02-05-2023)

### Novità: 1
- [#18351](https://parermine.regione.emilia-romagna.it/issues/18351) Creazione pacchetto unico per Verso

## 1.5.0 (13-04-2023)

### Novità: 4
- [#28360](https://parermine.regione.emilia-romagna.it/issues/28360) adeguamento SAML per regione PUGLIA
- [#28002](https://parermine.regione.emilia-romagna.it/issues/28002) Rimozione chat su VERSO
- [#27403](https://parermine.regione.emilia-romagna.it/issues/27403)  Logging accessi SPID non autorizzati
- [#26207](https://parermine.regione.emilia-romagna.it/issues/26207) Integrazione della gestione di SPID della Puglia nell'attuale gestione SPID in RER

## 1.4.0 (23-11-2022)

### Bugfix: 1
- [#27935](https://parermine.regione.emilia-romagna.it/issues/27935) Correzione gestione character iso-8859-1 su jboss 7

### Novità: 1
- [#27358](https://parermine.regione.emilia-romagna.it/issues/27358) Analisi librerie obsolete 2022

## 1.3.9 (09-09-2022)

### Bugfix: 1
- [#27633](https://parermine.regione.emilia-romagna.it/issues/27633) Correzione errore nella chiamata di verifica a SACER

## 1.3.8 (27-04-2022)

### Bugfix: 1
- [#27025](https://parermine.regione.emilia-romagna.it/issues/27025) Correzione del numero di versione per le chiamate al WS di versamento

### Novità: 1
- [#26608](https://parermine.regione.emilia-romagna.it/issues/26608) Inserimento dei link nei loghi della home di Verso

## 1.3.7 (08-03-2022)

### Bugfix: 1
- [#26915](https://parermine.regione.emilia-romagna.it/issues/26915) correzione configurazione del securityContext.xml

## 1.3.6 (09-03-2022)

### Bugfix: 2
- [#25455](https://parermine.regione.emilia-romagna.it/issues/25455) Correzione messaggio di errore durante la fase di login tramite SPID
- [#25318](https://parermine.regione.emilia-romagna.it/issues/25318) Correzione della gestione delle utenze non attive mediante autenticazione SPID

### Novità: 3
- [#25777](https://parermine.regione.emilia-romagna.it/issues/25777) Gestione di diversi livelli di accesso con credenziali SPID 
- [#25137](https://parermine.regione.emilia-romagna.it/issues/25137) Associazione utente SPID con anagrafica utenti per le applicazioni  VERSO
- [#24556](https://parermine.regione.emilia-romagna.it/issues/24556) Implementazione della chiamata ai ws di versamento 1.5

## 1.3.5

### Novità: 1
- [#26754](https://parermine.regione.emilia-romagna.it/issues/26754) Analisi librerie obsolete primo quadrimestre 2021

## 1.3.4

### Novità: 1
- [#25461](https://parermine.regione.emilia-romagna.it/issues/25461) ASL CN1: problema di caricamento in SACER_VERSO di file di dimensioni rilevanti 

## 1.3.3 (26-03-2021)

### Bugfix: 1
- [#24555](https://parermine.regione.emilia-romagna.it//issues/24555) Valorizzazione dello user id della chiamata al ws di versamento con lo user dell'automa di sistema e non con lo user dell'utente

## 1.3.2

### Novità: 2
- [#23671](https://parermine.regione.emilia-romagna.it//issues/23671) Rimozione logo IBACN per passaggio a RER
- [#22955](https://parermine.regione.emilia-romagna.it//issues/22955) [VERSO] Aggiunta codice fiscale nel messaggio di errore per l'utente in caso di login non autorizzato via SPID

## 1.3.1 (14-08-2020)

### Novità: 1
- [#22782](https://parermine.regione.emilia-romagna.it//issues/22782) Recepimento aggiornamenti librerie Secondo quadrimestre 2020 - VERSO

## 1.3.0 (16-07-2020)

### Novità: 1
- [#22619](https://parermine.regione.emilia-romagna.it//issues/22619) Integrazione con framework 4.1.6

## 1.2.1 (17-06-2020)

### Novità: 1
- [#22318](https://parermine.regione.emilia-romagna.it//issues/22318) Integrazione con SPID

## 1.1.1 (03-05-2019)

### Bugfix: 1
- [#18436](https://parermine.regione.emilia-romagna.it//issues/18436) SACER VERSO_Errore nella verifica dei dati specifici

### Novità: 2
- [#17948](https://parermine.regione.emilia-romagna.it//issues/17948) Java 8 e re-factor parer-pom / framework
- [#17938](https://parermine.regione.emilia-romagna.it//issues/17938) Aggiornamenti per modifiche a multiconservatore

## 1.0.34 (08-02-2019)

### Novità: 1
- [#17597](https://parermine.regione.emilia-romagna.it//issues/17597) Eliminazione jks dal sorgente dell'applicazione.

## 1.0.33 (11-09-2018)

### Bugfix: 2
- [#16036](https://parermine.regione.emilia-romagna.it//issues/16036) Contromisure alla vulnerabilità session termination
- [#15636](https://parermine.regione.emilia-romagna.it//issues/15636) Impostazione flag HttpOnly e secure sul cookie JSESSIONID

## 1.0.32 (08-06-2018)

### Bugfix: 1
- [#15296](https://parermine.regione.emilia-romagna.it//issues/15296) Versamento di una bozza - inserimento password

## 1.0.31 (10-05-2018)

### Bugfix: 1
- [#15052](https://parermine.regione.emilia-romagna.it//issues/15052) Gestione dell'encoding su verso

## 1.0.30 (25-01-2018)

### Bugfix: 1
- [#13974](https://parermine.regione.emilia-romagna.it//issues/13974) Eliminazione dai log applicativi dei messaggi di warning Field not found: {}idTipoStrutDoc:

## 1.0.29 (13-11-2017)

### Novità: 1
- [#13468](https://parermine.regione.emilia-romagna.it//issues/13468) Adeguamento a Framework 2.0.0

## 1.0.28 (17-10-2017)

### Bugfix: 1
- [#13227](https://parermine.regione.emilia-romagna.it//issues/13227) Registrazione dei login
