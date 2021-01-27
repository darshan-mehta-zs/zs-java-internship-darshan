package com.zs.hobbytracker.models;

public class Chess extends HobbyAttributes {

    /**
     * number of moves in a chess match
     */
    private int numberOfMoves;

    /**
     * result of chess match i.e. won/loss/draw
     */
    private String result;

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


}
