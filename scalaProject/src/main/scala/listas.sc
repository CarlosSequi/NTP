val lista = List(1,2,3) // la coleccion es inmutable y ademas
                        // lista no puedeapuntar a otro sitio ahora

val lista2 = List (4,5)

val lista3 = lista ::: lista2 // concatena ambas listas
                              // el metodo de concatenado se esta
                              // llamando sobre lista 2

val lista4 = 0 :: lista // a lista le pone un 0 por delante

val mayor2 = lista.count(v => v>2) // cuenta aquellos elementos de lista4
                                   // que son mayores que 2

val mayor3 = lista.count(_ > 2) // hace lo mismo que mayor2

lista.drop(2) // elimina los dos elementos del principio
lista.dropRight(2) // elimina los dos elemendos de la derecha (del final)
lista.dropWhile(_ < 2) // elimina mientras se cumpla la condicion
lista.length // length es una funcion, pero se llaman sin parentesis
lista.foreach(println)

///////////////////////////////////////////////////////////////////////


