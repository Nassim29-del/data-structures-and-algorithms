
/**
 * Provides utility methods for performing operations on 2D integer arrays
 * (matrices).
 * <p>
 * In Java, a 2D array is an array of arrays. Each element accessed using
 * matrix[row] represents a one-dimensional array.
 *
 * <p>Available operations include:
 * <ul>
 *     <li>Matrix addition</li>
 *     <li>Matrix multiplication</li>
 *     <li>Matrix transpose</li>
 *     <li>90-degree clockwise rotation</li>
 *     <li>Spiral order traversal</li>
 *     <li>Searching in a row-and-column sorted matrix</li>
 *     <li>Printing matrices</li>
 * </ul>
 *
 * <p>Time and space complexity:
 * <table border="1">
 *     <caption>Algorithm complexity</caption>
 *     <tr>
 *         <th>Operation</th>
 *         <th>Time Complexity</th>
 *         <th>Space Complexity</th>
 *     </tr>
 *     <tr>
 *         <td>add</td>
 *         <td>O(r * c)</td>
 *         <td>O(r * c)</td>
 *     </tr>
 *     <tr>
 *         <td>multiply</td>
 *         <td>O(r * c * k)</td>
 *         <td>O(r * k)</td>
 *     </tr>
 *     <tr>
 *         <td>transpose</td>
 *         <td>O(r * c)</td>
 *         <td>O(r * c)</td>
 *     </tr>
 *     <tr>
 *         <td>rotate90Clockwise</td>
 *         <td>O(n^2)</td>
 *         <td>O(1)</td>
 *     </tr>
 *     <tr>
 *         <td>spiralOrder</td>
 *         <td>O(r * c)</td>
 *         <td>O(r * c)</td>
 *     </tr>
 *     <tr>
 *         <td>searchSorted</td>
 *         <td>O(r + c)</td>
 *         <td>O(1)</td>
 *     </tr>
 * </table>
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class MatrixOperations {

    /**
     * Adds two matrices of the same dimensions.
     * <p>
     * Each element in the resulting matrix is the sum of the corresponding
     * elements in the two input matrices.
     *
     * @param a the first matrix
     * @param b the second matrix
     * @return a new matrix containing the sum of the two input matrices
     * @throws IllegalArgumentException if the matrices have different dimensions
     *
     * Time Complexity: O(r * c)
     * Space Complexity: O(r * c)
     */
    public static int[][] add(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Matrices must be the same size");
        }

        int rows = a.length, cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    /**
     * Multiplies two matrices using standard matrix multiplication.
     * <p>
     * If matrix A has dimensions r x c and matrix B has dimensions c x k,
     * the resulting matrix will have dimensions r x k.
     *
     * @param a the first matrix with dimensions r x c
     * @param b the second matrix with dimensions c x k
     * @return a new matrix containing the multiplication result
     * @throws IllegalArgumentException if the number of columns in A
     *         does not equal the number of rows in B
     *
     * Time Complexity: O(r * c * k)
     * Space Complexity: O(r * k)
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("Columns of A must equal rows of B");
        }

        int r = a.length, c = a[0].length, k = b[0].length;
        int[][] result = new int[r][k];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < k; j++) {
                for (int x = 0; x < c; x++) {
                    result[i][j] += a[i][x] * b[x][j];
                }
            }
        }

        return result;
    }

    /**
     * Creates the transpose of a matrix.
     * <p>
     * The transpose is obtained by swapping the rows and columns.
     * For example, a matrix with dimensions 2 x 3 becomes a matrix
     * with dimensions 3 x 2.
     *
     * @param m the matrix to transpose
     * @return a new matrix containing the transposed values
     *
     * Time Complexity: O(r * c)
     * Space Complexity: O(r * c)
     */
    public static int[][] transpose(int[][] m) {
        int[][] result = new int[m[0].length][m.length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                result[j][i] = m[i][j];
            }
        }

        return result;
    }

    /**
     * Rotates a square matrix 90 degrees clockwise in place.
     * <p>
     * The rotation is performed in two steps:
     * <ol>
     *     <li>Transpose the matrix by swapping rows and columns.</li>
     *     <li>Reverse each row of the transposed matrix.</li>
     * </ol>
     * The original matrix is modified directly; no additional matrix is created.
     *
     * @param m the square matrix to rotate
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static void rotate90Clockwise(int[][] m) {
        int n = m.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }

        for (int[] row : m) {
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
            }
        }
    }

    /**
     * Returns all elements of a matrix in spiral order.
     * <p>
     * Traversal starts at the top-left corner and moves:
     * right, down, left, and up, continuing inward until all
     * elements have been visited.
     *
     * @param m the matrix to traverse
     * @return a one-dimensional array containing the matrix elements
     *         in spiral order
     *
     * Time Complexity: O(r * c)
     * Space Complexity: O(r * c)
     */
    public static int[] spiralOrder(int[][] m) {
        int rows = m.length, cols = m[0].length;
        int[] result = new int[rows * cols];

        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1, idx = 0;

        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) {
                result[idx++] = m[top][j];
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[idx++] = m[i][right];
            }
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result[idx++] = m[bottom][j];
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[idx++] = m[i][left];
                }
                left++;
            }
        }

        return result;
    }

    /**
     * Searches for a target value in a matrix where every row and
     * every column is sorted in ascending order.
     * <p>
     * The search starts at the top-right corner:
     * <ul>
     *     <li>If the current value is greater than the target,
     *         move one column to the left.</li>
     *     <li>If the current value is smaller than the target,
     *         move one row down.</li>
     *     <li>If the current value equals the target,
     *         return its position.</li>
     * </ul>
     *
     * @param m the sorted matrix to search
     * @param target the value to find
     * @return an integer array containing the row and column indices
     *         of the target; returns {-1, -1} if the target is not found
     *
     * Time Complexity: O(r + c)
     * Space Complexity: O(1)
     */
    public static int[] searchSorted(int[][] m, int target) {
        int row = 0, col = m[0].length - 1;

        while (row < m.length && col >= 0) {
            if (m[row][col] == target) {
                return new int[]{row, col};
            }

            if (m[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Prints a matrix with a title.
     * <p>
     * Each row is displayed on a separate line, with values
     * formatted into aligned columns.
     *
     * @param title the heading to display before the matrix
     * @param m the matrix to print
     */
    public static void print(String title, int[][] m) {
        System.out.println(title);

        for (int[] row : m) {
            StringBuilder sb = new StringBuilder("  ");

            for (int val : row) {
                sb.append(String.format("%4d", val));
            }

            System.out.println(sb);
        }
    }

    /**
     * Runs examples demonstrating the matrix operations.
     * <p>
     * Creates sample matrices and demonstrates addition,
     * multiplication, transpose, rotation, spiral traversal,
     * searching in a sorted matrix, and printing a jagged array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{7, 8, 9}, {10, 11, 12}};
        int[][] c = {{1, 2}, {3, 4}, {5, 6}};

        print("Matrix A (2x3):", a);
        print("Matrix B (2x3):", b);
        print("A + B:", add(a, b));
        print("Transpose of A (3x2):", transpose(a));
        print("A (2x3) * C (3x2):", multiply(a, c));

        int[][] square = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        print("Square matrix:", square);

        System.out.println("Spiral order: " +
                java.util.Arrays.toString(spiralOrder(square)));

        rotate90Clockwise(square);
        print("Rotated 90 clockwise:", square);

        int[][] sorted = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16},
                {10, 13, 14, 17}
        };

        int[] pos = searchSorted(sorted, 9);
        System.out.println("Search 9 in sorted matrix -> row " +
                pos[0] + ", col " + pos[1]);

        // Jagged array: rows can have different lengths
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{2, 3};
        jagged[2] = new int[]{4, 5, 6};

        print("Jagged array:", jagged);
    }
}
