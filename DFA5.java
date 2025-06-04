import java.util.Set;
import java.util.HashSet;



/*  3. DFA5 – TokenFour1s
Reconoce: Únicamente la cadena "1111" (cuatro unos).

Ejemplo válido: 1111

No acepta: 111, 11111, 1110| */
public class DFA5 {
    static final Set<String> ESTADOS_FINALES = new HashSet<>();
    static {
        ESTADOS_FINALES.add("q4");
    }

    public static boolean validar(String cadena) {
        String estado = "q0";
        for (char c : cadena.toCharArray()) {
            switch (estado) {
                case "q0": estado = (c == '1') ? "q1" : null; break;
                case "q1": estado = (c == '1') ? "q2" : null; break;
                case "q2": estado = (c == '1') ? "q3" : null; break;
                case "q3": estado = (c == '1') ? "q4" : null; break;
                default: return false;
            }
            if (estado == null) return false;
        }
        return ESTADOS_FINALES.contains(estado);
    }
}
