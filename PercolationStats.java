import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    
    private Percolation exp;
    private int size;
    private int total;
    private double[] record;
    
   // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException(
               "Illegal parameter value: grid size and number of trials must be greater than 0");
        }
        size = n;
        total = trials;
        record = new double[trials];
        
        for (int i = 0; i<trials; i++){
            record[i] = test();
        }
    }
    
    private double test(){
        int count = 0;
        double fraction;
        exp = new Percolation(size);
        
        while (!exp.percolates()){
            int row = StdRandom.uniform(size) + 1;
            int col = StdRandom.uniform(size) + 1;
            
            if (!exp.isOpen(row,col)){
                exp.open(row,col);
                count++;
            }
        }
        
        fraction = ((double) count)/(size*size);
        return fraction;
    }
        

// sample mean of percolation threshold    
    public double mean(){
        return StdStats.mean(record);
    }
       
   // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(record);
    }
   // low  endpoint of 95% confidence interval    
    public double confidenceLo(){
        return mean() - 1.96*stddev()/Math.sqrt(total);
    }
   
   // high endpoint of 95% confidence interval    
    public double confidenceHi(){
        return mean() + 1.96*stddev()/Math.sqrt(total);
    }

    // test client (described below)    
   public static void main(String[] args){ 
       int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       PercolationStats answer = new PercolationStats(N,T);
       
       System.out.println("mean = " + answer.mean());
       System.out.println("stddev = " + answer.stddev());
       System.out.println("95% confidence interval = [" 
                              + answer.confidenceLo() + ", " + answer.confidenceHi() + "]");

   }
    
}
