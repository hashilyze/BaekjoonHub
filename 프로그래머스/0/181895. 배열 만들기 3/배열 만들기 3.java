import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
    	List<Integer> origin = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<Integer> li = new ArrayList<>();
        for(int[] interval : intervals) {
        	li.addAll(origin.subList(interval[0], interval[1] + 1));
        }
        return li.stream().mapToInt(x->x).toArray();
    }
}