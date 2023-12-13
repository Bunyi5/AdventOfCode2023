package tasks.task7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public enum CardWithJoker {
    ACE("A", 14),
    KING("K", 13),
    QUEEN("Q", 12),
    TEN("T", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    JOKER("J", 1);

    private static final Map<String, CardWithJoker> BY_NAME = new HashMap<>();

    static {
        for (CardWithJoker c : values()) {
            BY_NAME.put(c.name, c);
        }
    }

    private final String name;
    private final Integer value;

    CardWithJoker(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static List<String> getNames() {
        return Arrays.stream(CardWithJoker.values())
            .map(CardWithJoker::getName)
            .toList();
    }

    public static Integer valueByName(String name) {
        return BY_NAME.get(name).getValue();
    }
}
