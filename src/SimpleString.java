/**
 * SimpleString
 * This program is, simply put, a very simple, laid back set of strings. It plays a video of a cat playing with simple strings. (JK, I wish tho... last lab of year detected??)
 * This program is really a recreation of the basic methods of the String data type/class. You can create a list with an array
 * of chars or with another SimpleString. The SimpleString class has the following methods: substring, charAt, indexOf, concat, toString, and length.
 * Authors: Jack Hughes
 * Date: 2-20-20
 * On My Honor: JH
 */

public class SimpleString {
//your instance data here (Hint: use a char array)

    private char[] chars;
    public SimpleString() {
        chars = new char[0];
    }

    public SimpleString(char[] a) {
        chars = new char[a.length];
        System.arraycopy(a, 0, chars, 0, a.length);
    }
    public SimpleString(SimpleString str) {
        chars = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            chars[i] = str.charAt(i);
        }
    }
    public SimpleString substring(int from) {
        SimpleString newSimpleString;
        char[] newChars = new char[this.length() - from];
        int otherCounter = 0;
        for(int i = from; i < this.length(); i++){
            newChars[otherCounter] = chars[i];
            otherCounter ++;
        }
        newSimpleString = new SimpleString(newChars);
        return newSimpleString;
    }
    public SimpleString substring(int from, int to) {
        SimpleString newSimpleString;
        char[] newChars = new char[to-from];
        int otherCounter = 0;
        for(int i = from; i < to; i++){
            newChars[otherCounter] = chars[i];
            otherCounter ++;
        }
        newSimpleString = new SimpleString(newChars);
        return newSimpleString;
    }
    public char charAt(int val) {
        return chars[val];
    }
    public int indexOf(SimpleString myStr) {
        for (int i = 0; i < chars.length; i++) {
            int numMatches = 0;
            if (myStr.charAt(0) == chars[i]) {
                for (int j = 0; j < myStr.length(); j++) {
                    if(myStr.charAt(j) == chars[i + j]){
                        numMatches++;
                    }
                }
            }
            if(numMatches  == myStr.length()){
                return i;
            }
        }
        return -1;
    }
    public SimpleString concat(SimpleString str) {
        SimpleString newSimpleString;
        char[] newChars = new char[chars.length + str.length()];
        System.arraycopy(chars,0,newChars,0,chars.length);
        for(int i = 0; i < str.length(); i++){
            newChars[i + chars.length] = str.charAt(i);
        }
        newSimpleString = new SimpleString(newChars);
        return newSimpleString;
    }
    @Override
    public String toString() {
        String toReturn = "";
        for(int i = 0; i < chars.length; i++){
            toReturn = toReturn.concat(chars[i] + "");
        }
        return toReturn;
    }
    public int length() {
        return this.chars.length;
    }
}