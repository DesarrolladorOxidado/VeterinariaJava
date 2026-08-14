package com.guille.vistas;

import com.guille.controladores.*;
import com.guille.modelos.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {

    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_APELLIDO = "apellido";
    private static final String CAMPO_DOCUMENTO = "documento";
    private static final String CAMPO_TELEFONO = "teléfono";
    private static final String CAMPO_MATRICULA = "matrícula";

    private static final String CAMPO_NOMBRE_MASCOTA = "nombre de la mascota";
    private static final String CAMPO_TIPO_MASCOTA = "tipo mascota";
    private static final String CAMPO_RAZA = "raza";
    private static final String CAMPO_PESO = "peso";
    private static final String CAMPO_FECHA_NACIMIENTO = "fecha de nacimiento (día/mes/año)";

    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Scanner scanner;

    private ControladorDuenios controladorDuenios;
    private ControladorVeterinarios controladorVeterinarios;
    private ControladorMascotas controladorMascotas;
    private ControladorConsultas controladorConsultas;
    private ControladorHistoriasClinicas controladorHistoriasClinicas;

    public Aplicacion(){
        this.scanner = new Scanner(System.in);

        this.controladorDuenios = new ControladorDuenios();
        this.controladorVeterinarios = new ControladorVeterinarios();
        this.controladorMascotas = new ControladorMascotas();
        this.controladorConsultas = new ControladorConsultas();
        this.controladorHistoriasClinicas = new ControladorHistoriasClinicas();
        
    }


    public void ejecutar(){

        mostrarOpcionesMenu();

    }

    private void mostrarOpcionesMenu(){
        int opcion = -1;

        do{

            System.out.println("MENU VETERINARIA: por favor, elija una opción:" );
            System.out.println("1 - Registrar veterinario");
            System.out.println("2 - Registrar duenio");
            System.out.println("3 - Registrar mascotas");
            System.out.println("4 - Mostrar veterinarios");
            System.out.println("5 - Mostrar dueños");
            System.out.println("6 - Mostrar mascotas de un dueño");

            if ( this.controladorVeterinarios.cantidadVeterinarios() > 0 )
                System.out.println("7 - Nueva consulta");

            System.out.println("8 - Consultar historia clínica");
            System.out.println("0 - Salir");

            try {
                opcion = Integer.valueOf(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar una opción numérica");
                continue;
            }
            switch (opcion) {
                case 1 -> {
                    System.out.println("*** Registrar veterinario***");
                    registrarVeterinario();

                }
                case 2 -> {
                    System.out.println("*** Registrar duenio ***");
                    registrarDuenio();

                }
                case 3 -> {
                    System.out.println("*** Registrar mascota ***");
                    registrarMascotasDelDuenio();

                }
                case 4 -> {
                    System.out.println("*** Veterinarios ***");
                    mostrarVeterinarios();
                    continuar();

                }
                case 5 -> {
                    System.out.println("*** Duenios ***");
                    mostrarDuenios();
                    continuar();

                }
                case 6 -> {
                    System.out.println("*** Mascotas ***");
                    mostrarMascotas();

                }
                case 7 -> {

                    if ( this.controladorVeterinarios.cantidadVeterinarios() > 0) {
                        System.out.println("*** Nueva consulta ***");
                        nuevaConsulta();
                    }else{
                        System.out.println("No se puede registrar una consulta porque no hay veterinarios registrados");
                        continuar();
                    }
                }
                case 8 -> {
                    System.out.println("*** Consultar historia clínica ***");
                    consultarHistoriaClinica();
                }
                case 0 -> {

                }
                default -> {
                    System.out.println("La opción ingresada es incorrecta. Por favor, vuelva a intentarlo ");

                }
            }
        }while (opcion != 0);

        System.out.println("Gracias por usar el sistema.");

    }

    private void continuar(){
        System.out.println("Presione ENTER para continuar...");
        this.scanner.nextLine();
    }

    /* ----------------------- ------------------------------*/
    //              OPCIONES DE MENU
    /* ----------------------- -------------------------------*/

    private void registrarVeterinario(){
         char rta;

         do{
             String numeroDocumentoVeterinario;
             String matriculaVeterinario;

             do {

                 numeroDocumentoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_DOCUMENTO);
                 if (numeroDocumentoVeterinario.isEmpty())
                     return;

                 if (!this.controladorVeterinarios.existeVeterinarioConDocumento(numeroDocumentoVeterinario))
                     break;

                 System.out.println("Ya existe un veterinario con el número de documento " + numeroDocumentoVeterinario);
                 System.out.println("¿Desea volver a intentar? (s/n)");
                 rta = solicitarRespuestaSiNo();

                 if (rta != 's')
                     return;

             } while ( true);

             do{
                 matriculaVeterinario = solicitarCampoObligatorio(CAMPO_MATRICULA);
                 if (matriculaVeterinario.isEmpty())
                     return;

                 if (!this.controladorVeterinarios.existeVeterinarioConMatricula(matriculaVeterinario))
                     break;

                 System.out.println("Ya existe un veterinario con matricula " + matriculaVeterinario);
                 System.out.println("¿Desea volver a intentar? (s/n)");
                 rta = solicitarRespuestaSiNo();

                 if ( rta != 's')
                     return;

             }while (true);

             String nombreVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_NOMBRE);
             if (nombreVeterinario.isEmpty())
                 return;

             String apellidoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_APELLIDO);
             if (apellidoVeterinario.isEmpty())
                 return;

             String telefonoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_TELEFONO);
             if (telefonoVeterinario.isEmpty())
                 return;

             this.controladorVeterinarios.registrarVeterinario(nombreVeterinario,apellidoVeterinario,numeroDocumentoVeterinario,telefonoVeterinario,matriculaVeterinario);

             System.out.println("¿Ingresar otro veterinario? s/n");
             rta = solicitarRespuestaSiNo();

         }while ( rta == 's');
    }

    private void registrarDuenio(){

        char rta;
        do{

            String numeroDocumentoDuenio;

            do {
                numeroDocumentoDuenio = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
                if (numeroDocumentoDuenio.isEmpty())
                    return;

                if (!this.controladorDuenios.existeDuenioConDocumento(numeroDocumentoDuenio))
                    break;

                System.out.println("Ya existe un duenio con ese documento.");
                System.out.println("¿Desea volver a intentar? (s/n)");
                rta = solicitarRespuestaSiNo();

                if ( rta != 's')
                    return;

            }while (true);

            String nombreDuenio = solicitarCampoObligatorio(CAMPO_NOMBRE);
            if ( nombreDuenio.isEmpty()){
                return;
            }

            String apellidoDuenio = solicitarCampoObligatorio(CAMPO_APELLIDO);
            if ( apellidoDuenio.isEmpty()){
                return;
            }

            String telefonoDuenio = solicitarCampoObligatorio(CAMPO_TELEFONO);
            if ( telefonoDuenio.isEmpty()){
                return;
            }

           this.controladorDuenios.registrarDuenio(nombreDuenio,apellidoDuenio,numeroDocumentoDuenio,telefonoDuenio);

            System.out.println("¿Ingresar otro duenio? s/n");
            rta = solicitarRespuestaSiNo();

        }while (rta == 's');

    }

    private Duenio registrarDuenio(String numeroDocumentoDuenio){

        String nombreDuenio;
        String apellidoDuenio;
        String telefonoDuenio;

        nombreDuenio = solicitarCampoObligatorio(CAMPO_NOMBRE);
        if ( nombreDuenio.isEmpty())
            return null;

        apellidoDuenio = solicitarCampoObligatorio(CAMPO_APELLIDO);
        if ( apellidoDuenio.isEmpty())
            return null;

        telefonoDuenio = solicitarCampoObligatorio(CAMPO_TELEFONO);
        if ( telefonoDuenio.isEmpty())
            return null;

        return this.controladorDuenios.registrarDuenio(nombreDuenio,apellidoDuenio,numeroDocumentoDuenio,telefonoDuenio);
    }

    private void registrarMascotasDelDuenio() {

        char rta;

        Duenio duenio = obtenerDuenioParaMascota();

        if ( duenio == null )
            return;

        do {
            if (!asociarMascotaDuenio(duenio))
                return;

            System.out.println("¿Ingresar otra mascota? s/n");
            rta = solicitarRespuestaSiNo();

        }while ( rta == 's');

    }

    private void mostrarVeterinarios(){

        int cantidadVeterinarios = this.controladorVeterinarios.cantidadVeterinarios();

        if (cantidadVeterinarios > 0) {
            for (int i = 0; i < cantidadVeterinarios; i++) {
                System.out.println( this.controladorVeterinarios.obtenerVeterinarioPorIndice(i));
            }
        }else
            System.out.println("No hay veterinarios registrados aún");

    }

    private void mostrarDuenios(){
        int cantidadDuenios = this.controladorDuenios.cantidadDuenios();
        if (cantidadDuenios>0) {
            for (int i = 0; i < cantidadDuenios; i++) {
                System.out.println(this.controladorDuenios.obtenerDuenioPorIndice(i));
            }
        }else {
            System.out.println("No existen dueños aún");
        }
    }

    private void mostrarMascotas(){

        String numeroDocumento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
        if (numeroDocumento.isEmpty())
            return;

        Duenio duenio = this.controladorDuenios.obtenerDuenioPorDocumento(numeroDocumento);

        if ( duenio != null ){

            if(duenio.tieneMascotas()){
                mostrarMascotasDelDuenio(duenio);
            }else{
                System.out.println("No hay mascotas registradas para este dueño.");
                System.out.println("¿Desea dar de alta mascotas? (s/n)");

                char rta = solicitarRespuestaSiNo();

                if (rta != 's')
                    return;

                registrarMascotasDelDuenio(duenio);
                mostrarMascotasDelDuenio(duenio);

            }
        }else
            System.out.println("No se ha encontrado el duenio con documento: "+ numeroDocumento );

        continuar();
    }

    private void nuevaConsulta(){

        Veterinario veterinario = seleccionarVeterinario();

        if (veterinario == null)
            return;


        Duenio duenio = obtenerDuenioParaMascota();

        if ( duenio != null ){

            if ( !duenio.tieneMascotas()){
                System.out.println(duenio.getNombre() + " " + duenio.getApellido() + " no tiene mascotas registradas");
                System.out.println("¿Desea registrar mascotas? (s/n)");
                char rta = solicitarRespuestaSiNo();

                if (rta != 's')
                    return;

                registrarMascotasDelDuenio(duenio);
            }

            Mascota mascota = seleccionarMascota(duenio);

            if (mascota!=null) {
                registrarConsulta(mascota,veterinario);
                continuar();
            }
        }
    }

    private void consultarHistoriaClinica(){
        String documento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);

        if ( documento.isEmpty()){
            return;
        }

        Duenio duenio = this.controladorDuenios.obtenerDuenioPorDocumento(documento);

        if ( duenio == null){
            System.out.println("No se ha encontrado dueño con el documento especificado");
            continuar();
            return;
        }

        if ( !duenio.tieneMascotas()) {
            System.out.println("No cuenta con mascotas registradas");
            continuar();
            return;
        }

        Mascota mascota = seleccionarMascota(duenio);

        if (mascota == null) {
            System.out.println("No se ha seleccionado ninguna mascota");
            continuar();
            return;
        }

        HistoriaClinica historiaClinica = mascota.getHistoriaClinica();
        if ( historiaClinica.obtenerConsultas().isEmpty()){
            System.out.println("No hay consultas");
            continuar();
            return;
        }

        System.out.println("Historia clínica de: " + mascota.getNombre());
        System.out.println("Fecha creación: " + formatearFechaHora(historiaClinica.getFechaCreacion()));
        System.out.println("Ultima actualización: " + formatearFechaHora(historiaClinica.getFechaActualizacion()));

        consultarConsultasDeHistoriaClinica(historiaClinica);

        continuar();

    }

    private void consultarConsultasDeHistoriaClinica(HistoriaClinica historiaClinica){
        List<Consulta> consultas = historiaClinica.obtenerConsultas();
        int cantidadConsultas = consultas.size();
        int i = 1;

        for( Consulta consulta : consultas) {
            System.out.println( i + " - " + consulta.getMotivo() + " - Fecha: " + formatearFechaHora(consulta.getFecha()));
            i++;
        }

        int opcion = -1;
        boolean opcionValida;
        char rta;
        do {
            System.out.println("Seleccione una consulta para ver el detalle: ");

            do {
                try {
                    opcion = Integer.valueOf(this.scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                opcionValida = (opcion > 0) && (opcion <= cantidadConsultas);

                if (!opcionValida)
                    System.out.println("Opción incorrecta, por favor vuelva a intentarlo");

            }while (!opcionValida);

            System.out.println("Detalle de la consulta:");
            System.out.println(consultas.get(opcion-1));

            System.out.println("¿Ver otra consulta? (s/n)");
            rta = solicitarRespuestaSiNo();

        }while (rta == 's');

    }

    /* ----------------------- ------------------------------*/
    //              MÉTODOS AUXILIARES
    /* ----------------------- -------------------------------*/
    //---BUSQUEDA Y DEVOLUCIÓN
    private Duenio obtenerDuenioParaMascota(){
        char rta;
        String documentoDuenio = solicitarCampoObligatorio(Aplicacion.CAMPO_DOCUMENTO);

        if (documentoDuenio.isEmpty())
            return null;

        Duenio duenio = this.controladorDuenios.obtenerDuenioPorDocumento(documentoDuenio);

        if ( duenio != null)
            return duenio;

        System.out.println("No se ha encontrado el dueño con número de documento: " +  documentoDuenio);
        System.out.println("¿Desea darlo de alta? s/n");

        rta = solicitarRespuestaSiNo();

        if (rta != 's')
            return null;

        return registrarDuenio(documentoDuenio);
    }

    private Mascota seleccionarMascota(Duenio duenio){

        List<Mascota> mascotas = duenio.obtenerMascotas();
        int totalMascotas = duenio.cantidadMascotas();

        int i = 1;
        for (Mascota mascota : mascotas){
            System.out.println(i + " - " + mascota.getNombre() + " - " + mascota.getTipo());
            i++;
        }

        int opcion;
        boolean opcionInvalida;

        do {
            System.out.println("Seleccione una opción por favor: ");

            try {
                opcion = Integer.valueOf(this.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            opcionInvalida = opcion < 1 || opcion > totalMascotas;

            if (opcionInvalida) {
                System.out.println("La opción ingresada es inválida. ¿Desea volver a intentar? (s/n)");

                if (solicitarRespuestaSiNo() != 's')
                    return null;
            }

        } while (opcionInvalida);

        return mascotas.get(opcion - 1);
    }

    private Veterinario seleccionarVeterinario(){

        int totalVeterinarios = this.controladorVeterinarios.cantidadVeterinarios();

        for ( int i = 0; i < totalVeterinarios; i++)
            System.out.println( (i+1) + " - " + this.controladorVeterinarios.obtenerVeterinarioPorIndice(i));

        int opcion = -1;
        boolean opcionInvalida;

        do{
            System.out.println("Por favor, elija un veterinario: ");
            try{
                opcion = Integer.valueOf(this.scanner.nextLine().trim());
            }catch ( NumberFormatException e ){
                opcion  = -1;
            }

            opcionInvalida = opcion < 1 || opcion > totalVeterinarios;

            if (opcionInvalida) {
                System.out.println("La opción ingresada es inválida. ¿Desea volver a intentar? (s/n)");

                if (solicitarRespuestaSiNo() != 's')
                    return null;
            }

        }while ( opcionInvalida);

        return this.controladorVeterinarios.obtenerVeterinarioPorIndice(opcion-1);

    }

    private String solicitarCampoObligatorio(String campo){

        char rta;
        System.out.println("Ingrese " + campo + ": ");
        String valor = this.scanner.nextLine().trim();

        while ( valor.isEmpty()){
            System.out.println("El campo " + campo  + " no puede quedar vacío.");
            System.out.println("¿Desea completarlo? (s/n)");
            rta = solicitarRespuestaSiNo();
            if (rta != 's')
                return "";

            System.out.println("Ingrese " + campo + ": ");
            valor = this.scanner.nextLine().trim();
        }

        return valor;
    }

    private char solicitarRespuestaSiNo(){
        String caracter = this.scanner.nextLine().toLowerCase().trim();

        if (caracter.isEmpty())
            return 'n';
        else
            return caracter.charAt(0);

    }

    //---- INGRESO Y ASOCIACION
    private void registrarMascotasDelDuenio(Duenio duenio){
        char rta;

        do {
            if (!asociarMascotaDuenio(duenio))
                return;

            System.out.println("¿Ingresar otra mascota? s/n");
            rta = solicitarRespuestaSiNo();

        }while ( rta == 's');
    }

    private boolean asociarMascotaDuenio(Duenio duenio){

        String nombre = solicitarCampoObligatorio(CAMPO_NOMBRE_MASCOTA);
        if (nombre.isEmpty())
            return false;


        TipoMascota tipo = null;
        do {
            String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_MASCOTA);
            if (tipoSt.isEmpty())
                return false;

            try {
                tipo = TipoMascota.valueOf(tipoSt.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de mascota incorrecto");
                tipo = null;
            }
        }while ( tipo == null);

        String raza = solicitarCampoObligatorio(CAMPO_RAZA);
        if ( raza.isEmpty())
            return false;

        LocalDate fechaNacimiento = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
        do{
            String fechaNacimientoSt = solicitarCampoObligatorio(CAMPO_FECHA_NACIMIENTO);

            if ( fechaNacimientoSt.isEmpty())
                return false;

            try{

                fechaNacimiento = LocalDate.parse(fechaNacimientoSt, formatoFecha);

                if ( fechaNacimiento.isAfter(LocalDate.now())){
                    System.out.println("La fecha de nacimiento no puede ser posterior a la fecha actual");
                    fechaNacimiento = null;
                }

            }catch ( DateTimeParseException e ){
                System.out.println("El formato de fecha ingresado es inválido");
                fechaNacimiento = null;
            }

        }while (fechaNacimiento == null );

        double peso = 0;
        do{
            String pesoSt = solicitarCampoObligatorio(CAMPO_PESO);
            if ( pesoSt.isEmpty())
                return false;

            try{

                peso = Double.valueOf(pesoSt);

            }catch (NumberFormatException e){
                System.out.println("Peso incorrecto");
                peso = 0;
            }

        }while ( peso <= 0);


        this.controladorDuenios.agregarMascota(duenio,this.controladorMascotas.crearMascota(nombre, tipo, raza, fechaNacimiento, peso));

        return true;
    }

    private void registrarConsulta(Mascota mascota, Veterinario veterinario){
        char rta;
        String diagnostico = "";
        String tratamiento = "";
        String observaciones = "";

        String motivo = solicitarCampoObligatorio("motivo de la consulta");
        if (motivo.isEmpty())
            return;

        System.out.println("¿Ingresa diagnóstico? (s/n)");
        rta = solicitarRespuestaSiNo();
        
        if (rta=='s'){
            System.out.println("Ingrese diagnóstico: ");
            diagnostico = this.scanner.nextLine().trim();
        }
            
        
        System.out.println("¿Indica tratamiento? (s/n)");
        rta = solicitarRespuestaSiNo();
        if (rta=='s'){
            System.out.println("Ingrese tratamiento: ");
            tratamiento = this.scanner.nextLine().trim();
        }


        System.out.println("¿Ingresa observaciones? (s/n)");
        rta = solicitarRespuestaSiNo();
        if (rta=='s'){
            System.out.println("Ingrese observaciones: ");
            observaciones = this.scanner.nextLine().trim();
        }

        this.controladorHistoriasClinicas.agregarConsultaHistoriaClinica(mascota,this.controladorConsultas.crearConsulta(motivo,diagnostico,tratamiento,observaciones,veterinario));

    }


    //---- SALIDA POR PANTALLA
    private void mostrarMascotasDelDuenio(Duenio duenio) {
        List<Mascota> mascotas = duenio.obtenerMascotas();
        for(Mascota mascota : mascotas){
            System.out.println(mascota);
        }
    }

    private String formatearFechaHora(LocalDateTime fechaHora ){
        return fechaHora.format(FORMATO_FECHA_HORA);
    }

}



