package com.example.nick.hello.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nick.hello.dao.HostItemDao;
import com.example.nick.hello.manager.HostListManager;
import com.example.nick.hello.view.HostListItem;

/**
 * Created by Nick on 7/12/2560.
 */

public class HostListAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        if(HostListManager.getInstance().getDao() == null){
            return 0;
        }
        if(HostListManager.getInstance().getDao().getHost() == null){
            return 0;
        }
        return HostListManager.getInstance().getDao().getHost().size();
    }

    @Override
    public Object getItem(int position) {
        return HostListManager.getInstance().getDao().getHost().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HostListItem item;
        if(convertView != null){
            item = (HostListItem) convertView;
        }
        else {
            item = new HostListItem(parent.getContext());
        }
        HostItemDao dao = (HostItemDao) getItem(position);
        item.setNameText(dao.getHostname());
        item.setIpAddrText(dao.getHostUrl() + ":" + dao.getPort());
        return item;
    }
}
