# Fase de build com Maven e OpenJDK 21
FROM maven:3.8.7-openjdk-21-slim AS build

# Define o diretório de trabalho para a fase de build
WORKDIR /build

# Copia o código-fonte para o contêiner
COPY . .

# Executa o comando Maven para compilar o código e gerar o JAR
RUN mvn clean package -DskipTests

# Fase final: usa uma imagem com OpenJDK 21 para rodar a aplicação
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho para a execução
WORKDIR /app

# Copia o JAR gerado na fase anterior para o contêiner final
COPY --from=build /build/target/EncurtaDev.jar /app/EncurtaDev.jar

# Executa o JAR com o Java
CMD ["java", "-jar", "EncurtaDev.jar"]
