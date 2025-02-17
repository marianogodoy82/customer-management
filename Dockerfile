FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/customer-management-*.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
