package primeSentinel30;

import java.text.NumberFormat;

/** 
 * Intel(R) Core(TM) i5-7600K CPU @ 3.80GHz
 * 
 *   64_410_000_030    128.697 s	2,701,477,379 primes	- very close to max working value
 *   50_000_000_010     97.805 s    2,119,654,578 primes
 *   10_000_000_020     17.035 s      455,052,512 primes
 * 
 * Intel(R) Core(TM) i5-7600K CPU @ 3.80GHz with OC 4.3GHz
 * 
 *	    100_000_020		 0.044 s
 *	  1_000_000_020		 1.007 s	 50,847,536 primes
 *	 10_000_000_020		12.383 s	455,052,512 primes
 *
 *	     50_000_010		 0.024 s
 *	    500_000_010		 0.432 s
 *	  4_780_000_020		 5.629 s	about 225 * 10^6 primes
 *	  5_000_000_010		 5.909 s	about 234 * 10^6  primes
 *	 50_000_000_010		60.537 s	2,119,654,578 primes
 *	 64_400_000_000		89.428 s	2,701,075,683 primes
 *					   104.667 s	worst time
 */

public class MainApp {

	//private static final long MAX_VALUE = 2_147_483_647; // max value for signed int
	//private static final long MAX_VALUE = 6_469_693_230L;	/* 2,3,5,7,11,13,17,19,23,29   wheel */
	//private static final long MAX_VALUE = 9_699_690;	/*	2,3,5,7,11,13,17,19   wheel	*/
	//private static final long MAX_VALUE = 64_400_000_000L;
	//private static final long MAX_VALUE = 64_410_000_030L;
	private static final long MAX_VALUE = 10_000_000_000L;

	private static void printInfo(PrimesSentinel sentinel){
		String range = NumberFormat.getInstance().format( (MAX_VALUE/30+1) * 30 );
		String primes = NumberFormat.getInstance().format( sentinel.count() );
		System.out.format("primes in %s: %s %n",range,primes);
		System.out.print("sieve time: " + sentinel.getSieveTime() + "s \n");
	}

	public static void main(String[] args){

		PrimesSentinel sentinel = new PrimesSentinel(MAX_VALUE);
		printInfo(sentinel);
		//System.out.println(Long.MAX_VALUE);
		//sentinel.printPossiblePrimes();
		//sentinel.printCols(30, 500);
	}
}
