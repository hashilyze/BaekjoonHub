#include <iostream>
#include <vector>
#include <string>

std::vector<std::string> words;
std::vector<std::string> concats;
std::vector<int> permutation;
std::vector<bool> flags;


void GetPi(const std::string& text, std::vector<int>& out_pi){
    int n = text.size();
    out_pi.resize(n, 0);

    int matched = 0;
    for(int next = 1; next < n; ++next){
        while(matched > 0 && text[next] != text[matched]) matched = out_pi[matched - 1];
        if(text[next] == text[matched]) out_pi[next] = ++matched;
    }
}
int KMP(const std::string& text, const std::string& pattern){
    int count = 0;
    int txtLength = text.size() - 1, patLength = pattern.size();
    std::vector<int> pi; GetPi(pattern, pi);
    int matched = 0;
    for(int next = 0; next < txtLength; ++next){
        while(matched > 0 && text[next] != pattern[matched]) matched = pi[matched - 1];
        if(text[next] == pattern[matched] && ++matched == patLength) {
            ++count;
            matched = pi[matched - 1];
        }
    }
    return count;
}

void Permutation(){
    if(permutation.size() == words.size()){
        std::string concat;
        for(int i = 0; i < permutation.size(); ++i){
            concat += words[permutation[i]];
        }
        concats.push_back(concat);
    } else {
        for(int i = 0; i < flags.size(); ++i){
            if(!flags[i]){
                flags[i] = true;
                permutation.push_back(i);
                Permutation();
                permutation.pop_back();
                flags[i] = false;
            }
        }
    }
}

int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL); std::cout.tie(NULL);

    std::istream& in = std::cin;
    std::ostream& out = std::cout;

    int n, k;
    in >> n;
    for(int i = 0; i < n; ++i){
        std::string word;
        in >> word;
        words.push_back(word);
    }
    in >> k;

    permutation.push_back(0);
    flags.resize(words.size(), false);
    flags.front() = true;
    Permutation();

    int count = 0;
    for(const std::string concat : concats){
        if(KMP(concat + concat, concat) == k){
            count += n;
        }
    }
    out << count << '\n';

    return 0;
}