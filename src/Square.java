/**
 * Square
 * Its a Rectangle2 but less abstract--it has four sides that are all the same (quite spooky, really)
 * Authors: JH
 * Date: 2-5-20
 * On My Honor
 */
public class Square extends Rectangle2 {


    @Override
    public int getPerimeter() {
        return this.sideLengthOne * 4;
    }
}
