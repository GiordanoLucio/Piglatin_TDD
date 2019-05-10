package it.unisa.piglatin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PiglatinTest {

	@Test
	public void testPhrase() throws InvalidPhraseException {
		Piglatin piglatin = new Piglatin("a yellow bird");
		String phrase = piglatin.getPhrase();
		assertEquals("a yellow bird", phrase);
	}

	@Test
	public void testPhraseWithSingleWord() throws InvalidPhraseException {
		Piglatin piglatin = new Piglatin("yellow");
		String phrase = piglatin.getPhrase();
		assertEquals("yellow", phrase);
	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithDoubleSpace() throws InvalidPhraseException {
		new Piglatin("a  yellow bird");

	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithSpaceAtBeginning() throws InvalidPhraseException {
		new Piglatin(" a yellow bird");
	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithSpaceAtEnd() throws InvalidPhraseException {
		new Piglatin("a yellow bird ");
	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithComma() throws InvalidPhraseException {
		new Piglatin("a, yellow bird");
	}

	@Test(expected=InvalidPhraseException.class)
	public void testEmptyStringOnlySpace() throws Exception{
		Piglatin piglatin = new Piglatin(" ");
	}

	@Test
	public void testTranslateWordStartingWithA() throws Exception{
		Piglatin piglatin = new Piglatin("apple");
		String translation = piglatin.translate();
		assertEquals("appleay", translation);
	}

	@Test
	public void testTranslateWordStartingWithI() throws Exception{
		Piglatin piglatin = new Piglatin("illness");
		String translation = piglatin.translate();
		assertEquals("illnessay", translation);
	}

	@Test
	public void testTranslateWordStartingWithU() throws Exception{
		Piglatin piglatin = new Piglatin("umbrella");
		String translation = piglatin.translate();
		assertEquals("umbrellaay", translation);
	}

	@Test
	public void testTranslateWordStartingWithE() throws Exception{
		Piglatin piglatin = new Piglatin("obobob");
		String translation = piglatin.translate();
		assertEquals("obobobay", translation);
	}

	//we are considering minimum, just above it
	@Test
	public void testTranslateWordStartingWithB() throws Exception{
		Piglatin piglatin = new Piglatin("bird");
		String translation = piglatin.translate();
		assertEquals("irdbay", translation);
	}

	@Test
	public void testTranslateWordStartingWithC() throws Exception{
		Piglatin piglatin = new Piglatin("curl");
		String translation = piglatin.translate();
		assertEquals("urlcay", translation);
	}

	@Test
	public void testTranslateWordStartingWithG() throws Exception{
		Piglatin piglatin = new Piglatin("game");
		String translation = piglatin.translate();
		assertEquals("amegay", translation);
	}

	@Test
	public void testTranslateWordStartingWithY() throws Exception{
		Piglatin piglatin = new Piglatin("yellow");
		String translation = piglatin.translate();
		assertEquals("ellowyay", translation);
	}

	@Test
	public void testTranslateWordStartingWithZ() throws Exception{
		Piglatin piglatin = new Piglatin("zorro");
		String translation = piglatin.translate();
		assertEquals("orrozay", translation);
	}

	@Test
	public void testTranslatePhrase() throws Exception{
		Piglatin piglatin = new Piglatin("a yellow bird");
		String translation = piglatin.translate();
		assertEquals("aay ellowyay irdbay", translation);
	}


	//2 story
	@Test
	public void testTranslateWordWithXr() throws Exception{
		Piglatin piglatin = new Piglatin("xrayed");
		String translation = piglatin.translate();
		assertEquals("xrayeday", translation);
	}
	@Test
	public void testTranslateWordWithXr2() throws Exception{
		Piglatin piglatin = new Piglatin("xrumer");
		String translation = piglatin.translate();
		assertEquals("xrumeray", translation);
	}
	@Test
	public void testXrUpper() throws Exception{
		Piglatin piglatin = new Piglatin("XRUMER");
		String translation = piglatin.translate();
		assertEquals("XRUMERAY", translation);
	}
	@Test
	public void testChUpper() throws Exception{
		Piglatin piglatin = new Piglatin("CHAIR");
		String translation = piglatin.translate();
		assertEquals("AIRCHAY", translation);
	}

	@Test
	public void testTranslatedWordWithClustersAtBeginningSk() throws Exception{
		Piglatin piglatin = new Piglatin("skateboard");
		String translation = piglatin.translate();
		assertEquals("ateboardskay", translation);
	}

	@Test
	public void testTranslateWordWithClustersAtBeginningCh() throws Exception{
		Piglatin piglatin = new Piglatin("chair");
		String translation = piglatin.translate();
		assertEquals("airchay", translation);
	}

	@Test
	public void testTranslateWordWithClustersAtBeginningThr() throws Exception{
		Piglatin piglatin = new Piglatin("thread");
		String translation = piglatin.translate();
		assertEquals("eadthray", translation);
	}
	@Test
	public void testTranslateWordWithClustersAtBeginningBrr() throws Exception{
		Piglatin piglatin = new Piglatin("brreak");
		String translation = piglatin.translate();
		assertEquals("eakbrray", translation);
	}
	@Test
	public void testTranslateWordWithClustersAtBeginningSth() throws Exception{
		Piglatin piglatin = new Piglatin("sthephanie");
		String translation = piglatin.translate();
		assertEquals("ephaniesthay", translation);
	}

	//story 3
	@Test
	public void testTranslateUpperCase() throws Exception{
		Piglatin piglatin = new Piglatin("Test");
		String translation = piglatin.translate();
		assertEquals("Esttay", translation);
	}
	@Test
	public void testAllUpperCase() throws Exception{
		Piglatin piglatin = new Piglatin("TEST");
		String translation = piglatin.translate();
		assertEquals("ESTTAY", translation);
	}

	@Test
	public void testAllUpperCaseWithVowel() throws Exception{
		Piglatin piglatin = new Piglatin("APPLE");
		String translation = piglatin.translate();
		assertEquals("APPLEAY", translation);
	}

	@Test
	public void testPhraseIsNotOk() throws Exception{
		Piglatin piglatin = new Piglatin("OkOkOk");
		boolean translation = piglatin.phraseIsOk(piglatin.getPhrase());
		assertFalse(translation);
	}
	@Test
	public void testPhraseIsOk1() throws Exception{
		Piglatin piglatin = new Piglatin("APPLE");
		boolean translation = piglatin.phraseIsOk("aPple");
		assertFalse(translation);
	}

	@Test
	public void testPhraseIsNotOK() throws Exception{
		Piglatin piglatin = new Piglatin("OkkkkAk");
		boolean translation = piglatin.phraseIsOk(piglatin.getPhrase());
		assertFalse(translation);
	}
/**
	@Test
	public void TestOnlyFirstIsUpper() throws Exception{
		Piglatin piglatin = new Piglatin("Okkkkk");
		boolean translation = piglatin.onlyFirstIsUpperCase(piglatin.getPhrase());
		assertTrue(translation);
	}

	@Test
	public void TestNotOnlyFirstIsUpper() throws Exception{
		Piglatin piglatin = new Piglatin("OOk");
		boolean translation = piglatin.onlyFirstIsUpperCase(piglatin.getPhrase());
		assertFalse(translation);
	}
*/
	@Test(expected=InvalidPhraseException.class)
	public void testWrongWordCamelStartingVowel() throws Exception{
		Piglatin piglatin = new Piglatin("OkOk");
		String translation = piglatin.translate();
	}

	@Test(expected=InvalidPhraseException.class)
	public void testWrongPhraseCamel() throws Exception{
		Piglatin piglatin = new Piglatin("ThisIs AtEsT");
		String translation = piglatin.translate();
	}	

	@Test(expected=InvalidPhraseException.class)
	public void testWrongPhraseCapInTheMiddle() throws Exception{
		Piglatin piglatin = new Piglatin("biRd");
		String translation = piglatin.translate();
	}		

	@Test
	public void testXrayAllCapital() throws Exception{
		Piglatin piglatin = new Piglatin("XRAY");
		String translation = piglatin.translate();
		assertEquals("XRAYAY", translation);
	}		
	@Test
	public void testXrayOnlyFirstCapital() throws Exception{
		Piglatin piglatin = new Piglatin("Xray");
		String translation = piglatin.translate();
		assertEquals("Xrayay", translation);

	}		

	@Test
	public void testXrayOnlyFirstCapital2() throws Exception{
		Piglatin piglatin = new Piglatin("Bird");
		String translation = piglatin.translate();
		assertEquals("Irdbay", translation);
	}		

	@Test
	public void testPhraseWithSpaces() throws Exception{
		Piglatin piglatin = new Piglatin("I love Proxy");
		String translation = piglatin.translate();
		assertEquals("Iay ovelay Oxypray", translation);
	}

	@Test
	public void testProxy() throws Exception{
		Piglatin piglatin = new Piglatin ("Proxy yxxxorp");
		String translation = piglatin.translate();
		assertEquals("Oxypray orpyxxxay", translation);
	}
	

}
