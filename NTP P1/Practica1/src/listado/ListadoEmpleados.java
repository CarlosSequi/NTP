package listado;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoEmpleados {

    /* Dato miembro para almacenar a los empleados tal y como se encuentran
       en el archivo de datos.txt */
    private ArrayList<Empleado>listadoArchivo;

    /*Dato miembro para almacenar a los empleados como mapa con pares
     (una vez reparados los datos leidos del archivo)
      <dni - empleado>*/
    private Map<String, Empleado> listado;




    /**
     * Constructor de la clase
     * @param archivoEmpleados conteniendo la ruta donde se encuentran los datos
     * @throws IOException
     */
    public ListadoEmpleados(String archivoEmpleados) throws IOException {
        listado = new HashMap<>();
        listadoArchivo = new ArrayList<>();

        // creamos el flujo para procesar la lineas del archivo
        Stream<String> lineas = Files.lines(Paths.get(archivoEmpleados), StandardCharsets.ISO_8859_1);

        // creamos el empleado por cada una de las lineas
        lineas.forEach(linea ->
        {
            Empleado nuevoEmpleado = crearEmpleado(linea);
            listadoArchivo.add(nuevoEmpleado);
        });

    }

    /**
     * Metodo encargado de la creacion de un empleado mediante una linea de datos
     * @param lineaEmpleado cadena conteniendo un empleado en una linea de datos
     * @return un nuevo empleado
     */
    private Empleado crearEmpleado(String lineaEmpleado) {

        // patron para partir el contenido de las lineas
        Pattern patron = Pattern.compile(",");

        // separamos la linea en los distintos campos
        List<String> infoEmpleado = patron.splitAsStream(lineaEmpleado).collect(Collectors.toList());

        // creamos el empleado
        Empleado nuevoEmpleado = new Empleado(infoEmpleado.get(0), infoEmpleado.get(1), infoEmpleado.get(2), infoEmpleado.get(3));

        // lo devolvemos
        return nuevoEmpleado;

    }

    /**
     * @return cantidad de empleados
     */
    public int obtenerNumeroEmpleadosArchivo(){
        return listadoArchivo.size();
    }

    /**
     * Comprueba si existen empledaos con DNIs repetidos
     * @return booleano true/false
     */
    public boolean hayDnisRepetidosArchivo(){

        // almacenamos la cantidad de empleados antes de eliminar repetidos
        int numeroEmpleadosConRepetidos = obtenerNumeroEmpleadosArchivo();

        // Obtener los DNIs
        Stream<String> DNIsDisponibles = listadoArchivo.stream().map(Empleado::obtenerDni);

        // Eliminamos los repetidos
        Long cantidadDNIsSinRepetir = DNIsDisponibles.distinct().collect(Collectors.counting());

        // cantidad de dnis no repetidos
        //System.out.println(cantidadDNIsSinRepetir);

        // comprobamos si hay DNIs repetidos o no
        if(cantidadDNIsSinRepetir < numeroEmpleadosConRepetidos)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * obtiene la lista de empleados con dnis repetidos
     * @return lista de empleados con dnis repetidos
     */
    public Map<String, List<Empleado>> obtenerDnisRepetidosArchivo(){
        // creamos un map con todos los DNI y una lista de empleados que lo usan
        Map<String, List<Empleado>> dniListaEmpleados = listadoArchivo.stream().
                collect(Collectors.groupingBy(Empleado::obtenerDni));

        // creamos un predicado para quedarnos solo con los DNI que tengan mas de un empleado
        Predicate<Map.Entry<String, List<Empleado>>> condicion = dni -> dni.getValue().size() > 1;

        // nos quedamos con los dni que tienen mas de un empleado en su lista
        Map<String, List<Empleado>> dniRepetidosListaEmpleados = dniListaEmpleados.entrySet().stream()
                .filter(condicion).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // los sacamos por pantalla
        //dniRepetidosListaEmpleados.entrySet().stream().forEach(x-> x.getValue().forEach(System.out::println));

        // devolvemos este ultimo map
        return dniRepetidosListaEmpleados;
    }

    /**
     * @return numerp de empleados que tienen el dni repetido
     */
    public int contarEmpleadosDnisRepetidos (){
        Map<String, List<Empleado>> listaDnisRepetidos = obtenerDnisRepetidosArchivo();

        System.out.println(listaDnisRepetidos.entrySet().stream().mapToInt(entrada -> entrada.getValue().size()).sum());

        return listaDnisRepetidos.entrySet().stream().mapToInt(entrada -> entrada.getValue().size()).sum();
    }

    /**
     * Se encarga de reparar los dnis de los empleados que lo tengan repetido
     * @param listaRepeticion lista con los empleados con dni repetido
     */
    public void repararDnisRepetidos(Map<String, List<Empleado>> listaRepeticion){
        listaRepeticion.entrySet().stream().forEach(dni -> {
            dni.getValue().forEach(empleado -> {
                int indiceEmpleadoListadoArchivo = listadoArchivo.indexOf(empleado);
                listadoArchivo.get(indiceEmpleadoListadoArchivo).asignarDniAleatorio();
            });
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * obtiene la lista de empleados con correos repetidos
     * @return lista de empleados con correos repetidos
     */
    public Map<String, List<Empleado>> obtenerCorreosRepetidosArchivo()
    {
        // creamos un map con todos los correos y una lista de empleados que lo usan
        Map<String, List<Empleado>> correoListaEmpleados = listadoArchivo.stream().
                collect(Collectors.groupingBy(Empleado::obtenerCorreo));

        // creamos un predicado para quedarnos solo con los DNI que tengan mas de un empleado
        Predicate<Map.Entry<String, List<Empleado>>> condicion = correo -> correo.getValue().size() > 1;

        // nos quedamos con los dni que tienen mas de un empleado en su lista
        Map<String, List<Empleado>> correosRepetidosListaEmpleados = correoListaEmpleados.entrySet().stream()
                .filter(condicion).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // los sacamos por pantalla
        //correosRepetidosListaEmpleados.entrySet().stream().forEach(x-> x.getValue().forEach(emp -> System.out.println(emp.obtenerCorreo())));

        // devolvemos este ultimo map
        return correosRepetidosListaEmpleados;
    }

    /**
     * Comprueba si existen empledaos con correos repetidos
     * @return booleano true/false
     */
    public boolean hayCorreosRepetidosArchivo(){

        // almacenamos la cantidad de empleados antes de eliminar repetidos
        int numeroEmpleadosConRepetidos = obtenerNumeroEmpleadosArchivo();

        // Obtener los DNIs
        Stream<String> correosisponibles = listadoArchivo.stream().map(Empleado::obtenerCorreo);

        // Eliminamos los repetidos
        Long cantidadcorreosSinRepetir = correosisponibles.distinct().collect(Collectors.counting());

        // cantidad de correos no repetidos
        //System.out.println(cantidadcorreosSinRepetir);

        // comprobamos si hay DNIs repetidos o no
        if(cantidadcorreosSinRepetir < numeroEmpleadosConRepetidos)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return numerp de empleados que tienen el correo repetido
     */
    public int contarEmpleadosCorreosRepetidos (){
        Map<String, List<Empleado>> listaCorreosRepetidos = obtenerCorreosRepetidosArchivo();

        return listaCorreosRepetidos.entrySet().stream().mapToInt(entrada -> entrada.getValue().size()).sum();
    }

    /**
     * Se encarga de reparar los correos de los empleados que lo tengan repetido
     * @param listaRepeticion lista con los empleados con correo repetido
     */
    public void repararCorreosRepetidos(Map<String, List<Empleado>> listaRepeticion){
        listaRepeticion.entrySet().stream().forEach(correo -> {
            correo.getValue().forEach(empleado -> {
                int indiceEmpleadoListadoArchivo = listadoArchivo.indexOf(empleado);
                listadoArchivo.get(indiceEmpleadoListadoArchivo).generarCorreoCompleto();
            });
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metodo que una vez realizada la reparacion de todos los problemas
     * en cuanto a repeticion de DNIs y correos, inicializa el dato miembro
     * listado
     */
    public void validarListaArchivo()
    {
        listadoArchivo.stream().forEach(e -> listado.put(e.obtenerDni(),e));
        //listado.entrySet().stream().forEach(e -> System.out.println(e.getValue().obtenerCorreo()));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // PARTE 2

    /**
     * Metodo encargado de almacenar los datos en lineas para poder utilizar dicha informacion
     * a la hora de asignar sectores a los empleados.
     * @param archivo de datos de los sectores con sus empleados
     * @return cantidad de errores producidos al volcar los datos
     * @throws IOException
     */
    public long cargarArchivoAsignacionSector(String archivo) throws IOException {
        // creamos el flujo para procesar la lineas del archivo exceptuando la primera, que nos indica el sector
        List<String> lineas = Files.lines(Paths.get(archivo)).collect(Collectors.toList());

        // patron para partir el contenido de las lineas
        Pattern patron = Pattern.compile(" ");

        // almacenamos el sector correspondiente, que esta en la primera linea del archivo
        Sector sector = procesarNombreSector(lineas.get(0), patron);

        // procesamos la asignacion del sector y al mismo tiempo contamos los errores habidos
        long cantidadErrores=lineas.stream().skip(2).map(dni -> procesarAsignacionSector(dni, patron, sector)).filter(flag -> flag == false).count();

        return cantidadErrores;
    }

    /**
     * Se encarga de coger el nombre del sector que hay en el archivo y
     * gestionarlo con la clase Sector para que sea uno de ellos.
     * @param sec sector en forma de cadena
     * @param patronEspacios patron para eliminar espacios en blanco en caso de haberlos
     * @return un sector de la clase Sector
     */
    public Sector procesarNombreSector(String sec, Pattern patronEspacios)
    {
        // almacenamos el nombre del sector en una lista
        List<String> sector = patronEspacios.splitAsStream(sec).collect(Collectors.toList());

        // comprobamos la existencia de un sector con ese nombre
        Predicate<Sector> condicion = sect -> (sect.name().equals(sector.get(0)));

        // una vez nos hemos asegurado almacenamos el enumerado y lo devolvemos
        Sector sectorResultado = Arrays.stream(Sector.values()).filter(condicion).findFirst().get();

        return sectorResultado;
    }

    /**
     * Metodo encargado de asignar un sector a un empleado
     * @param dniConEspacios del empleado
     * @param patronEspacios para eliminar espacios en blanco
     * @param sector que se le asignara al empleado
     * @return booleano indicando si ha habido errores o no
     */
    public boolean procesarAsignacionSector(String dniConEspacios, Pattern patronEspacios, Sector sector)
    {
        // eliminamos los espacios si los hay de cada linea donde haya un dni
        List<String> dni = patronEspacios.splitAsStream(dniConEspacios).collect(Collectors.toList());
        boolean pertenece = false;

        // comprobamos si se encuentra en listado ese empleado
        if(listado.containsKey(dni.get(0)))
        {
            pertenece = true;

            // le asignamos el sector correspondiente
            listado.get(dni.get(0)).asignarSector(sector);
        }

        return pertenece;
    }


    /////////////////////////////////////////////////////////////////////////////////

    // Procesamiento de rutas

    /**
     * Metodo encargado de almacenar los datos en lineas para poder utilizar dicha informacion
     * a la hora de asignar rutas a los empleados.
     * @param archivo de datos de los rutas con sus empleados
     * @return cantidad de errores producidos al volcar los datos
     * @throws IOException
     */
    public long cargarArchivoAsignacionRuta(String archivo) throws IOException {
        // creamos el flujo para procesar la lineas del archivo exceptuando la primera, que nos indica la ruta
        List<String> lineas = Files.lines(Paths.get(archivo)).collect(Collectors.toList());

        // patron para partir el contenido de las lineas
        Pattern patron = Pattern.compile(" ");

        // almacenamos la ruta correspondiente, que esta en la primera linea del archivo
        Ruta ruta = procesarNombreRuta(lineas.get(0), patron);

        // procesamos la asignacion del sector y al mismo tiempo contamos los errores habidos
        long cantidadErrores=lineas.stream().skip(2).map(dni -> procesarAsignacionRuta(dni,patron, ruta)).filter(flag -> flag == false).count();

        return cantidadErrores;
    }

    /**
     * Se encarga de coger el nombre de la ruta que hay en el archivo y
     * gestionarlo con la clase Ruta para que sea uno de ellos.
     * @param rut sector en forma de cadena
     * @param patronEspacios patron para eliminar espacios en blanco en caso de haberlos
     * @return un sector de la clase Ruta
     */
    public Ruta procesarNombreRuta(String rut, Pattern patronEspacios)
    {
        // almacenamos el nombre del sector en una lista
        List<String> ruta = patronEspacios.splitAsStream(rut).collect(Collectors.toList());

        // comprobamos la existencia de un sector con ese nombre
        Predicate<Ruta> condicion = ru -> (ru.name().equals(ruta.get(0)));

        // una vez nos hemos asegurado almacenamos el enumerado y lo devolvemos
        Ruta rutaResultado = Arrays.stream(Ruta.values()).filter(condicion).findFirst().get();

        return Ruta.valueOf(ruta.get(0));
    }

    /**
     * Metodo encargado de asignar una ruta a un empleado
     * @param dniConEspacios del empleado
     * @param patronEspacios para eliminar espacios en blanco
     * @param ruta que se le asignara al empleado
     * @return booleano indicando si ha habido errores o no
     */
    public boolean procesarAsignacionRuta(String dniConEspacios, Pattern patronEspacios, Ruta ruta)
    {
        // eliminamos los espacios si los hay de cada linea donde haya un dni
        List<String> dni = patronEspacios.splitAsStream(dniConEspacios).collect(Collectors.toList());
        boolean pertenece = false;

        // comprobamos si se encuentra en listado ese empleado
        if(listado.containsKey(dni.get(0)))
        {
            pertenece = true;

            // le asignamos el sector correspondiente
            listado.get(dni.get(0)).asignarRuta(ruta);
        }

        return pertenece;
    }

    /////////////////////////////////////////////////////////////////////////////////

    // Procesamiento de sectores Y ruta

    /**
     * se encarga de recoger en un contenedor la cantidad de empleados que pertenecen a cada
     * ruta y a su vez, dividirlo por sectores.
     * @return map con la cantidad de empleados de cada ruta y cada sector
     */
    public Map<Sector, Map<Ruta,Long>> obtenerContadoresSectorRuta()
    {
        //Para cada sector obtenemos los contadores de ruta y recogemos el
        //resultado en el map de salida
        return Arrays.stream(Sector.values()).
                sorted(Comparator.comparing(Sector::ordinal)).
                collect(Collectors.toMap(sector -> sector, this::obtenerContadoresRuta));
    }

    /**
     * Se encarga de contar los empleados que hay en cada ruta de cierto sector
     * @param sector indicado para el que deseamos conocer la cantidad de empleados por ruta
     * @return map concada una de las rutas y sus empleados asignados(en cantidad)
     */
    public Map<Ruta, Long> obtenerContadoresRuta (Sector sector){
        return listado.values().stream().
                filter(empleado -> empleado.obtenerSector() == sector).
                sorted(Comparator.comparing(Empleado::obtenerRuta)).
                collect(Collectors.groupingBy (Empleado::obtenerRuta,
                        LinkedHashMap::new,
                        Collectors.mapping(Empleado::obtenerRuta, Collectors.counting())));
    }

    /**
     * para cada sector devuelve un numero de empleados asignados
     * @return lista con los empleados asignados por sector
     */
    public List<Long> obtenerContadoresSectores (){

        return obtenerContadoresSectorRuta().values().stream().
                map(Map::values).
                map(valor -> valor.stream().reduce((long) 0, Long::sum)).
                collect(Collectors.toList());
    }

    /**
     * realiza un filtrado de empleados en listado segun la condicion que reciba
     * @param condicion condicion para el filtrado
     * @return lista con empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosCondicion(Predicate<Empleado> condicion)
    {
        List<Empleado> listaEmpleados = listado.values().stream()
                .filter(condicion)
                .collect(Collectors.toList());

        return listaEmpleados;
    }

    /**
     * crea una condicion para empleados sin sector y sin ruta
     * y se la manda al metodo buscarEmpleadosCondicion
     * @return lista con los empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosSinSectorSinRuta()
    {
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSector() == Sector.NOSECTOR && empleado.obtenerRuta() == Ruta.NORUTA);
        return buscarEmpleadosCondicion(condicion);
    }

    /**
     * crea una condicion para empleados sin ruta
     * y se la manda al metodo buscarEmpleadosCondicion
     * @return lista con los empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosSinRuta(Sector sec)
    {
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSector() == sec) && (empleado.obtenerRuta() == Ruta.NORUTA);
        return buscarEmpleadosCondicion(condicion);
    }

    /**
     * crea una condicion para empleados con sector y sin ruta
     * y se la manda al metodo buscarEmpleadosCondicion
     * @return lista con los empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosConSectorSinRuta()
    {
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSector() != Sector.NOSECTOR && empleado.obtenerRuta() == Ruta.NORUTA);
        return buscarEmpleadosCondicion(condicion);
    }

    /**
     * crea una condicion para empleados sin sector
     * y se la manda al metodo buscarEmpleadosCondicion
     * @return lista con los empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosSinSector(Ruta ruta)
    {
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSector() == Sector.NOSECTOR
                && empleado.obtenerRuta() == ruta);
        return buscarEmpleadosCondicion(condicion);
    }

    /**
     * crea una condicion para empleados sin sector y con ruta
     * y se la manda al metodo buscarEmpleadosCondicion
     * @return lista con los empleados que cumplen dicha condicion
     */
    public List<Empleado> buscarEmpleadosSinSectorConRuta()
    {
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSector() == Sector.NOSECTOR && empleado.obtenerRuta() != Ruta.NORUTA);
        return buscarEmpleadosCondicion(condicion);
    }



}
