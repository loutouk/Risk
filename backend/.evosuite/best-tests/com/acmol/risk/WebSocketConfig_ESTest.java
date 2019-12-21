/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 21:50:39 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.acmol.risk.WebSocketConfig;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class WebSocketConfig_ESTest extends WebSocketConfig_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      WebSocketConfig webSocketConfig0 = new WebSocketConfig();
      StompEndpointRegistry stompEndpointRegistry0 = mock(StompEndpointRegistry.class, new ViolatedAssumptionAnswer());
      doReturn((StompWebSocketEndpointRegistration) null).when(stompEndpointRegistry0).addEndpoint(any(java.lang.String[].class));
      // Undeclared exception!
      try { 
        webSocketConfig0.registerStompEndpoints(stompEndpointRegistry0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.WebSocketConfig", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      WebSocketConfig webSocketConfig0 = new WebSocketConfig();
      MessageBrokerRegistry messageBrokerRegistry0 = mock(MessageBrokerRegistry.class, new ViolatedAssumptionAnswer());
      doReturn((MessageBrokerRegistry) null).when(messageBrokerRegistry0).setApplicationDestinationPrefixes(any(java.lang.String[].class));
      // Undeclared exception!
      try { 
        webSocketConfig0.configureMessageBroker(messageBrokerRegistry0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.WebSocketConfig", e);
      }
  }
}
