# VeterinariaJava

Proyecto de práctica desarrollado en Java con el objetivo de reforzar conceptos de Programación Orientada a Objetos, arquitectura MVC y buenas prácticas de desarrollo.

El proyecto consiste en un sistema de gestión para una veterinaria ejecutado mediante consola. Su desarrollo se realiza de forma incremental, incorporando nuevas funcionalidades y refactorizando el código a medida que aparecen nuevas necesidades.

## Objetivos

- Repasar y afianzar Java.
- Aplicar Programación Orientada a Objetos.
- Trabajar la separación de responsabilidades mediante una arquitectura MVC.
- Incorporar validaciones y manejo de errores.
- Utilizar Git y GitHub durante todo el desarrollo.
- Simular el desarrollo y evolución de un proyecto real mediante iteraciones.

## Funcionalidades actuales

Actualmente el sistema permite:

- Registrar dueños.
- Registrar veterinarios.
- Registrar mascotas asociadas a un dueño.
- Consultar las mascotas pertenecientes a un dueño.
- Registrar consultas veterinarias asociadas a una mascota y a un veterinario.
- Gestionar la historia clínica de cada mascota.
- Consultar la historia clínica de una mascota y visualizar sus consultas.
- Registrar la fecha de nacimiento de las mascotas y calcular su edad automáticamente.
- Mostrar dueños, veterinarios y mascotas registradas.
- Validar entradas de usuario y controlar entradas inválidas en los principales flujos de la aplicación.

## Arquitectura

El proyecto se encuentra organizado en tres paquetes principales:

- `modelos`: contiene las entidades del dominio.
- `vistas`: contiene la aplicación de consola y la interacción con el usuario.
- `controladores`: gestiona la creación, búsqueda y asociación de las entidades del sistema.

Entre las principales relaciones del modelo se encuentran:

Duenio → Mascotas  
Mascota → HistoriaClinica  
HistoriaClinica → Consultas
Consulta → Veterinario

La migración inicial hacia MVC se encuentra finalizada. Los principales flujos de la aplicación cuentan actualmente con validaciones y manejo de entradas inválidas. El proyecto continúa evolucionando de forma incremental antes de incorporar persistencia.

## Próximos pasos

- Mejorar la presentación de la información de las consultas.
- Incorporar pruebas automatizadas.
- Preparar el modelo para incorporar persistencia de datos.

## Tecnologías

- Java
- IntelliJ IDEA
- Git
- GitHub

## Estado del proyecto

🚧 En desarrollo.
