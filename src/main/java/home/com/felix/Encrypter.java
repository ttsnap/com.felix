package home.com.felix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Encrypter {

	private HashMap<Character, Integer> shiftingMap;

	public Encrypter(HashMap<Character, Integer> shiftingMap) {
		super();
		this.shiftingMap = shiftingMap;
	}

	public HashMap<Character, Integer> getShiftingMap() {
		return shiftingMap;
	}

	public void setShiftingMap(HashMap<Character, Integer> shiftingMap) {
		this.shiftingMap = shiftingMap;
	}

	public String encrypt(String inputString, int returnLength) {

		if (returnLength < inputString.length()) {
			return "[ERROR occored]: Parameter 'returnLength' must be at least length of the input String";
		}
		if (returnLength == inputString.length()) {
			return shiftLetters(inputString);
		} else {
			String randomEnd = generateRandomStringOfLength(returnLength - inputString.length());
			String extendedInput = inputString + randomEnd;
			return shiftLetters(extendedInput);
		}
	}

	private String shiftLetters(String inputString) {

		char[] inputArray = inputString.toCharArray();
		String result = inputString;

		List<Character> letters = new ArrayList<Character>(shiftingMap.keySet());

		Collections.sort(letters);
		boolean[] alreadyShiftedPlace;
		for (char letter : letters) {
			alreadyShiftedPlace = new boolean[inputArray.length];
			for (int i = inputArray.length - 1; i >= 0; i--) {
				if (inputArray[i] == letter && !alreadyShiftedPlace[i]) {
					if (i + shiftingMap.get(letter) + 1 <= inputArray.length) {
						result = result.substring(0, i) + result.substring(i + 1, i + shiftingMap.get(letter) + 1)
								+ letter + result.substring(i + shiftingMap.get(letter) + 1);
						alreadyShiftedPlace[i + shiftingMap.get(letter)] = true;
					} else {
						//
					}
				}
				inputArray = result.toCharArray();

			}
		}
		return result;
	}

	private String shiftLettersInverse(String inputString) {

		char[] inputArray = inputString.toCharArray();
		String result = inputString;

		List<Character> letters = new ArrayList<Character>(shiftingMap.keySet());
		
		// invert order of hash map keys
		Collections.sort(letters, (letter1, letter2) -> Character.compare(letter2, letter1));
		
		boolean[] alreadyShiftedPlace;
		for (char letter : letters) {
			alreadyShiftedPlace = new boolean[inputArray.length];
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] == letter && !alreadyShiftedPlace[i]) {
					if (i - shiftingMap.get(letter) >=0) {
						result = result.substring(0, i - shiftingMap.get(letter) )
								+ letter + result.substring(i - shiftingMap.get(letter), i) + result.substring(i+1);
						alreadyShiftedPlace[i - shiftingMap.get(letter)] = true;
					} else {
						//
					}
				}
				inputArray = result.toCharArray();

			}
		}
		return result;
	}

	public String decode(String inputString) {
		return shiftLettersInverse(inputString);
	}

	public static String generateRandomStringOfLength(int targetStringLength) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'

		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

	}

}
