package tasks.task7;

import java.util.Comparator;
import java.util.List;

import helper.Constants;
import org.apache.commons.lang3.StringUtils;

public class CamelCardComparator implements Comparator<String> {

    @Override
    public int compare(String hand1String, String hand2String) {
        Hand hand1 = getHand(hand1String);
        Hand hand2 = getHand(hand2String);
        if (hand1.getCardType().getValue() == hand2.getCardType().getValue()) {
            String[] hand1Cards = hand1String.split(Constants.NONE);
            String[] hand2Cards = hand2String.split(Constants.NONE);
            for (int i = 0; i < hand1Cards.length; i++) {
                if (Card.valueByName(hand1Cards[i]) > Card.valueByName(hand2Cards[i])) {
                    return 1;
                } else if ((Card.valueByName(hand1Cards[i]) < Card.valueByName(hand2Cards[i]))) {
                    return -1;
                }
            }
        } else if (hand1.getCardType().getValue() > hand2.getCardType().getValue()) {
            return 1;
        } else {
            return -1;
        }
        return 0;
    }

    private Hand getHand(String hand) {
        CardType cardType = CardType.HIGH_CARD;
        List<String> cardNames = Card.getNames();

        for (String cardName : cardNames) {
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
        }

        return Hand.builder()
            .hand(hand)
            .cardType(cardType)
            .build();
    }

}
