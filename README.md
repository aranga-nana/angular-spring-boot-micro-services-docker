<!-- 

Angular 1.4 , spring framework ,spring boot,autorconfigurtaion
-->

# angular-spring-boot-micro-services-docker
sample application to display of working of spring-boot as micro-service and deployes as docker container to the cloud provider (AWS and Azur).
sample will make use of following:
- JPA Configuration
- Overriding Spring boot web initialisation
- using spring auto configuration annotation
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
```

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

```
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
```

#######Basic Docker 
Lets learn bit about docker befor we run spring boot and mysql  to show how above auto configuration works,lets run linking container understands the internals

running mysql docker container

```
$ docker run --name mysql  -v /data/mysql:/var/lib/mysql/ -e MYSQL_ROOT_PASSWORD=password -d mysql
```
above statement will create mysql container called mysql and my docker-host's data/mysql to container's /var/lib/mysql
-v ensure after container been removed the user data is persisted.

#### How link in docker containers work
To experiment on it I have docker container created with mysql client in it. Lets run that container to shoow how 
docker --link works

```
$ docker pull aranga/mysql 
```
this will pull ubuntu image with the mysql client pre-installed in it.
lets run it wit intractive terminal (make sure mysql is running as per above command).
```
$docker run --link mysql:mysql -it aranga/mysql /bin/bash
```
above line tells docker to powert up aranga/mysql image and run bash in interactive mode. it will presents with
the bash prompt. --link will tell aranga/mysql container to add link to mysql container which represent by mysql
once you @ the bash prompt of aranga/my sql. run following command

```
$ printenv
```
you will presents with somthing like this

```
HOSTNAME=f9230acd7f12
MYSQL_ENV_MYSQL_ROOT_PASSWORD=password
TERM=xterm
MYSQL_VERSION=5.7.10-1debian8
MYSQL_PORT_3306_TCP_PORT=3306
MYSQL_PORT_3306_TCP=tcp://172.17.0.2:3306
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
PWD=/
MYSQL_ENV_MYSQL_VERSION=5.7.10-1debian8
HOME=/root
SHLVL=1
MYSQL_PORT_3306_TCP_PROTO=tcp
MYSQL_NAME=/ecstatic_goldstine/mysql
MYSQL_MAJOR=5.7
MYSQL_PORT_3306_TCP_ADDR=172.17.0.2
MYSQL_ENV_MYSQL_MAJOR=5.7
MYSQL_PORT=tcp://172.17.0.2:3306

```
those are the environment variables exposed by mysql container
Back to our spring-boot and conditonal loading.
particualrly we are interested in following variables
```
MYSQL_ENV_MYSQL_ROOT_PASSWORD=password
MYSQL_PORT_3306_TCP_PORT=3306
MYSQL_ENV_MYSQL_VERSION=5.7.10-1debian8
MYSQL_PORT_3306_TCP_ADDR=172.17.0.2
```
back to our Auto config class
```

@Configuration
@ConditionalOnProperty(name = "mysql.env.mysql.version")
public class MySqlContainerEnvConfigImpl implements AppConfig
{
    @Value("${mysql.env.mysql.root.password}")
    private String password;
    @Value("${mysql.port.3306.tcp.addr:localhost}")
    private String host;
    @Value("${mysql.port.3306.tcp.port:3306}")
    private int port;
 
    ....
    .....
}

```
enviorimnet variable MYSQL_ENV_MYSQL_VERSION will maks spering framework to instantiating   'MySqlContainerEnvConfigImpl'
and use the other environment varibale to set the password,host,and port.
lets run our spring-boot container !. this is already build and availbe from my repository
```
docker run --name my-spring-boot-app --link mysql:mysql -d -p 80:8080 aranga/spring-boot:1.2.2 
```
without single configuration our spring boot can auto matically connected to and container name mysql running docker mysql image.
NOTE:depending on the image environment variable name may chanege. so please change them accordingly 







































