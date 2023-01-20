package com.kata;

import java.util.HashMap;

// Using Java 17
public class SnakesLadders {
    private final int[] positionOfPlayer = new int[] {0, 0};
    private int currentPlayer = 0;
    private boolean isGameWon = false;

    private static final HashMap<Integer, Integer> teleportMap = new HashMap<>();
    static {
        teleportMap.put(2, 38);  teleportMap.put(36, 44); teleportMap.put(74, 53);
        teleportMap.put(7, 14);  teleportMap.put(46, 25); teleportMap.put(78, 98);
        teleportMap.put(8, 31);  teleportMap.put(49, 11); teleportMap.put(87, 94);
        teleportMap.put(15, 26); teleportMap.put(51, 67); teleportMap.put(89, 68);
        teleportMap.put(16, 6);  teleportMap.put(62, 19); teleportMap.put(92, 88);
        teleportMap.put(21, 42); teleportMap.put(64, 60); teleportMap.put(95, 75);
        teleportMap.put(28, 84); teleportMap.put(71, 91); teleportMap.put(99, 80);
    }

    public String play(int die1, int die2) {
        if (isGameWon) return "Game over!";
        movePlayer(currentPlayer, die1+die2);
        String result = isGameWon ? "Player "+(currentPlayer+1)+" Wins!"
                : "Player "+(currentPlayer+1)+" is on square "+positionOfPlayer[currentPlayer];
        if (die1 != die2) currentPlayer = currentPlayer==1 ? 0 : 1;
        return result;
    }

    private void movePlayer(int index, int dice) {
        int newPosition = positionOfPlayer[index]+dice;
        if (newPosition > 100) newPosition = 200 - newPosition;
        positionOfPlayer[index] = teleportMap.getOrDefault(newPosition, newPosition);
        if (positionOfPlayer[index] == 100) isGameWon = true;
    }
}