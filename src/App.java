import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
class Methods{
    Object Clonar(ArrayList ElObjeto){
        Object x;
        x = ElObjeto.clone();
        return x;
    }


    ArrayList<Stack<Object>> IngresarAuto(ArrayList<Stack<Object>> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora,String Matricula){
        Scanner input = new Scanner(System.in);
        
        
        Object x;
        //  ||||||||||||||||||||||||||||||   Metodo = Ingresar Auto ||||||||||||||||||||||||||||||//
        //Erick
        System.out.print("En que elevador quiere ingresar su auto");
        int NumEle = input.nextInt()-1;
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
            Elevadores.set(NumEle, (Stack<Object>)Autos.clone()); 
            BackupPop.clear();
            Datos.clear();
            Autos.clear();
        return Elevadores;
        //  ||||||||||||||||||||||||||||||   Metodo = Ingresar Auto ||||||||||||||||||||||||||||||//
    }
    ArrayList<Stack<Object>> RetirarAuto(ArrayList<Stack<Object>> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora, String Matricula){
        //  ||||||||||||||||||||||||||||||   Metodo = Retirar Auto ||||||||||||||||||||||||||||||//
        // Angel 
            //Informacion que vas a utilizar
            /*
            Matricula : Hacer un buscador donde Encuentre la matricula es decir 
                una comparacion si existe la matricula en esa linea ...
                Bueno te hecho una mano con eso
            Elevadores
            Stack
            Datos 
            Backup
            */
            // Inputs
            /*
            *  La Matricula de su auto
            *  El numero del elevador
            */

            //Output Mostrar en pantalla
            /*
            * Numero de elevador
            * Numero que ocupaba en el elevador 
            * Fecha
            * Hora de Ingreso
            * Agregar Hora de Salida 
            * 
            */
        
            //Tu busqueda en cada elevador por medio de la matricula

            ///HERE BRODATH!!!
            int pos=0;
            try {
            for (int i = 0; i < Elevadores.size(); i++) {
                System.out.println(Elevadores.get(i).get(1));
                    /*
                    if(algo.contains(Matricula)){
                        System.out.println("Aqui esta");
                        pos = i; /// DOnde i es tu posiciones en que espacio estaba 
                        break;
                     */
                    

            }
            } catch (Exception e) {
                System.out.println("No se encuentra");
                Menu(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
            }


        //  ||||||||||||||||||||||||||||||   Metodo = Retirar Auto ||||||||||||||||||||||||||||||//
        return Elevadores;
    }
    void InfoPorEle(ArrayList<Stack<Object>> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora){
        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//
        //Yanfer
        //Inputs
        /*
         * El numero de elevador donde supustamente esta su auto
         */

         //Outputs
         /*
          * Mostrar en la pantalla toda la informacion excepto la hora de salida
          */


        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//
    }
    void AutosTotal(ArrayList<Stack<Object>> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora){
        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//
        //Yanfer
        /*
         * La SUMA DE TODOS LOS AUTOS EN LOS ELEVADORES
         * LA SUMA DE TODOS LOS AUOTS POR CADA ELEVADOR
         */

        ////  ||||||||||||||||||||||||||||||   Metodo = Autos Totales ||||||||||||||||||||||||||||||//

    }
    void Menu(ArrayList<Stack<Object>> Elevadores,Stack<Object> Autos,ArrayList<String> Datos,Stack<Object> BackupPop,String Fecha,String LaHora){
        Scanner input = new Scanner(System.in);
        String Matricula = "";
        System.out.println("Cual opcion quieres realizar: \t");
        System.out.println("1- Ingresar el Auto");
        System.out.println("2- Retirar el Auto");
        System.out.println("3- Info. Autos por Elevador");
        System.out.println("4- Autos Totales");
        System.out.println("5- SALIR");
        int opc = input.nextInt();
        switch (opc) {
            case 1:
                System.out.print("Cual es la matricula del auto: \t"+input.nextLine());
                Matricula = input.nextLine();
                System.out.println("/////////"+Elevadores+"//////////");
                Elevadores = IngresarAuto(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora ,Matricula);
                System.out.println("/////////"+Elevadores+"//////////");
                Menu(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
                break;
            case 2:
                System.out.print("Cual es la matricula del auto: \t"+input.nextLine());
                Matricula = input.nextLine();
                System.out.println("/////////"+Elevadores+"//////////");
                Elevadores = RetirarAuto(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora, Matricula);
                System.out.println("/////////"+Elevadores+"//////////");
                Menu(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
        
            default:
                break;
        }
        System.out.println("/////////"+Elevadores+"//////////");
    }

}


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Methods Metodos = new Methods();
    
        ArrayList<Stack<Object>> Elevadores = new ArrayList<>();
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

        Metodos.Menu(Elevadores, Autos, Datos, BackupPop, Fecha, LaHora);
    }
}