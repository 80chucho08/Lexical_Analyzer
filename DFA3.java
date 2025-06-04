import java.util.HashSet;
import java.util.Set;
/* 1. DFA3 – Empieza con '1'
Reconoce: Cadenas binarias que empiezan con el dígito 1.

Ejemplos válidos: 1, 10, 1010, 111

Aplicación: Además de reconocerlas, se convierte su valor binario a decimal para mostrarlo en la tabla de símbolos.  */
public class DFA3 {
    static final Set<String> ESTADOS_FINALES = new HashSet<>();
    
    static {
        ESTADOS_FINALES.add("q1");
    }

    public static boolean validar(String cadena) {
        String estado = "q0";
        for (char c : cadena.toCharArray()) {
            estado = delta(estado, c);
            if (estado == null) return false;
        }
        return ESTADOS_FINALES.contains(estado);
    }

    public static String delta(String estado, char simbolo) {
        switch (estado) {
            case "q0": return (simbolo == '1') ? "q1" : null;
            case "q1": return (simbolo == '0' || simbolo == '1') ? "q1" : null;
            default: return null;
        }
    }
}
