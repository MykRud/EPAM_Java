package task_basic.Task_5;

public class ArrayRectangles {
    private final Rectangle[] rectangle_array;
    private int size;

    public ArrayRectangles(int n){
        rectangle_array = new Rectangle[n];
        for(int i = 0; i < n; i++){
            rectangle_array[i] = new Rectangle();
            size++;
        }
    }

    public ArrayRectangles(Rectangle[] arrayRectangle){
        rectangle_array = arrayRectangle;
    }

    public boolean addRectangle(Rectangle rectangle){
        for(int i = 0; i < rectangle_array.length; i++){
            if(rectangle_array[i] == null) {
                rectangle_array[i] = rectangle;
                return true;
            }
        }
        return false;
    }

    public int numberMaxArea(){
        int index = 0;
            for (int j = 0; j < rectangle_array.length-1; j++) {
                if (rectangle_array[index].area() < rectangle_array[j + 1].area()) {
                    index = j + 1;
                }
        }
        return index;
    }

    public int numberMinPerimeter(){
        int index = 0;
            for (int j = 0; j < rectangle_array.length-1; j++) {
                if (rectangle_array[index].perimeter() > rectangle_array[j + 1].perimeter()) {
                    index = j + 1;
                }
        }
        return index;
    }

    public int numberSquare(){
        int number = 0;
        for (Rectangle rectangle : rectangle_array) {
            if (rectangle.isSquare())
                number++;
        }
        return number;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Rectangle rect : rectangle_array){
            str.append(rect.toString() + ", ");
        }
        return str.toString();
    }
}
