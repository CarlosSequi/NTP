object MarcadorPosicion extends App{

  // Es posible crear literales tipo funcion dejando argumentos libres
  val multiplicarPor2 : Int => Int = _ * 2
  val multiplicarpor2Extendido = (x:Int) => x*2

  // la variable multiplicarPor2 puede usarse asignando el valor al argumento
  val resultado1 = multiplicarPor2(3)

  // tenemos funcion para operacion con cadenas
  def operacionCadenas(cadena:String, operacion:String => String):String = {
    if(cadena != null) operacion(cadena)
    else cadena
  }

  val resultado2 = operacionCadenas(cadena = "Hola, mundo", x => x.reverse)
  val resultado3 = operacionCadenas(cadena = "Hola, mundo", _.reverse)
  println("resultado2: " + resultado2)
  println("resultado3: " + resultado3)

  // el marcador tambien puede usarse en funciones de varios argumentos
  def aplicacionFuncion(x: Int, y:Int, funcion : (Int, Int) => Int) = funcion(x,y)

  // se usa mediante el marcador de posicion
  val resultado4 = aplicacionFuncion(12, 3, (x:Int, y:Int) => x*y)
  val resultado5 = aplicacionFuncion(12, 3, _*_)

  println("resultado4: " + resultado4)
  println("resultado5: " + resultado5)


}
