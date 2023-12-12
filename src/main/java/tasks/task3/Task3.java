package tasks.task3;

import java.io.IOException;
import java.util.regex.Pattern;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task3 extends Task<String[][], Integer> {

    public static void main(String[] args) throws IOException {
        Task3 task3 = new Task3();

        task3.runMain(RunType.TEST, 1, true, false);
        task3.runMain(RunType.REAL, 1, true, false);
    }

    @Override
    protected String[][] readFile(RunType runType, Integer fileNumber) throws IOException {
        return TxtConverter.convertTxtToStringMatrix(getTaskName(), runType, fileNumber, Constants.NONE);
    }

    @Override
    protected Integer solveFirstPart(String[][] inputText) {
        Pattern numberPattern = Pattern.compile("[0-9]");
        Pattern symbolPattern = Pattern.compile("[^0-9\\n.]");
        int sum = 0;
        StringBuilder numberAsString = new StringBuilder();
        boolean addNumber = false;

        for (int i = 0; i < inputText.length; i++) {
            for (int j = 0; j < inputText[0].length; j++) {
                String string = inputText[i][j];

                if (numberPattern.matcher(string).matches()) {
                    numberAsString.append(string);
                    // Check left
                    if (j > 0) {
                        // Check top left
                        if (i > 0) {
                            if (symbolPattern.matcher(inputText[i-1][j-1]).matches()) {
                                addNumber = true;
                            }
                        }
                        // Check bottom left
                        if (i < inputText.length - 1) {
                            if (symbolPattern.matcher(inputText[i+1][j-1]).matches()) {
                                addNumber = true;
                            }
                        }
                        if (symbolPattern.matcher(inputText[i][j-1]).matches()) {
                            addNumber = true;
                        }
                    }
                    // Check right
                    if (j < inputText[0].length - 1) {
                        // Check top right
                        if (i > 0) {
                            if (symbolPattern.matcher(inputText[i-1][j+1]).matches()) {
                                addNumber = true;
                            }
                        }
                        // Check bottom right
                        if (i < inputText.length - 1) {
                            if (symbolPattern.matcher(inputText[i+1][j+1]).matches()) {
                                addNumber = true;
                            }
                        }
                        if (symbolPattern.matcher(inputText[i][j+1]).matches()) {
                            addNumber = true;
                        }
                    }
                    // Check top
                    if (i > 0) {
                        if (symbolPattern.matcher(inputText[i-1][j]).matches()) {
                            addNumber = true;
                        }
                    }
                    // Check bottom
                    if (i < inputText.length - 1) {
                        if (symbolPattern.matcher(inputText[i+1][j]).matches()) {
                            addNumber = true;
                        }
                    }
                } else {
                    if (addNumber) {
                        sum = sum + Integer.parseInt(numberAsString.toString());
                        addNumber = false;
                    }
                    numberAsString.setLength(0);
                }
            }
            if (addNumber) {
                sum = sum + Integer.parseInt(numberAsString.toString());
            }
            addNumber = false;
            numberAsString.setLength(0);
        }
        return sum;
    }

    @Override
    protected Integer solveSecondPart(String[][] inputText) {
        return null;
    }
}
