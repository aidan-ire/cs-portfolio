import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordCount{
    public static void main(String[] args) throws IOException{
        String path = "dorian.txt";
        if(args.length > 0) path = args[0]; // a different file can be given on the command line

        if(!Files.exists(Paths.get(path))){
            System.out.println("Could not find " + path + ", give the path to the text file as an argument");
            return;
        }

        String text = new String(Files.readAllBytes(Paths.get(path))).toLowerCase();
        text = novelText(text); // leave out the project gutenberg header and licence if the file has them
        String[] words = text.split("\\W+"); // split on anything that isn't a letter, digit or underscore

        HashMap<String, Integer> map = countWords(words);

        int total = 0; // adding up the counts gives the number of words in the text
        for(Map.Entry<String, Integer> entry : map.entrySet()) total += entry.getValue();

        System.out.println("Words in the text: " + total);
        System.out.println("Different words: " + map.size());

        String best = mostFrequent(map, 6);
        if(best == null){
            System.out.println("There are no six-letter words in the text");
        }else{
            System.out.println("Most frequent six-letter word: " + best + " (" + map.get(best) + " times)");
        }
    }

    /*
     * novelText
     * the project gutenberg edition has a header before the novel and a licence
     * after it, marked off by a line starting "*** START" and a line starting
     * "*** END". this cuts the text down to what is between those two lines so
     * only the novel is counted. the text has already been lowercased.
     * Arguments:
     *   text - the whole file in lowercase
     * Returns:
     *   the text between the two marker lines, or the text unchanged if either
     *   marker is missing
     */
    public static String novelText(String text){
        int start = text.indexOf("*** start");
        int end = text.indexOf("*** end");

        if(start == -1 || end == -1 || end < start) return text; // not a gutenberg file, count all of it

        int lineEnd = text.indexOf('\n', start); // the marker line has the title in it, so skip the whole line
        if(lineEnd == -1 || lineEnd > end) return text;

        return text.substring(lineEnd + 1, end);
    }

    /*
    counts how many times each word occurs. the word is the key and the count is
    the value. the first time a word is seen it goes in with 1, after that the
    count is read back and put in again one higher (put overwrites the old value).
    looking up and updating a HashMap is O(1) on average so the whole count is
    O(n) for n words.
    */
    public static HashMap<String, Integer> countWords(String[] words){
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < words.length; i++){
            String word = words[i];

            if(word.length() == 0) continue; // split gives an empty string first if the text starts with punctuation

            if(!map.containsKey(word)){
                map.put(word, 1);
            }else{
                map.put(word, map.get(word) + 1);
            }
        }

        return map;
    }

    /*
     * mostFrequent
     * goes through every entry of the map and keeps the word of the given length
     * with the highest count.
     * Arguments:
     *   map - the word counts
     *   len - the word length to look for
     * Returns:
     *   the most frequent word with len letters, or null if there isn't one
     */
    public static String mostFrequent(HashMap<String, Integer> map, int len){
        String best = null;
        int bestCount = 0;

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getKey().length() == len && entry.getValue() > bestCount){
                best = entry.getKey();
                bestCount = entry.getValue();
            }
        }

        return best;
    }
}
