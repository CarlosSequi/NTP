object Fibonacci extends App {

  def fibonacci(n : Long) : Long = {
    if(n == 0 || n==1) n
    else fibonacci(n-1) + fibonacci(n-2)
  }

  def fibonacciTR(n : Long) : Long = {
    @annotation.tailrec
    def go(n:Long, previo:Long, actual:Long) : Long = {
      if(n == 0) previo
      else go(n-1, actual, actual+previo)
    }
    go(n,0,1)
  }

  // generamos el termino n=20 y calculamos el tiempo que tardaen ejecutarlo
  val tiempoInicio = System.nanoTime()
  val res2 = fibonacci(20)
  val tiempoFinal = System.nanoTime()
  println("Valor calculado: " + res2)
  println("Tiempo ejecucion normal: " + (tiempoFinal - tiempoInicio))

  // generamos el termino n=20 y calculamos el tiempo que tardaen ejecutarlo. Esta vez tail recursive
  val tiempoInicio2 = System.nanoTime()
  val res3 = fibonacciTR(20)
  val tiempoFinal2 = System.nanoTime()
  println("Valor calculado: " + res3)
  println("Tiempo ejecucion normal: " + (tiempoFinal2 - tiempoInicio2))

}
