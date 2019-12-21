/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 18:34:55 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.acmol.risk.Continent;
import com.acmol.risk.Territory;
import java.util.ArrayList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Continent_ESTest extends Continent_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Continent continent0 = new Continent("The name of the field/property to read should not be null");
      continent0.territories.add((Territory) null);
      // Undeclared exception!
      try { 
        continent0.findTerritoryById("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.Continent", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Continent continent0 = new Continent("");
      Territory territory0 = new Territory("");
      continent0.territories.add(territory0);
      Territory territory1 = continent0.findTerritoryById("");
      assertEquals(0, territory1.army);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Continent continent0 = new Continent("");
      Territory territory0 = new Territory("");
      ArrayList<Territory> arrayList0 = territory0.neighbors;
      arrayList0.add(territory0);
      continent0.territories = arrayList0;
      Territory territory1 = continent0.findTerritoryById((String) null);
      assertNull(territory1);
  }
}
