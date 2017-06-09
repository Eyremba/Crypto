/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.lang.reflect.Method;
import java.util.Random;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Crypt unit test class extends Crypt.
 *
 * @author navaile
 */
public class CryptTest extends Crypt {
	
	private static final String passPhrase = "passPhrase";
	private static final String handle = "handle";
	private static final int roCount = 250;
	private static final int pbCount = 100;
	private static final String msg = "Some secret message.";
	
	private static String cryptText = "";
	
	private static final String msgID = Crypt.genMsgID();
	
	private static final Random RND = new Random();
	
	public CryptTest() {
		super(passPhrase, handle, msgID, roCount, pbCount, true);
	}
	
	@org.junit.Test
	public void cryptTest() {
		
		try {
			cryptText = Crypt.encryptText(passPhrase, handle, roCount,
					pbCount, null, msg);
			String text = Crypt.decryptText(passPhrase, handle, roCount,
					pbCount, null, cryptText);
			
			Assert.assertEquals(msg.toLowerCase(), text.toLowerCase());
			
		}
		catch(Exception ex) {	ex.printStackTrace();					}
	}
	
	@Test
	public void setCountTest() {
		
		System.out.println("setCountTest");

		int min = 0, max = 1000, num = 500;
		
		int val = this.setCount(RND, num, min, max);
		System.out.println("val: " + val);
		assertTrue(val >= min && val <= max && val < num);
		
		num = -5;
		val = this.setCount(RND, num, min, max);
		System.out.println("val: " + val);
		assertTrue(val == min);
		
		num = 1250;
		val = this.setCount(RND, num, min, max);
		System.out.println("val: " + val);
		assertTrue(val <= max);
	}
	
	@Test
	public void iniMatrixTest() {
		
		int[][] matrix = this.iniMatrix(RND, roCount, DIR_SIZE, true);
		assertTrue(matrix.length == roCount);
		assertTrue(matrix[0].length == DIR_SIZE);
	}
	
	@Test
	public void genMsgIDTest() {
		assertTrue(Crypt.genMsgID().length() == 9);
	}
	
	@Test
	public void roSpinTest() {
		int[] roSpin = this.roSpin(RND, roCount);
		for(int i: roSpin)
			if(Math.abs(i) != 1)	fail();
	}
	
	@Test
	public void stepRotorsTest() {
//		this.stepRotors(RND, , rotorIndex, roSpin, DIR_SIZE);
	}
	
	
	
	

	@Test
	public void padTextTest() {
		assertTrue(Crypt.padText("test", 13, "X").length() == 13);
		assertTrue(Crypt.padText("test", 13, "X").equalsIgnoreCase("XXXXXXXXXtest"));
		assertTrue(Crypt.padText("test", 2, "X").length() == 4);
	}

	@Test
	public void emptyTest() {
		
	}
}