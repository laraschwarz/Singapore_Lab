import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Car {

    private Image carLeft;
    private Image carRight;
    private double x;
    private double y;
    private boolean left;

    public Car(double x, double y) {
        // this.x = x - 23;
        // this.y = y - 20;
        this.x = x;
        this.y = y;
        try {
            carLeft = ImageIO.read(new File("carLeft.png"));
            carRight = ImageIO.read(new File("carRight.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void set(double x, double y) {
        // this.x = x - 23;
        // this.y = y - 20;
        this.x = x;
        this.y = y;
    }

    public void drawMe(Graphics g) {
        int x1 = (int)Math.round(x);
        int y1 = (int)Math.round(y);
        if(left){
            g.drawImage(carLeft, x1-23, y1-20, 46, 40, null);
        }
        else{
            g.drawImage(carRight, x1-23, y1-20, 46, 40, null);
        }
    }

    public void moveTowards(int endX, int endY, double xIncr, double yIncr) {
        x += xIncr;
        y += yIncr;
        if(xIncr < 0){
            left = true;
        }
        else{
            left = false;
        }
    }
}
