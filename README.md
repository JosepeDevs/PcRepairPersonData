# Proyecto Microservicios con arquitectura hexagonal, diseño API-FIRST (OAS)
### TODAVÍA EN DESARROLLO
Este proyecto implementa microservicios utilizando una arquitectura hexagonal, API gateway, Eureka y CQRS. Fue desarrollado por JosepeDevs en el repositorio PcRepair.

## Tecnologías Utilizadas

- **Java**
- **Spring**
- **Spring Boot**
- **Spring Cloud**
- **Junit**
- **Mockito**
- **Eureka**
- **API Gateway**
- **Swagger: Swagger-UI, Swagger-codeGen, Swagger ReDoc**
- **CQRS**

## Estilo 
- **Microservicios**
- **Arquitectura hexagonal**
- **Desarrollo API-first (OpenAPI : OAS)**
- **Clases y microservicios aplicando principios SOLID**
- Excepciones propias
- Uso de opcionales de Java
- Value Objects con validadores en los constructores
- Librería Lombok para mayor productividad en clases repetitivas (constructores, getters, setters...)

## Infraestructura

La infraestructura del proyecto incluye:

- **AWS RDS --> PostgreSQL (para las escrituras: commands)**
- **API HTTP REST RestFul**
- **Postman/SwaggerUI**
- **MongoDB para las lectuas (queries)**

## Disclaimer

Este proyecto sirve como ejemplo de cómo implementar CQRS y otros patrones en un entorno real. Sin embargo, cabe destacar que la adición de estos patrones aumenta la complejidad el proyecto. Solo donde exista un beneficio claro lo implementaría (p.e. si hay mucha carga en las lecturas o se usan modelos complicados que implican multiples JOIN de una base de datos) puede merecer la pena aplicar el patrón CQRS, ya que con su planteamiento permite una mejor escalabilidad y mejor tiempo de respuesta. En este proyecto, se ha aplicado CQRS para probarlo, para un proyecto con este alcance realmente no haría falta.
