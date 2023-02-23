import java.util.Comparator;

public class TrueListComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        String s1Lower = s1.toLowerCase();
        String s2Lower = s2.toLowerCase();
        int len1 = s1.length();
        int len2 = s2.length();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            char ch1 = s1Lower.charAt(i);
            char ch2 = s2Lower.charAt(j);
            if (ch1 >= '0' && ch1 <= '9' && ch2 >= '0' && ch2 <= '9') {
                int num1 = 0;
                int num2 = 0;
                while (i < len1 && s1.charAt(i) >= '0' && s1.charAt(i) <= '9') {
                    num1 = num1 * 10 + (s1.charAt(i) - '0');
                    i++;
                }
                while (j < len2 && s2.charAt(j) >= '0' && s2.charAt(j) <= '9') {
                    num2 = num2 * 10 + (s2.charAt(j) - '0');
                    j++;
                }
                if (num1 != num2) {
                    return Integer.compare(num1, num2);
                }
            } else {
                if (ch1 != ch2) {
                    return Character.compare(ch1, ch2);
                }
                i++;
                j++;
            }
        }
        return Integer.compare(len1, len2);
    }
}