package edu.neu.coe.info6205.union_find;

import java.util.Arrays;
import java.util.Random;

public class HWQUPC_Solution  {

    public static int count(int n)
    {
        UF F = new UF_HWQUPC(n);
        int count = 0;
        Random rand = new Random();
        while (F.components() > 1)
        {
            int p = rand.nextInt(n) ;
            int q = rand.nextInt(n) ;
            F.connect(p, q);
            count = count+1;
            System.out.println(p+" "+q);
        }
        return count;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if(args.length>0) {
            int connections = count(n);
            System.out.println("Number of Objects" + args[0] + "\nNumber of connections" + connections);
        }
    }
}