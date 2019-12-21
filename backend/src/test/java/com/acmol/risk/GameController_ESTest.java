/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 21 20:04:05 GMT 2019
 */

package com.acmol.risk;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.acmol.risk.Continent;
import com.acmol.risk.GameController;
import com.acmol.risk.Player;
import com.acmol.risk.Territory;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class GameController_ESTest extends GameController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("", "");
      gameController0.currentActionNumber = 412;
      gameController0.skipFortify("ok");
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.currentPlayerNumber = (-780);
      // Undeclared exception!
      try { 
        gameController0.finishTurn();
        fail("Expecting exception: ArithmeticException");
      
      } catch(ArithmeticException e) {
         //
         // / by zero
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("+^s60Z[mocyVQ<Di[vs", "+^s60Z[mocyVQ<Di[vs");
      gameController0.currentPlayerNumber = (-3285);
      // Undeclared exception!
      try { 
        gameController0.attack("ok", "error");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GameController gameController0 = new GameController();
      // Undeclared exception!
      try { 
        gameController0.verifyOperation("!E93(n&AP", "!E93(n&AP");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GameController gameController0 = new GameController();
      // Undeclared exception!
      try { 
        gameController0.skipFortify("fOn Xp");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      GameController gameController0 = new GameController();
      // Undeclared exception!
      try { 
        gameController0.skipAttack("Wrong format in army allocation. Should be countryA:4,countryB:2...");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      GameController gameController0 = new GameController();
      // Undeclared exception!
      try { 
        gameController0.putReinforce("Hz+~J", "Hz+~J");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.players.put("mapconfig.json", (Player) null);
      gameController0.addPlayer("JOKER", "ZS");
      // Undeclared exception!
      try { 
        gameController0.getPlayersData("JOKER");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      GameController gameController0 = new GameController();
      // Undeclared exception!
      try { 
        gameController0.fortify("No winner", "GbG];[8Gv%");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.players = null;
      // Undeclared exception!
      try { 
        gameController0.addPlayer(" operation should be done first!", "R&a^<9|5k-!l");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.acmol.risk.GameController", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      GameController gameController0 = new GameController();
      Player player0 = new Player("m?j !p", "l*Lk'{8Mb@&!|z|L");
      gameController0.isEliminated(player0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.getPlayerTerritories(".");
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.gameIsOver = true;
      gameController0.verifyOperation("IE >h&pvQ%;63fL", (String) null);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      GameController gameController0 = new GameController();
      Continent continent0 = new Continent("Q{H~XthhCj3^B$");
      gameController0.continents.add(continent0);
      gameController0.addPlayer("MOs`^4!^n71ana", "");
      // Undeclared exception!
      gameController0.finishTurn();
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      GameController gameController0 = new GameController();
      Continent continent0 = new Continent("No winner");
      gameController0.continents.add(continent0);
      gameController0.findTerritoryById((String) null);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("6:u}w7+2v", "ARTILLERY");
      assertEquals(0, gameController0.currentPlayerNumber);
      
      gameController0.fortify("1y[3+$", "");
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("");
      territory0.neighbors.add(territory0);
      boolean boolean0 = gameController0.moveUnit(".", territory0, territory0, 0);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameIsOver);
      assertFalse(boolean0);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("mapconfig.json");
      Player player0 = new Player("1<V%R'rg%Wh!", "1<V%R'rg%Wh!");
      territory0.owner = player0;
      boolean boolean0 = gameController0.moveUnit(">1Ee4= y4%<^'n<_:gJ", territory0, territory0, 0);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentActionNumber);
      assertFalse(boolean0);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("mapconfig.json");
      boolean boolean0 = gameController0.moveUnit("*/t$SuOaK\"E^", territory0, (Territory) null, 0);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertFalse(boolean0);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentReinforce);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("");
      boolean boolean0 = gameController0.moveUnit(".", territory0, territory0, 0);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertFalse(boolean0);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(0, gameController0.currentPlayerNumber);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      GameController gameController0 = new GameController();
      boolean boolean0 = gameController0.moveUnit("gLP}XZ", (Territory) null, (Territory) null, (-3744));
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentReinforce);
      assertFalse(boolean0);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.winner();
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("mapconfig.json");
      gameController0.attack(territory0, territory0, (-322));
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      GameController gameController0 = new GameController();
      Territory territory0 = new Territory("Hz+~J");
      gameController0.attack(territory0, (Territory) null, 3);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.attack((Territory) null, (Territory) null, (-3744));
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("", "][\"U1Q");
      gameController0.verifyOperation("start", "start");
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      GameController gameController0 = new GameController();
      assertFalse(gameController0.gameIsOver);
      
      gameController0.gameIsOver = true;
      gameController0.attack("", "Missing parameters for the reinforcement call");
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      GameController gameController0 = new GameController();
      assertFalse(gameController0.gameIsOver);
      
      gameController0.gameIsOver = true;
      gameController0.skipAttack("");
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      GameController gameController0 = new GameController();
      assertFalse(gameController0.gameIsOver);
      
      gameController0.gameIsOver = true;
      gameController0.putReinforce("", "$X$it");
      assertEquals(6, gameController0.DICE_MAX_VAL);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      GameController gameController0 = new GameController();
      int int0 = gameController0.reinforcementUnitAvailable((String) null);
      assertEquals(0, int0);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      GameController gameController0 = new GameController();
      int int0 = gameController0.reinforcementUnitAvailable("error");
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, int0);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("+^s60Z[mocyVQ<Di[vs", "+^s60Z[mocyVQ<Di[vs");
      Player player0 = gameController0.findPlayerByNumber(880);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertNull(player0);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(0, gameController0.currentActionNumber);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("", "");
      assertFalse(gameController0.gameStarted);
      
      gameController0.verifyOperation("", " j/Ev-_WgU");
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.launchGame("Dt");
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameStarted);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("", "ARTILLERY");
      gameController0.launchGame("");
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertFalse(gameController0.gameStarted);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(1, gameController0.DICE_MIN_VAL);
  }

  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("JOKER", "ZS");
      gameController0.getPlayersData("JOKER");
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertFalse(gameController0.gameStarted);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.getPlayersData("Missing parameters for the reinforcement call");
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertFalse(gameController0.gameStarted);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentActionNumber);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer("com.acmol.risk.StringMessage", (String) null);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameStarted);
      assertEquals(0, gameController0.currentPlayerNumber);
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.addPlayer((String) null, (String) null);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertFalse(gameController0.gameStarted);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test39()  throws Throwable  {
      GameController gameController0 = new GameController();
      assertFalse(gameController0.gameStarted);
      
      gameController0.gameStarted = true;
      gameController0.addPlayer("9}^d", "mapconfig.json");
      assertEquals(1, gameController0.DICE_MIN_VAL);
  }

  @Test(timeout = 4000)
  public void test40()  throws Throwable  {
      GameController gameController0 = new GameController();
      int int0 = gameController0.rollTheDice();
      assertFalse(gameController0.gameStarted);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(1, int0);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertFalse(gameController0.gameIsOver);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertEquals(0, gameController0.currentActionNumber);
      assertEquals(3, gameController0.MIN_PLAYERS);
  }

  @Test(timeout = 4000)
  public void test41()  throws Throwable  {
      GameController gameController0 = new GameController();
      gameController0.initGame();
      assertEquals(3, gameController0.MIN_PLAYERS);
      assertEquals(6, gameController0.DICE_MAX_VAL);
      assertEquals(0, gameController0.currentReinforce);
      assertEquals(1, gameController0.DICE_MIN_VAL);
      assertEquals(6, gameController0.MAX_PLAYERS);
      assertEquals(3, gameController0.MIN_REINFORCE_UNITS);
      assertEquals(0, gameController0.currentPlayerNumber);
      assertEquals(3, gameController0.MAX_UNIT_PER_ATTACK);
      assertFalse(gameController0.gameStarted);
      assertFalse(gameController0.gameIsOver);
      assertEquals(0, gameController0.currentActionNumber);
  }
}
