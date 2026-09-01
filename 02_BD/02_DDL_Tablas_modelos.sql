CREATE TABLE DUENIOS(
id_duenio INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
nombre_duenio VARCHAR(50) NOT NULL,
apellido_duenio VARCHAR(50) NOT NULL,
telefono_duenio VARCHAR(30) NOT NULL,
tipo_documento_duenio VARCHAR(10) NOT NULL,
numero_documento_duenio VARCHAR(15) NOT NULL,
CONSTRAINT FK_TIPO_DOCUMENTO_DUENIO FOREIGN KEY (tipo_documento_duenio) REFERENCES TIPOS_DOCUMENTOS(id_tipo_documento),
CONSTRAINT U_TIPO_NUMERO_DOCUMENTO_DUENIO UNIQUE (tipo_documento_duenio,numero_documento_duenio)
)

CREATE TABLE VETERINARIOS(
id_veterinario INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
nombre_veterinario VARCHAR(50) NOT NULL,
apellido_veterinario VARCHAR(50) NOT NULL,
telefono_veterinario VARCHAR(30) NOT NULL,
tipo_documento_veterinario VARCHAR(10) NOT NULL,
numero_documento_veterinario VARCHAR(15) NOT NULL,
matricula_veterinario VARCHAR(15) NOT NULL,
CONSTRAINT FK_TIPO_DOCUMENTO_VETERINARIO FOREIGN KEY (tipo_documento_veterinario) REFERENCES TIPOS_DOCUMENTOS(id_tipo_documento),
CONSTRAINT U_TIPO_NUMERO_DOCUMENTO_VETERINARIO UNIQUE (tipo_documento_veterinario,numero_documento_veterinario),
CONSTRAINT U_MATRICULA_VETERINARIO UNIQUE(matricula_veterinario)
)

CREATE TABLE MASCOTAS(
id_mascota INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
nombre_mascota VARCHAR(50) NOT NULL,
tipo_mascota VARCHAR(10) NOT NULL,
raza_mascota VARCHAR(50) NOT NULL,
fecha_nacimiento_mascota DATE NOT NULL,
peso_mascota NUMERIC(5,2) NOT NULL,
id_duenio_mascota INT NOT NULL,
CONSTRAINT FK_TIPO_MASCOTA_MASCOTA FOREIGN KEY (tipo_mascota) REFERENCES TIPOS_MASCOTAS(id_tipo_mascota),
CONSTRAINT FK_DUENIO_MASCOTA FOREIGN KEY (id_duenio_mascota) REFERENCES DUENIOS(id_duenio)
)

CREATE TABLE HISTORIAS_CLINICAS(
id_historia_clinica INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
mascota_historia_clinica INT NOT NULL,
fecha_creacion_historia_clinica TIMESTAMP NOT NULL,
fecha_actualizacion_historia_clinica TIMESTAMP NOT NULL,
CONSTRAINT FK_MASCOTA_HISTORIA_CLINICA FOREIGN KEY (mascota_historia_clinica) REFERENCES MASCOTAS(id_mascota),
CONSTRAINT U_MASCOTA_HISTORIA_CLINICA UNIQUE(mascota_historia_clinica)
)

CREATE TABLE CONSULTAS(
id_consulta INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
fecha_consulta TIMESTAMP NOT NULL,
motivo_consulta TEXT NOT NULL,
diagnostico_consulta TEXT NOT NULL,
tratamiento_consulta TEXT NOT NULL,
observaciones_consulta TEXT NOT NULL,
veterinario_consulta INT NOT NULL,
historia_clinica_consulta INT NOT NULL,
CONSTRAINT FK_VETERINARIO_CONSULTA FOREIGN KEY (veterinario_consulta) REFERENCES VETERINARIOS(id_veterinario),
CONSTRAINT FK_HISTORIA_CLINICA_CONSULTA FOREIGN KEY (historia_clinica_consulta) REFERENCES HISTORIAS_CLINICAS(id_historia_clinica)
)
