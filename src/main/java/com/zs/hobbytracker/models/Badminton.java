package com.zs.hobbytracker.models;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Following class represents badminton hobby
 */
public class Badminton extends HobbyAttributes {

    /**
     * number of players a user is playing with
     */
    private int numberOfPlayers;
    /**
     * result of badminton match
     */
    private String result;

    /**
     * @return number of players a user is playing badminton with
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String toString() {
        return super.toString() + "Badminton{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", result='" + result + '\'' +
                '}';
    }

    /**
     * Setter method to set number of players in match
     *
     * @param numberOfPlayers accepts number of players a user is playing with
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Getter method to get the result of match
     *
     * @return result of match i.e. won/loss/draw
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter method to set result of chess match
     *
     * @param result accepts result of badminton match
     */
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Badminton badminton = (Badminton) o;
        return numberOfPlayers == badminton.numberOfPlayers && Objects.equals(result, badminton.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPlayers, result);
    }

    /**
     * No arguments constructor`
     */
    public Badminton() {

    }

    /**
     * Constructor for initialising fields
     *
     * @param userId          accepts id of user
     * @param hobbyId         accepts id of hobby
     * @param isTaskCompleted accepts if task is completed
     * @param startTime       accepts start time of hobby
     * @param endTime         accepts end time of hobby
     * @param dateLastPlayed  accepts when the hobby was last performed
     * @param hobbyName       accepts name of hobby
     * @param numberOfPlayers accepts number of players
     * @param result          accepts result of hobby performed
     */
    public Badminton(int userId, int hobbyId, boolean isTaskCompleted, Time startTime, Time endTime, Date dateLastPlayed, String hobbyName, int numberOfPlayers, String result) {
        super(userId, hobbyId, isTaskCompleted, startTime, endTime, dateLastPlayed, hobbyName);
        this.numberOfPlayers = numberOfPlayers;
        this.result = result;
    }
}
