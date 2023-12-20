package tasks.task8;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.Constants;
import helper.RunType;
import helper.TxtConverter;
import tasks.Task;

public class Task8 extends Task<Network, Integer> {

    public static void main(String[] args) throws IOException {
        Task8 task8 = new Task8();

        task8.runMain(RunType.TEST, 1, true, false);
        task8.runMain(RunType.TEST, 2, true, false);
        task8.runMain(RunType.REAL, 1, true, false);
    }

    @Override
    protected Network readFile(RunType runType, Integer fileNumber) throws IOException {
        List<String> lines = TxtConverter.convertTxtToStringList(getTaskName(), runType, fileNumber, Constants.LINE_SEPARATORS);

        String[] instructions = lines.get(0).split(Constants.NONE);
        lines.removeFirst();

        List<String[]> formattedLines = lines.stream()
            .map(line -> line.replaceAll("[(|)]", ""))
            .map(line -> line.split(Constants.WHITESPACE + Constants.EQUAL + Constants.WHITESPACE))
            .toList();

        Map<String, Node> nodes = new HashMap<>();
        for (String[] node : formattedLines) {
            String[] nodeEdges = node[1].split(Constants.COMMA + Constants.WHITESPACE);
            nodes.put(node[0], Node.builder()
                .left(nodeEdges[0])
                .right(nodeEdges[1])
                .build());
        }

        return Network.builder()
            .startNode(formattedLines.get(0)[0])
            .instructions(instructions)
            .nodes(nodes)
            .build();
    }

    @Override
    protected Integer solveFirstPart(Network network) {
        int stepCount = 0;
        int instructionsCounter = 0;
        String currentNode = network.getStartNode();
        String[] instructions = network.getInstructions();
        Map<String, Node> nodes = network.getNodes();

        // TODO: 20/12/2023 Not working
        while (!currentNode.equals("ZZZ")) {
            System.out.println(currentNode);
            if ("L".equals(instructions[instructionsCounter])) {
                currentNode = nodes.get(currentNode).getLeft();
            } else {
                currentNode = nodes.get(currentNode).getRight();
            }

            instructionsCounter++;
            stepCount++;

            if (instructionsCounter == instructions.length) {
                instructionsCounter = 0;
            }
        }

        return stepCount;
    }

    @Override
    protected Integer solveSecondPart(Network network) {
        return null;
    }
}
