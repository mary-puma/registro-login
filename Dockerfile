# Usar una imagen base con Java 17
FROM openjdk:11-slim

# Directorio donde se colocará la aplicación en el contenedor
WORKDIR /app

# Copiar el archivo jar del proyecto al directorio /app en el contenedor
COPY target/registro-login-1.0-SNAPSHOT.jar /app/registro-login.jar

# Exponer el puerto que usa la aplicación
EXPOSE 3306

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/registro-login.jar"]