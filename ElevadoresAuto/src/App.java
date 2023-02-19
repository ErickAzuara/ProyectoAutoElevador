import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Stack Hola = new Stack();

        //Entradas
        /*
         * Fecha
         * Numeros de autos
         * Hora de ingreso 
         * hora de Salida
         * 
         * Los datos se asignara automaticamente
         * cuando se ingrese o se retire
         */
        Stack Elevador = new Stack();
   
        ArrayList<String> NewAuto = new ArrayList<>();
        String[] Autos = new String[4];
        NewAuto.add("ASIN-241");
        NewAuto.add("23-02-1999");
        NewAuto.add("12:30");
        NewAuto.add("15:30");
        NewAuto.toArray(Autos);

        for (String Element : Autos) {
            System.out.println(Element);
        }

        Elevador.push(Hola);

        System.out.println(Elevador);

        NewAuto.clear();

        System.out.println(Elevador);

        





    }
}
