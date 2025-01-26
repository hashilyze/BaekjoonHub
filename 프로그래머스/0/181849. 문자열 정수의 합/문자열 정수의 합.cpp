#include <string>
#include <vector>

using namespace std;

int solution(string num_str) {
    int answer = 0;
    for(char digit : num_str) answer += digit - '0';
    return answer;
}