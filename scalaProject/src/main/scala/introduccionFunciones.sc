// Formas de declarar una funcion
def saludo = "hola"
saludo

def saludo2() = "hola2"
saludo2()

def saludo3() : String = "hola3"
saludo3

def multiplicar(x:Int, y:Int) : Int = x*y
multiplicar(3,5)

// no es necesario poner el tipo de dato que devuelve la funcion
def multiplicar2(x:Int, y:Int) = x*y
multiplicar(2,8)

// la palabra return la usamos para forzar la salida de una funcion
def quitarBlancosIniciales(s:String) : String = {
  if(s == null) return null
  s.trim() // la funcion trim quita los blancos iniciales
           // solo si s no es null, ya que si lo fuese no llegaria
           // hasta aqui.
}

// Funciones que no devuelven nada son los llamados procedimientos
// println no devuelve nada
def imprimirMensaje(s:String) = {
  println(s)
}

def mostrarValor(x:Int) = {
  println(x)
}

mostrarValor(3+3*2)

// cuando haya efectos laterales por convenio se utilizan
// los parentesis aunque no se reciban argumentos
// efectos laterales: escribir en archivos o por pantalla
def mostrarError() = {
  println("Hubo error de ejecucion")
}