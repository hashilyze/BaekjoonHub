#include <bits/stdc++.h>

using namespace std;

string solution(string my_string, string alp) {
    char from = alp[0], to = std::toupper(from);
    for(char &ch : my_string){
        if(ch == from) ch = to;
    }
    return my_string;
}