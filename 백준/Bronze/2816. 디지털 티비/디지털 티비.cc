#include <iostream>
#include <string>
using namespace std;

int main() {
    int n;
    cin >> n;

    int idx1, idx2;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        if (s == "KBS1") idx1 = i;
        if (s == "KBS2") idx2 = i;
    }

    if (idx1 > idx2) idx2++;
    for (int i = 0; i < idx1; i++) cout << 1;
    for (int i = 0; i < idx1; i++) cout << 4;
    if (idx2 != 1) {
        for (int i = 0; i < idx2; i++) cout << 1;
        for (int i = 1; i < idx2; i++) cout << 4;
    }
}
