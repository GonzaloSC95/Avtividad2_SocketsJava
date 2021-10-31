package Actividad2_Sockets_Version1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Libro implements Serializable {

    //ATRIBUTOS
    private String Isbn;
    private String titulo;
    private String autor;
    private String precio;
//---------------------------------------------------------------------------------
    //CONSTRUCTOR 1
    public Libro(String Isbn, String titulo, String autor, String precio) {
        this.Isbn = Isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio + " €";
    }

    //CONSTRUCTOR 2 - AÑADIR LIBRO A LA BIBLIOTECA VIRTUAL
    public Libro() {
        //ENTRADA DE DATOS
        //ASIGNACION DE ATRIBUTOS
        try {
            BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n"+ConsolaCOLOR.CYAN_BACKGROUND
                    +"Introduce los datos del libro que deseas añadir:"
                    +ConsolaCOLOR.BLACK_BACKGROUND);
            System.out.print("Introduce el isbn del libro:\t");
            this.Isbn = tec.readLine();
            System.out.print("Introduce el título del libro:\t");
            this.titulo = tec.readLine();
            System.out.print("Introduce el autor del libro:\t");
            this.autor = tec.readLine();
            System.out.print("Introduce el precio del libro:\t");
            this.precio = tec.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//----------------------------------------------------------------------
    //TO STRING DE LIBRO
    @Override
    public String toString() {
        String str = ConsolaCOLOR.letraVerde + "ISBN-" + ConsolaCOLOR.letraNegro + Isbn
                + " | " + ConsolaCOLOR.letraVerde + "TITULO: " + ConsolaCOLOR.letraNegro + titulo
                + " | " + ConsolaCOLOR.letraVerde + "AUTOR: " + ConsolaCOLOR.letraNegro + autor
                + " | " + ConsolaCOLOR.letraVerde + "PRECIO: " + ConsolaCOLOR.letraNegro + precio;
        return str.toUpperCase();
    }

    public String StringforTrim() {
        String libro = Isbn + "-" + titulo + "-" + autor + "-" + precio;
        return libro;
    }
    //-----------------GETTERS--------------------------

    public String getIsbn() {
        return Isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getPrecio() {
        return precio;
    }

}
