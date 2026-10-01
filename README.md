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
- Evolucionar progresivamente el proyecto hacia Spring Boot.

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
- Editar los datos de dueños, veterinarios y mascotas.
- Persistir la información del sistema en PostgreSQL.
- Gestionar tipos de documento y tipos de mascota mediante catálogos persistidos en la base de datos.
- Mantener el estado activo/inactivo de dueños, veterinarios y mascotas como soporte para bajas lógicas.
- Validar entradas de usuario y controlar entradas inválidas en los principales flujos de la aplicación.

## Arquitectura

El proyecto separa las distintas responsabilidades del sistema mediante:

- `modelos`: contiene las entidades y reglas del dominio.
- `vistas`: contiene la aplicación de consola y la interacción con el usuario.
- `controladores`: coordina los casos de uso de la aplicación.
- `servicios`: coordina operaciones que requieren múltiples acciones relacionadas dentro de un mismo caso de uso.
- `persistencia`: contiene la conexión con PostgreSQL, los DAO encargados del acceso a datos y la gestión de transacciones.
- `configuracion`: contiene la creación y configuración de las dependencias de la aplicación y utilidades compartidas de configuración.

Entre las principales relaciones del dominio se encuentran:

- `Duenio → Mascotas`
- `Mascota → HistoriaClinica`
- `HistoriaClinica → Consultas`
- `Consulta → Veterinario`

La persistencia se implementa mediante JDBC y el patrón DAO. Los objetos del dominio son reconstruidos a partir de los datos almacenados en PostgreSQL, utilizando la base de datos como fuente de verdad del sistema.

Los tipos de documento y tipos de mascota se administran como catálogos persistidos, evitando mantener sus posibles valores definidos directamente en el código de la aplicación.

Dueños, veterinarios y mascotas cuentan además con un estado activo/inactivo persistido. Esto permite conservar las entidades y sus relaciones históricas sin recurrir a eliminaciones físicas. Las consultas históricas pueden reconstruir entidades inactivas cuando sea necesario, mientras que los flujos operativos restringen las operaciones que requieren entidades activas.

Las operaciones que requieren múltiples escrituras relacionadas utilizan transacciones para mantener la consistencia de los datos. Actualmente se aplican, entre otros casos, al registro de una mascota junto con su historia clínica y al registro de una consulta junto con la actualización de su historia clínica.

El manejo de fechas y horas se encuentra centralizado para mantener criterios uniformes de parseo, formato y precisión temporal compatible con la persistencia en PostgreSQL.

El proyecto cuenta además con ambientes separados para desarrollo y pruebas, permitiendo ejecutar los tests de persistencia sobre una base de datos independiente.

## Manejo de errores y logging

La aplicación diferencia los mensajes destinados al usuario de la información técnica necesaria para diagnosticar errores.

Las excepciones relacionadas con persistencia se propagan hasta el punto de la aplicación encargado de decidir cómo continuar el flujo. Los errores recuperables permiten regresar al menú y volver a intentar la operación, mientras que los errores que impiden continuar con el funcionamiento básico de la aplicación provocan su finalización controlada.

El logging se implementa utilizando SLF4J y Logback. Los errores técnicos y sus excepciones se registran tanto en consola como en archivos de log, con rotación por fecha y tamaño y conservación de archivos históricos.

## Pruebas

El proyecto utiliza JUnit para probar tanto reglas del dominio como flujos que involucran persistencia.

Actualmente la suite cuenta con **30 tests automatizados**.

Las pruebas utilizan una base de datos independiente y verifican, entre otros aspectos, el registro, recuperación y actualización de entidades, las operaciones transaccionales y la correcta reconstrucción del estado persistido.

También se comprueba el manejo del estado activo/inactivo de dueños, veterinarios y mascotas, incluyendo el estado activo asignado al registrarlos y la recuperación de entidades previamente marcadas como inactivas en la base de datos.

## Próximos pasos

- Realizar una revisión general de la etapa actual del proyecto, incluyendo dominio, controladores, servicios y persistencia.
- Revisar posibles refactorizaciones y código que haya quedado obsoleto durante la evolución del sistema.
- Consolidar la versión actual de consola y JDBC como base estable del proyecto.
- Continuar la evolución del proyecto incorporando Spring Boot.

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

Actualmente se encuentra finalizando la etapa basada en aplicación de consola y persistencia mediante JDBC, antes de continuar su evolución con Spring Boot.
