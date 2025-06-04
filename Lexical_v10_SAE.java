import java.io.*;
import java.util.*;

public class Lexical_v10_SAE {

    public static List<String> tokenize(String line) {
        /*  Interfaz que representa una colección ordenada de elementos.
            En este programa, almacena los tokens generados por el método tokenize().
            Implementación usada: ArrayList<String>. */
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (Character.isWhitespace(c)) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else if ("+-*/()=".indexOf(c) != -1) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(Character.toString(c));
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens;
    }

    public static void main(String[] args) {
        DFA_Integer dfa = new DFA_Integer();
        NFA_Identifier nfa = new NFA_Identifier();
        NFAE_OperatorOrParen nfae = new NFAE_OperatorOrParen();
        SymbolTable table = new SymbolTable();
        DFA dfa01 = new DFA(); // para cadenas que terminan en 01
        DFA3 dfaStart1 = new DFA3(); // para cadenas que empiezan con 1
        DFA4 dfa0000 = new DFA4(); // para cadena exacta "0000"
        DFA5 dfa1111 = new DFA5(); // para cadena exacta "1111"

        try (BufferedReader reader = new BufferedReader(new FileReader("myString.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                int commentIndex = line.indexOf("//");
                if (commentIndex != -1) {
                    line = line.substring(0, commentIndex).trim();
                }

                if (line.isEmpty())
                    continue;

                List<String> tokens = tokenize(line);
                // ***************************************** */
                for (String token : tokens) {
                    token = token.trim();
                    if (token.isEmpty())
                        continue;

                    boolean reconocido = false;
                    // 1. Exactamente "1111"
                    if (!reconocido && dfa1111.validar(token)) {
                        table.addSymbol(token, "TokenFour1s");
                        reconocido = true;
                    } else
                    
                    // 2. Exactamente "0000"
                     if (!reconocido && dfa0000.validar(token)) {
                        table.addSymbol(token, "TokenFour0s");
                        reconocido = true;
                    }
                    
                    // 3. Empieza con '1'
                    else if (!reconocido && dfaStart1.validar(token)) {
                        try {
                            int valorDecimal = Integer.parseInt(token, 2);
                            table.addSymbol(token, "StartsWith1", String.valueOf(valorDecimal));
                        } catch (NumberFormatException e) {
                            table.addSymbol(token, "StartsWith1", "ERROR");
                        }
                        reconocido = true;
                    }
                    
                    // 4. Entero
                    else if (!reconocido && dfa.accepts(token)) {
                        table.addSymbol(token, "Integer");
                        reconocido = true;
                    }

                    // 5. Termina con "01"
                    else if (!reconocido && dfa01.validar(token)) {
                        table.addSymbol(token, "EndsWith01");
                        reconocido = true;
                    }

                    // 6. Identificador
                    else if (!reconocido && nfa.accepts(token)) {
                        table.addSymbol(token, "Identifier");
                        reconocido = true;
                    }

                    // 7. Operador o paréntesis
                    else if (!reconocido && nfae.accepts(token)) {
                        table.addSymbol(token, "Operator or Parenthesis");
                        reconocido = true;
                    }

                    // No reconocido
                    if (!reconocido) {
                        System.out.println("La cadena <" + token + "> no pertenece a ningún lenguaje.");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Tabla de Símbolos ---");
        table.printTable();
    }
}
