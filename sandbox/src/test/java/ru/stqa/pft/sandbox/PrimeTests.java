package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Denys on 4/30/2017.
 */
public class PrimeTests {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.inPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeLong (){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.inPrime(n));
  }



  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.inPrime(Integer.MAX_VALUE -2));
  }


}
