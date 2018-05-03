var i:Int=10
while( i > 0)
{
  println("Valor de i " + i)
  i=i+1
}
///////////////////////////////////////////////////////////////////////
i=5
do
{
  println("Valor de i " + i)
  i=i+1
}while(i >= 0)
///////////////////////////////////////////////////////////////////////
val x = 1 to 10 // incluye al 10
val y = 1 until 10 // excluye al 10
val z = 1 to 10 by 2 // de 2 en 2
val h = 10 to 1 by -2 // decrementando de 2 en 2
///////////////////////////////////////////////////////////////////////
for(i <- 1 to 10 by 4)
{
  println("Valor de i " + i)
}
///////////////////////////////////////////////////////////////////////
for(i <- 1 to 10)yield i // yield hace que el for devuelva un i
                         // en cada una de las iteraciones
///////////////////////////////////////////////////////////////////////
for(i <- 1 to 10;
    j <- 2 to 5) yield (i,j) // devuelve este par de valores en cada it.
///////////////////////////////////////////////////////////////////////
// para no utilizar punto y coma podemos hacer lo mismo asi
// pero hay que ponerlos en distintas lineas, sino sale error
for{i <- 1 to 10
    j <- 2 to 5} yield (i,j)
///////////////////////////////////////////////////////////////////////
var saludos = new Array[String](3)
saludos(0)="Hola"
saludos(1)=","
saludos(2)=" mundo"

for(i <- 0 until saludos.length)
  println(saludos(i))