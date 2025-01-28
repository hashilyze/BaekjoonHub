class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        int[] answer = new int[num2 - num1 + 1];
        for(int i = 0, n = num2 - num1 + 1; i < n; ++i) answer[i] = numbers[num1 + i];
        return answer;
    }
}