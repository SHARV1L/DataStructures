// Sharvil Singh - CS 570 C - 20011939

// This Code uses Big Endian Format

import java.util.*;
import java.lang.*;

import static java.lang.System.exit;

public class BinaryNumber {

    static int length;
    private int[] bnum;
    private int[] shiftedbNum;
    //private int[] sum;
    private boolean overFlow = false;
    // Constructor to create a Binary number and initialize it with zeros
    BinaryNumber(int length) {
        bnum = new int[length];
        Arrays.fill(bnum, 0);
        //System.out.println("binary number:",Arrays.toString(b1));
    }
    // Constructor to generate a binary number from a given string.
    BinaryNumber(String st) {

        String[] splitArray = st.split("");
        bnum = new int[splitArray.length];
        for (int i = 0; i < splitArray.length; i++) {

            bnum[i] = Integer.parseInt(splitArray[i]);
        }

            //System.out.println("$$"+i +splitArray[i]);

    }
    // function to get the length of a binary number
    public int getLength() {
        return bnum.length;
    }
    // function to get the digit at given index
    public int getDigit(int index) {
        return bnum[index];
    }
    //function to perform shiftR by a given amount
    public void shiftR(int amount) {
        int newLength = amount + bnum.length;
        int difference = newLength - bnum.length;

        //code to shift the number to right for a given amount
        int[] temp = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i < difference) {
                temp[i] = 0;
            } else {
                temp[i] = bnum[i - difference];
            }
        }
        shiftedbNum = temp;

        System.out.println("Shifted Number is: " + toString(shiftedbNum));

    }


    // function to return the decimal value of a given binary number
    public int toDecimal() {
        int decimal =0;
        for(int i = 0;i<bnum.length;i++)
        {
            decimal+= bnum[i]*Math.pow(2,bnum.length-1-i);
        }
      return decimal;
    }
    //function for adding two binary numbers
    void add(BinaryNumber aBinaryNumber) {
        int carry=0; int[] sum = new int[bnum.length+1];
        for(int i = bnum.length-1;i>=0;i--)
        {
            sum[i+1]=(carry +bnum[i]+aBinaryNumber.bnum[i])%2;
            if(sum[i+1]==0){
                if(bnum[i]==1 && aBinaryNumber.bnum[i]==1&&carry==0){
                    carry=1;
                    overFlow=true;
                }

            }
            if(sum[i+1]==1){
                if(bnum[i]==0 && aBinaryNumber.bnum[i]==0&&carry==1){
                    carry=0;
                    overFlow=false;
                }

            }

        }
        if(carry == 1){
            sum[0]=1; //appending 1 as MSB to the sum incase carry is one for the MSB Addition
            clearOverflow(); //clearing overflow
        }
        System.out.println("Sum of b1 and b2 is "+ aBinaryNumber.toString(sum));
    }


    // function to convert a given int[]  array to a String
    public String toString(int[] arr)
    {
        if (overFlow) {
            return "Overflow";
        }
        String s = "";
        for(int x: arr)
        {
            s = s+x;
        }
        return s;
    }

    //function to clear Overflow
    public void clearOverflow () {
        overFlow=false;
    }

    //main function
    public static void main (String[]args) {
        try{
            System.out.println("Enter the Binary Number length");
            boolean validLength = false;
            Scanner sc = new Scanner(System.in);
            while(!validLength){
            if(sc.hasNextInt()){
            length = sc.nextInt();
            if(length>10 || length<1){
                System.out.println("Please Enter length from 1-10");
            }
            else{
                validLength=true;
            }
            }}
            new BinaryNumber(length); //1st operation
            System.out.println("Enter the first Binary Number");

            String str="";
            boolean isValidNumber=false;
            while(!isValidNumber){
                str = sc.next();
                if(str.length()==length){


                for(int j=0;j<str.length();j++){
                if(str.charAt(j)=='0'||str.charAt(j)=='1'){
                    if(j==str.length()-1){
                        isValidNumber=true;

                    }
                }
                else{
                    isValidNumber=false;
                    System.out.println("Enter a valid boolean number");
                    break;
                }
            }}
                else {

                    System.out.println("Enter a boolean number of valid length");
                }

            }

            BinaryNumber ob = new BinaryNumber(str);//2nd operation

            int l1 = ob.getLength(); //3rd operation
            System.out.println("Length Of  Number is: " + l1);
            System.out.println("Enter the index value");
            int index ; boolean validIndex=false;
            while(!validIndex){
                if(sc.hasNextInt()) {
                    index = sc.nextInt();
                    if(index>= ob.getLength() || index<0){
                        System.out.println("Entered a valid index value ");

                    }
                    else {
                        validIndex=true;
                        int d1 = ob.getDigit(index); //4th operation
                        System.out.println("Value at " + index + " is : " + d1);

                    }
                }}

            System.out.println("Enter the shift amount: ");
            int amount = sc.nextInt();
            ob.shiftR(amount); //6th operation


            int decimalValue = ob.toDecimal();
            System.out.println("Decimal Value for B1 is:"+decimalValue);
            System.out.println("Enter the second binary number");
            String str2="" ; boolean isValidNumber2 = false;
            while(!isValidNumber2){
                str2 = sc.next();

                if(str2.length()== ob.getLength()){
                    for(int j=0;j<str2.length();j++){

                        if(str2.charAt(j)=='0'||str2.charAt(j)=='1'){
                            if(j==str2.length()-1){
                                isValidNumber2=true;

                            }
                        }
                        else{
                            isValidNumber2=false;
                            System.out.println("Enter a valid boolean number");
                            break;
                        }
                    }}
                else {
                    System.out.println("Enter a boolean number of valid length");
                }

            }

            BinaryNumber obj = new BinaryNumber(str2);
            ob.add(obj); //7th operation
            String s =ob.toString(ob.bnum);//8th operation // returns the string for array passed as argument
            System.out.println("Output for B1 toString() is: "+s);
            exit(0);

        }
        // Catch block handles the exceptions thrown.
        catch(ArithmeticException e)
        {
            System.out.println("Arithmetic Exception occurs");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBounds Exception occurs");
        }
        catch(NullPointerException e)
        {
            System.out.println("Null Pointer Exception occurs");
        }
        catch(Exception e)
        {
            System.out.println("Parent Exception occurs");
        }
    }
}

// Thank you!


