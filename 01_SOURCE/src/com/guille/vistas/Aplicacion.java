package com.guille.vistas;

import com.guille.controladores.Controladores;
import com.guille.modelos.*;
import com.guille.vistas.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {

    private static final Logger logger = LoggerFactory.getLogger(Aplicacion.class);

    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_APELLIDO = "apellido";
    private static final String CAMPO_TIPO_DOCUMENTO = "tipo documento";
    private static final String CAMPO_DOCUMENTO = "documento";
    private static final String CAMPO_TELEFONO = "teléfono";
    private static final String CAMPO_MATRICULA = "matrícula";

    private static final String CAMPO_NOMBRE_MASCOTA = "nombre de la mascota";
    private static final String CAMPO_TIPO_MASCOTA = "tipo mascota";
    private static final String CAMPO_RAZA = "raza";
    private static final String CAMPO_PESO = "peso";
    private static final String CAMPO_FECHA_NACIMIENTO = "fecha de nacimiento (día/mes/año)";

    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Scanner scanner;

    private final Controladores controladores;

    public Aplicacion(Controladores controladores){
        this.scanner = new Scanner(System.in);
        this.controladores = controladores;
    }


    public void ejecutar(){

        try {
            mostrarOpcionesMenu();
        }catch (SQLException e){
            logger.error("Error al acceder a la base de datos",e);
            System.out.println("Ocurrió un problema al acceder a los datos. La aplicación se cerrará");
        }
    }

    private void mostrarOpcionesMenu() throws SQLException{
        int opcion;
        OpcionMenuPrincipal opcionSeleccionada = null;

        do{

            List<OpcionMenuPrincipal> menuPrincipal = new ArrayList<>(List.of(OpcionMenuPrincipal.values()));

            boolean existenVeterinarios = this.controladores.getControladorVeterinarios().existenVeterinarios();

            if (!existenVeterinarios)
                menuPrincipal.remove(OpcionMenuPrincipal.NUEVA_CONSULTA);

            Menu.opcionesMenuPrincipal(menuPrincipal);

            try {
                opcion = Integer.valueOf(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar una opción numérica");
                continue;
            }

            if ( opcion < 1 || opcion > menuPrincipal.size()) {
                System.out.println("La opción ingresada es incorrecta. Por favor, vuelva a intentarlo ");
                continue;
            }

            opcionSeleccionada = menuPrincipal.get(opcion-1);

            switch (opcionSeleccionada) {
                case REGISTRAR_VETERINARIO -> {
                    System.out.println("*** Registrar veterinario***");
                    registrarVeterinario();

                }
                case REGISTRAR_DUENIO -> {
                    System.out.println("*** Registrar duenio ***");
                    registrarDuenio();

                }
                case REGISTRAR_MASCOTA -> {
                    System.out.println("*** Registrar mascota ***");
                    registrarMascotasDelDuenio();

                }
                case MOSTRAR_VETERINARIOS -> {
                    System.out.println("*** Veterinarios ***");
                    mostrarVeterinarios();
                    continuar();

                }
                case MOSTRAR_DUENIOS -> {
                    System.out.println("*** Duenios ***");
                    mostrarDuenios();
                    continuar();

                }
                case MOSTRAR_MASCOTAS -> {
                    System.out.println("*** Mascotas ***");
                    mostrarMascotas();

                }
                case NUEVA_CONSULTA -> {

                        if ( existenVeterinarios ) {
                            System.out.println("*** Nueva consulta ***");
                            nuevaConsulta();
                        }else{
                            System.out.println("No se puede registrar una consulta porque no hay veterinarios registrados");
                            continuar();
                        }
                }
                case CONSULTAR_HISTORIA_CLINICA -> {
                    System.out.println("*** Consultar historia clínica ***");
                    consultarHistoriaClinica();
                }
                case EDITAR -> {
                    System.out.println("*** Editar datos ***");
                    editarDatos();
                }
                case SALIR -> {
                    System.out.println("*** GRACIAS POR USAR EL SITEMA ***");
                }
            }
        }while (opcionSeleccionada != OpcionMenuPrincipal.SALIR);

    }

    private void editarDatos(){
        int opcion;
        OpcionesMenuEdicion opcionSeleccionada = null;

        do {
            List<OpcionesMenuEdicion> menuEdicion = new ArrayList<>(List.of(OpcionesMenuEdicion.values()));
            Menu.opcionesMenuEdicion(menuEdicion);

            try {
                opcion = Integer.valueOf(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar una opción numérica");
                continue;
            }

            if ( opcion < 1 || opcion > menuEdicion.size()) {
                System.out.println("La opción ingresada es incorrecta. Por favor, vuelva a intentarlo ");
                continue;
            }

            opcionSeleccionada = menuEdicion.get(opcion-1);

            switch (opcionSeleccionada){
                case EDITAR_VETERINARIO -> {
                        editarVeterinario();
                }
                case EDITAR_DUENIO -> {
                        editarDuenio();
                }
                case EDITAR_MASCOTA -> {

                }
            }
        }while (opcionSeleccionada != OpcionesMenuEdicion.VOLVER);
    }

    private void editarVeterinario() {
        System.out.println("\n******POR FAVOR, SELECCIONE UN VETERINARIO: ");

        Veterinario veterinario = seleccionarVeterinario();

        if ( veterinario == null)
            return;

        int opcion;
        OpcionesEditarVeterinario opcionSeleccionada = null;

        do {
            List<OpcionesEditarVeterinario> opcionesEditarVeterinarios = new ArrayList<>(List.of(OpcionesEditarVeterinario.values()));
            Menu.menuOpcionesEditarVeterinario(opcionesEditarVeterinarios);

            try {
                opcion = Integer.valueOf(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar una opción numérica");
                continue;
            }

            if ( opcion < 1 || opcion > opcionesEditarVeterinarios.size()) {
                System.out.println("La opción ingresada es incorrecta. Por favor, vuelva a intentarlo ");
                continue;
            }

            opcionSeleccionada = opcionesEditarVeterinarios.get(opcion-1);

            switch (opcionSeleccionada){
                case EDITAR_NOMBRE -> {
                        char rta;
                        System.out.println("***** EDITAR NOMBRE VETERINARIO *****");
                        System.out.println("* Nombre actual: " + veterinario.getNombre());
                        String nombreAnterior = veterinario.getNombre();

                        try{
                            String nuevoNombreVeterinario;
                            boolean cancelarEdicion = false;

                            do{
                                rta = 'n';
                                nuevoNombreVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_NOMBRE);

                                if (nuevoNombreVeterinario.isEmpty()){
                                    cancelarEdicion = true;
                                    break;
                                }

                                if ( nuevoNombreVeterinario.equals(nombreAnterior)){
                                    System.out.println("El nombre ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                        cancelarEdicion = true;
                                }

                            }while( rta == 's');

                            if ( cancelarEdicion )
                                break;

                            System.out.println("¿Está seguro de cambiar el nombre de " + nombreAnterior + " por " + nuevoNombreVeterinario + "? (s/n)");
                            rta = solicitarRespuestaSiNo();

                            if (rta != 's')
                                break;

                            veterinario.setNombre(nuevoNombreVeterinario);
                            this.controladores.getControladorVeterinarios().actualizarVeterinario(veterinario);

                            System.out.println("Nombre actualizado correctamente");
                            mostrarVeterinario(veterinario);
                            continuar();

                        }catch (SQLException e ){
                            veterinario.setNombre(nombreAnterior);
                            logger.error("Error al intentar actualizar los datos del veterinario.", e);
                            System.out.println("Ocurrió un error al intentar actualizar el nombre del veterinario. Por favor, vuelva a intentarlo más tarde.");
                            continuar();
                        }

                }
                case EDITAR_APELLIDO -> {
                        char rta;
                        System.out.println("***** EDITAR APELLIDO VETERINARIO *****");
                        System.out.println("* Apellido actual: " + veterinario.getApellido());
                        String apellidoAnterior = veterinario.getApellido();

                        try{
                            String nuevoApellidoVeterinario;
                            boolean cancelarEdicion = false;

                            do{
                                rta = 'n';

                                nuevoApellidoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_APELLIDO);
                                if (nuevoApellidoVeterinario.isEmpty()){
                                    cancelarEdicion = true;
                                    break;
                                }

                                if ( nuevoApellidoVeterinario.equals(apellidoAnterior)){

                                    System.out.println("El apellido ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                        cancelarEdicion = true;

                                }

                            }while (rta == 's');

                            if (cancelarEdicion)
                                break;

                            System.out.println("¿Está seguro de cambiar el apellido de " + apellidoAnterior + " por " + nuevoApellidoVeterinario + "? (s/n)");
                            rta =solicitarRespuestaSiNo();

                            if ( rta != 's')
                                break;

                            veterinario.setApellido(nuevoApellidoVeterinario);
                            this.controladores.getControladorVeterinarios().actualizarVeterinario(veterinario);

                            System.out.println("Apellido actualizado correctamente");
                            mostrarVeterinario(veterinario);
                            continuar();
                        } catch (SQLException e){
                            veterinario.setApellido(apellidoAnterior);
                            logger.error("Error al intentar actualizar los datos del veterinario.", e);
                            System.out.println("Ocurrió un error al intentar actualizar el apellido del veterinario. Por favor, vuelva a intentarlo más tarde.");
                            continuar();

                        }
                }
                case EDITAR_DOCUMENTO -> {
                    System.out.println("***** EDITAR DOCUMENTO VETERINARIO *****");
                    System.out.println("* Documento actual: " + veterinario.getTipoDocumento().getCodigo() + " " + veterinario.getNumeroDocumento());

                    char rta;
                    TipoDocumento tipoDocumentoAnterior = veterinario.getTipoDocumento();
                    String numeroDocumentoAnterior = veterinario.getNumeroDocumento();

                    try{
                        TipoDocumento nuevoTipoDocumento = null;
                        String nuevoNumeroDocumento = null;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';

                            do {
                                String nuevoTipoDocumentoST = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);

                                if (nuevoTipoDocumentoST.isEmpty()) {
                                    cancelarEdicion = true;
                                    break;
                                }

                                nuevoTipoDocumento = TipoDocumento.obtenerTipoDocumento(nuevoTipoDocumentoST);

                                if (nuevoTipoDocumento == null) {
                                    System.out.println("Tipo de documento incorrecto");
                                }
                            }while ( nuevoTipoDocumento == null);

                            if (cancelarEdicion )
                                break;

                            nuevoNumeroDocumento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
                            if ( nuevoNumeroDocumento.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            Veterinario veterinarioEncontrado = this.controladores.getControladorVeterinarios().obtenerVeterinarioConDocumento(nuevoTipoDocumento,nuevoNumeroDocumento);

                            if ( veterinarioEncontrado != null ){
                                if ( veterinarioEncontrado.getIdVeterinario() == veterinario.getIdVeterinario()){
                                    System.out.println("El documento ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if (rta != 's')
                                        cancelarEdicion = true;
                                }else{
                                    System.out.println("El tipo y número de documento ingresado ya existe. ¿Desea intentar nuevamente? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                        cancelarEdicion = true;
                                }
                            }

                        }while ( rta == 's');

                        if (cancelarEdicion)
                            break;

                        System.out.println("¿Está seguro de cambiar el documento " + tipoDocumentoAnterior.getCodigo() + " " + numeroDocumentoAnterior + " por " + nuevoTipoDocumento.getCodigo() + " " + nuevoNumeroDocumento + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        veterinario.setTipoDocumento(nuevoTipoDocumento);
                        veterinario.setNumeroDocumento(nuevoNumeroDocumento);

                        this.controladores.getControladorVeterinarios().actualizarVeterinario(veterinario);

                        System.out.println("Documento actualizado correctamente");
                        mostrarVeterinario(veterinario);
                        continuar();
                    }catch (SQLException e){
                        veterinario.setTipoDocumento(tipoDocumentoAnterior);
                        veterinario.setNumeroDocumento(numeroDocumentoAnterior);
                        logger.error("Error al intentar actualizar los datos del veterinario.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el documento del veterinario. Por favor, vuelva a intentarlo más tarde.");
                        continuar();
                    }
                }
                case EDITAR_TELEFONO -> {
                    char rta;
                    System.out.println("***** EDITAR TELÉFONO VETERINARIO *****");
                    System.out.println("* Teléfono actual: " + veterinario.getTelefono());
                    String telefonoAnterior = veterinario.getTelefono();

                    try{
                        String nuevoTelefonoVeterinario;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';
                            nuevoTelefonoVeterinario = solicitarCampoObligatorio(CAMPO_TELEFONO);

                            if ( nuevoTelefonoVeterinario.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            if ( nuevoTelefonoVeterinario.equals(telefonoAnterior)){

                                System.out.println("El teléfono ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                rta = solicitarRespuestaSiNo();

                                if ( rta != 's')
                                    cancelarEdicion = true;
                            }

                        }while (rta == 's');

                        if (cancelarEdicion)
                            break;

                        System.out.println("¿Está seguro de cambiar el número de teléfono " + telefonoAnterior + " por " + nuevoTelefonoVeterinario + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        veterinario.setTelefono(nuevoTelefonoVeterinario);
                        this.controladores.getControladorVeterinarios().actualizarVeterinario(veterinario);

                        System.out.println("Número de teléfono actualizado correctamente");
                        mostrarVeterinario(veterinario);
                        continuar();
                    } catch (SQLException e){
                        veterinario.setTelefono(telefonoAnterior);
                        logger.error("Error al intentar actualizar los datos del veterinario.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el número de teléfono del veterinario. Por favor, vuelva a intentarlo más tarde.");
                        continuar();

                    }

                }
                case EDITAR_MATRICULA -> {
                    char rta;
                    System.out.println("***** EDITAR MATRÍCULA VETERINARIO *****");
                    System.out.println("* Matrícula actual: " + veterinario.getMatricula());
                    String matriculaAnterior = veterinario.getMatricula();

                    try {

                         String nuevaMatriculaVeterinario;
                         boolean cancelarEdicion = false;

                         do {
                             rta = 'n';

                             nuevaMatriculaVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_MATRICULA);
                             if (nuevaMatriculaVeterinario.isEmpty()){
                                cancelarEdicion = true;
                                break;
                             }

                             Veterinario veterinarioEncontrado = this.controladores.getControladorVeterinarios().obtenerVeterinarioConMatricula(nuevaMatriculaVeterinario);

                             if ( veterinarioEncontrado != null){
                                 if ( veterinarioEncontrado.getIdVeterinario() == veterinario.getIdVeterinario() ){

                                    System.out.println("La matrícula ingresada coincide con la actual. ¿Desea ingresar otra? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                       cancelarEdicion = true;

                                 }else{

                                    System.out.println("La matrícula ingresada ya existe. ¿Desea intentar nuevamente? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                        cancelarEdicion = true;
                                 }
                             }

                        }while ( rta == 's');

                        if ( cancelarEdicion )
                            break;

                        System.out.println("¿Está seguro de cambiar la matrícula " + matriculaAnterior + " por " + nuevaMatriculaVeterinario + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        veterinario.setMatricula(nuevaMatriculaVeterinario);
                        this.controladores.getControladorVeterinarios().actualizarVeterinario(veterinario);

                        System.out.println("Matricula actualizada correctamente");
                        mostrarVeterinario(veterinario);
                        continuar();

                    }catch (SQLException e ){
                        veterinario.setMatricula(matriculaAnterior);
                        logger.error("Error al intentar actualizar los datos del veterinario.", e);
                        System.out.println("Ocurrió un error al intentar actualizar la matricula del veterinario. Por favor, vuelva a intentarlo más tarde.");
                        continuar();
                    }

                }
            }


        }while ( opcionSeleccionada != OpcionesEditarVeterinario.VOLVER);

    }

    public void editarDuenio(){
        System.out.println("\n******POR FAVOR, SELECCIONE UN DUEÑO: ");

        Duenio duenio = seleccionarDuenio();

        if ( duenio == null)
            return;

        int opcion;
        OpcionesEditarDuenio opcionSeleccionada = null;

        do {
            List<OpcionesEditarDuenio> opcionesEditarDuenios = new ArrayList<>(List.of(OpcionesEditarDuenio.values()));
            Menu.menuOpcionesEditarDuenio(opcionesEditarDuenios);

            try {
                opcion = Integer.valueOf(scanner.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar una opción numérica");
                continue;
            }

            if ( opcion < 1 || opcion > opcionesEditarDuenios.size()) {
                System.out.println("La opción ingresada es incorrecta. Por favor, vuelva a intentarlo ");
                continue;
            }

            opcionSeleccionada = opcionesEditarDuenios.get(opcion-1);

            switch (opcionSeleccionada){
                case EDITAR_NOMBRE -> {
                    char rta;
                    System.out.println("***** EDITAR NOMBRE DUENIO *****");
                    System.out.println("* Nombre actual: " + duenio.getNombre());
                    String nombreAnterior = duenio.getNombre();

                    try{
                        String nuevoNombreDuenio;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';
                            nuevoNombreDuenio = solicitarCampoObligatorio(Aplicacion.CAMPO_NOMBRE);

                            if (nuevoNombreDuenio.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            if ( nuevoNombreDuenio.equals(nombreAnterior)){
                                System.out.println("El nombre ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                rta = solicitarRespuestaSiNo();

                                if ( rta != 's')
                                    cancelarEdicion = true;
                            }

                        }while( rta == 's');

                        if ( cancelarEdicion )
                            break;

                        System.out.println("¿Está seguro de cambiar el nombre de " + nombreAnterior + " por " + nuevoNombreDuenio + "? (s/n)");
                        rta = solicitarRespuestaSiNo();

                        if (rta != 's')
                            break;

                        duenio.setNombre(nuevoNombreDuenio);
                        this.controladores.getControladorDuenios().actualizarDuenio(duenio);

                        System.out.println("Nombre actualizado correctamente");
                        mostrarDuenio(duenio);
                        continuar();

                    }catch (SQLException e ){
                        duenio.setNombre(nombreAnterior);
                        logger.error("Error al intentar actualizar los datos del dueño.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el nombre del dueño. Por favor, vuelva a intentarlo más tarde.");
                        continuar();
                    }

                }
                case EDITAR_APELLIDO -> {
                    char rta;
                    System.out.println("***** EDITAR APELLIDO DUEÑO *****");
                    System.out.println("* Apellido actual: " + duenio.getApellido());
                    String apellidoAnterior = duenio.getApellido();

                    try{
                        String nuevoApellidoDuenio;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';

                            nuevoApellidoDuenio = solicitarCampoObligatorio(Aplicacion.CAMPO_APELLIDO);
                            if (nuevoApellidoDuenio.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            if ( nuevoApellidoDuenio.equals(apellidoAnterior)){

                                System.out.println("El apellido ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                rta = solicitarRespuestaSiNo();

                                if ( rta != 's')
                                    cancelarEdicion = true;

                            }

                        }while (rta == 's');

                        if (cancelarEdicion)
                            break;

                        System.out.println("¿Está seguro de cambiar el apellido de " + apellidoAnterior + " por " + nuevoApellidoDuenio + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        duenio.setApellido(nuevoApellidoDuenio);
                        this.controladores.getControladorDuenios().actualizarDuenio(duenio);

                        System.out.println("Apellido actualizado correctamente");
                        mostrarDuenio(duenio);
                        continuar();
                    } catch (SQLException e){
                        duenio.setApellido(apellidoAnterior);
                        logger.error("Error al intentar actualizar los datos del dueño.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el apellido del dueño. Por favor, vuelva a intentarlo más tarde.");
                        continuar();

                    }
                }
                case EDITAR_DOCUMENTO -> {
                    System.out.println("***** EDITAR DOCUMENTO DUEÑO *****");
                    System.out.println("* Documento actual: " + duenio.getTipoDocumento().getCodigo() + " " + duenio.getNumeroDocumento());

                    char rta;
                    TipoDocumento tipoDocumentoAnterior = duenio.getTipoDocumento();
                    String numeroDocumentoAnterior = duenio.getNumeroDocumento();

                    try{
                        TipoDocumento nuevoTipoDocumento = null;
                        String nuevoNumeroDocumento = null;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';

                            do {
                                String nuevoTipoDocumentoST = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);

                                if (nuevoTipoDocumentoST.isEmpty()) {
                                    cancelarEdicion = true;
                                    break;
                                }

                                nuevoTipoDocumento = TipoDocumento.obtenerTipoDocumento(nuevoTipoDocumentoST);

                                if (nuevoTipoDocumento == null) {
                                    System.out.println("Tipo de documento incorrecto");
                                }
                            }while ( nuevoTipoDocumento == null);

                            if (cancelarEdicion )
                                break;

                            nuevoNumeroDocumento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
                            if ( nuevoNumeroDocumento.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            Duenio duenioEncontrado = this.controladores.getControladorDuenios().obtenerDuenioPorDocumento(nuevoTipoDocumento,nuevoNumeroDocumento);

                            if ( duenioEncontrado != null ){
                                if ( duenioEncontrado.getIdDuenio() == duenio.getIdDuenio()){
                                    System.out.println("El documento ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if (rta != 's')
                                        cancelarEdicion = true;
                                }else{
                                    System.out.println("El tipo y número de documento ingresado ya existe. ¿Desea intentar nuevamente? (s/n)");
                                    rta = solicitarRespuestaSiNo();

                                    if ( rta != 's')
                                        cancelarEdicion = true;
                                }
                            }

                        }while ( rta == 's');

                        if (cancelarEdicion)
                            break;

                        System.out.println("¿Está seguro de cambiar el documento " + tipoDocumentoAnterior.getCodigo() + " " + numeroDocumentoAnterior + " por " + nuevoTipoDocumento.getCodigo() + " " + nuevoNumeroDocumento + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        duenio.setTipoDocumento(nuevoTipoDocumento);
                        duenio.setNumeroDocumento(nuevoNumeroDocumento);

                        this.controladores.getControladorDuenios().actualizarDuenio(duenio);

                        System.out.println("Documento actualizado correctamente");
                        mostrarDuenio(duenio);
                        continuar();
                    }catch (SQLException e){
                        duenio.setTipoDocumento(tipoDocumentoAnterior);
                        duenio.setNumeroDocumento(numeroDocumentoAnterior);
                        logger.error("Error al intentar actualizar los datos del dueño.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el documento del dueño. Por favor, vuelva a intentarlo más tarde.");
                        continuar();
                    }
                }
                case EDITAR_TELEFONO -> {
                    char rta;
                    System.out.println("***** EDITAR TELÉFONO DUENIO *****");
                    System.out.println("* Teléfono actual: " + duenio.getTelefono());
                    String telefonoAnterior = duenio.getTelefono();

                    try{
                        String nuevoTelefonoDuenio;
                        boolean cancelarEdicion = false;

                        do{
                            rta = 'n';
                            nuevoTelefonoDuenio = solicitarCampoObligatorio(CAMPO_TELEFONO);

                            if ( nuevoTelefonoDuenio.isEmpty()){
                                cancelarEdicion = true;
                                break;
                            }

                            if ( nuevoTelefonoDuenio.equals(telefonoAnterior)){

                                System.out.println("El teléfono ingresado coincide con el actual. ¿Desea ingresar otro? (s/n)");
                                rta = solicitarRespuestaSiNo();

                                if ( rta != 's')
                                    cancelarEdicion = true;
                            }

                        }while (rta == 's');

                        if (cancelarEdicion)
                            break;

                        System.out.println("¿Está seguro de cambiar el número de teléfono " + telefonoAnterior + " por " + nuevoTelefonoDuenio + "? (s/n)");
                        rta =solicitarRespuestaSiNo();

                        if ( rta != 's')
                            break;

                        duenio.setTelefono(nuevoTelefonoDuenio);
                        this.controladores.getControladorDuenios().actualizarDuenio(duenio);

                        System.out.println("Número de teléfono actualizado correctamente");
                        mostrarDuenio(duenio);
                        continuar();
                    } catch (SQLException e){
                        duenio.setTelefono(telefonoAnterior);
                        logger.error("Error al intentar actualizar los datos del dueño.", e);
                        System.out.println("Ocurrió un error al intentar actualizar el número de teléfono del dueño. Por favor, vuelva a intentarlo más tarde.");
                        continuar();

                    }

                }

            }

        }while ( opcionSeleccionada != OpcionesEditarDuenio.VOLVER);

    }

    private void continuar(){
        System.out.println("Presione ENTER para continuar...");
        this.scanner.nextLine();
    }

    /* ----------------------- ------------------------------*/
    //              OPCIONES DE MENU
    /* ----------------------- -------------------------------*/

    private void registrarVeterinario() {
         char rta;

         do{
             String numeroDocumentoVeterinario;
             String matriculaVeterinario;

             TipoDocumento tipoDocumentoVeterinario = null;
             do {
                 String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);
                 if (tipoSt.isEmpty())
                     return;

                 tipoDocumentoVeterinario = TipoDocumento.obtenerTipoDocumento(tipoSt);
                 if (tipoDocumentoVeterinario == null)
                     System.out.println("Tipo de documento incorrecto");

             }while ( tipoDocumentoVeterinario == null);


             do {

                 numeroDocumentoVeterinario = solicitarCampoObligatorio(Aplicacion.CAMPO_DOCUMENTO);
                 if (numeroDocumentoVeterinario.isEmpty())
                     return;

                 try {
                     if (!this.controladores.getControladorVeterinarios().existeVeterinarioConDocumento(tipoDocumentoVeterinario, numeroDocumentoVeterinario))
                         break;
                 }catch (SQLException e ){
                     logger.error("Error al verificar si existe un veterinario con el documento indicado",e);
                     System.out.println("Ocurrió un inconveniente durante el proceso de registro del veterinario. Por favor, vuelva a intentarlo más tarde.");
                     continuar();
                     return;
                 }

                 System.out.println("Ya existe un veterinario con el documento " + tipoDocumentoVeterinario.getCodigo() + " " + numeroDocumentoVeterinario);
                 System.out.println("¿Desea volver a intentar? (s/n)");
                 rta = solicitarRespuestaSiNo();

                 if (rta != 's')
                     return;

             } while ( true);

             do{
                 matriculaVeterinario = solicitarCampoObligatorio(CAMPO_MATRICULA);
                 if (matriculaVeterinario.isEmpty())
                     return;

                 try {
                     if (!this.controladores.getControladorVeterinarios().existeVeterinarioConMatricula(matriculaVeterinario))
                         break;
                 }catch (SQLException e){
                     logger.error("Error al verificar si existe un veterinario con la matricula indicada",e);
                     System.out.println("Ocurrió un inconveniente durante el proceso de registro del veterinario. Por favor, vuelva a intentarlo más tarde.");
                     continuar();
                     return;
                 }

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

             try {
                 this.controladores.getControladorVeterinarios().registrarVeterinario(nombreVeterinario, apellidoVeterinario, tipoDocumentoVeterinario, numeroDocumentoVeterinario, telefonoVeterinario, matriculaVeterinario);
             }catch (SQLException e){
                 logger.error("Error al intentar registrar al veterinario",e);
                 System.out.println("Ocurrió un inconveniente durante el proceso de registro del veterinario. Por favor, vuelva a intentarlo más tarde.");
                 continuar();
                 return;
             }

             System.out.println("¿Ingresar otro veterinario? s/n");
             rta = solicitarRespuestaSiNo();

         }while ( rta == 's');
    }

    private void registrarDuenio(){

        char rta;
        do{

            String numeroDocumentoDuenio;
            TipoDocumento tipoDocumentoDuenio = null;

            do {
                String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);
                if (tipoSt.isEmpty())
                    return;

                tipoDocumentoDuenio = TipoDocumento.obtenerTipoDocumento(tipoSt);

                if (tipoDocumentoDuenio == null)
                    System.out.println("Tipo de documento incorrecto");

            }while ( tipoDocumentoDuenio == null);

            do{
                numeroDocumentoDuenio = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
                if (numeroDocumentoDuenio.isEmpty())
                    return;

                try {
                    if (!this.controladores.getControladorDuenios().existeDuenioConDocumento(tipoDocumentoDuenio, numeroDocumentoDuenio))
                        break;
                }catch (SQLException e ){
                    logger.error("Error al verificar si existe un dueño con el documento indicado", e);
                    System.out.println("Ocurrió un inconveniente durante el proceso de registro del dueño. Por favor, vuelva a intentarlo más tarde.");
                    continuar();
                    return;
                }
                System.out.println("Ya existe un duenio con el documento " + tipoDocumentoDuenio.getCodigo() + " " + numeroDocumentoDuenio);
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

            try {
                this.controladores.getControladorDuenios().registrarDuenio(nombreDuenio, apellidoDuenio, tipoDocumentoDuenio, numeroDocumentoDuenio, telefonoDuenio);
            }catch (SQLException e ){
                logger.error("Error al intentar registrar al dueño", e);
                System.out.println("Ocurrió un inconveniente durante el proceso de registro del dueño. Por favor, vuelva a intentarlo más tarde.");
                continuar();
                return;
            }

            System.out.println("¿Ingresar otro duenio? s/n");
            rta = solicitarRespuestaSiNo();

        }while (rta == 's');

    }

    private Duenio registrarDuenio(TipoDocumento tipoDocumentoDuenio,String numeroDocumentoDuenio){

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

        try {
            return this.controladores.getControladorDuenios().registrarDuenio(nombreDuenio, apellidoDuenio, tipoDocumentoDuenio, numeroDocumentoDuenio, telefonoDuenio);
        }catch (SQLException e ){
            logger.error("Error al intentar registrar al dueño", e);
            System.out.println("Ocurrió un inconveniente durante el proceso de registro del dueño. Por favor, vuelva a intentarlo más tarde.");
            continuar();
            return null;
        }
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

        try{
            List<Veterinario> veterinarios = this.controladores.getControladorVeterinarios().obtenerVeterinarios();

            if ( veterinarios.isEmpty() ){
                System.out.println("No hay veterinarios registrados");
                return;
            }

            for ( Veterinario veterinario : veterinarios ){
                mostrarVeterinario(veterinario);
                System.out.println("======================");
            }
        }catch (SQLException e){
            logger.error("Error al intentar obtener los veterinarios", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los veterinarios.");
        }
    }

    private void mostrarDuenios(){

        try{
            List<Duenio> duenios = this.controladores.getControladorDuenios().obtenerDuenios();

            if ( duenios.isEmpty() ){
                System.out.println("No hay dueños registrados");
                return;
            }

            for ( Duenio duenio : duenios ){
                mostrarDuenio(duenio);
                System.out.println("=====================================");
            }

        }catch (SQLException e ){
            logger.error("Error al intentar obtener los dueños", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los dueños.");
        }
    }

    private void mostrarMascotas(){

        TipoDocumento tipoDocumentoDuenio = null;
        do {
            String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);
            if (tipoSt.isEmpty())
                return;

            try {
                tipoDocumentoDuenio = TipoDocumento.valueOf(tipoSt.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de documento incorrecto");
                tipoDocumentoDuenio = null;
            }
        }while ( tipoDocumentoDuenio == null);


        String numeroDocumento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);
        if (numeroDocumento.isEmpty())
            return;

        Duenio duenio;
        try {
             duenio = this.controladores.getControladorDuenios().obtenerDuenioPorDocumento(tipoDocumentoDuenio, numeroDocumento);
        }catch (SQLException e){
            logger.error("Error al intentar obtener el dueño por documento", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los datos del dueño.");
            continuar();
            return;
        }

        if ( duenio != null ){

            try {
                if (this.controladores.getControladorDuenios().tieneMascotas(duenio.getIdDuenio())) {
                    mostrarMascotasDelDuenio(duenio);
                } else {
                    System.out.println("No hay mascotas registradas para este dueño.");
                    System.out.println("¿Desea dar de alta mascotas? (s/n)");

                    char rta = solicitarRespuestaSiNo();

                    if (rta != 's')
                        return;

                    registrarMascotasDelDuenio(duenio);
                    mostrarMascotasDelDuenio(duenio);

                }
            }catch( SQLException e ){
                logger.error("Error al verificar si el dueño tiene mascotas registradas", e);
                System.out.println("Ocurrió un inconveniente al intentar obtener las mascotas del dueño.");
                continuar();
                return;
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
            try {
                if (!this.controladores.getControladorDuenios().tieneMascotas(duenio.getIdDuenio())) {
                    System.out.println(duenio.getNombre() + " " + duenio.getApellido() + " no tiene mascotas registradas");
                    System.out.println("¿Desea registrar mascotas? (s/n)");
                    char rta = solicitarRespuestaSiNo();

                    if (rta != 's')
                        return;

                    registrarMascotasDelDuenio(duenio);
                }
            }catch (SQLException e ){
                logger.error("Error al verificar si el dueño tiene mascotas registradas", e);
                System.out.println("Ocurrió un inconveniente al intentar obtener las mascotas del dueño.");
                continuar();
                return;
            }

            Mascota mascota = seleccionarMascota(duenio);

            if (mascota!=null) {
                registrarConsulta(mascota,veterinario);
                continuar();
            }
        }
    }

    private void consultarHistoriaClinica(){
        TipoDocumento tipoDocumento = null;
        do {
            String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);
            if (tipoSt.isEmpty())
                return;

            try {
                tipoDocumento = TipoDocumento.valueOf(tipoSt.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de documento incorrecto");
                tipoDocumento = null;
            }
        }while ( tipoDocumento == null);

        String documento = solicitarCampoObligatorio(CAMPO_DOCUMENTO);

        if ( documento.isEmpty()){
            return;
        }

        Duenio duenio;
        try {
             duenio = this.controladores.getControladorDuenios().obtenerDuenioPorDocumento(tipoDocumento, documento);
        }catch (SQLException e ){
            logger.error("Error al intentar obtener el dueño por documento", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los datos del dueño.");
            continuar();
            return;
        }

        if ( duenio == null){
            System.out.println("No se ha encontrado dueño con el documento especificado");
            continuar();
            return;
        }

        try {
            if (!this.controladores.getControladorDuenios().tieneMascotas(duenio.getIdDuenio())) {
                System.out.println("No cuenta con mascotas registradas");
                continuar();
                return;
            }
        }catch (SQLException e){
            logger.error("Error al verificar si el dueño tiene mascotas registradas", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener las mascotas del dueño.");
            continuar();
            return;
        }

        Mascota mascota = seleccionarMascota(duenio);

        if (mascota == null) {
            System.out.println("No se ha seleccionado ninguna mascota");
            continuar();
            return;
        }

        HistoriaClinica historiaClinica;
        try {
            historiaClinica = this.controladores.getControladorHistoriasClinicas().obtenerHistoriaClinica(mascota.getIdMascota());
        }catch (SQLException e){
            logger.error("Error al obtener la historia clínica", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener la historia clínica.");
            continuar();
            return;
        }

        System.out.println("Historia clínica de: " + mascota.getNombre());
        System.out.println("Fecha creación: " + formatearFechaHora(historiaClinica.getFechaCreacion()));
        System.out.println("Ultima actualización: " + formatearFechaHora(historiaClinica.getFechaActualizacion()));

        if ( historiaClinica.obtenerConsultas().isEmpty()){
            System.out.println("No hay consultas");
            continuar();
            return;
        }

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

            System.out.println("** Detalle de la consulta:");
            mostrarDetalleConsulta(consultas.get(opcion-1));

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

        TipoDocumento tipoDocumentoDuenio;
        do {
            String tipoSt = solicitarCampoObligatorio(CAMPO_TIPO_DOCUMENTO);
            if (tipoSt.isEmpty())
                return null;

            tipoDocumentoDuenio = TipoDocumento.obtenerTipoDocumento(tipoSt);

            if ( tipoDocumentoDuenio == null)
                System.out.println("Tipo de documento incorrecto");

        }while ( tipoDocumentoDuenio == null);


        String documentoDuenio = solicitarCampoObligatorio(Aplicacion.CAMPO_DOCUMENTO);

        if (documentoDuenio.isEmpty())
            return null;

        Duenio duenio;
        try {
            duenio = this.controladores.getControladorDuenios().obtenerDuenioPorDocumento(tipoDocumentoDuenio, documentoDuenio);
        }catch (SQLException e ){
            logger.error("Error al intentar obtener el dueño por documento", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los datos del dueño.");
            continuar();
            return null;
        }

        if ( duenio != null)
            return duenio;

        System.out.println("No se ha encontrado el dueño con documento: " + tipoDocumentoDuenio.getCodigo() + " " + documentoDuenio);
        System.out.println("¿Desea darlo de alta? s/n");

        rta = solicitarRespuestaSiNo();

        if (rta != 's')
            return null;

        return registrarDuenio(tipoDocumentoDuenio,documentoDuenio);
    }

    private Mascota seleccionarMascota(Duenio duenio){

        try {
            List<Mascota> mascotas = this.controladores.getControladorMascotas().obtenerMascotasDeUnDuenio(duenio.getIdDuenio());
            int totalMascotas = mascotas.size();

            int i = 1;
            for (Mascota mascota : mascotas) {
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
        }catch (SQLException e){
            logger.error("Error al intentar obtener las mascotas del dueño", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener las mascotas del dueño.");
            continuar();
            return null;
        }
    }

    private Veterinario seleccionarVeterinario(){

        try {
            List<Veterinario> veterinarios = this.controladores.getControladorVeterinarios().obtenerVeterinarios();
            int i = 1;

            if (veterinarios.isEmpty()) {
                System.out.println("No hay veterinarios registrados.");
                continuar();
                return null;
            }

            for(Veterinario veterinario : veterinarios){
                System.out.println(i + " - " + veterinario.getNombre() + " " + veterinario.getApellido() + " - Matricula: " + veterinario.getMatricula());
                i++;
            }

            int opcion = -1;
            boolean opcionInvalida;
            int totalVeterinarios = veterinarios.size();
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

            return veterinarios.get(opcion-1);

        }catch (SQLException e){
            logger.error("Error al intentar obtener los veterinarios", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los veterinarios.");
            continuar();
            return null;
        }

    }

    private Duenio seleccionarDuenio(){

        try {
            List<Duenio> duenios = this.controladores.getControladorDuenios().obtenerDuenios();
            int i = 1;

            if (duenios.isEmpty()) {
                System.out.println("No hay dueños registrados.");
                continuar();
                return null;
            }

            for(Duenio duenio : duenios){
                System.out.println(i + " - " + duenio.getNombre() + " " + duenio.getApellido() );
                i++;
            }

            int opcion = -1;
            boolean opcionInvalida;
            int totalDuenios = duenios.size();
            do{
                System.out.println("Por favor, elija un dueño: ");
                try{
                    opcion = Integer.valueOf(this.scanner.nextLine().trim());
                }catch ( NumberFormatException e ){
                    opcion  = -1;
                }

                opcionInvalida = opcion < 1 || opcion > totalDuenios;

                if (opcionInvalida) {
                    System.out.println("La opción ingresada es inválida. ¿Desea volver a intentar? (s/n)");

                    if (solicitarRespuestaSiNo() != 's')
                        return null;
                }

            }while ( opcionInvalida);

            return duenios.get(opcion-1);

        }catch (SQLException e){
            logger.error("Error al intentar obtener los dueños", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener los dueños.");
            continuar();
            return null;
        }

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


        TipoMascota tipo;
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

        }while ( peso <= 0 || !Double.isInfinite(peso));

        try {
             this.controladores.getControladorMascotas().registrarMascota(nombre, tipo, raza, fechaNacimiento, peso, duenio.getIdDuenio());
        }catch (SQLException e){
            logger.error("Error al intentar registrar la mascota", e);
            System.out.println("Ocurrió un inconveniente durante el registro de la mascota. Por favor, vuelva a intentarlo más tarde.");
            continuar();
            return false;
        }
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

        try {
            int idHistoriaClinica = this.controladores.getControladorHistoriasClinicas().obtenerIdHistoriaClinica(mascota.getIdMascota());

            if ( idHistoriaClinica == -1 ) {
                System.out.println("No se encontró la historia clínica de la mascota.");
                return;
            }
            this.controladores.getControladorConsultas().registrarConsulta(motivo, diagnostico, tratamiento, observaciones, veterinario, idHistoriaClinica);
        }catch (SQLException e){
            logger.error("Error al intentar registrar la consulta", e);
            System.out.println("Ocurrió un inconveniente durante el registro de la consulta. Por favor, vuelva a intentarlo más tarde.");
        }
    }


    //---- SALIDA POR PANTALLA
    private void mostrarVeterinario(Veterinario veterinario){
        System.out.println("Nombre: " + veterinario.getNombre());
        System.out.println("Apellido: " + veterinario.getApellido());
        System.out.println("Tipo documento: " + veterinario.getTipoDocumento().getCodigo() + " - Número: " + veterinario.getNumeroDocumento());
        System.out.println("Matricula: " + veterinario.getMatricula());
        System.out.println("Teléfono: " + veterinario.getTelefono());
        System.out.println("Fecha alta: " + formatearFechaHora(veterinario.getFechaAlta()));
    }

    private void mostrarDuenio(Duenio duenio){
        System.out.println("Nombre: " + duenio.getNombre());
        System.out.println("Apellido: " + duenio.getApellido());
        System.out.println("Tipo documento: " + duenio.getTipoDocumento().getCodigo() + " - Número: " + duenio.getNumeroDocumento());
        System.out.println("Teléfono: " + duenio.getTelefono());
        System.out.println("Fecha alta: " + formatearFechaHora(duenio.getFechaAlta()));
    }

    private void mostrarMascotasDelDuenio(Duenio duenio) {
        try {
            List<Mascota> mascotas = this.controladores.getControladorMascotas().obtenerMascotasDeUnDuenio(duenio.getIdDuenio());

            if ( mascotas.isEmpty()){
                return;
            }

            for (Mascota mascota : mascotas) {
                mostrarMascota(mascota);
            }
        }catch (SQLException e){
            logger.error("Error al intentar obtener las mascotas del dueño", e);
            System.out.println("Ocurrió un inconveniente al intentar obtener las mascotas del dueño.");
        }
    }

    private void mostrarMascota(Mascota mascota) {
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Tipo: " + mascota.getTipo());
        System.out.println("Raza: " + mascota.getRaza());
        System.out.println("Fecha de nacimiento: " + formatearFecha(mascota.getFechaNacimiento()));
        System.out.println("Peso: " + mascota.getPeso());
        System.out.println("Fecha alta: " + formatearFechaHora(mascota.getFechaAlta()));
        System.out.println("==============================================");
    }

    private void mostrarDetalleConsulta(Consulta consulta) {

        System.out.println("* Fecha de la consulta: " + formatearFechaHora(consulta.getFecha()));
        System.out.println("* Motivo: " + consulta.getMotivo());
        System.out.println("* Diagnóstico: " + consulta.getDiagnostico());
        System.out.println("* Tratamiento: " + consulta.getTratamiento());
        System.out.println("* Observaciones: " + consulta.getObservaciones());
        System.out.println("* Atendido por: " + consulta.getVeterinario().getNombre() + " " + consulta.getVeterinario().getApellido());

    }

    private String formatearFechaHora(LocalDateTime fechaHora ){
        return fechaHora.format(FORMATO_FECHA_HORA);
    }
    private String formatearFecha(LocalDate fecha) {
        return fecha.format(FORMATO_FECHA);
    }

}



