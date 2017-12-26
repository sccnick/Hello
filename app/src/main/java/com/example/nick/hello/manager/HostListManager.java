package com.example.nick.hello.manager;


import android.content.Context;

import com.example.nick.hello.dao.HostItemCollectionDao;

/**
 * Created by Nick on 7/12/2560.
 */

public class HostListManager {
    private static HostListManager instance;
    private HostItemCollectionDao dao;

    public static HostListManager getInstance() {
        if (instance == null)
            instance = new HostListManager();
        return instance;
    }

    private Context mContext;

    private HostListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public HostItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(HostItemCollectionDao dao) {
        this.dao = dao;
    }
}
