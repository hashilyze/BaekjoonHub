int solution(int n) {
    int ans = 0;
    if(n % 2){
        for(int i = 1; i <= n; i += 2) ans += i;
    } else{
        for(int i = 2; i <= n; i += 2) ans += i * i;
    }
    return ans;
}