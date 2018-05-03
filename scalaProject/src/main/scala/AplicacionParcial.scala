object AplicacionParcial extends App {

  // definimos una funcion para determinar divisibilidad
  def divisible(x:Int, y:Int) = x%y == 0

  // podemos definir una variable sin indicar argumentos
  val f = divisible _

  // aplicacion parcial: crear una funcion para comprobar divisibilidad por 3
  val divisiblePor3 = divisible(_:Int,3)
  val resultado1 = divisiblePor3(27)
  println("resultado2: " + resultado1)

  // mas claro con varias listas de argumentos
  def divisiblePor(x:Int)(y:Int) = x%y == 0

  val dibisible3V2 = divisiblePor(_:Int)(3)
  val divisibilidad100 = divisiblePor(100)(_:Int)


}
