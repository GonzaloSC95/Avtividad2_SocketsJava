package Actividad2_Sockets_Version2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class HiloCliente extends Thread {

    //---------ATRIBUTOS-------------------
    private Socket socketAlcliente;
    private Servidor bibliotecaVirtual;

    //--------------------------
    //-----------CONSTRUCTOR-------------
    public HiloCliente(Socket socketAlcliente, Servidor bibliotecaVirtual) {
        this.socketAlcliente = socketAlcliente;
        this.bibliotecaVirtual = bibliotecaVirtual;
        //---------------------------------
        this.start();
    }

    @Override
    public void run() {
        String stringRecibido = "";
        String[] arrayDatos = null;
        //------------------------
        DataOutputStream envio = null;
        DataInputStream recepcion = null;
        //------------------------------
        try {
            //------------------------------------
            envio = new DataOutputStream(this.socketAlcliente.getOutputStream());
            recepcion = new DataInputStream(this.socketAlcliente.getInputStream());

            while (true) {
                //------------------------------
                String recopilandoLibros = "";
                //-------------Recepcion de datos------------------------
                stringRecibido = recepcion.readUTF();
                arrayDatos = stringRecibido.split("-");
                //-------------------------------------------------------
                //------------------NOMBRE DE USUARIO-----------------------------------
                if (!arrayDatos[0].equalsIgnoreCase("Fin")) {
                    this.setName(arrayDatos[0]);
                    System.out.println(ConsolaCOLOR.YELLOW_BACKGROUND + "USUARIO: " + this.getName().toUpperCase()
                            + ConsolaCOLOR.BLACK_BACKGROUND);
                    //////////////////////////////--------------------
                    String[] aux = ArrayUtils.remove(arrayDatos, 0);
                    //--------------------------------------------------------------------
                    System.out.println("DATOS INTODUCIDOS POR EL USUARIO: " + Arrays.toString(aux));
                } else {
                    System.out.println("\n"+ConsolaCOLOR.letraRojo
                            + "EL USUARIO " + this.getName().toUpperCase() + " SE HA DESCONECTADO\n"
                            + ConsolaCOLOR.letraNegro);
                    break;
                }
                //---------------Procedimiento segun consulta-----------
                if (arrayDatos[1].equalsIgnoreCase("1")) {            
                    //---------------1.CONSULTA POR ISBN-----------
                    for (Libro lib : bibliotecaVirtual.bibliotecaVirtual) {
                        if (lib.getIsbn().equalsIgnoreCase(arrayDatos[2])) {
                            //libros.add(lib);
                            recopilandoLibros=recopilandoLibros+lib.toString()+"*";
                        }
                    }
                    //envio.writeUTF(libros.toString());
                    envio.writeUTF(recopilandoLibros);
                } else if (arrayDatos[1].equalsIgnoreCase("2")) {
                    //---------------2.CONSULTA POR TITULO-----------
                    for (Libro lib : bibliotecaVirtual.bibliotecaVirtual) {
                        if (lib.getTitulo().equalsIgnoreCase(arrayDatos[2])) {
                            //libros.add(lib);
                            recopilandoLibros=recopilandoLibros+lib.toString()+"*";
                        }
                    }
                    envio.writeUTF(recopilandoLibros);
                } else if (arrayDatos[1].equalsIgnoreCase("3")) {
                    //---------------3.CONSULTA POR AUTOR-----------
                    for (Libro lib : bibliotecaVirtual.bibliotecaVirtual) {
                        if (lib.getAutor().equalsIgnoreCase(arrayDatos[2])) {
                            //libros.add(lib);
                            recopilandoLibros=recopilandoLibros+lib.toString()+"*";
                        }
                    }
                    envio.writeUTF(recopilandoLibros);
                } else if (arrayDatos[1].equalsIgnoreCase("4")) {
                    //---------------4.INSERTAR UN LIBRO-----------
                    Libro libro = new Libro(arrayDatos[2], arrayDatos[3], arrayDatos[4], arrayDatos[5]);
                    bibliotecaVirtual.bibliotecaVirtual.add(libro);
                    System.out.println("\n" + ConsolaCOLOR.WHITE_BACKGROUND
                            +this.getName().toUpperCase()+" HA AÑADIDO UN LIBRO A LA BIBLIOTECA VIRTUAL: " + libro.toString()
                            + ConsolaCOLOR.BLACK_BACKGROUND + "\n");
                }
                //----------------------------
            }
            this.socketAlcliente.close();
        } catch (UnknownHostException e) {
            //System.out.println(e.getMessage());
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }
}
