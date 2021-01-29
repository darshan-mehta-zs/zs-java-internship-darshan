package com.zs.hobbytracker.dao;

import com.zs.hobbytracker.Hobby;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.*;
import java.util.List;

/**
 * Badminton dao to read or write from or to the database
 */
public class BadmintonDao {

    /**
     * store badminton hobby data to database
     *
     * @param connection accepts connection to database
     * @param badminton  accepts badminton object to be stored to database
     * @throws ApplicationRuntimeException
     */
    public void hobbyBadmintonTick(Connection connection, Badminton badminton) throws ApplicationRuntimeException {
        String query = "insert into hobby_badminton(hobby_id,user_id,start_time,end_time,date_last_played,number_of_players,result,is_task_completed) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, badminton.getHobbyId());
            statement.setInt(2, badminton.getUserId());
            statement.setTime(3, badminton.getStartTime());
            statement.setTime(4, badminton.getEndTime());
            statement.setDate(5, badminton.getDateLastPlayed());
            statement.setInt(6, badminton.getNumberOfPlayers());
            statement.setString(7, badminton.getResult());
            statement.setBoolean(8, badminton.isTaskCompleted());
            statement.executeUpdate();
            Hobby.cache.put(badminton.getUserId(), badminton);
            statement.close();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Sql Timeout or Sql Exception");
        }
    }

    /**
     * Fetches the badminton hobby data as a list for a user
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return the badminton hobby data as a List
     * @throws ApplicationRuntimeException
     */
    public List<HobbyAttributes> getBadmintonDataUserWise(Connection connection, int userId) throws ApplicationRuntimeException {
        String query = "select * from hobby_badminton where user_id=? and hobby_id=1 order by date_last_played";
        List<HobbyAttributes> hobbyAttributesList;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            hobbyAttributesList = new Badminton().convertToList(resultSet);
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Sql Timeout or Sql Exception");
        }
        return hobbyAttributesList;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Badminton object containing data of when was the last time user performed the hobby
     * @throws ApplicationRuntimeException
     */
    public Badminton lastTick(Connection connection, int userId) throws ApplicationRuntimeException {
        String query = "select start_time,end_time,date_last_played,number_of_players,result from hobby_badminton where user_id=? order by date_last_played desc limit 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            Badminton badminton = new Badminton();
            resultSet.next();
            badminton.setStartTime(resultSet.getTime("start_time"));
            badminton.setEndTime(resultSet.getTime("end_time"));
            badminton.setDateLastPlayed(resultSet.getDate("date_last_played"));
            badminton.setNumberOfPlayers(resultSet.getInt("number_of_players"));
            badminton.setResult(resultSet.getString("result"));
            statement.close();
            return badminton;
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Sql Timeout or Sql Exception");
        }
    }

    /**
     * Fetch details of badminton hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Badminton object containing data
     * @throws ApplicationRuntimeException
     */
    public Badminton detailsForDate(Connection connection, int userId, Date date) throws ApplicationRuntimeException {
        String query = "select start_time,end_time,date_last_played,number_of_players,result from hobby_badminton where user_id=? and date_last_played=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setDate(2, date);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            Badminton badminton = new Badminton();
            resultSet.next();
            badminton.setStartTime(resultSet.getTime("start_time"));
            badminton.setEndTime(resultSet.getTime("end_time"));
            badminton.setDateLastPlayed(resultSet.getDate("date_last_played"));
            badminton.setNumberOfPlayers(resultSet.getInt("number_of_players"));
            badminton.setResult(resultSet.getString("result"));
            return badminton;
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Sql Timeout or Sql Exception");
        }

    }
}
