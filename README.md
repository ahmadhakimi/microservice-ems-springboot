#  Employee Management System (EMS)🧑‍💼
### High-level architecture ems

 # PLEASE USE THE PARENT-NEW-MICROSERVICE FOLDER ## ‼️

![ems-diagram](https://github.com/user-attachments/assets/6c510c88-c338-406d-93fb-eda67f1f194a)

EMS is a microservice application to manage employee and department

## **Features** 🌟
- create, update, and soft delete employees
- View individual employee details and list of employees
- create the department
- view the list of departments registered and list of employees within department 

## **Technologies Used:** 🛠️
- Java 21
- Spring Boot 3.2.5
- Hibernate
- MySQL
- Maven
- Spring Cloud 2023.0.2
- Eureka Server
- Eureka Discovery Client
- Webflux Client

## **Services: ** : :electron:
- Employee route
- Department route
- Api Gateway
- Eureka Server host 

## **Requirements** ✔️
for building and running the application you will need the:

- [JDK 21](https://www.oracle.com/my/java/technologies/downloads/#jdk21-windows)
- [Maven 3](https://maven.apache.org/download.cgi)

### Installation 🔽

1. **Clone the repository:**
   ```bash
   https://github.com/ahmadhakimi/microservice-ems-springboot

## **Running application locally** ⏯️
- load maven project
  ``` bash
  mvn clean compile
- ensure all the maven successfully loaded within the project
- run all applications:
  - discovery-server
  - api-gateway
  - employee-service
  - department-service

- Ensure all services are running and check if api-gateway, employee-service, department-service
  ``` bash
  localhost:8761/eureka/web
  or
  localhost:8080/eureka/web


## **Setting up the database ⚙️
- Make sure the MySQL is running
- update the `application.properties` file with your database details such as database uri, username, password, etc.

## **Usage** 📖

###**Employee**
1. Create Employee:
   - ```bash
      POST localhost:8080/api/employee
   - example request body to create employee:
     ```json
     {
     "name":"Kimi",
     "email":"kimi@gmail.com",
     "password":"abc123",
     "role":"USER",
     "createdBy":"kimi",
     "updatedBy":"kimi",
     "deleted":"false",
     "deptId":"100",
     "bonus":"100",
     "salary":"3000",
     "annualLeave":"13",
     "totalClaim":"1399"
     }

3. Employees list
   - ``` bash
     GET localhost:8080/api/employee
     
5. View Employee
   ```bash
   GET localhost:8080/api/employee/{employee_id}
7. Update Employee
   - only put the related update details in the body request only
   ``` JSON
   {
      "name":"Malik",
      "salary":1000
   }

   PUT localhost:8080/api/employee//{id}
9. Delete Employee
   ``` bash
   DELETE localhost:8080/api/employee/{id}
  
###**Department*
1. Create department
   - the body request contains the department name, department address, and department code. Here is the example: 
    ``` json
     {
     "deptName":"Human Resource",
     "deptAddress":"123 Street",
     "deptCode":"HR03"
     }

    POST localhost:8080/api/dept
   
2. Department List
   ```bash
      GET localhost:8080/api/dept
4. View department
   ``` bash
      GET localhost:8080/api/dept/{dept_code}

##**Future Implementation**
- Add security features using JWT and Spring Security 🔐
- Resilience4j for circuit breaker for service fault-tolerance 🚦
- Zipkin and micrometer brave for tracing the HTTP service from the request client until the response to the client 🛤️
- Observe the logs, and error metrics using Prometheus, Grafana, and ELK stack 🔍
- Containerized the services using Docker 🖥️
