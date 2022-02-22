# Eureka start guide:
![img.png](img.png)
![img_1.png](img_1.png)
# Eureka Server: eureka(registry)
## Set up Server: 
1. Add dependencies: Config Client and Eureka Server on spring initializr
2. set[application.properties](./eureka/src/main/resources/application.properties):
   - set application name
   - set default port 8761
   - indicate that this is a server rather than client
   - set log level to see additional info
     - now use: {TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF} instead of ON as the value
3. [EurekaApplication](./eureka/src/main/java/com/udacity/eureka/EurekaApplication.java): add annotation to notify that is a eureka server
4. http://localhost:8761/ to see eureka page

# Eureka Client service: dog microservice:
##  how does Spring Data Rest work?
1. At application startup, Spring Data Rest finds all of the **spring data repositories**
2. Then, Spring Data Rest **creates an endpoint** that matches the entity name
3. Next, Spring Data Rest **appends an S** to the entity name in the endpoint
4. Lastly, Spring Data Rest exposes CRUD (Create, Read, Update, and Delete) operations as RESTful APIs over HTTP
   
    
## spring data rest client
1. create entity: [Dog.java](./dogMicroservice/src/main/java/com/udacity/dogMicroservice/entity/Dog.java) @Entity, @Id (auto generation strategy)
2. Create a repository that extends CrudRepository.[DogRepository.java](./dogMicroservice/src/main/java/com/udacity/dogMicroservice/repository/DogRepository.java)
3. application.properties: [application.properties](./dogMicroservice/src/main/resources/application.properties)
  - H2 settings
  - spring.jpa.defer-datasource-initialization let hibernate create the table for us from data.sql
4. there is no controller layer, no need to address controller annotation
5. access: http://localhost:8082/dogs for dog details created by Spring Data Rest (append s)


## Registration
- Step 1: Turn the microservice into a Eureka client by adding the appropriate annotations and dependencies in the Maven POM file.
  1. [pom](./dogMicroservice/pom.xml):
    - add eureka client, cloud config, dependencies management
  2. [DogMicroserviceApplication](./dogMicroservice/src/main/java/com/udacity/dogMicroservice/DogMicroserviceApplication.java):
    - add annotation: @EnableEurekaClient to notify spring that it is a eureka client

- Step 2: Use a web browser to view the Eureka web console to ensure your microservice is registered.
  - after modification, access: http://localhost:8761/
  - an instance: DOG-MICROSERVICE appeared, which means new dog-service shown as registered with the Eureka server.

# Question：
1. what dependencies management do?
2. what:eureka.client.serviceUrl.defaultZone mean? why need two lines of this?
   - what is that mean:
        - In the preceding example, "defaultZone" is a magic string fallback value that provides the service URL for any client that does not express a preference (in other words, it is a useful default).
   - do we need two lines?
    - Actually there is no difference between two default zones.   
        It is just that we can define the default zones in two such ways.   
        You can remove any one of the zones and try to run the application, it will work.
    

3. what eureka.instance.prefer-ip-address mean?
    - In some cases, it is preferable for Eureka to advertise the IP addresses of services rather than the hostname.
    - . Set eureka.instance.preferIpAddress to true and, when the application registers with eureka, it uses its IP address rather than its hostname.

4. difference between CrudRepository and @Repository?
    - @Repository: 
        - The inclusion of this annotation is necessary since it will allow the DogRepository to be included in the Application Context/Spring Container and thus be used throughout the application.
    - CrudRepository:
        - the CrudRepository interface already provides us with methods that perform CRUD operations and that we can use them as they are:
            1. save
            2. findById 
            3. findAll 
            4.saveAll
            5. delete 
            6. deleteById 
            7. deleteAll
        -  we have to follow this structure because it is the way Spring Data gives us to perform CRUD operations, which is similar to the structure MyBatis gives us to perform CRUD operations.
5. eureka.instance.prefer-ip-address=true?
    - Set eureka.instance.preferIpAddress to true and, when the application registers with eureka, it uses its IP address rather than its hostname.