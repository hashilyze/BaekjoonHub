#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int convertAlpha(const string &str) {
    int val = 0;
    int len = str.size();
    for (int i = 0; i < len; i++) {
        val = val * 26 + (str[i] - 'A');
    }
    return val;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int tc;
    cin >> tc;
    while (tc--) {
        string plate;
        cin >> plate;
        string letters = plate.substr(0, 3);
        string digits = plate.substr(4);

        int left = convertAlpha(letters);
        int right = stoi(digits);

        if (abs(left - right) <= 100) {
            cout << "nice\n";
        } else {
            cout << "not nice\n";
        }
    }
    return 0;
}
