package com.example.androidex;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EX01_03 extends AppCompatActivity {

    private TextView Tv;    // 定义 TextView 的对象
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // 调用父类的 onCreate 芳法
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);         // 通过 setContentView 方法设置当前页面的布局文件为 activity main
        Tv = findViewById(R.id.Tv);                     // 通过 findViewById 得到对应的 TextView 对象
        constraintLayout = findViewById(R.id.main);
        // constraintLayout.setBackgroundColor(256);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /// 菜单创建
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}