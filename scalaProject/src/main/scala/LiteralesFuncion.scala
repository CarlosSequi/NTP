object LiteralesFuncion extends App {

  // Creacion de literal tipo funcion
  val multiplicarPor2 = (x:Int) => x*2

  // Los literales pueden asignarse directamente
  val variableFuncion = multiplicarPor2

  // Las dos variables pueden usarse para ejecutar
  val res1 = multiplicarPor2(2)
  val res2 = variableFuncion(2)
  println("res1: " + res1 + " res2: " + res2)

  // Definimos una funcion por el mecanismo clasico
  def calcularMaximo(a:Int, b:Int) : Int = if(a>b) a else b

  // Esta funcion tambien puede asignarse a una variables
  // pero no como si fuera un literal: usando un subrayado o bien
  // especificando el tipo de la funcion
  val variableFuncion2 : (Int, Int) => Int = calcularMaximo
  val variableFuncion3 = calcularMaximo _

  // las dos variables son utilizables sin problema
  println("maximo entre 3 y 5 con variableFuncion2: " + variableFuncion2(3,5))
  println("maximo entre 3 y 5 con variableFuncion3: " + variableFuncion3(3,5))

  // creacion de literales tipo funcion sin argumentos
  val saluda = () => "Hola, mundo"
  println(saluda())
}
