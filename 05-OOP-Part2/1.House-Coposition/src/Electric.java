public class Electric {
    private int numberOfFittings;
    private String lightBrand;
    private String fanBrand;

    public Electric(int numberOfFittings, String lightBrand, String fanBrand) {
        this.numberOfFittings = numberOfFittings;
        this.lightBrand = lightBrand;
        this.fanBrand = fanBrand;
        System.out.println("In constructor Electric()");
    }
}
