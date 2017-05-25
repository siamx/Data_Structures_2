package graphs;

/*
 * Created by ahmed on 5/20/17.
 */

import java.util.PriorityQueue;
import java.util.Queue;

import static graphs.Vertex.State;
import static graphs.Vertex.State.Discovered;

public class Prim {
    private boolean directed;
    private Edge[] adjacencyList;
    private Vertex[] vertices;

    private Prim(boolean directed) {
        this.directed = directed;
    }

    public static void main(String[] args) throws Exception {
        Prim g = new Prim(false);
        g.initializeGraph();
        g.primMST();
        g.printParents();
    }

    //Initialize the graph from a given file.
    private void initializeGraph() {
//        Scanner scanner = new Scanner(System.in);

//        System.out.println("Enter vertex #: ");
//        int vertexCount = scanner.nextInt();
        int vertexCount = 10;

        //Initialize the vertices.
        vertices = new Vertex[vertexCount];
        for (int i = 0; i < vertexCount; ++i) vertices[i] = new Vertex();

        //Initialize the adjacency list.
        adjacencyList = new Edge[vertexCount];
//
//        System.out.println("Enter Edge #: ");
//        int edgeCount = scanner.nextInt();
//
//        System.out.println("Enter Edges in form (From To Cost): ");
//        for (int i = 0; i < edgeCount; i++) {
//            int src = scanner.nextInt();
//            int dest = scanner.nextInt();
//            int cost = scanner.nextInt();
//            addEdge(src, dest, cost);
//        }

        // a b c d e f g h i
        // 1 2 3 4 5 6 7 8 9


        // a b 4
        addEdge(1, 2, 4);
        // a h 8
        addEdge(1, 8, 8);
        // b h 11
        addEdge(2, 8, 11);
        // b c 8
        addEdge(2, 3, 8);
        // h i 7
        addEdge(8, 9, 7);
        // c i 2
        addEdge(3, 9, 2);
        // i g 6
        addEdge(9, 7, 6);
        // h g 1
        addEdge(8, 7, 1);
        // g f 2
        addEdge(7, 6, 2);
        // c f 4
        addEdge(3, 6, 4);
        // c d 7
        addEdge(3, 4, 7);
        // d f 14
        addEdge(4, 6, 14);
        // f e 10
        addEdge(6, 5, 10);
        // d e 9
        addEdge(4, 5, 9);

    }


    private void addEdge(int source, int dest, int weight) {
        //Add an edge from the from to the destination.
        Edge edge = new Edge(source, dest, weight, adjacencyList[source]);

        adjacencyList[source] = edge;

        //If this is an undirected graph, add the reverse edge.
        if (!directed) {
            Edge reverseEdge = new Edge(dest, source, weight, adjacencyList[dest]);
            adjacencyList[dest] = reverseEdge;
        }
    }

    private void primMST() throws Exception {

        //Mark all vertices undiscovered.
        for (Vertex vertex : vertices) {
            vertex.state = State.Undiscovered;
        }

        //Undiscovered vertex count.
        int undiscoveredNodes = vertices.length - 1;

        //Create a priority queue which always gives the smallest
        //edge remaining.
        Queue<Edge> minimumEdges = new PriorityQueue<>(vertices.length);

        //Mark the first vertex discovered.
        vertices[1].state = Discovered;
        undiscoveredNodes--;

        //Put all edges that 1 can reach into the queue. If 1 can
        //reach no edges, then 1 is unreachable since our edges
        //are all bidirectional in an undirected graph.
        Edge newEdge = adjacencyList[1];
        while (newEdge != null) {
            minimumEdges.add(newEdge);
            newEdge = newEdge.next;
        }

        //Repeatedly take the next edge.  If it connects two
        //tree nodes (discovered nodes) discard it and take the
        //next one.  Add the destination to the graph and
        //repeat until no non-tree edges remain.
        while (undiscoveredNodes > 0 && !minimumEdges.isEmpty()) {
            Edge curEdge = minimumEdges.remove();
            if (vertices[curEdge.from].state == Discovered && vertices[curEdge.to].state == Discovered) {
                continue;
            }

            //Mark the new tree-vertex discovered and set its parent.
            Vertex dv = vertices[curEdge.to];
            dv.state = Discovered;
            dv.parent = curEdge.from;
            dv.parentWeight = curEdge.weight;
            --undiscoveredNodes;

            //Add new edges reachable by the new node to
            //the minimum edges queue.
            newEdge = adjacencyList[curEdge.to];
            while (newEdge != null) {
                minimumEdges.add(newEdge);
                newEdge = newEdge.next;
            }
        }

        if (undiscoveredNodes > 0) {
            throw new Exception("Some nodes were not found!\nThe graph has many components\n");
        }
    }

//    private void printGraph() {
//        System.out.println("Full Graph is: G {");
//        for (Edge e : adjacencyList) {
//            while (e != null) {
//                System.out.println("\t" + (char) e.from + " -> " + (char) e.to + " \tCost: " + e.weight);
//                e = e.next;
//            }
//        }
//        System.out.println("}");
//    }

    private void printParents() {
        int totalCost = 0;
        System.out.println("Minimum Spanning Tree is G: ");
        for (int i = 0; i < vertices.length; ++i) {
            Vertex v = vertices[i];
            if (v.parent == -1) continue;
            totalCost += v.parentWeight;
            System.out.println("\t" + (char) (v.parent + 96) + "    ->    " + (char) (i + 96) + "   \tCost: " + v.parentWeight);
        }
        System.out.println("\nTotal Cost = " + totalCost);
    }
}