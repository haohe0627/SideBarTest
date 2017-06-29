package com.example.hh.sidebartest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haohe on 2017/4/19 0019.
 */

public class SortAdapter extends BaseAdapter implements SectionIndexer{

    private List<SortModel> list = null;
    private Context context;

    public SortAdapter(List<SortModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void updateList(List<SortModel>list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        SortModel sortModel = list.get(position);
        if(convertView == null){

            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.tvLetter = (TextView)convertView.findViewById(R.id.catalog);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        //根据position获取分类的首字母的 char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的char的位置，则认为是第一次出现
        int c = getPositionForSection(section);
        if(position == getPositionForSection(section)){

            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(sortModel.getSortLetters());
        }else{
            holder.tvLetter.setVisibility(View.GONE);
        }

        holder.tvTitle.setText(list.get(position).getName());

        return convertView;
    }

    class ViewHolder{
        TextView tvLetter, tvTitle;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    // 根据分类的首字母的Char ascii值（索引号）获取其第一次出现该首字母的位置
    // 根据分类列的索引号获得该序列的首个位置
    @Override
    public int getPositionForSection(int sectionIndex) {

        for(int i = 0; i<getCount(); i++){ // 数组个数
            String sortStr = list.get(i).getSortLetters(); // 第 i 条数据的首字母
            char firstChar = sortStr.toUpperCase().charAt(0); // 该字符的char值
            if(firstChar == sectionIndex){ // 如果该char值等于

                return i;
            }
        }
        return -1;
    }

    // 根据ListView的当前位置获取分类的首字母的char ascii值（索引号）
    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }
}
