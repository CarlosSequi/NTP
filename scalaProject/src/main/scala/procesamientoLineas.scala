import scala.io.Source

object procesamientoLineas {

  // Version 1: procesar lineas y para cada linea escribir longitud
  //            y contenido

  def imprimirLineasV1(nombreArchivo : String) : Unit = {
    // obtengo ibjetos de la clase String para cada linea
    val lineas = Source.fromFile(nombreArchivo).getLines().toList

    // Procesamos cada linea
    for(linea <- lineas)
      println(linea.length + " | "  + linea)
  }


  // Version 2: mantener sangrado, usando para ello un doble procesamiento
  //            de lineas; determinar el numero de caracteres necesarios
  //            la longitud.
  //            Escribir el contenido teniendo en cuenta esa longitud.

  def calcularAnchoTamlinea(linea : String) = linea.length.toString.length

  def imprimirLineasV2(nombreArchivo : String) : Unit = {
    // obtengo ibjetos de la clase String para cada linea
    val lineas = Source.fromFile(nombreArchivo).getLines().toList

    // Primera pasada sobre lineas para determinar la mas larga
    var maximoAnchoTam = 0

    // calculamos el tamaño maximo de numero de caracteres
    // la funcion max calcula el maximo entre maximoAnchoTam y lo que nos devuelva la funcion calcularAnchoTamLinea
    // para cada una de las lineas
    for(linea <- lineas)
      maximoAnchoTam = maximoAnchoTam.max(calcularAnchoTamlinea(linea))

    // otra forma de implementarlo
    val maximoAnchoTam2 = lineas.map(linea => calcularAnchoTamlinea(linea)).max

    // procesamiento para imprimir el contenido de las lineas ç
    for(linea <- lineas)
    {
      val tamLinea = calcularAnchoTamlinea(linea)
      val relleno = " "*(maximoAnchoTam2-tamLinea)
      println(relleno + linea.length + " | " + linea)
    }
  }

  // Version 3: obtener la linea mas larga y calcular a partir de ahi el
  //            numero de caracteres para la longitud.

  def imprimirLineasV3(nombreArchivo : String) : Unit = {

    // obtengo ibjetos de la clase String para cada linea
    val lineas = Source.fromFile(nombreArchivo).getLines().toList

    // obtengo la linea de mayor longitud
    val  lineaMasLarga = lineas.reduceLeft((a,b) => if(a.length > b.length) a else b)

    // calculamos el maximo ancho
    val maximoAnchoTam = calcularAnchoTamlinea(lineaMasLarga)

    for(linea <- lineas)
    {
      val tamLinea = calcularAnchoTamlinea(linea)
      val relleno = " "*(maximoAnchoTam - tamLinea)
      println(relleno + linea.length + " | " + linea)
    }
  }

  
  def main(args : Array[String]) : Unit = {

    if(args.length == 0){
      println("Uso de programa debe incluir nombre de archivo")
    }
    else{
      // println("--------------------- Version 1 ---------------------")
      // imprimirLineasV1(args(0))
      // println("-----------------------------------------------------")

      // println("--------------------- Version 2 ---------------------")
      // imprimirLineasV2(args(0))
      // println("-----------------------------------------------------")

       println("--------------------- Version 3 ---------------------")
       imprimirLineasV3(args(0))
       println("-----------------------------------------------------")
    }

  }

}
