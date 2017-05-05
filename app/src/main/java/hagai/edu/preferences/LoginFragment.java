package hagai.edu.preferences;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends DialogFragment implements View.OnClickListener {
    EditText etEmail , etPassword;
    Button btnLogin ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        //inform the listener about the user name...logged in
        listener.onLogin(email, true);
        dismiss();

    }
    //we need a listener
    private OnLogingListener listener;

    public  interface  OnLogingListener{
        void  onLogin (String name , boolean isLoggedIn);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  OnLogingListener) {

            listener = (OnLogingListener) context;
        }else {
            throw new RuntimeException(context+ "Must Implement" +OnLogingListener.class.getSimpleName());

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}


