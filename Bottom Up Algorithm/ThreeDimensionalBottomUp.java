import java.util.ArrayList;
import java.util.Scanner;

public class IE343ProjectPart1 {
    //ANIL TURGUT
    private static int BottomUp(int [] v, int [] w, int [] h, int W , int H){                        //Bottom-up function implements the bottom-up algorithm i have learned
        int n = v.length;                                                                            //I have assigned the number of item to the variable n
        int [][][] Knapsack = new int[n+1][W+1][H+1];                                                //I have created a three-dimensional array (matrix) which contains the values from Value Function

        for(int j = 0; j < W; j++){                                                                  //In this nested for loop, i have assigned the values of first row = 0
            for(int k = 0; k < H; k++){                 //Complexity = αWH where α is constant
                Knapsack[0][j][k] = 0;
            }
        }

        for(int i = 1; i <= n; i++){                                                                                    //In here, three-dimensional for loop tries to implement the bottom-up algorithm.
            for(int j = 0; j <= W; j++){                                                                                //In each iteration, i,j,k variables are turning into three-dimensional matrix called Knapsack
                int weight = w[i-1];                                                                                    //and assigns the corresponding value to a "cell". Then, in this code snippet i have used 3 constraints
                for(int k = 0; k <= H; k++){            //Complexity = αnWH = nWH where α is constant                                                                //First and second one are the capacity constraints (W,H) and the third one is which one is maximum (extra function)
                    int volume = h[i-1];                                                                                //Moreover, i have initialized the items' weights and volumes in different iterations and then,
                    if(weight <= j && volume <= k){                                                                     //In every iteration the capacity values of item is compared with the remaining capacity values,
                        Knapsack[i][j][k] = max(Knapsack[i-1][j][k],v[i-1]+Knapsack[i-1][j-weight][k-volume]);       //If it is less than remaining one, the max value between "taking this item" and "not taking this item" is being defined
                                               //(Do not take the item i,   Take item i)                                //After the decision had been made, the required updates are being made(For instance: if item is taken, the value of item is added to
                        //System.out.println(Knapsack[i][j][k]);                                                        //the value which coming from the [item -1][remaining W][remaining H])
                    }else{                                                                                              //If the item's capacity is more than the remaining capacity, then the program continues without this item.
                        Knapsack[i][j][k] = Knapsack[i-1][j][k];
                    }
                }
            }
        }
        return Knapsack[n][W][H];                                                                                       //Finally, the BottomUp function returns the last cell (in terms of indexes)
    }                                                                                                                   //which is equal to the objective function value (optimum solution)
    public static int max(int a, int b){
        if (a > b){
            return a;                                       //In the max() function, i basically tried to create a function which returns the maximum value
        }else{                                              // between two values a and b (parameters)
            return b;
        }
    }
    public static void main(String[] args) {
        

        Scanner scan = new Scanner(System.in);                                        //In here i tried to extend the code by using the Scanner in order to get
        System.out.println("Enter the number of item you have: ");                      //input from the user. Program asks for all the values about Knapsack and
        int numberOfItem = scan.nextInt();                                              //the values about the items we have. Then, i have transfer these inputs to the
        System.out.println("Enter the capacity of your knapsack: ");                    //function BottomUp as parameters, i ll get the output(objective value)
        int W = scan.nextInt();
        System.out.println("Enter the volume of your knapsack: ");
        int H = scan.nextInt();
        int [] v = new int[numberOfItem];
        int [] w = new int[numberOfItem];
        int [] h = new int[numberOfItem];
        for (int i = 0; i < numberOfItem; i++){
            System.out.println("Enter the value of item "+(i+1)+": ");
            v[i] = scan.nextInt();
            System.out.println("Enter the weight of item "+(i+1)+": ");
            w[i] = scan.nextInt();
            System.out.println("Enter the volume of item "+(i+1)+": ");
            h[i] = scan.nextInt();
        }
        /*int [] v = {25,30,20,18,42,55,27,10};                                                      //Since the getting input from user might be time-consuming i'll also work with the
        int [] w = {6,5,4,3,3,9,12,5};                                                          //sample values. I have created three arrays (value, weight and volume) and i have
        int [] h = {4,6,5,6,7,9,5,2};                                                          //defined 2 variables volume and weight. Finally, i have printed out the objective value.
        int H = 35;
        int W = 20;*/

        System.out.println("Optimum objective value is: " + BottomUp(v,w,h,W,H));       //Total Complexity of this algorithm = nWH
    }
}
