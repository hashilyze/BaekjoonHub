#include <bits/stdc++.h>

using namespace std;

int solution(vector<string> order) {
    std::unordered_map<std::string, int> prices;
    prices.insert({"iceamericano", 4500});
    prices.insert({"americanoice", 4500});
    prices.insert({"hotamericano", 4500});
    prices.insert({"americanohot", 4500});
    prices.insert({"americano", 4500});
    
    prices.insert({"anything", 4500});
    
    prices.insert({"icecafelatte", 5000});
    prices.insert({"cafelatteice", 5000});
    prices.insert({"hotcafelatte", 5000});
    prices.insert({"cafelattehot", 5000});
    prices.insert({"cafelatte", 5000});
    
    int ans = 0;
    for(const std::string &str : order) ans += prices[str];
    return ans;
}