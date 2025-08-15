#include <bits/stdc++.h>
using namespace std;

int main(){
	int t, a, b, i, j, length = 0, X = 0;
	string str;
	cin >> t;
	while(t-- > 0){
        cin >> a >> b >> str;
		for(j = 0; j < str.size(); j++){
			X = str[j] - 65;
			str[j] = ((a * X + b) % 26) + 65;
		}
        cout << str << '\n';
	}
}