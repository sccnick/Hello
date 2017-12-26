package com.example.nick.hello.manager;

import android.content.Context;

import com.example.nick.hello.dao.DockerContainerCollectionDao;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerManager {
    private static DockerContainerManager instance;
    private DockerContainerCollectionDao dao;

    public static DockerContainerManager getInstance() {
        if (instance == null)
            instance = new DockerContainerManager();
        return instance;
    }

    private Context mContext;

    private DockerContainerManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public DockerContainerCollectionDao getDao() {
        return dao;
    }

    public void setDao(DockerContainerCollectionDao dao) {
        this.dao = dao;
    }
}
