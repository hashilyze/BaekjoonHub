#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

void GetPi(const std::string& text, std::vector<int>& out_pi){
    int n = text.size();
    out_pi.resize(n, 0);

    int matched = 0;
    for(int next = 1; next < n; ++next){
        while(matched && text[next] != text[matched]) matched = out_pi[matched - 1];
        if(text[next] == text[matched]) out_pi[next] = ++matched;
    }
}

void KMP(const std::string& text, const std::string& pattern, std::vector<int>& out_locations){
    int txtLength = text.size(), patLength = pattern.size();
    std::vector<int> pi; GetPi(pattern, pi);

    int matched = 0;
    for(int next = 0; next < txtLength; ++next){
        while(matched && text[next] != pattern[matched]) matched = pi[matched - 1];
        if(text[next] == pattern[matched] && ++matched == patLength) {
            out_locations.push_back(next - matched + 1);
            matched = pi[matched - 1];
        }
    }
}

int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL); std::cout.tie(NULL);
    // std::ifstream fin("sol.inp");
    // std::ofstream fout("sol.out");
    
    std::istream& in = std::cin;
    std::ostream& out = std::cout;
    
    std::string text, pattern;
    std::getline(in, text);
    std::getline(in, pattern);

    std::vector<int> locations;
    KMP(text, pattern, locations);
    out << locations.size() << '\n';
    for(int location : locations){
        out << location + 1 << '\n';
    }

    return 0;
}