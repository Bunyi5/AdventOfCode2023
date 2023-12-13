package tasks.task7;

import helper.Constants;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractCamelCardComparator {

    protected CardType getCardType(String hand, String cardName, CardType cardType) {
        switch (StringUtils.countMatches(hand, cardName)) {
            case 5:
                cardType = CardType.FIVE_OF_A_KIND;
                break;
            case 4:
                if (cardType.getValue() < CardType.FOUR_OF_A_KIND.getValue()) {
                    cardType = CardType.FOUR_OF_A_KIND;
                }
                break;
            case 3:
                if (cardType.getValue() < CardType.FULL_HOUSE.getValue()) {
                    String remainingHand = hand.replace(cardName, "");
                    String[] split = remainingHand.split(Constants.NONE);

                    if (split[0].equals(split[1])) {
                        cardType = CardType.FULL_HOUSE;
                    } else {
                        cardType = CardType.THREE_OF_A_KIND;
                    }
                }
                break;
            case 2:
                if (cardType.getValue() < CardType.TWO_PAIR.getValue()) {
                    String remainingHand = hand.replace(cardName, "");
                    String[] split = remainingHand.split(Constants.NONE);
                    String card1 = split[0];
                    String card2 = split[1];
                    String card3 = split[2];

                    if (card1.equals(card2) || card1.equals(card3) || card2.equals(card3)) {
                        cardType = CardType.TWO_PAIR;
                    } else {
                        cardType = CardType.ONE_PAIR;
                    }
                }
        }

        return cardType;
    }
}
