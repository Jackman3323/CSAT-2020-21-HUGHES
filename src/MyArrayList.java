/**
 * MyArrayList
 * This is MY arrayList! NOT YOURS! lol just kidding as long as you pay me royalties I don't care
 * This program is basically my own version of the ArrayList class, of course we're not advanced enough to have
 * parameterized classes yet so it's not quite as cool. It has an object array as instance data as well as keeping
 * track of the number of filled spaces in that array so that it can automatically double the capacity of the array
 * when necessary.
 * Authors: Jack Hughes
 * Date: 4/11/20
 * On My Honor: JH
 * P.S. From 4/16/20:
 * Idk why i didn't submit this last week when I had it done lol I guess i got immediately caught up in minesweeper oops
 * I'm also going to try to make an iOS app. What should it do?
 */
public class MyArrayList {
    final int DEFAULT_CAPACITY = 10;
    int filledSpaces;
    Object[] objectArray;

    /* constructs an array list with a DEFAULT_CAPACITY */
    public MyArrayList() {
        objectArray = new Object[DEFAULT_CAPACITY];
    }

    /* constructs an array list with a capacity that is passed in */
    public MyArrayList(int initialCapacity) {
        objectArray = new Object[initialCapacity];
    }

    /* returns the size of the array list */
    public int size() {
        return objectArray.length;
    }

    /* returns the Object at position index */
    public Object get(int index) {
        return objectArray[index];
    }

    /* replaces the element at index with x returns the element formerly at the specified position */
    public Object set(int index, Object newItem) {
        Object currObj = objectArray[index];
        objectArray[index] = newItem;
        return currObj;
    }

    /* appends item to the end of the list, returns true */
    public boolean add(Object item) {
        if (this.size() == filledSpaces) {
            //full
            this.doubleCapacity();
        }
        objectArray[filledSpaces] = item;
        filledSpaces++;
        return true;
    }

    /* inserts item at position index, sliding elements at position index and higher to the right (adds 1 to their indices) and adjusts size */
    public void add(int index, Object item) {
        if (this.size() == filledSpaces) {
            //full
            this.doubleCapacity();
        }
        for(int i = filledSpaces; i > index;i--){
            objectArray[i] = objectArray[i-1];
        }
        objectArray[index] = item;
        filledSpaces ++;
    }

    /* removes elements from position index sliding elements at position index + 1 and higher to the left (subtracts 1 from their indices) and adjusts size */
    public Object remove(int index){
        Object temp = objectArray[index];
        for(int i = index; i < filledSpaces - 1; i++){
            objectArray[i] = objectArray[i+1];
        }
        filledSpaces--;
        return temp;
    }

    /* doubles the capacity of the Object array */
    private void doubleCapacity() {
        Object[] newObjects = new Object[objectArray.length * 2]; //Object array with doubled capacity
        //Now add in the old values to the beginning of the new array
        for(int i = 0; i < filledSpaces; i++){
            newObjects[i] = objectArray[i];
        }
        objectArray = newObjects; //Now assign main array to the temporary one
    }

}
