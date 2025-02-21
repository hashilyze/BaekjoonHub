#include <iostream>
#include <fstream>
#include <vector>

const int MAX_SIZE = 101;
const int LIMIT = 3;

int n, m;
int values[MAX_SIZE];

int MaxSum(int k, int index = 0, int curSum = 0){
    if(k == 0) {
        return curSum <= m ? curSum : 0;
    } else if(index == n) {
        return 0;
    }

    int max = std::max(MaxSum(k, index + 1, curSum), MaxSum(k - 1, index + 1, curSum + values[index]));
    return max;
}

int main(void){
    std::ios::sync_with_stdio(false);
    std::cin.tie(0);
#ifndef ONLINE_JUDGE
    std::ifstream fin("sol.inp");
    std::ofstream fout("sol.out");

    std::istream& in = fin;
    std::ostream& out = fout;
#else
    std::istream& in = std::cin;
    std::ostream& out = std::cout;
#endif
    in >> n >> m;
    for(int i = 0; i < n; ++i) {
        in >> values[i];
    }
    out << MaxSum(LIMIT) << '\n';    

    return 0;
}