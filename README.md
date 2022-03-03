## Management accaount Application
The application exposes 3 Rest apis provided by Fabrick API (https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0)
to execute operations of 
- get account info
- money transfer
- get list transactions by account id

### Build command
mvn clean package
### Run command
mvn spring-boot:run
### Swagger Url
http://localhost:8080/swagger-ui.html