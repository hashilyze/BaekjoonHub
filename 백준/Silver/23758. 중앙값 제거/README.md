# [Silver I] 중앙값 제거 - 23758 

[문제 링크](https://www.acmicpc.net/problem/23758) 

### 성능 요약

메모리: 58912 KB, 시간: 1432 ms

### 분류

그리디 알고리즘, 수학, 정렬

### 제출 일자

2025년 3월 10일 23:37:10

### 문제 설명

<p>$N$개의 자연수 $a_1$, $a_2$, $...$, $a_N$이 주어진다. 0을 좋아하는 amel은 $N$개의 수 중 0이 등장할 때까지 다음 연산을 반복하려고 한다.</p>

<ul>
	<li>중앙값을 2로 나누고 나머지는 버린다.</li>
</ul>

<p>중앙값은 $N$개의 수를 오름차순으로 나열했을 때 $\lfloor\cfrac{N+1}{2}\rfloor$번째에 위치한 수이다. 또한 $\lfloor x\rfloor$은 $x$ 이하이면서 가장 큰 정수를 뜻한다.</p>

<p>예를 들어, $[7, 3, 9, 5]$ 에서 한 번의 연산을 수행하면 $[7, 3, 9, 2]$가 된다.</p>

<p>amel이 몇 번의 연산을 수행하여야 목표를 달성할 수 있을지 계산해주자.</p>

### 입력 

 <p>첫째 줄에 수의 개수 $N$이 주어진다. ($1 \le N \le 2 \times 10^6$)</p>

<p>둘째 줄에 자연수 $a_1$, $a_2$, $...$, $a_N$이 공백을 사이에 두고 주어진다. ($1 \le a_i \le 10^9$)</p>

<p><strong>입력되는 수가 매우 많음에 유의하자.</strong></p>

### 출력 

 <p>amel이 수행하여야 하는 연산의 횟수를 한 줄로 출력한다.</p>

