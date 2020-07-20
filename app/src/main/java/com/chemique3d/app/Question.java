package com.chemique3d.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Question extends AppCompatActivity {


    String tag_ = "QST_GEN_RESULT_ACT:";
    String t = "test";
    private TextView ques;
    private EditText ip;
    private Button finish, next;
    private CheckBox ans;
    private CoordinatorLayout mCLayout;
    private RecyclerView mRecyclerView;
    //    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> exampleList = new ArrayList<>();
    private ExampleAdapter mAdapter;
    private int totalQ = 6;
    private String url;
    private ProgressBar progressBar;
    private int progressStatus = 30;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Context mContext = getApplicationContext();

        Bundle b = getIntent().getExtras();
        // or other values
        if (b != null)
            url = b.getString("url");


        finish = findViewById(R.id.finish);
        next = findViewById(R.id.next);

        progressBar = findViewById(R.id.progressBar);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApi();
            }
        });


//        exampleList
//        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  1", "What is the bond angle of NaOH","220","240","110","45"));
//        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  2", "What is the bond angle of KOH","140","20","110","45"));
//        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  3", "What is the bond angle of Li(OH)2","320","240","110","45"));
//        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  4", "What is the bond angle of NaOH",t,"240","110","45"));


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);

        getApi();
//

//        mmAdapter = new ExampleAdapter(exampleList);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {

            }
        });


        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
//                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public void removeItem(int position) {
        exampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public String score() {
        String correct = Integer.toString(ExampleAdapter.correctResponses());
//        int count = 0;
        int count = exampleList.size();
        System.out.println(count);


        return correct + "/" + totalQ;


    }

    public void getApi() {

//        String url = "http://192.168.1.2:5010/test";
        //String url = ip.getText().toString();
//        ques.setText(" ");
//        ans.setText(" ");

        RequestQueue requstQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(tag_,response.toString());
                        try {
                            String qs, anw0, anw1, anw2, anw3, id;

                            JSONObject length = response.getJSONObject("question");
//                            String qst = response.getJSONObject("question").getString("0");
                            //  tv_Response.setText(value);
                            //System.out.println(qst);
                            //ques.setText(" ");
//                            ques.setText(qst);

//                            String answ = response.getJSONObject("answer").getString("0");
                            //System.out.println(answ);
                            //ans.setText(" ");
//                            ans.setText(answ);
                            //System.out.println(length.length());
                            totalQ = length.length();

                            JSONObject answe = response.getJSONObject("answer");

//                            System.out.println(ans1);

//                            exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  1", qst,ans0,ans1,ans2,ans3));
//                            exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  1", qst,ans0,ans1,ans2,ans3));
//                            exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  1", "What is the bond angle of NdfdgtsgaOH","120","240","110","45"));
//
                            for (int i = 0; i < length.length(); i++) {
                                id = Integer.toString(i);
                                String qst = response.getJSONObject("question").getString(id);

                                String ans0 = answe.getJSONObject(id).getString("0");
                                String ans1 = answe.getJSONObject(id).getString("1");
                                String ans2 = answe.getJSONObject(id).getString("2");
                                String ans3 = answe.getJSONObject(id).getString("3");
//
                                exampleList.add(new ExampleItem(R.drawable.ic_sun, "Question  ", qst, ans0, ans1, ans2, ans3));
//                            mAdapter = new ExampleAdapter(exampleList);
                                mRecyclerView.setAdapter(mAdapter);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(tag_, error.getMessage());
                    }
                }
        ) {
            //here I want to post data to sever
        };

        jsonobj.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requstQueue.add(jsonobj);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, scorec.class);
        startActivity(intent);

    }


}
