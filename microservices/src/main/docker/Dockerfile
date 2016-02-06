FROM java:8
VOLUME /tmp
VOLUME /var/spring-boot
ADD  app-0.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dlog_dir=/var/spring-boot/logs -Dlog_dir=/var/spring-boot/data","-jar","/app.jar"]