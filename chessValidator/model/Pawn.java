public class Pawn extends Piece{



    @override
    public void move(Sting startingPostion,String endPosition) thows Exception{
       try{
        char c[] = startingPostion.toCharArray();
        char col = c[0];
        char row = c[1];
        char d[] = endPosition.toCharArray();
       }
       catch(Exception e){
        throw e;
       }
    }
}