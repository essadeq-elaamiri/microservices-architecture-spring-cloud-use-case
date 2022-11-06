# Microservices-architecture-spring-cloud-use-case

## Content 
- [Use case](#the-use-case)
- [Technical services ](#technical-services)
    - [Configuration service](#configuration-service)
- [Business services ](#business-services)
- [Front-end](#front-end)

## The use case 

![usecase](./images/1.PNG)

## Technical services 
### Configuration service
#### Config service dependencies 
    - Config Server
    - Spring boot Actuator
    - Consul Discovery
### Consul Discovery service
We will create it as a micro-service, because it is available as a `jar` executable file or, a docker image 
- Website : https://www.consul.io/
- As jar file [v1.13.3 (latest version 2022-11-06)]: https://releases.hashicorp.com/consul/1.13.3/consul_1.13.3_windows_386.zip
- As Docker image: https://hub.docker.com/_/consul


### Gateway service
#### Gateway service Dependencies 
    - Spring Cloud Gateway
    - Consul Discovery : to register in the dicovery service
    - Spring boot Actuator


## Business services 
### Customer service
#### Customer service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
### Inventory service
#### Inventory service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
### Order service
#### Order service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
## Front-end
