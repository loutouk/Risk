/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 21:48:26 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import com.acmol.risk.Territory;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Territory_ESTest extends Territory_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Territory territory0 = new Territory("");
      assertEquals(0, territory0.army);
  }
}
