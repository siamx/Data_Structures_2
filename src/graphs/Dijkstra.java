package graphs;

import java.util.*;

/**
 * Created by ahmed on 5/19/17.
 */
public class Dijkstra {
    private int parent[];
    private int dist[];
    private int nodes, edges;
    private Map<Integer, Character> map = new HashMap<>();
    // adjacency List
    private ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.run();
    }

    private void run() {
        Scanner in = new Scanner(System.in);

//        System.out.println("Enter Node #:");
//        nodes = in.nextInt();
//        System.out.println("Enter Edges #:");
//        edges = in.nextInt();
//        nodes++;
        nodes = 6;
        edges = 10;
        //build adjacency List
        for (int i = 0; i < nodes + 1; i++)
            adjList.add(new ArrayList<>());

        // s t x y z
        // 1 2 3 4 5
        map.put(1, 's');
        map.put(2, 't');
        map.put(3, 'x');
        map.put(4, 'y');
        map.put(5, 'z');

        adjList.get(1).add(new Edge(2, 10));
        adjList.get(1).add(new Edge(4, 5));
        adjList.get(2).add(new Edge(3, 1));
        adjList.get(2).add(new Edge(4, 2));
        adjList.get(4).add(new Edge(2, 3));
        adjList.get(4).add(new Edge(3, 9));
        adjList.get(4).add(new Edge(5, 2));
        adjList.get(3).add(new Edge(3, 4));
        adjList.get(5).add(new Edge(3, 6));
        adjList.get(5).add(new Edge(1, 7));
        // Take Edges Input
//        System.out.println("Enter Edges info (from to weight):");
//        for (int i = 0; i < edges; i++) {
//            int from = in.nextInt();
//            int to = in.nextInt();
//            int w = in.nextInt();
//
//            adjList.get(from).add(new Edge(to, w));
//            adjList.get(to).add(new Edge(from, w));
//        }
//
//        System.out.println("Enter Start Point:");
        dijkstra(1);
//
        System.out.println("Printing Shortest Path from s, to all nodes:");
        for (int i = 1; i < 6; i++)
            printPath(i);
    }

    private void dijkstra(int start) {
        parent = new int[nodes];
        dist = new int[nodes];
        for (int i = 0; i < 6; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        dist[start] = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start, 0));
        while (!priorityQueue.isEmpty()) {
            int curNode = priorityQueue.peek().to;
            int weight = priorityQueue.poll().weight;

            if (dist[curNode] < weight) continue;

            for (int i = 0; i < adjList.get(curNode).size(); i++) {
                Object edge = adjList.get(curNode).get(i);
                int to = ((Edge) edge).to;
                int w = ((Edge) edge).weight;
                if (dist[to] > dist[curNode] + w) {
                    parent[to] = curNode;
                    dist[to] = dist[curNode] + w;
                    priorityQueue.add(new Edge(to, dist[to]));
                }
                // Optimization if found
//                if (to == to) {
//                    break;
//                }
            }
        }
    }

    private void printPath(int dest) {
        System.out.print("\nShortest Path cost to: " + map.get(dest) + ", is: " + dist[dest] + "\nPath: ");
        while (parent[dest] != -1) {
            System.out.print(map.get(dest) + "  <--  ");
            dest = parent[dest];
        }
        System.out.println(map.get(dest));
    }
}
/*
5
6
1 2 3
2 3 4
3 4 5
4 5 6
1 3 1
3 5 1
 */