import java.util.Set;
import java.util.HashSet;

/*  2. DFA4 – TokenFour0s
Reconoce: Únicamente la cadena "0000" (cuatro ceros).

Ejemplo válido: 0000

No acepta: 000, 00000, 0001 */
public class DFA4 {
    static final Set<String> ESTADOS_FINALES = new HashSet<>();
    static {
        ESTADOS_FINALES.add("q4");
    }

    public static boolean validar(String cadena) {
        String estado = "q0";
        for (char c : cadena.toCharArray()) {
            switch (estado) {
                case "q0": estado = (c == '0') ? "q1" : null; break;
                case "q1": estado = (c == '0') ? "q2" : null; break;
                case "q2": estado = (c == '0') ? "q3" : null; break;
                case "q3": estado = (c == '0') ? "q4" : null; break;
                default: return false;
            }
            if (estado == null) return false;
        }
        return ESTADOS_FINALES.contains(estado);
    }
}
