import java.util.*;

/**
 * MaxPathFromTheLeftTopCorner
 * score: 77%
 * correctness: 100%
 * performance: 33%
 * problem: 
 * - performance test: running time 9.912 sec, expected 9.328 sec
 *
 * Find a maximal value path in a matrix, 
 * starting in the top-left corner and ending in the bottom-right corner.
 */
class Technetium{

    class Cell {
        public int value = 0;
        public Cell previousCell = null;
        public Cell nextCell1 = null;
        public Cell nextCell2 = null;
        public int usedCounter = 2;
        int counter = 0;

        public Cell() {            
            this.counter = 0;
            this.previousCell = null;
            this.nextCell1 = null;
            this.nextCell2 = null;
            this.value = 0;
        }

        public Cell(int value) {
            this.counter = 0;
            this.previousCell = null;
            this.nextCell1 = null;
            this.nextCell2 = null;
            this.value = value;
        }

        public void initAndAddCell(int value, Cell cell) {
            cell.usedCounter -= 1;

            if (cell.counter < 8) {
                this.counter = cell.counter + 1;
                this.previousCell = cell.previousCell;
                this.value = cell.value * 10 + value;
            } else {
                this.counter = 0;
                cell.usedCounter = 3;
                this.previousCell = cell;
                this.value = value;
            }
        }

        public void compareAndAddCell(int value, Cell c1, Cell c2) {
            if (c1.isGreaterThan(c2)) {
                this.initAndAddCell(value, c1);
            } else {
                this.initAndAddCell(value, c2);
            }
        }

        // True if this object is greater than c, else False
        public boolean isGreaterThan(Cell c) {
            Cell c1 = this;
            Cell c2 = c;

            while(c1.previousCell != null) {
                c1.previousCell.nextCell1 = c1;
                c1 = c1.previousCell;
            }
            
            while(c2.previousCell != null) {
                c2.previousCell.nextCell2 = c2;
                c2 = c2.previousCell;
            }

            while (c1.nextCell1 != null && c2.nextCell2 != null) {
                if (c1.value > c2.value) {
                    return true;
                } else if (c1.value < c2.value) {
                    return false;
                }
                c1 = c1.nextCell1;
                c2 = c2.nextCell2;
            }

            if (c1.nextCell1 != null) {
                return true;
            } else if (c2.nextCell2 != null) {
                return false;
            }
            if (c1.value > c2.value) {
                return true;
            }

            return false;
            
        }

        public String toString() {
            Cell c1 = this;            
            String s = String.valueOf(c1.value);
            while(c1.previousCell != null) {
                c1 = c1.previousCell;
                s = String.valueOf(c1.value) + s;
            }

            return s;
        }
    }

    public String solution(int[][] A) {
        int M = A.length;
        int N = A[0].length;

        Cell[][] C = new Cell[M][N];
        
        C[0][0] = new Cell(A[0][0]);
        
        // update corner
        for (int i = 1; i < M; i++) {
            C[i][0] = new Cell();
            C[i][0].initAndAddCell(A[i][0], C[i-1][0]);
        }
        for (int i = 1; i < N; i++) {
            C[0][i] = new Cell();
            C[0][i].initAndAddCell(A[0][i], C[0][i-1]);
        }

        // update the rest cells
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                C[i][j] = new Cell();
                C[i][j].compareAndAddCell(A[i][j], C[i-1][j], C[i][j-1]);

                if (C[i-1][j].usedCounter == 0) {
                    C[i-1][j] = null;
                }
                if (C[i][j-1].usedCounter == 0) {
                    C[i][j-1] = null;
                }
            }
        }

        return C[M-1][N-1].toString();
    }

    public static void test(int[][] A) {
        Technetium sol = new Technetium();

        long time = 0;

        time = System.currentTimeMillis();

        System.out.println(sol.solution(A));

        System.out.println("time: " + (System.currentTimeMillis() - time) / 1000000.0);
    }

    public static void main(String[] args) {       
        int[][] A = new int[][] { {9, 9, 7}, {9, 7, 2}, {6, 9, 5}, {9, 1, 2} };
        test(A);
        
        int[][] B = new int[][] { {1}, {2}, {3}, {4}, {5}, {6} };
        test(B);

        int[][] C = new int[][] { {1, 2, 3, 4, 5, 6} };
        test(C);        
        
        Random rng = new Random();
        int[][] R = new int[1000][1000];
        for(int i = 0; i < R.length; i++) {
            for(int j = 0; j < R[i].length; j++) {
                R[i][j] = rng.nextInt(2) + 8;
            }   
        }
        test(R);
    }
}
