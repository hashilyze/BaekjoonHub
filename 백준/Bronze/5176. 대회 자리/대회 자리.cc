#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int t;
    cin >> t;

    while (t--) {
        int p, m;
        cin >> p >> m;

        vector<int> seat(p);
        for (int i = 0; i < p; i++) {
            cin >> seat[i];
        }

        sort(seat.begin(), seat.end());
        int dup = 0;

        for (int i = 1; i < p; i++) {
            if (seat[i] == seat[i - 1]) dup++;
        }

        cout << dup << "\n";
    }

    return 0;
}
