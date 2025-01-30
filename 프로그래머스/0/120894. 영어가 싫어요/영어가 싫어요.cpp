#include <bits/stdc++.h>

using namespace std;

long long solution(string numbers) {
    // zero
    
    // one
    
    // two
    // three
    
    // four
    // five
    
    // sive
    // seven
    
    // eight
    
    // nine
    
    long long answer = 0;
    for(int i = 0; i < numbers.size();){
        answer *= 10;
        if(numbers[i] == 'z') {
            answer += 0LL;
            i += 4;
        } else if(numbers[i] == 'o'){
            answer += 1LL;
            i += 3;
        } else if(numbers[i] == 't'){
            if(numbers[i + 1] == 'w'){ // 2
                answer += 2LL;
                i += 3;  
            } else { // 3
                answer += 3LL;
                i += 5;  
            }
        } else if(numbers[i] == 'f'){
            if(numbers[i + 1] == 'o'){ // 4
                answer += 4LL;
                i += 4;  
            } else { // 5
                answer += 5LL;
                i += 4;  
            }
        } else if(numbers[i] == 's'){
            if(numbers[i + 1] == 'i'){ // 6
                answer += 6LL;
                i += 3;  
            } else { // 7
                answer += 7LL;
                i += 5;  
            }
        } else if(numbers[i] == 'e'){ // 8
            answer += 8LL;
            i += 5;
        } else if(numbers[i] == 'n'){ // 9
            answer += 9LL;
            i += 4;
        }
    }
    return answer;
}