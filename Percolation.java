import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
    private boolean[][] grid;
    private int size;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF uf;
     
            
    // create n-by-n grid, with all sites blocked         
    public Percolation(int n){
       size = n;
       grid = new boolean[size][size];
       bottom = size*size + 1;
       uf = new WeightedQuickUnionUF(size*size + 2);
    }
    
    // get index of site (row, column)
     private int getIndex(int row, int col){
        if (row < 1 || row > size || col < 1 || col > size){
            throw new IndexOutOfBoundsException(
               "Illegal parameter value: row and column indices must be between 1 and " + size);
         }
        return (row-1)*size + col;
     }
    
    // open site (row, column) if it is not open already        
    public void open(int row, int col){
       if (row < 1 || row > size || col < 1 || col > size){
           throw new IndexOutOfBoundsException(
               "Illegal parameter value: row and column indices must be between 1 and " + size); 
       }
       grid[row-1][col-1] = true;
       
       if (row == 1){
           uf.union(top, getIndex(row, col));
       }
       if (row == size){
           uf.union(bottom, getIndex(row, col)); 
       }
       if (row > 1 && isOpen(row-1, col)){
           uf.union(getIndex(row,col), getIndex(row-1,col));
       }
       if (row < size && isOpen(row+1, col)){
           uf.union(getIndex(row,col), getIndex(row+1,col));
       }
       if (col > 1 && isOpen(row, col-1)){
           uf.union(getIndex(row,col), getIndex(row,col-1));
       }
       if (col < size && isOpen(row, col+1)){
           uf.union(getIndex(row,col), getIndex(row,col+1));   
       }
    }        
       
    // is site (row, column) open?
    public boolean isOpen(int row, int col){     
        if (row < 1 || row > size || col < 1 || col > size){
            throw new IndexOutOfBoundsException(
               "Illegal parameter value: row and column indices must be between 1 and " + size);
        }
        return (grid[row-1][col-1] == true);
    }
        
    
    // is site (row, column) full?    
    public boolean isFull(int row, int col){     
        if (row < 1 || row > size || col < 1 || col > size){
            throw new IndexOutOfBoundsException(
               "Illegal parameter value: row and column indices must be between 1 and " + size);
        }
        return (uf.connected(top, getIndex(row, col)));
    }
    
    public int numberOfOpenSites(){
        return uf.count();    
    }
    
    // does the system percolate?
    public boolean percolates(){            
        return uf.connected(top, bottom);
    }    
    
    // test client (optional)
    public static void main(String[] args){  
    }
}
