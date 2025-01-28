class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = new int[2];
        for(int e : num_list){
            ++answer[e % 2];
        }
        return answer;
    }
}