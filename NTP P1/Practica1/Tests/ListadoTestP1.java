
import listado.Empleado;
import listado.Sector;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import listado.ListadoEmpleados;
import listado.Ruta;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Práctica 1 NTP
 */
public class ListadoTestP1 {
   private static ListadoEmpleados listado;

   /**
    * Codigo a ejecutar antes de realizar las llamadas a los métodos
    * de la clase; incluso antes de la propia instanciación de la
    * clase. Por eso el método debe ser estatico
    */
   @BeforeClass
   public static void inicializacion() {
      System.out.println("Metodo inicializacion conjunto pruebas");
      // Se genera el listado de empleados
      try {
         listado = new ListadoEmpleados("./datos/datos.txt");
      } catch (IOException e) {
         System.out.println("Error en lectura de archivo de datos");
      };
   }

   /**
    * Test para comprobar que se ha leido de forma correcta la
    * informacion de los empleados
    *
    * @throws Exception
    */
   @Test
   public void testConstruccionListado() throws Exception {
      assert (listado.obtenerNumeroEmpleadosArchivo() == 5000);
   }

   /**
    * Test para comprobar que se ha detectado bien la cantidad de empleados
    * que tienen el DNI repetido
    */
   @Test
   public void testCantidadDNIRepetidos(){
      assert (listado.contarEmpleadosDnisRepetidos() == 4);
   }

   /**
    * Test para comprobar que se ha detectado bien la cantidad de empleados
    * que tienen el correo repetido
    */
   @Test
   public void testCantidadCorreosRepetidos(){
      assert (listado.contarEmpleadosCorreosRepetidos() == 315);
   }

   /**
    * Test para comprobar que funciona bien la reparacion de DNIs
    */
   @Test
   public void testComprobarRepararDnisRepetidos(){
      boolean hayRepetidosInicialmente = listado.hayDnisRepetidosArchivo() == true;
      Map<String, List<Empleado>> listaDnisRepetidos = listado.obtenerDnisRepetidosArchivo();
      listado.repararDnisRepetidos(listaDnisRepetidos);
      assert (listado.hayDnisRepetidosArchivo() == false && hayRepetidosInicialmente);
   }

   /**
    * Test para comprobar que funciona bien la reparacion de correos
    */
   @Test
   public void testComprobarRepararCorreosRepetidos(){
      boolean hayRepetidosInicialmente = listado.hayCorreosRepetidosArchivo() == true;
      Map<String, List<Empleado>> listaCorreosRepetidos = listado.obtenerCorreosRepetidosArchivo();
      listado.repararCorreosRepetidos(listaCorreosRepetidos);
      assert (listado.hayCorreosRepetidosArchivo() == false && hayRepetidosInicialmente);
   }

}