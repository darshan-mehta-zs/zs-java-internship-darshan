package main.java.com.zs.hobbytracker.models;

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


}
