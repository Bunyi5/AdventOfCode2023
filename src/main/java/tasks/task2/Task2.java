package tasks.task2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task2 extends Task<List<Game>, Integer> {

    public static void main(String[] args) throws IOException {
        Task2 task2 = new Task2();

        task2.runMain(RunType.TEST, 1, true, true);
        task2.runMain(RunType.REAL, 1, true, true);
    }

    @Override
    protected List<Game> readFile(RunType runType, Integer fileNumber) throws IOException {
        List<String> lines = TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATOR);
        List<Game> games = new ArrayList<>();

        for (String line : lines) {
            String[] gameSplit = line.split(Constants.COLON + Constants.WHITESPACE);
            int gameId = Integer.parseInt(gameSplit[0].split(Constants.WHITESPACE)[1]);

            List<List<Cube>> cubeSets = new ArrayList<>();
            String[] cubeSetSplit = gameSplit[1].split(Constants.SEMICOLON + Constants.WHITESPACE);
            for (String cubeSetString : cubeSetSplit) {

                List<Cube> cubes = new ArrayList<>();
                String[] cubeSplit = cubeSetString.split(Constants.COMMA + Constants.WHITESPACE);
                for (String cubeString : cubeSplit) {

                    String[] cubePropertySplit = cubeString.split(Constants.WHITESPACE);
                    Cube cube = new Cube(Integer.parseInt(cubePropertySplit[0]), cubePropertySplit[1]);
                    cubes.add(cube);
                }

                cubeSets.add(cubes);
            }

            Game game = new Game(gameId, cubeSets);
            games.add(game);
        }

        return games;
    }

    @Override
    protected Integer solveFirstPart(List<Game> inputText) {
        int maxRedCubes = 12;
        int maxGreenCubes = 13;
        int maxBlueCubes = 14;

        return inputText.stream()
            .filter(game -> game.getGames().stream()
                .allMatch(cubes -> cubes.stream()
                    .allMatch(cube -> checkByColor(maxRedCubes, maxGreenCubes, maxBlueCubes, cube))))
            .mapToInt(Game::getId)
            .sum();
    }

    @Override
    protected Integer solveSecondPart(List<Game> inputText) {
        int sumOfMinCubes = 0;

        for (Game game : inputText) {
            int minRedCubes = 0;
            int minGreenCubes = 0;
            int minBlueCubes = 0;

            for (List<Cube> cubes : game.getGames()) {
                for (Cube cube : cubes) {
                    switch (cube.getColor()) {
                        case "red" -> {
                            if (cube.getValue() > minRedCubes) {
                                minRedCubes = cube.getValue();
                            }
                        }
                        case "green" -> {
                            if (cube.getValue() > minGreenCubes) {
                                minGreenCubes = cube.getValue();
                            }
                        }
                        case "blue" -> {
                            if (cube.getValue() > minBlueCubes) {
                                minBlueCubes = cube.getValue();
                            }
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + cube.getColor());
                    }
                }
            }

            sumOfMinCubes = sumOfMinCubes + (minRedCubes * minGreenCubes * minBlueCubes);
        }

        return sumOfMinCubes;
    }

    private boolean checkByColor(int maxRedCubes, int maxGreenCubes, int maxBlueCubes, Cube cube) {
        return switch (cube.getColor()) {
            case "red" -> cube.getValue() <= maxRedCubes;
            case "green" -> cube.getValue() <= maxGreenCubes;
            case "blue" -> cube.getValue() <= maxBlueCubes;
            default -> false;
        };
    }
}
