#include <iostream>
#include <string>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T; 
    cin >> T;

    while (T--) {
        string s1, s2;
        cin >> s1 >> s2;

        cout << "Distances:";
        for (size_t i = 0; i < s1.size(); i++) {
            int diff = (s2[i] - s1[i] + 26) % 26;
            cout << " " << diff;
        }
        cout << "\n";
    }

    return 0;
}
