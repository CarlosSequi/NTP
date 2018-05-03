import org.scalacheck.Properties
import org.scalacheck.Prop.{forAll, throws, AnyOperators}
import org.scalacheck.Gen._

object ListaTest extends Properties("ListaTest"){

  // Metodo de generacion de listas de valores enteros
  val secuenciaEnteros = listOf(choose(0,10))

  // LAS SIGUIENTES PROPERTIES SON PARA COMPROBAR QUE LOS METODOS
  // CREADOS EN LA CLASE LISTA FUNCIONAN BIEN

  property("longitud de lista") =
    forAll(secuenciaEnteros) {
      xs => {
        // creamos lista a partir de xs
        val lista : Lista[Int] = Lista(xs : _*)
        val longitudList = xs.length
        val longitudLista = Lista.longitud(lista)
        // con ?= hacemos lo mismo que con == solo que muestra info de depuracion
        // en caso de que no se cumpla la igualdad
        longitudList ?= longitudLista
      }
    }

  property("suma de enteros") =
    forAll(secuenciaEnteros) {
      xs => {
        // objeto a partir de la clase Lista
        val lista:Lista[Int] = Lista(xs :_*)
        val sumaList = xs.map(x => x.toDouble).sum
        val sumaLista = Lista.sumaEnteros(lista)

        // se comprueba la igualdad
        sumaList ?= sumaLista
      }
    }

}
