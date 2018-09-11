# Contact Directory Service 📘

### Overview
Contact Directory Service is a simple contact record management REST API service, with feature to add, view, list, update and delete contacts from its records which are stored in memory cache.
For each contact one can store following fields:
 - Name (Must be unique)
 - Phone Number
 - Email

### Documentation

The full API specification can be found in `swagger.yaml`

### Prerequisites
* IDE ( preferred Eclipse 4.3+)
* JDK 1.8
* Maven 3.*

### Installation

1. Download/clone the project and change to the root folder of the project.
```sh
$ cd directory-service
``
2. Build the project using following maven command.
```sh
$ mvn clean package -DskipTests
```
3. Execute following maven command to run test and store generated coverage reports in `./target/site/jacoco/*html`
```sh
$ mvn clean test
```
4. And then excute the following maven command
 ```sh
$ mvn exec:java
```
 Now the REST Api is up and running on `localhost:8080`
 username/password - `admin`/`admin` for REST Api.

 ### Running

 Sample curl commands,

 To add new contact joe:
 ```sh
$ curl -X POST "http://localhost:8080/v1/contacts" -H "accept: */*" -H "Authorization: Basic YWRtaW46c21hcnRiZWFy" -H "Content-Type: application/json" -d "{\"name\":\"joe\",\"phoneNumber\":\"7892341670\",\"email\":\"joe@example.com\"}"
Contact name: joe added to records
 ````
To show registered user joe:
```sh
$ curl -X GET "http://localhost:8080/v1/contacts/Joe" -H "accept: application/json" -H "Authorization: Basic YWRtaW46c21hcnRiZWFy"
{"email":"joe@example.com","name":"joe","phoneNumber":"7892341670"}

