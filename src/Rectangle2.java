/**
 * Rectangle2
 * Its a polygon but less abstract--it has four sides (quite spooky, really)
 * Authors: JH
 * Date: 2-5-20
 * On My Honor
 */
public class Rectangle2 extends Polygon {


    @Override
    public int getSides() {
        return super.getSides() + 1;
    }
}
