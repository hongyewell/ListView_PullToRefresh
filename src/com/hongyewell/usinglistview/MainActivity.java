package com.hongyewell.usinglistview;

import java.util.ArrayList;
import Util.WebUtil;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongyewell.pojo.Product;
import com.hongyewell.pojo.ProductAdapter;


public class MainActivity extends Activity {
	
	private PullToRefreshListView lv;
	private ProductAdapter adapter;
	private ArrayList<Product> resList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
       adapter = new ProductAdapter(MainActivity.this, R.layout.product_item, resList);
       
       lv =  (PullToRefreshListView) findViewById(R.id.mylv);
       lv.setAdapter(adapter);
	   	new AsyncTask<Void, Void, Void>() {
	
			@Override
			protected Void doInBackground(Void... arg0) {
				try {
					Thread.sleep(1000);
					resList = new WebUtil().sendGET();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			//更新UI
			protected void onPostExecute(Void result) {
				adapter.addAll(resList.get(1));
				lv.onRefreshComplete();
			};
		}.execute();
		
		
       lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

		@Override
		public void onRefresh(PullToRefreshBase<ListView> refreshView) {
			
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... arg0) {
					try {
						Thread.sleep(1000);
						resList = new WebUtil().sendGET();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				//更新UI
				protected void onPostExecute(Void result) {
					adapter.addAll(resList);
					lv.onRefreshComplete();
				};
			}.execute();			
		}
	});
       
    }
 
}
