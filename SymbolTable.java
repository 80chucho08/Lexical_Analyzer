import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    /*  es el puente entre el análisis léxico y
        la visualización de resultados. 
        Automatiza la asociación entre 
        tokens y autómatas, y organiza la 
        información de forma clara para el usuario. */
    private List<Symbol> symbols = new ArrayList<>();
    // Método original, sin valor decimal
    public void addSymbol(String lexeme, String tokenType) {
        addSymbol(lexeme, tokenType, null);
    }
    // Nuevo método sobrecargado con valor decimal
    public void addSymbol(String lexeme, String tokenType, String valorDecimal) {
        String automaton;
        switch (tokenType) {
            case "Integer":
                automaton = "DFA";
                break;
            case "Identifier":
                automaton = "NFA";
                break;
            case "Operator or Parenthesis":
                automaton = "NFA-E";
                break;
            case "StartsWith1":
                automaton = "DFA3";
                break;
            case "EndsWith01":
                automaton = "DFA";
                break;
            case "TokenFour0s":
                automaton = "DFA4";
                break;
            case "TokenFour1s":
                automaton = "DFA5";
                break;
            default:
                automaton = "UNKNOWN";
                break;
        }
        symbols.add(new Symbol(tokenType, lexeme, automaton, valorDecimal));
    }
    public void printTable() {
        System.out.println("|                 TOKEN     |   LEXEMA   |   AUTOMATA    |  VALOR DECIMAL");
        System.out.println("--------------------------------------------------------------------------");
        for (Symbol symbol : symbols) {
            System.out.println(symbol);
        }
    }
}
