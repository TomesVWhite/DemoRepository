package com.tomes.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tomes.dialog.dialogbean.GameMasterCommonDialog;
import com.tomes.dialog.dialogbean.GameMasterProgressDialog;
import com.tomes.dialog.dialogbean.SDKUtil;

public class MainActivity extends AppCompatActivity {

    private GameMasterProgressDialog mGameMasterProgressDialog;

    private static final int UPLOAD_FINISH=0;
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPLOAD_FINISH:
                    System.out.println("Tomes-->handle messsage");
                    mGameMasterProgressDialog.closeDialog();
                    showShareDialog();
                    break;
                case 1:
                    Toast.makeText(MainActivity.this,"视频上传成功，请点击分享",Toast.LENGTH_LONG).show();
            }

        }
    };
    /**
     * 上传成功
     */
    private boolean uploadSuccess;

    /**
     * 上传失败
     */
    private boolean uploadailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        shareMedia();
    }

    private void showShareDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("share").setMessage("you can share").setPositiveButton("confine", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("cacel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
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
                //测试上传进度
//                uploadProgress();
            }
        }).showDialog();
    }

    int count = 0;

    /**
     * 上传进度dialog
     */
    private void uploadProgress() {
        mGameMasterProgressDialog = new GameMasterProgressDialog(this, "上传视频中", "取消上传").setOnDiaLogListener(new GameMasterProgressDialog.OnDialogListener() {
            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
//                interruptUploadingInfo();
                mGameMasterProgressDialog.closeDialog();
            }

            @Override
            public void dialogBackKey() {
                interruptUploadingInfo();
            }
        });
        mGameMasterProgressDialog.showDialog();
         Thread temp=new Thread(new Runnable() {
            @Override
            public void run() {

                while (count <= 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mGameMasterProgressDialog.refreshProgress(count);
                        }
                    });
                    System.out.println("Tomes->progress:"+count);
                    SystemClock.sleep(500);
                    count += 5;
                }
                uploadSuccess = true;
                //下载完关闭上传进度dialog
//                mGameMasterProgressDialog.closeDialog();
                mHandler.sendEmptyMessage(UPLOAD_FINISH);
            }
        });
        temp.start();

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
      final GameMasterCommonDialog a= new GameMasterCommonDialog(this, this.getString(R.string.interrupt_Uploading_info),
                getString(R.string.interrupt_Uploading_title), getString(R.string.interrupt_Uploading_right_btn),
                getString(R.string.interrupt_Uploading_left_btn)).setOnDiaLogListener(new GameMasterCommonDialog.OnDialogListener() {
            @Override
            public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {
//                dialogInterface.dismiss();
//                a.closeDialog();
//                mGameMasterProgressDialog.closeDialog();
            }

            @Override
            public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
            }
        });
        a.showDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUploadResult();
        System.out.println("Tomes-->onresume");
    }

    private void checkUploadResult() {
        if(uploadSuccess){
            Toast.makeText(this,"视频上传成功，请点击分享",Toast.LENGTH_LONG).show();
        }
        if(uploadailed){
            Toast.makeText(this,"“视频上传视频，请再上传",Toast.LENGTH_LONG);
        }
    }
}
