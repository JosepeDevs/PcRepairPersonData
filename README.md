# Personal data service 

## Microservice Features
### Personal data Management
- Adding personal data (secured, requires a valid JWT)
- Read/Get personal data (secured, requires a valid JWT)
- Editing personal data (secured, requires a valid JWT)
- Delete personal data (secured, requires a valid JWT)

## Microservice from project:
### https://github.com/JosepeDevs/PcTallerProject


## ABOUT THIS MICROSERVICE

### Coordinate along other microservices
There are execution configurations saved in the project for easier setup.
1. Run first PcRepairEurekaServerService, by default it will use port 9876
2. Run PcRepairAPIGateway, by default it will use port 7777
3. Run PcRepairPersonData, by default it will use port 8080
4. To try load balancing, create another run config and run PcRepairPersonData in a different port (for example 8081)
5. Send GetAllPerson Request concurrently to port 7777 or one request very close to another, see the logs how different ports takes the request as provided by the load balancing.
6. For direct testing of the micro you can attack port 8080 or wherever it is exposing

### Database

To get a starter copy of the database with data this projects uses ctl and ldr.
The LOB are included in a separate file in case they grow too big.
We use FILLER in CTL and that field is not loaded into the table, but used to provide the LOBFILE's location.

1. Execute the docker-compose
2. Check logs of jpd_database-init_database, if error in connection, execute only container with init_database.
3. Database should be created and populated