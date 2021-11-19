package com.company.Task_5;

public class Rectangle {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB){
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public Rectangle(double sideA){
        this(sideA, 5);
    }

    public Rectangle(){
        this(4, 3);
    }

    public double getSideA(){
        return this.sideA;
    }

    public double getSideB(){
        return this.sideB;
    }

    public double area(){
        return sideA * sideB;
    }

    public double perimeter(){
        return 2*(sideA+sideB);
    }

    public boolean isSquare(){
        return sideA == sideB;
    }

    public void replaceSides(){
        double temp = sideB;
        sideB = sideA;
        sideA = temp;
    }
    public String toString(){
        return "{" + sideA + ", " + sideB + "}";
    }
}
