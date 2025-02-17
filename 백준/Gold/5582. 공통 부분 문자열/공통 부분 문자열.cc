#include <bits/stdc++.h>

constexpr int MAX_N = 4001;
int dp[2][MAX_N];

int LCS(const std::string& s1, const std::string& s2){
    int max = 0;    
    for(int i = 1, len1 = s1.length(); i <= len1; ++i){
        int pi = (i + 1) % 2, ci = i % 2;
        for(int j = 1, len2 = s2.length(); j <= len2; ++j){
            if (s1[i - 1] == s2[j - 1]){
                dp[ci][j] = dp[pi][j - 1] + 1;
                max = std::max(max, dp[ci][j]);
            } else{
                dp[ci][j] = 0;
            }
        }
    }
    return max;
}

int main(void){
    std::ios_base::sync_with_stdio(false); std::cin.tie(NULL);

    std::string str1, str2;
    std::cin >> str1 >> str2;
    std::cout << LCS(str1, str2) << "\n";

    return 0;
}