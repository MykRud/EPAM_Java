package com.company.Task_5;

public class Main {
    public static void main(String[] args) {
        // Rectangle
        Rectangle rectangle = new Rectangle();          // Def constructor
        System.out.println("Default constructor: ");
        System.out.println(rectangle);
        System.out.println("Side A: " + rectangle.getSideA());       // getSideA()
        System.out.println("Side B: " + rectangle.getSideB());       // getSideB()
        rectangle = new Rectangle(7);             // Constructor with 1 param
        System.out.println("Constructor with 1 parameter: ");
        System.out.println(rectangle);
        rectangle = new Rectangle(7, 6);    // Constructor with 2 params
        System.out.println("Constructor with 2 parameter: ");
        System.out.println(rectangle);
        // Area()
        System.out.println("Area of rectangle: " + rectangle.area());
        // Perimeter()
        System.out.println("Perimeter of rectangle: " + rectangle.perimeter());
        // replaceSides()
        rectangle.replaceSides();
        System.out.println("Replaced sides: " + rectangle);
        rectangle = new Rectangle(7, 7);
        //isSquare()
        System.out.println("Is rectangle a square?: " + rectangle.isSquare());


        // ArrayRectangles
        ArrayRectangles arrayRectangles = new ArrayRectangles(5);           // First Constructor
        System.out.println(arrayRectangles);                                   // toString()
        Rectangle[] array = new Rectangle[]{new Rectangle(4, 4),    // Second Constructor
                                            new Rectangle(10, 4),
                                            new Rectangle(15, 6),
                                            new Rectangle(1, 3),
                                            new Rectangle(10, 10)};
        arrayRectangles = new ArrayRectangles(array);
        System.out.println(arrayRectangles);               // toString()
        // addRectangle()
        System.out.println("Adding rectangle: " + arrayRectangles.addRectangle(new Rectangle(10, 3)));
        System.out.println(arrayRectangles);               // toString()
        // numberMaxArea()
        System.out.println("Index of Rectangle with max area: " + arrayRectangles.numberMaxArea());
        // numberMinPerimeter()
        System.out.println("Index of Rectangle with min perimeter: " + arrayRectangles.numberMinPerimeter());
        // numberSquare()
        System.out.println("Number of squares: " + arrayRectangles.numberSquare());

    }
}
