import java.util.ArrayList;

public class SpaceShip implements ISpaceObject{

    private final int SpaceShipY=400;
    private final int HeadY=605;
    
    private int SpaceShipX;
    
    private int R;
    private int G;
    private int B;

    int time=-1;
    boolean timeBoolean=false;

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<SpecialBullet> specialBullets = new ArrayList<>();

    
    public SpaceShip(int SpaceShipX, int r, int g, int b) {
        this.SpaceShipX = SpaceShipX;
        R = r;
        G = g;
        G = b;
    }
    
    public int getSpaceShipY() {
        return SpaceShipY;
    }

    public int getHeadY() {
        return HeadY;
    }
    
    public int getSpaceShipX() {
        return SpaceShipX;
    }

    public void setSpaceShipX(int SpaceShipX) {
        this.SpaceShipX = SpaceShipX;
    }

   

    public int getR() {
        return R;
    }

    
    public int getG() {
        return G;
    }
    
    public int getB() {
        return B;
    }
    
    public void setRGB(int r,int g,int b) {
        R = r;
        G = g;
        B = b;
    }

    @Override
    public void showObject() {

        Main.processing.fill(R,G,B);

        Main.processing.noStroke();
        //main body
        Main.processing.rect(SpaceShipX-10,576+70,20,4);
        Main.processing.rect(SpaceShipX-35, 580+70, 70, 15);
        Main.processing.rect(SpaceShipX-15, 595+70, 30, 5);
        //
        // rect(SpaceShipX-10,496,20,4);
        //left wing
        Main.processing.rect(SpaceShipX-35, 545+70, 10, 35);
        //right wing
        Main.processing.rect(SpaceShipX+25, 545+70, 10, 35);

    }
}
