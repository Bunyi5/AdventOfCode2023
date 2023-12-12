package result;

import java.util.Objects;

import helper.RunType;

public class ResultChecker {

    public static void assertResults(Object a, String taskName, int partTaskNumber, RunType runType) {
        String reset = "\033[0m";
        String yellow = "\033[0;33m";
        String red = "\033[0;31m";
        String green = "\033[0;32m";

        Object b = Answers.valueOf(taskName.toUpperCase() + "_" + partTaskNumber + "_" + runType).getAnswer();

        if (Objects.isNull(a) || Objects.isNull(b)) {
            System.out.println(yellow + "The result is null!" + reset);
            return;
        } if (!a.equals(b)) {
            System.out.println(red + "The result is not right!" + reset);
        } else {
            System.out.println(green + "The result is right!" + reset);
        }
    }
}
