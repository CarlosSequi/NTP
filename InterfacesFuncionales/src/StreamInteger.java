import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInteger {
    public static void main(String args[]){
        Integer[] valores = {2,9,5,0,3,7,1,4,8,6};

        // Creacion del flujo
        Stream<Integer> flujo = Arrays.stream(valores);
        flujo.forEach(System.out::println);
        
        // esto siguiente no se puede hacer porque enlaoperacion anterior ya hemos
        // agotado el flujo, tendriamos que volver a crear un flujo
        // flujo.count();
        
        // con collect recogemos el resultado del procesamiento y trabajamos con el
        // eneste caso el resultado lo recogemos sobre una lista
        List<Integer> lista = Arrays.stream(valores).sorted().collect(Collectors.toList());

        // creamos un flujo a partir se la lista
        lista.stream().sorted().collect(Collectors.toList());
    }
}
