package app.utils;

public class ApiProps {

    // == HIBERNATE CONFIG FILE ==
    public static final String DB_NAME = "trips";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "postgres";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;

    // == API CONFIG ==
    public static final int PORT = 7070;
    public static final String API_CONTEXT = "/api";
}
