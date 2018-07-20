package com.tomes.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tomes.dialog.dialogbean.GameMasterCommonDialog;
import com.tomes.dialog.dialogbean.GameMasterProgressDialog;
import com.tomes.dialog.dialogbean.ProgressBarDialog;
import com.tomes.dialog.dialogbean.SDKUtil;

public class MainActivity extends AppCompatActivity {

    private GameMasterProgressDialog mGameMasterProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        shareMedia();
    }

    /**
     * 第一步，显示分享
     */
    private void shareMedia() {
        new GameMasterCommonDialog(this, this.getString(R.string.share_media_info),
                getString(R.string.share_media_title), getString(R.string.upload_media_not_wifi_right_btn),
                getString(R.string.upload_media_not_wifi_left_btn)).setOnDiaLogListener(new GameMasterCommonDialog.OnDialogListener() {
            @Override
            public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {
                if (SDKUtil.isWiFiActive(MainActivity.this)) {
                    uploadProgress();
                } else {
                    notWifiInfo();
                }
            }

            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
                Toast.makeText(MainActivity.this, "22222", Toast.LENGTH_SHORT).show();
                //测试上传进度
//                uploadProgress();
            }
        }).showDialog();
    }

    /**
     * 上传进度dialog
     */
    private void uploadProgress() {
        mGameMasterProgressDialog = new GameMasterProgressDialog(this,"上传视频中","取消上传").setOnDiaLogListener(new GameMasterProgressDialog.OnDialogListener() {
            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
                interruptUploadingInfo();
            }

            @Override
            public void dialogBackKey() {
                interruptUploadingInfo();
            }
        });
        mGameMasterProgressDialog.showDialog();
    }

    /**
     * 如果没有wifi
     */
    private void notWifiInfo() {
        new GameMasterCommonDialog(this, this.getString(R.string.upload_media_not_wifi_info),
                getString(R.string.upload_media_title), getString(R.string.upload_media_not_wifi_right_btn),
                getString(R.string.upload_media_not_wifi_left_btn)).setOnDiaLogListener(new GameMasterCommonDialog.OnDialogListener() {
            @Override
            public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {
                uploadProgress();
            }

            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
            }
        }).showDialog();
    }

    /**
     * 上传中退出提示
     */
    private void interruptUploadingInfo() {
        new GameMasterCommonDialog(this, this.getString(R.string.interrupt_Uploading_info),
                getString(R.string.interrupt_Uploading_title), getString(R.string.interrupt_Uploading_right_btn),
                getString(R.string.interrupt_Uploading_left_btn)).setOnDiaLogListener(new GameMasterCommonDialog.OnDialogListener() {
            @Override
            public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                mGameMasterProgressDialog.closeDialog();
            }

            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
            }
        }).showDialog();
    }

}
