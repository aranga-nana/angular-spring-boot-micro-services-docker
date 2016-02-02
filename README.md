# angular-spring-boot-micro-services-docker
sample application to display of working of spring-boot as micro-service and deployes as docker container to the cloud provider (AWS and Azur)
will going include
- JPA Configuration
- Overriding Spring boot web initialisation
- Angular base client app (using material design)
- Implementation  of token base Authentication and authorisation
- AWS Deployment as docker container.
- Passing DB_URL to the docker container inside AWS 

I know there are so many sample out there in the web but none of them have complete end to end design.
In coming months will work on each topic and let you guys know. if you are interested in drop me an email.
always looking forward for your suggestions and comment. 


###LOGGING
Spring boot use LogBack (self4j implementation) as logging framework which is far superior than log4j
In case if you want to use log4j I include log4 configuration and necessary changes to build script
####NOTE:
Have to remove Logger from all the spring boot module as it is dependency for most 'spring-boot-starter-*' modules

