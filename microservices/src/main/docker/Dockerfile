FROM java:8
VOLUME /tmp
VOLUME /var/log/spring-boot
VOLUME /data
ADD  app-0.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlog_dir=/var/log/spring-boot/","-Dhome.dir=/data","-Dserver.port=80","-jar","/app.jar"]