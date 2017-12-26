package com.example.nick.hello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nick.hello.dao.HostItemCollectionDao;
import com.example.nick.hello.manager.Contextor;
import com.example.nick.hello.manager.HostListManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class LoginActivity extends AppCompatActivity {

    public TextView tvCreate;
    public Button btnLogin;
    public EditText etUser;
    public EditText etPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = (EditText)findViewById(R.id.edt_user);
        etPass =(EditText)findViewById(R.id.edt_pass);
        tvCreate = (TextView)findViewById(R.id.tv_create);
        btnLogin = (Button)findViewById(R.id.btn_login);
        init();

    }

    public void init(){
        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();
        Call<HostItemCollectionDao> call = Httpmanager.getInstance().getService().UserLogin(username, password);
        call.enqueue(new Callback<HostItemCollectionDao>() {
            @Override
            public void onResponse(Call<HostItemCollectionDao> call, Response<HostItemCollectionDao> response) {
                if(response.isSuccessful()){
                    HostItemCollectionDao dao = response.body();
                    if(dao.getStatus()){
                        HostListManager.getInstance().setDao(dao);
                        Toast.makeText(getBaseContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), HostActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(getBaseContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getBaseContext(),"HTTP NOT FOUND",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<HostItemCollectionDao> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Connection Problem",Toast.LENGTH_SHORT).show();

            }
        });
//        Toast.makeText(getBaseContext(),"Login Success",Toast.LENGTH_SHORT).show();
    }

    public void create(){
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
    }

}
