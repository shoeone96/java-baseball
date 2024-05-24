package org.example.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallCounterTest {
    
    @Test
    @DisplayName("일치하는 숫자가 하나도 없는 경우 0 ball 0 strike가 담긴 BallStrikeCount를 반환한다.")
    void returnBallStrikeWithZeroStrikeZeroBall() {
        //given
        BaseBallCounter baseBallCounter = new BaseBallCounter();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorImpl();
        List<Integer> computerNumbers = randomNumberGenerator.getRandomNumbers();
        List<Integer> personInputList = getUserInput(computerNumbers, Collections.emptyList());

        //when
        BallStrikeCount ballStrikeCount = baseBallCounter.checkCount(computerNumbers,
            personInputList);

        //then
        assertThat(ballStrikeCount.ballCount()).isZero();
        assertThat(ballStrikeCount.strikeCount()).isZero();
    }

    @Test
    @DisplayName("ball 만 있을 경우 ball만 담긴 BallStrikeCount를 반환한다.")
    void returnBallStrikeWithBall() {
        //given
        BaseBallCounter baseBallCounter = new BaseBallCounter();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorImpl();
        List<Integer> computerNumbers = randomNumberGenerator.getRandomNumbers();
        List<Integer> personInputList = getUserInput(computerNumbers, List.of(computerNumbers.get(0)));

        //when
        BallStrikeCount ballStrikeCount = baseBallCounter.checkCount(computerNumbers,
            personInputList);

        //then

        if(personInputList.get(0).equals(computerNumbers.get(0))){
            assertThat(ballStrikeCount.strikeCount()).isOne();
        } else {
            assertThat(ballStrikeCount.ballCount()).isOne();
        }
    }


    private static List<Integer> getUserInput(List<Integer> randomNumbers, List<Integer> sameNumbers) {
        Set<Integer> personInput = new HashSet<>();
        if(!sameNumbers.isEmpty()){
            personInput.addAll(sameNumbers);
        }
        Random random = new Random();
        while(personInput.size() < 3){
            int randomNumber = random.nextInt(9) + 1;
            if(randomNumbers.contains(randomNumber)){
                continue;
            }
            personInput.add(randomNumber);
        }

        return personInput.stream().toList();
    }

}
