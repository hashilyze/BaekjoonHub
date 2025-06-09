#include <bits/stdc++.h>
using namespace std;

int get_min_clock_number(int a, int b, int c, int d) {
    int digits[4] = {a, b, c, d};
    int min_num = INT_MAX;

    for (int i = 0; i < 4; ++i) {
        int num = 0;
        for (int j = 0; j < 4; ++j) {
            num = num * 10 + digits[(i + j) % 4];
        }
        min_num = min(min_num, num);
    }
    return min_num;
}

int main() {
    int a, b, c, d;
    cin >> a >> b >> c >> d;

    set<int> unique_clock_numbers;

    for (int i = 1; i <= 9; ++i) {
        for (int j = 1; j <= 9; ++j) {
            for (int k = 1; k <= 9; ++k) {
                for (int w = 1; w <= 9; ++w) {
                    unique_clock_numbers.insert(get_min_clock_number(i, j, k, w));
                }
            }
        }
    }

    int target = get_min_clock_number(a, b, c, d);
    vector<int> sorted_clock_numbers(unique_clock_numbers.begin(), unique_clock_numbers.end());
    auto it = find(sorted_clock_numbers.begin(), sorted_clock_numbers.end(), target);
    cout << (it - sorted_clock_numbers.begin() + 1) << '\n';

    return 0;
}
