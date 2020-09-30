public class Pug {
    private String name;
    private final static int numLegs = 4;
    private double age;
    private double weight;
    private int numWrinkles;

    public Pug(){
        name = "dog";
        weight = 3; //Units = grams
        numWrinkles = 1000;
    }
    //Constructors
    public Pug(String name){
        this();
        this.name = name;
    }
    public Pug(double weight){
        this();
        this.weight = weight;
    }
    public Pug(String name, double weight, int numWrinkles, double age){
        this.name = name;
        this.weight = weight;
        this.numWrinkles = numWrinkles;
        this.age = age;
    }
    //Accessors
    public String getName(){
        return name + "is a cutie.";
    }

    public double getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public static int getNumLegs() {
        return numLegs;
    }

    public int getNumWrinkles() {
        return numWrinkles;
    }
    //Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(double age) {
        if(age < 0 || age > 30){
            System.out.println("Invalid age ye moron!!");
        }
        else{this.age = age;}
    }

    public void setWeight(double weight) {
        if(weight < 75){
            System.out.println("Invalid weight ye moron!!");
        }
        else{this.weight = weight;}
    }

    public void setNumWrinkles(int numWrinkles) {
        if(numWrinkles < 1000){
            System.out.println("Invalid wrinkles ye moron!!");
        }
        else{this.numWrinkles = numWrinkles;}
    }

    @Override
    public String toString() {
        return "Pug{" +
                "name='" + name  +
                ", age=" + age +
                ", weight=" + weight +
                ", numWrinkles=" + numWrinkles +
                '}';
    }
}
