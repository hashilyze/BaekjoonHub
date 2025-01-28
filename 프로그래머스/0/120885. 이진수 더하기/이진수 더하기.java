class Solution {
    public String solution(String bin1, String bin2) {
        int i1 = 0, i2 = 0;
        for(int i = 0; i < bin1.length(); ++i){
            i1 += (bin1.charAt(i) - '0') << (bin1.length() - i - 1);
        }
        for(int i = 0; i < bin2.length(); ++i){
            i2 += (bin2.charAt(i) - '0') << (bin2.length() - i - 1);
        }
        int i = i1 + i2;
        if(i == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        while(i > 0){
            sb.append(i % 2 == 0 ? '0' : '1');
            i >>= 1;
        }
        return sb.reverse().toString();
    }
}