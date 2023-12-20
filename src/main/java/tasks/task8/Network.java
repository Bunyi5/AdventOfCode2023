package tasks.task8;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Network {

    private String startNode;

    private String[] instructions;

    private Map<String, Node> nodes;
}
