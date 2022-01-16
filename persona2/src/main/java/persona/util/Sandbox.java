package persona.util;

import java.util.Random;

public class Sandbox {
	 public static final int NUM_ITERATIONS = 10000000;
	    public static void main(String[] args) {
	        Random rand = new Random(System.nanoTime());

	        int cur;
	        int last = -1;
	        int secondLast = -2;
	        int nDoubles = 0;
	        int nTriples = 0;

	        for (int i = 0; i < NUM_ITERATIONS; i++) {
	            cur = rand.nextInt(100);
	            if (cur == last) {
	                nDoubles++;
	                if (cur == secondLast) nTriples++;
	            }
	            secondLast = last;
	            last = cur;
	        }

	        System.out.println("Doubles: " + nDoubles);
	        System.out.println("Triples: " + nTriples);
	        System.out.println();
	        System.out.println("Double Rate: 1 in " + Math.round(1.0 * NUM_ITERATIONS / nDoubles));
	        System.out.println("Triple Rate: 1 in " + Math.round(1.0 * NUM_ITERATIONS / nTriples));

	        
	    }
}
