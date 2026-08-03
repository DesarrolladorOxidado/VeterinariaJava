package com.guille.vistas;

import com.guille.controladores.ControladorDuenios;
import com.guille.modelos.*;

import java.util.ArrayList;
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
    private static final String CAMPO_EDAD = "edad";
    private static final String CAMPO_PESO = "peso";


    private Scanner scanner;

    private ControladorDuenios controladorDuenios;

    //Momentaneo, estos atributos luego van a ir al lugar
    //correspondiente porque no deben estar aquí
    //private List<Duenio> duenios;
    private List<Veterinario> veterinarios;

    public Aplicacion(){
        this.scanner = new Scanner(System.in);
        this.veterinarios = new ArrayList<>();

        this.controladorDuenios = new ControladorDuenios();


    }

    public void ejecutar(){

        mostrarOpcionesMenu();

    }

    private void mostrarOpcionesMenu(){
        int opcion;

        do{

            System.out.println("MENU VETERINARIA: por favor, elija una opción:" );
            System.out.println("1 - Registrar veterinario");
            System.out.println("2 - Registrar duenio");
            System.out.println("3 - Registrar mascotas");
            System.out.println("4 - Mostrar veterinarios");
            System.out.println("5 - Mostrar dueños");
            System.out.println("6 - Mostrar mascotas de un dueño");
            System.out.println("7 - Nueva consulta");
            System.out.println("0 - Salir");

            opcion = Integer.valueOf(scanner.nextLine());

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
                    //continuar();

                }
                case 7 -> {
                    System.out.println("*** Nueva consulta ***");

                    nuevaConsulta();
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
        String nombreVeterinario;
        String apellidoVeterinario;
        String numeroDocumentoVeterinario;
        String telefonoVeterinario;
        String matriculaVeterinario;

        char rta;

        do{

            nombreVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_NOMBRE);
            if (nombreVeterinario.isEmpty())
                return;

            apellidoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_APELLIDO);
            if (apellidoVeterinario.isEmpty())
                return;


            do {

                numeroDocumentoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_DOCUMENTO);
                if (numeroDocumentoVeterinario.isEmpty())
                    return;

                if (!existeVeterinarioConDocumento(numeroDocumentoVeterinario))
                    break;

                System.out.println("Ya existe un veterinario con el número de documento " + numeroDocumentoVeterinario);
                System.out.println("¿Desea volver a intentar? (s/n)");
                rta = this.scanner.nextLine().toLowerCase().charAt(0);

                if (rta != 's')
                    return;

            } while ( true);


            telefonoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_TELEFONO);
            if (telefonoVeterinario.isEmpty())
                return;

            do{
                matriculaVeterinario = solicitarCampoObligatorio(CAMPO_MATRICULA);
                if (matriculaVeterinario.isEmpty())
                    return;

                if (!existeVeterinarioConMatricula(matriculaVeterinario))
                    break;

                System.out.println("Ya existe un veterinario con matricula " + matriculaVeterinario);
                System.out.println("¿Desea volver a intentar? (s/n)");
                rta = this.scanner.nextLine().toLowerCase().charAt(0);

                if ( rta != 's')
                    return;

            }while (true);


            this.veterinarios.add(crearVeterinario(nombreVeterinario,apellidoVeterinario,numeroDocumentoVeterinario,telefonoVeterinario,matriculaVeterinario));

            System.out.println("¿Ingresar otro veterinario? s/n");
            rta = scanner.nextLine().toLowerCase().charAt(0);


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
                rta = this.scanner.nextLine().toLowerCase().charAt(0);

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
            rta = scanner.nextLine().toLowerCase().charAt(0);

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
            rta = scanner.nextLine().toLowerCase().charAt(0);

        }while ( rta == 's');

    }

    private void mostrarVeterinarios(){
        if ( !this.veterinarios.isEmpty()) {
            for (Veterinario veterinario : this.veterinarios) {
                System.out.println(veterinario);
            }
        }else{
            System.out.println("No existen veterinarios aún");
        }
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

                char rta = this.scanner.nextLine().toLowerCase().charAt(0);

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

        Duenio duenio = obtenerDuenioParaMascota();

        if ( duenio != null ){

            if ( !duenio.tieneMascotas()){
                System.out.println(duenio.getNombre() + " " + duenio.getApellido() + " no tiene mascotas registradas");
                System.out.println("¿Desea registrar mascotas? (s/n)");
                char rta = this.scanner.nextLine().toLowerCase().charAt(0);

                if (rta != 's')
                    return;

                registrarMascotasDelDuenio(duenio);
            }

            Mascota mascota = seleccionarMascota(duenio);

            if (mascota!=null) {
                registrarConsulta(mascota.getHistoriaClinica());
                continuar();
            }
        }
    }

    /* ----------------------- ------------------------------*/
    //              MÉTODOS AUXILIARES
    /* ----------------------- -------------------------------*/

    //----CREACIÓN
    private Veterinario crearVeterinario(String nombre,String apellido,String numeroDocumento, String telefono, String matricula){

        return new Veterinario(nombre,apellido,numeroDocumento,telefono,matricula);
    }
    private Mascota crearMascota(String nombre, String tipoMascota, String raza, int edad, double peso){

        Mascota mascota = new Mascota(nombre, TipoMascota.valueOf(tipoMascota));
        mascota.setRaza(raza);
        mascota.setEdad(edad);
        mascota.setPeso(peso);

        return mascota;
    }
    private Consulta crearConsulta(String motivo){
        return new Consulta(motivo);
    }

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

        rta = scanner.nextLine().toLowerCase().charAt(0);

        if (rta != 's')
            return null;

        return registrarDuenio(documentoDuenio);
    }

    private Mascota seleccionarMascota(Duenio duenio){

            Mascota mascotaSeleccionada;
            List<Mascota> mascotas = duenio.obtenerMascotas();
            int i = 1;
            int opcion;
            int totalMascotas = duenio.cantidadMascotas();

            for (Mascota mascota : mascotas){
                System.out.println( i + " - " + mascota.getNombre() + " - " + mascota.getTipo());
                i++;
            }

            System.out.println("Seleccione una opción por favor: ");
            opcion = Integer.valueOf(this.scanner.nextLine());

            boolean opcionInvalida = (opcion < 1) || (opcion > totalMascotas);

            while (opcionInvalida){
                System.out.println("La opción ingresada es inválida. ¿Desea volver a intentar? (s/n)");
                char rta = this.scanner.nextLine().toLowerCase().charAt(0);

                if (rta != 's')
                    return null;

                System.out.println("Seleccione una opción por favor: ");
                opcion = Integer.valueOf(this.scanner.nextLine());

                opcionInvalida = (opcion < 1) || (opcion > totalMascotas);
            }

            mascotaSeleccionada = mascotas.get(opcion-1);

            return mascotaSeleccionada;
    }

    private boolean existeVeterinarioConDocumento(String documento){

        for (Veterinario veterinario : this.veterinarios)
            if ( veterinario.getNumeroDocumento().equals(documento))
                return true;
        return false;
    }
    private boolean existeVeterinarioConMatricula(String matricula){
        for ( Veterinario veterinario : this.veterinarios){
            if (veterinario.getMatricula().equalsIgnoreCase(matricula)){
                return true;
            }
        }

        return false;
    }

    private String solicitarCampoObligatorio(String campo){

        char rta;
        System.out.println("Ingrese " + campo + ": ");
        String valor = this.scanner.nextLine();

        while ( valor.isEmpty()){
            System.out.println("El campo " + campo  + " no puede quedar vacío.");
            System.out.println("¿Desea completarlo? (s/n)");
            rta = this.scanner.nextLine().toLowerCase().charAt(0);
            if (rta != 's')
                return "";

            System.out.println("Ingrese " + campo + ": ");
            valor = this.scanner.nextLine();
        }

        return valor;
    }


    //---- INGRESO Y ASOCIACION
    private void registrarMascotasDelDuenio(Duenio duenio){
        char rta;

        do {
            if (!asociarMascotaDuenio(duenio))
                return;

            System.out.println("¿Ingresar otra mascota? s/n");
            rta = scanner.nextLine().toLowerCase().charAt(0);

        }while ( rta == 's');
    }

    private boolean asociarMascotaDuenio(Duenio duenio){

        String nombre = solicitarCampoObligatorio(CAMPO_NOMBRE_MASCOTA);
        if (nombre.isEmpty())
            return false;

        String tipo = solicitarCampoObligatorio(CAMPO_TIPO_MASCOTA);
        if (tipo.isEmpty())
            return false;

        String raza = solicitarCampoObligatorio(CAMPO_RAZA);
        if ( raza.isEmpty())
            return false;

        String edad = solicitarCampoObligatorio(CAMPO_EDAD);
        if (edad.isEmpty())
            return false;

        String peso = solicitarCampoObligatorio(CAMPO_PESO);
        if ( peso.isEmpty())
            return false;

        this.controladorDuenios.agregarMascota(duenio,crearMascota(nombre, tipo, raza, Integer.parseInt(edad), Double.parseDouble(peso)));

        return true;
    }

    private void registrarConsulta(HistoriaClinica historiaClinica){
        String motivo;
        char rta;
        
        System.out.println("Ingrese el motivo de la consulta: ");
        motivo = this.scanner.nextLine();

        Consulta consulta = crearConsulta(motivo);
        
        System.out.println("¿Ingresa diagnóstico? (s/n)");
        rta = this.scanner.nextLine().toLowerCase().charAt(0);
        
        if (rta=='s'){
            System.out.println("Ingrese diagnóstico: ");
            String diagnostico = this.scanner.nextLine();
            consulta.setDiagnostico(diagnostico);
        }
            
        
        System.out.println("¿Indica tratamiento? (s/n)");
        rta = this.scanner.nextLine().toLowerCase().charAt(0);
        if (rta=='s'){
            System.out.println("Ingrese tratamiento: ");
            String tratamiento = this.scanner.nextLine();
            consulta.setTratamiento(tratamiento);
        }


        System.out.println("¿Ingresa observaciones? (s/n)");
        rta = this.scanner.nextLine().toLowerCase().charAt(0);
        if (rta=='s'){
            System.out.println("Ingrese observaciones: ");
            String observaciones = this.scanner.nextLine();
            consulta.setObservaciones(observaciones);
        }

        historiaClinica.registrarConsulta(consulta);

    }


    //---- SALIDA POR PANTALLA
    private void mostrarMascotasDelDuenio(Duenio duenio) {
        List<Mascota> mascotas = duenio.obtenerMascotas();
        for(Mascota mascota : mascotas){
            System.out.println(mascota);
        }
    }


}


