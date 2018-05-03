package listado;
import java.io.IOException;

public class MainClass {

    public static void main(String args[]) throws IOException {

        // Creamos el objeto con la lista de empleados
        ListadoEmpleados listaEmpleados = new ListadoEmpleados("datos/datos.txt");

        // Sacamos por pantalla la cantidad de empleados que hay
        System.out.println(listaEmpleados.obtenerNumeroEmpleadosArchivo());

        // comprobamos cuantos DNI repetidos hay
        System.out.println(listaEmpleados.contarEmpleadosDnisRepetidos());

        // Comprobamos si hay DNIS repetidos
        if(listaEmpleados.hayDnisRepetidosArchivo())
        {
            System.out.println("Hay DNIs repetidos, procedemos a repararlos");
            listaEmpleados.repararDnisRepetidos(listaEmpleados.obtenerDnisRepetidosArchivo());
        }
        else
        {
            System.out.println("No hay DNIs repetidos");
        }

        // comprobamos cuantos DNI repetidos hay otra vez
        System.out.println(listaEmpleados.contarEmpleadosDnisRepetidos());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // comprobamos cuantos correos repetidos hay
        System.out.println(listaEmpleados.contarEmpleadosCorreosRepetidos());

        // Comprobamos si hay correos repetidos
        if(listaEmpleados.hayCorreosRepetidosArchivo())
        {
            System.out.println("Hay correos repetidos, procedemos a repararlos");
            listaEmpleados.repararCorreosRepetidos(listaEmpleados.obtenerCorreosRepetidosArchivo());
        }
        else
        {
            System.out.println("No hay correos repetidos");
        }

        // comprobamos cuantos correos repetidos hay otra vez
        System.out.println(listaEmpleados.contarEmpleadosCorreosRepetidos());

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // validamos el listado de empleados
        listaEmpleados.validarListaArchivo();

    }
}
