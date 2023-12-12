package tasks.task4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task4 extends Task<List<String>, Integer> {

    public static void main(String[] args) throws IOException {
        Task4 task4 = new Task4();

        task4.runMain(RunType.TEST, 1, true, true);
        task4.runMain(RunType.REAL, 1, true, true);
    }

    @Override
    protected List<String> readFile(RunType runType, Integer fileNumber) throws IOException {
        return TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATOR);
    }

    @Override
    protected Integer solveFirstPart(List<String> inputText) {
        int sum = 0;
        List<String> strings = removeCard(inputText);
        for (String string : strings) {
            String[] numbers = string.split(Constants.WHITESPACE + Constants.PIPE + Constants.WHITESPACES);

            int count = countMyWinningNumbers(numbers);

            sum = sum + (int) Math.pow(2,count - 1);
        }
        return sum;
    }

    @Override
    protected Integer solveSecondPart(List<String> inputText) {
        List<String> strings = removeCard(inputText);
        int[] scratchCards = new int[strings.size()];
        Arrays.fill(scratchCards, 1);

        for (int i = 0; i < strings.size(); i++) {
            String[] numbers = strings.get(i).split(Constants.WHITESPACE + Constants.PIPE + Constants.WHITESPACES);

            int count = countMyWinningNumbers(numbers);

            for (int j = 1; j <= count; j++) {
                scratchCards[i + j] = scratchCards[i + j] + scratchCards[i];
            }
        }

        return Arrays.stream(scratchCards).sum();
    }

    private List<String> removeCard(List<String> inputText) {
        return inputText.stream()
            .map(line -> line.split(Constants.COLON + Constants.WHITESPACES))
            .map(strings -> strings[1])
            .toList();
    }

    private int countMyWinningNumbers(String[] numbers) {
        List<Integer> winningNumbers = Arrays.stream(numbers[0].split(Constants.WHITESPACES))
            .map(Integer::parseInt)
            .toList();

        List<Integer> myNumbers = Arrays.stream(numbers[1].split(Constants.WHITESPACES))
            .map(Integer::parseInt)
            .toList();

        long count = myNumbers.stream()
            .filter(winningNumbers::contains)
            .count();

        return (int) count;
    }
}
