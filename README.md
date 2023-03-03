# Myglic
My Glicemy

## Description
Myglic is a simple application to manage your glicemy. I start to develop this application to learn more about SpringBoot 3. 
This still a work in progress. I hope you enjoy it.

### Features
- Add a new glicemy rate
- List all glicemy rates
- Inactivate a glicemy rate

### Technologies
- SpringBoot 3
- Spring Data JPA (Repositories as interfaces)
- Spring Data Rest (REST API)
- Tests with JUnit 5 and Mockito
| Tests with SpringBootTest to controller layer

### What do you need to run this project?
- Java 17
- Maven 3.8.1
- Postgresql
- IntelliJ IDEA (as IDE -> in util folder you can find some useful scripts to run the project)
- Postman (to test the REST API)

### How to run this project?
- Clone this repository
- Create a database in Postgresql (scripts in util folder -> need to change owner of database to postgres, to your own)
- Open the project in IntelliJ IDEA and run target: `myglic run test`
- Or, Maven: `mvn spring-boot:run -Dspring.profiles.active=test` (you can change the profile to run the project)
- Or, script: Run one of the scripts in util/script/start_script folder to run the project

---
## **IMPORTANT**
This project is still a work in progress.
And it is one of two parts -> this is the backend part. The frontend part is in https://github.com/dlucchesi/myglicv
