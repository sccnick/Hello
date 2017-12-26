package com.example.nick.hello;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.nick.hello.R;
import com.example.nick.hello.adapter.ContainerAdapter;
import com.example.nick.hello.dao.DockerContainerCollectionDao;
import com.example.nick.hello.dao.DockerContainerProcessCollectionDao;
import com.example.nick.hello.manager.DockerContainerManager;
import com.example.nick.hello.manager.DockerContainerProcessManager;
import com.example.nick.hello.manager.HostListManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {


    private String hostUrl;
    private String hostPort;
    private String dockerName;
    private int positionOfHost;
    private SwipeMenuCreator creator;
    private SwipeMenuListView listView;
    private ContainerAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LayoutInflater inflater;

    private String[] CONTROL = {"START", "STOP", "PAUSE", "UNPAUSE", "RESTART", "SET THESHOLD"};

    public ContainerFragment() {
        super();
        // Required empty public constructor
    }

    public static ContainerFragment newInstance(int who){
        ContainerFragment fragment = new ContainerFragment();
        Bundle args = new Bundle();
        args.putInt("who", who);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        positionOfHost = getArguments().getInt("who");
        hostUrl = HostListManager.getInstance().getDao().getHost().get(positionOfHost).getHostUrl().toString();
        hostPort = HostListManager.getInstance().getDao().getHost().get(positionOfHost).getPort().toString();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_container,container,false);
        initInstances(rootView,savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        listView = (SwipeMenuListView) rootView.findViewById(R.id.containernaja);
        listAdapter = new ContainerAdapter();
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                refreshWhenOptionOnSelectedSuccess(hostUrl, hostPort);
            }
        });
        listView.setOnItemClickListener(listViewItemClickListener);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                return;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            }
        });
//        refreshWhenOptionOnSelectedSuccess(hostUrl, hostPort);
//        listView.setOnItemClickListener(listViewItemClickListener);
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                return;
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    public void refreshWhenOptionOnSelectedSuccess(String url, String port) {
        Call<DockerContainerCollectionDao> refresh = Httpmanager.getInstance().getService().listDockerContainer(url, port);
        refresh.enqueue(new Callback<DockerContainerCollectionDao>() {
            @Override
            public void onResponse(Call<DockerContainerCollectionDao> call, Response<DockerContainerCollectionDao> response) {
//                DockerContainerCollectionDao dao = response.body();
                if (response.isSuccessful()) {
                    DockerContainerCollectionDao dao = response.body();
                    DockerContainerManager.getInstance().setDao(dao);
                    listAdapter.notifyDataSetChanged();
                }
//                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DockerContainerCollectionDao> call, Throwable t) {
                Toast.makeText(getActivity(), "Refresh fail.", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    final AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            dockerName = DockerContainerManager.getInstance().getDao().getHost().get(position).getName().toString();
            Call<DockerContainerProcessCollectionDao> call = Httpmanager.getInstance().getService().getProcess(hostUrl, hostPort, dockerName);
            call.enqueue(new Callback<DockerContainerProcessCollectionDao>() {
                @Override
                public void onResponse(Call<DockerContainerProcessCollectionDao> call, Response<DockerContainerProcessCollectionDao> response) {
                    DockerContainerProcessCollectionDao dao = response.body();
                    if (response.isSuccessful()) {
                        DockerContainerProcessManager.getInstance().setDao(dao);
                        startActivity(new Intent(getActivity(), ProcessActivity.class));
                    } else {
                        Toast.makeText(getActivity(),"This container is exited state.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DockerContainerProcessCollectionDao> call, Throwable t) {
//                    Toast(getContext().getResources().getString(R.string.onFailure));
                    Toast.makeText(getActivity(),"This container is exited state.",Toast.LENGTH_SHORT).show();

                }
            });

        }
    };
}
