/**
 *
 * myHashTable.
 *
 * The functions that are allowed to use are:
 *
 * myHashTable(int size): myHashTable test = new myHashTable(31);
 * addWord(String s): test.addWord(s);
 * printCollision(): test.printCollision();
 * printTableSize(): test.printTableSize();
 * printHashTable(): test.printHashTable();
 *
 *
 */

import java.util.*;

public class myHashTable {
    private String[] table;
    private List<String> wordsForRehash;
    private int collisionTimes;

    // Construct method.
    private myHashTable(int size){
        table = new String[size];
        wordsForRehash = new ArrayList<>();
        collisionTimes = 0;
    }

    // To add words.
    private void addWord(String s){
        wordsForRehash.add(s);
        table[computeHash(s)] = s;
        double load = (double)(wordsForRehash.size()) / (double)(table.length);
        if (load >= 0.50000001){
            System.out.println("Table extension needed. Extending the table...");
            expandTable();
        }
    }

    // To compute hash value.
    private int computeHash(String s){
        // 1. Compute the result of hash function.
        char[] tmp = s.toCharArray();
        int hashValue = 0;
        for (char c : tmp){
            hashValue += c;
        }
        // 2. See if there is collision.
        boolean collisionHappen = false;
        int index = hashValue % table.length;
        for (int i = 1; ; i++){
            // 2.1 There is no collision.
            if (table[index] == null){
                break;
            }
            // 2.2 There is collision.
            else {
                index = (hashValue + i * i) % table.length;
                collisionHappen = true;
            }
        }
        if (collisionHappen){
            collisionTimes++;
        }
        return index;
    }

    // To extend the table.
    private void expandTable(){
        // Find the appropriate size first.
        int tmp = table.length * 2;
        while (true){
            if (isPrime(tmp)){
                break;
            }
            else {
                tmp += 1;
            }
        }
        // Move the elements to the new array.
        // And also, update all the values.
        table = new String[tmp];
        collisionTimes = 0;
        for (String s : wordsForRehash){
            table[computeHash(s)] = s;
        }
    }

    // To print how many times collision happens.
    private void printCollision(){
        System.out.println("The total number of the collision: " + collisionTimes);
    }

    // To print the table size.
    private void printTableSize(){
        System.out.println("The size of the table: " + table.length);
    }

    // To print the hash table.
    private void printHashTable(){
        int nullCount = 0;
        for (int i = 0; i < table.length; i += 1){
            if (table[i] == null && nullCount == table.length - 1){
                System.out.println("The table is empty.");
            }
            else if (table[i] == null){
                nullCount++;
            }
            else {
                System.out.println(i + "->" + table[i]);
            }
        }
    }

    // To find the next prime.
    private boolean isPrime(int num){
        if (num % 2 == 0){
            return false;
        }
        for (int i = 3; i * i <= num; i += 2){
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        /**
         * myHashTable(int size): myHashTable test = new myHashTable(31);
         * addWord(String s): test.addWord(s);
         * printCollision(): test.printCollision();
         * printTableSize(): test.printTableSize();
         * printHashTable(): test.printHashTable();
         */
        myHashTable test = new myHashTable(31);
        String[] words = {
                "Hash", "table", "Pick", "word", "lengths",
                "maximum", "minimum", "Insert", "ASCII", "space",
                "probing", "size", "adequate", "Chen", "Yingue",
                "UTDallas", "ECSS", "Algo", "2018Fall", "Texas"
        };
        for (String s : words){
            test.addWord(s);
        }

        test.printTableSize();
        test.printCollision();
        test.printHashTable();
    }
}
