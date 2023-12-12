package tasks;

import java.io.IOException;

import helper.RunType;
import result.ResultChecker;

public abstract class Task<T, S> {

    protected void runMain(RunType runType, Integer fileNumber, boolean runFirst, boolean runSecond) throws IOException {
        System.out.println(runType + " " + fileNumber);
        T inputText = readFile(runType, fileNumber);

        if (runFirst) {
            runFirstPart(runType, inputText);
        }

        if (runSecond) {
            runSecondPart(runType, inputText);
        }
    }

    private void runFirstPart(RunType runType, T inputText) {
        S firstSolution = solveFirstPart(inputText);
        System.out.println("First part solution: " + firstSolution);
        ResultChecker.assertResults(firstSolution, getTaskName(), 1, runType);
    }

    private void runSecondPart(RunType runType, T inputText) {
        S secondSolution = solveSecondPart(inputText);
        System.out.println("Second part solution: " + secondSolution);
        ResultChecker.assertResults(secondSolution, getTaskName(), 2, runType);
    }

    protected String getTaskName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    protected abstract T readFile(RunType runType, Integer fileNumber) throws IOException;

    protected abstract S solveFirstPart(T inputText);

    protected abstract S solveSecondPart(T inputText);

}
