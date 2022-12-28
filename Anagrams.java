// NAME: SHARVIL SINGH
// CLASS: CS 570-C
// C-WID: 20011939

import java.io.*;
import java.util.*;

public class Anagrams
    {
        //INITIALIZING DATA
        final Integer[] primes= {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        Map<Character,Integer> letterTable;
        Map<Long,ArrayList<String>> anagramTable;

        // CONSTRUCTOR FOR INITIALIZING HASH MAP
        public Anagrams()
            {
                letterTable= new HashMap<Character,Integer>();
                buildLetterTable();

                anagramTable= new HashMap<Long,ArrayList<String>>();
            }

        // LETTER-TABLE FOR HASH CODE
        private void buildLetterTable()
            {
                int i = 0;
                Character[] alphabets= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

                while(i < 26)
                    {
                        //To assign respective prime number to alphabets character
                        letterTable.put(alphabets[i],primes[i]);
                        i++;
                    }
            }

        // STRING ADDED AS INPUT INTO THE HASH TABLE
        private void addWord(String s)
            {
                //If String Not Present, Add The Word To Anagram Table And Compute Hash Code
                if (!anagramTable.containsKey(myHashCode(s)))
                    {
                        ArrayList<String> hash_val=new ArrayList<String>();
                        hash_val.add(s);

                        anagramTable.put(myHashCode(s), hash_val);
                    }
                else
                    { //If String Is Present In Anagram Table Then Hash Code Is Fetched From The Anagram Word
                        ArrayList<String> hash_val=anagramTable.get(myHashCode(s));
                        hash_val.add(s);

                        anagramTable.replace(myHashCode(s), hash_val);
                    }
            }

        // HASH CODE COMPUTED
        private Long myHashCode(String s)
            {
                int i=0;
                long hashCode=1;

                while(i < s.length())
                    {
                        Character x_val=s.charAt(i);
                        hashCode *=letterTable.get(x_val);
                        i++;
                    }
                return hashCode;
            }

        // INPUT FILE PROCESSING
        private void processFile(String s) throws IOException
            {
                String  strLine;
                FileInputStream fstream  =  new  FileInputStream(s);
                BufferedReader br = new BufferedReader (new InputStreamReader(fstream));

                while ((strLine = br.readLine()) != null )
                    {
                        this.addWord ( strLine );
                    }
                br.close ();
            }

        // GETTING THE BIGGEST NUMBER
        private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
        {
            int alpha=0;
            ArrayList<Map.Entry<Long,ArrayList<String>>> x_val= new ArrayList<Map.Entry<Long,ArrayList<String>>>();

            for (Map.Entry<Long,ArrayList<String>> ent_val:anagramTable.entrySet())
                {
                    if(ent_val.getValue().size() == alpha)
                        {
                            x_val.add(ent_val);
                        }
                    else {
                        if(ent_val.getValue().size() > alpha)
                        {
                            x_val.clear();
                            x_val.add(ent_val);
                            alpha=ent_val.getValue().size();
                        }
                    }
                }
            return x_val;
        }

        // MAIN FUNCTION
        public static void main(String[] args)
        {
            Anagrams ana = new  Anagrams();
            final long startTime  = System.nanoTime();

            try {
                ana.processFile("words_alpha.txt"); // INPUT FILE FOR WORDS
            } catch( IOException e1 ) {
                e1.printStackTrace ();
            }

            ArrayList <Map.Entry<Long,ArrayList<String>>> maxEntries = ana.getMaxEntries();

            final long  estimatedTime = System.nanoTime() - startTime;
            final double  seconds  =  ((double) estimatedTime / 1000000000);

            System.out.println ("ELAPSED TIMING(SECONDS): "+  seconds );

            System.out.println("MAXIMUM ANAGRAM KEY: " + maxEntries.get(0).getKey());

            System.out.println ("ANAGRAMS MAXIMUM LIST: "+ maxEntries.get(0).getValue() );

            System.out.println("ANAGRAM LIST MAXIMUM LENGTH: "+maxEntries.get(0).getValue().size());
        }
    }