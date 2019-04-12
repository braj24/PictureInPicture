package com.bhushansingh.pictureinpicturemodeexample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class PictureInPictureActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingActionButton;
    private VideoView mVideoView;

    PictureInPictureParams.Builder pictureInPictureParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_in_picture);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParams = new PictureInPictureParams.Builder();
        }

        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mVideoView = findViewById(R.id.videoView);

        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);

        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(Uri.parse(getIntent().getStringExtra("videoURL")));
        mVideoView.requestFocus();


        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.setMediaController(mediaController);
                mediaController.setAnchorView(mVideoView);
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPictureInPictureMode();
            }
        });
    }

    private void openPictureInPictureMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Rational rational = new Rational(mVideoView.getWidth(), mVideoView.getHeight());
            pictureInPictureParams.setAspectRatio(rational).build();
            enterPictureInPictureMode(pictureInPictureParams.build());
        }

    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode)
            mFloatingActionButton.setVisibility(View.GONE);
        else
            mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!isInPictureInPictureMode()) {
                Rational rational = new Rational(mVideoView.getWidth(), mVideoView.getHeight());
                pictureInPictureParams.setAspectRatio(rational).build();
                enterPictureInPictureMode(pictureInPictureParams.build());
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mVideoView.setVideoURI(Uri.parse(intent.getStringExtra("videoURL")));
        mVideoView.requestFocus();
    }
}
