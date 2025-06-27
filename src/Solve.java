import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
public class Solve {
    static final int WIDTH = 8;
    static final int HEIGHT = 8;
    static boolean found = false;
    static int[][] board = new int[HEIGHT][WIDTH];
    static int counter = 0;
    static void dfsSearch(int[][] plansza, Pozycja position, int krok){
        counter++;
        if(found){
            return;
        }
        plansza[position.y][position.x] = krok;
        List<Pozycja> ruchy = getAvailableMoves(plansza,position);
        if(ruchy.isEmpty()){
            if(krok >= (WIDTH*HEIGHT)){
                board = deepCopy(plansza);
                found = true;
            }
            return;
        }
        for (Pozycja ruch: ruchy) {
            dfsSearch(deepCopy(plansza),ruch,krok+1);
        }
    }
    static void dfsSearchWarnsdorrf(int[][] plansza, Pozycja position, int krok){
        counter++;
        if(found){
            return;
        }
        plansza[position.y][position.x] = krok;
        List<Pozycja> ruchy = getAvailableMoves(plansza,position);
        if(ruchy.isEmpty()){
            if(krok >= (WIDTH*HEIGHT)){
                board = deepCopy(plansza);
                found = true;
            }
            return;
        }
        //choose the next position that will give you the least positions to visit using priorityqueue
        PriorityQueue<Pozycja> pq = new PriorityQueue<>();
        for(Pozycja p : ruchy){
            p.moveValue = getAvailableMoves(plansza,p).size();
            pq.add(p);
        }
        while (!pq.isEmpty()){
            dfsSearchWarnsdorrf(deepCopy(plansza),pq.poll(),krok+1);
        }
    }
    static List<Pozycja> getAvailableMoves(int[][] plansza, Pozycja position){
        List<Pozycja> lista = new ArrayList<>(8);
        addMove(plansza,new Pozycja(position.x+2, position.y+1),lista);
        addMove(plansza,new Pozycja(position.x+2, position.y+-1),lista);
        addMove(plansza,new Pozycja(position.x+-1, position.y+2),lista);
        addMove(plansza,new Pozycja(position.x+-1, position.y+-2),lista);
        addMove(plansza,new Pozycja(position.x+1, position.y+2),lista);
        addMove(plansza,new Pozycja(position.x+1, position.y+-2),lista);
        addMove(plansza,new Pozycja(position.x+-2, position.y+1),lista);
        addMove(plansza,new Pozycja(position.x+-2, position.y+-1),lista);
        return lista;
    }
    public static void reset(){
        found=false;
        counter=0;
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                board[i][j] = 0;
            }
        }
    }
    static void addMove(int[][] plansza, Pozycja position, List<Pozycja> ruchy){
        if(between(position.x,plansza.length) && between(position.y,plansza[0].length) && plansza[position.y][position.x] == 0){
            ruchy.add(position);
        }
    }
    static boolean between(int a, int b){
        return a >= 0 && a < b;
    }
    static void wyswietlPlansze(){
        if(board.length <= 0){
            return;
        }
        System.out.println("------------------------");
        for (int[] wiersz: board) {
            System.out.println(Arrays.toString(wiersz));
        }
        System.out.println("------------------------\n");
    }
    static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
