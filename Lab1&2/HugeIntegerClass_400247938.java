package lab1;

public class HugeInteger
{
    private boolean isNegative;
    private int[] data;
    private int dataLength;

    public HugeInteger(String val)
    {
        // remove negative sign
        if (val.charAt(0) == '-')  //"2345"
        {
            isNegative = true;
            val = val.substring(1);
        }

        dataLength = val.length();
        data = new int[dataLength];  //[xxxx]
        for (int i = 0; i < dataLength; i++)
        {
            data[i] = Integer.parseInt(String.valueOf(val.charAt(dataLength - (i + 1))));//make each character from the string to transfer into the int array "data"//5432
        }
    }
    public HugeInteger(int n) throws Exception{//5
    	  data=new int[n];//[xxxxx]
    	  if(n>=1) {
    	   for(int i=0; i<n;i++) {
    	    data[i] = (int)(Math.random()*10);
    	   }
    	  }
    	  else {
    		  throw new Exception("Invalid size");
    	  }
    	 }

    public HugeInteger(int[] d, int l)
    {
        data = d;
        dataLength = l;
    }

    public HugeInteger add(HugeInteger h)
    {
        HugeInteger result;
        int resultSize = this.dataLength > h.dataLength ? this.dataLength + 1 : h.dataLength + 1;
        int[] resultList = new int[resultSize];//the size of the summation
        int index = 0;
        int remaining = 0;
        while (index < this.dataLength && index < h.dataLength)
        {
            int currentSum = this.data[index] + h.data[index] + remaining;//2345+234
            resultList[index] += currentSum % 10;
            remaining = currentSum / 10;
            index++;
        }
        while (index < this.dataLength)
        {
            int currentSum = this.data[index] + remaining;
            resultList[index] += currentSum % 10;
            remaining = currentSum / 10;
            index++;
        }
        while (index < h.dataLength)
        {
            int currentSum = h.data[index] + remaining;
            resultList[index] += currentSum % 10;
            remaining = currentSum / 10;
            index++;
        }
        if (remaining != 0)
        {
            resultList[index] = remaining;
        }
        result = new HugeInteger(resultList, resultSize);
        return result;
    }

    public String toString()
    {
        String result = "";
        if (isNegative)
        {
            result += "-";//5432
        }
        for (int i = dataLength - 1; i >= 0; i--)
        {
            result += data[i];
        }
       
        if (result.charAt(0) == '0' && result.length() > 1)//if the digit is 0 and the result length is greater than 1,then get rid of 0 of the array
        {
            result = result.substring(1);
        }
        return result;
    }
}