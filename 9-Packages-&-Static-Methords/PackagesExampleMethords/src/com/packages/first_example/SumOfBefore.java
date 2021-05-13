package com.packages.first_example;

public class SumOfBefore {

//will return the sum
    public static int returnSum(int num){
        int answer = 0 ;
        for(int i=1; i<=num; i++){
            answer +=i;
        }
        return answer;
    }

}
