public class Symbol {
    /*  es una clase que representa un 
        elemento del lenguaje fuente 
        después del análisis léxico. 

        Su objetivo es almacenar la 
        información esencial que se 
        extrae de cada lexema reconocido 
        por los autómatas    

    (DFA, NFA, NFA-Ɛ). */
    
    private String tokenType;
    private String lexeme;
    private String automaton;
    private String valorDecimal; // puede ser null

    public Symbol(String tokenType, String lexeme, String automaton) {
        this(tokenType, lexeme, automaton, null);
    }

    public Symbol(String tokenType, String lexeme, String automaton, String valorDecimal) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.automaton = automaton;
        this.valorDecimal = valorDecimal;
    }

    @Override
    public String toString() {
        String extra = (valorDecimal != null) ? "  |  " + valorDecimal : "";
        return String.format("| %25s | %9s | %12s |%s", tokenType, lexeme, automaton, extra);
    }
}
