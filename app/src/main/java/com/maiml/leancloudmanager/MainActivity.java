package com.maiml.leancloudmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.io.FileNotFoundException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

        findViewById(R.id.uploadFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        findViewById(R.id.queryFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryFile();
            }
        });



    }

    private void queryFile() {

        AVQuery<AVObject> query = new AVQuery<>("_File");

        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list == null) {
                    return;
                }

                for (AVObject object : list) {
                    String name = object.getString("name");
                    String url = object.getString("url");
                    Log.e("tag", "----- name = " + name + "   url = " + url);
                }

                Log.e("tag", "----size = " + list.size());
            }
        });


    }


    private void uploadFile() {
        String path = "/storage/emulated/0/DCIM/Camera/20170716_065948.jpg";

        try {
            final AVFile file = AVFile.withAbsoluteLocalPath("20170716_065948.jpg", path);

            file.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    Log.d("tag",file.getUrl());//返回一个唯一的 Url 地址
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void test() {
        // 测试 SDK 是否正常工作的代码
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("saved", "success!");
                }
            }
        });
    }
}
