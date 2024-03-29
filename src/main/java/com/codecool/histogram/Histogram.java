package com.codecool.histogram;

import java.util.*;

/**
 * Class to generate diagram of word distribution in text based on their length.
 */
public class Histogram {

    private Map<Range, Integer> histogram;

    public Histogram() {
        this.histogram = new HashMap<>();
    }

    /**
     * Get the already generated histogram 
     */
    public Map<Range, Integer> getHistogram() {
        return histogram;
    }

    /**
     * Generate x amount of step width ranges.
     */
    public List<Range> generateRanges(int step, int amount) {
        if (step < 0) {
            throw new IllegalArgumentException("Step must be positive.");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            ranges.add(new Range(i * step + 1, i * step + step));
        }
        return ranges;
    }

    /**
     * Calculates the distribution of words in a given text based on provided ranges.
     */
    public Map<Range, Integer> generate(String text, List<Range> ranges) {
        if (text == null || ranges == null) {
            throw new IllegalArgumentException();
        }

        histogram = new HashMap<>();
        if ("".equals(text)) {
            return histogram;
        }
        // remove punctuation and split by whitespace
        String[] words = text.replaceAll("[^a-zA-Z \\r?\\n]", "").split("\\s+");

        for (String word : words) {
            for (Range range : ranges) {
                if (range.isInRange(word)) {
                    Integer count = histogram.getOrDefault(range, 0);
                    histogram.put(range, count + 1);
                }
            }
        }
        return histogram;
    }
    

    /**
     * Returns with the string representation of the generated histogram
     */
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();

        for (Range range : histogram.keySet()) {
            Integer count = histogram.get(range);
            String line = String.format("%s| %s%s", range.toString(), "*".repeat(count), System.lineSeparator());
            resultBuilder.append(line);
        }

        return resultBuilder.toString();
    }
}
