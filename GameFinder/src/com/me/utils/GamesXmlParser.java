package com.me.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.me.general.Constants;
import com.me.general.Utils;
import com.me.model.Game;

import android.util.Xml;

public class GamesXmlParser {
	private static final String namespace = null;   
	   
    public ArrayList<Game> parse(String in) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(new StringReader(in));
        parser.nextTag();
        return readGame(parser);
    }
    
    private ArrayList<Game> readGame(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Game> games = new ArrayList<Game>();

        parser.require(XmlPullParser.START_TAG, namespace, Constants.ROOT_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(Constants.START_TAG)) {
                games.add(addGame(parser));
            } else {
                skip(parser);
            }
        }  
        return games;
    }
    
    private Game addGame(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, Constants.START_TAG);
        Game game = new Game();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String attribute = parser.getName();
            Object value = null;
            
            if (attribute.equals(Constants.PLATFORM_TAG))
            	value = Utils.getPlatformaName(Integer.parseInt(readAttribute(parser, attribute)));
            else if (attribute.equals(Constants.GENRE_TAG))
            	value = Utils.getGenName(Integer.parseInt(readAttribute(parser, attribute)));
            else
            	value = readAttribute(parser, attribute);
            
            game.set(attribute, value);
        }
        return game;
    }   
    
    private String readAttribute(XmlPullParser parser, String attribute) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, attribute);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, attribute);
        return title;
    }
    
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
     }
}
