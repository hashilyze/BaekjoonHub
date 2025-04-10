#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

void GetLps(const std::string& text, std::vector<int>& out_lps){
    int n = text.size();
    out_lps.resize(n, 0);

    int matched = 0;
    for(int next = 1; next < n; ++next){
        while(matched > 0 && text[next] != text[matched]) matched = out_lps[matched - 1];
        if(text[next] == text[matched]) out_lps[next] = ++matched;
    }
}

void GetZ(const std::string& text, std::vector<int>& out_z){
    int n = text.size();
    out_z.resize(n, 0);
    out_z[0] = n;

    int lbound = 0, rbound = 0;
    for(int pos = 1; pos < n; ++pos){
        if(pos > rbound){
            lbound = rbound = pos;
            while(rbound < n && text[rbound - lbound] == text[rbound]){
                ++rbound;
            }
            out_z[pos] = rbound - lbound;
            --rbound;
        } else{
            int k = pos - lbound;
            if(pos + out_z[k] < rbound + 1){
                out_z[pos] = out_z[k];
            } else{
                lbound = pos;
                while(rbound < n && text[rbound - lbound] == text[rbound]){
                    ++rbound;
                }
                out_z[pos] = rbound - lbound;
                --rbound;
            }
        }
    }
}

void Solve(const std::string& text, std::vector<std::pair<int, int>>& out_counts){
    int length = text.size();
    std::vector<int> zArr; GetZ(text, zArr);

    std::vector<int> count(length + 1, 0);
    for(int i = 0; i < length; ++i) ++count[zArr[i]];
    for(int i = length; i > 0; --i) count[i - 1] += count[i];

    std::vector<int> lps; GetLps(text, lps);
    int ps = length;
    while(ps){
        out_counts.emplace_back(ps, count[ps]);
        ps = lps[ps - 1];
    }
    std::reverse(out_counts.begin(), out_counts.end());
}

int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL); std::cout.tie(NULL);
    // std::ifstream fin("sol.inp");
    // std::ofstream fout("sol.out");
    
    std::istream& in = std::cin;
    std::ostream& out = std::cout;
    

    std::string text;
    in >> text;

    std::vector<std::pair<int, int>> counts;
    Solve(text, counts);

    out << counts.size()<< '\n';
    for(auto count : counts){
        out << count.first << ' ' << count.second << '\n';
    }

    return 0;
}