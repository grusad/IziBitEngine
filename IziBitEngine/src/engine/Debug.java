package engine;

public class Debug {

    private static Debug instance;

    public static Debug getInstance(){
        if(instance == null){
            instance = new Debug();
        }

        return instance;
    }
}
