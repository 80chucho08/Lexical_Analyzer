public class DFA_Integer {
    private enum State { q0, q1, ERROR }

    public boolean accepts(String str) {
        if (str == null || str.isEmpty()) return false;

        State currentState = State.q0;

        for (char c : str.toCharArray()) {
            switch (currentState) {
                case q0:
                    if (Character.isDigit(c)) {
                        currentState = State.q1;
                    } else {
                        currentState = State.ERROR;
                    }
                    break;

                case q1:
                    if (Character.isDigit(c)) {
                        // Stay in q1
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
