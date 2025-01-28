#include <string>
#include <vector>

using namespace std;

string solution(vector<string> str_list, string ex) {
    string answer = "";
    for(const std::string &str : str_list){
        if(str.find(ex) == std::string::npos)
            answer += str;
    }
    return answer;
}