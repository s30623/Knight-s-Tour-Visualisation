public class Pozycja implements Comparable {
    public int x;
    public int y;
    public int moveValue = 0;

    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.moveValue,((Pozycja)o).moveValue);
    }

}
