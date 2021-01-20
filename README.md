# Roman Numeral 
This is a Java/ Maven/ Spring boot based microservice to convert number to Roman Numeral. It also includes devops capabilities for metrics, health, logging etc.
Limited to set of numbers ranging from 1-3999

### Reference Documentation

For further reference, please consider the following sections:

* [Roman Numeral](https://en.wikipedia.org/wiki/Roman_numerals)
* [Spring IO Initializer](https://start.spring.io)

### About the Service
The service is a simple Roman Numeral converter REST service. It utilizes spring web and actuator to expose various endpoints. It runs on a local Tomcat server on port 8080.
Spring provides many external supported monitoring system and tried using below :

* **Micrometer:** Exposes the metrics from our application
* **Prometheus:** Stores our metrics data

##### Logging
Config file application.properties have been enhanced to set log level and log location for application: /Users/dmakkar/romannumeral/logs/application.log

Here are the Endpoints:
### Get Roman Numeral
````
Valid Request:
http://localhost:8080/romannumeral?query=50

Response:
{
"number": 50,
"romanNumeral": "L"
}

Invalid Request
http://localhost:8080/romannumeral?query=-10
Response:
HTTP Status 400 – Bad Request
````

### Get information about system health, configurations, etc.
````
http://localhost:8080/actuator/logger
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/prometheus
````

## Dependency attribution
* Intellij
* Java 1.8 + (I am using the latest version 14.0.1)
* Maven 3.x
* Spring Boot Web 
* Spring Boot Actuator
* Micrometer
* JUnit5

## How to build and run
You can run below commands from command line at project pom level
#### Build
````
# mvn clean install
*** This will create a jar file at this location target/romannumeral-0.0.1-SNAPSHOT.jar
````
#### Run App
````
# mvn spring-boot:run
````
#### Run Test 
````
# mvn test
````

## Project Structure
#### Source Packaging
````
Main App class is located at src/main/java/com/simple/numeric/converter/romannumeral
Controller is located at src/main/java/com/simple/numeric/converter/romannumeral/controller
Model and method to convert to roman numeral is located at src/main/java/com/simple/numeric/converter/romannumeral/model
````

#### Test Packaging
````
Smoke Test is used to make sure controller is accesible and is located at src/test/java/com/simple/numeric/converter/romannumeral
Junit Test are used to validate the toRoman() method for various valid and invalid inputs using the Paramterized Test and is located at src/test/java/com/simple/numeric/converter/romannumeral/junit
Integration Test are used to test the http endpoint with query param and invalid param and is located at src/test/java/com/simple/numeric/converter/romannumeral/integration
````

#### Configs 
````
Application config file i.e application.properties is located at src/main/resources
````

## Engineering and Testing Methodology 
Following algorithm is used to convert number to roman numeral:
1.  Created a Tree Map for number and unique roman numeral to maintain the order.
2. This supports only number from 1-3999
    * If number is less than zero or number is greater than 3999 return Empty String
    * While number is greater than zero and less than 4000 
        * Find the Floor Key closest to the number from Tree Map
        * Append the Value from tree Map to String builder
        * Subtract the closest floor key from the number
    
Testing covers algorithm and Http Endpoints with set of valid and invalid inputs.

## Error Handling
The query param passed is empty results in default behavior by return roman numeral for number 1.
Any other input outside 1-3999 results in a ResponseStatusException which throws HttpStatus.BAD_REQUEST - 400.

## Additional Ways to Monitor with Supported Monitoring Systems
We have added micrometer dependency in this project and once you have prometheus running on an actuator - /actuator/prometheus and this endpoint will be used to expose metrics data in a format which can be scraped by prometheus server.
You need to do below to set up prometheus
* Install Docker  
* Download Prometheus image 
```
# docker pull prom/prometheus
```
* Prometheus Configuration file (prometheus.yml)
```
global:
  scrape_interval: 10s

scrape_configs:
  - job_name: 'prometheus'
    static_configs:
    - targets: ['127.0.0.1:9090']

  - job_name: 'spring_micrometer'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ['HOST_IP:8080']
```
* Running Prometheus using docker
```
docker run -d --name=prometheus -p 9090:9090 -v <PATH_TO_prometheus.yml_FILE>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
```
* Explore the Prometheus DashBoard
```
http://localhost:9090
```


`P.S.` I have used New Relic in past but this was my effort to try something new with Prometheus. We can enhance to visualize metrics using Grafana.
