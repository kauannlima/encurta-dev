# Fase de build: OpenJDK 17 e Maven
FROM openjdk:17-jdk-slim AS build

# Instala o Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho para a fase de build
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte para o contêiner
COPY src ./src

# Executa o Maven para construir o projeto
RUN mvn clean install -DskipTests

# Fase final: usa uma imagem leve do OpenJDK 17
FROM openjdk:17-slim

# Define o diretório de trabalho para a execução
WORKDIR /app

# Expõe a porta em que a aplicação vai rodar
EXPOSE 8080

# Copia o JAR gerado para o contêiner
COPY --from=build /app/target/EncurtaDev-0.0.1-SNAPSHOT.jar /app/EncurtaDev.jar

# Executa o JAR
CMD ["java", "-jar", "EncurtaDev.jar"]
