package com.example.nick.hello;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.nick.hello.adapter.HostListAdapter;
import com.example.nick.hello.dao.AddHostCheckDao;
import com.example.nick.hello.dao.DockerContainerCollectionDao;
import com.example.nick.hello.dao.HostItemCollectionDao;
import com.example.nick.hello.manager.DockerContainerManager;
import com.example.nick.hello.manager.HostListManager;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostFragment extends Fragment  {

    private LayoutInflater inflater;
    private SwipeMenuCreator creator;
    private SwipeMenuListView listView;
    private HostListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton floatingActionButton;


    public HostFragment() {
        super();
        // Required empty public constructor
    }

    public static HostFragment newInstance() {
        HostFragment fragment = new HostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }



    private void init(Bundle savedInstanceState) {
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    private void reloadData(){
        Call<HostItemCollectionDao> call = Httpmanager.getInstance().getService().UserLogin(HostListManager.getInstance().getDao().getUsername().toString()
        ,HostListManager.getInstance().getDao().getPassword().toString());
        call.enqueue(new Callback<HostItemCollectionDao>() {
            @Override
            public void onResponse(Call<HostItemCollectionDao> call, Response<HostItemCollectionDao> response) {
                swipeRefreshLayout.setRefreshing(false);
                HostItemCollectionDao dao = response.body();
                if(response.isSuccessful()){
                    HostListManager.getInstance().setDao(dao);
                }else {
                    HostListManager.getInstance().setDao(dao);
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HostItemCollectionDao> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Connection problem", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_host, container, false );
        initInstances(rootView, savedInstanceState);
        //return inflater.inflate(R.layout.fragment_host, container, false);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        listView = (SwipeMenuListView) rootView.findViewById(R.id.listView);
        listAdapter = new HostListAdapter();
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab_add_new_host);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

        listView.setOnItemClickListener(listViewItemClickListener);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflater = LayoutInflater.from(getActivity());
                final View addHostDialog = inflater.inflate(R.layout.dialog_addhost, null);
                final EditText editName = (EditText) addHostDialog.findViewById(R.id.edt_host_name);
                final EditText edtIp = (EditText) addHostDialog.findViewById(R.id.edt_host_ipaddr);
                final EditText edtPort = (EditText) addHostDialog.findViewById(R.id.edt_host_port);

                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setView(addHostDialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                            if (!edtIp.getText().toString().isEmpty() && !edtPort.getText().toString().isEmpty() && !editName.getText().toString().isEmpty()){
                                Call<AddHostCheckDao> call = Httpmanager.getInstance().getService().addDockerHost(edtIp.getText().toString()
                                , edtPort.getText().toString()
                                , editName.getText().toString()
                                , HostListManager.getInstance().getDao().getIds());
                                call.enqueue(new Callback<AddHostCheckDao>() {
                                    @Override
                                    public void onResponse(Call<AddHostCheckDao> call, Response<AddHostCheckDao> response) {
                                        AddHostCheckDao dao = response.body();
                                        if(dao.getStatus()){
                                            reloadData();
                                            Toast.makeText(getActivity(), "Host added successful", Toast.LENGTH_SHORT).show();

                                        }
                                        else {
                                            Toast.makeText(getActivity(), "This host is already existed.", Toast.LENGTH_SHORT).show();

                                        }
                                        return;
                                    }

                                    @Override
                                    public void onFailure(Call<AddHostCheckDao> call, Throwable t) {
                                        Toast.makeText(getActivity(), "EZ",Toast.LENGTH_SHORT).show();
//                                        reloadData();
                                        return;
                                    }
                                });
                        }

                    }
                }).setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    final AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


//            Call<DockerContainerPeakCollectionDao> callForPeak = HttpManager.getInstance().getServeice().getPeak(
//                    HostListManager.getInstance().getDao().getHost().get(position).getHostUrl().toString()
//                    , HostListManager.getInstance().getDao().getHost().get(position).getPort().toString());
//
//            callForPeak.enqueue(new Callback<DockerContainerPeakCollectionDao>() {
//                @Override
//                public void onResponse(Call<DockerContainerPeakCollectionDao> call, Response<DockerContainerPeakCollectionDao> response) {
//                    DockerContainerPeakCollectionDao dao = response.body();
//                    DockerContainerPeakManager.getInstance().setDao(dao);
//                }
//
//                @Override
//                public void onFailure(Call<DockerContainerPeakCollectionDao> call, Throwable t) {
////                    Toast.makeText(getActivity(), getContext().getResources().getString(R.string.onFailure),Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            Call<DockerContainerEachStatCollectionDao> callEach = HttpManager.getInstance().getServeice().getEachStat(
//                    HostListManager.getInstance().getDao().getHost().get(position).getHostUrl().toString()
//                    , HostListManager.getInstance().getDao().getHost().get(position).getPort().toString());
//
//            callEach.enqueue(new Callback<DockerContainerEachStatCollectionDao>() {
//                @Override
//                public void onResponse(Call<DockerContainerEachStatCollectionDao> call, Response<DockerContainerEachStatCollectionDao> response) {
//                    DockerContainerEachStatCollectionDao dao = response.body();
//                    DockerContainerEachStatManager.getInstance().setDao(dao);
//                }
//
//                @Override
//                public void onFailure(Call<DockerContainerEachStatCollectionDao> call, Throwable t) {
////                    Toast.makeText(getActivity(), getContext().getResources().getString(R.string.onFailure),Toast.LENGTH_SHORT).show();
//                }
//            });


            Call<DockerContainerCollectionDao> call = Httpmanager.getInstance().getService().listDockerContainer(
                    HostListManager.getInstance().getDao().getHost().get(position).getHostUrl().toString()
                    , HostListManager.getInstance().getDao().getHost().get(position).getPort().toString());
            call.enqueue(new Callback<DockerContainerCollectionDao>() {
                             @Override
                             public void onResponse(Call<DockerContainerCollectionDao> call, Response<DockerContainerCollectionDao> response) {
                                 DockerContainerCollectionDao dao = response.body();
                                 if (dao.getStatus()) {
                                     Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                     DockerContainerManager.getInstance().setDao(dao);
                                     Intent intent = new Intent(getActivity(),ContainerActivity.class);
                                     intent.putExtra("who", position);
                                     startActivity(intent);
                                     getActivity().finish();
                                 } else {
                                     Toast.makeText(getContext(), "No Docker Container in this host.", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Call<DockerContainerCollectionDao> call, Throwable t) {
                                 Toast.makeText(getContext(), "Connection problem", Toast.LENGTH_SHORT).show();
                             }
                         }
            );
        }
    };
}
