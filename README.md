# Personal data service 

## Microservice Features
### Personal data Management
- Adding personal data (secured, requires a valid JWT)
- Read/Get personal data (secured, requires a valid JWT)
- Editing personal data (secured, requires a valid JWT)
- Delete personal data (secured, requires a valid JWT)

## Microservice from project:
### https://github.com/JosepeDevs/PcTallerProject

---

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

---

## SonarQube Step-by-Step Guide

### SonarQube minimum setup

- Click on **Run All** in the docker-compose file (if plugin installed) (the play button under *services*) or docker compose up

- When Docker finishes, it will display in the sonarqube image the message:
  "INFO  app[][o.s.a.SchedulerImpl] SonarQube is operational"


- At this point, open a browser and go to:

http://localhost:9001

- Log in with:
  Username: admin
  Password: admin

- You will be requested to change the password, for example: jpd

- Go to your **My account**, then navigate to **Security**.

- Create a **new token**:
- Give it a name.
- Select **Global** scope.
- Set it to **never expire**.

- Copy the generated token and store it safely for later recovery (it will be needed).

- Go to "Projects" and click on create manually, give it a display name, ideally equal to the ProjectKey
- Select name of Main branch name, in this case "main"
- Finish clicking "Set up"

### Maven Configuration

- In Maven **settings**, add a new profile.

```xml
<profiles>
    <profile>
        <id>sonarLocalJpd</id>
        <properties>
            <sonar.host.url>http://localhost:9001</sonar.host.url>
            <sonar.login>PASTE_GENERATED_TOKEN_HERE</sonar.login>
        </properties>
    </profile>
    <profile>
        <id>otherProfilesPreviosulyExistent</id>
        ...
    </profile>
</profiles>
```

### IDE Configuration

- Installing the SonarQube Plugin in the IDE (InteliJ)

Go to Settings > Plugins > Marketplace.
Search for SonarQube and install it.

- Once installed, click "SonarQube for IDE" icon, then the wrench icon "configure SonarQube for IDE".

- Check the box "Bind project to SonarQube (Server, Cloud)" and click "configure the connection".

- Click the “+” button, then:

- Enter a name for the connection (e.g., jpdSonar).

- Select SonarQube Server.

- Set the SonarQube Server URL to: http://localhost:9001

- Click Next, then Create token.
This will open a browser (you might need to log in as admin using the password previously set — in this example, jpd).
Approve the connection by clicking Allow connection.

- If completed quickly, the IDE window will update automatically or at least it will have the token field filled in. Click Next and then Create.

- If everything went well, the connection will appear under Connections to SonarQube.
- Select the connection and click OK.

- Choose the connection from the dropdown menu.
- Choose the project in the list (the one we previously created) (if you did not create a project in sonarqube click Cancel — it will still work as long as the token setup is correct).
- Then after accepting you will see a notification "Project successfully bound".

In some cases, you may omit the projectKey if it is defined in the Maven goal.

### Analize a project with Maven goal

- For InteliJ, go to the Maven Window, you may need to click Refresh and then Reload All Maven Projects.

- Expand profiles and check sonarLocalJpd.

- It should appear selected as shown in the example image.

- Final Step

Run:
```bash
mvn clean install sonar:sonar
```

Go to localhost:9001, enter the project and see the latest analysis results for coverage or debt time and code smells