package com.hongyewell.pojo;

import java.util.List;

import com.hongyewell.usinglistview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProductAdapter extends ArrayAdapter<Product> {
	private int resourceId;

	public ProductAdapter(Context context, int textViewResourceId, List<Product> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Product product = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView productIdTextView = (TextView) view.findViewById(R.id.product_id);
		TextView productNameTextView = (TextView) view.findViewById(R.id.product_prdname);
		TextView productCityTextView = (TextView) view.findViewById(R.id.product_city);
		productIdTextView.setText(product.getId());
		productNameTextView.setText(product.getPrdname());
		productCityTextView.setText(product.getCity());
		return view;
	}

}
