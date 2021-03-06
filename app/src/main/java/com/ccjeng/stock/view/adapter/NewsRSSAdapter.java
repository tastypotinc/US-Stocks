package com.ccjeng.stock.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccjeng.stock.R;
import com.ccjeng.stock.model.rss.RSSFeed;
import com.ccjeng.stock.view.activity.NewsActivity;

public class NewsRSSAdapter extends RecyclerView.Adapter<NewsRSSAdapter.CustomViewHolder> {
	private static final String TAG = "NewsListAdapter";
	private RSSFeed items;
	private Context context;
	
	public NewsRSSAdapter(Context context) {
		  this.context = context;
	 }

	public void setData(RSSFeed list){
		this.items = list;
	}

	@Override
	public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rsslist_item, parent, false);

		CustomViewHolder viewHolder = new CustomViewHolder(view);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

		//Setting text view title
		customViewHolder.titleView.setText(Html.fromHtml(items.getItem(i).getTitle()));

		if (items.getItem(i).getPubDate().length()==0) {
			customViewHolder.dateView.setVisibility(View.GONE);
		} else {
			customViewHolder.dateView.setText(items.getItem(i).getPubDate());
		}

		if (items.getItem(i).getDescription().length()==0) {
			customViewHolder.descrView.setVisibility(View.GONE);
		} else {
			customViewHolder.descrView.setText(Html.fromHtml(items.getItem(i).getDescription()));
		}

		customViewHolder.imageView.setVisibility(View.GONE);
		/*
		if (items.getItem(i).getImg().length()==0) {
			customViewHolder.imageView.setVisibility(View.GONE);
		} else {
			//Load image
			Picasso.with(context)
					.load(items.getItem(i).getImg())
					.resize(72,72)
					.centerInside()
					.into(customViewHolder.imageView);
		}*/
	}

	@Override
	public int getItemCount() {
		return (null != items ? items.getItemCount() : 0);
	}

	public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		protected ImageView imageView;
		protected TextView titleView;
		protected TextView dateView;
		protected TextView descrView;

		public CustomViewHolder(View view) {
			super(view);
			this.imageView = (ImageView) view.findViewById(R.id.image);
			this.titleView = (TextView) view.findViewById(R.id.title);
			this.dateView = (TextView) view.findViewById(R.id.date);
			this.descrView = (TextView) view.findViewById(R.id.descr);
            view.setOnClickListener(this);
		}

        // Handles the row item being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
          //  context.showNewsDetail(position, items);

			Intent intent = new Intent();
			intent.setClass(context, NewsActivity.class);

			Bundle bundle = new Bundle();
			bundle.putString("newsUrl", items.getItem(position).getLink());
			bundle.putString("newsTitle", items.getItem(position).getTitle());

			intent.putExtras(bundle);
			context.startActivity(intent);
        }
	}

}