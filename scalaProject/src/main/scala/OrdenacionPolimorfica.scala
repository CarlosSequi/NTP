object OrdenacionPolimorfica{

  def ordenado[A](as:Array[A], criterio:(A,A) => Boolean) : Boolean = {
    @annotation.tailrec
    def go(i:Int): Boolean =
    {
      // caso base 1: tratamos con los dos ultimos elementos
      if(i == as.length-2) criterio(as(i), as(i+1))
      // caso base 2: los elementos a comparar no cumplen el criterio
      else if(criterio(as(i), as(i+1)) == false) false
      // caso inductivo
      else go(i+1)
    }
    go(0)
  }

  def ordenadoV2[A](as:Array[A])(criterio : (A,A) => Boolean) : Boolean = {
    @annotation.tailrec
    def go(i:Int): Boolean =
    {
      // caso base 1: tratamos con los dos ultimos elementos
      if(i == as.length-2) criterio(as(i), as(i+1))
      // caso base 2: los elementos a comparar no cumplen el criterio
      else if(criterio(as(i), as(i+1)) == false) false
      // caso inductivo
      else go(i+1)
    }
    go(0)
  }

  def main(args : Array[String]) : Unit = {
    val array1 : Array[Int] = Array(1, 5, 20, 25, 30, 35, 40)
    val array2 : Array[Int] = Array(40, 35, 30, 25, 20, 5, 1)

    val res1 = OrdenacionPolimorfica.ordenado(array1, (x:Int, y:Int) => x < y)
    println("Ordenacion creciente de array1: " + res1)

    val res2 = OrdenacionPolimorfica.ordenado(array2, (x:Int, y:Int) => x < y)
    println("Ordenacion creciente de array2: " + res2)

    // esto lo podemos hacer gracias a la separacion en listas de argumentos de ordenadoV2
    val res3 = OrdenacionPolimorfica.ordenadoV2(array1)(_>_)
  }

}
