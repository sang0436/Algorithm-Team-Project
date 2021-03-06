# Algorithm-Team-Project

인천대학교 정보통신공학과 이상민, 허진, 임동한입니다.



## 프로그램 소개

이 프로그램은 인천대학교 정보통신공학과 '컴퓨터 알고리즘' 과목의 팀 프로젝트로 만든 프로그램입니다.

그리디 알고리즘과 최적의 해를 찾는 알고리즘의 구동 시간과 해의 차이를 비교하는 프로그램입니다.

사용자는 GUI 자판기 프로그램에 음식 메뉴와 수량을 선택하고 자신의 현재 금액을 입력합니다. 시작을 누르면

그리디 알고리즘과 최적의 해를 찾는 알고리즘으로 각각 거스름돈의 지폐(동전) 최소 개수를 계산하여  콘솔에 출력합니다.  각각의 알고리즘의 구동 시간도 비교하여 출력합니다.



## 프로그램 진행 순서

먼저 시작화면입니다.

![자판기_1](https://user-images.githubusercontent.com/63089645/80276777-c82aa880-8725-11ea-802b-3b2aef4619b7.png)



+1을 누르면 수량이 증가하고 -1을 누르면 수량이 감소하고, 시작을 누르면 다음과 같이 합계가 나옵니다.

![자판기_2](https://user-images.githubusercontent.com/63089645/80276805-f14b3900-8725-11ea-956f-16d2612e2e2a.png)



여기서 현재금액을 입력하고 구매를 누르면 다음과 같이 콘솔창에 결과가 나옵니다.

![자판기_3](https://user-images.githubusercontent.com/63089645/80276814-00ca8200-8726-11ea-940b-2d9cdbc32ee9.png)

![자판기_4](https://user-images.githubusercontent.com/63089645/80276850-38d1c500-8726-11ea-8450-895dc9387711.png)



현재 금액이 20000원이고 음식 금액이 11550원이면, 거스름돈은 8450원일것입니다.

보시면 그리디 알고리즘으로 구동했을 때의 거스름돈 최소 지폐(동전) 개수는 8개

최적의 해를 구하는 알고리즘으로 구동했을 때의 거스름돈 최소 지폐(동전) 개수는 5개입니다.

구동 시간을 ns 단위로 계산하면 450배가 넘는 구동 시간 차이를 보여주고 있습니다.

이처럼 그리디 알고리즘은 항상 최적의 해를 계산해주지 못하지만 빠른 속도의 처리 능력을 보여줌을 알 수 있습니다.



## 알고리즘 코드 설명

올려놓은 GreedyChange.java 에 알고리즘만을 구현한 코드가 올라와 있습니다.



이 코드는 그리디 알고리즘 코드입니다.

```java
public static int[] coinArr = {10000, 8700, 5000, 3200, 1050, 500, 160, 100, 50, 10, 1};

public static void getCount(int coin) {
    	a = count;
        count += (price / coin); //(1)
        price = price - (coin * (price / coin)); //(2)
        a = count - a;
    }
```

count는 최소 개수를 저장하는 변수이고 a는 지폐 단위의 사용 횟수를 저장하는 변수입니다.

price는 거스름돈이고 coin은 지폐 단위입니다.

그리디 알고리즘의 핵심은 '현재 상황에서 가장 최고의 선택만을 따라가는 것'입니다.

동전 거스름돈에서는 거스름돈보다 작은 지폐 중 가장 큰 지폐를 선택하는 것이 최고의 선택입니다.

만약 price가 8450원이라면 5000원을 먼저 거슬러주는 것이 최고의 선택일 것입니다. 

따라서 내림차순으로 이루어진 coinArr 배열의 인덱스 차례대로 coin에 대입합니다. 즉, 10000원 -> 8700원 -> 5000원 ... 순서로 진행합니다.

(1) price가 8450원일 때 8450 / 5000원을 나눈 1을 count에 대입합니다.

(2) 이제 price는 8450원에서 방금 준 거스름돈 개수 X 거스름돈 단위 만큼 뺀 값이 대입되겠습니다.

(price = 8450 - (5000 * (8450 / 5000)))

이 순서대로 price가 0이 될때까지 반복하면 됩니다.



다음 코드는 최적의 해를 구하는 코드입니다.

```java
public static int[] coinArr2 = {1, 10, 50, 100, 160, 500, 1050, 3200, 5000, 8700, 10000};

public static void DynamicChange() { //최적의 해
    	int[] dp = new int[price+1]; //dp[숫자]는 숫자 거스름돈의 최소 수 배열
    	dp[0] = 0;
    	for (int i=1;i<=price;i++) {
    		dp[i] = 99999; //최소값을 찾으므로 무수히 큰 수로 초기화
    	}
    	for (int i=1;i<=price;i++) {
    		for (int j=0; j<coinArr2.length ; j++) {
    			if (i < coinArr2[j])
    				continue; //거스름돈이 동전단위보다 적으면 다음 반복
    			if (dp[i] > dp[i-coinArr2[j]] + 1)
    				dp[i] = dp[i-coinArr2[j]] + 1;
    		}
    		
    	}
    	System.out.println("최적의 해 : " + dp[price]);
    	
    }
```

최적의 해를 구하는 코드는 '동적 알고리즘'(Dynamic Algorithm)을 사용했습니다.

여기서는 dp[price+1]라는 배열을 만듭니다. 여기서 배열 안의 값은 그 인덱스의 거스름돈 최소 개수입니다.

만약 dp[500]은 500원의 거스름돈 최소 개수입니다.

이 코드를 보면 price가 8450일때, dp[0]부터 dp[8451]까지의 배열을 만듭니다.

그리고 dp[1] (1원일때의 최소 개수)일 때부터 dp[8450] (8450원일때의 최소 개수) 까지 일일이 모두 조건을 따져봅니다.

왜냐하면 동적 알고리즘은 전에 구한 해가 나중에 구하는 해에 영향을 주기 때문입니다.

이렇게 되면 그리디 알고리즘보다 속도가 느릴 수 밖에 없습니다. 1원부터 8450원까지 모두 따져보기 때문입니다.

하지만 무조건 최적의 해를 구합니다. 이것이 그리디 알고리즘과의 차이입니다.

## 한글 깨짐 현상 해결

이클립스에서 프로젝트를 실행시킬 때, 인코딩 에러로 인해, 한글 깨짐 현상이 생기는 경우가 있습니다. 그럴 때를 위한 지침서입니다.

<img src="https://t1.daumcdn.net/cfile/tistory/2302B3335954C4BC13?download" alt="Window" style="zoom:50%;" />

우선 'Window > Preferences'를 클릭합니다.

<img src="https://ifh.cc/g/bYOyOO.png" alt="Preferences" style="zoom:50%;" />

인코딩 문제이기 때문에 검색창에 encoding을 검색합니다.

"Content Types > Text"를 클릭하여 Default encoding 에 UTF-8이라고 입력한 후 'Update'를 눌러줍니다.

<img src="https://ifh.cc/g/6R4r0M.png" alt="ContentTypes" style="zoom:50%;" />

'Workspace > Text file encoding > Other: UTF-8'로 체크한 뒤 Apply를 클릭합니다.

<img src="https://ifh.cc/g/4SFQfY.png" alt="XML" style="zoom:40%;" />

'XML Files > Encoding'에서 'UTF - 8'을 선택해준 후 'Apply'를 누릅니다.

Apply and close를 누르고 이클립스를 한번 재실행해 주면 오류가 해결됩니다.


### 파일 설명

GreedyChange.java : 알고리즘 구현 코드

vending_machine_gui.java : 자판기 GUI 구현 코드

Vending_machine : 최종 프로젝트 폴더 (최종 코드, 이미지 포함)
