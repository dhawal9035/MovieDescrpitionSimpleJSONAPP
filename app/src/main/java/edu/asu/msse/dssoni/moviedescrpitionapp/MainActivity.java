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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String selectedStuff;
    public ExpandableListView elview;
    public ExpandableMovieListAdapter myListAdapter;
    public MovieLibrary movieLibrary;
    public LinkedHashMap<String,List<String>> map;
    static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = new LinkedHashMap<String,List<String>>();
        movieLibrary = new MovieLibrary(this);
        elview = (ExpandableListView) findViewById(R.id.lvExp);
        myListAdapter = new ExpandableMovieListAdapter(this);
        elview.setAdapter(myListAdapter);

    }

    public void setSelectedStuff(String aStuff) {
        this.selectedStuff = aStuff;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * Implement onOptionsItemSelected(MenuItem item){} to handle clicks of buttons that are
     * in the action bar.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        Intent intent = new Intent(this, AddActivity.class);
        switch (item.getItemId()) {
            case R.id.action_add:
                intent.putExtra("message", "This is a Add new button");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_CONTACT_REQUEST){
            if (resultCode == RESULT_OK){
                String removeTitle = data.getStringExtra("removeMovieJSON");
                String genre = movieLibrary.movies.get(removeTitle).genre;
                Log.d("remove", removeTitle + genre);
                String[] genreArray = genre.split(",");
                for(int i = 0; i<genreArray.length;i++) {
                    genreArray[i] = genreArray[i].trim();
                    Log.d("genre",genreArray[i]);
                }

                for(int i=0;i<genreArray.length; i++) {
                    map.get(genreArray[i]).remove(removeTitle);
                }
                movieLibrary.removeMovie(removeTitle);
                myListAdapter = new ExpandableMovieListAdapter(this);
                elview.setAdapter(myListAdapter);
                myListAdapter.notifyDataSetChanged();
                }
            }
        }
    }
