package ShopApp;

public class IdCreator {
    private static IdCreator instance;
    public static IdCreator getInstance(){
        if (instance == null)
            instance = new IdCreator();
        return instance;
    }

    private IdCreator(){}

    private int nextId = 1;

    public int getNextId(){
        return nextId++;
    }
}
