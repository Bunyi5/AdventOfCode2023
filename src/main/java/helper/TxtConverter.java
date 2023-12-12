package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TxtConverter {

    public static String convertTxtToStringContent(String taskName, RunType runType, Integer fileNumber) throws IOException {
        Path path = Path.of(Constants.RESOURCE_PATH, taskName, runType.toString().toLowerCase() + fileNumber + ".txt");
        return Files.readString(path);
    }

    public static List<String> convertTxtToStringList(String taskName, RunType runType, Integer fileNumber, String separator) throws IOException {
        String content = convertTxtToStringContent(taskName, runType, fileNumber);

        return Arrays.stream(content.split(separator))
            .collect(Collectors.toList());
    }

    public static String[][] convertTxtToStringMatrix(String taskName, RunType runType, Integer fileNumber, String separator) throws IOException {
        List<String> stringList = convertTxtToStringList(taskName, runType, fileNumber, Constants.LINE_SEPARATOR);

        return stringList.stream()
            .map(row -> row.split(separator))
            .toArray(String[][]::new);
    }

}
