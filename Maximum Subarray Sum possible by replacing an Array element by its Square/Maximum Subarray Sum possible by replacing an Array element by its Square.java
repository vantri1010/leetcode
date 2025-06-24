import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
         int n = Integer.parseInt(br.readLine().trim());
         String[] arr_Arr = br.readLine().split(" ");
         int[] Arr = new int[n];
         for(int i_Arr=0; i_Arr<arr_Arr.length; i_Arr++)
         {
         	Arr[i_Arr] = Integer.parseInt(arr_Arr[i_Arr]);
         }

         long out_ = solution(Arr);
         System.out.println(out_);

         wr.close();
         br.close();
    }
    static long solution(int[] Arr){
        int n = Arr.length;
        int dp[][] = new int[n][2];

        // Stores sum without squaring
        dp[0][0] = Arr[0];
 
        // Stores sum squaring
        dp[0][1] = Arr[0] * Arr[0];
 
        // Stores the maximum subarray sum
        int max_sum = Math.max(dp[0][0], dp[0][1]);
        for (int i = 1; i < n; i++) {
 
            // Either extend the subarray
            // or start a new subarray
            dp[i][0] = Math.max(Arr[i], dp[i - 1][0] + Arr[i]);
 
            // Either extend previous squared
            // subarray or start a new subarray
            // by squaring the current element
            dp[i][1] = Math.max(dp[i - 1][1] + Arr[i], Arr[i] * Arr[i]);
 
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + Arr[i] * Arr[i]);
 
            // Update maximum subarray sum
            max_sum = Math.max(max_sum, dp[i][1]);
            max_sum = Math.max(max_sum, dp[i][0]);
        }
 
        // Return answer
        return max_sum;    
    }
}