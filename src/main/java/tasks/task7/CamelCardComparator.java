package tasks.task7;

import java.util.Comparator;
import java.util.List;

import helper.Constants;

public class CamelCardComparator extends AbstractCamelCardComparator implements Comparator<String> {

    @Override
    public int compare(String hand1String, String hand2String) {
        CardType hand1CardType = getHand(hand1String);
        CardType hand2CardType = getHand(hand2String);
        if (hand1CardType.getValue() == hand2CardType.getValue()) {
            String[] hand1Cards = hand1String.split(Constants.NONE);
            String[] hand2Cards = hand2String.split(Constants.NONE);
            for (int i = 0; i < hand1Cards.length; i++) {
                if (CardWithoutJoker.valueByName(hand1Cards[i]) > CardWithoutJoker.valueByName(hand2Cards[i])) {
                    return 1;
                } else if ((CardWithoutJoker.valueByName(hand1Cards[i]) < CardWithoutJoker.valueByName(hand2Cards[i]))) {
                    return -1;
                }
            }
        } else if (hand1CardType.getValue() > hand2CardType.getValue()) {
            return 1;
        } else {
            return -1;
        }
        return 0;
    }

    private CardType getHand(String hand) {
        CardType cardType = CardType.HIGH_CARD;
        List<String> cardNames = CardWithoutJoker.getNames();

        for (String cardName : cardNames) {
            cardType = getCardType(hand, cardName, cardType);
        }

        return cardType;
    }

}
