public class Furniture {
    private boolean sofa;
    private boolean table;

    public Furniture(boolean sofa, boolean table) {
        this.sofa = sofa;
        this.table = table;
        System.out.println("In constructor Furniture()");
    }
    public void print(){
        System.out.println("In furniture.print()");
    }

    public boolean isSofa() {
        return sofa;
    }

    public void setSofa(boolean sofa) {
        this.sofa = sofa;
    }

    public boolean isTable() {
        return table;
    }

    public void setTable(boolean table) {
        this.table = table;
    }
}
