package com.example.nick.hello;

import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.nick.hello.dao.SignUpCheckDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPass;
    private EditText etRepass;
    private Button btnCreate;

    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUser = (EditText) findViewById(R.id.edt_user);
        etPass = (EditText) findViewById(R.id.edt_pass);
        etRepass = (EditText) findViewById(R.id.edt_re_pass);
        btnCreate = (Button) findViewById(R.id.btn_sign_up);

        init();
    }

    public void init(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupfragment();
            }
        });
    }

    public void signupfragment() {
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();
        String repass = etRepass.getText().toString();

//                final PregressDialog progressDialog
//                progressDialog = new ProgressDialog(SignupActivity.this);
//                progressDialog.setIndeterminate(true);
//                progressDialog.setTitle("Loading...");
//                progressDialog.show();

        if(!username.isEmpty() && !password.isEmpty() ){
            if (!password.equals(repass)){
//                        progressDialog.dismiss();
                Toast.makeText(getBaseContext(),"Wrong password!", Toast.LENGTH_SHORT).show();

            }
            else {
//                APIService mApiservice = this.getInterfaceService();
                Call<SignUpCheckDao>call = Httpmanager.getInstance().getService().Signup(username, password);
                call.enqueue(new Callback<SignUpCheckDao>() {
                    @Override
                    public void onResponse(Call<SignUpCheckDao> call, Response<SignUpCheckDao> response) {

                        SignUpCheckDao dao = response.body();
                        if (dao.getStatus()){
                            Toast.makeText(getBaseContext(), "register Success!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getBaseContext(), "This username already existed!", Toast.LENGTH_SHORT).show();

                        }

                        // redirect to Main Activity page
//                                    progressDialog.dismiss();
//                        Toast.makeText(getBaseContext(), "register Success!!", Toast.LENGTH_SHORT).show();
//                        finish();

                    }

                    @Override
                    public void onFailure(Call<SignUpCheckDao> call, Throwable t) {
//                                progressDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        else {
            finish();
        }

    }

//    private APIService getInterfaceService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.103:81")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        final APIService mInterfaceService = retrofit.create(APIService.class);
//        return mInterfaceService;
//    }

}




