

import java.util.Scanner;

public class GreedyChange {
    public static int price = 0; //줘야할 거스름돈
    public static int count = 0; //횟수
    public static int a = 0; //동전 단위마다의 횟수
    
    public static int[] coinArr = {500, 160, 100, 50, 10, 1};
    public static int[] coinArr2 = {1, 10, 50, 100, 160, 500};
    
    public static void getCount(int coin) {
    	a = count;
        count += (price / coin); //지폐 수
        price = price - (coin * (price / coin)); //남은 금액
        a = count - a;
    }
    
    
    public static void DynamicChange() { //최적의 해
    	int[] dp = new int[price+1]; //dp[숫자] 배열은 숫자 거스름돈의 최소 수 배열
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
    	System.out.println("최적의 해 " + dp[price]);
    	
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        int MyMoney = sc.nextInt(); //넣은 금액
        int FoodMoney = sc.nextInt(); //가격 합계
        price = MyMoney - FoodMoney; //거스름돈
        
        int div[] = new int[coinArr.length]; //거스름돈 화폐단위로 나누기
        for(int i=0 ; i<coinArr.length ; i++) {
        	div[i] = 0;
        }
        for(int i=0 ; i<coinArr.length ; i++) {
            getCount(coinArr[i]);
            div[i] = a;
        }
        
        System.out.println(count);
        System.out.println("500원 : " + div[0] + " 160원 : " + div[1] + " 100원 : " + div[2] + " 50원 : " + div[3] + " 10원 : " + div[4] + " 1원 : " + div[5]);
        price = MyMoney - FoodMoney;
        DynamicChange();
    }
    
}

