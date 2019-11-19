import java.util.*;
import 	java.lang.Math;

/**
 * FlippingMatrix
 * score: 65%
 * correctness: 100%
 * performance: 33%
 * problem: 
 * 	- performance test: running time around 0.7 sec, expected around 0.5 sec,  
 */
class Niobium
 {
	public int solution(int[][] A) {
		int N = A.length;
		int M = A[0].length;

		HashMap<String, Integer> map = new HashMap<>();
		int maxValue = 0;

		// one row
		if (N == 1) {
			return 1;
		}

		// one column
		if (M == 1) {
			return N;
		}

		/**
		 * 1023				(10 digit binary = 	4 digit integer >> 6)
		 * 1048575			(20 				7 				>> 13)
		 * 1099511627775	(40 				13				>> 17)
		 */
		int threshold = 20;
		String format = "%08d"; // 10 digit max 1023
		StringBuilder sbs0 = new StringBuilder();
		StringBuilder sbs1 = new StringBuilder();
		StringBuilder sbb0 = new StringBuilder();
		StringBuilder sbb1 = new StringBuilder();

		for (int i = 0; i < N; i++) {
			sbs0.setLength(0);
			sbs1.setLength(0);
			sbb0.setLength(0);
			sbb1.setLength(0);

			for (int j = 0; j < M; j++) {
				sbb0.append( (A[i][j] - 1) * -1 );
				sbb1.append( A[i][j] );

				if (j % threshold == threshold-1) {
					sbs0.append( String.format(format, Integer.parseInt(sbb0.toString(), 2)) );
					sbs1.append( String.format(format, Integer.parseInt(sbb1.toString(), 2)) );

					sbb0.setLength(0);
					sbb1.setLength(0);
				}
			}		
			if ((M-1) % threshold != threshold - 1) {
				sbs0.append( String.format(format, Integer.parseInt(sbb0.toString(), 2)) );
				sbs1.append( String.format(format, Integer.parseInt(sbb1.toString(), 2)) );
			}

			String s0 = sbs0.toString();
			String s1 = sbs1.toString();

			if (map.containsKey(s1)) {
				map.replace(s1, map.get(s1) + 1);
			} else {
				map.put(s1, 1);
			}
			maxValue = Math.max(maxValue, map.get(s1));

			if (map.containsKey(s0)) {
				map.replace(s0, map.get(s0) + 1);
			} else {
				map.put(s0, 1);
			}
			maxValue = Math.max(maxValue, map.get(s0));
		}

		return maxValue;
    }
	
    public static void test(int[][] A) {
        Niobium sol = new Niobium();

        long time = 0;

        time = System.currentTimeMillis();

        System.out.println(sol.solution(A));

        System.out.println("time: " + (System.currentTimeMillis() - time) / 1000000.0);
    }

    public static void main(String[] args) {     
		
		int[][] A = { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, {1, 0, 1, 1} };
		test(A);

		int[][] B = { { 0, 1, 0, 1 }, { 1, 0, 1, 0 }, {0, 1, 0, 1}, {1, 0, 1, 0} };
		test(B);		
		
        Random rng = new Random();
        int[][] R = new int[1000][1000];
        for(int i = 0; i < R.length; i++) {
            for(int j = 0; j < R[i].length; j++) {
                R[i][j] = rng.nextInt(2);
            }   
        }
		test(R);
		
	}
}
