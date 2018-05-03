val x=10
val y=30

val maximo = x>y match{
  case true => x
  case false => y
}

val error = 500

val mensaje = error match{
  case 200 => "OK"
  case 400 => {
               println("Error de ejecucion")
               "Error 400"
              }
  case 500 => {
                println("Error sintactico")
                "Error 500"
              }
}

// en el ultimo caso hacemos que cualquier otra cosa que no sea un dia
// de la semana se convierta en un error
val dia = "lunes"
val laborable = dia match
{
  case "lunes" | "martes" | "miercoles" | "jueves" | "viernes" => "laborable"
  case "sabado" | "domingo"  => "festivo"
  case otraCosa =>
    {
      println(s"Cadena introducida: $otraCosa")
    }
    "ERROR"
}


val respuesta = null
val mensajeRespuesta = respuesta match
{
  case s if s != null => "Cadena no nula"
  case s => "Cadena nula"
}

val a:Float = 12.345f
val b:Any = a
val respuesta2 = b match
{
  case z:String => s"$z - String"
  case z:Double => f"$z%2f - Double"
  case z:Float => f"$z%2f - Float"
  case z:Long => f"${z} - Long"
  case z:Int => f"${z} - Int"
}
