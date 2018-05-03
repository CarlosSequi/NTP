// Cuando una clase extiende de App hereda el main y no es necesario declararlo
object Factorial extends App{

  // Declaracion de la funcion factorial
  // en las funciones recursivas es obligatorio indicar el tipo de dato a devolver
  def factorial(x : Int) : Int = {
    if(x == 0) return 1
    else x * factorial(x-1)
  }

  val fac15=factorial(15)
  println("Valor de factorial: " + fac15)

  // funciones tail recursive para evitar el uso costoso de la pila
  // en la anterior no se puede porque estamos devolviendo justo
  // la operacion de multiplicacion, para poder hacerla tail recursive
  // es necesario hacer una acumulacion
  @annotation.tailrec // con esta anotacion aseguramos que es tail recursive, si no lo fuese daria error
  def factorialTR(x:Int, acum:Int) : Int = {
    if(x==0 || x==1) acum
    else factorialTR(x-1, acum*x)
  }

  val fact15TR=factorialTR(15,1)
  println("Valor del factorial con TR: " + fact15TR)

  // definicion natural de factorial con tail recursive
  // para poder usarla de forma natural, sin tener que pasarle a la funcion
  // el valor inicial del acumulador
  def factorialTR(x:BigInt) : BigInt = {
    @annotation.tailrec
    def auxiliar(x:BigInt, acum : BigInt) : BigInt = {
      if(x==0 || x==1) acum
      else auxiliar(x-1, x*acum)
    }
    auxiliar(x, 1)
  }

  println("El factorial de 30 es: " + factorialTR(30))

}
