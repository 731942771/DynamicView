package com.cuiweiyou.dynamicview.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuiweiyou.dynamicview.R;
import com.cuiweiyou.dynamicview.adapter.LVAdapter;
import com.cuiweiyou.dynamicview.bean.Student;
import com.cuiweiyou.dynamicview.view.HolderView;

public class MainActivity extends Activity {

	/** 顶部联系人View容器 **/
	private LinearLayout mLVTo;
	/** 当前显示的联系人首声母或首字 **/
	private TextView mTVNote;
	/** 联系人列表 **/
	private ListView mLVDirectory;
	/** 联系人信息集合 **/
	private List<Student> studentList;
	/** ListView适配器 **/
	private LVAdapter mAdapter;
	/** 选择的Item集合 **/
	private HashMap<Integer, HolderView> viewList = new HashMap<Integer, HolderView>();
	/** 是否长按过了 **/
	private boolean isLongPress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initList();

		initView();
	}

	// 实例化控件
	private void initView() {
		mLVTo = (LinearLayout) findViewById(R.id.lv_to); 				// 顶部联系人容器
		/** 联系人列表 **/
		mLVDirectory = (ListView) findViewById(R.id.lv_directory); 
		/** 适配器 **/
		mAdapter = new LVAdapter(MainActivity.this, studentList);
		mLVDirectory.setAdapter(mAdapter);
		mLVDirectory.setOnItemClickListener(new OnItemClickListener() { // 条目点击
					@Override
					public void onItemClick(AdapterView<?> adapter, View view, final int position, long id) { // id=position-headerView的个数（id < headerviewer的个数+用户view的个数），否则=-1
						
						// 如果没有被长按过
						if (!isLongPress)
							return;
						
						// 条目对应的学生信息
						Student student = studentList.get(position);
						
						// 适配器内条目控制器
						LVAdapter.HondlerView mAdapterHoldeView = (LVAdapter.HondlerView) view.getTag();
						
						// 如果条目是已经选中的
						if (mAdapterHoldeView.mIVCheck.isChecked()) {
							// 从适配器的选中集合删除，同时刷新
							mAdapter.remove(studentList.get(position));
							// 从顶部容器中删除
							mLVTo.removeView(viewList.get(position));
							// 从选中集合中删除
							viewList.remove(position);
						} 
						
						// 如果条目是没有被选中的
						else {
							// 添加到适配器的选中集合，同时刷新
							mAdapter.add(studentList.get(position));
							/** 联系人View项目 */
							final HolderView delView = new HolderView(MainActivity.this);
							delView.setName(student.getName());
							delView.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									// 从适配器的选中集合删除，同时刷新
									mAdapter.remove(studentList.get(position));
									// 从顶部容器中删除
									mLVTo.removeView(viewList.get(position));
									// 从选中集合中删除
									viewList.remove(position);
								}
							});
							mLVTo.addView(delView);
							viewList.put(position, delView);
						}
					}
				}
			);
		
		// 发生一次长按后，打开开关
		mLVDirectory.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				mAdapter.setCheckBoxIsShow(true);
				isLongPress = true;
				
				Toast.makeText(MainActivity.this, "LongClick", 0).show();

				return true;	// 拦截住此时的按下事件
			}
		});
	}

	/**
	 * 虚拟联系人数据
	 */
	private void initList() {
		studentList = new ArrayList<Student>();

		studentList.add(new Student("张三", 22, 1));
		studentList.add(new Student("李四", 44, 1));
		studentList.add(new Student("花无缺", 35, 1));
		studentList.add(new Student("王五", 28, 1));
		studentList.add(new Student("翠花", 15, 0));
		studentList.add(new Student("老胡", 52, 1));
		studentList.add(new Student("姚明", 43, 1));
		studentList.add(new Student("小骨", 22, 0));
		studentList.add(new Student("尊尚", 88, 1));
		studentList.add(new Student("金庸", 78, 1));
		studentList.add(new Student("梅超风", 103, 0));
		studentList.add(new Student("校长", 74, 1));
		
		Collections.sort(studentList, new Comparator<Student>() {
			@Override
			public int compare(Student lhs, Student rhs) {
				// 按年龄排序
				return lhs.getAge() - rhs.getAge();
			}
		});
	}
}
