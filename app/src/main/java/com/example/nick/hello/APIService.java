package com.example.nick.hello;



import com.example.nick.hello.dao.AddHostCheckDao;
import com.example.nick.hello.dao.DockerContainerCollectionDao;
import com.example.nick.hello.dao.DockerContainerProcessCollectionDao;
import com.example.nick.hello.dao.HostItemCollectionDao;
import com.example.nick.hello.dao.SignUpCheckDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Nick on 9/11/2560.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("user_sign.py")
    Call<SignUpCheckDao>Signup(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login.py")
    Call<HostItemCollectionDao>UserLogin(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("addhost.py?")
    Call<AddHostCheckDao> addDockerHost(
            @Field("url") String url,
            @Field("port") String port,
            @Field("name") String name,
            @Field("ids") int ids
    );
    @FormUrlEncoded
    @POST("api_getcontainerall.py?")
    Call<DockerContainerCollectionDao> listDockerContainer(
            @Field("url") String url,
            @Field("port") String port
    );
    @FormUrlEncoded
    @POST("api_getprocess.py?")
    Call<DockerContainerProcessCollectionDao> getProcess(
            @Field("url") String url,
            @Field("port") String port,
            @Field("container") String container
    );
}
