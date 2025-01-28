class Solution {
    public int solution(int num, int k) {
        String strNum = "" + num;
        for(int i = 0; i < strNum.length(); ++i){
            if(strNum.charAt(i) - '0' == k) 
                return i + 1;
        }
        return -1;
    }
}