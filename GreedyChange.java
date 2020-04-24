import java.util.Scanner;

public class GreedyChange {
    public static int price = 0; //����� �Ž�����
    public static int count = 0; //Ƚ��
    public static int a = 0; //���� ���������� Ƚ��
    
    public static int[] coinArr = {10000, 8700, 5000, 3200, 1050, 500, 160, 100, 50, 10, 1};
    public static int[] coinArr2 = {1, 10, 50, 100, 160, 500, 1050, 3200, 5000, 8700, 10000};
    
    public static void getCount(int coin) {
    	a = count;
        count += (price / coin); //���� ��
        price = price - (coin * (price / coin)); //���� �ݾ�
        a = count - a;
    }
    
    
    public static void DynamicChange() { //������ ��
    	int[] dp = new int[price+1]; //dp[����]�� ���� �Ž������� �ּ� �� �迭
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
    	System.out.println("������ �� : " + dp[price]);
    	
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
        long GreedyStart = System.nanoTime(); //�׸���˰��� ��������
        for(int i=0 ; i<coinArr.length ; i++) {
            getCount(coinArr[i]);
            div[i] = a;
        }
        long GreedyEnd = System.nanoTime(); //�׸���˰��� ��������
        
        System.out.println("�׸��� �˰��� �� : " + count);
        for(int i=0 ; i<div.length ; i++) {
        	System.out.println(coinArr[i]+ "�� : " + div[i]); //ȭ������� ���
        }
        
        long Greedytimes = (GreedyEnd - GreedyStart);
        System.out.println("�׸��� �����ð�(ns) : " + Greedytimes);
        
        price = MyMoney - FoodMoney; //�׸���˰��� �����ϰ� �Ž����� 0�� �Ǿ����Ƿ� �ٽ� ä���ֱ�
        
        long DynamicStart = System.nanoTime(); //�����˰���(��������) ��������
        DynamicChange();
        long DynamicEnd = System.nanoTime(); //�����˰��� (��������) ��������
        
        long Dynamictimes = (DynamicEnd - DynamicStart);
        System.out.println("������ �� �����ð�(ns) : " + Dynamictimes);
    }
    
}

