package com.xingyi.caiyuan.view.society.linkman;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;

import java.util.HashMap;
import java.util.List;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaders; // List of Headers
    // List View content as a Map of Header with its Items
    private HashMap<String, List<ItemPojo>> listItems;

    public ExpandableListAdapter(Context context, List<String> listHeader,
                                 HashMap<String, List<ItemPojo>> listItems) {
        this.context = context;
        this.listHeaders = listHeader;
        this.listItems = listItems;
    }

    @Override
    public ItemPojo getChild(int groupPosition, int itemPosititon) {
        return this.listItems.get(this.listHeaders.get(groupPosition))
                .get(itemPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int item) {
        return item;
    }

    @Override
    public View getChildView(int groupPosition, final int itemPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String itemTitle = (String) getChild(groupPosition, itemPosition).getTitle();
        final String itemNum = (String) getChild(groupPosition, itemPosition).getNum();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.society_message_expnd_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(itemTitle);
        TextView num = (TextView) convertView.findViewById(R.id.number);
        num.setText(itemNum);




        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItems.get(this.listHeaders.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeaders.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listHeaders.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.society_massage_expand_header, null);
        }

        TextView header = (TextView) convertView.findViewById(R.id.header);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerTitle);
        TextView count = (TextView) convertView.findViewById(R.id.count);
        count.setTextColor(Color.DKGRAY);
        count.setText(getChildrenCount(groupPosition) +"");



        ImageView img= (ImageView) convertView.findViewById(R.id.img);
        img.setImageDrawable(convertView.getResources().getDrawable(R.drawable.spread_press));
        if(!isExpanded){
            img.setImageDrawable(convertView.getResources().getDrawable(R.drawable.spread_unpress));
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
