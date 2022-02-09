package task_basic.MVC;

public class Player {
    private String name;
    private int id = 0;
    private int[] shoots;

    public Player(String name){
        this.name = name;
    }

    public boolean checkForName(String name){
        return this.name.equals(name);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setShoots(int[] shoots) {
        this.shoots = shoots;
    }

    public int[] getShoots(){
        return shoots;
    }
}