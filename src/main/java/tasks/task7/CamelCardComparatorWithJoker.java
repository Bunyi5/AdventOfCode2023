package tasks.task7;

import java.util.Comparator;
import java.util.List;

import helper.Constants;

public class CamelCardComparatorWithJoker extends AbstractCamelCardComparator implements Comparator<String> {

    @Override
    public int compare(String hand1String, String hand2String) {
        CardType hand1CardType = getHand(hand1String);
        CardType hand2CardType = getHand(hand2String);
        if (hand1CardType.getValue() == hand2CardType.getValue()) {
            String[] hand1Cards = hand1String.split(Constants.NONE);
            String[] hand2Cards = hand2String.split(Constants.NONE);
            for (int i = 0; i < hand1Cards.length; i++) {
                if (CardWithJoker.valueByName(hand1Cards[i]) > CardWithJoker.valueByName(hand2Cards[i])) {
                    return 1;
                } else if ((CardWithJoker.valueByName(hand1Cards[i]) < CardWithJoker.valueByName(hand2Cards[i]))) {
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

    private CardType getHand(String handWithJoker) {
        CardType cardType = CardType.HIGH_CARD;
        List<String> cardNames = CardWithJoker.getNames();

        for (String cardName : cardNames) {
            String hand = handWithJoker.replace(CardWithJoker.JOKER.getName(), cardName);
            cardType = getCardType(hand, cardName, cardType);
        }

        return cardType;
    }

}
