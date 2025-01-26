#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    int e1 = num_list[num_list.size() - 2];
    int e2 = num_list[num_list.size() - 1];
    num_list.push_back(e1 < e2 ? e2 - e1 : e2 * 2);
    return num_list;
}