package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class Benchmark_Timer_InsertionSort {


    private static Config config;
    public static Integer[] partialArray(int n){


        Random rand = new Random();
        Integer[] a = new Integer[n];
        for (int i = 0; i<n/2; i++){
            a[i] = i;
        }

        for (int i = n/2; i<n; i++){

            a[i] =  rand.nextInt(n) + n/2;
        }

        return a;
    }
    
    public static Integer[] randomArray(int n){


        Random rand = new Random();
        Integer[] a = new Integer[n];
        for(int i = 0; i<n;i++){
            a[i] = rand.nextInt(n);
        }
        return a;
    }
    
   
    public static Integer[] sortedArray(int n){

        Integer[] a = new Integer[n];
        for(int i = 1; i<=n;i++){
            a[i-1] = i;
        }
        return a;
    }

    public static Integer[] reverseArray(int n){

        Integer[] a = new Integer[n];
        for(int i = 0; i<n;i++){
            a[i] = n-i;
        }
        return a;
    }
    

    public static void benchmarkTest(int n, String desc, Supplier<Integer[]> supplier){

        Helper<Integer> helper = new BaseHelper<>(desc, n, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                desc ,
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::sort,
                null

        );
        System.out.println(benchmark.runFromSupplier(supplier, 5)+  " ms");
    }


    public static void main(String[] args) {


           int n = 10000;
           
            String desc = "Insertion sort for partially sorted array of size: " + n;
            Supplier<Integer[]> supplier = () -> sortedArray(n);
            supplier = () -> partialArray(n);
            benchmarkTest(n, desc, supplier);
            
            desc = "Insertion sort for randomly sorted array of size: " + n;
            supplier = () -> randomArray(n);
            benchmarkTest(n, desc, supplier);
            
            desc = "Insertion sort for a sorted array of size: " + n;
            benchmarkTest(n, desc, supplier);

            desc = "Insertion sort for reverse sorted array of size: " + n;
            supplier = () -> reverseArray(n);
            benchmarkTest(n, desc, supplier);
            


    }

}
