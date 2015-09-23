package com.hongyewell.usinglistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends Activity {
	
	private PullToRefreshListView lv;
	private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       lv =  (PullToRefreshListView) findViewById(R.id.mylv);
       List<String> arr = new ArrayList<String>();
       arr.add("geek_ymv");
       arr.add("hongyewell");
       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
       lv.setAdapter(adapter);
       lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

		@Override
		public void onRefresh(PullToRefreshBase<ListView> refreshView) {
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... arg0) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				//ÐÞ¸ÄUI
				protected void onPostExecute(Void result) {
					adapter.addAll("hello yingmidog", "hello hongyewell");
					lv.onRefreshComplete();
				};
			}.execute();

			
		}
	});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 
}
