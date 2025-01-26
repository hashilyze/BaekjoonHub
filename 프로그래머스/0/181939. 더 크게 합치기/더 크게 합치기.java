class Solution {
    public int solution(int a, int b) {
        int case1 = Integer.parseInt("" + a + b);
        int case2 = Integer.parseInt("" + b + a);
        return case1 < case2 ? case2 : case1;
    }
}