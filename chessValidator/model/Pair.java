
public class Pair<T> {
    T row;
    T col;
    public Pair(T a, T b){
        this.row  =a;
        this.col = b;
    }

    public T getRow(){
        return this.row;
    }
    public T getCol(){
        return this.col;
    }
}
