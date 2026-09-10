# VeterinariaJava

Proyecto de práctica desarrollado en Java con el objetivo de reforzar conceptos de Programación Orientada a Objetos, persistencia de datos, arquitectura por capas y buenas prácticas de desarrollo.

El proyecto consiste en un sistema de gestión para una veterinaria ejecutado mediante consola. Su desarrollo se realiza de forma incremental, incorporando nuevas funcionalidades, persistencia y refactorizaciones a medida que aparecen nuevas necesidades.

## Objetivos

- Repasar y afianzar Java.
- Aplicar Programación Orientada a Objetos.
- Trabajar la separación de responsabilidades entre modelos, controladores, persistencia y vista.
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
- `persistencia`: contiene la conexión con PostgreSQL y los DAO encargados del acceso a datos.
- `configuracion`: contiene la creación y configuración de las dependencias de la aplicación.

Entre las principales relaciones del dominio se encuentran:

```text
Duenio → Mascotas
Mascota → HistoriaClinica
HistoriaClinica → Consultas
Consulta → Veterinario
```

La persistencia se implementa mediante JDBC y el patrón DAO. Los objetos del dominio son reconstruidos a partir de los datos almacenados en PostgreSQL, utilizando la base de datos como fuente de verdad del sistema.

El proyecto cuenta además con ambientes separados para desarrollo y pruebas, permitiendo ejecutar los tests de persistencia sobre una base de datos independiente.

## Pruebas

El proyecto utiliza JUnit para probar tanto reglas del dominio como flujos que involucran persistencia.

Actualmente la suite cuenta con **22 tests automatizados**.

## Próximos pasos

- Incorporar transacciones para operaciones que requieren múltiples escrituras relacionadas.
- Continuar mejorando el manejo de errores y validaciones.
- Revisar y optimizar consultas a la base de datos a medida que aumente la complejidad del sistema.
- Continuar evolucionando la capa de persistencia.

## Tecnologías

- Java
- PostgreSQL
- JDBC
- JUnit
- IntelliJ IDEA
- Git
- GitHub

## Estado del proyecto

🚧 En desarrollo.
