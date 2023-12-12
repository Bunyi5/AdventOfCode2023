package tasks.task7;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Hand {

    private final String hand;

    private final Card mostPowerfulCard;

    private final CardType cardType;

}
