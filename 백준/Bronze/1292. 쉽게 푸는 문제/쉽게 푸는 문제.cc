#include <iostream>
using namespace std;

int main() {
    int sequence[1001];
    int index = 1;

    for (int num = 1; index <= 1000; ++num) {
        for (int count = 0; count < num && index <= 1000; ++count) {
            sequence[index++] = num;
        }
    }

    int start, end, total = 0;
    cin >> start >> end;

    for (int i = start; i <= end; ++i) {
        total += sequence[i];
    }

    cout << total;
    return 0;
}
