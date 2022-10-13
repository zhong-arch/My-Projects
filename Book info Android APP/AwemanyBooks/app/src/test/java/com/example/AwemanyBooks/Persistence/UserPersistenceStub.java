package com.example.AwemanyBooks.Persistence;

import java.util.LinkedList;

import com.example.AwemanyBooks.Object.User;

public class UserPersistenceStub implements Userpersistence{

    private LinkedList<User> usersList;

    public UserPersistenceStub() {
        this.usersList = new LinkedList<User>();

        User user = new User("user1","password1");
        usersList.add(user);

        user = new User("user2","password2");
        usersList.add(user);

        user = new User("user3","password3");
        usersList.add(user);
    }

    @Override
    public boolean checkDuplicate(String username) {
        boolean isDuplicate = true;

        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(username)){
                isDuplicate = false;
            }
        }

        return isDuplicate;
    }

    @Override
    public void insertUser(String username, int hashedPassword) {
        User user = new User(username,hashedPassword+"");
        usersList.add(user);
    }

    @Override
    public boolean deleteUser(String username) {
        boolean isDeleted = false;

        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(username)){
                usersList.remove(i);
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    @Override
    public int changePassword(String username, int oldHashedPw, int newHashedPw) {
        int result = 0;

        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(username)){
                result++;
                if(usersList.get(i).getPassword().equals(oldHashedPw+"")){
                    result++;
                    usersList.get(i).setPassword(newHashedPw+"");
                }
            }
        }

        return result;
    }

    @Override
    public boolean matchRecord(String username, int hashedPw) {
        boolean isMatching = false;

        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(username)){
                if(usersList.get(i).getPassword().equals(hashedPw+"")){
                    isMatching = true;
                }
            }
        }

        return isMatching;
    }
}
