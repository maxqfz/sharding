FROM openjdk:11.0.6-jre

COPY build/libs/sharding-*.jar app.jar

ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar