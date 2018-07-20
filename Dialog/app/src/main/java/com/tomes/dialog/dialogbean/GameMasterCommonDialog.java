package com.tomes.dialog.dialogbean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by zxm on 2017/11/6.
 */

public class GameMasterCommonDialog implements DialogInterface.OnDismissListener {
    private Context mContext;
    private int mCustomLayoutId;
    private String mDialogTitle;
    private String mPositiveText;
    private String mNegativeText;
    private String mDialogMessage;

    private View mDialogView;
    private OnDialogListener mListener;
    private CustomDialog.Builder mDialog;

    /**
     * 带有自定义view的构造器
     *
     * @param context        上下文
     * @param customLayoutId 对话框自定义布局的id 若没有自定义布局，默认传入0
     * @param dialogTitle    对话框的标题
     * @param positiveText   表示是确定意图的按钮上text文本
     * @param negativeText   表示是取消意图的按钮上text文本
     */
    public GameMasterCommonDialog(Context context, int customLayoutId, String dialogTitle, String positiveText, String negativeText) {
        this.mContext = context;
        this.mDialogTitle = dialogTitle;
        this.mPositiveText = positiveText;
        this.mNegativeText = negativeText;
        this.mCustomLayoutId = customLayoutId;
        this.mDialogView = View.inflate(context, customLayoutId, null);
    }

    /**
     * 不带自定义view的构造器
     *
     * @param context       上下文
     * @param dialogMessage 对话框的内容
     * @param dialogTitle   对话框的标题
     * @param positiveText  表示是确定意图的按钮上text文本
     * @param negativeText  表示是取消意图的按钮上text文本
     */
    public GameMasterCommonDialog(Context context, String dialogMessage, String dialogTitle, String positiveText, String negativeText) {
        this.mContext = context;
        this.mDialogTitle = dialogTitle;
        this.mPositiveText = positiveText;
        this.mNegativeText = negativeText;
        this.mDialogMessage = dialogMessage;
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
        mDialog = new CustomDialog.Builder(mContext);
        mDialog.setTitle(mDialogTitle);
        //dialogMessage和dialogView是互斥关系，dialogMessage存在dialogView就不存在
        if (mDialogMessage != null) {
            mDialog.setMessage(mDialogMessage);
        } else {
            mDialog.setContentView(mDialogView);
        }

        Dialog dialog = mDialog.setPositiveButton(mPositiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                if (mListener != null) {
                    mListener.dialogPositiveListener(mDialogView, dialogInterface, which);
                }
            }
        }).setNegativeButton(mNegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                if (mListener != null) {
                    mListener.dialogNegativeListener(mDialogView, dialogInterface, which);
                }
            }
        }).create();
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    public void hideImage(){
        if (null != mDialog) {
            mDialog.hideImage();
        }
    }

    public void setMessageGravityCenter(){
        if (null != mDialog) {
            mDialog.setMessageGravityCenter();
        }
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
    public GameMasterCommonDialog setOnDiaLogListener(OnDialogListener listener) {
        this.mListener = listener;
        //把当前对象返回，用于链式编程
        return this;
    }

    /**
     * 定义一个监听器接口
     */
    public interface OnDialogListener {
        //customView：如果没有自定义view，那么它为null
        void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which);

        void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mContext = null;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside){
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }
}
