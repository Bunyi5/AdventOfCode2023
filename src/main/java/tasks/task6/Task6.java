package tasks.task6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task6 extends Task<List<String[]>, BigInteger> {

    public static void main(String[] args) throws IOException {
        Task6 task6 = new Task6();

        task6.runMain(RunType.TEST, 1, true, true);
        task6.runMain(RunType.REAL, 1, true, true);
    }

    @Override
    protected List<String[]> readFile(RunType runType, Integer fileNumber) throws IOException {
        List<String> lines = TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATOR);

        return lines.stream()
            .map(line -> line.replaceAll("^\\D*", ""))
            .map(line -> line.split(Constants.WHITESPACES))
            .toList();
    }

    @Override
    protected BigInteger solveFirstPart(List<String[]> inputText) {
        List<Integer> countWins = new ArrayList<>();

        List<int[]> races = new ArrayList<>();
        for (int i = 0; i < inputText.get(0).length; i++) {
            races.add(new int[]{Integer.parseInt(inputText.get(0)[i]), Integer.parseInt(inputText.get(1)[i])});
        }

        for (int[] race : races) {
            int countWin = 0;
            for (int i = 0; i <= race[0]; i++) {
                if (((race[0] - i) * i) > race[1]) {
                    countWin++;
                }
            }
            countWins.add(countWin);
        }

        return BigInteger.valueOf(countWins.stream()
            .reduce(1, (a, b) -> a * b));
    }

    @Override
    protected BigInteger solveSecondPart(List<String[]> inputText) {
        BigInteger countWin = BigInteger.ZERO;

        BigInteger milliseconds = new BigInteger(String.join("", inputText.get(0)));
        BigInteger millimeters = new BigInteger(String.join("", inputText.get(1)));

        for (BigInteger i = BigInteger.ZERO; i.compareTo(milliseconds) <= 0; i = i.add(BigInteger.ONE)) {
            if (((milliseconds.subtract(i)).multiply(i)).compareTo(millimeters) > 0) {
                countWin = countWin.add(BigInteger.ONE);
            }
        }

        return countWin;
    }
}
