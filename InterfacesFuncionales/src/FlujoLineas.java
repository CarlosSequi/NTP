import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CollectionCertStoreParameters;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlujoLineas {
    public static void main(String args[]) throws IOException{

        // patron para partir el contenido de las lineas
        Pattern patron = Pattern.compile("\\s+");

        // creamos el flujo para procesar la lineas del archivo
        Stream<String> lineas = Files.lines(Paths.get("alicia.txt"), StandardCharsets.ISO_8859_1);

        // procesamos el contenido sobre el flujo de lineas
        // obtenemos un diccionario con cada palabra pasada a minuscula y el numero de veces que aparece.
        // Con flatmap aplanamos para que no nos de un flujo de flujos de palabras, sino que nos de solo un
        // flujo de palabras
        TreeMap<String, Long> diccionarioContadorPalabras = lineas.map(linea -> linea.replaceAll("(?!')\\p{Punct}", "")).
                flatMap(linea -> patron.splitAsStream(linea)).
                filter(palabra -> !palabra.isEmpty()).
                collect(Collectors.groupingBy(String::toLowerCase,
                        TreeMap::new, Collectors.counting()));
        
        // Generar coleccion con una inicial y que me genere la coleccion de entradas de contadores con esa inicial
        TreeMap<Character, List<Map.Entry<String, Long>>> listaInicialesPalabras = diccionarioContadorPalabras.entrySet().stream().collect(Collectors.groupingBy(entrada -> entrada.getKey().
                charAt(0), TreeMap::new, Collectors.toList()));

    }
}

// para tener mas eficiencia (rapida velocidad de acceso) usamos un HashMap, para tener los elementos ordenados usamos un TreeMap.
// La clase Map es abstracta, no existe en si, existen dos tipos: HashMap y TreeMap
// Lo mismo ocurre exactamente con la clase Set
