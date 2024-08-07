package creational.prototype;

class Address2
{
    public String streetAddress, city, country;

    public Address2(String streetAddress, String city, String country)
    {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address2(Address2 other)
    {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee
{
    public String name;
    public Address2 address;

    public Employee(String name, Address2 address)
    {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other)
    {
        name = other.name;
        address = new Address2(other.address);
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class PrototypeCopyConstructors {
    public static void main(String[] args)
    {
        Employee john = new Employee("John",
                new Address2("123 London Road", "London", "UK"));

        //Employee chris = john;
        Employee chris = new Employee(john);

        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}

class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this(other.x, other.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

// Exercício
class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy()
    {
        return new Line(new Point(this.start), new Point(this.end));
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

class demo {
    public static void main(String[] args) {
        Line line1 = new Line(new Point(1,2), new Point(3,4));
        Line line2 = line1.deepCopy();
        System.out.println(line1);
        System.out.println(line2);
    }
}