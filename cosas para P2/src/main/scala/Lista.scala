/**
  * Interfaz generica para una lista
  */
// sealed = clase sellada
// trait = interfaz
sealed trait Lista[+A]

// clases y objetos case
// este me va a representar la lista vacia
case object Nil extends Lista[Nothing]

// definimos la lista con elementos
case class Constructor[+A](cabeza : A, cola : Lista[A]) extends  Lista[A]

// para definir los metodos lo hacemos como si los creasemos estaticos:
// los vamos a meter todos dentro del siguiente objeto
object Lista{
  def apply[A](elementos:A*) : Lista[A] = {
    if(elementos.isEmpty) Nil
    else Constructor(elementos.head, apply(elementos.tail : _*))
  }

  def longitud[A](lista : Lista[A]) : Int = {
    // dentro de este match utilizamos los casos que hemos definido arriba, Nil y Constructor
    // es decir, arriba hemos creado dos tipos de estructuras, en caso de que lista tenga la estructura
    // de Nil, devolvera longitud 0, en caso de que coincida con la estructura de Constructor, devolvera
    // su tamaño
    lista match {
      case Nil => 0
      case Constructor(cabeza, cola) => 1 + longitud(cola)
    }
  }


  def sumaEnteros(enteros:Lista[Int]) : Double = enteros match {
    case Nil => 0.0
    case Constructor(cabeza, cola) => cabeza.toDouble + sumaEnteros(cola)
  }
}

object Prueba extends App {
  val lista1 = Lista(1,2,3,4,5,6,7)
  // si no tuviesemos el metodo apply de la clase Llista tendriamos que
  // construit un objeto de la siguiente forma
  val lista2 = Constructor(1, Constructor(2, Constructor(3, Nil)))
}