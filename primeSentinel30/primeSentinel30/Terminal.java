package primeSentinel30;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

enum Mode {
	DEFAULT,
	CHECK,
	RANGE,
}

public class Terminal {

	private static final long INIT_VALUE = 3_100_000_000L;
	private PrimesSentinel sentinel;

	public void run() {
		String prompt = "> ";
		Scanner scan = new Scanner(System.in);

		System.out.println("please wait, initializing...");
		System.out.println();
		sentinel = new PrimesSentinel(INIT_VALUE);
		Mode mode = Mode.DEFAULT;

		System.out.println("### Prime Sentinel modulo 30 ###");
		System.out.println();
		System.out.println("Primality test in range from 1 to " + format(Long.MAX_VALUE));
		System.out.println("Count primes in range from 1 to " + format(sentinel.getMaxValue()));
		printHelp();
		while(true) {
			System.out.print( prompt );
			String command = scan.nextLine();

			if(command.startsWith("q") || command.startsWith("e")) break;
			else if(command.startsWith("h")) printHelp();
			else if(command.startsWith("i")) { printInfo(); }
			else if(command.startsWith("t")) { isPrimeLongTest(); }
			else if(command.equals("d")) { mode = Mode.DEFAULT; prompt = "> "; }
			else if(command.equals("c")) { mode = Mode.CHECK; prompt = "check > "; }
			else if(command.equals("cols")) {} //sentinel.printCols(30, 500);
			else if(command.equals("r")) { mode = Mode.RANGE; prompt = "range > "; }// count primes in given range

			else if(command.startsWith("c ")) {
				if( command.split(" ").length > 1)
					this.isPrimeView( command.split(" ")[1]);
			}
			else if(command.startsWith("r ")) {
				String[] args = command.split(" ");
				if(args.length == 3)
					this.countPrimesBySieveView(sentinel, args[1], args[2]);
			}

			else if(mode == Mode.CHECK) { this.isPrimeView(command); }	/*	check if value is prime number	*/
			else if(mode == Mode.RANGE) {	/*	count primes in given range	*/
				String[] args = command.split(" ");
				if(args.length == 2)
					this.countPrimesBySieveView(sentinel, args[0], args[1]);
			}
		}

		System.out.println("quitting...");
		scan.close();
	}

	private void printHelp() {
		String[] help = {
				"",
				"# quit              quit application",
				"# help              this help",
				"# c <value>         check if value is a prime",
				"# c                 go to check mode",
				"# r <start> <end>   count primes in given range",
				"# r                 go to range mode",
				"# b                 back to default mode",
				"# test              benchmark function isPrimeLong",
//				"# info              get sieve info",
//				"# cols              print columns",
				""
				};

		for(String line : help)
			System.out.println(line);
	}

	private void countPrimesBySieveView(PrimesSentinel sentinel, String startNum, String endNum) {
		long start = this.getLongValue(startNum);
		long end = this.getLongValue(endNum);
		long count = 0;
		if(start > 0 && end > 0) {
			count = sentinel.countRange(start, end);
			if(count >= 0)
				System.out.println(count + " primes in range: " + format(start) + ":" + format(end));
		}
		else
			System.out.println("enter value beetwen 1 and " + format(sentinel.getMaxValue()) );
	}

	private void isPrimeView(String value) {
		long num = getLongValue(value);
		long maxValue = sentinel.getMaxValue() * sentinel.getMaxValue();
		if(maxValue<0) maxValue = Long.MAX_VALUE;
		if(num > 0 && num < maxValue)
			System.out.println( num + ((sentinel.isPrimeLong(num)==0) ? " prime" : " not prime") );
		else
			System.out.println("enter value beetwen 1 and " + format(maxValue) );
	}

	private long getLongValue(String value) {
		value = value.replace("_", "");
		value = value.replace(" ", "");
		value = value.replace(",", "");
		value = value.replace(".", "");
		try {
			long num = Long.parseLong(value);
			return num;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
		}
		return -1;
	}

	private void isPrimeLongTest() {

		int count = 0;
		long summaryTime = System.currentTimeMillis();
		long totalPrimesTime = 0;
		long totalRejectTime = 0;
		long secondsCounter = System.currentTimeMillis();
		for(long i=Long.MAX_VALUE; i>Long.MAX_VALUE-1000; i-=2) {
			long start = System.currentTimeMillis();
			int res = sentinel.isPrimeLong(i);
			long diff = System.currentTimeMillis() - start;
			if(res==0) { 
				count++;
				totalPrimesTime += diff;
			}
			else
				totalRejectTime += diff;
			if( (System.currentTimeMillis()-secondsCounter) >= 5000) {
				System.out.println("primes: " + count + "   primes time: " + ((double) totalPrimesTime/1000) + "s   reject time: " + ((double) totalRejectTime/1000) + "s");
				secondsCounter = System.currentTimeMillis();
			}
		}
		System.out.println(count + " primes in range from " + format(Long.MAX_VALUE-1000) + " to " + format(Long.MAX_VALUE));
		System.out.println("primes time: " + ((double) totalPrimesTime/1000) + "s   reject time: " + ((double) totalRejectTime/1000) + "s");
		System.out.println("summary time: " + ((double) (System.currentTimeMillis() - summaryTime)/1000));
	}

	private void printInfo() {
		sentinel.printSieveInfo();
		System.out.println("sieve making time: " + sentinel.getSieveTime());
		System.out.println("checking primes in range: from 1 to " + sentinel.getMaxValue());
	}

	private String format(long value) {
		char sep = new DecimalFormat().getDecimalFormatSymbols().getGroupingSeparator();
		return NumberFormat.getInstance().format( value ).replace(sep, '_');
	}

	public static void main(String[] args) {
		Terminal terminal = new Terminal();
		terminal.run();
	}
}
