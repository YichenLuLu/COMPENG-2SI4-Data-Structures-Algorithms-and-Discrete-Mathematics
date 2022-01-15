package HugeInteger;

import java.math.BigInteger; 
import java.util.Random;




/*public class testClass {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		char str;
		int bigNum = 482947929;
		
		HugeInteger hugeNum = new HugeInteger("99");
		HugeInteger hugeNum2 = new HugeInteger("99");
		
		System.out.println(hugeNum.compareTo(hugeNum2));
		
//		str = s.next().charAt(0);
//		bigNum = str;s
//		System.out.println(hugeNum.add(hugeNum2));
		HugeInteger sum = hugeNum.multiply(hugeNum2);
//		System.out.println(hugeNum.compareTo(hugeNum2));
		System.out.println(sum.toString());
//		System.out.println(hugeNum2.toString());
		s.close();
		
	}
}*/

public class TestHugeInteger{
    public static void main(String args[]){

        HugeInteger x1;
        HugeInteger x2;
        HugeInteger x3;

        BigInteger y1;
        BigInteger y2;
        BigInteger y3;

        int fails = 0;
        int each_fail = 0;

        int[] sizes = new int[] {1,(int)1e3,(int)5e3,(int)1e4};

        System.out.println("Testing constructors and toString()");
       /* System.out.println("Expected output - ");
        System.out.println("S1: String in the middle exception caught\n");
        System.out.println("S2: String at the end exception caught\n");
        System.out.println("S3: String in the beginning exception caught\n");
        System.out.println("S4: String with leading zeros exception caught\n");
        System.out.println("S5: Negative String with exception caught\n");
        System.out.println("S6: Empty String exception caught\n");
        System.out.println("R1: Size zero exception caught\n");
        System.out.println("R2: Negative size exception caught\n");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
        System.out.println("Current Output - ");*/
        try{
            for(int i=0;i<sizes.length;i++){
                int size = sizes[i];
                y1 = new BigInteger(randomString(size));
                String number1 = y1.toString();
                x1 = new HugeInteger(number1);

                if(!(number1.equals(x1.toString()))){
                    System.out.println("Test failed for toString()");
                    if(size<=100){
                        System.out.println("Input string given - "+number1);
                        System.out.println("Output from toString() - "+x1.toString());
                    }
                    fails++;
                    each_fail++;
                }
            }
            x1 = new HugeInteger("-0");
            if(!(new String("0").equals(x1.toString()))){
                System.out.println("Test failed for leading negative sign\nExpected: 0 Current: -0");
                fails++;
                each_fail++;
            }
            
           
        }
        catch(Exception e){
            System.out.println(e);
            fails++;
            each_fail++;
        }

        try{
            for(int i=0;i<sizes.length;i++){
                int size = sizes[i];
                x1 = new HugeInteger(size);
                /*if(size<=100){
                    System.out.println("Random Huge Integer: "+x1.toString());
                }*/
            }
        }
        catch(Exception e){
            System.out.println(e);
            fails++;
            each_fail++;
        }

        //Invalid Inputs 
        try{
            x1 = new HugeInteger("7097209430973667806-014363363584489148645");
        }
        catch(Exception e){
            System.out.println("S1: String in the middle exception caught\n");
        }
        try{
            x1 = new HugeInteger("7097209430973667806014363363584489148645!");
        }
        catch(Exception e){
            System.out.println("S2: String at the end exception caught\n");
        }
        try{
            x1 = new HugeInteger(",7097209430973667806014363363584489148645");
        }
        catch(Exception e){
            System.out.println("S3: String in the beginning exception caught\n");
        }
        try{
            x1 = new HugeInteger("000-7097209430973667806014363363584489148645");
        }
        catch(Exception e){
            System.out.println("S4: String with leading zeros exception caught\n");
        }
        try{
            x1 = new HugeInteger("-,7097209430973667806014363363584489148645");
        }
        catch(Exception e){
            System.out.println("S5: Negative String with exception caught\n");
        }
        try{
            x1 = new HugeInteger("");
        }
        catch(Exception e){
            System.out.println("S6: Empty String exception caught\n");
        }

        //Random Constructor Test cases 

        try{
            x1 = new HugeInteger(10);
            x1 = new HugeInteger(100);
        }
        catch(Exception e){
            System.out.println("Random constructor throws exception for valid input");
        }

        try{
            x1 = new HugeInteger(0);
        }
        catch(Exception e){
            System.out.println("R1: Size zero exception caught\n");
        }
        try{
            x1 = new HugeInteger(-12);
        }
        catch(Exception e){
            System.out.println("R2: Negative size exception caught\n");
        }
        if(each_fail!=0){
            System.out.println("Test failed for constructors or toString()");
        }
        else{
            System.out.println("PASS");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
        }
        each_fail = 0;

        //Checking Add/Subtract operations 

        System.out.println("Testing addition/subtraction\n");

        try{
            for(int i=0;i<sizes.length;i++){
                int size = sizes[i];
                String number1 = randomString(size);
                String number2 = randomString(size);
                
                y1 = new BigInteger(number1);
                y2 = new BigInteger(number2);
                y3 = y1.add(y2);

                x1 = new HugeInteger(number1);
                x2 = new HugeInteger(number2);
                x3 = x1.add(x2);

                if(!(y3.toString().equals(x3.toString()))){
                    System.out.println("Error in addition");
                    if(size<=100){
                        System.out.println("Expected output: "+number1+" + "+number2+" = "+y3.toString());
                        System.out.println("Current output: "+x3.toString());
                    }
                    fails++;
                    each_fail++;
                }
                number1 = randomString(size);
                number2 = randomString(size);
                
                y1 = new BigInteger(number1);
                y2 = new BigInteger(number2);
                y3 = y1.subtract(y2);

                x1 = new HugeInteger(number1);
                x2 = new HugeInteger(number2);
                x3 = x1.subtract(x2);

                if(!(y3.toString().equals(x3.toString()))){
                    System.out.println("Error in subtraction");
                    if(size<=100){
                        System.out.println("Expected output: "+number1+" - "+number2+" = "+y3.toString());
                        System.out.println("Current output: "+x3.toString());
                    }
                    fails++;
                    each_fail++;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            each_fail++;
            fails++;
        }
        if(each_fail!=0){
            System.out.println("Test failed for addition/subtraction");
        }
        else{
            System.out.println("PASS");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
        }
        each_fail = 0;

        //Test multiply 
        
        System.out.println("Testing multiplication\n");

        try{
            for(int i=0;i<sizes.length-2;i++){
                int size = sizes[i];
                String number1 = randomString(size);
                String number2 = randomString(size);
                
                y1 = new BigInteger(number1);
                y2 = new BigInteger(number2);
                y3 = y1.multiply(y2);
                
                x1 = new HugeInteger(number1);
                x2 = new HugeInteger(number2);
                x3 = x1.multiply(x2);

                if(!(y3.toString().equals(x3.toString()))){
                    System.out.println("Error in multiplication");
                    if(size<=100){
                        System.out.println("Expected output: "+number1+" * "+number2+" = "+y3.toString());
                        System.out.println("Current output: "+x3.toString());
                    }
                    fails++;
                    each_fail++;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            each_fail++;
            fails++;
        }
        if(each_fail!=0){
            System.out.println("Test failed for multiplication");
        }
        else{
            System.out.println("PASS");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
        }
        each_fail = 0;

        //Testing comparision 

        System.out.println("Testing compareTo()");

        try{
            for(int i=0;i<sizes.length;i++){
                int size = sizes[i];
                String number1 = randomString(size);
                String number2 = randomString(size);
                
                y1 = new BigInteger(number1);
                y2 = new BigInteger(number2);
                int k1 = y1.compareTo(y2);

                x1 = new HugeInteger(number1);
                x2 = new HugeInteger(number2);
                int k2 = x1.compareTo(x2);

                if(k1!=k2){
                    System.out.println("Error in compareTo()");
                    if(size<=100){
                        System.out.println("Expected output: "+number1+" compareTo "+number2+" = "+k1);
                        System.out.println("Current output: "+k2);
                    }
                    fails++;
                    each_fail++;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            each_fail++;
            fails++;
        }
        if(each_fail!=0){
            System.out.println("Test failed for compareTo()");
        }
        else{
            System.out.println("PASS");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
        }
        each_fail = 0;
        
        if(fails==0){
            System.out.println("PASS: ALL");
        }
        else{
            System.out.println("Tests FAILED for some cases");
        }

    }

    public static String randomString(int size){
        boolean neg = false;
        Random random = new Random();

        String rand_string = "";
        for(int i=1;i<=size;i++){
            int next_digit = random.nextInt(9) + 1;
            rand_string = rand_string+next_digit;
        }
        if(random.nextInt(9)<5){
            rand_string = "-"+rand_string;
        }
        return rand_string;
    }
}
            




