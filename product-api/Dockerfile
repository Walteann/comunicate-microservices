FROM maven:3.8.5-jdk-11
COPY --chown=maven:maven . .
WORKDIR .
RUN mvn clean install -DskipTests
EXPOSE 8081
CMD ["mvn", "spring-boot:run"]
