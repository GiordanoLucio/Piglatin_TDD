package it.unisa.piglatin;

import java.util.HashSet;
import java.util.Set;

public class Piglatin {

	private String phrase;
	private static Set<Character> validCharacters;
	private static Set<Character> vowels;
	private static Set<String> clusters;
	static {
		vowels = new HashSet<>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');


		validCharacters = new HashSet<>();
		validCharacters.add(' ');
		validCharacters.add('a');
		validCharacters.add('b');
		validCharacters.add('c');
		validCharacters.add('d');
		validCharacters.add('e');
		validCharacters.add('f');
		validCharacters.add('g');
		validCharacters.add('h');
		validCharacters.add('i');
		validCharacters.add('j');
		validCharacters.add('k');
		validCharacters.add('l');
		validCharacters.add('m');
		validCharacters.add('n');
		validCharacters.add('o');
		validCharacters.add('p');
		validCharacters.add('q');
		validCharacters.add('r');
		validCharacters.add('s');
		validCharacters.add('t');
		validCharacters.add('u');
		validCharacters.add('v');
		validCharacters.add('w');
		validCharacters.add('x');
		validCharacters.add('y');
		validCharacters.add('z');
		validCharacters.add('A');
		validCharacters.add('B');
		validCharacters.add('C');
		validCharacters.add('D');
		validCharacters.add('E');
		validCharacters.add('F');
		validCharacters.add('G');
		validCharacters.add('H');
		validCharacters.add('I');
		validCharacters.add('J');
		validCharacters.add('K');
		validCharacters.add('L');
		validCharacters.add('M');
		validCharacters.add('N');
		validCharacters.add('O');
		validCharacters.add('P');
		validCharacters.add('Q');
		validCharacters.add('R');
		validCharacters.add('S');
		validCharacters.add('T');
		validCharacters.add('U');
		validCharacters.add('V');
		validCharacters.add('W');
		validCharacters.add('X');
		validCharacters.add('Y');
		validCharacters.add('Z');
	}

	public Piglatin(String phrase) throws InvalidPhraseException {
		if(phrase.contains("  ") || startsOrEndWithSpace(phrase) || containsInvalidCharacter(phrase)) {
			throw new InvalidPhraseException();
		}
		for(String word:phrase.split(" "))
			if(!word.matches("((^([a-z]*)$)|(^([A-Z]{1}([a-z]*)|([A-Z]*))$))?"))
				throw new InvalidPhraseException();

		this.phrase= phrase;
	}

	private boolean containsInvalidCharacter(String phrase) {
		for (int i = 0; i < phrase.length(); i++) {
			char character = phrase.charAt(i);
			if(!validCharacters.contains(character)) {
				return true;

			}
		}
		return false;
	}

	private boolean startsOrEndWithSpace(String phrase){
		return phrase.startsWith(" ") || phrase.endsWith(" ");
	}

	public String getPhrase() {
		// TODO Auto-generated method stub
		return phrase;
	}

	public String translate() throws Exception {
		String[] words = getWords(phrase);	
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String translatedWord = translateWord(word);
			builder.append(translatedWord);
			if(i < words.length-1) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}	


	private String translateWord(String word) throws Exception{
		String AY = "AY";
		String ay = "ay";
		String add = "";
		//if(word.length()>1 && onlyFirstIsUpperCase(word)) {add=ay;}
		if(isUpper(word)) {
			add=AY;
		}else{
			add = ay;
		}
		if(phraseIsOk(word)) {
			if(startsWithVowel(word)) {
				if(onlyFirstIsUpperCase(word) && word.length() > 1) {
					return convertOnlyFirstToUpper(word + add);
				}else {
					return word+add;
				}
			}else if(word.length() > 1 && startsWithXrCheck(word)) {
				if(onlyFirstIsUpperCase(word)) {
					return convertOnlyFirstToUpper(startsWithXr(word));
				}else {
					return startsWithXr(word);
				}
			}else if(startsWithClusters(word)) {
				if(onlyFirstIsUpperCase(word)) {
					return convertOnlyFirstToUpper(convertConsonantCluster(word));
				}else {
					return convertConsonantCluster(word);
				}
			}else {
				if(onlyFirstIsUpperCase(word)) {
					char firstCharacter = word.charAt(0);
					String substring = word.substring(1);
					return convertOnlyFirstToUpper(substring + firstCharacter + add);
				}else {
					char firstCharacter = word.charAt(0);
					String substring = word.substring(1);
					return substring+firstCharacter+add;
				}
			}
		}
		else {
			throw new InvalidPhraseException();
		}
	}

	private String[] getWords(String phrase) {
		return phrase.split("\\s");
	}

	private boolean startsWithVowel(String phrase) {
		char firstCharacter = phrase.charAt(0);
		return vowels.contains(firstCharacter);
	} 

	private boolean startsWithClusters(String word) {
		int count = 0;
		int i = 0;
		while(i < word.length() && validCharacters.contains(word.charAt(i)) && !vowels.contains(word.charAt(i))) {
			count +=1;
			i++;
		}
		if(count>1) {
			return true;
		}else {
			return false;
		}
	}

	private String convertConsonantCluster(String word) {
		String ay;
		if(isUpper(word)) {
			ay="AY";
		}else {
			ay="ay";
		}
		int count = 0;
		int i = 0;
		while(i < word.length() && validCharacters.contains(word.charAt(i)) && !vowels.contains(word.charAt(i))) {
			count +=1;
			i++;
		}
		String substring = word.substring(count);
		return substring + word.substring(0, count) + ay;
	}

	private Boolean startsWithXrCheck(String word) {
		String startingTwo = word.substring(0, 2);
		String xr = "xr";
		String XR = "XR";
		String Xr = "Xr";
		if(startingTwo.equals(xr) || startingTwo.equals(XR) || startingTwo.equals(Xr)) {
			return true;
		}else {
			return false;
		}
	}

	private String startsWithXr(String word) throws Exception {
		if (isUpper(word)) {
			return word+"AY";
		}else if(onlyFirstIsUpperCase(word)) {
			return convertOnlyFirstToUpper(word+"ay");
		}else {
			return word+"ay";
		}
	}

	/**private Boolean onlyFirstIsUpperCase(String word) throws Exception {
		String upper = word.toUpperCase();
		boolean val = false;
		if(word.length()>1 ) {
			if(word.charAt(0) == upper.charAt(0) && word.charAt(1) != upper.charAt(1)) {
				val = true;
			}else {
				for(int i=1; i<word.length();i++) {
					if (word.charAt(i) == upper.charAt(i) && word.charAt(i-1) != upper.charAt(i-1)) {
						val = false;
					}
				}
			}
			return val;
		}else {
			if(word.charAt(0) == upper.charAt(0)) {
				val=true;
			}else {
				val=false;
			}
			return val;
		}	
	}*/
	private Boolean onlyFirstIsUpperCase(String word) throws Exception {
		String upper = word.toUpperCase();
		boolean val = false;
		if(word.length()>1 ) {
			if(word.charAt(0) == upper.charAt(0) && word.charAt(1) != upper.charAt(1)) {
				val = true;
			}else {
				val= false;
			}
		}
		return val;}

	private String convertOnlyFirstToUpper(String word) {
		String lowerCase=word.toLowerCase();
		return Character.toUpperCase(lowerCase.charAt(0)) + lowerCase.substring(1);		
	}
	private Boolean phraseIsOk(String word) throws InvalidPhraseException{
		String upper = word.toUpperCase();
		boolean val = true;
		for(int i=1; i<word.length();i++) {
			if (word.charAt(i) == upper.charAt(i) && word.charAt(i-1) != upper.charAt(i-1)) {
				val = false;
			}
		}
		return val;
	}

	private Boolean isUpper(String word) {
		String wordUpper = word.toUpperCase();
		if (wordUpper.equals(word)){
			return true;
		}
		return false;
	}
}