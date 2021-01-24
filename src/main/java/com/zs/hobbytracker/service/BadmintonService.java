package main.java.com.zs.hobbytracker.service;

import main.java.com.zs.hobbytracker.dao.BadmintonDao;
import main.java.com.zs.hobbytracker.models.Badminton;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Service for Badminton hobby
 */
public class BadmintonService {

    BadmintonDao badmintonDao;

    /**
     * Constructor which injects dao as dependency
     */
    public BadmintonService() {
        badmintonDao = new BadmintonDao();
    }

    /**
     * calls dao to store badminton hobby data to database
     *
     * @param connection accepts connection to database
     * @param badminton  accepts badminton object to be stored to database
     */
    public void badmintonTick(Connection connection, Badminton badminton) {
        badmintonDao.hobbyBadmintonTick(connection, badminton);
    }


    /**
     * Finds longest streak for badminton hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of badminton in number of days
     */
    public int getLongestStreak(Connection connection, int userId) {
        List<HobbyAttributes> hobbyAttributesList = badmintonDao.getBadmintonDataUserWise(connection, userId);
        int longestStreak = 0;
        int currentLongestStreak = 0;
        for (int i = 1; i < hobbyAttributesList.size(); i++) {
            if (hobbyAttributesList.get(i).getDateLastPlayed().getDate() - hobbyAttributesList.get(i - 1).getDateLastPlayed().getDate() == 1) {
                if (currentLongestStreak == 0)
                    currentLongestStreak++;
                currentLongestStreak++;
            } else {
                longestStreak = Math.max(currentLongestStreak, longestStreak);
                currentLongestStreak = 0;
            }
        }
        return Math.max(currentLongestStreak, longestStreak);
    }

    /**
     * Finds latest streak for badminton hobby in number of days
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @return An Integer value for longest streak of badminton in number of days
     */
    public int getLatestStreak(Connection connection, int userId) {
        List<HobbyAttributes> hobbyAttributesList = badmintonDao.getBadmintonDataUserWise(connection, userId);
        if (hobbyAttributesList.size() == 1 && hobbyAttributesList.get(hobbyAttributesList.size() - 1).getDateLastPlayed().toString().equals(new Date(System.currentTimeMillis()).toString()))
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
     * @return Badminton object containing data
     */
    public Badminton lastTick(Connection connection, int userId) {
        return badmintonDao.lastTick(connection, userId);
    }

    /**
     * Fetch details of badminton hobby performed by user on given date
     *
     * @param connection accepts connection to database
     * @param userId     accepts id of user
     * @param date       accepts date
     * @return Badminton object containing data
     */
    public Badminton detailsForDate(Connection connection, int userId, Date date) {
        return badmintonDao.detailsForDate(connection, userId, date);
    }
}
