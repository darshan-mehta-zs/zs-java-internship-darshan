package main.java.com.zs.hobbytracker.service;

import main.java.com.zs.hobbytracker.dao.ChessDao;
import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Service for Chess hobby
 */
public class ChessService {

    ChessDao chessDao;

    /**
     * Constructor which injects chess dao as dependency
     */
    public ChessService() {
        chessDao = new ChessDao();
    }

    /**
     * calls dao to store chess hobby data to database
     *
     * @param connection accepts connection to database
     * @param Chess      accepts chess object to be stored to database
     */
    public void chessTick(Connection connection, Chess Chess) {
        chessDao.hobbyChessTick(connection, Chess);
    }

    /**
     * Finds longest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess hobby in number of days
     */
    public int getLongestStreak(Connection connection, int userId) {
        List<HobbyAttributes> hobbyAttributesList = chessDao.getChessDataUserWise(connection, userId);
        int longestStreak = 0;
        int currentLongestStreak = 0;
        for (HobbyAttributes hobbyAttributes : hobbyAttributesList) {
            if (hobbyAttributes.isTaskCompleted()) {
                currentLongestStreak++;
            } else {
                longestStreak = Math.max(currentLongestStreak, longestStreak);
                currentLongestStreak = 0;
            }
        }
        return Math.max(currentLongestStreak, longestStreak);
    }

    /**
     * Finds latest streak for chess hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of chess in number of days
     */
    public int getLatestStreak(Connection connection, int userId) {
        List<HobbyAttributes> hobbyAttributesList = chessDao.getChessDataUserWise(connection, userId);
        if (hobbyAttributesList.size() == 1 && hobbyAttributesList.get(0).isTaskCompleted())
            return 1;
        int count = 0;
        for (int i = 1; i < hobbyAttributesList.size(); i++) {
            if (hobbyAttributesList.get(i).getDateLastPlayed().getDate() - hobbyAttributesList.get(i - 1).getDateLastPlayed().getDate() == 1) {
                if (count == 0)
                    count++;
                count++;
            } else {
                count = 0;
            }
        }
        if (count == 0 && hobbyAttributesList.get(hobbyAttributesList.size() - 1).getDateLastPlayed().toString().equals(new Date(System.currentTimeMillis()).toString()))
            count++;
        if (!hobbyAttributesList.get(hobbyAttributesList.size() - 1).getDateLastPlayed().toString().equals(new Date(System.currentTimeMillis()).toString())) {
            return 0;
        }
        return count;
    }

    /**
     * Fetch details of when was the last time user performed the hobby
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return chess object containing data
     */
    public Chess lastTick(Connection connection, int userId) {
        return chessDao.lastTick(connection, userId);
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
        return chessDao.detailsForDate(connection, userId, date);
    }
}
