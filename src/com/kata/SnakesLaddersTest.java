package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakesLaddersTest {

        @Test
        public void exampleTests() {
            SnakesLadders game = new SnakesLadders();
            assertEquals("Player 1 is on square 38", game.play(1, 1));
            assertEquals("Player 1 is on square 44", game.play(1, 5));
            assertEquals("Player 2 is on square 31", game.play(6, 2));
            assertEquals("Player 1 is on square 25", game.play(1, 1));
        }

    @Test
    public void randomTests() {
        for (int runs = 1; runs < 6; runs++) {
            SnakesLadders  game     = new SnakesLadders();
            SnakesLadders2 solution = new SnakesLadders2();
            for (int moves = 1; moves < 51; moves++) {
                int x = (int)(Math.random() * 6) + 1,
                        y = (int)(Math.random() * 6) + 1;
                String expected = solution.play(x, y),
                        actual   = game.play(x, y);
                System.out.format("Game #%d move #%d {%d, %d}: %s\n",
                        runs, moves, x, y, actual);
                assertEquals(expected, actual);
                if ( actual.equals("Game over!") )
                    break;
            }
        }
    }

    private class SnakesLadders2 {
        public SnakesLadders2() {}
        public String play(int die1, int die2) {
            if ( won )
                return "Game over!";
            int roll = die1 + die2;
            if ( roll + playerSquare[player] <= 100 ) {
                playerSquare[player] += roll;
                if ( playerSquare[player] == 100 ) {
                    won = true;
                    return String.format("Player %d Wins!", player + 1);
                }
            }
            else
                playerSquare[player] = 100 - ((playerSquare[player] + roll) - 100);
            for (int i = 0; i < trap.length; i++)
                if ( playerSquare[player] == trap[i][0] )
                    playerSquare[player] = trap[i][1];
            String message = String.format("Player %d is on square %d", player + 1, playerSquare[player]);
            if ( die1 != die2 )
                player = 0 == player ? 1 : 0;
            return message;
        }
        private int[]         playerSquare = new int[]{0, 0};
        private int           player       = 0;
        private boolean       won          = false;
        private final int[][] trap         = new int[][]{
                new int[]{ 2,38}, new int[]{ 7,14}, new int[]{ 8,31},
                new int[]{15,26}, new int[]{21,42}, new int[]{28,84},
                new int[]{36,44}, new int[]{51,67}, new int[]{71,91},
                new int[]{78,98}, new int[]{87,94}, new int[]{16,6 },
                new int[]{46,25}, new int[]{49,11}, new int[]{62,19},
                new int[]{64,60}, new int[]{74,53}, new int[]{89,68},
                new int[]{92,88}, new int[]{95,75}, new int[]{99,80}};
    }


}
