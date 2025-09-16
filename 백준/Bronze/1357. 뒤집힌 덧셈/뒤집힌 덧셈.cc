#include <bits/stdc++.h>

using namespace std;

int main() {
	string n1, n2; cin >> n1 >> n2;
	reverse(n1.begin(), n1.end());
	reverse(n2.begin(), n2.end());
	string ans = to_string(stoi(n1) + stoi(n2));
	reverse(ans.begin(), ans.end());
	cout << stoi(ans);
}