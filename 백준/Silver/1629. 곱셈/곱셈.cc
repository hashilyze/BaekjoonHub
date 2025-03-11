#include <iostream>
#include <fstream>
#include <vector>

int FastPow(int base, int pow, int mod){
    if(pow == 0) return 1;
    if(pow % 2) return ((long long)(base % mod) * FastPow(base, pow - 1, mod)) % mod;
    int half = FastPow(base, pow / 2, mod);
    return ((long long)half * half) % mod;
}

int Pow(int base, int pow, int mod){
    long long ret = 1LL;
    for(int i = 0; i < pow; ++i){
        ret = ((ret % mod) * (base % mod)) % mod;
    }
    return ret;
}

int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL);
#ifndef ONLINE_JUDGE
    std::ifstream fin("sol.inp");
    std::ofstream fout("sol.out");
    
    std::istream& in = fin;
    std::ostream& out = fout;
#else 
    std::istream& in = std::cin;
    std::ostream& out = std::cout;
#endif
    long long base, pow, mod;
    in >> base >> pow >> mod;
    out << FastPow(base, pow, mod) << '\n';

    return 0;

}