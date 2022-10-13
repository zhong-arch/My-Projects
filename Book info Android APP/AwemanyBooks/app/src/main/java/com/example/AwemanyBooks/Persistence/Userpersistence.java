package com.example.AwemanyBooks.Persistence;

public interface Userpersistence {

    boolean checkDuplicate(String username);
    void insertUser(String username,int hashedPassword);
    boolean deleteUser(String username);
    int changePassword(String username,int oldHashedPw,int newHashedPw);
    boolean matchRecord(String username, int hashedPw);
}
