package primeSentinel30;

import java.text.NumberFormat;
import java.util.BitSet;

public class PrimesSentinel {

	private Offsets30 offset = new Offsets30();
	private int[] endNumber = {1,7,11,13,17,19,23,29};
	private final int[] wages = {1,2,4,8,16,32,64,128};
	private final int[] column_nr = {9,0,9,9,9,9,9,1,9,9,9,2,9,3,9,9,9,4,9,5,9,9,9,6,9,9,9,9,9,7,9};
	private byte[] table;
	private long sieve_time;
	private long max_value;
	private long count_time=Long.MAX_VALUE;

	public PrimesSentinel(long maxValue) {

		max_value = maxValue;
		table = new byte[(int) (maxValue/30+1)];
		table[0] = 0b00000001;   /*	mark that number, one isn't prime	*/

		long start = System.currentTimeMillis();
		sieveUntil((int) Math.sqrt(maxValue));
		sieve_time = System.currentTimeMillis() - start; // save time of sieving
	}

	public void printSieveInfo() {
		String value = NumberFormat.getInstance().format( max_value );
		String length = NumberFormat.getInstance().format( table.length );

		System.out.println("     value: " + value);
		System.out.println("table size:  " + length);
		System.out.println("size in MB: " + table.length/1024/1024);
	}

	/**
	 * 
	 * @param
	 * 			number to check
	 * 
	 * @return
	 * 			0 - mean that this is prime
	 * 			1 - mean that it isn't prime
	 */
	public int isPrime(long num) {	/*	max: this.max_value	*/
		if(num == 2) return 0;	/*	prime	*/
		if(num == 3) return 0;	/*	prime	*/
		if(num == 5) return 0;	/*	prime	*/
		int row = (int) (num/30);
		int col = column_nr[ (int) (num%30) ];
		if(col<=8) {
			return table[row]&wages[col];
		}
		return 1;				/*	not prime	*/
	}

	public int isPrimeLong(long num) {	/*	max Long.MAX_VALUE	*/

		if(num < this.max_value) return isPrime(num);

		int tableVal = column_nr[(int) (num%30)];
		if(tableVal==9) return 1;				/*	not prime	*/

		long sqrt = (long) Math.sqrt(num);

		int max_i = (int) (sqrt/30+1);
		for(int i=0; i<max_i; i++) {
			for(int j=0; j<8; j++) {
				if((table[i]&wages[j])==0) {	/*	if this is prime	*/
					long prime = (long) i * 30 + endNumber[j];
					if(num%prime==0) return 1;	/*	not prime	*/
				}
			}
		}
		return 0;				/*	prime	*/
	}

	private void sieveUntil(int numberSqrt) {

		int max_i = (int) numberSqrt/30+1;

		for(int i=0; i<max_i; i++) {
			
			for(int j=0; j<8; j++) {				/*	bits in sieve: 0 1 2 3 4 5 6 7	*/

				if( (table[i] & wages[j]) == 0) {	/*	if number is prime	*/
					
					int numberValue = i*30+endNumber[j];
//					if(numberValue <= numberSqrt) {
						markMultiplesOfANumber(numberValue, j);
//					}
				}
			}
		}
	}

	private int findMaxValueInIntArray(int[] array) {
		int max = 0;
		for(int v : array)
			if(v > max) max = v;
		return max;
	}

	private int[] calculateDiffs(int[] offsets, int maxValue) {
		
		int[] diffs = new int[8];
		diffs[0] = maxValue - offsets[0];
		diffs[1] = maxValue - offsets[1];
		diffs[2] = maxValue - offsets[2];
		diffs[3] = maxValue - offsets[3];
		diffs[4] = maxValue - offsets[4];
		diffs[5] = maxValue - offsets[5];
		diffs[6] = maxValue - offsets[6];
		diffs[7] = maxValue - offsets[7];
		
		return diffs;
	}

	private void markMultiplesOfANumber(int number, int row) {

		int[] offsets = offset.calculateOffsets(number, row);

		/*	optimization code	*/
		int maxOffset = findMaxValueInIntArray(offsets);
		int[] diffs = calculateDiffs(offsets, maxOffset);

		while(maxOffset<table.length) {

			table[ maxOffset - diffs[0] ] |= 0b00000001;
			table[ maxOffset - diffs[1] ] |= 0b00000010;
			table[ maxOffset - diffs[2] ] |= 0b00000100;
			table[ maxOffset - diffs[3] ] |= 0b00001000;
			table[ maxOffset - diffs[4] ] |= 0b00010000;
			table[ maxOffset - diffs[5] ] |= 0b00100000;
			table[ maxOffset - diffs[6] ] |= 0b01000000;
			table[ maxOffset - diffs[7] ] |= 0b10000000;
			
			maxOffset+=number;
		}

		/*	endind	*/
		for(int i=maxOffset - diffs[0]; i<table.length; i+=number) {
			table[i] |= 0b00000001;
		}

		for(int i=maxOffset - diffs[1]; i<table.length; i+=number) {
			table[i] |= 0b00000010;
		}

		for(int i=maxOffset - diffs[2]; i<table.length; i+=number) {
			table[i] |= 0b00000100;
		}

		for(int i=maxOffset - diffs[3]; i<table.length; i+=number) {
			table[i] |= 0b00001000;
		}

		for(int i=maxOffset - diffs[4]; i<table.length; i+=number) {
			table[i] |= 0b00010000;
		}

		for(int i=maxOffset - diffs[5]; i<table.length; i+=number) {
			table[i] |= 0b00100000;
		}

		for(int i=maxOffset - diffs[6]; i<table.length; i+=number) {
			table[i] |= 0b01000000;
		}

		for(int i=maxOffset - diffs[7]; i<table.length; i+=number) {
			table[i] |= 0b10000000;
		}

	}

	public long count() {	/*	count primes in sieve	*/

		long possiblePrimes = 0;

		long start = System.currentTimeMillis();
		for(int i=0; i<table.length; i++) {
			possiblePrimes += (8 - Integer.bitCount( table[i] & 0xff ) );
		}
		count_time = System.currentTimeMillis() - start;
		return possiblePrimes + 3;	/*	add prime numbers 2, 3 and 5	*/
	}

	public double getSieveTime() { return (double) sieve_time/1000; }
	public double getCountTime() { return (double) count_time/1000; }
	public long   getMaxValue()  { return this.max_value; }
}




