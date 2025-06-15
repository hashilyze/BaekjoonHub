#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<string> files(n);
    for (int i = 0; i < n; i++) cin >> files[i];

    for (int i = 0; i < files[0].size(); i++) {
        bool flag = false;
        for (int j = 1; j < n; j++) {
            if (files[j - 1][i] != files[j][i]) {
                flag = true;
                cout << '?';
                break;
            }
        }
        if(!flag) {
            cout << files[0][i];
        }
    }
    cout << '\n';
    return 0;
}