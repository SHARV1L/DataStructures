// SHARVIL SINGH
// ASSIGNMENT 2: COMPLEXITY
// CWID: 20011939
// CS 570 C

import java.util.*;

public class Complexity
{
    private static int count;
    public static void method1(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Cannot have n less than 0");
        }
        count = 0;
        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void method2(int n)
    {
        if(n < 0){
            throw new IllegalArgumentException("Cannot have n less than 0");
        }

        count = 0;
        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void method3(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Cannot have n less than 0");
        }

        count = 0;
        int i = 1;
        while (i < n)
        {
            count++;
            i *= 2;
        }
        System.out.println(count);
    }

    public static void method4(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Cannot have n less than 0");
        }

        count = 0;
        for(int j = 0; j < n ; j++)
        {
            int i = 1;
            while (i < n)
            {
                count++;
                i *= 2;
            }
        }

        System.out.println(count);
    }

    public static void method5(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Cannot have n less than 0");
        }

        count = 0;
        int i = n;
        while(i > 2)
        {
            count++;
            i = (int) Math.sqrt(i);
        }

        System.out.println(count);
    }

    public static int method6(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Cannot have n less than 0");
        }

        count = 0;
        count++;
        if(n <= 1) return n;
        return method6(n-1) + method6(n-2);
    }

    public static void main(String[] args)
    {
        // CALLING ALL THE FUNCTIONS WITH SAME PARAMETERS
        System.out.println("Enter the base value of your loops:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Below is the running time of Different Algorithms:");
        method1(n);
        method2(n);
        method3(n);
        method4(n);
        method5(n);
        method6(n);
        System.out.println(count);
    }
}