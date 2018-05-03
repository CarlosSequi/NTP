object TiposFunciones extends App {

  def duplicar(x:Int) = x*2
  val resultado=duplicar(20)
  val funcion : (Int) => Int = duplicar
  val otra=funcion
  val funcion2 = duplicar _ // dejamos sin especificar los argumentos para indicar que no estamos llamando a la funcion
                            // sino  que estamos asignandosela a funcion2

  println(funcion2(100))

  def calcularMaximo(a:Int, b:Int) = if(a>b) a else b
  val funcion3 = calcularMaximo _
  val funcion4 :(Int, Int) => Int = calcularMaximo

  funcion3(20,40)

}
