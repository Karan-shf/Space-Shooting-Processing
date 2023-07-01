public class Bullet implements ISpaceObject {

    private static int BulletWidth = 2;
    private static int BulletHeight = 6;

    private static int R = 0;
    private static int G = 0;
    private static int B = 255;

    private static int Damage = 200;

    private int BulletX;
    private int BulletY = 640;


    public Bullet(int bulletX) {
        BulletX = bulletX;
    }

    @Override
    public void showObject() {
        
        Main.processing.fill(R, G, B);
        Main.processing.rect(BulletX, BulletY, BulletWidth, BulletHeight);
    }

    public static int getBulletWidth() {
        return BulletWidth;
    }

    public static int getBulletHeight() {
        return BulletHeight;
    }

    public static int getR() {
        return R;
    }

    public static int getG() {
        return G;
    }

    public static int getB() {
        return B;
    }

    public static void setRGB(int r, int b, int g) {
        R=r;
        G=g;
        B=b;
    }

    public static int getDamage() {
        return Damage;
    }

    public static void setDamage(int damage) {
        Damage = damage;
    }

    public int getBulletX() {
        return BulletX;
    }

    public int getBulletY() {
        return BulletY;
    }

    public void setBulletY(int bulletY) {
        BulletY = bulletY;
    }

}