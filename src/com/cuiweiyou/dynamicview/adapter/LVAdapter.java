package com.cuiweiyou.dynamicview.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuiweiyou.dynamicview.R;
import com.cuiweiyou.dynamicview.bean.Student;

/**
 * ListView适配器
 * 
 * @author Administrator
 */
public class LVAdapter extends BaseAdapter {

	/** 学生信息列表 **/
	private List<Student> list;
	/** 上下文 **/
	private Context context;
	/** 是否选择了 **/
	private boolean isChecked;
	/** 被选择的学生 **/
	private List<Student> posList = new ArrayList<Student>();

	/**
	 * ListView适配器
	 * @param context 上下文
	 * @param list  数据集合
	 */
	public LVAdapter(Context context, List<Student> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	/** 获取每个Item对应的Bean **/
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/** 获取Item条目View **/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/**内部类bean：条目控制器**/
		HondlerView mHV = null;

		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_listview, null);

			mHV = new HondlerView();
			mHV.mIVAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
			mHV.mTVName = (TextView) convertView.findViewById(R.id.tv_name);
			mHV.mTVAge = (TextView) convertView.findViewById(R.id.tv_age);
			mHV.mTVSex = (TextView) convertView.findViewById(R.id.tv_sex);
			mHV.mIVCheck = (CheckBox) convertView.findViewById(R.id.iv_check);

			convertView.setTag(mHV);
		} else {
			mHV = (HondlerView) convertView.getTag();
		}
		
		Student student = list.get(position);
		mHV.mTVName.setText(student.getName());
		mHV.mTVAge.setText(student.getAge() + "");
		mHV.mTVSex.setText(student.getSex() == 0 ? "女" : "男");
		
		/** 全局属性：是否选择了 **/
		if (isChecked) {
			mHV.mIVCheck.setVisibility(View.VISIBLE);
		} else {
			mHV.mIVCheck.setVisibility(View.GONE);
		}
		
		/** 全局属性：被选择的学生 **/
		if (posList.contains(student)) {	// 集合中是否有此对象
			mHV.mIVCheck.setChecked(true);
		} else {
			mHV.mIVCheck.setChecked(false);
		}
		
		return convertView;
	}

	/**
	 * 设置选择状态<br/>
	 * 同时适配器刷新
	 * @param isShowCheck
	 */
	public void setCheckBoxIsShow(boolean isChecked) {
		this.isChecked = isChecked;
		notifyDataSetChanged();
	}

	/**
	 * 向选中条目集合添加条目<br/>
	 * 同时刷新适配器
	 * @param student
	 */
	public void add(Student student) {
		posList.add(student);
		notifyDataSetChanged();
	}

	/**
	 * 从选中条目集合删除条目<br/>
	 * 同时刷新适配器
	 * @param student
	 */
	public void remove(Student student) {
		posList.remove(student);
		notifyDataSetChanged();
	}
	

	/**
	 * 条目控制器
	 * 
	 * @author Administrator
	 */
	public class HondlerView {
		/** 头像 **/
		public ImageView mIVAvatar;
		/** 姓名 **/
		public TextView mTVName;
		/** 年龄 **/
		public TextView mTVAge;
		/** 性别 **/
		public TextView mTVSex;
		/** 选择 **/
		public CheckBox mIVCheck;
	}
}
