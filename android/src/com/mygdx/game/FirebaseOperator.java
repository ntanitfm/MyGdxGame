package com.mygdx.game;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * FireBaseOperator
 *
 * 抽象化されたDbo(DataBaseOperator)の
 * Androidにおける処理の定義が行われたクラス
 * Created by ntani on 2017/11/07.
 */

public class FirebaseOperator implements DatabaseOperator {
    private String TAG = FirebaseOperator.class.getSimpleName();
    private List<ResultData> resultList;
    private DatabaseReference dbRef;
    private ValueEventListener vel;

    public FirebaseOperator() {
        Log.v(TAG, "constructor");
        dbRef = FirebaseDatabase.getInstance().getReference("Results");
        Log.v(TAG, "constructor");
        // Firebaseインスタンス
        dbRef = FirebaseDatabase.getInstance().getReference("Results");
        // データ読み込み(リスナー)
        vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "onDataChange called");
                resultList = new ArrayList<>();
                for (DataSnapshot res : dataSnapshot.getChildren()) {
                    resultList.add(res.getValue(ResultData.class));
                }
                Log.v(TAG, "size " + resultList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled is called");
            }
        };
        dbRef.addListenerForSingleValueEvent(vel);
        Log.v(TAG, "constructor finished");
    }
    @Override
    public void write(ResultData res) {
        Log.v(TAG, "write called");
        dbRef.push().setValue(res);
    }
    @Override
    public List<ResultData> read() {
        Log.v(TAG, "read called");
        dbRef.addListenerForSingleValueEvent(vel);
        // 並べ替え
        Collections.sort(resultList, new Comparator<ResultData>() {
            @Override
            public int compare(ResultData o1, ResultData o2) {
                long comp = o1.time - o2.time;
                if(comp<0) return -1;
                else return 1;
            }
        });
        return resultList;
    }
}
