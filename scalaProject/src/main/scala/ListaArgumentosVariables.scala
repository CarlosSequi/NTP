object ListaArgumentosVariables extends App{

  def sumar(numeros:Int *) : Int = {
    var total=0
    for(i <- numeros) total += i
    total
  }

  println("Suma de un valor: " + sumar(1))
  println("Suma de 2 valores: " + sumar(1,2))
  println("Suma de varios valores: " + sumar(1,3,5,6))

  // funciones con varias listas de argumentos
  def multiplicar(x:Int)(y:Int) = x*y
  val multiplicarPor3 = multiplicar(3)_ // almacenamos en multiplicarPor3 -> 3*y
                                        // con la barra baja final indica que lo dejamos sin indicar
  val resultado = multiplicarPor3(4)
  println("Multiplicacion de 3 por 4: " + resultado)

}
