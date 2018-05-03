object factorialConValorPorDefecto extends App {

  // uso de parametros por defecto
  def factorialTR(x:BigInt, acum:BigInt=1) : BigInt = {
    if(x==0 || x==1) acum
    else factorialTR(x-1, x*acum)
  }

  println("Factorial de 20: " + factorialTR(20))

  // podemos no seguir el orden deargumentos definido en la funcioni simplemente usando sus nombres
  // a la hora de llamar a la funcion
  val fact30 = factorialTR(acum=1, x=30)

}
