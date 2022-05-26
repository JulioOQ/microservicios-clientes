FROM openjdk:11
VOLUME /tmp
EXPOSE 8001
ADD ./target/microservicios-clientes-0.0.1-SNAPSHOT.jar microservicio-clientes.jar
ENTRYPOINT ["java","-jar","/microservicio-clientes.jar"]


