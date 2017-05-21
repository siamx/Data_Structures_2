package graphs;

/**
 * Created by ahmed on 5/20/17.
 */

class Vertex {
    int parent;
    int pweight;
    State state;

    Vertex() {
        state = State.Undiscovered;
        parent = -1;
    }

    //State enumeration for vertices during traversal.
    public enum State {
        Undiscovered, Discovered
    }
}
