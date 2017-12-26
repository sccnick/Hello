package com.example.nick.hello.manager;

import android.content.Context;

import com.example.nick.hello.dao.DockerContainerProcessCollectionDao;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerProcessManager {
    private static DockerContainerProcessManager instance;
    private DockerContainerProcessCollectionDao dao;

    public static DockerContainerProcessManager getInstance() {
        if (instance == null)
            instance = new DockerContainerProcessManager();
        return instance;
    }

    private Context mContext;

    private DockerContainerProcessManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public DockerContainerProcessCollectionDao getDao() {
        return dao;
    }

    public void setDao(DockerContainerProcessCollectionDao dao) {
        this.dao = dao;
    }
}
