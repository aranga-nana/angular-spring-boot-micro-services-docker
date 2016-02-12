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
please make sure -Dfile is correct path to the jar.please make sure you have maven install before running
following command
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

####Auto Configuration
######Use Case 1
Instead of passing whole connection string to the data source we can make use
of the auto configuration annotation. To create correct type of instance in this
project make use of @CondtionOnProperty annotation

```

@Configuration
@ConditionalOnProperty(name = "db.type", havingValue = "mysql")
public class MySqlDataSourceDriverImpl implements DataSourceDriver
{
    ..
    ...
}

````
In above case spring framework inject  instance of
MySqlDataSourceDriverImpl.class if the db.type property is
is mysql. please not the application.property will have value defien incase
there's no environment variable or VM variable not defined.


######Use case 2
Use auto configuration to load correct database parameter to when
creating Data Source.

In this case we use two different annotation.
@ConditionalOnMissingBean which act as out default configuration
i.e properties get initalise through VM/ENV variable.

and
@ConditionalOnProperty
if there's no environment variable call 'mysql.env.mysql.version' , the
this class not get initialise, in that case MySqlContainerEnvConfigImpl
bean will be missing and this will cause DefaultConfigProperyImpl to get instantiate because
of @ConditionalOnMissingBean annotation

```
@Configuration
@ComponentScan(basePackages = { "com.*" })
@PropertySource(value = {"classpath:/config.properties","file:${home_dir}/config.properties"},ignoreResourceNotFound = true)
@ConditionalOnMissingBean(MySqlContainerEnvConfigImpl.class)
public class DefaultConfigProperyImpl implements AppConfig
{
   ....
    ....
}

@ConditionalOnProperty(name = "mysql.env.mysql.version")
public class MySqlContainerEnvConfigImpl implements AppConfig
{
    ...
}

please note  that mysql prefix in the property is coming form link name give at the time of docker run

docker run --name spring-boot --link mysql:mysql -d aranga/spring-boot:1.2

```





