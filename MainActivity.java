package com.example.teacher_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.teacher_player.databinding.ActivityMainBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    ResultSet rSet1,rSet2,rSet3,rSet4,rSet5;
    //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
    String url = "jdbc:mysql://47.102.207.49:3306/db1";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "12345678";
    Connection connection = null;
    public static final int MSG_ONE = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过消息的内容msg.what  分别更新ui
            switch (msg.what) {
                case MSG_ONE:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getStatus();
                        }
                    }).start();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为无标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        PlayerView playerView = findViewById(R.id.video_view);
        playerView.setPlayer(player);
        Uri uri= Uri.parse("http://106.14.252.249:8080/hls2/livestream.m3u8");
        DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
        String userAgent = Util.getUserAgent(this, "teacher_player");
        DefaultDataSourceFactory mediaDataSourceFactory = new DefaultDataSourceFactory(this, BANDWIDTH_METER,
                new DefaultHttpDataSourceFactory(userAgent, BANDWIDTH_METER));
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri,null,null);
        player.prepare(hlsMediaSource);
        player.setPlayWhenReady(true);

        //ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.fangxia1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            String sql = "UPDATE raisehandstatus SET status=0 WHERE name='stu1'";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.executeUpdate();
                            System.out.println("修改状态成功！！！");

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        Button button2 = (Button) findViewById(R.id.fangxia2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            String sql = "UPDATE raisehandstatus SET status=0 WHERE name='stu2'";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.executeUpdate();
                            System.out.println("修改状态成功！！！");

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        Button button3 = (Button) findViewById(R.id.fangxia3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            String sql = "UPDATE raisehandstatus SET status=0 WHERE name='stu3'";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.executeUpdate();
                            System.out.println("修改状态成功！！！");

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        Button button4 = (Button) findViewById(R.id.fangxia4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            String sql = "UPDATE raisehandstatus SET status=0 WHERE name='stu4'";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.executeUpdate();
                            System.out.println("修改状态成功！！！");

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        Button button5 = (Button) findViewById(R.id.fangxia5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            String sql = "UPDATE raisehandstatus SET status=0 WHERE name='stu5'";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.executeUpdate();
                            System.out.println("修改状态成功！！！");

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        new CheckThread().start();
    }

    public class CheckThread extends Thread {
        //重写run方法

        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    //每隔一秒 发送一次消息
                    Thread.sleep(500);
                    Message msg = new Message();
                    //消息内容 为MSG_ONE
                    msg.what = MSG_ONE;
                    //发送
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private void getStatus() {

        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！！");
            String sql1 = "SELECT * FROM raisehandstatus WHERE name='stu1'";
            String sql2 = "SELECT * FROM raisehandstatus WHERE name='stu2'";
            String sql3 = "SELECT * FROM raisehandstatus WHERE name='stu3'";
            String sql4 = "SELECT * FROM raisehandstatus WHERE name='stu4'";
            String sql5 = "SELECT * FROM raisehandstatus WHERE name='stu5'";
            // 创建用来执行sql语句的对象
            java.sql.Statement statement1 = connection.createStatement();
            java.sql.Statement statement2 = connection.createStatement();
            java.sql.Statement statement3 = connection.createStatement();
            java.sql.Statement statement4 = connection.createStatement();
            java.sql.Statement statement5 = connection.createStatement();
            // 执行sql查询语句并获取查询信息
            rSet1 = statement1.executeQuery(sql1);
            rSet1.next();
            final int i1 = rSet1.getInt("status");
            rSet2 = statement2.executeQuery(sql2);
            rSet2.next();
            final int i2 = rSet2.getInt("status");
            rSet3 = statement3.executeQuery(sql3);
            rSet3.next();
            final int i3 = rSet3.getInt("status");
            rSet4 = statement4.executeQuery(sql4);
            rSet4.next();
            final int i4 = rSet4.getInt("status");
            rSet5 = statement5.executeQuery(sql5);
            rSet5.next();
            final int i5 = rSet5.getInt("status");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    TextView t1 = findViewById(R.id.st1);
                    TextView t2 = findViewById(R.id.st2);
                    TextView t3 = findViewById(R.id.st3);
                    TextView t4 = findViewById(R.id.st4);
                    TextView t5 = findViewById(R.id.st5);
                    if (i1 == 1){
                        t1.setText("raised");
                    }
                    else{
                        t1.setText("down");
                    }
                    if (i2 == 1){
                        t2.setText("raised");
                    }
                    else{
                        t2.setText("down");
                    }
                    if (i3 == 1){
                        t3.setText("raised");
                    }
                    else{
                        t3.setText("down");
                    }
                    if (i4 == 1){
                        t4.setText("raised");
                    }
                    else{
                        t4.setText("down");
                    }
                    if (i5 == 1){
                        t5.setText("raised");
                    }
                    else{
                        t5.setText("down");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
