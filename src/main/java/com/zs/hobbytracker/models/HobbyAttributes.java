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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(int hobbyId) {
        this.hobbyId = hobbyId;
    }

    public Date getDateLastPlayed() {
        return dateLastPlayed;
    }

    public void setDateLastPlayed(Date dateLastPlayed) {
        this.dateLastPlayed = dateLastPlayed;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

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

    public HobbyAttributes(int userId, int hobbyId, boolean isTaskCompleted, Time startTime, Time endTime, Date dateLastPlayed, String hobbyName) {
        this.userId = userId;
        this.hobbyId = hobbyId;
        this.isTaskCompleted = isTaskCompleted;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateLastPlayed = dateLastPlayed;
        this.hobbyName = hobbyName;
    }

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
}
