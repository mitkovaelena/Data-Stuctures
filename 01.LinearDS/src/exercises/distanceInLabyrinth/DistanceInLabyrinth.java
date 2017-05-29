package exercises.distanceInLabyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class DistanceInLabyrinth {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] startingCoordinates = new int[2];
        boolean madeChanges;

        String[][] matrix = new String[n][n];

        for (int i = 0; i < matrix.length; i++) {
            String[] line = reader.readLine().split("");
            for (int j = 0; j < matrix.length; j++) {
                if (Objects.equals(line[j], "*")) {
                    startingCoordinates[0] = i;
                    startingCoordinates[1] = j;
                }
                matrix[i][j] = line[j];
            }
        }

        checkNeighbouringCells(matrix, startingCoordinates[0], startingCoordinates[1], 0);

        do {
            madeChanges = false;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (!Objects.equals(matrix[i][j], "*") && !Objects.equals(matrix[i][j], "x") && !Objects.equals(matrix[i][j], "0")) {
                        boolean temp = checkNeighbouringCells(matrix, i, j, Integer.parseInt(matrix[i][j]));
                        if (!madeChanges) madeChanges = temp;
                    }
                }
            }
        } while (madeChanges);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(Objects.equals(matrix[i][j], "0") ? "u" : matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean checkNeighbouringCells(String[][] matrix, int x, int y, int num) {
        boolean madeChanges = false;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) == Math.abs(j)) continue;
                try {
                    if (!Objects.equals(matrix[x + i][y + j], "*") && !Objects.equals(matrix[x + i][y + j], "x")) {
                        if (Integer.parseInt(matrix[x + i][y + j]) == 0 || Integer.parseInt(matrix[x + i][y + j]) > num + 1 || num == 0) {
                            matrix[x + i][y + j] = String.valueOf(num + 1);
                            madeChanges = true;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return madeChanges;
    }
}
