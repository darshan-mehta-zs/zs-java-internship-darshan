package com.zs.hobbytracker.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * The following class contains common attributes for hobby and its getter and setter methods
 */
public abstract class HobbyAttributes {

    /**
     * Id of user
     */
    private int userId;

    /**
     * Id of hobby
     */
    private int hobbyId;

    /**
     * whether hobby performed or not
     */
    private boolean isTaskCompleted;

    /**
     * start time of hobby
     */
    private Time startTime;

    /**
     * end time of hobby
     */
    private Time endTime;

    /**
     * date hobby was last played
     */
    private Date dateLastPlayed;

    /**
     * Name of Hobby
     */
    private String hobbyName;

    /**
     * Fetches id of user
     *
     * @return id of user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the id of the user
     *
     * @param userId accepts id of user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Fetches id of hobby
     *
     * @return id of hobby
     */
    public int getHobbyId() {
        return hobbyId;
    }

    /**
     * Sets id of hobby
     *
     * @param hobbyId accepts id for hobby
     */
    public void setHobbyId(int hobbyId) {
        this.hobbyId = hobbyId;
    }

    /**
     * Fetches date when hobby was last performed
     *
     * @return date when hobby was last performed
     */
    public Date getDateLastPlayed() {
        return dateLastPlayed;
    }

    /**
     * Sets date when hobby was last performed
     *
     * @param dateLastPlayed accepts date when hobby was last performed
     */
    public void setDateLastPlayed(Date dateLastPlayed) {
        this.dateLastPlayed = dateLastPlayed;
    }

    /**
     * Sets name of hobby
     *
     * @param hobbyName accepts name of hobby
     */
    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    /**
     * Fetches name of hobby
     *
     * @return name of hobby
     */
    public String getHobbyName() {
        return hobbyName;
    }

    /**
     * Fetches if task is completed
     *
     * @return if hobby is completed
     */
    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    /**
     * Sets if task is completed
     *
     * @param taskCompleted accepts is task is completed
     */
    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    /**
     * Fetches start time of hobby
     *
     * @return start time of hobby
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Sets start time of hobby
     *
     * @param startTime accepts start time of hobby
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Fetches end time of hobby
     *
     * @return end time of hobby
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Sets end time of hobby
     *
     * @param endTime accepts end time for hobby
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "HobbyAttributes{" +
                "userId=" + userId +
                ", hobbyId=" + hobbyId +
                ", isTaskCompleted=" + isTaskCompleted +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dateLastPlayed=" + dateLastPlayed +
                ", hobbyName='" + hobbyName + '\'' +
                '}';
    }

    /**
     * Constructor to initialise the data members
     *
     * @param userId          id of user
     * @param hobbyId         id of hobby
     * @param isTaskCompleted if task is completed
     * @param startTime       start time of hobby
     * @param endTime         end time of hobby
     * @param dateLastPlayed  when hobby was last performed
     * @param hobbyName       name of hobby
     */
    public HobbyAttributes(int userId, int hobbyId, boolean isTaskCompleted, Time startTime, Time endTime, Date dateLastPlayed, String hobbyName) {
        this.userId = userId;
        this.hobbyId = hobbyId;
        this.isTaskCompleted = isTaskCompleted;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateLastPlayed = dateLastPlayed;
        this.hobbyName = hobbyName;
    }

    /**
     * No argument constructor
     */
    public HobbyAttributes() {

    }

    /**
     * Converts resultSet to ArrayList of HobbyAttributes
     *
     * @param resultSet accepts resultSet generated as result of query
     * @return List of HobbyAttributes
     * @throws SQLException
     */
    public static List<HobbyAttributes> convertToList(ResultSet resultSet) throws SQLException {
        List<HobbyAttributes> hobbyAttributesList = new ArrayList<>();
        while (resultSet.next()) {
            HobbyAttributes hobbyAttributes = new Badminton();
            hobbyAttributes.setHobbyId(resultSet.getInt("hobby_id"));
            hobbyAttributes.setUserId(resultSet.getInt("user_id"));
            hobbyAttributes.setStartTime(resultSet.getTime("start_time"));
            hobbyAttributes.setEndTime(resultSet.getTime("end_time"));
            hobbyAttributes.setDateLastPlayed(resultSet.getDate("date_last_played"));
            hobbyAttributes.setTaskCompleted(resultSet.getBoolean("is_task_completed"));
            hobbyAttributesList.add(hobbyAttributes);

        }
        return hobbyAttributesList;
    }

    /**
     * Calculates the longest streak for particular hobby
     *
     * @param hobbies list of hobbies
     * @return longest streak for hobby
     */
    public static int getLongestStreak(List<HobbyAttributes> hobbies) {
        if (hobbies.size() == 0)
            return 0;
        int longestStreak = 0;
        int currentLongestStreak = 1;
        for (int i = 1; i < hobbies.size(); i++) {
            if (hobbies.get(i).getDateLastPlayed().getDate() - hobbies.get(i - 1).getDateLastPlayed().getDate() == 1) {
                currentLongestStreak++;
            } else {
                longestStreak = Math.max(currentLongestStreak, longestStreak);
                currentLongestStreak = 1;
            }
        }
        return Math.max(currentLongestStreak, longestStreak);
    }

    /**
     * Calculate latest streak for hobby
     *
     * @param hobbies list of hobbies
     * @return longest streak for hobby
     */
    public static int getLatestStreak(List<HobbyAttributes> hobbies) {
        if (hobbies.size() == 0)
            return 0;
        int count = 1;
        for (int i = 1; i < hobbies.size(); i++) {
            if (hobbies.get(i).getDateLastPlayed().getDate() - hobbies.get(i - 1).getDateLastPlayed().getDate() == 1) {
                count++;
            } else {
                count = 1;
            }
        }
        return count;
    }

}
