package tasks.task1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Numbers {

    ONE("one", "1"),
    TWO("two", "2"),
    THREE("three", "3"),
    FOUR("four", "4"),
    FIVE("five", "5"),
    SIX("six", "6"),
    SEVEN("seven", "7"),
    EIGHT("eight", "8"),
    NINE("nine", "9");

    private final String name;
    private final String value;

}
