package home.com.felix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Tests {

	Encrypter encrypter;

	@Test
	public void test_Encrypter_encode_givenStringAndHashMapReturnsExpectedEncryptedString() {

		// Setup

		HashMap<Character, Integer> shiftingMap = new HashMap<Character, Integer>();

		encrypter = new Encrypter(shiftingMap);

		shiftingMap.put('a', 1);
		shiftingMap.put('b', 2);
		shiftingMap.put('d', 1);

		List<String> klartexte = new ArrayList<String>();

		klartexte.add(0, "hallo");
		klartexte.add(1, "accacc");
		klartexte.add(2, "acbccc");
		klartexte.add(3, "abcccc");
		klartexte.add(4, "bc");
		klartexte.add(5, "bac");
		klartexte.add(6, "aaabbbccccb");
		klartexte.add("Das ist ein längerer String, der hier getestet wird.");

		// Act &
		// Check

		Assertions.assertEquals("hlalo", encrypt(klartexte.get(0)));
		Assertions.assertEquals("caccac", encrypt(klartexte.get(1)));
		Assertions.assertEquals("caccbc", encrypt(klartexte.get(2)));
		Assertions.assertEquals("acbccc", encrypt(klartexte.get(3)));
		Assertions.assertEquals("bc", encrypt(klartexte.get(4)));
		Assertions.assertEquals("cab", encrypt(klartexte.get(5)));
		Assertions.assertEquals("aabaccbbccb", encrypt(klartexte.get(6)));

		for (String klartext : klartexte) {
			String encryptedString = encrypter.encrypt(klartext, 100);
			System.out.println("'" + klartext + "' is extendedly encrpyted to: " + encryptedString + "; Decryption: "
					+ encrypter.decode(encryptedString));
		}

		// let's test the decrypt/decode function
		for (String klartext : klartexte) {
			Assertions.assertEquals(klartext, encrypter.decode(encrypter.encrypt(klartext, 7 * klartext.length()))
					.substring(0, klartext.length()));
		}

		// let's try another shifting map
		setAllValuesForShiftingMap(shiftingMap);
		for (String klartext : klartexte) {
			String encryptedString = encrypter.encrypt(klartext, 400);
			System.out.println("'" + klartext + "' is extendedly encrpyted to: " + encryptedString + "; Decryption: "
					+ encrypter.decode(encryptedString));
		}
		for (int i = 0; i < 100; i++) {
			String klartext = Encrypter.generateRandomStringOfLength(50);
			Assertions.assertEquals(klartext, encrypter.decode(encrypter.encrypt(klartext, 7 * klartext.length()))
					.substring(0, klartext.length()));
		}
	}

	private void setAllValuesForShiftingMap(HashMap<Character, Integer> shiftingMap) {
		shiftingMap.put('a', 11);
		shiftingMap.put('b', 10);
		shiftingMap.put('c', 22);
		shiftingMap.put('d', 8);
		shiftingMap.put('e', 10);
		shiftingMap.put('f', 4);
		shiftingMap.put('g', 15);
		shiftingMap.put('h', 7);
		shiftingMap.put('i', 6);
		shiftingMap.put('j', 3);
		shiftingMap.put('k', 13);
		shiftingMap.put('l', 32);
		shiftingMap.put('m', 10);
		shiftingMap.put('n', 31);
		shiftingMap.put('o', 9);
		shiftingMap.put('p', 10);
		shiftingMap.put('q', 7);
		shiftingMap.put('r', 10);
		shiftingMap.put('s', 20);
		shiftingMap.put('t', 5);
		shiftingMap.put('u', 2);
		shiftingMap.put('v', 18);
		shiftingMap.put('w', 1);
		shiftingMap.put('x', 10);
		shiftingMap.put('y', 60);
		shiftingMap.put('z', 10);
		shiftingMap.put(' ', 80);
		shiftingMap.put(',', 99);
		shiftingMap.put('.', 50);

		encrypter.setShiftingMap(shiftingMap);
	}

	private String encrypt(String klartext) {
		return encrypter.encrypt(klartext, klartext.length());
	}
}
