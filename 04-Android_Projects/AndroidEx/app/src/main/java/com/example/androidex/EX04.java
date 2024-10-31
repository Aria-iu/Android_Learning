package com.example.androidex;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EX04 extends AppCompatActivity {
    private TextView tv;
    private TextView tv_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ex04);

        tv = findViewById(R.id.Tv04);
        tv_image = findViewById(R.id.Tv_image1);
        String str = "<font color=green>我有一个梦想</font><br>"
                +"<font color=red>成为一名优秀的</font><br>"
                +"<font color=green>Android 开发者</font><br>"
                +"<font color=purple>制作属子自己的</font><br>"
                +"<font color=blue>应用……</font>";
        tv.setText(Html.fromHtml(str));
        String str2 = "<h1>测试图片</h1><p><img src="+R.drawable.ic_launcher_background+"></p>";
        //  ImageGetter 类中有接口方法 getDrawable,此方法在由中检测到＜img＞标签后调用，
        //  并且把识别 到的图片值传给 getDrawable 的参数source ，
        //  然后你就根据得到的图片值来进行图片的显示
        tv_image.setText(Html.fromHtml(str2,imgGetter,null));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private Html.ImageGetter imgGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            int id = Integer.parseInt(source);
            Drawable drawable = getResources().getDrawable(id);
            drawable.setBounds(0,0,drawable.getIntrinsicHeight(),drawable.getIntrinsicHeight());
            return drawable;
        }
    };
}