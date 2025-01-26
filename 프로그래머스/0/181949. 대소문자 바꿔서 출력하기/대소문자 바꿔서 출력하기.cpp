#include <bits/stdc++.h>

using namespace std;

int main(void) {
    std::string str;
    std::cin >> str;
    for(char ch : str){
        if(std::isupper(ch)) std::cout << (char)(ch - 'A' + 'a');
        else std::cout << (char)(ch - 'a' + 'A');
    }
    
    return 0;
}