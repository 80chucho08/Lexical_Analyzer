public class NFA_Identifier {
// NFA para identificar identificadores en un lenguaje de programación simple
    private enum State { q0, q1, ERROR }

    public boolean accepts(String str) {
        if (str == null || str.isEmpty()) return false;

        State currentState = State.q0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (currentState) {
                case q0:
                    if (Character.isLetter(c)) {
                        currentState = State.q1;
                    } else {
                        currentState = State.ERROR;
                    }
                    break;

                case q1:
                    if (Character.isLetterOrDigit(c)) {
                        // Permanecer en q1
                    } else {
                        currentState = State.ERROR;
                    }
                    break;

                default:
                    return false;
            }
        }

        return currentState == State.q1;
    }
}
