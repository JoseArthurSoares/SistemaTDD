package main;

public class IdGenerator {
    private static long currentId = 0L;

    public static synchronized long getNextId() {
        return ++currentId;
    }
}
