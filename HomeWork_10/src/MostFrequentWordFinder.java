import java.util.*;

public class MostFrequentWordFinder {

    private final HashMap<String, Integer> mapWordFrequency = new HashMap<>();

    private String mostFrequentWord;

    public static class PairWordFrequency {
        public String word;
        public Integer frequency;

        public PairWordFrequency(String word, Integer frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return '[' + word + ", "
                    + frequency +
                    ']';
        }
    }

    public MostFrequentWordFinder(String line) {
        checkLineNotNullPtr(line);
        String[] clearedWords = clearWords(line);
        findMostMostFrequentWord(clearedWords);
        checkWordsHaveBeenEntered();
    }

    private void checkLineNotNullPtr(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Expected string got null ptr");
        }
    }

    private String[] clearWords(String line) {
        String[] words = line.split(" ");
        return Arrays.stream(words).filter(word -> !word.equals("")).toArray(String[]::new);
    }

    private void findMostMostFrequentWord(String[] words) {
        for (String word : words) {
            if (mapWordFrequency.containsKey(word)) {
                updateWordFrequency(word);
            } else {
                addNewWord(word);
            }
            updateMostFrequentWord(word);
        }
    }

    private void updateWordFrequency(String word) {
        Integer frequency = mapWordFrequency.get(word);
        frequency++;
        mapWordFrequency.replace(word, frequency);
    }

    private void addNewWord(String word) {
        mapWordFrequency.put(word, 1);
    }

    private void updateMostFrequentWord(String newWord) {
        setupInitialWordValue(newWord);

        final Integer newFrequency = mapWordFrequency.get(newWord);
        final Integer oldFrequency = mapWordFrequency.get(mostFrequentWord);

        if (newFrequency.compareTo(oldFrequency) > 0) {
            mostFrequentWord = newWord;
        }
    }

    private void setupInitialWordValue(String newWord) {
        if (mostFrequentWord == null) {
            mostFrequentWord = newWord;
        }
    }

    private void checkWordsHaveBeenEntered() {
        if (mostFrequentWord == null) {
            throw new IllegalArgumentException("No words have been entered");
        }
    }

    public PairWordFrequency getMostFrequentWordAndFrequency() {
        return new PairWordFrequency(mostFrequentWord, mapWordFrequency.get(mostFrequentWord));
    }

}
