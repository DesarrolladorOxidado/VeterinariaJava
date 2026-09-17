# VeterinariaJava

Proyecto de práctica desarrollado en Java con el objetivo de reforzar conceptos de Programación Orientada a Objetos, persistencia de datos, arquitectura por capas y buenas prácticas de desarrollo.

El proyecto consiste en un sistema de gestión para una veterinaria ejecutado mediante consola. Su desarrollo se realiza de forma incremental, incorporando nuevas funcionalidades, persistencia y refactorizaciones a medida que aparecen nuevas necesidades.

## Objetivos

- Repasar y afianzar Java.
- Aplicar Programación Orientada a Objetos.
- Trabajar la separación de responsabilidades entre modelos, controladores, servicios, persistencia y vista.
- Implementar persistencia de datos utilizando PostgreSQL y JDBC.
- Incorporar pruebas automatizadas.
- Incorporar validaciones y manejo de errores.
- Utilizar Git y GitHub durante todo el desarrollo.
- Simular el desarrollo y evolución de un proyecto real mediante iteraciones.

## Funcionalidades actuales

Actualmente el sistema permite:

- Registrar dueños.
- Registrar veterinarios.
- Registrar mascotas asociadas a un dueño.
- Consultar las mascotas pertenecientes a un dueño.
- Registrar consultas veterinarias realizadas por un veterinario.
- Crear automáticamente una historia clínica al registrar una mascota.
- Consultar la historia clínica y las consultas de una mascota.
- Mostrar dueños, veterinarios y mascotas registrados.
- Persistir la información del sistema en PostgreSQL.
- Validar entradas de usuario y controlar entradas inválidas en los principales flujos de la aplicación.

## Arquitectura

El proyecto separa las distintas responsabilidades del sistema mediante:

- `modelos`: contiene las entidades y reglas del dominio.
- `vistas`: contiene la aplicación de consola y la interacción con el usuario.
- `controladores`: coordina los casos de uso de la aplicación.
- `servicios`: coordina operaciones que requieren múltiples acciones relacionadas dentro de un mismo caso de uso.
- `persistencia`: contiene la conexión con PostgreSQL, los DAO encargados del acceso a datos y la gestión de transacciones.
- `configuracion`: contiene la creación y configuración de las dependencias de la aplicación.

Entre las principales relaciones del dominio se encuentran:

```text
Duenio → Mascotas
Mascota → HistoriaClinica
HistoriaClinica → Consultas
Consulta → Veterinario
```

La persistencia se implementa mediante JDBC y el patrón DAO. Los objetos del dominio son reconstruidos a partir de los datos almacenados en PostgreSQL, utilizando la base de datos como fuente de verdad del sistema.

Las operaciones que requieren múltiples escrituras relacionadas utilizan transacciones para mantener la consistencia de los datos. Actualmente se aplican, entre otros casos, al registro de una mascota junto con su historia clínica y al registro de una consulta junto con la actualización de su historia clínica.

El proyecto cuenta además con ambientes separados para desarrollo y pruebas, permitiendo ejecutar los tests de persistencia sobre una base de datos independiente.

## Manejo de errores y logging

La aplicación diferencia los mensajes destinados al usuario de la información técnica necesaria para diagnosticar errores.

Las excepciones relacionadas con persistencia se propagan hasta el punto de la aplicación encargado de decidir cómo continuar el flujo. Los errores recuperables permiten regresar al menú y volver a intentar la operación, mientras que los errores que impiden continuar con el funcionamiento básico de la aplicación provocan su finalización controlada.

El logging se implementa utilizando SLF4J y Logback. Los errores técnicos y sus excepciones se registran tanto en consola como en archivos de log, con rotación por fecha y tamaño y conservación de archivos históricos.

## Pruebas

El proyecto utiliza JUnit para probar tanto reglas del dominio como flujos que involucran persistencia.

Actualmente la suite cuenta con 24 tests automatizados.

## Próximos pasos

- Continuar mejorando las validaciones de datos y entradas de usuario.
- Incorporar nuevos casos de uso, como la edición de datos registrados.
- Ampliar la cobertura de pruebas automatizadas a medida que se incorporen nuevas funcionalidades.

## Tecnologías

- Java
- PostgreSQL
- JDBC
- JUnit
- SLF4J
- Logback
- IntelliJ IDEA
- Git
- GitHub

## Estado del proyecto

🚧 En desarrollo.
