package main.java.com.zs.hobbytracker.service;

import main.java.com.zs.hobbytracker.exception.InvalidInputException;
import main.java.com.zs.hobbytracker.models.Badminton;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Connection;
import java.sql.Date;

public interface ServiceI {

    public void tick(Connection connection, HobbyAttributes hobbyAttributes) throws InvalidInputException;

    public int getLongestStreak(Connection connection, int userId);

    public int getLatestStreak(Connection connection, int userId);

    public HobbyAttributes lastTick(Connection connection, int userId);

    public HobbyAttributes detailsForDate(Connection connection, int userId, Date date);

}
