package tasks.task1;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task1 extends Task<List<String>, Integer> {

    public static void main(String[] args) throws IOException {
        Task1 task1 = new Task1();

        task1.runMain(RunType.TEST, 1, true, false);
        task1.runMain(RunType.TEST, 2, false, true);
        task1.runMain(RunType.REAL, 1, true, true);
    }

    @Override
    protected List<String> readFile(RunType runType, Integer fileNumber) throws IOException {
        return TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATOR);
    }

    @Override
    protected Integer solveFirstPart(List<String> inputText) {
        return inputText.stream()
            .map(line -> line.replaceAll("[a-z]", ""))
            .map(numbers -> String.valueOf(numbers.charAt(0)) + numbers.charAt(numbers.length() - 1))
            .mapToInt(Integer::valueOf)
            .sum();
    }

    @Override
    protected Integer solveSecondPart(List<String> inputText) {
        String numbersAsString = "(one|two|three|four|five|six|seven|eight|nine|\\d)";
        Pattern firstNumberPattern = Pattern.compile(".*?" + numbersAsString + ".*");
        Pattern lastNumberPattern = Pattern.compile(".*" + numbersAsString + ".*");

        return inputText.stream()
            .map(line -> {
                String firstNumber = extractNumber(firstNumberPattern, line);
                String lastNumber = extractNumber(lastNumberPattern, line);

                return Integer.valueOf(firstNumber + lastNumber);
            })
            .mapToInt(Integer::valueOf)
            .sum();
    }

    private String extractNumber(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        String lastNumberString;
        if (matcher.matches()) {
            lastNumberString = matcher.group(1);
        } else {
            throw new IllegalArgumentException("Can not read line: " + line);
        }

        return Arrays.stream(Numbers.values())
            .filter(numbers -> numbers.getName().equals(lastNumberString))
            .map(Numbers::getValue)
            .findFirst()
            .orElse(lastNumberString);
    }
}
