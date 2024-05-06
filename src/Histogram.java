import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * Title:Histogram.java
 * Abstract: Create a histogram that displays the number of occurrence for each letter
 * Author:Stacy Kirchner
 * Date: 8 November 2022
 */

public class Histogram {
    public static void main(String[] args){
        char[] letter = new char[11];
        int[] count = new int[200];
        int[] letterCount = new int[200];
        String filename = "";

        read(letter, letterCount, filename);

    }

    /**
     * returns the file name inputted by user
     */
    public static String getFileName(){
        String userFileName = "";
        System.out.println("Input filename:");
        Scanner scan = new Scanner(System.in);
        userFileName = scan.nextLine();
        return userFileName;
    }

    /**
     * reads file into program;
     * create char and int arrays to manipulate
     */
    public static void read(char[] letter, int[] letterCount, String filename) {
        String input = "";
        StringBuilder sb = new StringBuilder();
        String fileName = getFileName(); /* sets filename to the name inputted by user */
        File f = new File(fileName);
        int cnt = 0;

        try{
            Scanner scan = new Scanner(f);
            while(scan != null && scan.hasNext()){
                input = scan.next();
                sb.append(input);
                cnt++;
            }
        }catch(FileNotFoundException e){
            System.out.println("could not find the file " + e);
            e.printStackTrace();
        }

        letter = new char[cnt];
        String string2 = sb.toString();
        letter = string2.toCharArray();

        /**
         * count the number of instances for each char
         */
        for(int i = 0;  i < letter.length; i++){
            for (int j = 0; j < letter.length; j++) {
                if(letter[i] == letter[j]){
                    letterCount[i]++;
                }
            }
        }

        /**
         * detects duplicate chars in letter array
         */
        for(int i = 0; i < letter.length; i++) {
            for (int j = i+1; j < letter.length; j++) {
                if (letter[i] == letter[j] ){
                    letter[j] = ' ';
                    letterCount[j] = 0;
                }
            }
        }

        /**
         * determine new length of arrays
         */
        int count = 0;
        for(int i = 0; i < letter.length; i++) {
            if (letter[i] != ' ' ) {
              count = count + 1;
            }
        }

        /**
         * create new shorter array for letter
         */
        char[] newLetter = new char[count];
        int j = 0;
        for(int i = 0; i < letter.length; i++){
            if (letter[i] != ' ') {
                newLetter[j] = letter[i];
                j++;
            }
        }

        /**
         * create new shorter array for letterCount
         */
        int[] newLetterCount = new int[count];
        int l = 0;
        for(int i = 0; i < letterCount.length; i++){
            if(letterCount[i] != 0){
                newLetterCount[l] = letterCount[i];
                l++;
            }
        }

        /**
         * sorts array before print out
         */
        sort(newLetter, newLetterCount);

        System.out.println("Char occurrence");
        for (int k = 0; k < newLetter.length; k++){
            System.out.println(newLetter[k] + " " + newLetterCount[k]);
        }

        display(newLetter, newLetterCount);
    }

    /**
     * sorts the char array and int array in ascending order of occurrence
     */
    public static void sort(char[] letter, int[] letterCount) {
       int temp;
       char tmp;
       for(int i = 0; i < letter.length-1; i++){
           if(letterCount[i] > letterCount[i+1]){
               temp = letterCount[i];
               letterCount[i] = letterCount[i+1];
               letterCount[i+1] = temp;
               tmp = letter[i];
               letter[i] = letter[i+1];
               letter[i+1] = tmp;
               i = -1;
           }
       }
    }

    /**
     * display the histogram
     */
    public static void display(char[] letter, int[] count) {
        String abc = "       A B C D E F G H I J K";

        /**
         * sort letter and count array in descending order
         */
        int temp;
        char tmp;
        for(int i = 0; i < letter.length-1; i++){
            if(count[i] < count[i+1]){
                temp = count[i];
                count[i] = count[i+1];
                count[i+1] = temp;
                tmp = letter[i];
                letter[i] = letter[i+1];
                letter[i+1] = tmp;
                i = -1;
            }
        }

        /**
         * create an int array with number of occurrences descending
         */
        int [] counting = new int[count[0]];
        int x = count[0];
        for (int i = 0; i < counting.length; i++) {
            counting[i] = x;
            x--;
        }

        System.out.println("=============================");
        for(int i = 0; i < counting.length; i++){
            System.out.println("|" + "    " + counting[i] + "|" + "                    " + letter[i]);
        }
        System.out.println("-----------------------------");
        System.out.println(abc);
    }
}
