# Proyecto API-FIRST OAS

Este proyecto implementa microservicios utilizando una arquitectura hexagonal, API gateway, Eureka y CQRS. Fue desarrollado por JosepeDevs en el repositorio PcRepair.

## Tecnologías Utilizadas

- **Java**
- **Spring**
- **Spring Boot**
- **Eureka**
- **API Gateway**
- **CQRS**

Además, se aplican principios de arquitectura hexagonal y desarrollo API-first, siguiendo las especificaciones de OpenAPI.

## Infraestructura

La infraestructura del proyecto incluye:

- **AWS RDS**
- **PostgreSQL**
- **MongoDB**

## Backend

El backend está compuesto por una API REST basada en OpenAPI, diseñada según lo planificado.

## Frontend

El frontend proporciona funcionalidades para:

- Alta de clientes
- Edición de clientes
- Actualización de clientes
- Borrado lógico de clientes
- Gestión de catálogo de reparaciones
  - Alta de posibles reparaciones
  - Edición de posibles reparaciones
  - Búsqueda de posibles reparaciones
  - Borrado lógico de posibles reparaciones

## Microservicios y Casos de Uso

Los microservicios cubren diferentes aspectos del negocio, incluyendo la gestión de pedidos:

- Alta de pedidos
- Edición de pedidos
- Búsqueda de pedidos
- Borrado lógico de pedidos

## Disclaimer

Este proyecto sirve como ejemplo de cómo implementar CQRS y otros patrones en un entorno real. Sin embargo, cabe destacar que la adición de estos patrones puede aumentar la complejidad innecesariamente en proyectos donde no son requeridos, simplemente por su naturaleza o escala.
