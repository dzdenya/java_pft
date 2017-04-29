package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Equation;

/**
 * Created by Denys on 4/29/2017.
 */
public class EquationTests {

  @Test
  public void test0() {
    Equation equation = new Equation(1,1,1);
    Assert.assertEquals(equation.rootNumber(),0);
  }

  @Test
  public void test1() {
    Equation equation = new Equation(1,2,1);
    Assert.assertEquals(equation.rootNumber(),1);
  }

  @Test
  public void test2() {
    Equation equation = new Equation(1,5,6);
    Assert.assertEquals(equation.rootNumber(),2);
  }


}
