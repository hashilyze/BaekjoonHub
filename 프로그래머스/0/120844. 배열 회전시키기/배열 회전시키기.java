class Solution {
    public int[] solution(int[] numbers, String direction) {
        int a;
        if(direction.equals("right")){
            a = numbers.length - 1;
        } else{
            a = 1;
        }
        
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; ++i){
            answer[i] = numbers[(a + i) % numbers.length];
        }
        return answer;
    }
}