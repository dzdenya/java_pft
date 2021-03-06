package ru.stqa.pft.sandbox;

/**
 * Created by Denys on 4/30/2017.
 */
public class Primes {
  public static boolean inPrime(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean inPrimeFast(int n) {
    int m =(int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean inPrimeWhile(int n) {
    int i = 2;
    while (i<n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  public static boolean inPrime(long n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
