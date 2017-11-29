# RedSky
Built as RESTfull webservice

To run application:
(You need to have Maven and Spring Boot installed in your IDE)
- Clone project to directory on your computer
- Import it in your IDE as Maven project
- Create new postgreSQL database named redSkyAssesmentDB
- Set username and password for your postgreSQL in application.properties file in src/main/resources
- Clean install Maven project (in eclipse: right click on project -> Run As -> Maven build... -> type "clean install" in Goals field)
- Run RedskyApplication class as Spring Boot App

To test endpoints:
- Use swagger at http://localhost:8080/swagger-ui.html#!/ in your browser (recomended, since it's provide templates of JSON that should be sent to endpoint). You will find location-controller and device-controller endpoints there
- Or use PostMan
