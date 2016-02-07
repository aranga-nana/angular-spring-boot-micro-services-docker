# angular-spring-boot-micro-services-docker
sample application to display of working of spring-boot as micro-service and deployes as docker container to the cloud provider (AWS and Azur).
sample will make use of following:
- JPA Configuration
- Overriding Spring boot web initialisation
- Angular base client app (using material design)
- Implementation  of token base Authentication and authorisation
- AWS Deployment as docker container.
- Passing DB_URL to the docker container inside AWS 

I know there are so many sample out there in the web but none of them have complete end to end design.
In coming months will work on each topic and let you guys know. if you are interested in drop me an email.
always looking forward for your suggestions and comment. 

## How to build the sample

#######jar
NOTE: please give execution permission to gradlew
```
chmod +x ./gradlew
```
build jar file
```
./gradlew build
```
#######docker container image
```
./gradlew buildDocker
```
make sure you have docker account if you want to push it,otherwise change the push flag to false
```
task buildDocker(type: Docker, dependsOn: build) {
	push = true //false - stop pushing to reformatory
	applicationName = jar.baseName
	tagVersion = jarTagName
	dockerfile = file('microservices/src/main/docker/Dockerfile')
	doFirst {

		copy {
			from jar
			into stageDir
		}
	}
}


```

######-DTAG flag
can be use to pass the parameters when building using something like jenkins

##### build container and run !
NOTE: need to have docker running and executing user has to be root or part of the docker group


```
./gradlew -DTAG=1.0 buildDocker
```
run container with tty map to host
```
docker run -p 8080:80 aranga/spring-boot:1.0 -it
```
run in detach mode
```
docker run -p 8080:80 -d aranga/spring-boot:1.0
```

http://docker-host:8080/greeting/

# Code sample explanations !!!

##Loggers
Spring boot use LogBack (self4j implementation) as logging framework which is far superior than log4j
In case if you want to use log4j I include log4 configuration and necessary changes to build script
######NOTE:
Have to remove Logger from all the spring boot module as it is dependency for most 'spring-boot-starter-*' modules
<br>
gradle:
```
    compile("org.springframework.boot:spring-boot-starter-web"){
        exclude  module: "spring-boot-starter-logging" //remove the LogBack
    }

    compile ("org.springframework.boot:spring-boot-starter-data-jpa"){
        exclude  module: "spring-boot-starter-logging" //remove LogBack
    }

```
##Customising Banner

- place banner.txt in classes (place in resource folder will do same!!)
- can use any environment variable /property variable inside the text file too !!!.

##Turn off JPA Auto Configuration
I would like to use my own configuration class to initialise Hibernate. i.e JPAConfig.java.
In order to disable auto config, I have to add these two classes to exclude in EnableAutoConfiguration.
```
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MicroserviceApplication implements ApplicationRunner
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}

```
######NOTE:
In case you want to connect to ms sql server,I recommend using sql jdbc driver from the Microsoft.
Unfortunately it not available in maven repository.You have to manually add it to your local maven repository.
if you want to use sqljdbc4.jar. here's the maven command to add it to local.
please make sure -Dfile is correct path to the jar
```
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sql
-DartifactId=sqljdbc -Dversion=4.0.0 -Dpackaging=jar

```
then add to your gradle build file

```
repositories {

	mavenLocal()
	mavenCentral()

}

dependencies{
 ...
 ...
 compile("com.microsoft.sql:sqljdbc:4.0.0")
}