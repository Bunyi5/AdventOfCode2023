package tasks.task7;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task7 extends Task<Map<String, Integer>, Integer> {

    public static void main(String[] args) throws IOException {
        Task7 task7 = new Task7();

        task7.runMain(RunType.TEST, 1, true, false);
        task7.runMain(RunType.REAL, 1, true, false);
    }

    @Override
    protected Map<String, Integer> readFile(RunType runType, Integer fileNumber) throws IOException {
        List<String> lines = TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATOR);
        return lines.stream()
            .map(line -> line.split(Constants.WHITESPACE))
            .collect(Collectors.toMap(line -> line[0], line -> Integer.parseInt(line[1])));
    }

    @Override
    protected Integer solveFirstPart(Map<String, Integer> inputText) {

        Map<String, Integer> sortedMap = new TreeMap<>(new CamelCardComparator());
        sortedMap.putAll(inputText);

        int totalWinnings = 0;
        List<Integer> values = sortedMap.values().stream().toList();
        for (int i = 0; i < values.size(); i++) {
            totalWinnings = totalWinnings + (values.get(i) * (i + 1));
        }

        return totalWinnings;
    }

    @Override
    protected Integer solveSecondPart(Map<String, Integer> inputText) {
        return null;
    }
}
