package com.mygdx.game;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    private DatabaseReference dbRef;
    private String PATH;
    private Gson gson;
    private String json;

    public FirebaseOperator() {
        Log.v(TAG, "constructor");
        dbRef = FirebaseDatabase.getInstance().getReference("Results");
        PATH = "https://mygdxgame-7c33b.firebaseio.com/Results.json";
        gson = new Gson();
        json = "";
    }
    @Override
    public void write(ResultData res) {
        Log.v(TAG, "write called");
        dbRef.push().setValue(res);
    }

    @Override
    public List<ResultData> read() {
        Log.v(TAG, "read called");
        // json取得実行
        getJson();
        // 処理完了まで待機
        while (json.isEmpty()) {
            try{
                Thread.sleep(100);
            }
            catch (Exception e) {
                Log.v(TAG, "json取得待機中に失敗");
            }
        }
        List<ResultData> resultList = new ArrayList<>();
        // jsonを変換
        try {
            JSONObject jObj = new JSONObject(json);
            Iterator<String> iter = jObj.keys();
            // 各結果の取得
            while (iter.hasNext()) {
                String key = iter.next();
                Object value = jObj.get(key);
                ResultData res = gson.fromJson(value.toString(), ResultData.class);
                resultList.add(res);
                Log.v(TAG, res.toString());
            }
        }
        catch (Exception e) {
            Log.v(TAG, "JSONオブジェクトの変換で失敗");
        }
        return resultList;
    }

    // Firebaseから直接jsonの取得
    private void getJson() {
        Log.v(TAG, "getJson called");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.v(TAG, "Thread start");
                    // GET通信
                    URL url = new URL(PATH);
                    HttpURLConnection http = (HttpURLConnection)url.openConnection();
                    http.setRequestMethod("GET");
                    Log.v(TAG, "connect ready");
                    http.connect();
                    Log.v(TAG, "connect done");
                    // 内容読み出し
                    BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String tmp = "", line = "";
                    while ((line = reader.readLine()) != null) tmp += line;
                    // json代入
                    json = tmp;
                    Log.v(TAG, "json substituted = " + json);
                    reader.close();
                }
                catch(Exception e) {
                    Log.v(TAG, "json取得失敗");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
