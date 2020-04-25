import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                        vending_machine_gui.x++;
                        button.setText("수량 : " + vending_machine_gui.x);
                    } else if (xyz == 2) {
                        vending_machine_gui.y++;
                        button.setText("수량 : " + vending_machine_gui.y);
                    } else {
                        vending_machine_gui.z++;
                        button.setText("수량 : " + vending_machine_gui.z);
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
                        vending_machine_gui.x--;
                        button.setText("수량 : " + vending_machine_gui.x);
                    } else if (xyz == 2) {
                        vending_machine_gui.y--;
                        button.setText("수량 : " + vending_machine_gui.y);
                    } else {
                        vending_machine_gui.z--;
                        button.setText("수량 : " + vending_machine_gui.z);
                    }
                }
            });

        }
        return a;
    }
}

public class vending_machine_gui extends JFrame{
    final int w = 52;
    static int x = 0;
    static int y = 0;
    static int z = 0;
    vending_machine_gui(){

        setTitle("자판기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentpane = getContentPane();
        contentpane.setBackground(Color.pink);
        contentpane.setLayout(new FlowLayout());

        ImageIcon 라면 = new ImageIcon("images/라면.jpg");
        ImageIcon 아이스티 = new ImageIcon("images/아이스티.jpg");
        ImageIcon 코카콜라 = new ImageIcon("images/콜라.jpg");
        JLabel ramen = new JLabel(라면);
        JLabel icedtea = new JLabel(아이스티);
        JLabel cola = new JLabel(코카콜라);
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
                total_money.setText((3000* vending_machine_gui.x + 1600* vending_machine_gui.y + 750* vending_machine_gui.z) + "원");
            }
        });
        countx.setBackground(Color.orange);
        county.setBackground(Color.orange);
        countz.setBackground(Color.orange);
        countx.setText("수량 : " + vending_machine_gui.x);
        county.setText("수량 : " + vending_machine_gui.y);
        countz.setText("수량 : " + vending_machine_gui.z);
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
        new vending_machine_gui();
    }
}