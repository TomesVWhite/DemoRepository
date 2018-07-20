package com.tomes.dialog.dialogbean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tomes.dialog.R;

/**
 * Created by Administrator on 2017/11/3.
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private String title;
        private String message;
        private Context context;
        private View contentView;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        private CustomDialog dialog;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置对话框要显示的信息
         *
         * @param message 对话框内容
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置对话框标题
         *
         * @param title 对话框标题
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * 设置表示确定意图按钮文本和监听器
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * 设置表示取消意图按钮文本和监听器
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * 创建对话框
         *
         * @return
         */
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new CustomDialog(context, R.style.GameMasterCommonDialog);

            //获取屏幕宽高
            Point point = ScreenUtil.getPoint(context);
            int width = point.x;
            int height = point.y;
            width = Math.min(width, height);

            View layout = inflater.inflate(R.layout.game_master_common_dialog, null);
            LayoutParams layoutParams = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(false);

            //设置标题
            if (title == null) {
                layout.findViewById(R.id.game_master_dialog_title).setVisibility(View.GONE);
            } else {
                ((TextView) layout.findViewById(R.id.game_master_dialog_title)).setText(title);
            }

            //设置对话框内容信息
            if (message != null) {
                ((TextView) layout.findViewById(R.id.game_master_dialog_message)).setText(Html.fromHtml(message));
            } else if (contentView != null) {
                //如果没有信息，添加contentView
                ((LinearLayout) layout.findViewById(R.id.game_master_dialog_content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.game_master_dialog_content)).addView(
                        contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }

            //设置确定意图的按钮
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.game_master_dialog_define_btn)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    layout.findViewById(R.id.game_master_dialog_define_btn)
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                //没有确定意图的按钮，隐藏
                layout.findViewById(R.id.game_master_dialog_define_btn).setVisibility(View.GONE);
            }

            //设置取消意图的按钮
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.game_master_dialog_cancel_btn)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    layout.findViewById(R.id.game_master_dialog_cancel_btn)
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                //如果没有取消意图的按钮，隐藏
                layout.findViewById(R.id.game_master_dialog_cancel_btn).setVisibility(View.GONE);
            }

            dialog.setContentView(layout, layoutParams);

            return dialog;
        }

        public void hideImage(){
            if (null != dialog) {
                dialog.findViewById(R.id.image).setVisibility(View.GONE);
            }
        }

        public void setMessageGravityCenter(){
            if (null != dialog) {
                TextView messageTx = (TextView) dialog.findViewById(R.id.game_master_dialog_message);
                messageTx.setGravity(Gravity.CENTER);
            }
        }


        /**
         * 关闭对话框
         */
        public void close() {
            dialog.dismiss();
        }

        /**
         * 判断对话框是否正在显示
         *
         * @return
         */
        public boolean isShowing() {
            return dialog.isShowing();
        }

        public void openSwitch(final ToggleButton itemSwitch) {
            dialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    itemSwitch.setChecked(true);
                }
            });
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }
}
