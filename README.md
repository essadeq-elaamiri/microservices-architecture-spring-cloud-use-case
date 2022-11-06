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

Just to make thing easy to navigate between the microservices without the need to open a new window for each one we can in Intellij Idea 
1. `create a project ` (empty one).
2. Add the projects as modules to the empty project `Add new Module from existing source`.


##  2. <a name='Technicalservices'></a>Technical services 

###  2.2. <a name='ConsulDiscoveryservice'></a>Consul Discovery service
We will create it as a micro-service, because it is available as a `jar` executable file or, a docker image 
- Website : https://www.consul.io/
- As jar file [v1.13.3 (latest version 2022-11-06)]: https://releases.hashicorp.com/consul/1.13.3/consul_1.13.3_windows_386.zip
- As Docker image: https://hub.docker.com/_/consul

- Pulling the image :
```
$ docker pull consul
```

- Run it in dev mode (UI):
```
$ docker run -d -p 8500:8500 -p 8600:8600/udp --name=my-consul consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0

```
- Visiting `http://localhost:8500` (`http://localhost:8500/ui/dc1/services`)

![http://localhost:8500/ui/dc1/services](./images/3.PNG)


###  2.1. <a name='Configurationservice'></a>Configuration service
####  2.1.1. <a name='Configservicedependencies'></a>Config service dependencies 
    - Config Server
    - Spring boot Actuator
    - Consul Discovery
####  Activating config service

```java
@SpringBootApplication
@EnableConfigServer
public class ECommConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommConfigServiceApplication.class, args);
	}

}

```
####  Config service properties 

```properties
server.port=8888
spring.application.name=config-service
## referring to a local github repo to control the config versions
spring.cloud.config.server.git.uri=file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo
```
####  Config service repo and files  

- Above the path `path` is refering to the configuration repository that we use to store the configuration.
- We used a local path (file protocol), but we can use any other way to access our config(remote repo for example...).
- It should be an external repository (Outside the microservice)
- And it should be a git repostory to follow and control the configuration history (versionning)|[Detecting changes].
- We can initialize the git repo by `$ git init`
- The repo holds the global config, which is shared between all the microservices and custom congif for each one we want :

![config repo](./images/4.PNG)

- We can also create a config file for the developement and one other for production and other for test for example (`Custom config files for each environment`)

![dev](./images/5.PNG)

- Here our config-service is on the consul dashboard .But, there is aproblem (Exception):

<pre>
...
org.springframework.cloud.config.server.environment.NoSuchLabelException: No such label: master
...
</pre>
- **Solution** : we sould just `git add` our files and `git commit` them.
- The service will be registred implicitlly to consul, but we can force that with :

```java

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient // Enable Register to discovery server 
public class ECommConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommConfigServiceApplication.class, args);
	}
}

```
- Now we can access the configuration via `http://localhost:8888/<service-name>/<environment>`
- Here the result of visiting  `http://localhost:8888/customer-service/dev`:

```json
{
  "name": "customer-service",
  "profiles": [
    "dev"
  ],
  "label": null,
  "version": "dec25c8ed68c614619d3a7249f397c8842eb004a",
  "state": null,
  "propertySources": [
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\application.properties",
      "source": {
        "global.parmas.globlaName": "e-comm-enset"
      }
    }
  ]
}
```
- We can return `default`, `dev`, `prod` ... configuration just by changing the `<environement>` 

#### Tests

- Visit `http://localhost:8888/inventory-service/dev` 

<details>

```json
{
  "name": "inventory-service",
  "profiles": [
    "dev"
  ],
  "label": null,
  "version": "07c770e67a73facb14e6998017faa925b267dc77",
  "state": null,
  "propertySources": [
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\inventory-service-dev.properties",
      "source": {
        "inventory.params.inv1": "4500"
      }
    },
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\application.properties",
      "source": {
        "global.parmas.globlaName": "e-comm-enset"
      }
    }
  ]
}
```

</details>

- Visit `http://localhost:8888/inventory-service/prod` 

<details>

```json
{
  "name": "inventory-service",
  "profiles": [
    "prod"
  ],
  "label": null,
  "version": "07c770e67a73facb14e6998017faa925b267dc77",
  "state": null,
  "propertySources": [
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\inventory-service-prod.properties",
      "source": {
        "inventory.params.inv1": "4500"
      }
    },
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\application.properties",
      "source": {
        "global.parmas.globlaName": "e-comm-enset"
      }
    }
  ]
}
```

</details>

- Visiting : `http://localhost:8888/application/default`
- Returns the global config 

<details>

```json
{
  "name": "application",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "07c770e67a73facb14e6998017faa925b267dc77",
  "state": null,
  "propertySources": [
    {
      "name": "file:///C:/Users/elaam/IdeaProjects/microservices-architecture-spring-cloud-use-case/e-comm-config-repo/file:C:\\Users\\elaam\\IdeaProjects\\microservices-architecture-spring-cloud-use-case\\e-comm-config-repo\\application.properties",
      "source": {
        "global.parmas.globlaName": "e-comm-enset"
      }
    }
  ]
}
```

</details>

#### How to use it ?
- In the functional services, we have to add the ability to seach the config to the service
- The dependency `config client` will help us in that.
- We just use the property : `spring.config.import=optional:configserver:http://localhost:8888`
- Now the service will seach its configuration in the config server via `http://localhost:8888`
- See that in details here => [customer service](#Customerservice)


###  2.3. <a name='Gatewayservice'></a>Gateway service
####  2.3.1. <a name='GatewayserviceDependencies'></a>Gateway service Dependencies 
    - Spring Cloud Gateway
    - Consul Discovery : to register in the dicovery service
    - Spring boot Actuator


##  3. <a name='Businessservices'></a>Business services (Functional services)
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
