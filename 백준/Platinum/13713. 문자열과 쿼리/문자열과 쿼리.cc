#include <iostream>
#include <string>
#include <vector>


void GetZ(const std::string& text, std::vector<int>& out_z){
    int length = text.size();
    out_z.resize(length, 0);
    out_z[0] = length;
    
    int l = 0, r = 0;
    for(int i = 1; i < length; ++i){
        int k = i - l;
        if(i > r){
            l = r = i;
            while(r < length && text[r - l] == text[r]){
                ++r;
            }
            out_z[i] = r - l;
            --r;
        } else{
            if(out_z[k] < r - i + 1){
                out_z[i] = out_z[k];
            } else{
                l = i;
                while(r < length && text[r - l] == text[r]){
                    ++r;
                }
                out_z[i] = r - l;
                --r;
            }
        }
    }
}

int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL); std::cout.tie(NULL);
    std::istream& in = std::cin;
    std::ostream& out = std::cout;

    std::string text;
    int n, query;
    in >> text >> n;

    std::string rtext(text.crbegin(), text.crend());
    std::vector<int> z; 
    GetZ(rtext, z);
    
    while(n--){
        in >> query;
        out << z[rtext.size() - query] << '\n';
    }

    return 0;
}