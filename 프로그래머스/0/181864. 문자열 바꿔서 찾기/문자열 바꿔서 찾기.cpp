#include <bits/stdc++.h>

using namespace std;

int solution(string myString, string pat) {
    for(char &ch : myString){
        ch = (ch - 'A' + 1) % 2 + 'A';
    }
    return myString.find(pat) != std::string::npos ? 1 : 0;
}