package com.me.gamefinder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.me.model.Game;
import com.me.gamefinder.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

	private Activity context;
	private ArrayList<Game> games;
	private LayoutInflater inflater;
	
	public GameAdapter() {}
	
	public GameAdapter(Activity context, ArrayList<Game> games)
	{
		this.context = context;
		this.setGames(games);
		this.inflater = (LayoutInflater)this.context.getLayoutInflater();
	}
	
	@Override
	public int getCount() {
		return getGames().size();
	}

	@Override
	public Object getItem(int position) {
		return getGames().get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
	
	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.game_list_element, parent, false);
			ViewHolder vh = new ViewHolder();
			vh.imageView = (ImageView)convertView.findViewById(R.id.gameImageView1);
			vh.textView1 = (TextView)convertView.findViewById(R.id.gameTextView1);
			vh.textView2 = (TextView)convertView.findViewById(R.id.gameTextView2);
			vh.textView3 = (TextView)convertView.findViewById(R.id.gameTextView3);
			
			convertView.setTag(vh);
		}
		
		ViewHolder vh2 = (ViewHolder)convertView.getTag();		
		
		InputStream is = null;
		try {
			is = context.openFileInput(getGames().get(position).getPicture() + ".png");
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			vh2.imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		vh2.textView1.setText(getGames().get(position).getName());
		vh2.textView2.setText(getGames().get(position).getPublisher());
		vh2.textView3.setText(getGames().get(position).getPlatform());
		
		return convertView;
	}

	class ViewHolder
	{
		ImageView imageView;
		TextView textView1;
		TextView textView2;
		TextView textView3;
	}
}
