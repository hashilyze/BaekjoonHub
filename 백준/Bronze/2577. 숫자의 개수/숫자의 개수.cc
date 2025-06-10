#include <iostream>
using namespace std;

int main(void) {
    int num[10] = {0, };
    int A, B, C;
    cin >> A >> B >> C;
    int S = A * B * C;
    while (S) {
        num[S%10]++;
        S /= 10;
    }
    for (int i = 0; i < 10; i++) cout << num[i] << "\n";
}