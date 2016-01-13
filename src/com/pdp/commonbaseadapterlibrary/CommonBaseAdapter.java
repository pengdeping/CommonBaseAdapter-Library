package com.pdp.commonbaseadapterlibrary;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Jay on 2015/12/08    
 * @author Penny
 * TODO 这个类是用来被继承的基类，不要直接用
 * @param T 代表的是传入的数据集， 可以写成一个bean类
 *  
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    private List<T> mData;
    private int mLayoutRes;           //布局id
    private Context mContext ;

    public CommonBaseAdapter() {
    }

    public CommonBaseAdapter(Context context ,List<T> mData, int mLayoutRes) {
    	this.mContext = context ;
    	if (mData==null) 
			this.mData = new ArrayList<T>() ;
		else
            this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
    	// TODO Auto-generated method stub
    	return super.getViewTypeCount();
    }
    
    
    @Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(mContext, convertView, parent, mLayoutRes
                , position);
        bindView(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * 这个方法是用来重写一些具体操作的，入设置值，点击事件，判断操作等
     * @param holder
     * @param obj
     * 通过holder可以拿到所有的控件，并做处理
     */
    public abstract void bindView(ViewHolder holder, T obj); 

    //添加一个元素
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

}
