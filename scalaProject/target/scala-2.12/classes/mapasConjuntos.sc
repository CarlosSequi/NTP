var ciudades=Set("Granada", "Jaen", "Malaga")

// añadimos un objeto
ciudades += "Madrid"
println(ciudades)

// todo lo que haya de tipo Set a partir de este import sera mutable
// aunque sea una variable de tipo val(no mmutable)
import scala.collection.mutable.Set

val asignaturas=Set("Matematicas", "Fisica")
asignaturas += "Informatica"
println(asignaturas)

import scala.collection.mutable.HashSet
val conjunto = HashSet("1","2")
val dias = Map(1->"Lunes", 2->"Martes")
println(dias)

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

