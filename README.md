primeSentinel30
===============

Prime sieving algorithm in java, using modulo 30. Scans the range from `1` to `10^9`  in `1.333` second on `i5` processor. Single threaded.

2017.12.12 big update
---------------------

- many cleanups
- added terminal for easier usage
- added primes counting function ( only in sieve for now )
- fixed `isPrimeLong` function, now we can check if number is a prime from `1` to `Long.MAX_VALUE` ( probably `9.223.372.036.854.775.807` )
- added benchmark for `isPrimeLong` function

TODO
----
- sieve benchmarks directly from terminal



Sieve benchmarks
================

  Intel(R) Core(TM) i5-7600K CPU @ 3.80GHz
  ----------------------------------------
  
    64_410_000_030    128.697 s    2,701,477,379 primes	- almost max working value
    50_000_000_010     97.805 s    2,119,654,578 primes
    10_000_000_020     17.035 s      455,052,512 primes
     1_000_000_020      1.333 s       50,847,536 primes
  
  Intel(R) Core(TM) i5-7600K CPU @ 4.30GHz OC
  --------------------------------------------------------
  
    64_400_000_000     89.428 s    2,701,075,683 primes
    50_000_000_010     60.537 s    2,119,654,578 primes
    10_000_000_020     12.383 s    455,052,512 primes
     5_000_000_010      5.909 s    about 234 * 10^6  primes
     1_000_000_020      1.007 s     50,847,536 primes
       100_000_020      0.044 s      5,761,456 primes
