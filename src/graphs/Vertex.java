package graphs;

/**
 * Created by ahmed on 5/20/17.
 */

class Vertex {
    int parent;
    int parentWeight;
    State state;

    Vertex() {
        state = State.Undiscovered;
        parent = -1;
    }

    public enum State {
        Undiscovered, Discovered
    }
}
