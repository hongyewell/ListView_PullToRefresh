package Util;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hongyewell.pojo.Product;

public class WebUtil {
	
	public ArrayList<Product> sendGET(){		
		ArrayList<Product> tempList = new ArrayList<Product>();
		try{
			//String url = "http://miying.sinaapp.com/menu/";
			String url = "http://10.0.2.2:8080/Shopping/products";
			HttpGet request = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String result= EntityUtils.toString(response.getEntity());
				//JSONObject jsonobj = new JSONObject(result);
				JSONArray jsonArray = new JSONArray(result);
				
				for(int i=0; i<jsonArray.length(); i++){
					JSONObject jobj = (JSONObject) jsonArray.get(i);
					Product pro = new Product();
					pro.setId(Integer.parseInt(jobj.getString("id")));
					pro.setPrdname(jobj.getString("prdname"));
					pro.setCity(jobj.getString("city"));
					tempList.add(pro);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return tempList;
	}
}
