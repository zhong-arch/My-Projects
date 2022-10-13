package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.ViewModels.LoginViewModel;
import com.example.AwemanyBooks.application.Service;

public class LoginFragment extends Fragment {

    private LoginFragmentListener listener;
    private LoginViewModel loginViewModel;
    private String uid;
    private boolean loginAttempt;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =
                ViewModelProviders.of(getActivity()).get(LoginViewModel.class);;
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        loginAttempt = false;
        uid = Service.getCurrentUser();
        updateView(root);
        initListeners(root);

        return root;
    }

    private void initListeners(final View view){
        Button loginButton = (Button) view.findViewById(R.id.button1);
        Button signInButton = (Button) view.findViewById(R.id.button4);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = ((EditText) getView().findViewById(R.id.editText2)).getText().toString();
                String password = ((EditText) getView().findViewById(R.id.editText3)).getText().toString();
                loginAttempt = loginViewModel.login(userName, password);
                if(loginAttempt){
                    uid = userName;
                }
                listener.onInputSent(userName);
                updateView(view);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt = false;
                String userName = ((EditText)getView().findViewById(R.id.editText2)).getText().toString();
                String password = ((EditText)getView().findViewById(R.id.editText3)).getText().toString();
                updateView(view);
                displayRegisterMessage(view, loginViewModel.signUp(userName,password));
            }
        });
        TextView loginMessage = (TextView) view.findViewById(R.id.loginMessage);
        loginMessage.setText("Welcome to Awemany, Please login or sign up");
    }

    private void updateView(View view){
        LinearLayout loginLayout = (LinearLayout) view.findViewById(R.id.not_logged);
        LinearLayout loginSucesss = (LinearLayout) view.findViewById(R.id.loginSuccess);
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView usernameWelcome = (TextView) view.findViewById(R.id.username_update);
        if (!uid.equals("local")){
            loginLayout.setVisibility(View.INVISIBLE);
            loginSucesss.setVisibility(View.VISIBLE);
            username.setText(uid);
            usernameWelcome.setText(uid);
        } else{
            loginLayout.setVisibility(View.VISIBLE);
            loginSucesss.setVisibility(View.INVISIBLE);
            displayRegisterMessage(view, -1);
            displayLoginMessage(view);
        }
    }

    private void displayRegisterMessage(View view, int signUpResult){
        TextView loginMessage = (TextView) view.findViewById(R.id.loginMessage);
        if(signUpResult == -1){
            loginMessage.setText("");

        }else if(signUpResult == 0){
            loginMessage.setText("Username Is Already Taken Please Try Again");
        }else if(signUpResult == 1){
            loginMessage.setText("Sign Up Successful Log In To Continue");
        }else if(signUpResult == 2){
            loginMessage.setText("Username Is Empty");
        }else{
            loginMessage.setText("Password Is Empty");
        }
        loginMessage.setVisibility(View.VISIBLE);

    }
    private void displayLoginMessage(View view){
        TextView loginMessage = (TextView) view.findViewById(R.id.loginMessage);
        if (loginAttempt == true){
            loginMessage.setText("Login Successful");
        }else{
            loginMessage.setText("Username or Password Incorrect Please Try Again");
        }
        loginMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener){
            listener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + " must implement fragmentListnener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}