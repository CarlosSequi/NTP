import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamString {
    public static void main (String args[]){
        String [] cadenas={"Rojo","Naranja","Amarillo","Verde","Azul","Indigo","Violeta"};

        List<String> listaMayus = Arrays.stream(cadenas).map(cad -> cad.toUpperCase()).collect(Collectors.toList());
        // o bien: 
        // Arrays.stream(cadenas).map(String::toUpperCase);
        
        // Filtrar para quedarnos con la cadenas en orden lexicografico por encima de M
        // ademas las vamos a ordenar y a recoger sobre una lista
        // compareTo devuelve 0 si son iguales, negativo si esta por debajo y positivo si esta por encima
        // Con CASE_INSENSITIVE_ORDER ignoramos las mayusculas y minusculas
        Arrays.stream(cadenas).filter(cad -> cad.compareToIgnoreCase("m") > 0).sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
    }
}
