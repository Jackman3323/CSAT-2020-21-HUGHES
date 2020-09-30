/**
 * Octagon
 * It's a polygon but slightly less abstract--it has eight sides (scary i know)
 * Authors: JH
 * Date: 2-5-20
 * On My Honor: JH
 */
public class Octagon extends Polygon{
    public Octagon(){
        super();
        numSides = 8;
    }

    @Override
    public int getSides() {
        return this.sideLengthOne * 8;
    }
}
