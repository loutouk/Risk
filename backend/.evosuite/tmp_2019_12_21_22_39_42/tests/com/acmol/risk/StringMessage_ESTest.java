/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 21:48:05 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import com.acmol.risk.StringMessage;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class StringMessage_ESTest extends StringMessage_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      StringMessage stringMessage0 = new StringMessage("", "");
  }
}
