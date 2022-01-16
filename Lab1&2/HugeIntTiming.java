package lab1and2;

public class HugeIntTiming {
    public static void main(String[] args) {
        int[] nValues = new int[]{10,100,500,1000,5000,10000};
        HugeInteger huge1, huge2, huge3;
        double startTime, endTime, runTime = 0.0;
        int MAXNUMINTS = 110, MAXRUN = 100000;
        int compareVal = 0;

        System.out.println("Addition");
        for (int n : nValues) {
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                startTime = System.nanoTime();

                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge3 = huge1.add(huge2);
                }

                endTime = System.nanoTime();
                runTime += (endTime - startTime) / MAXRUN / 1e9;
            }
            runTime /= MAXNUMINTS;
            System.out.println("running time at n = " + n + " is " + runTime);
        }
        System.out.println();

        System.out.println("Subtraction");
        for (int n : nValues) {
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                startTime = System.nanoTime();

                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge3 = huge1.subtract(huge2);
                }

                endTime = System.nanoTime();
                runTime += (endTime - startTime) / MAXRUN / 1e9;
            }
            runTime /= MAXNUMINTS;
            System.out.println("running time at n = " + n + " is " + runTime);
        }
        System.out.println();

        MAXRUN = 1000000;

        System.out.println("Comparison");
        for (int n : nValues) {
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                startTime = System.nanoTime();

                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    compareVal = huge1.compareTo(huge2);
                }

                endTime = System.nanoTime();
                runTime += (endTime - startTime) / MAXRUN / 1e9;
            }
            runTime /= MAXNUMINTS;
            System.out.println("running time at n = " + n + " is " + runTime);
        }
        System.out.println();

        MAXRUN = 10000;

        System.out.println("Multiplication");
        nValues = new int[]{10,100,500,1000};
        for (int n : nValues) {
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                startTime = System.nanoTime();
                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge3 = huge1.multiply(huge2);
                }
                endTime = System.nanoTime();
                runTime += (endTime - startTime) / MAXRUN / 1e9;
            }
            runTime /= MAXNUMINTS;
            System.out.println("running time at n = " + n + " is " + runTime);
        }
    }
}
