import java.util.HashSet;
import java.util.Set;

public class DFA {
    static final Set<String> ESTADOS_FINALES = new HashSet<>();

    static {
        ESTADOS_FINALES.add("q2");
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
            case "q0": return (simbolo == '0') ? "q1" : (simbolo == '1') ? "q0" : null;
            case "q1": return (simbolo == '0') ? "q1" : (simbolo == '1') ? "q2" : null;
            case "q2": return (simbolo == '0') ? "q1" : (simbolo == '1') ? "q0" : null;
            default: return null;
        }
    }
}
