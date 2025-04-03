#include <iostream>
#include <string>
#include <vector>

const int MAX_FIB_FOR_LIMIT = 32;
const int MAX_FIBONACCI = 101;
int FibLength[MAX_FIBONACCI];
bool FibFront[MAX_FIBONACCI];
bool FibBack[MAX_FIBONACCI];
unsigned long long FibCount[MAX_FIBONACCI];

// KMP String Mathcing
void GetLps(const std::string& text, std::vector<int>& out_lps){
    int length = text.size();
    out_lps.resize(length, 0);

    int next = 1, matched = 0;
    while(next < length){
        if(text[next] == text[matched]){
            ++next;
            ++matched;
            out_lps[next - 1] = matched;
        } else{
            if(matched){
                matched = out_lps[matched - 1];
            } else{
                ++next;
            }
        }
    }
}

void KMP(const std::string& text, const std::string& pattern, int& out_count){
    int txtLength = text.size(), patLength = pattern.size();    
    out_count = 0;

    std::vector<int> lps; GetLps(pattern, lps);

    int next = 0, matched = 0;
    while(next < txtLength){
        if(matched < patLength && text[next] == pattern[matched]){
            ++next;
            ++matched;
            if(matched == patLength){
                ++out_count;
            }
        } else{
            if(matched){
                matched = lps[matched - 1];
            } else{
                ++next;
            }
        }
    }
}

// Fibonacci
void FibonacciWord(int n, std::string& word){
    if(n == 0){
        word += '0';
        return;
    } else if(n == 1){
        word += '1';
        return;
    }
    FibonacciWord(n - 1, word);
    FibonacciWord(n - 2, word);
}

// void MemoFibonacciWord(int n){
//     FibWord[0] = "0";
//     FibWord[1] = "1";
//     for(int i = 2; i <= n; ++i){
//         FibWord[i] = FibWord[i - 1] + FibWord[i - 2];
//     }
// }

void MemoFibonacciLength(int n){
    FibLength[0] = 1;
    FibLength[1] = 1;
    for(int i = 2; i <= n; ++i){
        FibLength[i] = FibLength[i - 1] + FibLength[i - 2];
    }
}

void MemoFibonacciFront(int n, int f){
    FibFront[f] = false;
    FibFront[f + 1] = true;
    for(int i = f + 2; i <= n; ++i){
        FibFront[i] = FibFront[i - 1];
    }
}
void MemoFibonacciBack(int n, int f){
    FibBack[f] = false;
    FibBack[f + 1] = true;
    for(int i = f + 2; i <= n; ++i){
        FibBack[i] = FibBack[i - 2];
    }
}


void Solve(int n, const std::string& pattern){
    int patLength = pattern.size();
    
    // Step.1: Find min word
    int first = 0, second;
    while(FibLength[first] < patLength){
        ++first;
    }
    second = first + 1;

    std::string firstWord, secondWord;
    firstWord.reserve(FibLength[first]);
    FibonacciWord(first, firstWord);
    secondWord.reserve(FibLength[second]);
    FibonacciWord(second, secondWord);

    // Step.2: Count matching
    int f;  KMP(firstWord, pattern, f);
    int s;  KMP(secondWord, pattern, s);
    int ff; KMP(firstWord + firstWord, pattern, ff);    ff -= 2 * f;
    int ss; KMP(secondWord + secondWord, pattern, ss);  ss -= 2 * s;
    int fs; KMP(firstWord + secondWord, pattern, fs);   fs -= f + s;
    int sf; KMP(secondWord + firstWord, pattern, sf);   sf -= f + s;

    // Step.3: Memoization Front and Second
    MemoFibonacciFront(n, first);
    MemoFibonacciBack(n, first);

    // Step.4
    FibCount[first] = f;
    FibCount[second] = s;
    for(int i = first + 2; i <= n; ++i) {
        FibCount[i] = FibCount[i - 1] + FibCount[i - 2];
        bool front = FibFront[i - 2], back = FibBack[i - 1];
        if(front && back){ // ss
            FibCount[i] += ss;
        } else if(front){ // fs
            FibCount[i] += fs;
        } else if(back){ // sf
            FibCount[i] += sf;
        } else{ // ff
            FibCount[i] += ff;
        }
    }
}

int main(void){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    int i = 0;
    int n;
    MemoFibonacciLength(MAX_FIB_FOR_LIMIT);
    while(std::cin >> n){
        std::string pattern;
        std::cin >> pattern;

        Solve(n, pattern);
        
        std::cout << "Case " << ++i << ": " << FibCount[n] << '\n';
    }
    
    return 0;
}