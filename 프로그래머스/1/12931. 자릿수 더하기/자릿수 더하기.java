import java.util.*;

public class Solution {
    public int solution(int n) {
        int sum = 0;
        for(; n > 0; n /= 10) sum += n % 10;
        return sum;
    }
}