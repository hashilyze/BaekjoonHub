#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> num(n);
    for (int i = 0; i < n; i++) {
        cin >> num[i];
    }

    if (num[1] - num[0] == num[2] - num[1]) {
        cout << num[n - 1] + (num[1] - num[0]) << endl;
    } else if (num[1] / num[0] == num[2] / num[1]) {
        cout << num[n - 1] * (num[1] / num[0]) << endl;
    }

    return 0;
}
