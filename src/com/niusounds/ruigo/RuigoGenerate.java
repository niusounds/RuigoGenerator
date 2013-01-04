package com.niusounds.ruigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuigoGenerate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RuigoGenerate main = new RuigoGenerate();
		String input = "ばれんたいん";
		String filter = "たいん";
		String[] result = main.generate(input);
		result = main.filter(result, filter);
		for (String s : result) {
			System.out.println(s);
		}
	}

	public String[] filter(String[] result, String filter) {
		List<String> list = new ArrayList<String>();
		for (String s : result) {
			if (s.contains(filter)) {
				list.add(s);
			}
		}
		return list.toArray(new String[0]);
	}

	private Map<Character, String[]> map = new HashMap<Character, String[]>();

	public RuigoGenerate() {
		map.put('a', new String[] { "あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ", "が", "ざ", "だ", "ば", "ぱ" });
		map.put('i', new String[] { "い", "き", "し", "ち", "に", "ひ", "み", "り", "ぎ", "じ", "ぢ", "び", "ぴ" });
		map.put('u', new String[] { "う", "く", "す", "つ", "ぬ", "ふ", "む", "ゆ", "る", "ぐ", "ず", "づ", "ぶ", "ぷ" });
		map.put('e', new String[] { "え", "け", "せ", "て", "ね", "へ", "め", "れ", "げ", "ぜ", "で", "べ", "ぺ" });
		map.put('o', new String[] { "お", "こ", "そ", "と", "の", "ほ", "も", "よ", "ろ", "を", "ご", "ぞ", "ど", "ぼ", "ぽ" });
		map.put('n', new String[] { "ん" });
	}

	public String[] generate(String input) {
		String tmp = input.replaceAll("[あかさたなはまやらわがざだばぱ]", "a").replaceAll("[いきしちにひみりぎじぢびぴ]", "i")
				.replaceAll("[うくすつぬふむゆるぐずづぶぷ]", "u").replaceAll("[えけせてねへめれげぜでべぺ]", "e")
				.replaceAll("[おこそとのほもよろをごぞどぼぽ]", "o").replaceAll("[ん]", "n");
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < tmp.length(); i++) {
			list.add(map.get(tmp.charAt(i)));
		}

		String[] result = zentori(list, 0);
		return result;
	}

	private String[] zentori(List<String[]> list, int index) {
		if (list.size() - 1 > index) {
			return kumiawase(list.get(index), zentori(list, index + 1));
		} else {
			return list.get(index);
		}
	}

	private String[] kumiawase(String[] arr1, String[] arr2) {
		List<String> result = new ArrayList<String>();
		for (String s1 : arr1) {
			for (String s2 : arr2) {
				result.add(s1 + s2);
			}
		}
		return result.toArray(new String[0]);
	}

}
