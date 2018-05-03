import scala.annotation.tailrec

object Ejercicio1  extends App {

  // definir funcion identidad que recibe un entero y lo devuelve
  def identidad(x:Int) = x

  // funcion para calcular cuadrado de un valor
  def cuadrado(x:Int) = x*x

  //funcion para calcular la potencia 2 de un numero
  def potencia2(x:Int) :Int = {
    if(x==0) 1
    else 2*potencia2(x-1)
  }

  // lo mismo pero mediante tail recursive
  def potencia2TR(x:Int) :Int = {
    @annotation.tailrec
    def go(x : Int, acumulador : Int) : Int = {
      if(x==0) acumulador
      else go(x-1, 2*acumulador)
    }
    go(x,1)
  }


  // sumatorio con tail recursive
  def sumatorioEnterosTR(a:Int, b:Int) :Int = {
    @annotation.tailrec
    def go(a:Int, acum:Int) : Int = {
      if(a>b) acum
      else go(a+1, a+acum)
    }
    go(a, 0)
  }

  // calculamos la sumatoria de dos numeros en el intervalo que forman
  def sumatorioEnteros(a:Int, b:Int) : Int =
  {
    if(a>b) 0
    else a + sumatorioEnteros(a+1, b)
  }

  def sumatorioCuadrados(a:Int, b:Int) : Int =
  {
    if(a>b) 0
    else cuadrado(a) + sumatorioCuadrados(a+1,b)
  }

  def sumatorioPotencia2(a:Int, b:Int) : Int =
  {
    if(a>b) 0
    else potencia2TR(a) + sumatorioPotencia2(a+1,b)
  }

 // como estas tres ultimas son iguales, vamos a resumirlas en una sola
 // que reciba la funcion a usar
  def sumatorio(funcion: Int => Int, a: Int, b:Int) : Int = {
   if(a>b) 0
   else funcion(a) + sumatorio(funcion, a+1, b)
 }

  // sumatorio enteros usando estta ultima funcion
  def sumatorioEnterosV2(a:Int, b:Int) : Int = sumatorio(identidad, a, b)
  def sumatorioCuadradosV2(a:Int, b:Int) : Int = sumatorio(cuadrado, a, b)
  def sumatorioPotencias2V2(a:Int, b:Int) : Int = sumatorio(potencia2TR, a, b)

  // funcion que devuelve una funcion una vez especificada la funcion que se quiere aplicar a cada elemento
  def sumatorioGeneral(funcion: Int => Int) : (Int, Int) => Int ={
    def sumaConLimite(a:Int, b:Int) : Int = {
      if(a>b) 0
      else funcion(a) + sumaConLimite(a+1, b)
    }
    sumaConLimite
  }

  // ahora utilizamos dicha funcion
  def sumatorioV3 = sumatorioGeneral(identidad)
  println(sumatorioV3(3,5))

  // lo mismo pero con listas de argumentos
  def sumatorioGeneral2(a:Int, b:Int)(f: Int => Int) : Int = {
    if(a>b) 0
    else f(a) + sumatorioGeneral2(a+1,b)(f)
  }
  // lo usamos
  val resultado5 = sumatorioGeneral2(1,10)(identidad)


  // la implementamos tail recursive
  def sumatorioGeneral2TR(a:Int, b:Int)(f: Int => Int) : Int = {
    def go(a:Int, acum:Int): Int=
    {
      if(a>b) 0
      else go(a+1, acum+f(a))
    }
    go(a,0)
  }

}
