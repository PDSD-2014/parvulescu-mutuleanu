package com.me.gamefinder;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.me.model.Game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedGameActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalied_game_activity);
		
		Intent intent = getIntent();
		Game game = (Game)intent.getSerializableExtra("detaliedGame");
		
		TextView tvTitlu = (TextView)findViewById(R.id.textView1);
		TextView tvPublisher = (TextView)findViewById(R.id.textView2);
		TextView tvPlatforma = (TextView)findViewById(R.id.textView3);
		TextView tvGen = (TextView)findViewById(R.id.textView4);
		TextView tvDetalii = (TextView)findViewById(R.id.textView5);

		ImageView image = (ImageView)findViewById(R.id.imageView1);
		
		tvTitlu.setText(game.getName());
		tvPublisher.setText(game.getPublisher());
		tvPlatforma.setText(game.getPlatform());
		tvGen.setText(game.getGenre());
		tvDetalii.setText(game.getDescription());
		
		InputStream is = null;
		try {
			is = DetailedGameActivity.this.openFileInput(game.getPicture() + ".png");
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			image.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
