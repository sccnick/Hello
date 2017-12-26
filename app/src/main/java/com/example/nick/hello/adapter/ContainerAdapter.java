package com.example.nick.hello.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nick.hello.R;
import com.example.nick.hello.dao.DockerContainerListDao;
import com.example.nick.hello.manager.DockerContainerManager;
import com.example.nick.hello.view.DockerContainerListItem;

/**
 * Created by Nick on 8/12/2560.
 */

public class ContainerAdapter extends BaseAdapter {

    public interface ContainerAdapterListener{
        void onControlClick(int position);
        void onRenameClick(int position);
    }

    private ContainerAdapterListener listener;

    public void setListener(ContainerAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if(DockerContainerManager.getInstance().getDao() == null)
            return 0;
        if(DockerContainerManager.getInstance().getDao().getHost() == null)
            return 0;
        return DockerContainerManager.getInstance().getDao().getHost().size();
    }

    @Override
    public Object getItem(int position) {
        return DockerContainerManager.getInstance().getDao().getHost().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DockerContainerListItem item;
        if(convertView != null){
            item = (DockerContainerListItem) convertView;
        }
        else {
            item = new DockerContainerListItem(parent.getContext());
        }
        DockerContainerListDao dao = (DockerContainerListDao) getItem(position);
        item.setName(dao.getName().toString());
        item.setImage(dao.getImage().toString());
        if(dao.getState().toString().equals("running")){
//            item.setBackgroundColor(Color.GREEN);
            item.setIvStatus(R.color.colorRunning);
        }
        else if(dao.getState().toString().equals("exited")){
//            item.setBackgroundColor(Color.RED);
            item.setIvStatus(R.color.colorExited);
        }
        else if(dao.getState().toString().equals("paused")){
//            item.setBackgroundColor(Color.YELLOW);
            item.setIvStatus(R.color.colorPause);
        }
//        item.setStatus(dao.getStatus().toString());

//        item.getIvControl().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toast.makeText(Contextor.getInstance().getContext(),"Control position : " + position, Toast.LENGTH_LONG).show();
//                if(listener != null) {
//                    listener.onControlClick(position);
//                }
//            }
//        });
//
//        item.getIvRename().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toast.makeText(Contextor.getInstance().getContext(),"Control position : " + position, Toast.LENGTH_LONG).show();
//                if(listener != null) {
//                    listener.onRenameClick(position);
//                }
//            }
//        });

        return item;
    }

}
