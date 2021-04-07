package com.example.teacher_player;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DengLuActivity extends AppCompatActivity implements View.OnClickListener {
    //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
    String url = "jdbc:mysql://47.102.207.49:3306/db1";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "12345678";
    Connection connection = null;
    Button denglu, zhuce;
    EditText phone, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        initview();
    }

    private void initview() {
        denglu = (Button) findViewById(R.id.denglu);
        zhuce = (Button) findViewById(R.id.zhuce);
        phone = (EditText) findViewById(R.id.phone);
        password2 = (EditText) findViewById(R.id.password);
        denglu.setOnClickListener(this);
        zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce:
                Intent intent = new Intent(DengLuActivity.this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.denglu:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        try {
                            //1、加载驱动
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            System.out.println("驱动加载成功！！！");
                            //2、获取与数据库的连接
                            connection = DriverManager.getConnection(url, userName, password);
                            System.out.println("连接数据库成功！！！");
                            //3.sql添加数据语句
                            String sql = "SELECT * FROM tb1 ";
                            //获取输入框的数据
                            String phone1 = phone.getText().toString();
                            String password1 = password2.getText().toString();
                            // 创建用来执行sql语句的对象
                            java.sql.Statement statement = connection.createStatement();
                            // 执行sql查询语句并获取查询信息
                            ResultSet rSet = statement.executeQuery(sql);
                            if (phone1.equals("") || password1.equals("")) {
                                Toast.makeText(DengLuActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                            }
                            int flag=0;
                            while (rSet.next()) {
                                if (phone1.equals(rSet.getString("phone")) && password1.equals(rSet.getString("password"))) {
                                    //Intent intent = new Intent(DengLuActivity.this, MainActivity.class);
                                    //startActivity(intent);
                                    Toast.makeText(DengLuActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    flag=1;
                                    break;
                                } else if (phone1.equals(rSet.getString("phone")) && !password1.equals(rSet.getString("password"))){
                                    Toast.makeText(DengLuActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                    flag=2;
                                    break;
                                }
                            }
                            if (flag==0) {
                                Toast.makeText(DengLuActivity.this, "账号不存在请先注册", Toast.LENGTH_SHORT).show();
                            }
                            else if (flag==1){
                                Intent intent = new Intent(DengLuActivity.this, MainActivity.class);
                                startActivity(intent);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Looper.loop();
                    }
                }).start();
                break;
        }
    }
}
