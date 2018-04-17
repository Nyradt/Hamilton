/*
Daryn Thompson
Hamilton
4/17/2018


 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Hamilton {
    public static Scanner scanner = new Scanner(System.in);
    //public static int[][] aTable = {{0,1,1},{1,0,1},{1,1,0}};
    //public static int[][] aTable = {{0,0,0},{1,0,1},{1,1,0}};
    public static int[][] aTable = {{0,1,1,0,0,0,1,1},{1,0,1,0,0,0,1,1},{1,1,0,1,0,1,0,0},{0,0,1,0,1,0,0,0},{0,0,0,1,0,1,0,0},{0,0,1,0,1,0,1,0},{1,1,0,0,0,1,0,1},{1,1,0,0,0,0,1,0}};
    public static int n = 8;                  //number of vertices
    public static int[]vindex = new int[n];
    public static int keepGoing =0;

    public static void main(String[] args) {
      //  getGraph();

        printTable(n,aTable);

        hamiltonian(0);

//        promising(1,aTable) ;                            //               //test

    }

    public int[][] getGraph(){

        int[][] aTable;

        System.out.println("Enter number of vertices");
        int size = scanner.nextInt();
        aTable = new int[size][size];
        String blank = scanner.nextLine();  //needed to start by line input


        System.out.println("Enter adjacency matrix one line at a time, with commas " +
                "separating each integer values, 0(zero) for same vertex and -2 for infinity");
        for (int i = 0; i < size; i++) {
            int RowNum = i + 1;
            System.out.println("Enter row #:" + RowNum);
            String row = scanner.nextLine();
            String[] rowString = row.split(",");

            for (int j = 0; j < size; j++) {
                if (Integer.parseInt(rowString[j]) == -2) {
                    aTable[i][j] = Integer.MAX_VALUE;   //Max_Value represents infinity
                } else {
                    aTable[i][j] = Integer.parseInt(rowString[j]);
                }
            }
        }

        printTable(size,aTable);                            //prints the entered array

        //System.out.print(edges);
        return aTable;
    }


    //Pre Condition: A 2d array of size nxn in an integer array, where infinity is equal to -2 and the
    //  the same vertex of a row and column is equal to 0(zero)
    //Post Condition: Table is printed on screen with row by column labels
    //  with "-" for same vertex by same vertex and "inf" for infinity

    public static void printTable(int size, int[][] aTable) {
        System.out.print("   | ");
        for (int i = 1; i < size + 1; i++) {  //prints column title
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 1; i < size * 4 + 4; i++) { //prints ---- row
            System.out.print("-");
        }
        System.out.println();
        for (int x = 0; x < size; x++) {
            System.out.printf("%3d|", x + 1);  //prints row number
            for (int y = 0; y < size; y++) {
                if (aTable[x][y] == Integer.MAX_VALUE) {
                    System.out.print("inf");
                } else {
                    if (aTable[x][y] == 0) {
                        System.out.print(" -  ");
                    } else {
                        System.out.printf("%3d ", aTable[x][y]);
                    }

                }

            }
            System.out.println();

        }
    }

        public static int wOffset = 0;

        //needd to do promising conditions
        public static boolean promising (int i,int[][]w)
        {

            //printVindex();
            //System.out.println();
            int j;
            boolean sw;
//            System.out.println("Promising started "+Boolean.toString(sw)+" "+i);

            if(i==(n-1) && w[vindex[n-1]-wOffset][vindex[0]-wOffset]==0){  //check first last adjecnt
                sw = false;
//                System.out.println("is first adjecent to last?"+Boolean.toString(sw));
            }
            else if(i>0 && w[vindex[i-1]-wOffset][vindex[i]-wOffset] == 0 )
            {
                sw = false;
 //               System.out.println("ith vertex is not adjacent to (i-1)t"+Boolean.toString(sw));

            }
            else {
                sw = true;
                j=1;
//                System.out.println("i = "+i);

                while (j<i && sw){          //check to see if vertex is already used
 //                   if(w[i-wOffset]==w[j-wOffset]){
                    if(vindex[i]==vindex[j]){
                        sw = false;
//                        System.out.println("Vertex is already used"+Boolean.toString(sw));

                    }
                j++;
                }
            }
            return sw;
        }
        public static void hamiltonian(int i)

        {
            int j;
//            System.out.println("Hamilton ran");
            if(promising(i,aTable))
            {
//                System.out.println("value of " +i +" was promising ");

                if (i==(n-1))
                {
              //      System.out.println(vindex[0]+" through "+vindex[n-1]);
                    for(int k=0;k<n-1;k++) {
                        System.out.println((vindex[k]+1) + " through " + (vindex[k + 1]+1));
                    }
                    System.out.println();
                    if(keepGoing==0)
                    {
                        System.out.println("Press 1 to print all circuits");
                        keepGoing = scanner.nextInt();
                    }

                }
                else
                {
                    for(j=1; j<n; j++){  //j probaly 1 book says 2
//                        System.out.printf("j %d, i %d, n-1 %d ",j,i,n-1);
                        vindex[i+1]=j;
                        hamiltonian(i+1);

                    }
                }
            }
        }

        public static void printVindex(){
            for(int k= 0;0<3;k++){
                System.out.print(vindex[k] +" ");
            }
        }


}



