/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 21:44:52 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.acmol.risk.AlternatePathfinder;
import com.acmol.risk.Territory;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class AlternatePathfinder_ESTest extends AlternatePathfinder_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory("$NOdJ{9O{Y_FVvW`v^H");
      Territory territory1 = new Territory(")']h'%75");
      territory0.neighbors.add(territory1);
      Territory territory2 = new Territory("$NOdJ{9O{Y_FVvW`v^H");
      alternatePathfinder0.availablePath(territory0, territory2);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory("Wq< ");
      territory0.neighbors = null;
      Territory territory1 = new Territory("/N#gh@4XJ9x4UQSw");
      // Undeclared exception!
      try { 
        alternatePathfinder0.availablePath(territory0, territory1);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.AlternatePathfinder", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory(".ExrP<X`?W~H|");
      Territory territory1 = new Territory(".ExrP<X`?W~H|");
      territory0.neighbors.add(territory0);
      territory1.neighbors = territory0.neighbors;
      alternatePathfinder0.availablePath(territory1, territory0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory(".ExrP<X`?W~H|");
      Territory territory1 = new Territory(".ExrP<X`?W~H|");
      territory0.neighbors.add(territory0);
      boolean boolean0 = alternatePathfinder0.availablePath(territory0, territory1);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory(".ExrP<X`?W~H|");
      boolean boolean0 = alternatePathfinder0.availablePath(territory0, (Territory) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      AlternatePathfinder alternatePathfinder0 = new AlternatePathfinder();
      Territory territory0 = new Territory(".ExrP<X`?W~H|");
      boolean boolean0 = alternatePathfinder0.availablePath((Territory) null, territory0);
      assertFalse(boolean0);
  }
}
