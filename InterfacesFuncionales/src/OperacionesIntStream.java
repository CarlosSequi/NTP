import java.util.OptionalInt;
import java.util.Random;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


public class OperacionesIntStream {

    private int valores[];

    public OperacionesIntStream(int numeroValores){
        valores = new int[numeroValores];
        Random generador = new Random();
        for(int i=0; i < numeroValores; ++i){
            valores[i] = generador.nextInt(101);
        }
    }

    // Método para mostrar los valores (paradigma imperativo)
    public void mostrarValoresImperativo(){
        for(int i=0; i < valores.length; ++i){
            System.out.printf("%d", valores[i]);
        }
        System.out.println();
    }

    // Método para mostrar valores (paradigma funcional)
    public void mostrarValoresFuncional(){
        // Definir el flujo, y con el método "of" se indica sobre qué colección se genera
        IntStream flujo = IntStream.of(valores);

        // Definir la operación a realizar
        IntConsumer operacion = valor -> System.out.printf("%d", valor);

        // Se realiza la operación
        flujo.forEach(operacion);

        System.out.println();
    }

    // Otra forma más directa de definir la función anterior según el paradigma funcional
    public void mostrarValoresFuncional2(){
        IntStream.of(valores).forEach(valor -> System.out.printf("%d", valor));
        System.out.println();
    }

    public void obtenerMinimo(){
        // El tipo OptionalInt actúa como wrapper para el caso en el que la colección esté vacía
        OptionalInt minimo = IntStream.of(valores).min();
        int entero_minimo = minimo.getAsInt();
    }

    public void obtenerMaximo(){
        // El tipo OptionalInt actúa como wrapper para el caso en el que la colección esté vacía
        int maximo = IntStream.of(valores).max().getAsInt();
        System.out.println("Maximo: "+ maximo);
    }

    public void obtenerSuma(){
        System.out.println();
        int suma = IntStream.of(valores).sum();
        System.out.println("Suma: " + suma);
    }

    public void obtenerSumaReduce(){
        // el 0 es el elemento neutro para la suma (para la multiplicacion por ejemplo seria un 1)
        // la primera operacion que hara sera 0 + X, luego el resultado de eso + y, asi sucesivamente
        System.out.println();
        IntStream.of(valores).reduce(0, (x,y) ->
        {
            System.out.println("Valores de x e y: " + x + " " + y);
            return (x + y);
        });
    }

    public void obtenerProductoReduce(){
        System.out.println();
        int producto = IntStream.of(valores).reduce(1, (x,y) -> x*y);
        System.out.println("Producto: "+producto);
    }

    public void obtenerProductoAmpliado(){
        System.out.println();
        double producto = IntStream.of(valores).asDoubleStream().reduce(1,(x,y) -> x*y);
        System.out.println("Producto: "+producto);
    }

    public void sumaValoresCuadrados(){
        System.out.println();
        double sumaCuadrados = IntStream.of(valores).asDoubleStream().reduce(0,(x,y) -> x+y*y);
        System.out.println("Suma de cuadrados: "+sumaCuadrados);
    }

    public double obtenerMedia(){
        return(IntStream.of(valores).average().getAsDouble());
    }

    // Filtrado de valores pares
    public void filtrarValoresPares(){
        // devuelve un flujo (con el toArray devuelve un array)
        int [] arrayEnterosPares = IntStream.of(valores).filter(valor ->valor%2 == 0).toArray();
    }

    public void filtrarvaloresParesOrdenar(){
        int[] arrayEnterosParesOrdenados = IntStream.of(valores).filter(valor -> valor%2 == 0).sorted().toArray();
    }

    public void filtrarConVariasCondiciones(){
        // array con enteros pares ordenados y mayores que 5
        // definimos los predicados para no liarnos
        IntPredicate par = valor -> valor%2 == 0;
        IntPredicate mayor5 = valor -> valor > 5;

        IntStream.of(valores).filter(par.and(mayor5)).sorted().forEach(System.out::println);
    }
    
    public void convertirColeccion(){
        IntStream.of(valores).filter(valor -> valor % 2 == 0).mapToDouble(valor -> valor * 8.7).toArray();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String args[]){
        OperacionesIntStream objeto = new OperacionesIntStream(10);

        // Probamos el mostrar valores
        objeto.mostrarValoresFuncional2();

        // Probamos el obtener maximo
        objeto.obtenerMaximo();

        // Probamos la suma reduce
        objeto.obtenerSumaReduce();

        // Probamos el producto reduce
        objeto.obtenerProductoReduce();

        // Probamos la suma de los cuadrados
        objeto.sumaValoresCuadrados();
    }
}
