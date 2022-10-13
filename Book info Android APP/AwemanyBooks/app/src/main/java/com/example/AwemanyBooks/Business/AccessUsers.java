package com.example.AwemanyBooks.Business;


import android.util.Log;

import com.example.AwemanyBooks.Persistence.Userpersistence;
import com.example.AwemanyBooks.application.Service;
import com.example.AwemanyBooks.Object.User;

public class AccessUsers {
    private Userpersistence userpersistence;

    public AccessUsers(){ userpersistence=Service.getUserpersistence(); }

    public AccessUsers(final Userpersistence userpersistence) {
        this();
        this.userpersistence = userpersistence;
    }

    public boolean checkDuplicate(String name){
        boolean result=userpersistence.checkDuplicate(name);
        return result;
    }
    public boolean matchRecord(String uid, int hashedPw){
        boolean result=userpersistence.matchRecord(uid,hashedPw);
        return result;
    }
    public void register(String user,int hashedPw){
        Log.v("DBDEBUG","accessUser");
        userpersistence.insertUser(user,hashedPw);
    }
    public boolean removeUser(User u){
        return userpersistence.deleteUser(u.getUID());
    }

    public String changePassword(String username,int oldPw, int newPw){
        String result="";
        int code=userpersistence.changePassword(username,oldPw,newPw);
        if(code==0){
            result="Passowrd Change Success!";
        }
        else if(code==1){
            result="Old Password Is Wrong, Password Not Changed";
        }
        else{
            result="No Such User Exists";
        }

        return result;
    }

}

