package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * Created by ahmed on 5/19/17.
 */

public class Prim {
    static int weightArray[][] = new int[20][20];
    static int visited[] = new int[20];
    static int d[] = new int[20];
    static int p[] = new int[20];
    static int verticeCount, edgeCount;
    static int nodeA, nodeB, weight;
    static int current, total, mincost;
    static Scanner in = new Scanner(System.in);

    public static void main(String args[]) throws IOException {

        // Instantiate the graph based on input
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nEnter number of vertices: ");
        verticeCount = Integer.parseInt(buf.readLine());
        System.out.print("\nEnter number of edges: ");
        edgeCount = Integer.parseInt(buf.readLine());
        for (int i = 1; i <= verticeCount; i++) {
            for (int j = 1; j <= verticeCount; j++) {
                weightArray[i][j] = 0;
            }
        }

        for (int i = 1; i <= verticeCount; i++) {
            p[i] = visited[i] = 0;
            d[i] = 32767;
        }

        for (int i = 1; i <= edgeCount; i++) {
            System.out.print("\nEnter edge nodeA, nodeB and weightArray weight: ");
            nodeA = in.nextInt();
            nodeB = in.nextInt();
            weight = in.nextInt();
            weightArray[nodeA][nodeB] = weightArray[nodeB][nodeA] = weight;
        }
        // End of graph instantiation

        current = 1;
        d[current] = 0;
        total = 1;
        visited[current] = 1;
        while (total != verticeCount) {
            for (int i = 1; i <= verticeCount; i++) {
                if (weightArray[current][i] != 0) {
                    if (visited[i] == 0) {
                        if (d[i] > weightArray[current][i]) {
                            d[i] = weightArray[current][i];
                            p[i] = current;
                        }
                    }
                }
            }
            mincost = 32767;
            for (int i = 1; i <= verticeCount; i++) {
                if (visited[i] == 0) {
                    if (d[i] < mincost) {
                        mincost = d[i];
                        current = i;
                    }
                }
            }
            visited[current] = 1;
            total++;
        }

        mincost = 0;
        for (int i = 1; i <= verticeCount; i++)
            mincost = mincost + d[i];

        System.out.print("\n Minimum cost=" + mincost);
        System.out.print("\n Minimum Spanning tree is");

        for (int i = 1; i <= verticeCount; i++)
            System.out.print("\n vertex" + i + "is connected to" + p[i]);
    }
}