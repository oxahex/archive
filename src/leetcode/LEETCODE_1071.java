package leetcode;

/**
 * @date    2023-07-30
 * @title   Greatest Common Divisor of Strings(1071)
 * @tags    String, Math
 * @input
 * Given two strings str1 and str2,
 * @output
 * return the largest string x such that x divides both str1 and str2.
 */
public class LEETCODE_1071 {

    /**
     * <pre>
     *     BF 방식과 GCD를 이용하는 방식 기록
     *
     *     BF
     *      m = str1.length()
     *      n = str2.length()
     *      m, n 중 더 짧은 문자열의 모든 접두사를 확인.
     *      확인해야 하는 접두사의 개수는 m 또는 n개임. (둘 중 짧은 것)
     *      그리고 다른 긴 문자열과 이 접두사를 비교해야 하므로, 각 접두사 당 O(m + n)
     *      따라서 전체 시간 복잡도는 O(min(m, n) * (m + n))
     *
     *     GCD
     *      최대공약수를 이용할 수 있음.
     *      m + n 문자열과 n + m 문자열이 다르면 나눌 수 있는 관계가 아님.
     *      같은 base로 만들어진 문자열이라면 base 반복의 횟수 차이지 문자열이 다를 수 없으므로 둘은 같아야 함.
     *      따라서 if (!(str1 + str2).equals(str2 + str1)) return ""; 로 전처리 작업.
     *
     *      두 문자열은 같은 길이의 문자로 나눌 수 있어야 하므로,
     *      두 문자열 길이의 최대 공약수 만큼의 길이를 가지는 문자열로 반드시 나눌 수 있음.
     * </pre>
     * */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
