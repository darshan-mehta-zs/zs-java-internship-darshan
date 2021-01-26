package main.java.com.zs.hobbytracker.dao;

import main.java.com.zs.hobbytracker.Hobby;
import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Chess dao to read or write from or to the database
 */
public class ChessDao {

    /**
     * store chess hobby data to database
     *
     * @param connection accepts connection to database
     * @param chess      accepts chess object to be stored to database
     */
    public void hobbyChessTick(Connection connection, Chess chess) {
        String query = "insert into hobby_Chess(hobby_id,user_id,start_time,end_time,date_last_played,number_of_moves,result,is_task_completed) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, chess.getHobbyId());
            statement.setInt(2, chess.getUserId());
            statement.setTime(3, chess.getStartTime());
            statement.setTime(4, chess.getEndTime());
            statement.setDate(5, chess.getDateLastPlayed());
            statement.setInt(6, chess.getNumberOfMoves());
            statement.setString(7, chess.getResult());
            statement.setBoolean(8, chess.isTaskCompleted());
            statement.executeUpdate();
            Hobby.lruCache.put(chess.getUserId(), chess);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Fetches the chess hobby data as a list for a user
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return the chess hobby data as a List
     */
    public List<HobbyAttributes> getChessDataUserWise(Connection connection, int userId) {
        String query = "select * from hobby_chess where user_id=? and hobby_id=2 order by date_last_played";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<HobbyAttributes> hobbyAttributesList = HobbyAttributes.convertToList(resultSet);
            return hobbyAttributesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return Chess object containing data of when was the last time user performed the hobby
     */
    public Chess lastTick(Connection connection, int userId) {
        String query = "select * from hobby_chess where user_id=? order by date_last_played desc limit 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            Chess chess = new Chess();
            resultSet.next();
            chess.setHobbyId(resultSet.getInt("hobby_id"));
            chess.setUserId(resultSet.getInt("user_id"));
            chess.setStartTime(resultSet.getTime("start_time"));
            chess.setEndTime(resultSet.getTime("end_time"));
            chess.setDateLastPlayed(resultSet.getDate("date_last_played"));
            chess.setTaskCompleted(resultSet.getBoolean("is_task_completed"));
            chess.setNumberOfMoves(resultSet.getInt("number_of_moves"));
            chess.setResult(resultSet.getString("result"));
            return chess;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetch details of chess hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Chess object containing data
     */
    public Chess detailsForDate(Connection connection, int userId, Date date) {
        String query = "select * from hobby_chess where user_id=? and date_last_played=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setDate(2, date);
            ResultSet resultSet = statement.executeQuery();
            Chess chess = new Chess();
            resultSet.next();
            chess.setHobbyId(resultSet.getInt("hobby_id"));
            chess.setUserId(resultSet.getInt("user_id"));
            chess.setStartTime(resultSet.getTime("start_time"));
            chess.setEndTime(resultSet.getTime("end_time"));
            chess.setDateLastPlayed(resultSet.getDate("date_last_played"));
            chess.setTaskCompleted(resultSet.getBoolean("is_task_completed"));
            chess.setNumberOfMoves(resultSet.getInt("number_of_moves"));
            chess.setResult(resultSet.getString("result"));
            return chess;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
