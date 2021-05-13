package com.packages.first_example;

public class Factorial {

	//will return the factorial
    public static int returnFactorial(int num){
        int answer=1;
        for(int i=1; i<=num; i++){
            answer *=i;
        }
        return answer;
    }

}
