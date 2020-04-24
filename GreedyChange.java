

import java.util.Scanner;

public class GreedyChange {
    public static int price = 0; //����� �Ž�����
    public static int count = 0; //Ƚ��
    public static int a = 0; //���� ���������� Ƚ��
    
    public static int[] coinArr = {500, 160, 100, 50, 10, 1};
    public static int[] coinArr2 = {1, 10, 50, 100, 160, 500};
    
    public static void getCount(int coin) {
    	a = count;
        count += (price / coin); //���� ��
        price = price - (coin * (price / coin)); //���� �ݾ�
        a = count - a;
    }
    
    
    public static void DynamicChange() { //������ ��
    	int[] dp = new int[price+1]; //dp[����] �迭�� ���� �Ž������� �ּ� �� �迭
    	dp[0] = 0;
    	for (int i=1;i<=price;i++) {
    		dp[i] = 99999; //�ּҰ��� ã���Ƿ� ������ ū ���� �ʱ�ȭ
    	}
    	for (int i=1;i<=price;i++) {
    		for (int j=0; j<coinArr2.length ; j++) {
    			if (i < coinArr2[j])
    				continue; //�Ž������� ������������ ������ ���� �ݺ�
    			if (dp[i] > dp[i-coinArr2[j]] + 1)
    				dp[i] = dp[i-coinArr2[j]] + 1;
    		}
    		
    	}
    	System.out.println("������ �� " + dp[price]);
    	
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        int MyMoney = sc.nextInt(); //���� �ݾ�
        int FoodMoney = sc.nextInt(); //���� �հ�
        price = MyMoney - FoodMoney; //�Ž�����
        
        int div[] = new int[coinArr.length]; //�Ž����� ȭ������� ������
        for(int i=0 ; i<coinArr.length ; i++) {
        	div[i] = 0;
        }
        for(int i=0 ; i<coinArr.length ; i++) {
            getCount(coinArr[i]);
            div[i] = a;
        }
        
        System.out.println(count);
        System.out.println("500�� : " + div[0] + " 160�� : " + div[1] + " 100�� : " + div[2] + " 50�� : " + div[3] + " 10�� : " + div[4] + " 1�� : " + div[5]);
        price = MyMoney - FoodMoney;
        DynamicChange();
    }
    
}

