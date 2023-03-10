package com.techelevator.tenmo.model;

/**
 * This class is used to model a user's info. Specifically in the making of a list of users that is printed out when
 * sending money.
 *
 * @author Dustin
 */
public class UserInfoDto {
    long userId;
    String username;

    public UserInfoDto() {}

    /**
     * Constructs a user from the received info.
     *
     * @param id The user's id number.
     * @param username The user's name.
     */
    public UserInfoDto(long id, String username) {
        this.userId = userId;
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * An override of the to string method. Used to print a formatted line containing the user's info.
     *
     * @return A string containing a user's id number and name.
     */
    @Override
    public String toString() {
        String fString = String.format("| %-11d | %-25s |", getUserId(), getUsername());
        return  fString;
    }
}
