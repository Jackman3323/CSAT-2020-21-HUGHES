/**
 * PolygonDriver
 * It makes some polygons of different types and compares them to see if they have the same num of sides by -REDACTED- them. (lol)
 * Authors: JH
 * Date: 2-5-20
 * On My Honor
 */
public class PolygonDriver {
    public static void main(String[] args) {
        //Declarations and initializations
        Polygon poly = new Polygon();
        Rectangle2 rect = new Rectangle2();
        Square square = new Square();
        Octagon octo = new Octagon();
        //Return statement
        System.out.println("Polygons!!");
        //Stats
        System.out.println(poly.toString());
        System.out.println(rect.toString());
        System.out.println(square.toString());
        System.out.println(octo.toString());
        //Results
        System.out.print("Rectangle equals square ");
        System.out.println(rect.equals(square));
        System.out.print("Octagon equals polygon ");
        System.out.println(octo.equals(poly));
    }
}
