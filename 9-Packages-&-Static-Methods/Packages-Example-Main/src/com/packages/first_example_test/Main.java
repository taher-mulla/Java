package com.packages.first_example_test;


//these packages are in the other folder and are called from there
import com.packages.first_example.Factorial;
import com.packages.first_example.SumOfBefore;

public class Main {

    public static void main(String[] args) {
    
        int a = SumOfBefore.returnSum(3);
        System.out.println(a);

        a = Factorial.returnFactorial(4);
        System.out.println(a);

    }
}
