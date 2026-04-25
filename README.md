# atividadeServidoresII
# Projeto Spring Boot com PostgreSQL

Este projeto utiliza Spring Boot com integração ao PostgreSQL.

## Como configurar o PostgreSQL

Antes de executar o projeto, é necessário configurar o banco de dados no arquivo:

```properties
src/main/resources/application.properties
```

## Exemplo de configuração

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogoDB
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Explicação

* `seu_banco`: nome do banco criado no PostgreSQL
* `seu_usuario`: usuário do PostgreSQL
* `sua_senha`: senha do PostgreSQL

## Dependência Maven

Verifique se no `pom.xml` existe a dependência:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Como executar

1. Criar o banco no PostgreSQL
2. Configurar o `application.properties`
3. Rodar o projeto Spring Boot

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
