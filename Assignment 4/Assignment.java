import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Assignment {
    public static void printGraph(int[][] graph) {
        System.out.println("Printing graph in adjacency list...");

        //Although my graph is saved as adjacency matrix, I print it out as a adjacency list.
        for (int i = 0; i < graph.length; i++){
            //1. Print out which node I'm looking for.
            System.out.print(i);

            //2. For this node "i", print out the other nodes that are connected to it.
            for (int j = 0; j < graph.length; j++){
                if (graph[i][j] == 1){
                    System.out.print(" -> " + j);
                }
            }
            System.out.println();
        }

        System.out.println();
    }
    public static void DFS(int[][] graph, int start) {
        System.out.println("Printing DFS traverse sequence, starting from " + start + ".");

        //Use a boolean array to remember whether I've checked this node or not;
        boolean[] checked = new boolean[graph.length];
        checked[start] = true;
        //Use stack to save predecessor of each node;
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        System.out.print(start);

        while (!stack.isEmpty()){
            for (int i = 0; i < graph.length; i++){
                //If there is a unchecked node, print it out and also save it into stack.
                if (graph[stack.peek()][i] == 1 && checked[i] == false){
                    checked[i] = true;
                    stack.add(i);
                    System.out.print(" -> " + i);
                    break;
                }
                //If i = graph.length - 1, it means that for this node there is no need to continue DFS traversing.
                if (i == graph.length - 1)
                    stack.pop();
            }
        }

        System.out.println();
    }
    public static void BFS(int[][] graph, int start) {
        System.out.println("Printing BFS traverse sequence, starting from " + start + ".");

        //Use a boolean array to remember whether I've checked this node or not;
        boolean[] checked = new boolean[graph.length];
        //Use queue to save predecessor of each node;
        Queue<Integer> queue = new LinkedList<>();
        checked[start] = true;
        queue.add(start);

        while (!queue.isEmpty()){
            int row = queue.remove();
            //If there is a unchecked node, save it into queue.
            for (int i = 0; i < graph[0].length; i++){
                if (graph[row][i] == 1 && checked[i] == false){
                    checked[i] = true;
                    queue.add(i);
                }
            }

            //Print out the node that is checked.
            if (row == start)
                System.out.print(row);
            else
                System.out.print(" -> " + row);
        }

        System.out.println();
    }
    public static void main(String[] args){
        int[][] graph = {
                {0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 0}
        };
        printGraph(graph);

        DFS(graph, 0);
        DFS(graph, 3);
        DFS(graph, 6);
        DFS(graph, 9);

        BFS(graph, 0);
        BFS(graph, 3);
        BFS(graph, 6);
        BFS(graph, 9);
    }
}