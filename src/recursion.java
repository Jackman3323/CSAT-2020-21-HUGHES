public class recursion {

    //Calls itself after updating until end condition is met

    public void recursiveLoopPrint(int i, int max){
        if(i >= max){
            System.out.println(i);
            return;
        }
        else{
            System.out.println(i);
            i++;
            this.recursiveLoopPrint(i,max);
        }
    }

    public void mystery2(int i){
        if(i >= 4) return;
        else{
            mystery2(++i);
            System.out.println(i + " ");
            mystery2(i);
        }
    }

    public static void main(String[] args) {
        recursion thing = new recursion();
        thing.mystery2(0);
    }
}
