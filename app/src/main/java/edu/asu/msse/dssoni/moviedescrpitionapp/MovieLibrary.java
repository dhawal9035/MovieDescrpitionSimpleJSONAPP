/**
 *  Copyright 2016 Dhawal Soni
 *
 *  I give the Instructor and Arizona State University right to use
 *  this application source code to build and evaluate the software package.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  Created by Dhawal Soni on 2/11/2016.
 *
 *  @author Dhawal Soni mailto:dhawal.soni@asu.edu
 *  @version February 11, 2016
 */

package edu.asu.msse.dssoni.moviedescrpitionapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class MovieLibrary extends Object implements Serializable{
    private MainActivity parent;
    Hashtable<String,MovieDescription> movies;
    public MovieLibrary(MainActivity parent){
        this.parent = parent;
        movies = new Hashtable<String,MovieDescription>();
        this.clearMovies();
    }

    public void clearMovies(){
        InputStream is= parent.getApplicationContext().getResources().openRawResource(R.raw.movies);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        MovieDescription md;
        try{
            JSONObject jo = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = jo.keys();
            while(it.hasNext()){
                String title = it.next();
                JSONObject movie = jo.optJSONObject(title);
                if(movie!=null){
                    md = new MovieDescription(movie.toString());
                    movies.put(title,md);
                    String[] genre = md.getGenre().split(",");
                    for(int i=0;i<genre.length;i++){
                        genre[i].trim();
                    }
                    for(int i =0;i<genre.length;i++){
                        if(!parent.map.containsKey(genre[i])){
                            List<String> list = new ArrayList<>();
                            list.add(md.getTitle());
                            parent.map.put(genre[i],list);
                        } else {
                            List<String> list = (List<String>) parent.map.get(genre[i]);
                            list.add(md.getTitle());
                            parent.map.put(genre[i],list);
                        }
                    }
                }
            }
        }
        catch(JSONException je){
            je.printStackTrace();
        }
        catch(IOException ie){
            ie.printStackTrace();
        }
    }

    void removeMovie(String title){
        movies.remove(title);
    }

    void addMovie(String title){
        MovieDescription md = new MovieDescription(title);
        movies.put(md.title, md);
    }

}
