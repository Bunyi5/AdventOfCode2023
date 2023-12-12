package tasks.task7;

import lombok.Getter;

@Getter
public enum CardType {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int value;

    CardType(int value) {
        this.value = value;
    }

}
