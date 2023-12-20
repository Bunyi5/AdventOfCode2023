package result;

import java.math.BigInteger;

import lombok.Getter;

@Getter
public enum Answers {
    TASK1_1_TEST (142),
    TASK1_1_REAL (54968),
    TASK1_2_TEST (281),
    TASK1_2_REAL (54094),

    TASK2_1_TEST (8),
    TASK2_1_REAL (2076),
    TASK2_2_TEST (2286),
    TASK2_2_REAL (70950),

    TASK3_1_TEST (4361),
    TASK3_1_REAL (546563),
    TASK3_2_TEST (0),
    TASK3_2_REAL (0),

    TASK4_1_TEST (13),
    TASK4_1_REAL (23673),
    TASK4_2_TEST (30),
    TASK4_2_REAL (12263631),

    TASK5_1_TEST (new BigInteger("35")),
    TASK5_1_REAL (new BigInteger("313045984")),
    TASK5_2_TEST (new BigInteger("46")),
    TASK5_2_REAL (0),

    TASK6_1_TEST (new BigInteger("288")),
    TASK6_1_REAL (new BigInteger("2374848")),
    TASK6_2_TEST (new BigInteger("71503")),
    TASK6_2_REAL (new BigInteger("39132886")),

    TASK7_1_TEST (6440),
    TASK7_1_REAL (253866470),
    TASK7_2_TEST (5905),
    TASK7_2_REAL (254494947),

    TASK8_1_TEST (2),
    TASK8_1_REAL (0),
    TASK8_2_TEST (0),
    TASK8_2_REAL (0);

    private final Object answer;

    Answers(Object answer) {
        this.answer = answer;
    }

}
