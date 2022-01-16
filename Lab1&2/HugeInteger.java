package lab1;
import java.util.Arrays;
import java.util.Random;

public class HugeInteger {
    // true: positive number or 0
    // false: negative number
    boolean positive = true;
    // the integer's absolute value
    int[] value = null;

    public HugeInteger(String val) {
        if (val == null || val.isEmpty()) {
            throw new RuntimeException("error");
        }
        int beginCheck = 0;
        if (val.charAt(0) == '-') {
            positive = false;
            // digits must exist
            if (val.length() == 1) {
                throw new RuntimeException("error");
            }
            beginCheck = 1;
        }

        // notice for preceeding zero.
        for (int i = beginCheck; i < val.length(); i++) {
            char c = val.charAt(i);
            if (!('0' <= c && c <= '9')) {
                throw new RuntimeException("error");
            }
            if (c != '0') {
                break;
            } else {
                beginCheck++;
            }
        }
        // digits length
        int length = val.length() - beginCheck;
        if (length == 0) {
            value = new int[1];
            // when it is 0, positive should be true
            positive = true;
        } else {
            value = new int[length];
            int valueIdx = 0;

            for (int i = beginCheck; i < val.length(); i++, valueIdx++) {
                char c = val.charAt(i);
                if (!('0' <= c && c <= '9')) {
                    throw new RuntimeException("error");
                }
                value[valueIdx] = (int) (c - '0');
            }
        }
    }

    public HugeInteger(int n) {
        if (n < 1) {
            throw new RuntimeException("error");
        }
        positive = true;
        value = new int[n];
        Random rand = new Random();
        // the first number's range[1,9]
        value[0] = rand.nextInt(9) + 1;
        for (int i = 1; i < n; i++) {
            // other number's range[0,9]
            value[i] = rand.nextInt(10);
        }
    }

    public HugeInteger add(HugeInteger h) {
        HugeInteger result = new HugeInteger();
        if (this.positive == h.positive) {
            result.positive = this.positive;
            result.value = add2Value(this.value, h.value);
        } else {
            int cmpValue = compare2Value(this.value, h.value);
            result.positive = this.positive;
            if (cmpValue >= 0) {
                result.value = substract2Value(this.value, h.value);
            } else {
                result.value = substract2Value(h.value, this.value);
                result.positive = !result.positive;
            }

            if (result.isZero()) {
                // when it is 0, positive should be true
                result.positive = true;
            }
        }
        return result;
    }

    public HugeInteger subtract(HugeInteger h) {
        HugeInteger negH = new HugeInteger();
        negH.positive = !h.positive;
        negH.value = Arrays.copyOf(h.value, h.value.length);
        if (negH.isZero()) {
            // when it is 0, positive should be true
            negH.positive = true;
        }
        return this.add(negH);
    }

    public HugeInteger multiply(HugeInteger h) {
        HugeInteger result = new HugeInteger();
        if (this.isZero() || h.isZero()) {
            result = makeZeroInteger();
        }
        result.positive = (this.positive == h.positive);
        result.value = multiply2Value(this.value, h.value);
        return result;
    }

    public int compareTo(HugeInteger h) {
        if (this.positive != h.positive) {
            if (this.positive) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (this.positive) {
                return compare2Value(this.value, h.value);
            } else {
                return -compare2Value(this.value, h.value);
            }
        }
    }

    public String toString() {
        if (isZero()) {
            return "0";
        }
        String result = "";
        if (!positive) {
            result += "-";
        }
        for (int i = 0; i < value.length; i++) {
            result += (char) (value[i] + '0');
        }
        return result;
    }

    //The following are helper functions

    // help to check whether it is 0 or not.
    private boolean isZero() {
        return value.length == 1 && value[0] == 0;
    }

    // -1: v1 < v2
    // 0: v1 = v2
    // 1: v1 > v2
    private static int compare2Value(int[] v1, int v2[]) {
        if (v1.length > v2.length) {
            return 1;
        } else if (v1.length < v2.length) {
            return -1;
        } else {
            for (int i = 0; i < v1.length; i++) {
                if (v1[i] < v2[i]) {
                    return -1;
                } else if (v1[i] > v2[i]) {
                    return 1;
                }
            }
            return 0;
        }
    }

    // 0211 ----> 211
    // 1234 ----> 1234
    private static int[] removePreceedingZeros(int[] v) {
        int baseIndex = 0;
        while (baseIndex < v.length && v[baseIndex] == 0) {
            baseIndex++;
        }
        int length = v.length - baseIndex;
        if (length == 0) {
            return new int[] { 0 };
        } else {
            int ri = 0;
            int[] result = new int[length];
            for (int i = baseIndex; i < v.length; i++) {
                result[ri] = v[i];
                ri++;
            }
            return result;
        }
    }

    private static int[] add2Value(int[] v1, int[] v2) {
        // carry at most once when adding 2 numbers
        int[] resultValue = new int[Math.max(v1.length, v2.length) + 1];
        int resultBaseIndex = resultValue.length - 1;
        int v1BaseIndex = v1.length - 1;
        int v2BaseIndex = v2.length - 1;
        int carry = 0;
        // process same part for v1 and v2
        while (v1BaseIndex >= 0 && v2BaseIndex >= 0) {
            resultValue[resultBaseIndex] = carry + v1[v1BaseIndex] + v2[v2BaseIndex];
            if (resultValue[resultBaseIndex] >= 10) {
                carry = 1;
                resultValue[resultBaseIndex] %= 10;
            } else {
                carry = 0;
            }
            resultBaseIndex--;
            v1BaseIndex--;
            v2BaseIndex--;
        }
        // if v1 is longer
        while (v1BaseIndex >= 0) {
            resultValue[resultBaseIndex] = carry + v1[v1BaseIndex];
            if (resultValue[resultBaseIndex] >= 10) {
                carry = 1;
                resultValue[resultBaseIndex] %= 10;
            } else {
                carry = 0;
            }
            resultBaseIndex--;
            v1BaseIndex--;
        }

        // if v2 is longer
        while (v2BaseIndex >= 0) {
            resultValue[resultBaseIndex] = carry + v2[v2BaseIndex];
            if (resultValue[resultBaseIndex] >= 10) {
                carry = 1;
                resultValue[resultBaseIndex] %= 10;
            } else {
                carry = 0;
            }
            resultBaseIndex--;
            v2BaseIndex--;
        }
        if (carry > 0) {
            // resultBaseIndex must be 0 here.
            // carry must be 1 here
            resultValue[resultBaseIndex] = 1;
        }
        return removePreceedingZeros(resultValue);
    }

    // assumption:
    // compare2Value(v1, v2) == 0 or 1.
    private static int[] substract2Value(int[] v1, int[] v2) {
        int[] resultValue = new int[v1.length];
        int carry = 0;
        int resultBaseIndex = resultValue.length - 1;
        int v1BaseIndex = v1.length - 1;
        int v2BaseIndex = v2.length - 1;
        // process same part for v1 and v2
        while (v1BaseIndex >= 0 && v2BaseIndex >= 0) {
            resultValue[resultBaseIndex] = v1[v1BaseIndex] - carry - v2[v2BaseIndex];
            if (resultValue[resultBaseIndex] < 0) {
                carry = 1;
                resultValue[resultBaseIndex] += 10;
            } else {
                carry = 0;
            }
            resultBaseIndex--;
            v1BaseIndex--;
            v2BaseIndex--;
        }
        // if v1 is longer
        while (v1BaseIndex >= 0) {
            resultValue[resultBaseIndex] = v1[v1BaseIndex] - carry;
            if (resultValue[resultBaseIndex] < 0) {
                carry = 1;
                resultValue[resultBaseIndex] += 10;
            } else {
                carry = 0;
            }
            resultBaseIndex--;
            v1BaseIndex--;
        }
        return removePreceedingZeros(resultValue);
    }

    private int[] multiply2Value(int[] v1, int[] v2) {
        int[] result = new int[] { 0 };
        Object[] cache = new Object[10];
        //I cache v1*1, v1*2, ...., v1*9.
        for (int i = 1; i < 10; i++) {
            if (i == 1) {
                cache[i] = v1;
            } else {
                cache[i] = add2Value((int[]) cache[i - 1], v1);
            }
        }
        for (int i = v2.length - 1; i >= 0; i--) {
            if (v2[i] > 0) {
                result = add2Value(appendingZeros((int[]) cache[v2[i]], v2.length - 1 - i), result);
            }
        }
        return result;
    }

    private int[] appendingZeros(int[] v, int zeros_count) {
        int[] result = new int[v.length + zeros_count];
        System.arraycopy(v, 0, result, 0, v.length);
        if (result[0] != 0) {
            return result;
        } else {
            return removePreceedingZeros(result);
        }
    }

    // only for inner useage
    private HugeInteger() {
    }

    private static HugeInteger makeZeroInteger() {
        HugeInteger result = new HugeInteger();
        result.positive = true;
        result.value = new int[] { 0 };
        return result;
    }
}
