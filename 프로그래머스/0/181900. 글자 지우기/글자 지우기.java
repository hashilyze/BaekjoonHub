class Solution {
    public String solution(String my_string, int[] indices) {
        String[] strs = my_string.split("");
        for(int idx : indices) strs[idx] = "";
        return String.join("", strs);
    }
}