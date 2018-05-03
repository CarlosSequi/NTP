object MetodosGenericos extends App {

  // metodo parametrizado con A
  // este en concreto devuelve todo menos el primer elemento de la lista
  def eliminarPrimero [A] (lista : List[A]) : List[A] = lista.tail

  val lista1 = eliminarPrimero(List(1,2,3,4))
  println(lista1)
  val lista2=eliminarPrimero(List('a','b','c'))
  val lista3=eliminarPrimero(List("hola","amigo","adios"))

}
