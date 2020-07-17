# TeamUp
Questo progetto utilizza Spring Framework con Gradle, con queste dipendenze: 
Thymeleaf, Spring JPA, MYSQL, Spring Data, Spring Web, Spring JDBC.

Per creare un nuovo progetto recarsi su start.spring.io e seguire le istruzioni; nella sezione add dependency selezionare le dipendenze sopra citate.
Qualora si importi un progetto già esistente, basta solo assicurarsi di avere i plugin di Spring installati sul proprio IDE.

è stato utilizzato IntelliJ IDEA IDE: 
1. Assicurarsi di avere la JDK 11.
2. Caricare tramite File -> Open e selezionare l'intera cartella di progetto.
3. Recarsi su File -> Settings -> Plugins e scaricare i plugins di Spring e Gradle dal Marketplace : Spring, Spring Boot, Gradle e Java EE.
4. Andare su File -> Project Structure -> Facets e assicurarsi di avere i seguenti : JPA, Spring.
5. Recarsi su Run -> Edit Configuration, cliccare sul + in alto a sx e selezionare Spring Boot dal menu, nella sezione Main Class della finestra selezionare il file di Run del progetto, identificato come TeamupApplication (dovrebbe apparire subito dopo la pressione del bottone con i tre puntini ).
6. Lanciare l'esecuzuione del progetto e aprire il browser digitando localhost:8081, qualora tale porta fosse occupata da altre applicazioni, recarsi nel file system del progetto nelle cartelle src -> main -> resources -> application.properties e modificare il dato server.port immettendo la porta libera.
7. Nel file application.properties nella sezione srping.datasource.url, appena dopo il localhost:3306, immettere il nome del proprio db e modificare le righe datasource.username e password, immettendo le proprie username e password di MYSQL (di solito root, root).

NOTA : è stato utilizzato MYSQL 8.

