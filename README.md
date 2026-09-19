# Nexvia

Plataforma backend para la gestión, priorización, escalamiento y seguimiento de tickets y reclamos.

Proyecto académico desarrollado para el curso **Diseño de Patrones**.

## Tecnologías

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Patrones de diseño

- Chain of Responsibility
- Command
- Factory
- Observer
- State
- Strategy

## Funcionalidades

- Gestión de tickets y reclamos.
- Priorización mediante estrategias.
- Gestión de estados.
- Escalamiento de tickets.
- Notificaciones asociadas a eventos.
- Persistencia mediante PostgreSQL.
- Exposición de servicios REST.

## Configuración local

Crea una base de datos PostgreSQL llamada `nexvia` y configura las variables de entorno:

```text
DB_URL=jdbc:postgresql://localhost:5432/nexvia
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
```

También puedes ajustar `src/main/resources/application.properties`.

## Ejecución

En Windows:

```bash
mvnw.cmd spring-boot:run
```

En Linux/macOS:

```bash
./mvnw spring-boot:run
```

> Proyecto académico desarrollado con fines de aprendizaje.
