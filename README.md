# HOW TO RUN THE APP
1. run command: mvn clean install
2. After “BUILD SUCCESS”, go to "target" folder and look for JAR file 
3. run command: java –jar  JARFILE

- NOTE: JARFILE default = onlineshop-0.0.1-SNAPSHOT.jar

WEB APPLICATION:
- open browser at "http://localhost:8080/swagger-ui.html#/" 
to check swagger auto-generated documentation

API ENDPOINTS:
- BASE URL: Base URL: localhost:8080/
- /order
    - /create
    - /list
- /product
    - /add
    - /list
    - /show/{sku}
    - /delete/{sku}
    - /update/{sku}

H2 DATABASE:
 - open browser at "http://localhost:8080/console/"
 - use following jdbc url "jdbc:h2:mem:testdb"
 - Connect and browse PRODUCT and CLIENT_ORDER tables 

