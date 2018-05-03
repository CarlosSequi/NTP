object BusquedaBinaria extends App {

  /**
    * busca un elemento y devuelve su indice en caso de encontrarlo, -1 en caso de no encontrarlo
    * va dividiendo el vector en 2 en funcion de si esta por encima de la mitad o por debajo
    * @param coleccion
    * @param aBuscar
    * @param mayorQue
    * @tparam A
    * @return
    */
  def busquedaBinaria [A] (coleccion : Array[A], aBuscar: A, mayorQue : (A,A) => Boolean): Int = {
    def go(izquierda:Int, derecha:Int) : Int = {

    }
    go(0, coleccion.length-1)
  }

}
