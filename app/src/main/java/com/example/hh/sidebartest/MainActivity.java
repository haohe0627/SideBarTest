package com.example.hh.sidebartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  coder: haohe
 *  relay on : http://blog.csdn.net/xiaanming/article/details/12684155
 */

public class MainActivity extends AppCompatActivity {

    private SideBar sideBar;
    private ListView sortListView;

    private SortAdapter adapter;
    private CharacterParser characterParser;
    private List<SortModel> sortModellist;
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        characterParser = CharacterParser.getInstance();
        pinyinComparator= new PinyinComparator();

        sideBar = (SideBar)findViewById(R.id.sidebar);
        sortListView = (ListView)findViewById(R.id.listview);

        sideBar.setTouchLetterListener(new SideBar.OnTouchingLetterListener() {
            @Override
            public void onLetterChanged(String s) {
                int postion = adapter.getPositionForSection(s.charAt(0));
                if(postion != -1){
                    sortListView.setSelection(postion);
                }
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        sortModellist = getData(getResources().getStringArray(R.array.data));
        Collections.sort(sortModellist, pinyinComparator);
        sortListView.setAdapter(adapter = new SortAdapter(sortModellist, this));
    }


    private List<SortModel> getData(String []data){
        List<SortModel>sortList = new ArrayList<>();
        SortModel sortModel;
        for(int i = 0; i<data.length; i++){
            sortModel = new SortModel();

            sortModel.setName(data[i]);
            String sortString  = characterParser.getSelling(data[i]).substring(0,1).toUpperCase(); // 将字符转为拼音且截取首字母

            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            sortList.add(sortModel);
        }
        return  sortList;
    }

   

}
