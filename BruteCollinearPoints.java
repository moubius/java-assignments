import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    
   private  ArrayList<LineSegment> segments;
     
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points){
       checkPoints(points);
       
       segments = new ArrayList<LineSegment>();
     
       Arrays.sort(points);
       
       for (int i = 0; i < points.length; i++){
           for (int j = i+1; j < points.length; j++){
               for (int k = j+1; k < points.length; k++){
                   for (int l = k+1; l < points.length; l++){
                       if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) 
                               && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])){                     
                           segments.add(new LineSegment(points[i], points[l]));                         
                       }
                   }
               }
           }
       }
   }
                  
   // the number of line segments   
   public int numberOfSegments(){
       return segments.size();
   }
           
   
   // the line segments    
   public LineSegment[] segments(){
       return segments.toArray(new LineSegment[segments.size()]);
   }
   
   // check points for null and duplicate
   private void checkPoints(Point[] points){
       if (points == null){
            throw new NullPointerException("Empty array");
       }
       
       for (int i = 0; i < points.length; i++){ 
            if (points[i] == null){
                throw new NullPointerException("Empty point");
            }
       }
       
       for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                if (points[i].compareTo(points[j]) == 0){
                    throw new IllegalArgumentException("Duplicate points");
                }
            }
       }
   }
   
}
