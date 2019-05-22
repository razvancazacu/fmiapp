package com.mds.DataBaseConnection;

public interface DatabaseServices {

    String insertTable = "INSERT INTO `users`\n" +
            "(`username`,\n" +
            "`email`,\n" +
            "`password`,\n" +
            "`create_time`)\n" +
            "VALUES\n" +
            "(?,\n" +
            "?,\n" +
            "?,\n" +
            "?);";
    String deleteUser = "DELETE FROM `users` WHERE `username` = ?;";

}
