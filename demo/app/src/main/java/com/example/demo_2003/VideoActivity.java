package com.example.demo_2003;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


//import com.yc.video.player.VideoPlayer;
import com.yc.video.old.other.VideoPlayerManager;
import com.yc.video.player.VideoPlayer;
import com.yc.video.ui.view.BasisVideoController;


public class VideoActivity extends AppCompatActivity {


    String url;

    //    private String url = "https://www.w3schools.com/html/movie.mp4";
    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            initView();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        initData();

    }

    private void initData() {
        //设置播放类型
        // IjkPlayer or MediaPlayer
//        videoPlayer.setPlayerType(VideoPlayer.TYPE_NATIVE);
        //网络视频地址
//        String videoUrl = DataUtil.getVideoListData().get(0).getVideoUrl();
        //设置视频地址和请求头部
        videoPlayer.setUrl(url);
        //创建视频控制器
//        VideoPlayerController controller = new VideoPlayerController(this);
        BasisVideoController controller = new BasisVideoController(this);
        videoPlayer.setController(controller);

//        videoPlayer.start();

        videoPlayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoPlayer.start();
            }
        }, 1000);
    }


    private void initView() {
        videoPlayer = (VideoPlayer) findViewById(R.id.video_player);
        url = Environment.getExternalStorageDirectory() + "/DCIM/Camera/home.mp4";
    }

    @Override
    protected void onStop() {
        super.onStop();
        //从前台切到后台，当视频正在播放或者正在缓冲时，调用该方法暂停视频
        VideoPlayerManager.instance().suspendVideoPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁页面，释放，内部的播放器被释放掉，同时如果在全屏、小窗口模式下都会退出
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        //处理返回键逻辑；如果是全屏，则退出全屏；如果是小窗口，则退出小窗口
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            //销毁页面
            VideoPlayerManager.instance().releaseVideoPlayer();
        }
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //从后台切换到前台，当视频暂停时或者缓冲暂停时，调用该方法重新开启视频播放
        VideoPlayerManager.instance().resumeVideoPlayer();
    }
}