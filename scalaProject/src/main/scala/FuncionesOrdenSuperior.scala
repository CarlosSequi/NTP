object FuncionesOrdenSuperior extends App {

  // es de orden superior porque tiene la operacion a realizar como argumento, una funcion
  def operacionSeguraString(cadena : String, operacion : String => String) = {
    if(cadena != null) operacion(cadena)
    else cadena
  }

  def invertirCadena(cadena : String) = cadena.reverse
  def aMayuscula(cadena : String) = cadena.toUpperCase

  val res1 = operacionSeguraString("Hola, Pepe", invertirCadena)
  println(res1)
  val res2 = operacionSeguraString(res1, aMayuscula)
  println(res2)

  // Composicionde funciones
  def componer (f : Double => Double, g : Double => Double) : Double => Double = {
    x => f(g(x))
  }

  def mas5(x:Double) = x+5
  def cuadrado(x:Double) = x*x
  val fg = componer(mas5, cuadrado)

  val valor = fg(5)

  println(valor)

}
