import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private static final int SIZE = 8;
    private static final int CELL_SIZE = 110;
    JButton[][] buttons;
    private Timer timer;
    ImageIcon qni;

    public Gui() throws HeadlessException {
        qni = new ImageIcon("Chess-Knight-Free-PNG.png");
        Image image = qni.getImage();
        Image newim = image.getScaledInstance(CELL_SIZE+10,CELL_SIZE+10, Image.SCALE_SMOOTH);
        qni = new ImageIcon(newim);
        int random_x = (int)(Math.random()*Solve.WIDTH);
        int random_y = (int)(Math.random()*Solve.HEIGHT);

        Pozycja pozycja = new Pozycja(random_x,random_y);

        Solve.dfsSearchWarnsdorrf(Solve.board,pozycja,1);
        Solve.wyswietlPlansze();
        System.out.println("Koniec");
        System.out.println(Solve.counter);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(SIZE, SIZE));
        panel.setPreferredSize(new Dimension(SIZE * CELL_SIZE, SIZE * CELL_SIZE));
        this.setTitle("Problem Skoczka");
        buttons = new JButton[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton btn = new JButton();
                btn.setMargin(new Insets(0, 0, 0, 0));
                btn.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                btn.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                buttons[row][col] = btn;
                panel.add(btn);
            }
        }
        JButton losuj = new JButton("Losuj pozycje");
        losuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solve.reset();
                int random_x = (int)(Math.random()*Solve.WIDTH);
                int random_y = (int)(Math.random()*Solve.HEIGHT);

                Pozycja pozycja = new Pozycja(random_x,random_y);
                Solve.dfsSearchWarnsdorrf(Solve.board,pozycja,1);
                Solve.wyswietlPlansze();
                System.out.println(Solve.counter);
                visualize();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout()); // so preferred size of panel is respected
        this.add(panel);
        this.add(losuj);
        this.pack(); // size frame to fit preferred sizes
        this.setLocationRelativeTo(null); // center on screen
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(1200,1000);
        visualize();
    }
    void clear(){
        for (int i = 0; i < Solve.board.length; i++) {
            for (int j = 0; j < Solve.board[i].length; j++) {
                buttons[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                buttons[i][j].setText("");
                buttons[i][j].setIcon(new ImageIcon());
            }
        }
    }
    public void visualize() {
        clear();
        final int maxNumber = Solve.HEIGHT * Solve.WIDTH;
        final int[] number = {1};
        if(timer != null) timer.stop();
        int[] previous = new int[2];
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (number[0] > maxNumber) {
                    ((Timer) e.getSource()).stop();
                    return;
                }
                for (int i = 0; i < Solve.board.length; i++) {
                    for (int j = 0; j < Solve.board[i].length; j++) {
                        if (Solve.board[i][j] == number[0]) {
                            buttons[previous[0]][previous[1]].setIcon(new ImageIcon());
                            buttons[i][j].setBackground(Color.RED);
                            buttons[i][j].setText(number[0] + "");
                            buttons[i][j].setIcon(qni);
                            previous[0] = i;
                            previous[1] = j;
                        }
                    }
                }
                number[0]++;
            }
        });

        timer.start();
    }

}
