public class Dimensions {
    private int l;
    private int b;
    private int h;

    public Dimensions(int l, int b, int h) {
        this.l = l;
        this.b = b;
        this.h = h;
        System.out.println("In constructor Dimensions();");
    }

    public int getL() {
        return l;
    }

    public int getB() {
        return b;
    }

    public int getH() {
        return h;
    }

    public void setL(int l) {
        this.l = l;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setH(int h) {
        this.h = h;
    }
}
