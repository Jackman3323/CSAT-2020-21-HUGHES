public class PrintNums {

    public PrintNums(){

    }

    public void printSomeOrderedNums(){
        /**
         * Ways I can think of:
         * 1. Sout them in order on ten lines
         * 2. For loop using i + 1
         * 3. For loop using a decreasing i and subtracting it from 10
         * 4. For loop that prints from an array of nums that are in the right order
         * 5. For loop that prints from an arrayList of nums that are in the right order
         * 6. While loop with a counter
         * 6. While loop with a decreasing counter
         * 7. While loop that prints from an array
         * 8. While loop that prints from an arrayList
         * 9. (Didn't finish all of these lol)
         */

        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(8);
        System.out.println(9);
        System.out.println(10);

        for(int i = 0; i < 10; i++){
            System.out.println(i+1);
        }

        for(int i = 1; i < 11; i++){
            System.out.println(i);
        }

        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        for(int i = 0; i < 10; i++){
            System.out.println(arr[i]);
        }
    }
}
