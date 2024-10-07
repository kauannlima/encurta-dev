# Fase de build com Maven e OpenJDK 21
FROM maven:3.9.0-openjdk-21-slim AS build

# Define o diretório de trabalho para a fase de build
WORKDIR /app

# Copia o código-fonte para o contêiner
COPY . .

# Executa o Maven para construir o projeto
RUN mvn clean install -DskipTests

# Fase final: usa uma imagem com OpenJDK 21 para rodar a aplicação
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho para a execução
WORKDIR /app

# Expõe a porta que a aplicação vai usar
EXPOSE 8080

# Copia o JAR gerado para o contêiner
COPY --from=build /app/target/EncurtaDev-0.0.1-SNAPSHOT.jar /app/EncurtaDev.jar

# Executa o JAR
CMD ["java", "-jar", "EncurtaDev.jar"]
