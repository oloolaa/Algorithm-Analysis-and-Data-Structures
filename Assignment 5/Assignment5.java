import java.util.*;
public class Assignment5 {
    public static List bellmanFordAlgorithm(int[][] graph, int startNode){
        // Set an array for saving the nodes' number.
        int[] nodesNum = new int[graph.length];
        for (int i = 0; i < nodesNum.length; i++){
            nodesNum[i] = i;
        }
        // Set the initial value for all the nodes.
        int[] distance = new int[graph.length];
        for (int i = 0; i < distance.length; i++){
            if (i == startNode){
                distance[i] = 0;
            }
            else{
                distance[i] = Integer.MAX_VALUE;
            }
        }
        // "predecessor" saves the predecessor of the nodes.
        int[] predecessor = new int[graph.length];
        for (int i = 0; i < predecessor.length; i++){
            predecessor[i] = startNode;
        }
        // Run Bellman-Ford for |V - 1| times to get the shortest path.
        int times = 1;
        while (times <= distance.length - 1){
            for (int i = 0; i < distance.length; i++){
                for (int j = 0; j < distance.length; j++){
                    if (graph[i][j] != 0 && distance[i] != Integer.MAX_VALUE && graph[i][j] + distance[i] < distance[j]){
                        distance[j] = graph[i][j] + distance[i];
                        predecessor[j] = i;
                    }
                }
            }
            times++;
        }
        // Run for the |V| time to see if there is a negative loop.
        boolean changed = false;
        for (int i = 0; i < distance.length; i++){
            for (int j = 0; j < distance.length; j++){
                if (graph[i][j] != 0 && distance[i] != Integer.MAX_VALUE && graph[i][j] + distance[i] < distance[j]){
                    changed = true;
                    break;
                }
            }
            if (changed){
                break;
            }
        }
        /*
        * Check the last traverse and see if there is anything changed.
        * If there is, then it means this graph has negative cycle.
        * Otherwise we have the final result for the shortest path.
        */
        if (changed){
            System.out.println("Negative loop exists. There is no shortest path for this graph.");
            return null;
        }
        else {
            System.out.println("The nodes are: " + Arrays.toString(nodesNum));
            for (int i = 0; i < graph.length; i++){
                System.out.println("From " + startNode + " to " + i +", the distance is " + distance[i] + ", and its predecessor is " + predecessor[i] + ".");
            }
            List<int[]> res = new ArrayList<>();
            res.add(nodesNum);
            res.add(distance);
            res.add(predecessor);
            return res;
        }
    }
    public static void main(String[] args){
        // Simple test case.
        int[][] graphTest = new int[5][5];
        graphTest[0][1] = -1;
        graphTest[0][2] = 4;
        graphTest[1][2] = 3;
        graphTest[1][3] = 2;
        graphTest[1][4] = 2;
        graphTest[3][1] = 1;
        graphTest[3][2] = 5;
        graphTest[4][3] = -3;
        List<int[]> res0 = bellmanFordAlgorithm(graphTest, 0);
        System.out.println();
        // Test case for homework.
        int[][] graph1 = new int[10][10], graph2 = new int[10][10];
        graph1[0][1] = 10; graph2[0][1] = 10;
        graph1[1][2] = 10; graph2[1][2] = 10;
        graph1[2][3] = 10; graph2[2][3] = 10;
        graph1[3][4] = 10; graph2[3][4] = 10;
        graph1[4][5] = 10; graph2[4][5] = 10;
        graph1[5][6] = 10; graph2[5][6] = 10;
        graph1[6][7] = 10; graph2[6][7] = 10;
        graph1[7][8] = 10; graph2[7][8] = 10;
        graph1[8][9] = 10; graph2[8][9] = 10;
        graph1[0][2] = -3; graph2[2][0] = -21;
        graph1[1][4] = -5; graph2[1][4] = -5;
        graph1[3][4] = -6; graph2[3][4] = -6;
        graph1[3][6] = -10; graph2[3][6] = -10;
        graph1[5][6] = -7; graph2[5][6] = -7;
        graph1[5][7] = 8; graph2[5][7] = 8;
        List<int[]> res1 = bellmanFordAlgorithm(graph1, 0);
        System.out.println();
        List<int[]> res2 = bellmanFordAlgorithm(graph2, 0);
    }
}
