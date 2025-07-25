# Etapa 1: Construção do aplicativo
FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

# Copia o arquivo pom.xml e o código fonte
COPY pom.xml .
COPY src ./src

# Baixa as dependências e compila o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/chapecos-0.0.1-SNAPSHOT.jar /app/chapecos.jar

CMD ["java", "-jar", "/app/chapecos.jar"]

EXPOSE 8091