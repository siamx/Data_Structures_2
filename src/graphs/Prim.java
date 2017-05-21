package graphs;

/*
 * Created by ahmed on 5/20/17.
 */

import java.util.PriorityQueue;
import java.util.Queue;

import static graphs.Vertex.State;

public class Prim {
    //Indicates whether this is a directed or undirected graph.
    private boolean directed;

    //This is the adjacency list.  Each index in this array
    //corresponds to a vertex in the graph.  The list which
    //is stored at each index represents the list of vertices
    //adjacent to the vertex for that position.  So, for
    //example, if vertex 2 connects to vertex 4 in the graph,
    //adjacencyList[2]'s list will have an edge with to
    //4 in it.
    private Edge[] adjacencyList;

    //List of vertex information which can be used to store
    //the state of any vertex and its parent, allowing us
    //to store the chain from any node back to the root
    //node analyzed in a traversal.
    private Vertex[] vertices;

    //------------------------------------------------------------
    // Data members representing graph state.
    //------------------------------------------------------------

    private Prim(boolean directed) {
        this.directed = directed;
    }

    public static void main(String[] args) throws Exception {
        //Creating and loading undirected graph.  In real life
        //this would be done from file or database, but here
        //we just hard-code a graph in the init function.
        Prim g = new Prim(false);

        g.initializeGraph();
        //Construct the minimum spanning tree using Prim's
        //algorithm and then print the parents assigned to
        //each node.
        g.primMST();
        g.printGraph();
        g.printParents();
    }

    //Initialize the graph from a given file.
    private void initializeGraph() {

        //Save the counts which should be in the file header.
        int vertexCount = 6;

        //Initialize the vertices.
        vertices = new Vertex[vertexCount];
        for (int i = 0; i < vertexCount; ++i) {
            vertices[i] = new Vertex();
        }

        //Initialize the adjacency list.
        adjacencyList = new Edge[vertexCount];

        addEdge(0, 1, 2);
        addEdge(0, 4, 1);
        addEdge(0, 5, 7);
        addEdge(1, 2, 3);
        addEdge(1, 4, 4);
        addEdge(2, 3, 2);
        addEdge(4, 3, 9);
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
        int uv = vertices.length;

        //Create a priority queue which always gives the smallest
        //edge remaining.
        Queue<Edge> minimumEdges = new PriorityQueue<>(vertices.length);

        //Mark the first vertex discovered.
        vertices[0].state = State.Discovered;
        uv--;

        //Put all edges that 0 can reach into the queue. If 0 can
        //reach no edges, then 0 is unreachable since our edges
        //are all bidirectional in an undirected graph.
        Edge newEdge = adjacencyList[0];
        while (newEdge != null) {
            minimumEdges.add(newEdge);
            newEdge = newEdge.next;
        }

        //Repeatedly take the next edge.  If it connects two
        //tree nodes (discovered nodes) discard it and take the
        //next one.  Add the destination to the graph and
        //repeat until no non-tree edges remain.
        while (uv > 0 && !minimumEdges.isEmpty()) {

            //TODO: Can we be more intelligent about skipping
            //TODO: edges or avoiding it altogether?
            Edge e = minimumEdges.remove();
            if (vertices[e.from].state == State.Discovered &&
                    vertices[e.to].state == State.Discovered) {
                continue;
            }

            //Mark the new tree-vertex discovered and set its
            //parent.
            Vertex dv = vertices[e.to];
            dv.state = State.Discovered;
            dv.parent = e.from;
            dv.pweight = e.weight;
            --uv;

            //Add new edges reachable by the new node to
            //the minimum edges queue.
            newEdge = adjacencyList[e.to];
            while (newEdge != null) {
                minimumEdges.add(newEdge);
                newEdge = newEdge.next;
            }
        }

        if (uv > 0) {
            throw new Exception("Some nodes were not found!");
        }
    }

    private void printGraph() {
        System.out.println("Full Graph is:\nG {");
        for (Edge e : adjacencyList) {
            while (e != null) {
                System.out.println("\t" + e.from + " -> " +
                        e.to + " \tCost: " + e.weight);
                e = e.next;
            }
        }
        System.out.println("}");
    }

    //------------------------------------------------------------
    // Prim's algorithm implementation.
    //------------------------------------------------------------

    private void printParents() {
        System.out.println("Minimum Spanning Tree is:");
        System.out.println("digraph G {");
        for (int i = 0; i < vertices.length; ++i) {
            Vertex v = vertices[i];
            if (v.parent == -1) continue;
            System.out.println("\t" + v.parent + " -> " + i + "\tCost: " + v.pweight);
        }
        System.out.println("}");
    }
}