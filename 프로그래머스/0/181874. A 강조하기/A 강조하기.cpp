#include <bits/stdc++.h>

using namespace std;

string solution(string myString) {
    for(char &ch : myString){
        if(ch == 'a') ch = 'A';
        else if('A' < ch && ch <= 'Z') ch = std::tolower(ch);
    }
    return myString;
}