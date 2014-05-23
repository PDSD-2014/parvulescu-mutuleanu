package com.me.gamefinder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.me.general.Utils;
import com.me.model.Game;
import com.me.utils.GamesXmlParser;

public class MainActivity extends Activity {
	
	static final String REST_URI = "http://192.168.56.1:8080/GameFinderREST/jaxrs/games";
	
	private ListView lv;
	private GameAdapter adapter;
	private GamesXmlParser parser = new GamesXmlParser();
	private ArrayList<Game> games;
	
	Spinner spinnerGen;
	Spinner spinnerPlatforma;
	EditText qSearch;
	ImageView searchButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.listView1);
		games = new ArrayList<Game>();
		adapter = new GameAdapter(this, games);
		lv.setAdapter(adapter);
		
		spinnerGen = (Spinner)findViewById(R.id.spinner1);
		spinnerPlatforma = (Spinner)findViewById(R.id.spinner2);
		qSearch = (EditText)findViewById(R.id.editText1);
		searchButton = (ImageView)findViewById(R.id.imageView2);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new MyAsyncTask().execute();
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(view.getContext(), DetailedGameActivity.class);
				intent.putExtra("detaliedGame", games.get(position));
				startActivity(intent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, String> {

	    @Override
	    protected String doInBackground(String... urls) {
			
			try {
		        HttpClient client = new DefaultHttpClient();
		        HttpGet get;
		        
		        if (Utils.isNullOrWhiteSpace(qSearch.getText().toString()))
		        	get = new HttpGet(REST_URI + "/" + 
		        			Utils.getPlatformaNumber(spinnerPlatforma.getSelectedItem().toString().trim()) + "/" + 
		        			Utils.getGenNumber(spinnerGen.getSelectedItem().toString().trim()));
		        else
		        	get = new HttpGet(REST_URI + "/" + 
		        			Utils.getPlatformaNumber(spinnerPlatforma.getSelectedItem().toString().trim()) + "/" + 
		        			Utils.getGenNumber(spinnerGen.getSelectedItem().toString().trim()) + "/" +
		        			qSearch.getText().toString());
		        
		        HttpResponse rasp_get= client.execute(get);  
		        HttpEntity rasp_entity = rasp_get.getEntity();  
		 
		        if (rasp_entity != null) {
		        	String stringResult = EntityUtils.toString(rasp_entity);
		        	
		        	ArrayList<Game> tempGames = parser.parse(stringResult);
		        	for (Game game:tempGames)
		        	{
		        		get = new HttpGet(REST_URI + "/getpicture/" + game.getPicture());
				        rasp_get= client.execute(get);  
				        rasp_entity = rasp_get.getEntity();
				        
		        		FileOutputStream fOut = openFileOutput(game.getPicture() + ".png", Context.MODE_PRIVATE);
		        		fOut.write(EntityUtils.toByteArray(rasp_entity));
		        		fOut.close();
		        	}
		        	
		        	return stringResult;
		        }
		        
			} catch (Exception e) {
			        e.printStackTrace();
			}
			
			return null;		
	    }

	    @Override
	    protected void onPostExecute(String result) {
	    	try {
				games = parser.parse(result);
				adapter.setGames(games);
				adapter.notifyDataSetChanged();
	        }
	        catch (IOException e) 
	        {
				e.printStackTrace();
			}
	        catch (XmlPullParserException e)
	        {
				e.printStackTrace();
			}
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	    }
	}
}
