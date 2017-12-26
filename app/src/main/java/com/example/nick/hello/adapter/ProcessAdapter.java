package com.example.nick.hello.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nick.hello.dao.DockerContainerProcessDao;
import com.example.nick.hello.manager.DockerContainerProcessManager;
import com.example.nick.hello.view.ViewProcessListItem;

/**
 * Created by Nick on 13/12/2560.
 */

public class ProcessAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        if(DockerContainerProcessManager.getInstance().getDao() == null){
            return 0;
        }
        if(DockerContainerProcessManager.getInstance().getDao().getHost() == null){
            return 0;
        }
        return DockerContainerProcessManager.getInstance().getDao().getHost().size();
    }

    @Override
    public Object getItem(int position) {
        return DockerContainerProcessManager.getInstance().getDao().getHost().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewProcessListItem item;
        if(convertView != null){
            item = (ViewProcessListItem) convertView;
        }
        else {
            item = new ViewProcessListItem(parent.getContext());
        }
        DockerContainerProcessDao dao = (DockerContainerProcessDao) getItem(position);
        item.setName(dao.getName().toString());
        item.setProcessId(dao.getProcessId().toString());
        item.setMemUse(dao.getMemUse().toString());
        item.setCpuUse(dao.getCpuUse().toString());
        return item;
    }
}
