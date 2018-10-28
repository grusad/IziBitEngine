package engine;

public class Vector2 {

    public int x;
    public int y;

    public Vector2() {};

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        Vector2 vector = new Vector2();
        vector.x = this.x + other.x;
        vector.y = this.y + other.y;
        return vector;
    }

    public Vector2 sub(Vector2 other){
        Vector2 vector = new Vector2();
        vector.x = this.x - other.x;
        vector.y = this.y - other.y;
        return vector;
    }

    public Vector2 multiply(Vector2 other){
        Vector2 vector = new Vector2();
        vector.x = this.x * other.x;
        vector.y = this.y * other.y;
        return vector;
    }

    // TODO: Should this exist?
    public Vector2 divide(Vector2 other){
        return null;
    }

}
