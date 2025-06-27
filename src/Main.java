import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int random_x = (int)(Math.random()*Solve.WIDTH);
        int random_y = (int)(Math.random()*Solve.HEIGHT);
        Pozycja pozycja = new Pozycja(random_x,random_y);
        Solve.wyswietlPlansze();
        Solve.dfsSearchWarnsdorrf(Solve.board,pozycja,1);
        SwingUtilities.invokeLater(Gui::new);

    }



}