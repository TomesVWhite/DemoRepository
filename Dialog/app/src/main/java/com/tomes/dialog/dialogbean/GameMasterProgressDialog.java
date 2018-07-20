package com.tomes.dialog.dialogbean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by zxm on 2017/11/6.
 */

public class GameMasterProgressDialog implements DialogInterface.OnDismissListener {
    private Context mContext;
    private String mDialogTitle;
    private String mNegativeText;

    private View mDialogView;
    private OnDialogListener mListener;
    private ProgressBarDialog.Builder mDialog;

    /**
     * 不带自定义view的构造器
     *
     * @param context      上下文
     * @param dialogTitle  对话框的标题
     * @param negativeText 表示是取消意图的按钮上text文本
     */
    public GameMasterProgressDialog(Context context, String dialogTitle, String negativeText) {
        this.mContext = context;
        this.mDialogTitle = dialogTitle;
        this.mNegativeText = negativeText;
    }

    /**
     * 获取自定义View
     *
     * @return
     */
    public View getDialogView() {
        return mDialogView;
    }

    /**
     * 设置自定义View
     *
     * @param dialogView 自定义布局的View对象
     */
    public void setDialogView(View dialogView) {
        this.mDialogView = dialogView;
    }

    /**
     * 显示对话框
     */
    public void showDialog() {
        mDialog = new ProgressBarDialog.Builder(mContext);
        mDialog.setTitle(mDialogTitle);
        //dialogMessage和dialogView是互斥关系，dialogMessage存在dialogView就不存在

        mDialog.setContentView(mDialogView);
        Dialog dialog = mDialog.setNegativeButton(mNegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (mListener != null) {
                    mListener.dialogNegativeListener(mDialogView, dialogInterface, which);
                }
            }
        }).create();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    mListener.dialogBackKey();
                }
                return false;
            }
        });
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    /**
     * 关闭对话框
     */
    public void closeDialog() {
        mDialog.close();
    }

    /**
     * 判断对话框是否正在显示
     *
     * @return
     */
    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public void controlSwitch(ToggleButton itemSwitch) {
        mDialog.openSwitch(itemSwitch);
    }

    /**
     * 注册监听器方法
     *
     * @param listener 监听器，用于将确定和取消意图的两个点击事件监听器，合成一个监听器，
     *                 并传入一个标记变量区别确定和取消意图
     * @return
     */
    public GameMasterProgressDialog setOnDiaLogListener(OnDialogListener listener) {
        this.mListener = listener;
        //把当前对象返回，用于链式编程
        return this;
    }

    /**
     * 定义一个监听器接口
     */
    public interface OnDialogListener {

        void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which);
        void dialogBackKey();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mContext = null;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    public void setOnKeyListener(boolean canceledOnTouchOutside) {
        mDialog.setOnKeyListener(canceledOnTouchOutside);
    }
}
