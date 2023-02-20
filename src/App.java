import java.util.*;

class Elevador {

    final int numero;
    final private Stack<Auto> autos;
    final private int capacidad;

    /**
     * Clase auxiliar para reportar el estado del elevador.
     */
    static class Status {
        final int numeroElevador;
        final int numeroAuto;
        final Date fechaIngreso;
        final String matricula;

        Status(int numeroElevador, int numeroAuto, Date fechaIngreso, String matricula) {
            this.numeroElevador = numeroElevador;
            this.numeroAuto = numeroAuto;
            this.fechaIngreso = fechaIngreso;
            this.matricula = matricula;
        }
    }

    static class Auto {
        final String matricula;
        final Date fechaIngreso;

        Auto(String matricula, Date fechaIngreso) {
            this.matricula = matricula;
            this.fechaIngreso = fechaIngreso;
        }
    }

    Elevador(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.autos = new Stack<Auto>();
    }

    /**
     * Obtener una lista de solo lectura que representa el estado del elevador.
     */
    List<Status> status() {
        List<Status> status = new ArrayList<Status>();
        for (int i = 0; i < autos.size(); i++) {
            Auto auto = autos.elementAt(i);
            if (auto == null)
                continue;
            status.add(new Status(numero, i, auto.fechaIngreso, auto.matricula));
        }
        return status;
    }

    /**
     * Ingresar un auto al elevador.
     * 
     * @param matricula
     * @return
     * @throws IllegalStateException Arrojada si el elevador ya no tiene espacios
     *                               disponibles.
     */
    Status ingresarAuto(String matricula) throws IllegalStateException {
        if (disponibles() <= 0) {
            throw new IllegalStateException("No se puede agrear auto, elevador lleno");
        }

        Auto auto = new Auto(matricula, new Date());
        autos.push(auto);

        return new Status(numero, autos.size(), auto.fechaIngreso, auto.matricula);
    }

    /**
     * Retirar un auto utilizando el numero dentro del elevador.
     * 
     * @param numero El numero a retirar.
     * @return El reporte del auto retirado
     * @throws IllegalStateException    Arrojada si el elevador esta vacío
     * @throws IllegalArgumentException Arrojada si el vehiculo no se encuentra en
     *                                  el elevador.
     */
    Status retirarAuto(int numero) throws IllegalStateException, IllegalArgumentException {
        if (vacio()) {
            throw new IllegalStateException("No se puede retirar auto, elevador vacio");
        }

        try {
            Auto auto = autos.elementAt(numero);
            autos.remove(numero);
            return new Status(this.numero, numero, auto.fechaIngreso, auto.matricula);
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(String.format("No se puede retirar auto #%d, lugar vacio", numero));
        }
    }

    /**
     * Retira un vehiculo utilizando su matricula.
     * 
     * @param matricula
     * @return El reporte del auto retirado
     * @throws IllegalStateException    Arrojado si el elevador esta vacío
     * @throws IllegalArgumentException Arrajada si la matricula no se encuentra
     *                                  dentro del elevador
     */
    Status retirarAuto(String matricula) throws IllegalStateException, IllegalArgumentException {
        for (int i = 0; i < autos.size(); i++) {
            Auto auto = autos.elementAt(i);
            if (!matricula.equalsIgnoreCase(auto.matricula))
                continue;
            return retirarAuto(i);
        }
        throw new IllegalArgumentException(String.format(
                "No se puede retirar auto con matricula %s, no se encuentra en elevador %d", matricula, numero));

    }

    /**
     * Determina el status de una matricual en particular
     * 
     * @param matricula La matricula consultada.
     * @return La informatcion del vehiculo
     * @throws IllegalArgumentException Arrojada si la matricula no se encuentra en
     *                                  el elevador.
     */
    Status statusAuto(String matricula) throws IllegalArgumentException {
        for (int i = 0; i < capacidad; i++) {
            Auto auto = autos.get(i);
            if (auto == null)
                continue;
            if (auto.matricula.equals(matricula)) {
                return new Status(numero, i, auto.fechaIngreso, auto.matricula);
            }
        }

        throw new IllegalArgumentException(
                String.format("El auto con matricula %s no se encuentra en este elevador #%d", matricula, numero));
    }

    /**
     * Determina si el elevador no tiene ningun auto.
     * 
     * @return `true` si el elevador esta vacío, de lo contrario `false`
     */
    boolean vacio() {
        return disponibles() == capacidad;
    }

    /**
     * @return Determina el numero de espacios disponibles
     */
    int disponibles() {
        return capacidad - autos.size();
    }

    /**
     * @return Determina el numero de espacios ocupados.
     */
    int ocupados() {
        return capacidad - disponibles();
    }
}

/**
 * Clase que gestiona elevadores y autos estacionados en ellos.
 */
class AdministradorElevador {
    private Scanner input;
    private List<Elevador> elevadores;

    AdministradorElevador(Scanner input, List<Elevador> elevadores) {
        this.input = input;
        this.elevadores = elevadores;
    }

    private Elevador.Status ingresarAuto() {
        String matricula = leerMatriculaAuto("Cual es la matricula del auto (vacio para cancelar):", "");
        if ("".equals(matricula)) {
            return null;
        }

        boolean valido = false;
        Elevador elevador = null;
        do {
            int numeroElevador = leerNumeroElevador("En que elevador quiere ingresar su auto (0 para cancelar):", 0);
            if (numeroElevador == 0) {
                return null;
            }

            for (Elevador e : elevadores) {
                if (e.numero == numeroElevador) {
                    elevador = e;
                    break;
                }
            }

            if (elevador.disponibles() == 0) {
                System.out.println(String.format("Elevador sin espacios: %d", elevador.disponibles()));
                valido = false;
            } else {
                valido = true;
            }
        } while (!valido);
        return elevador.ingresarAuto(matricula);
    }

    // Informacion que vas a utilizar
    /*
     * Matricula : Hacer un buscador donde Encuentre la matricula es decir
     * una comparacion si existe la matricula en esa linea ...
     * Bueno te hecho una mano con eso
     * Elevadores
     * Stack
     * Datos
     * Backup
     */
    // Inputs
    /*
     * La Matricula de su auto
     * El numero del elevador
     */
    private Elevador.Status retirarAuto() {
        String matricula = leerMatriculaAuto("Matricula de auto a retirar (vacio para cancelar):", "");
        Elevador.Status resultado = null;
        for (Elevador elevador : elevadores) {
            try {
                resultado = elevador.retirarAuto(matricula);
            } catch (IllegalArgumentException ex) {
                continue;
            } catch (IllegalStateException ex) {
                continue;
            }
        }
        if (resultado == null) {
            return null;
        }
        return resultado;
    }

    /**
     * Clase auxiliar para reportar el status de un elevador.
     */
    static class TotalElevador {
        final int numero;
        final List<Elevador.Status> status;

        TotalElevador(int numero, List<Elevador.Status> status) {
            this.numero = numero;
            this.status = status;
        }
    }

    /**
     * Inputs:
     * El numero de elevador donde supustamente esta su auto
     *
     * Outputs:
     * Mostrar en la pantalla toda la informacion excepto la hora de salida
     */
    private TotalElevador statusElevador() {
        int numeroElevador = leerNumeroElevador("Numero de elevador a consultar (0 para cancelar):", 0);
        if (numeroElevador == 0) {
            return null;
        }

        Elevador elevador = null;
        for (Elevador e : elevadores) {
            if (e.numero == numeroElevador) {
                elevador = e;
                break;
            }
        }
        if (elevador == null) {
            System.out.println(String.format("Elevador invalido: %d", numeroElevador));
            return null;
        }

        return new TotalElevador(numeroElevador, elevador.status());
    }

    /**
     * La SUMA DE TODOS LOS AUTOS EN LOS ELEVADORES
     * LA SUMA DE TODOS LOS AUOTS POR CADA ELEVADOR
     */
    private void statusTotal() {
        int autosTotales = 0;

        System.out.println();
        for (Elevador elevador : elevadores) {
            autosTotales += elevador.ocupados();

            System.out.println(String.format("Elevador #%d", elevador.numero));
            System.out.println(String.format("\tDisponibles %d", elevador.disponibles()));
            System.out.println(String.format("\tOcupados %d", elevador.ocupados()));
            for (Elevador.Status status : elevador.status()) {
                System.out.println(String.format("\t\t#%d", status.numeroAuto));
                System.out.println(String.format("\t\tMatricula: %s", status.matricula));
                System.out.println(String.format("\t\tFecha Ingreso: %s", status.fechaIngreso));
            }
        }
        System.out.println(String.format("Total Autos: %d", autosTotales));
    }

    // ======================================= Interfaz de Usuario ==========================================

    void menu() {
        System.out.println("Cual opcion quieres realizar:");
        System.out.println("\t1- Ingresar el Auto");
        System.out.println("\t2- Retirar el Auto");
        System.out.println("\t3- Info. Autos por Elevador");
        System.out.println("\t4- Autos Totales");
        System.out.println("\t5- SALIR");
        int opc = input.nextInt();

        switch (opc) {
            case 1: {

                Elevador.Status resultado = ingresarAuto();
                if (resultado == null) {
                    System.out.println("No se pudo ingresar auto");
                } else {
                    System.out.println("Auto Ingresado:");
                    System.out.println(String.format("\tMatricula: %s", resultado.matricula));
                    System.out.println(String.format("\tFecha Ingreso: %s", resultado.fechaIngreso));
                }
                System.out.println();
                menu();
                break;
            }
            case 2: {
                Elevador.Status resultado = retirarAuto();
                if (resultado == null) {
                    System.out.println("No se pudo retirar auto");
                } else {
                    System.out.println("Auto Retirado:");
                    System.out.println(String.format("\tElevador: %d", resultado.numeroElevador));
                    System.out.println(String.format("\t#%d", resultado.numeroAuto));
                    System.out.println(String.format("\tMatricula: %s", resultado.matricula));
                    System.out.println(String.format("\tFecha Ingreso: %s", resultado.fechaIngreso));
                    System.out.println(String.format("\tFecha Retirado: %s", new Date()));
                }
                System.out.println();
                menu();
                break;
            }
            case 3: {
                TotalElevador resultado = statusElevador();
                if (resultado == null) {
                    System.out.println("No se pudo obtener el status del elevador");
                } else {
                    System.out.println(String.format("Elevador #%d", resultado.numero));
                    for (Elevador.Status status : resultado.status) {
                        System.out.println(String.format("\t#%d", status.numeroAuto));
                        System.out.println(String.format("\tMatricula: %s", status.matricula));
                        System.out.println(String.format("\tFecha Ingreso: %s", status.fechaIngreso));
                    }
                }
                System.out.println();
                menu();
                break;
            }
            case 4:
                statusTotal();
                System.out.println();
                menu();
                break;
            case 5:
                // Salir
                break;
            default:
                break;
        }
    }

    /**
     * Solicita al usuario in valor entero que representa un numero de elevador. Esta funcion valida que el numero ingresado
     * se a un numero de elevador valido. Si el valor no es valido se le volvera a solicitar un valor al usuario hasta
     * que ingrese un valor valido o ingrese el valor determinado por el parametro `valorCancelar` de esta funcion.
     * 
     * @param mensaje El mensaje o "prompt" a mostrar al usuario
     * @param valorCancelar El valor que cuando se ingresado cancela la seleccion de elevador
     */
    private int leerNumeroElevador(String mensaje, int valorCancelar) {
        int numeroElevador = valorCancelar;
        Set<Integer> opciones = new HashSet<Integer>();
        for (Elevador elevador : elevadores) {
            opciones.add(elevador.numero);
        }

        boolean invalido = false;
        do {
            System.out.println(mensaje);
            for (Elevador elevador : elevadores) {
                System.out.println(String.format("\t%d)", elevador.numero));
            }
            numeroElevador = input.nextInt();
            invalido = !opciones.contains(numeroElevador);
        } while (invalido && numeroElevador != valorCancelar); 
        return numeroElevador;
    }

    /**
     * Solicita al usuario una cadena que representa la matricula de su auto.
     * @param mensaje El mensaje o "prompt" a mostrar al usuario.
     * @param valorCancelar
     * @return
     */
    private String leerMatriculaAuto(String mensaje, String valorCancelar) {
        System.out.println(mensaje + input.nextLine());
        String matricula = input.nextLine();
        return matricula;
    }
}

public class App {
    static int CAPACIDAD_ELEVADOR = 5;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        List<Elevador> elevadores = Arrays.asList(
                new Elevador(1, CAPACIDAD_ELEVADOR),
                new Elevador(2, CAPACIDAD_ELEVADOR),
                new Elevador(3, CAPACIDAD_ELEVADOR));

        AdministradorElevador administrador = new AdministradorElevador(input, elevadores);
        administrador.menu();
    }
}