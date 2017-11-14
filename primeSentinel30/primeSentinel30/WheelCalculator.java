package primeSentinel30;

import java.text.NumberFormat;
import java.util.Locale;

public class WheelCalculator {

	private long calculateModulo(int[] primes) {
		long modulo = 1;
		for(int p : primes)
			modulo *= p;
		return modulo;
	}

	private long calculateWheelSize(int[] primes, long modulo) {
		long wheelSize = modulo;
		for(int p : primes)
			wheelSize -= wheelSize/p;
		return wheelSize;
	}

	private String withCommas(long number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

	private void printWheel(long modulo, long wheelSize, int[] primes) {
		System.out.print("first " + primes.length + " prime(s): ");
		for(int p : primes) System.out.print(p + " ");
		System.out.println("    modulo: " + withCommas(modulo) + "  ");
		System.out.print("wheel size: " + withCommas( wheelSize ) + " ");
		System.out.println("dropped: " + withCommas( modulo - wheelSize ));
		float f = (float) 100/modulo;
		System.out.print("checking: " + f * wheelSize + "% ");
		System.out.println("dropped: " + f * (modulo - wheelSize) + "%\n");
	}

	public void getWheel(int[] primes) {
		long modulo = calculateModulo(primes);
		long wheelSize = calculateWheelSize(primes, modulo);
		printWheel(modulo, wheelSize, primes);
	}

	public static void main(String[] args) {
		
		WheelCalculator app = new WheelCalculator();
		app.getWheel(new int[] {2});
		app.getWheel(new int[] {2,3});
		app.getWheel(new int[] {2,3,5});
		app.getWheel(new int[] {2,3,5,7});
		app.getWheel(new int[] {2,3,5,7,11});
		app.getWheel(new int[] {2,3,5,7,11,13});
		app.getWheel(new int[] {2,3,5,7,11,13,17});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29,31});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29,31,37});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43});
		app.getWheel(new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47});
	}
}
