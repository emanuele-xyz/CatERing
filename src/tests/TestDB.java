package tests;

import persistence.PersistenceManager;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("TEST DATABASE CONNECTION");
        PersistenceManager.testSQLConnection();
    }
}
