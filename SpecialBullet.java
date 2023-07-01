public class SpecialBullet implements ISpaceObject{

    private int SpecialBulletX;
    private int SpecialBulletY;

    private int Extent;
    
    private int R;
    private int G;
    private int B;

    boolean move = true;

    int time=0;
    boolean timeBoolean=false;

    public SpecialBullet(int specialBulletX, int specialBulletY, int extent, int r, int g, int b) {
        SpecialBulletX = specialBulletX;
        SpecialBulletY = specialBulletY;
        Extent = extent;
        R = r;
        G = g;
        B = b;
    }

    
    public int getSpecialBulletX() {
        return SpecialBulletX;
    }
    
    public void setSpecialBulletX(int specialBulletX) {
        SpecialBulletX = specialBulletX;
    }
    
    public int getSpecialBulletY() {
        return SpecialBulletY;
    }
    
    public void setSpecialBulletY(int specialBulletY) {
        SpecialBulletY = specialBulletY;
    }
    
    public int getExtent() {
        return Extent;
    }
    
    public void setExtent(int extent) {
        Extent = extent;
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
    
    public void setRGB(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }
    
    @Override
    public void showObject() {

        Main.processing.fill(R, G, B);
        Main.processing.circle(SpecialBulletX, SpecialBulletY, Extent);
        
    }

    public void specialPower(Block block){
        if (Extent<=80) {
            Extent += 5;
        }
        if (block.getBlockX()+Block.getBlockWidth()/2>SpecialBulletX) {
            block.setBlockX(block.getBlockX()-2);
        } else {
            block.setBlockX(block.getBlockX()+2);
        }
        if (block.getBlockY()+Block.getBlockHeight()/2>SpecialBulletY) {
            block.setBlockY(block.getBlockY()-2);
        } else {
            block.setBlockY(block.getBlockY()+2);
        }
        block.SetHP(block.GetHP()-5);
        Main.GameScore+=10;
        // time++;
    }

}
