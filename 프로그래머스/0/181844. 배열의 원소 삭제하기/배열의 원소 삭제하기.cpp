#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> arr, vector<int> delete_list) {
    std::unordered_set<int> set(delete_list.begin(), delete_list.end());
    for(int i = 0; i < arr.size(); ++i){
        if(set.find(arr[i]) != set.end()){
            arr.erase(arr.begin() + i--);
        }
    }
    return arr;
}