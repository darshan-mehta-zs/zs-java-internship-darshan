package com.zs.hobbytracker.models;

/**
 * Following class represents User
 */
public class User {
    /**
     * Id of user
     */
    int id;

    /**
     * Name of user
     */
    String name;

    /**
     * Getter method to get Id of user
     *
     * @return id of user
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for setting id of user
     *
     * @param id accepts id of user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method to get name of user
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set name of user
     *
     * @param name accepts name of user
     */
    public void setName(String name) {
        this.name = name;
    }
}
