package com.xingyi.caiyuan.view.society.linkman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LinkmanFrag extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listHeader;
    HashMap<String, List<ItemPojo>> listItems;
    SwipeRefreshLayout swipeRefreshLayout;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.society_linkman_layout, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.list_view);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listHeader, listItems);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setGroupIndicator(null);
        // 下面一句是让第0个头目录在打开界面是就展开的语句
//        expListView.expandGroup(0);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on society_message_expnd_item click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int itemPosition, long id) {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        listHeader.get(groupPosition)
                                + " : "
                                + listItems.get(
                                listHeader.get(groupPosition)).get(
                                itemPosition).getTitle(), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.linkman_srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /*
    * Preparing the list data
    */
    private void prepareListData() {

        listHeader = new ArrayList<String>();
        listItems = new HashMap<String, List<ItemPojo>>();

        //添加头目录
        listHeader.add("好友");
        listHeader.add("群聊");

        // Just a string which contains all alphabets and numbers - the random strings are generated from this string.


        //在“好友”头目录下添加子目录
        ArrayList<ItemPojo> itemPojoList = new ArrayList<>();

        // End of Random String Generation

        // Create POJO Object with random string and number.
        ItemPojo itemPojo = new ItemPojo("小明", "1");
        ItemPojo itemPojo2 = new ItemPojo("小蔡", "hhggg");


        itemPojoList.add(itemPojo);
        itemPojoList.add(itemPojo2);

        // Add list of above POJO's to a Map of type Map<String, ItemPojo>.
        listItems.put(listHeader.get(0), itemPojoList);

        //在“群聊”目录下添加子目录

        ArrayList<ItemPojo> itemPojoList2 = new ArrayList<>();

        // End of Random String Generation

        // Create POJO Object with random string and number.
        ItemPojo itemPojo3 = new ItemPojo("小王", "123");
        ItemPojo itemPojo4 = new ItemPojo("Mr.fox", "852");


        itemPojoList2.add(itemPojo3);
        itemPojoList2.add(itemPojo4);

        // Add list of above POJO's to a Map of type Map<String, ItemPojo>.
        listItems.put(listHeader.get(1), itemPojoList2);

    }

}