import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GreedyChange {
    public static int price = 0; //줘야할 거스름돈
    public static int count = 0; //횟수
    public static int a = 0; //동전 단위마다의 횟수

    public static int[] coinArr = {10000, 8700, 5000, 3200, 1050, 500, 160, 100, 50, 10, 1};
    public static int[] coinArr2 = {1, 10, 50, 100, 160, 500, 1050, 3200, 5000, 8700, 10000};

    public static void getCount(int coin) {
        a = count;
        count += (price / coin); //지폐 수
        price = price - (coin * (price / coin)); //남은 금액
        a = count - a;
    }


    public static void DynamicChange() { //최적의 해
        int[] dp = new int[price + 1]; //dp[숫자]는 숫자 거스름돈의 최소 수 배열
        dp[0] = 0;
        for (int i = 1; i <= price; i++) {
            dp[i] = 99999; //최소값을 찾으므로 무수히 큰 수로 초기화
        }
        for (int i = 1; i <= price; i++) {
            for (int j = 0; j < coinArr2.length; j++) {
                if (i < coinArr2[j])
                    continue; //거스름돈이 동전단위보다 적으면 다음 반복
                if (dp[i] > dp[i - coinArr2[j]] + 1)
                    dp[i] = dp[i - coinArr2[j]] + 1;
            }

        }
        System.out.println("최적의 해 : " + dp[price]);
    }
}
class button{
    static JButton button_maker(int i, JTextArea button, int xyz){
        JButton a;
        if(i == 1){
            a = new JButton("+1");
            a.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseClicked(MouseEvent e) {
                    if (xyz == 1) {
                        vending_machine.x++;
                        button.setText("수량 : " + vending_machine.x);
                    } else if (xyz == 2) {
                        vending_machine.y++;
                        button.setText("수량 : " + vending_machine.y);
                    } else {
                        vending_machine.z++;
                        button.setText("수량 : " + vending_machine.z);
                    }
                }
            });
        }
        else{
            a = new JButton("-1");
            a.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseClicked(MouseEvent e) {
                    if (xyz == 1) {
                        vending_machine.x--;
                        button.setText("수량 : " + vending_machine.x);
                    } else if (xyz == 2) {
                        vending_machine.y--;
                        button.setText("수량 : " + vending_machine.y);
                    } else {
                        vending_machine.z--;
                        button.setText("수량 : " + vending_machine.z);
                    }
                }
            });

        }
        return a;
    }
}



public class vending_machine extends JFrame{
    final int w = 52;
    static int x = 0;
    static int y = 0;
    static int z = 0;
    vending_machine(){

        setTitle("자판기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentpane = getContentPane();
        contentpane.setBackground(Color.DARK_GRAY);
        contentpane.setLayout(new FlowLayout());

        ImageIcon ramenimage = new ImageIcon("images/라면.jpg");
        ImageIcon icedteaimage = new ImageIcon("images/아이스티.jpg");
        ImageIcon colaimage = new ImageIcon("images/콜라.jpg");
        JLabel ramen = new JLabel(ramenimage);
        JLabel icedtea = new JLabel(icedteaimage);
        JLabel cola = new JLabel(colaimage);
        JTextArea countx = new JTextArea();
        JTextArea county = new JTextArea();
        JTextArea countz = new JTextArea();
        JTextArea labelx = new JTextArea("라면 \n3000원");
        JTextArea labely = new JTextArea("아이스티 \n1600원");
        JTextArea labelz = new JTextArea("코카콜라 \n750원");
        JTextArea cur = new JTextArea("현재금액");
        JTextField cur_money = new JTextField("");
        JButton start = new JButton("시작");
        JTextArea total = new JTextArea("합계");
        JTextField total_money = new JTextField("");
        JButton buy = new JButton("구매");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                total_money.setText("" + (3000*vending_machine.x + 1600*vending_machine.y + 750*vending_machine.z));
            }
        });
        buy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int MyMoney = Integer.parseInt(cur_money.getText());
                int FoodMoney = Integer.parseInt(total_money.getText());
                GreedyChange.price = MyMoney - FoodMoney; //거스름돈

                int div[] = new int[GreedyChange.coinArr.length]; //거스름돈 화폐단위로 나누기
                for(int i=0 ; i<GreedyChange.coinArr.length ; i++) {
                    div[i] = 0;
                }
                long GreedyStart = System.nanoTime(); //그리디알고리즘 구동시작
                for(int i=0 ; i<GreedyChange.coinArr.length ; i++) {
                    GreedyChange.getCount(GreedyChange.coinArr[i]);
                    div[i] = GreedyChange.a;
                }
                long GreedyEnd = System.nanoTime(); //그리디알고리즘 구동종료

                System.out.println("그리디 알고리즘 해 : " + GreedyChange.count);
                for(int i=0 ; i<div.length ; i++) {
                    System.out.println(GreedyChange.coinArr[i]+ "원 : " + div[i]); //화폐단위별 출력
                }

                long Greedytimes = (GreedyEnd - GreedyStart);
                System.out.println("그리디 구동시간(ms) : " + Greedytimes);

                GreedyChange.price = MyMoney - FoodMoney; //그리디알고리즘 실행하고 거스름돈 0원 되었으므로 다시 채워넣기

                long DynamicStart = System.nanoTime(); //동적알고리즘(최적의해) 구동시작
                GreedyChange.DynamicChange();
                long DynamicEnd = System.nanoTime(); //동적알고리즘 (최적의해) 구동종료

                long Dynamictimes = (DynamicEnd - DynamicStart);
                System.out.println("최적의 해 구동시간(ms) : " + Dynamictimes);
            }
        });
        countx.setBackground(Color.pink);
        county.setBackground(Color.pink);
        countz.setBackground(Color.pink);
        countx.setText("수량 : " + vending_machine.x);
        county.setText("수량 : " + vending_machine.y);
        countz.setText("수량 : " + vending_machine.z);
        JButton countxp = button.button_maker(1,countx,1);
        JButton countxm = button.button_maker(2,countx,1);
        JButton countyp = button.button_maker(1,county,2);
        JButton countym = button.button_maker(2,county,2);
        JButton countzp = button.button_maker(1,countz,3);
        JButton countzm = button.button_maker(2,countz,3);
        labelx.setPreferredSize(new Dimension(w,30));
        countx.setPreferredSize(new Dimension(w,30));
        countxp.setPreferredSize(new Dimension(w,30));
        countxm.setPreferredSize(new Dimension(w,30));
        labely.setPreferredSize(new Dimension(w,30));
        county.setPreferredSize(new Dimension(w,30));
        countym.setPreferredSize(new Dimension(w,30));
        countyp.setPreferredSize(new Dimension(w,30));
        labelz.setPreferredSize(new Dimension(w,30));
        countz.setPreferredSize(new Dimension(w,30));
        countzp.setPreferredSize(new Dimension(w,30));
        countzm.setPreferredSize(new Dimension(w,30));
        cur_money.setPreferredSize(new Dimension(100,30));
        total_money.setPreferredSize(new Dimension(100,30));

        contentpane.add(ramen);
        contentpane.add(icedtea);
        contentpane.add(cola);
        contentpane.add(labelx);
        contentpane.add(countxm);
        contentpane.add(countxp);
        contentpane.add(countx);
        contentpane.add(labely);
        contentpane.add(countym);
        contentpane.add(countyp);
        contentpane.add(county);
        contentpane.add(labelz);
        contentpane.add(countzm);
        contentpane.add(countzp);
        contentpane.add(countz);
        contentpane.add(cur);
        contentpane.add(cur_money);
        contentpane.add(start);
        contentpane.add(total);
        contentpane.add(total_money);
        contentpane.add(buy);
        setSize(708,400);
        setVisible(true);
    }
    public static void main(String[] args){
        new vending_machine();
    }
}