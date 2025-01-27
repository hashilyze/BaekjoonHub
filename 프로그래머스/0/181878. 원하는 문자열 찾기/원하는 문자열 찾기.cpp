#include <bits/stdc++.h>

using namespace std;

int solution(string myString, string pat) {
    return std::regex_search(myString, std::regex(pat, regex_constants::icase)) ? 1 : 0;
}