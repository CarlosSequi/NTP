import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEmpleado {

    public static void main(String[] args) {
        // inicializacion array empleados:
        Empleado [] empleados = {
                new Empleado("juan", "lopez", 5000, "IT"),
                new Empleado("mateo", "insausti", 3587, "ventas"),
                new Empleado("joaquin", "fernandez", 4700.77, "marketing"),
                new Empleado("Lucas", "martinez", 6200,"IT"),
                new Empleado("pedro", "garcia", 3300, "ventas"),
                new Empleado("Fernando", "Gonzalez", 4236.4, "marketing")
        };

        // Crear predicado para quedarnos con empleados con sueldo superior
        // a 4000€ e inferior a 6000€
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSueldo() >= 4000 &&
                                                     empleado.obtenerSueldo() <= 6000);

        // comparador para ordenar por sueldo haciendo referencia a la funcion obtener sueldo
        Comparator<Empleado> comparador = Comparator.comparing(Empleado::obtenerSueldo);

        // Obtenemos lo deseado: empleados con el sueldo entre 4000 y 6000 ordenados
        Arrays.stream(empleados).filter(condicion).sorted(comparador).forEach((System.out::println));

        // Obtener el primer elemento que cumple una condicion (con el get() nos devuelve un empleado, sino nos
        // devolveria un optional de empleado
        Empleado empleado = Arrays.stream(empleados).filter(condicion).sorted(comparador).findFirst().get();

        // Referencias funcionales (los dos puntos dobles)
        // (Empleado es el argumento que recibe la funcion y String es el tipo que devuelve)
        Function<Empleado, String> refNombre = Empleado::obtenerNombre;
        Function<Empleado, String> refApellido = Empleado::obtenerPrimerApellido;

        // Creamos un comparador a partir de estas referencias funcionales
        comparador = Comparator.comparing(refApellido).thenComparing(refNombre);

        // Ordenamos los empleados usando este criterio
        Arrays.stream(empleados).sorted(comparador).forEach(System.out::println);

        //Ordenacion de los empleados con el mismo comparador pero en orden inverso
        Arrays.stream(empleados).sorted(comparador.reversed()).forEach(System.out::println);
        
        // Obtener los apellidos pero sin repeticion
        Stream<String> apellidosDisponibles = Arrays.stream(empleados).map(Empleado::obtenerPrimerApellido);
        // Eliminamos los repetidos
        apellidosDisponibles.distinct().sorted().forEach(System.out::println);
        
        // Almacenamos en una lista estos apellidos (hemos de volver a crear el flujo)
        List<String> listaApellidosSinRepeticion = Arrays.stream(empleados).map(Empleado::obtenerPrimerApellido).distinct().collect(Collectors.toList());

        // Obtenemos los nombres sin repetidos
        List<String> listaNombresSinRepeticion = Arrays.stream(empleados).map(Empleado::obtenerNombre).distinct().sorted().collect(Collectors.toList());

        // Obtenemos los empleados agrupados por departamento
        // de vuelve un map de nombres de departamentos junto con la lista de sus empleados para cada uno
        Map<String, List<Empleado>> mapa = Arrays.stream(empleados).collect(Collectors.groupingBy(Empleado::obtenerDepartamento));
        // creamos el flujo con las entradas de este map obtenido:
        Stream<Map.Entry<String, List<Empleado>>> flujo = mapa.entrySet().stream();

        // Recorrido de entradas
        flujo.forEach(entrada -> {
            System.out.println(entrada.getKey());
            entrada.getValue().stream().forEach(System.out::println);
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Implementamos esto ultimo sin programacion funcional
        Map<String, List<Empleado>> agrupamiento = new HashMap<>();
        String departamento;
        List<Empleado> listaEmpleados;
        // Vamos considerando cada empleado
        for(int i=0; i<empleados.length; i++)
        {
            //obtenemos el departamento del empleado
            departamento = empleados[i].obtenerDepartamento();

            //comprobamos si ya existe esa clave en el mapa
            listaEmpleados=agrupamiento.get(departamento);

            // tratamiento del empleado en funcion de si existia la entrada
            // para el departamento o no
            if(listaEmpleados == null)
            {
                listaEmpleados = new LinkedList<>();
                listaEmpleados.add(empleados[i]);

                // Meto la lista en el mapa
                agrupamiento.put(departamento, listaEmpleados);
            }
            else
            {
                listaEmpleados.add(empleados[i]);
            }
        }

        // creamos un iterador sobre la coleccion agrupamiento
        Iterator<String> claves = agrupamiento.keySet().iterator();
        String clave;
        while(claves.hasNext())
        {
            // obtengo la siguiente clave (departamento)
            clave = claves.next();

            // la mostramos
            System.out.println(clave);

            // Listado completo de empleados asociados a la clave
            listaEmpleados = agrupamiento.get(clave);

            // se itera sobre la coleccion
            for(Empleado elementoLista : listaEmpleados)
            {
                System.out.println(elementoLista);
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Agrupamiento con funcionalidad completa: vamos a indicar
        // el tipo de mapa a usar, concretamente se lo decimos en TreeMap::new
        // Con el ultimo parametro le decimos como tratar la lista, como reducirla, es decir, al final tendremos un
        // treemap de String(departamento) y Long(cantidad empleados)
        TreeMap<String, Long> cantidadEmpleadosPorDepartamento = Arrays.stream(empleados).
                collect(Collectors.groupingBy(Empleado::obtenerDepartamento, TreeMap::new, Collectors.counting()));

        // Lo mostramos por pantalla
        cantidadEmpleadosPorDepartamento.entrySet().stream().forEach(entrada -> {System.out.printf
                ("el departamento %s tiene %d empleados %n", entrada.getKey(), entrada.getValue());});

        // Sumar sueldos de todos los empleados
        double sumaSueldos = Arrays.stream(empleados).mapToDouble(Empleado::obtenerSueldo).sum();

        // Obtener la media de sueldos
        double mediaSueldos = Arrays.stream(empleados).mapToDouble(Empleado::obtenerSueldo).average().getAsDouble();



    }
}