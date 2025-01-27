import java.io.*;
import java.util.*;

class Solution {
    public String solution(String myString) {
        StringBuilder sb = new StringBuilder(myString.length());
        for(char ch : myString.toCharArray()){
            if(ch == 'a') sb.append('A'); 
            else if('A' < ch && ch <= 'Z') sb.append(Character.toLowerCase(ch));
            else sb.append(ch);
        }
        return sb.toString();
    }
}