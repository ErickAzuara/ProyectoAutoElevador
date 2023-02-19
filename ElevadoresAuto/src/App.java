import java.io.*;
import java.util.*;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
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
        //ArrayList<Object>[] Elevadores = new ArrayList[3];
        Stack Autos = new Stack();
        ArrayList<String> Datos = new ArrayList();
        Stack BackupPop = new Stack();

        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        System.out.println(hora+" "+minutos+" "+segundos);
        System.out.println(Autos.size());
        
        Datos.add("1");
        Datos.add("1");
        Datos.add("1");
        Datos.add("1");
        Datos.add("1");
        
        Object x = Datos.clone();
        Autos.push(x);
        Datos.clear();
        

        Datos.add("2");
        Datos.add("2");
        Datos.add("2");
        Datos.add("2");
        Datos.add("2");

        x = Datos.clone();
        x = null;
        Autos.push(x);
        x = Datos.clone();
        Autos.push(x);

        
   

        System.out.println(Autos.size());
        System.out.println("----");

        ;
        System.out.println(Autos.empty());
        /*
        System.out.println(Autos);
        Datos.clear();
        System.out.println(Autos);
        
        Object Backup = Autos.pop();
        System.out.println("as"+Backup);
        System.out.println(Autos);
         */

        /*
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

         */
        //Cual es tu matricula de auto
        System.out.print("Cual es la matricula del auto");
       //---String Matricula = input.nextLine();
        //input.nextLine();

        //Desplegar la elevadores
        //Mostrar elevador
        //Seleccion de elevador
        System.out.print("En que elevador quiere ingresar su auto");
        //---int NumEle = input.nextInt();


        //Ingreso de Auto al elevador seleccionado
        //Verificar si hay espacio disponible
        int pos = 0, opc=0;
        for (Object elem : Autos) {
            if(elem == null){
                opc =1;
                break;
            }
            pos++;
        }
        System.out.println("----"+Autos+"----");
        if(opc>0){
            for (int i = 0; i < pos; i++) {
                Object Back = Autos.pop();
                BackupPop.push(Back);
            }
            Autos.remove(null);
            Autos.push("xxx");    /////////Se agrega el nuevo auto
            for (Object AgrNuevo : BackupPop) {
                Autos.push(AgrNuevo);
            }
        }else{
            Autos.push("zzz");    /////////Se agrega el nuevo auto
        }
        //Verificaciones
        
        



        



    }
}
