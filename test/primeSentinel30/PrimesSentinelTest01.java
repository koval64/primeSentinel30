package primeSentinel30;

import org.junit.jupiter.api.Test;

class PrimesSentinelTest01 {

	@Test
	void test() {
		long max_value = 3_100_000_000L;
		PrimesSentinel sentinel = new PrimesSentinel( max_value );

		System.out.println( "testing number 1     " + ((sentinel.isPrime(1)==1) ? "pass" : "fail") );

		int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
		System.out.print("testing first " + primes.length + " primes     ");
		boolean status = true;
		for(int prime : primes) {
			if( sentinel.isPrime(prime) == 1 )		/*	0 prime - 1 not prime	*/
				status = false;
		}
		System.out.println( (status==true) ? "pass" : "fail" );

		for(int prime : primes)
			this.testMultiplesOfNumber(sentinel, max_value, prime);

		this.testIsPrimeLong(sentinel);
	}

	private void testMultiplesOfNumber(PrimesSentinel sentinel, long maxValue, int num) {
		System.out.print("testing complex numbers divisible by " + num + "     ");
		boolean status = true;
		long start = System.currentTimeMillis();
		for(long i=2*num; i<maxValue; i+=num) {
			if( sentinel.isPrimeLong(i)==0)		/* 0 means that this is prime	*/
				status = false;
		}
		long diff = System.currentTimeMillis() - start;
		System.out.println( ((status==true) ? "pass" : "fail") + "     time: " + ((double) diff/1000) + " ms");
	}

	private void testIsPrimeLong(PrimesSentinel sentinel) {
		/*	integer max value: 2147483647	 */
		//                      101233350
		long[] primes = {2298974999L,
				         4011949691L};
		for(long prime : primes)
			testLongPrimes(sentinel, prime);

		long[] complex = {9_223_372_036_854_775_309L};
		for(long val : complex)
			testLongComplex(sentinel, val);
	}

	private void testLongComplex(PrimesSentinel sentinel, long value) {
		long start = System.currentTimeMillis();
		int res = sentinel.isPrimeLong( value );
		long diff = System.currentTimeMillis() - start;
		System.out.println( "testing complex " + value + "     " + ((res==1) ? "pass" : "fail") + "     time: " + ((double) diff/1000) + " ms"  );
	}

	private void testLongPrimes(PrimesSentinel sentinel, long value) {
		long start = System.currentTimeMillis();
		int res = sentinel.isPrimeLong( value );
		long diff = System.currentTimeMillis() - start;
		System.out.println( "testing prime " + value + "     " + ((res==0) ? "pass" : "fail") + "     time: " + ((double) diff/1000) + " ms"  );
	}
}
