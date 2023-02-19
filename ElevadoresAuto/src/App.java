import java.io.*;
import java.util.*;
class Methods{
    Object Clonar(ArrayList ElObjeto){
        Object x;
        x = ElObjeto.clone();
        return x;
    }


    ArrayList<Object> IngresarAuto(ArrayList<Object> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora){
        Scanner input = new Scanner(System.in);
        
        
        Object x;
        //  ||||||||||||||||||||||||||||||   Metodo = Ingresar Auto ||||||||||||||||||||||||||||||//
        //Erick
        //Cual es tu matricula de auto
        System.out.print("Cual es la matricula del auto: \t");
        String Matricula = input.nextLine();
        System.out.print("En que elevador quiere ingresar su auto");
        int NumEle = input.nextInt()-1;
        Autos.push(Elevadores.get(NumEle));      
        int pos = 0;
        Object Back;
        for (Object elem : Autos) {
            System.out.println(elem+"++++");
            if(elem == null){
                break;
            }
            pos++;
        }
        pos=Autos.size()-(pos+1);
            for (int i = 0; i < pos+1; i++) {   //Saca todo lo que estaba despues del espacio vacio
                Back = Autos.pop();
                BackupPop.push(Back);
            }
            BackupPop.remove(null);
            Datos.add(Matricula);               //Añade las datos y los datos automaticos
            Datos.add(Fecha);
            Datos.add(LaHora);
            Autos.push(Clonar(Datos));
            for (Object AgrNuevo : BackupPop) { //Vuelve a meter los otros autos
                Autos.push(AgrNuevo);
            }
            x = Autos.clone();
            System.out.println(x);
            //Elevadores.set(1, x); ///Guardar las modificaciones
            Elevadores.set(NumEle, x); 
            BackupPop.clear();
            Autos.clear();
            Datos.clear();
        return Elevadores;
        //  ||||||||||||||||||||||||||||||   Metodo = Ingresar Auto ||||||||||||||||||||||||||||||//
    }

}


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Methods Metodos = new Methods();
    
        ArrayList<Object> Elevadores = new ArrayList<>();
        Stack<Object> Autos = new Stack<Object>();
        ArrayList<String> Datos = new ArrayList<>();
        Stack<Object> BackupPop = new Stack<Object>();
        

        Calendar calendario = Calendar.getInstance();
        String Fecha,LaHora;
        Fecha = calendario.get(Calendar.DAY_OF_MONTH)+"-"+calendario.get(Calendar.MONTH)+"-"+calendario.get(Calendar.YEAR);
        LaHora = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE);
       
        Elevadores.add(null);
        Elevadores.add(null);
        Elevadores.add(null);
        
        System.out.println("/////////"+Elevadores+"//////////");
        Elevadores = Metodos.IngresarAuto(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
        System.out.println("/////////"+Elevadores+"//////////");
        Elevadores = Metodos.IngresarAuto(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
        System.out.println("/////////"+Elevadores+"//////////");
        Elevadores = Metodos.IngresarAuto(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
        System.out.println("/////////"+Elevadores+"//////////");

        //Menu 
        /*
        do {                        
            
        } while (Fecha == "");
        */


        
        //  ||||||||||||||||||||||||||||||   Metodo = Retirar Auto ||||||||||||||||||||||||||||||//
        // Angel 



        //  ||||||||||||||||||||||||||||||   Metodo = Retirar Auto ||||||||||||||||||||||||||||||//
        //  ||||||||||||||||||||||||||||||   Metodo = Autos Por Elevador ||||||||||||||||||||||||||||||//
        //Yanfer


        //  ||||||||||||||||||||||||||||||   Metodo = Autos Por Elevador ||||||||||||||||||||||||||||||//
        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//
        //Yanfer


        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//

        



    }
}
