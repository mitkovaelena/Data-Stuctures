package exercises.AStar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[][] map =
            {
                    {'-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '*', '-', '-', '-', '-', '-'},
                    {'W', 'W', 'W', 'W', 'W', '-', '-', '-'},
                    {'-', '-', '-', '-', 'W', 'W', '-', '-'},
                    {'-', '-', '-', 'P', 'W', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-'}
            };

    public static void main(String[] args) throws IOException {
        map = readMap();

        Node start = findGoal('P');
        Node goal = findGoal('*');

        AStar aStar = new AStar(map);
        Iterable<Node> path = aStar.getPath(start, goal);

        for (Node node : path) {
            int row = node.getRow();
            int col = node.getCol();
            map[row][col] = '@';
        }

        printMap();
    }

    private static char[][] readMap() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        char[][] map = new char[n][n];
        for (int i = 0; i < map.length; i++) {
            char[] line = reader.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line[j];
            }
        }

        return map;
    }

    static Node findGoal(char goal) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == goal) {
                    return new Node(row, col);
                }
            }
        }

        throw new IllegalArgumentException("Object not present on map");
    }

    static void printMap() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                System.out.print(map[row][col] + " ");
            }

            System.out.println();
        }
    }
}