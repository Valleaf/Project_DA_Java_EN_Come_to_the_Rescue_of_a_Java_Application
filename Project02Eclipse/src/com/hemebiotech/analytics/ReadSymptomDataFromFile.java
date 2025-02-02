package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it,
	 *                 one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public List<String> getSymptoms() {
		ArrayList<String> result = new ArrayList<>();

		if (filepath != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

				String line = reader.readLine();
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				System.err.println(
						"File not found. Please check the filenames and try again. Filepath given : " + filepath);
			}
		}

		return result;
	}

	@Override
	public TreeMap<String, Integer> getUniqueSymptomNames(List<String> symptoms) {
		// Converting a List to a HashMap will remove all duplicates and allow us to
		// count the number of elements.
		HashMap<String, Integer> symptomsAndOccurences = new HashMap<>();
		for (String symptom : symptoms) {
			symptomsAndOccurences.put(symptom, Collections.frequency(symptoms, symptom));
		}

		// Converting the results to a TreeMap will automatically sort the elements
		// alphabetically.
		TreeMap<String, Integer> sortedSymptoms = new TreeMap<>();
		sortedSymptoms.putAll(symptomsAndOccurences);
		return sortedSymptoms;
	}

}
