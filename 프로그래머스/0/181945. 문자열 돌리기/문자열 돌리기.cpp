#include <iostream>
#include <string>

using namespace std;

int main(void) {
    string str;
    cin >> str;
    for(char ch : str) std::cout << ch << '\n';
    return 0;
}