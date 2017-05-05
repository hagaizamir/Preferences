package hagai.edu.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLogingListener, TextWatcher {
    FloatingActionButton fab;
    Toolbar toolbar;
    EditText etNote;
    SharedPreferences prefs;

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ness " , " onPause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ness " , " onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ness " , " onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ness " , " onStart");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score" , score);
        Log.d("Ness " , " onSaveInstanceState");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Ness " , " onCreate");

        if (savedInstanceState==null){
            //the real start
            //getSupportFragmentManager().beginTransaction().replace(.....new LoginFragment())
//            score = savedInstanceState.getInt("score");
        }

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etNote = (EditText) findViewById(R.id.etNote);
        etNote.addTextChangedListener(this);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences("notes" , MODE_PRIVATE);

        load();





        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case  R.id.action_settings:
                return  true;
            case  R.id.action_login:
                new LoginFragment().show(getSupportFragmentManager(), "login");
                return true;
        }
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLogin(String name, boolean isLoggedIn) {
        if (isLoggedIn){
            Snackbar.make(fab , name + "Logged In" , Snackbar.LENGTH_INDEFINITE).setAction("OK!", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        save();


    }
    private void load (){
        String note = prefs.getString("Note", "");
        etNote.setText(note);

    }
    private void save (){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Note" , etNote.getText().toString());
        editor.apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    private  int score = 0;

    public void increment(View view) {
        score++;
        Toast.makeText(this, "Score" + score, Toast.LENGTH_SHORT).show();
    }



    //    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("score" , score);
//        Log.d("Ness " , " onSaveInstanceState");
//
//    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        score = savedInstanceState.getInt("score");
        Log.d("Ness " , " onRestoreInstanceState");
    }
}
