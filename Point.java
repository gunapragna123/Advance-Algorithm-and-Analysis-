//S987P265 Kotur 
package AAA;
import java.util.Scanner;
import java.io.PrintWriter;

public class Point implements Comparable
{  private double x = 0, y = 0, z = 0;  // Coords of a point
   private boolean maximal = false;        // Whether the point is maximal

   public void setMaximal()  // Sets maximal to true: The point is maximal
   {
      maximal = true;
   }
   public double getX(){
      return x;
   }
   public double getY(){
      return y;
   }
   public boolean getMaximal(){
      return maximal;
   }

   public void inputPoint(Scanner ISObj)
   // Inputs a single point: Its x, y and z coords
   {
      String line = ISObj.nextLine();
      String[] coords = line.split("\\s");
      
      double coord_arr[] = new double[3];
      for(int k=0; k<3; k++){
         coord_arr[k] = Double.parseDouble(coords[k]);
      }
      
      x = coord_arr[0];
      y = coord_arr[1];
      z = coord_arr[2];
   }

   public int compareTo(Object o)
   // This is needed for "implements Comparable" above.
   // Compares THIS point to the other point "o": Returns +1, -1 or 0.
   // Used by Arrays.Sort() call in maxima.java.
   {  if ((o != null) && (o instanceof Point))
      {  
         Point p = (Point) o;
         
         if((p.x==x) && (p.y==y) && (p.z==z)){
            return 0;
         }
         
         if((z<p.z) || ((z==p.z) && (y<p.y)) || ((z==p.z) && (y==p.y) && (x<p.x))){
            return -1;
         }

         return 1;
      }
      return -1;
   }
}

