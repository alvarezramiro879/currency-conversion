# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR a la imagen
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre tu aplicación
EXPOSE 8080

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]