package engine;

public class Font {

    public static final Font DEFAULT = new Font("default_font.png", 26, 2, 8);

    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?'\"-+=/\\%()<>";


    private IBImage[] characters;
    private int spacing = 7;

    public Font(String path, int cols, int rows, int spacing){
        this.characters = IBImage.split(cols, rows, new IBImage(path));
        this.spacing = spacing;

    }

    public int getSpacing(){
        return spacing;
    }

    public void setSpacing(int spacing){
        this.spacing = spacing;
    }

    public IBImage getData(char c){
        int index = chars.indexOf(c);
        return ((index < 0) || (index > chars.length() - 2) ? null : characters[index]);
    }

}
