package tasks.task5;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task5 extends Task<List<List<List<BigInteger>>>, BigInteger> {

    public static void main(String[] args) throws IOException {
        Task5 task5 = new Task5();

        task5.runMain(RunType.TEST, 1, true, true);
        task5.runMain(RunType.REAL, 1, true, true);
    }

    @Override
    protected List<List<List<BigInteger>>> readFile(RunType runType, Integer fileNumber) throws IOException {
        String almanac = TxtConverter.convertTxtToStringContent(getTaskName(), runType, fileNumber);
        String[] maps = almanac.split(Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + ".*" + Constants.LINE_SEPARATOR);
        maps[0] = maps[0].replace("seeds: ", "");

        return Arrays.stream(maps)
            .map(map -> map.split(Constants.LINE_SEPARATOR))
            .map(mapArrays -> Arrays.stream(mapArrays)
                .map(mapLine -> Arrays.stream(mapLine.split(Constants.WHITESPACE))
                        .map(BigInteger::new)
                        .toList())
                .toList()
            ).toList();
    }

    @Override
    protected BigInteger solveFirstPart(List<List<List<BigInteger>>> inputText) {
        List<List<BigInteger>> seeds = inputText.get(0);
        List<List<List<BigInteger>>> maps = inputText.stream()
            .filter(lists -> !lists.equals(seeds))
            .toList();

        return getLowestLocation(seeds.get(0), maps);
    }

    @Override
    protected BigInteger solveSecondPart(List<List<List<BigInteger>>> inputText) {
        List<List<BigInteger>> firstElement = inputText.get(0);
        List<BigInteger> seedRanges = firstElement.get(0);
        List<BigInteger> seeds = new ArrayList<>();
        List<List<List<BigInteger>>> maps = inputText.stream()
            .filter(lists -> !lists.equals(firstElement))
            .toList();

        // TODO: 09/12/2023 Not working
        for (int i = 0; i < seedRanges.size(); i = i + 2) {
            for (BigInteger j = BigInteger.ZERO; j.compareTo(seedRanges.get(i + 1)) < 0; j = j.add(BigInteger.ONE)) {
                seeds.add(seedRanges.get(i).add(j));
            }
        }

        return getLowestLocation(seeds, maps);
    }

    private BigInteger getLowestLocation(List<BigInteger> seeds, List<List<List<BigInteger>>> inputText) {
        List<BigInteger> correspondingNumbers = seeds;

        for (List<List<BigInteger>> maps : inputText) {
            correspondingNumbers = getNextCorrespondingNumbers(correspondingNumbers, maps);
        }

        return correspondingNumbers.stream()
            .min(BigInteger::compareTo)
            .orElseThrow();
    }

    private List<BigInteger> getNextCorrespondingNumbers(List<BigInteger> seeds, List<List<BigInteger>> maps) {
        List<BigInteger> finalNumbers = new ArrayList<>();
        BigInteger result = null;

        for (BigInteger seed : seeds) {
            for (List<BigInteger> mapLine : maps) {
                result = getCorrespondingNumber(seed, mapLine);

                if (result != null) {
                    finalNumbers.add(result);
                    break;
                }
            }
            if (result == null) {
                finalNumbers.add(seed);
            }
            result = null;
        }

        return finalNumbers;
    }

    private BigInteger getCorrespondingNumber(BigInteger seed, List<BigInteger> mapLine) {
        BigInteger firstInRange = mapLine.get(1);
        BigInteger lastInRange = mapLine.get(1).add(mapLine.get(2));

        if (seed.compareTo(firstInRange) >= 0 && seed.compareTo(lastInRange) <= 0) {
            return mapLine.get(0).add(seed.subtract(firstInRange));
        }

        return null;
    }
}
