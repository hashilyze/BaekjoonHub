#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n;
        cin >> n;

        for (int pos = 0; n; ++pos, n >>= 1) {
            if (n & 1) cout << pos << ' ';
        }
        cout << '\n';
    }
    return 0;
}
