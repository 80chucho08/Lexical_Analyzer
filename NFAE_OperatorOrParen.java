public class NFAE_OperatorOrParen {
    // NFAE para identificar operadores y paréntesis en un lenguaje de programación simple
    // Reconoce: +, -, *, /, (, ), =
    private enum State { q0, q1, ERROR }

    public boolean accepts(String str) {
        if (str == null || str.length() != 1) return false;

        State currentState = State.q0;
        char c = str.charAt(0);

        switch (currentState) {
            case q0:
                if (c == '+' || c == '-' || c == '*' || c == '/' ||
                    c == '(' || c == ')' || c == '=') {
                    currentState = State.q1;
                } else {
                    currentState = State.ERROR;
                }
                break;

            default:
                return false;
        }

        return currentState == State.q1;
    }
}
