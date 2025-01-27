import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] arr = myStr.split("a|b|c");
        List<String> li = new ArrayList<String>();
        for(String str : arr) {
        	if(!str.isEmpty()) li.add(str);
        }
        if(li.size() > 0){
            return li.toArray(new String[li.size()]);    
        } else{
            return  new String[]{"EMPTY"};
        }
        
    }
}