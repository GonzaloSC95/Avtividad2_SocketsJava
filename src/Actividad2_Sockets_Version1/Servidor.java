package Actividad2_Sockets_Version1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Servidor {

    //ATRIBUTOS DE CONEXION
    public static final int PUERTO = 2018;
    //ATRIBUTO BIBLIOTECA
    public static LinkedList<Libro> bibliotecaVirtual = new LinkedList<>();

    public Servidor() {
        //LIBROS YA DISPONIBLES EN LA BIBLIOTECA
        Libro l1 = new Libro("0764526413", "Harry Potter y el Caliz de Fuego", "JK Rowling", "35,58");
        Libro l2 = new Libro("1719520014", "SpiderMan vs Venom", "Stan Lee", "40,18");
        Libro l3 = new Libro("0684212345", "Hulk el mostruoso", "Stan Lee", "10,15");
        Libro l4 = new Libro("0111120126", "El Quijote", "Miguel de Cervantes", "9,10");
        Libro l5 = new Libro("0348956880", "El Primer Vengador", "Stan Lee", "45,14");
        bibliotecaVirtual.add(l1);
        bibliotecaVirtual.add(l2);
        bibliotecaVirtual.add(l3);
        bibliotecaVirtual.add(l4);
        bibliotecaVirtual.add(l5);
    }

    public static void main(String[] args) {
        System.out.println("-------------------");
        System.out.println(ConsolaCOLOR.CYAN_BACKGROUND
                + "     SERVIDOR    "
                + ConsolaCOLOR.BLACK_BACKGROUND);
        System.out.println("-------------------");
        //----------------------------------------------
        Socket socketAlCliente;
        int peticion = 1;
        Servidor x = new Servidor();
        //-------------------------------------------------------
        InetSocketAddress direccion = new InetSocketAddress(PUERTO);
        //---------------------------------------------------------
        try (ServerSocket servidor = new ServerSocket()) {
            servidor.bind(direccion);
            //-----------------------------------------
            while (true) {
                //----------------------------------------------------------
                System.out.println(ConsolaCOLOR.WHITE_BACKGROUND + "LIBROS DISPONIBLES EN LA BIBLIOTECA VIRTUAL: " 
                        + bibliotecaVirtual.size() + ConsolaCOLOR.BLACK_BACKGROUND);
                //----------------------------------------------------------
                socketAlCliente = servidor.accept();
                System.out.println("");
                System.out.println(ConsolaCOLOR.letraMorado + "SERVIDOR: PETICIÓN Nº " + (peticion++)+" RECIBIDA" + ConsolaCOLOR.letraNegro+"\n");
                new HiloCliente(socketAlCliente, x);

            }

        } catch (UnknownHostException e) {
            System.out.println("SERVIDOR: El cliente " + peticion + ", se ha desconectado.");
        } catch (IOException e) {
            System.out.println("SERVIDOR: El cliente " + peticion + ", se ha desconectado.");
        } catch (Exception e) {
            System.out.println("SERVIDOR: El cliente " + peticion + ", se ha desconectado.");
        }
    }

}
