package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.AwemanyBooks.Business.AccessUsers;
import com.example.AwemanyBooks.Presentation.MainActivity;
import com.example.AwemanyBooks.Presentation.ViewModels.LoginViewModel;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.application.Service;

public class AccountFragment extends Fragment {

    private LoginFragmentListener listener;
    private LoginViewModel loginViewModel;
    private String uid;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =
                ViewModelProviders.of(getActivity()).get(LoginViewModel.class);;
        View root = inflater.inflate(R.layout.fragment_user_account, container, false);
        uid = Service.getCurrentUser();
        updateView(root, false);
        initListeners(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Service.getCurrentUser().equals("local")) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void initListeners(final View view){
        Button logOutButton = (Button) view.findViewById(R.id.button5);
        Button changePassword = (Button) view.findViewById(R.id.button1);
        Button cancel = (Button) view.findViewById(R.id.return_button);
        Button changePasswordSubmit = (Button) view.findViewById(R.id.change_password_submit);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logout();
                uid = "local";
                listener.onInputSent(uid);
                updateView(view, false);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView(view, true);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView(view, false);
            }
        });
        changePasswordSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = ((EditText) view.findViewById(R.id.newPass)).getText().toString();
                String oldPassword = ((EditText) view.findViewById(R.id.oldPass)).getText().toString();
                TextView changePasswordMessage = (TextView) view.findViewById(R.id.password_change_message);
                AccessUsers u = new AccessUsers();
                changePasswordMessage.setText(u.changePassword(uid, oldPassword.hashCode(), newPassword.hashCode()));
                resetText((EditText) view.findViewById(R.id.newPass));
                resetText((EditText) view.findViewById(R.id.oldPass));
            }
        });
    }

    private void updateView(View view, boolean checkPassword){
        LinearLayout accountPage = (LinearLayout) view.findViewById(R.id.logged);
        LinearLayout changePasswordPage = (LinearLayout) view.findViewById(R.id.change_password);
        TextView username = (TextView) view.findViewById(R.id.username);
        if (!uid.equals("local")) {
            username.setText(uid);
        }
        if(!checkPassword){
            accountPage.setVisibility(View.VISIBLE);
            changePasswordPage.setVisibility(View.INVISIBLE);
        }else{
            accountPage.setVisibility(View.INVISIBLE);
            changePasswordPage.setVisibility(View.VISIBLE);
        }
    }

    private void resetText(EditText toEdit){
        toEdit.setText("");
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
