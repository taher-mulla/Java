public class speedCon{

	public static void main(String[] args){
		double kmphD = 10.5D;
		long mphL = toMilesPerHour(kmphD);
		System.out.println("The ethord toMilesPerHour sent the long val: "+mphL);
		printConversion(kmphD);
		
		System.out.println("\nThe ethord toMilesPerHour sent the long val: " + toMilesPerHour(1.5D));
		System.out.println("The ethord toMilesPerHour sent the long val: " + toMilesPerHour(10.25D));
		System.out.println("The ethord toMilesPerHour sent the long val: " + toMilesPerHour(-5.6D));
		System.out.println("The ethord toMilesPerHour sent the long val: " + toMilesPerHour(25.42D));
		System.out.println("The ethord toMilesPerHour sent the long val: " + toMilesPerHour(75.114D));
		
	}
	
	public static long toMilesPerHour(double kmphD){
		
		if(kmphD >= 0){
			long mphL;
			mphL = Math.round(kmphD/1.609);
			return mphL;
		}
			return -1;

	}
	
	public static void printConversion(double kmphD){
		if (kmphD<0)
			System.out.println("Invalid Value");
		else
			System.out.println(kmphD+" km/h = "+(kmphD/1.609)+" mi/hr");
	}
}
