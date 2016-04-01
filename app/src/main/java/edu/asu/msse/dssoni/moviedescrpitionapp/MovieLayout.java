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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MovieLayout extends AppCompatActivity {

    MovieDescription md;
    private Intent intent;
    EditText yearText;
    EditText releasedText;
    EditText runText;
    EditText actorText;
    EditText genreText;
    EditText ratingText;
    EditText plotText;
    String movieTitle="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_layout);
        TextView title = (TextView) findViewById(R.id.textView);
        yearText = (EditText) findViewById(R.id.editText11);
        releasedText = (EditText) findViewById(R.id.editText10);
        runText = (EditText) findViewById(R.id.editText12);
        actorText = (EditText) findViewById(R.id.editText13);
        genreText = (EditText) findViewById(R.id.editText14);
        ratingText = (EditText) findViewById(R.id.editText15);
        plotText = (EditText) findViewById(R.id.editText16);
        yearText.setKeyListener(null);
        releasedText.setKeyListener(null);
        runText.setKeyListener(null);
        actorText.setKeyListener(null);
        genreText.setKeyListener(null);
        ratingText.setKeyListener(null);
        plotText.setKeyListener(null);
        Intent newIntent = getIntent();

        //md = new MovieDescription(intent.getStringExtra("title"));
        movieTitle = newIntent.getStringExtra("title");
        title.setText(movieTitle);
        yearText.setText(newIntent.getStringExtra("year"));
        releasedText.setText(newIntent.getStringExtra("released"));
        runText.setText(newIntent.getStringExtra("runtime"));
        actorText.setText(newIntent.getStringExtra("actors"));
        genreText.setText(newIntent.getStringExtra("genre"));
        ratingText.setText(newIntent.getStringExtra("rated"));
        plotText.setText(newIntent.getStringExtra("plot"));

    }

    public void removeMovie(View view) {
        intent = new Intent();
        String removeTitle =  movieTitle;
       // Log.d("removetitle",md.title);
        intent.putExtra("removeMovieJSON", removeTitle);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    public void modifyMovie(View view){
        yearText = (EditText) findViewById(R.id.editText11);
        releasedText = (EditText) findViewById(R.id.editText10);
        runText = (EditText) findViewById(R.id.editText12);
        actorText = (EditText) findViewById(R.id.editText13);
        genreText = (EditText) findViewById(R.id.editText14);
        ratingText = (EditText) findViewById(R.id.editText15);
        plotText = (EditText) findViewById(R.id.editText16);

        yearText.setFocusableInTouchMode(true);
        releasedText.setFocusableInTouchMode(true);
        runText.setFocusableInTouchMode(true);
        actorText.setFocusableInTouchMode(true);
        genreText.setFocusableInTouchMode(true);
        ratingText.setFocusableInTouchMode(true);
        plotText.setFocusableInTouchMode(true);
    }
}
