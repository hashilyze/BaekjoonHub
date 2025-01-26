class Solution {
    public String solution(String my_string, int[] indices) {
        StringBuilder sb = new StringBuilder(my_string);
        for(int idx : indices) {
        	sb.setCharAt(idx, ' ');
        }
        return String.join("", sb.toString().split(" "));
    }
}