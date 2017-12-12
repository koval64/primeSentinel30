package primeSentinel30;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class WheelCalculator {

	public long calculateModulo(int[] primes) {
		long modulo = 1;
		for(int p : primes)
			modulo *= p;
		return modulo;
	}

	public long calculateWheelSize(int[] primes, long modulo) {
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
		float fraction = (float) 100/modulo;
		System.out.print("checking: " + fraction * wheelSize + "% ");
		System.out.println("dropped: " + fraction * (modulo - wheelSize) + "%\n");
	}

	public void getWheel(int[] primes) {
		long modulo = calculateModulo(primes);
		long wheelSize = calculateWheelSize(primes, modulo);
		printWheel(modulo, wheelSize, primes);
	}

	private void printFormatted(String message, long value) {
		char sep = new DecimalFormat().getDecimalFormatSymbols().getGroupingSeparator();
		String val = NumberFormat.getInstance().format( value ).replace(sep, '_');
		System.out.println(message + val);
	}

	public static void main(String[] args) {

		WheelCalculator app = new WheelCalculator();

		app.printFormatted("Integer.MAX_VALUE: ", Integer.MAX_VALUE);
		app.printFormatted("   Long.MAX_VALUE: ", Long.MAX_VALUE);
		System.out.println();

		int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
		for(int i=1; i<=primes.length; i++) {
			app.getWheel( Arrays.copyOfRange(primes, 0, i) );
		}
	}
}
