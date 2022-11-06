<!-- vscode-markdown-toc -->
* 1. [The use case](#Theusecase)
* 2. [Technical services](#Technicalservices)
	* 2.1. [Configuration service](#Configurationservice)
		* 2.1.1. [Config service dependencies](#Configservicedependencies)
	* 2.2. [Consul Discovery service](#ConsulDiscoveryservice)
	* 2.3. [Gateway service](#Gatewayservice)
		* 2.3.1. [Gateway service Dependencies](#GatewayserviceDependencies)
* 3. [Business services](#Businessservices)
	* 3.1. [Customer service](#Customerservice)
		* 3.1.1. [Customer service Dependencies](#CustomerserviceDependencies)
	* 3.2. [Inventory service](#Inventoryservice)
		* 3.2.1. [Inventory service Dependencies](#InventoryserviceDependencies)
	* 3.3. [Order service](#Orderservice)
		* 3.3.1. [Order service Dependencies](#OrderserviceDependencies)
* 4. [Front-end](#Front-end)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# Microservices-architecture-spring-cloud-use-case


##  1. <a name='Theusecase'></a>The use case 

![usecase](./images/1.PNG)

##  2. <a name='Technicalservices'></a>Technical services 
###  2.1. <a name='Configurationservice'></a>Configuration service
####  2.1.1. <a name='Configservicedependencies'></a>Config service dependencies 
    - Config Server
    - Spring boot Actuator
    - Consul Discovery
###  2.2. <a name='ConsulDiscoveryservice'></a>Consul Discovery service
We will create it as a micro-service, because it is available as a `jar` executable file or, a docker image 
- Website : https://www.consul.io/
- As jar file [v1.13.3 (latest version 2022-11-06)]: https://releases.hashicorp.com/consul/1.13.3/consul_1.13.3_windows_386.zip
- As Docker image: https://hub.docker.com/_/consul


###  2.3. <a name='Gatewayservice'></a>Gateway service
####  2.3.1. <a name='GatewayserviceDependencies'></a>Gateway service Dependencies 
    - Spring Cloud Gateway
    - Consul Discovery : to register in the dicovery service
    - Spring boot Actuator


##  3. <a name='Businessservices'></a>Business services 
###  3.1. <a name='Customerservice'></a>Customer service
####  3.1.1. <a name='CustomerserviceDependencies'></a>Customer service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
###  3.2. <a name='Inventoryservice'></a>Inventory service
####  3.2.1. <a name='InventoryserviceDependencies'></a>Inventory service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
###  3.3. <a name='Orderservice'></a>Order service
####  3.3.1. <a name='OrderserviceDependencies'></a>Order service Dependencies 
    - Spring Web
    - Spring Data Jpa
    - H2 Database
    - Lombok
    - Rest Repositories
    - Consul Discovery : to register in the dicovery service
    - Config client : to find its configuration
    - Spring boot Actuator
##  4. <a name='Front-end'></a>Front-end
