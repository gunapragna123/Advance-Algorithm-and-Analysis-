//S987P265 Kotur
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Collections;

public class maxima
{
   public static void main(String args[]) {
      if (args.length != 2)
      {  // Error check
         System.out.println("Need to provide two inputs");
         System.exit(0);
      }

      Scanner infile = null;
      try
      {  
         infile = new Scanner(new File(args[0])); 
         
      }
      catch(FileNotFoundException e)
      {  System.out.println("Error opening the input file " + args[0]);
         System.exit(0);
      }

      PrintWriter outfile = null;
      try
      {  outfile = new PrintWriter(args[1]); }
      catch(FileNotFoundException e)
      {  System.out.println("Error opening the output file " + args[1]); 
         System.exit(0);
      }

      for (int i=1; i<=10; i++)  // Iterate on 10 sets of points
      {  int numPoints = infile.nextInt();
         infile.nextLine();
         Point[] points = new Point[numPoints];
         // System.out.println(points.length);

         inputPoints(infile, points);  // Reads the i-th input set
         Arrays.sort(points);  // Sorts points
         
         int maxNum = findMaximal(points);
         outfile.println("Output for " + i + "-th Set of Points");
         printMaximal(outfile, points, maxNum);
      }
      infile.close();

      outfile.close();
   }

   private static void inputPoints(Scanner infile, Point[] points)
   // Inputs the points into points[]
   {
      int point_length = points.length;
      for(int j=0; j<point_length; j++){
         Point p = new Point();
         p.inputPoint(infile);

         points[j] = p;
      }

   }

   private static void printMaximal(PrintWriter outfile, Point[] points,
                                    int maxNum)
   // Outputs the index of the maximal elements in points[]
   {  outfile.println("Input Size = " + points.length);
      outfile.println("MaxNum = " + maxNum);
      outfile.println();
      outfile.println("Maxima(S)");

      for (int i=0; i < points.length; ++i)
      {  
         if (points[i].getMaximal())
            outfile.println(i);
      }
      outfile.println();
      outfile.println("--------------------------------------------");
      outfile.println();
   }

   private static int findMaximal(Point[] points)
   // Find the maximal elements in points[]
   {  
      // Declare bst;
      TreeMap<Double,Double> bst = new TreeMap<Double,Double>(); 
      // System.out.println(bst);      
      // Insert (key, value) = (1.0, 0.0) into bst;
      bst.put(1.0, 0.0); 
      int maxNum = 0;

      double xcoord, ycoord;
      //System.out.println(points.length);
      //System.out.println(points[points.length-1].getX());
      //System.out.println(points[points.length-1].getY());
      for (int i = points.length-1; i >= 0; i--)
      {  
         xcoord = points[i].getX();
         ycoord = points[i].getY();
         if(i==points.length-1){
            // bst.remove(1.0);
            bst.put(ycoord, xcoord);
            (points[i]).setMaximal();
            maxNum += 1;
            continue;
         }


         double prev_y = 1.0;
         double prev_x = 0.0;
         boolean found = false;            
            // Find the point q in BST such that y(q) is just above (or equal to) y(pi)
         double yq = bst.higherKey(ycoord);
  
         double xq = bst.higherEntry(ycoord).getValue();

            if(yq < ycoord){

               continue;
            }
            else{

               if(xq<xcoord){

                  points[i].setMaximal();
                  maxNum += 1;
                  // consider the nodes in the BST
                  // whose y-coordinates are less than y(pi), in decreasing order of their y-coordinates; delete them
                  // one-by-one, until you come across a point p with x(p) > x(pi)
                  SortedMap<Double, Double> map_head = new TreeMap<Double, Double>();
                  map_head = bst.headMap(yq); 

                  Map<Double, Double> newMap = new TreeMap<Double, Double>(Collections.reverseOrder());
                  newMap.putAll(map_head);
                  for (Map.Entry<Double, Double> q1 : newMap.entrySet()){
                     double yp = q1.getKey();
                     double xp = q1.getValue();      
                     if(xp<=xcoord && yp < ycoord){
                        bst.remove(yp);
                        
                     }
                  }


                  bst.put(ycoord, xcoord);      
                  
               }

            }
         }

      

      return maxNum;
   }
}
