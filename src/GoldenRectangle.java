/**
 * GoldenRectangle
 * This class can construct golden rectangles, either a default one or a custom one, it can also return a new golden rectangle
 * based on the current one, thus splitting the current one into a square and another golden rectangle. The process can go on
 * forever.
 * Authors: JH
 * Date: 1-22-19
 * On My Honor: JH
 */
public class GoldenRectangle extends Rectangle{

    //default constructor
    //sets the (x,y) location to (0,0), the height to 100,
    //and the width to an appropriate value so that the
    //rectangle is golden.

    public GoldenRectangle(){
        super();
    }
    //second constructor
//sets the location to (x,y)
//the orientation can be either 'W' for Wide or 'L' for Long
//set the width and height to the appropriate values so that the
//rectangle is Golden and in the right orientation
    public GoldenRectangle(int x, int y, int shortSide, char orientation){
        super(x,y,0,0);
        int mathBoi =(int) (shortSide * ((1 + Math.sqrt(5))/2));
        if(orientation == 'W'){this.setSize(shortSide,mathBoi);}
        else if(orientation == 'L'){this.setSize(mathBoi,shortSide);}
    }
    //returns a NEW GoldenRectangle (without changing the original one).
    //this new golden rectangle will be the rectangle that results from
    // partitioning the original golden rectangle into a square and a
    //rectangle. (See diagram below)
    public GoldenRectangle getNextGoldenRectangle(){
        GoldenRectangle newRect;
        int shortSide;
        if(this.getHeight() > this.getWidth()){
            shortSide = (int) (this.getWidth() / ((1 + Math.sqrt(5))/2));
            //Orientation is L for Long, height is larger than width
            newRect = new GoldenRectangle(this.getX(),this.getY(),shortSide,'W');
        }
        else{
            //Orientation is W for Wide, width is larger than height
            shortSide = (int) (this.getHeight() / ((1 + Math.sqrt(5))/2));
            newRect = new GoldenRectangle(this.getX(),this.getY(),shortSide,'L');

        }
        return newRect;
    }
}

