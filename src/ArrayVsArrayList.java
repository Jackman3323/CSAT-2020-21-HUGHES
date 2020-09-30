/**
 * ArrayVsArrayList
 * This is the class file for the ultimate showdown. This class offers a variety of constructors and methods to put arrayLists and arrays
 * into epic brawls. Of course, none of these methods allows for any actual competition because they all take 0 seconds, but it's still
 * very fun to watch (we think at least)
 * Authors: JH, EW, DS
 * Date: 4/24
 * On My Honor: JH
 */

import java.util.ArrayList;
import java.util.Arrays;
public class ArrayVsArrayList {
    private ArrayList<Integer> arrayList;
    private Integer[] array;
    private int numFilled;
    public static final int DEFAULT_CAPACITY = 10;

    public ArrayVsArrayList(){
        arrayList = new ArrayList<Integer>(0);
        array = new Integer[DEFAULT_CAPACITY];
        numFilled = 0;
    }


    //Init both to defaultcapacity and add a to front
    public ArrayVsArrayList(Integer a){
        arrayList = new ArrayList<Integer>(DEFAULT_CAPACITY);
        array = new Integer [DEFAULT_CAPACITY];
        array[0] = a;
        arrayList.add(0,a);
        numFilled = 1;
    }

    public ArrayVsArrayList(Integer [] a){
        arrayList = new ArrayList<Integer>(0);
        array = new Integer [DEFAULT_CAPACITY];
        arrayList.addAll(Arrays.asList(a));
        System.arraycopy(a,0,this.array,0,a.length);
        numFilled = a.length;
    }
    //Add elem at index, grow array if necessary
    public char addAndPrintTime(int index, Integer elem){
        long startTimeArray = System.currentTimeMillis();
        if(numFilled == this.array.length){
            //Need to grow
            this.doubleCapacity();
        }
        //Add to array:
        for(int i = numFilled; i >= index; i--) {
            if (i == index) {
                //Insert here!! (but ofc you still have to move this box over)
                this.array[i + 1] = this.array[i];
                this.array[i] = elem;
            } else {
                //Move this box over
                this.array[i + 1] = this.array[i];
            }
        }
        long endTimeArray = System.currentTimeMillis();
        System.out.println("Adding to the array took " + (endTimeArray - startTimeArray) + " seconds!");
        //Add to arrayList:
        long startTimeArrayList = System.currentTimeMillis();
        arrayList.add(index, elem);
        long endTimeArrayList = System.currentTimeMillis();
        System.out.println("Adding to the ArrayList took " + (endTimeArrayList - startTimeArrayList) + " seconds!");
        numFilled++;
        char winner;
        if(endTimeArray-startTimeArray > endTimeArrayList - startTimeArrayList){
            //Array took longer
            winner = 'L';
        }
        else{
            winner = 'A';
        }
        return winner;
    }

    public void doubleCapacity(){
        Integer [] newOne = new Integer [this.array.length * 2];
        System.arraycopy(this.array,0,newOne,0,this.array.length);
        this.array = newOne;
    }
    //return a if array won, l if otherwise
    public char removeAndPrintTime(int index){
        //remove from array
        long startTimeArray = System.currentTimeMillis();
        Integer removedArray = this.array[index];
        for(int i = index; i < numFilled; i++){
            this.array[i] = this.array[i+1]; //Shift left
        }
        this.array[this.array.length-1] = null; //Remove last one
        long endTimeArray = System.currentTimeMillis();
        System.out.println("Removing from the array took " + (endTimeArray - startTimeArray) + " seconds!");
        //Remove from arrayList
        long startTimeArrayList = System.currentTimeMillis();
        Integer removedArrayList = this.arrayList.get(index);
        this.arrayList.remove(index);
        long endTimeArrayList = System.currentTimeMillis();
        System.out.println("Removing from the ArrayList took " + (endTimeArrayList - startTimeArrayList) + " seconds!");
        System.out.println("Removed elements matched: " + removedArray.equals(removedArrayList));
        numFilled--;
        char winner;
        if(endTimeArray-startTimeArray > endTimeArrayList - startTimeArrayList){
            //Array took longer
            winner = 'L';
        }
        else{
            winner = 'A';
        }
        return winner;
    }
}
