#include <bits/stdc++.h>

using namespace std;

int main(void) {
    std::string str;
    std::cin >> str;
    for(char ch : str){
        if(std::isupper(ch)) std::cout << (char)std::tolower(ch);
        else std::cout << (char)std::toupper(ch);
    }
    
    return 0;
}