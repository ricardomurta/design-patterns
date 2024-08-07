package _solid;

// You should be able to substitute a subclass for a base class

class Rectangle
{
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return width*height; }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public boolean isSquare() { // Can use this approach instead of creating a class Square
        return width == height;
    }
}

class Square extends Rectangle
{
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) { // Violates the Liskov Substitution Principle
        super.setWidth(width);
        super.setHeight(width);
        System.out.println("Aqui width " + super.getWidth() + " " + super.getHeight());
    }

    @Override
    public void setHeight(int height) { // Violates the Liskov Substitution Principle
        super.setHeight(height);
        super.setWidth(height);
        System.out.println("Aqui height " + super.getWidth() + " " + super.getHeight());
    }
}

class RectangleFactory
{
    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }

    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }
}

public class _3_LiskovSubstitutionPrinciple {
    // maybe conform to ++
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width * 10
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        Rectangle sq = new Square();
        sq.setHeight(5);
        sq.setWidth(9);
        useIt(sq);
    }
}
