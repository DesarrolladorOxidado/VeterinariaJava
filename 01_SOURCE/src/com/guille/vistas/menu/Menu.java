package com.guille.vistas.menu;

import java.util.List;

public class Menu {

    private Menu(){

    }

    public static void opcionesMenuPrincipal(List<OpcionMenuPrincipal> menuPrincipal){
        System.out.println("===============================");
        System.out.println("     SISTEMA VETERINARIA        ");
        System.out.println("================================");
        System.out.println("POR FAVOR, ELIJA UNA OPCIÓN:");

        mostrarOpciones(menuPrincipal);

    }

    public static void opcionesMenuEdicion(List<OpcionesMenuEdicion> menuEdicion){
        System.out.println("--------------------------------");
        System.out.println("     EDITAR DATOS        ");
        System.out.println("--------------------------------");
        System.out.println("POR FAVOR, ELIJA UNA OPCIÓN:");

        mostrarOpciones(menuEdicion);

    }

    public static void menuOpcionesEditarVeterinario(List<OpcionesEditarVeterinario> opcionesEditarVeterinarios){
        System.out.println("--------------------------------");
        System.out.println("     EDITAR DATOS VETERINARIO        ");
        System.out.println("--------------------------------");
        System.out.println("POR FAVOR, ELIJA UNA OPCIÓN:");

        mostrarOpciones(opcionesEditarVeterinarios);
    }

    private static void mostrarOpciones(List<? extends OpcionesMenu> opcionesMenus){
        int numeroOpcion = 1;
        for( OpcionesMenu opcionMenu : opcionesMenus){
            System.out.println( numeroOpcion + " - " + opcionMenu.getDescripcion());
            numeroOpcion++;
        }
    }

}
