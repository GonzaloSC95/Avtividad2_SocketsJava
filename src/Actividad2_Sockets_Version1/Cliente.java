package Actividad2_Sockets_Version1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    //ATRIBUTOS DE CONEXION
    public static final int PUERTO = 2018;
    public static final String IP = "localhost";
    //---------------------------------------
    private static String USER;

    //--------------------------------------
    public static void main(String[] args) {
        System.out.println("-------------------");
        System.out.println(ConsolaCOLOR.YELLOW_BACKGROUND
                + "    CLIENTE    "
                + ConsolaCOLOR.BLACK_BACKGROUND);
        System.out.println("-------------------");
        //--------------------------------------------
        InetSocketAddress direccion = new InetSocketAddress(IP, PUERTO);
        //---------------------------------------------------
        String envioFinal, opcionMenu, stringRecibido;
        ///-------------------------------
        String isbn, titulo, autor;
        //------------------------------------------
        DataInputStream recibir = null;
        DataOutputStream envio = null;
        //---------------SOCKET---------------------------
        try (Socket socketAlservidor = new Socket();
                BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));) {
            //------------CONEXION-----------------------
            System.out.println(ConsolaCOLOR.letraVerde + "ESTABELECIENDO CONEXIÓN CON EL SERVIDOR......." + ConsolaCOLOR.letraNegro);
            socketAlservidor.connect(direccion);
            System.out.println(ConsolaCOLOR.letraVerde + "CONEXIÓN ESTABLECIDA POR EL PUERTO: " + ConsolaCOLOR.letraNegro + PUERTO
                    + ConsolaCOLOR.letraVerde + " .DIRECCIÓN: " + ConsolaCOLOR.letraNegro + IP + ".");
            //------------SOLICITUD NOMBRE DE USUARIO--------------------------------
            System.out.print(ConsolaCOLOR.YELLOW_BACKGROUND
                    + "INTRODUCE TU NOMBRE DE USUARIO:"
                    + ConsolaCOLOR.BLACK_BACKGROUND + "\t");
            //USER = tc.readLine().toUpperCase();
            //----------------------------
            while ((USER = tc.readLine().toUpperCase()).isEmpty()) {                
                System.out.print("\n"+ConsolaCOLOR.letraRojo
                    + "DEBES INTRODUCIR UN NOMBRE DE USUARIO:"
                    + ConsolaCOLOR.letraNegro + "\t");
            }
            //-----------------------------
            do {
                //----------------SE MUESTRA EL MENU Y ELEGIMOS UNA DE LAS OPCIONES-----------------------------------
                System.out.println("\n" + ConsolaCOLOR.letraAzul + "¿Que deseas hacer?" + ConsolaCOLOR.letraNegro + "\n"
                        + "1.Consultar libro por ISBN.\n"
                        + "2.Consultar libro por titulo.\n"
                        + "3.Consultar libro por autor.\n"
                        + "4.Añadir libro.\n"
                        + "5.Salir de la aplicación.");
                System.out.print(ConsolaCOLOR.letraAzul + "Introduce una opción del menu:\t" + ConsolaCOLOR.letraNegro);
                opcionMenu = tc.readLine();

                ///////////////////////////////////////////////////////////////////////////////////////////////////
                //-------------------------ACCIONES EN FUNCION DE LA OPCION ELEGIDA-------------------------------
                if (opcionMenu.equalsIgnoreCase("1")) {
                    //---------------1.CONSULTAR LIBRO POR ISBN.------------------------------------------
                    System.out.print("\n" + ConsolaCOLOR.letraMorado
                            + "Introduce el isbn del libro que deseas consultar:"
                            + ConsolaCOLOR.letraNegro + "\t");
                    //---------------------------------------
                    //isbn = tc.readLine();
                    if ((isbn = tc.readLine()).isEmpty()) {
                        isbn=".";
                    }
                    //-------------------------------------
                    envioFinal = USER + "-" + 1 + "-" + isbn;
                    //----------------------------
                    envio = new DataOutputStream(socketAlservidor.getOutputStream());
                    envio.writeUTF(envioFinal);
                    //-----------------------------
                    recibir = new DataInputStream(socketAlservidor.getInputStream());
                    //-------------------------------------
                    stringRecibido = recibir.readUTF();
                    String[] resultadoFinal = stringRecibido.split("\\*");
                    int contador = 1;
                    //---------------------------------------------------
                    if (stringRecibido.isEmpty()) {
                       System.out.println("\n" + ConsolaCOLOR.letraRojo 
                                +"SERVIDOR: NO TENEMOS ESE LIBRO DISPONIBLE :("
                                + ConsolaCOLOR.letraNegro);
                    } else {
                        System.out.println("\n" + ConsolaCOLOR.WHITE_BACKGROUND + "RESULTADO DE TU BUSQUEDA: ");
                        for (int i = 0; i < resultadoFinal.length; i++) {
                            System.out.println("Libro " + (contador++) + ": " + resultadoFinal[i]);
                        }
                        System.out.println(ConsolaCOLOR.BLACK_BACKGROUND);
                    }
                } else if (opcionMenu.equalsIgnoreCase("2")) {
                    //---------------2.CONSULTAR LIBRO POR TITULO.------------------------------------------
                    System.out.print("\n" + ConsolaCOLOR.letraMorado
                            + "Introduce el título del libro que deseas consultar:"
                            + ConsolaCOLOR.letraNegro + "\t");
                    //titulo = tc.readLine();
                    if ((titulo = tc.readLine()).isEmpty()) {
                        titulo=".";
                    }
                    envioFinal = USER + "-2-" + titulo;
                    //----------------------------
                    envio = new DataOutputStream(socketAlservidor.getOutputStream());
                    envio.writeUTF(envioFinal);
                    //-----------------------------
                    recibir = new DataInputStream(socketAlservidor.getInputStream());
                    stringRecibido = recibir.readUTF();
                    String[] resultadoFinal = stringRecibido.split("\\*");
                    int contador = 1;
                    if (stringRecibido.isEmpty()) {
                       System.out.println("\n" + ConsolaCOLOR.letraRojo 
                                +"SERVIDOR: NO TENEMOS ESE LIBRO DISPONIBLE :("
                                + ConsolaCOLOR.letraNegro);
                    } else {
                        System.out.println("\n" + ConsolaCOLOR.WHITE_BACKGROUND + "RESULTADO DE TU BUSQUEDA: ");
                        for (int i = 0; i < resultadoFinal.length; i++) {
                            System.out.println("Libro " + (contador++) + ": " + resultadoFinal[i]);
                        }
                        System.out.println(ConsolaCOLOR.BLACK_BACKGROUND);
                    }
                } else if (opcionMenu.equalsIgnoreCase("3")) {
                    //---------------3.CONSULTAR LIBRO POR AUTOR.------------------------------------------
                    System.out.print("\n" + ConsolaCOLOR.letraMorado
                            + "Introduce el autor del libro que deseas consultar:"
                            + ConsolaCOLOR.letraNegro + "\t");
                    //----------------------------------------------------------------------------------------
                    //autor = tc.readLine();
                    if ((autor = tc.readLine()).isEmpty()) {
                        autor=".";
                    }
                    //----------------------------------------------------------------------------------------
                    envioFinal = USER + "-3-" + autor;
                    //----------------------------
                    envio = new DataOutputStream(socketAlservidor.getOutputStream());
                    envio.writeUTF(envioFinal);
                    //-----------------------------
                    recibir = new DataInputStream(socketAlservidor.getInputStream());
                    stringRecibido = recibir.readUTF();
                    String[] resultadoFinal = stringRecibido.split("\\*");
                    int contador = 1;
                    //--------------------------------------------------------------------
                    if (stringRecibido.isEmpty()) {
                        System.out.println("\n" + ConsolaCOLOR.letraRojo 
                                +"SERVIDOR: NO TENEMOS ESE LIBRO DISPONIBLE :("
                                + ConsolaCOLOR.letraNegro);
                    } else {
                        System.out.println("\n" + ConsolaCOLOR.WHITE_BACKGROUND + "RESULTADO DE TU BUSQUEDA: ");
                        for (int i = 0; i < resultadoFinal.length; i++) {
                            System.out.println("Libro " + (contador++) + ": " + resultadoFinal[i]);
                        }
                        System.out.println(ConsolaCOLOR.BLACK_BACKGROUND);
                    }
                } else if (opcionMenu.equalsIgnoreCase("4")) {
                    //---------------4.AÑADIR LIBRO.------------------------------------------
                    Libro libro = new Libro();
                    envioFinal = USER + "-4-" + libro.StringforTrim();
                    //----------------------------
                    envio = new DataOutputStream(socketAlservidor.getOutputStream());
                    envio.writeUTF(envioFinal);
                    //--------------------------------
                    System.out.println("\n" + ConsolaCOLOR.WHITE_BACKGROUND + "Añadiste 1 libro a la biblioteca virtula: " + libro.toString());
                    System.out.println(ConsolaCOLOR.BLACK_BACKGROUND);
                } else if (opcionMenu.equalsIgnoreCase("5")) {
                    //---------------5.SALIR DE LA APLICACION.------------------------------------------
                    System.out.println("\n-------------------------------------------------------------------");
                    System.out.println(ConsolaCOLOR.BLUE_BACKGROUND + ConsolaCOLOR.letraBlanco + "SERVIDOR: Muchas gracias por utilizar nuestra biblioteca Virtual."
                            + "Esperamos volver a verte pronto :).");
                    System.out.println("-------------------------------------------------------------------\n");
                    envio = new DataOutputStream(socketAlservidor.getOutputStream());
                    envio.writeUTF("fin");
                    break;
                } else {
                    //------------------------------OPCION INCORRECTA-------------------------------------------------------------------
                    System.out.println("\n" + ConsolaCOLOR.letraRojo + "DEBES ELEGIR UNA OPCIÓN DEL MENU. LA OPCIÓN "
                            + opcionMenu + " NO ES VÁLIDA :(" + ConsolaCOLOR.letraNegro);
                }

            } while (true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
