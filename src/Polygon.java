/**
 * Polygon
 * This class makes some shapes that have some sides and a total perimeter and a way to compare shapes to see if they're the same number of sides
 * Authors: JH
 * Date: 2-5-20
 * On My Honor
 */

import java.util.Random;
public class Polygon {
    public int numSides;
    public int sideLengthOne;
    public int sideLengthTwo;
    public int sideLengthThree;
    public Random r = new Random();
    // default constructor
    // makes a polygon with 3 sides
    // sets the side lengths to random numbers between 1 and 10
    public Polygon(){
        numSides = 3;
        sideLengthOne = r.nextInt(10)+1;
        sideLengthTwo = r.nextInt(10)+1;
        sideLengthThree = r.nextInt(10)+1;
    }

    // returns the number of sides of the polygon
    public int getSides(){
        return numSides;
    }

    // returns the perimeter of the polygon
    public int getPerimeter(){
        return sideLengthOne + sideLengthTwo + sideLengthThree;
    }

    // compares two polygons to see if they have the same number of sides
    public boolean equals(Object o){
        if(o instanceof Polygon){
            return ((Polygon)o).getSides() == this.getSides();
        }
        else{
            return super.equals(o);
        }
    }

    @Override
    //Delicious formatting
    public String toString() {
        return this.getClass() +
                "{numSides=" + numSides +
                ", sideLengthOne=" + sideLengthOne +
                ", sideLengthTwo=" + sideLengthTwo +
                ", sideLengthThree=" + sideLengthThree +
                ", r=" + r +
                '}';
    }
}
