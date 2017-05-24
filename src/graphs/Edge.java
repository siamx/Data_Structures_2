package graphs;

/**
 * Created by ahmed on 5/19/17.
 */
class Edge implements Comparable {
    Edge next;
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight, Edge next) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.next = next;
    }

    Edge(int to, int weight) {
        from = -1;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        return weight - ((Edge) o).weight;
    }
}