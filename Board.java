import java.util.*;

public class Board {
    
    private int[][] blocks;
    private int n;
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks){
        this.blocks = blocks;
        this.n = blocks.length;
    }
    
    // get index of site (i, i)
    private int getIndex(int i, int j){
        return i*n + j + 1;
     }
     
    // get row of block k
    private int getRow(int k){
        return k/n;
    }
        
    // get column of block k 
    private int getCol(int k){
        return (k-1)%n;
    }   
        
    // board dimension n    
    public int dimension(){
        return n;
    }
        
    // number of blocks out of place    
    public int hamming(){
        int count = 0;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (blocks[i][j] != 0 && blocks[i][j] != getIndex(i,j)){
                    count++;
                }
            }
        }
        return count;
    }
        
        
    // sum of Manhattan distances between blocks and goal    
    public int manhattan(){
        int count = 0;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(blocks[i][j] != 0){
                    count = count + Math.abs(getRow(blocks[i][j]-i)) + Math.abs(getCol(blocks[i][j]-j));
                }
            }
        }
        return count;  
    }
        
    // is this board the goal board?    
    public boolean isGoal(){
        return (hamming()==0);
    }
    
        
    // a board that is obtained by exchanging any pair of blocks    
    public Board twin(){
        int[][] t = copyBoard();
        
        if (t[0][0] == 0 || t[0][1] == 0){
            int temp = t[1][0];
            t[1][0] = t[1][1];
            t[1][1] = temp;
        }                             
        else{
            int temp = t[0][0];
            t[0][0] = t[0][1];
            t[0][1] = temp;
        }        
            
        return new Board(t);
    }
    
    // copy board
    private int[][] copyBoard(){
        int[][] b = new int[n][n];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                b[i][j] = blocks[i][j];
            }
        }
                    
        return b;
    }
        
    // does this board equal y?    
    public boolean equals(Object y) {
        if (!(y instanceof Board)){
            return false;
        }
        Board that = (Board) y;
        if (that.n != n){
            return false;
        }
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(that.blocks[i][j] != blocks[i][j]){
                    return false;
                }
            }
        }
        
        return true;
    }
       
    // all neighboring boards    
    public Iterable<Board> neighbors(){
        ArrayList<Board> boards = new ArrayList<Board>();
        int row = 0;
        int col = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (blocks[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        
        if (row > 0){
            int[][] right = copyBoard();
            right[row][col] = right[row-1][col];
            right[row-1][col] = 0;
            boards.add(new Board(right));
        }
        if (row < n){
            int[][] left = copyBoard();
            left[row][col] = left[row+1][col];
            left[row+1][col] = 0;
            boards.add(new Board(left));
        }
        if (col > 0){
            int[][] down = copyBoard();
            down[row][col] = down[row][col-1];
            down[row][col-1] = 0;
            boards.add(new Board(down));
        }
        if (col < n){
            int[][] up = copyBoard();
            up[row][col] = up[row][col+1];
            up[row][col+1] = 0;
            boards.add(new Board(up));
        }
        
        return boards;
    }
        
    // string representation of this board (in the output format specified below)    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%2d", blocks[i][j]));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
        
    // unit tests (not graded)    
    public static void main(String[] args){
    }
    
}