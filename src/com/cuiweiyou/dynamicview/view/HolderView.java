package com.cuiweiyou.dynamicview.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cuiweiyou.dynamicview.R;

/**
 * 用于添加的View项目<br/>
 * 实现点击事件 
 * @author Administrator
 */
public class HolderView extends LinearLayout {
	/** 不居中的文本标签 **/
	private TextView nameTv;

	/**
	 * 用于添加的View项目<br/>
	 * 用于new对象时的构造方法
	 * @param context 上下文
	 */
	public HolderView(Context context) {
		super(context);
		
		init(context);
	}

	/**
	 * 定义布局界面
	 * @param context 上下文
	 */
	private void init(Context context) {
		View hv = View.inflate(context, R.layout.view_holder, null);
		
		nameTv = (TextView) hv.findViewById(R.id.tv_holder_name);
		
		addView(hv);
	}

	/**
	 * 设置姓名文本
	 * @param name 姓名
	 */
	public void setName(String name) {
		nameTv.setText(name);
	}

}
