#include <iostream>
#include <string>
#include <vector>

void MaxRepeat(const std::string text, int& out_maxRepeat){
    int length = text.size();
    int next = 1, sub = 1;
    while(next < length){
        int i = 0;
        while(i < sub && next + i < length && text[next + i] == text[i]){
            ++i;
        }
        // 일치: next += sub
        // 불일치: 
        //         (i = 0) - 소비 O
        //         (i > 0) - 소비 X
        //
        if(i == sub){
            next += i;
        } else{
            if(i == 0){
                next = std::min(length, next + i + 1);
            } else{
                next = std::min(length, next + i);
            }
            sub = next;;
        }
    }
    out_maxRepeat = length / sub;

    // for(int sub = 1; sub <= text.length(); ++sub){
    //     if(text.length() % sub){
    //         continue;
    //     }
    //     if(text[next] == text[next % sub]){
    //         ++next;
    //     } 


    //     bool matched = true;
    //     for(int pos = 0; pos < text.length(); ++pos){
    //         if(text[pos] != text[pos % sub]){
    //             matched = false;
    //             break;
    //         }
    //     }
    //     if(matched){
    //         out_maxRepeat = text.length() / sub;
    //         return;
    //     }
    // }
}

int main(void){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    std::string text;
    while(std::cin >> text){
        if(text == "."){
            break;
        }
        int maxRepeat;
        MaxRepeat(text, maxRepeat);
        std::cout << maxRepeat << '\n';
    }

    return 0;
}