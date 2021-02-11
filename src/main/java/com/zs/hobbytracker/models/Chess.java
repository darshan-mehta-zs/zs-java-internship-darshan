package com.zs.hobbytracker.models;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Following class represents Chess hobby
 */
public class Chess extends HobbyAttributes {

    /**
     * number of moves in a chess match
     */
    private int numberOfMoves;

    /**
     * result of chess match i.e. won/loss/draw
     */
    private String result;

    public Chess() {

    }

    public Chess(int userId, int hobbyId, boolean isTaskCompleted, Time startTime, Time endTime, Date dateLastPlayed, String hobbyName, int numberOfMoves, String result) {
        super(userId, hobbyId, isTaskCompleted, startTime, endTime, dateLastPlayed, hobbyName);
        this.numberOfMoves = numberOfMoves;
        this.result = result;
    }

    /**
     * getter method to get number of moves in a chess match
     *
     * @return number of moves in a chess match
     */
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /**
     * setter method to set number of moves in a chess match
     *
     * @param numberOfMoves in a chess match
     */
    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    /**
     * getter method to get the result of chess match
     *
     * @return result of chess match
     */
    public String getResult() {
        return result;
    }

    /**
     * setter method to set result of chess match
     *
     * @param result result of chess match
     */
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return super.toString() + "Chess{" +
                "numberOfMoves=" + numberOfMoves +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chess chess = (Chess) o;
        return numberOfMoves == chess.numberOfMoves && Objects.equals(result, chess.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfMoves, result);
    }
}
