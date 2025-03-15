# Use uma imagem base do Java 23
FROM openjdk:23-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY target/OrdemDeServicos-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que sua aplicação Spring Boot usa (geralmente 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]