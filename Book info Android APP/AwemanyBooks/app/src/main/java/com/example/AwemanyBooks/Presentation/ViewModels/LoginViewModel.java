package com.example.AwemanyBooks.Presentation.ViewModels;


import androidx.lifecycle.ViewModel;

import com.example.AwemanyBooks.Business.AccessUsers;
import com.example.AwemanyBooks.application.Service;

public class LoginViewModel extends ViewModel {

    AccessUsers accessUsers;

    public LoginViewModel() {
        accessUsers = new AccessUsers();
    }

    public boolean login(String username, String password) {
        boolean result = accessUsers.matchRecord(username,password.hashCode());
        if(result){
            Service.setCurrentUser( username );
        }
        return result;
    }

    public void logout(){
        Service.setCurrentUser("local");
    }

    public int signUp(String username, String password){
        int result = -1;
        AccessUsers test=new AccessUsers();
        Boolean goodUserName=test.checkDuplicate(username);
        if(goodUserName && !username.equals("") && !password.equals("")){
            test.register(username,password.hashCode());
        }
        if( username.equals("") ){
            result = 2;//empty username
        }else if( password.equals("") ){
            result = 3;//empty password
        }else if( goodUserName ){
            result = 1;//worked username
        }else{
            result = 0;//duplicated username
        }
        return result;
    }
}