import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    public final Comparator<Point> slope_Order;
    
    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        slope_Order = new SlopeOrder();
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (compareTo(that) == 0){         
            return Double.NEGATIVE_INFINITY;
        } 
        else if (that.x == this.x){  
            return Double.POSITIVE_INFINITY;
        } 
        else if (that.y == this.y) {      
            return 0.0;
        } 
        else {
            double num = that.y - this.y;
            double den = that.x - this.x;
            return num / den;
        }
    }

    // compare two points by y-coord, breaking ties by x-coord
    public int compareTo(Point that) {
        if (this.y < that.y){
            return -1;
        }
        else if (this.x > that.y){
            return 1;
        }
        else if (this.x < that.x){
            return -1;
        }
        else if (this.x > that.x){
            return 1;
        }
        else return 0;
        
    }


    //Compares two points by the slope they make with this point.
    private class SlopeOrder implements Comparator<Point> {
        
        public int compare(Point p1, Point p2){
            if (Point.this.slopeTo(p1) < Point.this.slopeTo(p2)){
                return -1;
            }
            if (Point.this.slopeTo(p1) > Point.this.slopeTo(p2)){
                return 1;
            }
            else return 0;
        }
    }


    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    public static void main(String[] args) {
    }
}