package com.pdp.commonbaseadapterlibrary;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jay on 2015/12/08  
 * @author Penny
 * 这个类在开发过程不断完善，把遇到的控件的基本操作加入进来
 * listView的item的焦点事件可以用focusable属性
 * 或者descendantFocusability(焦点事件不往下传递，用在item布局的容器中)属性
 * （if） 如果遇到checkBox 服用的混乱的问题可以在bean中加入一个boolean属性标记
 *  或者在子adapter中加一个标记集记录是否被选中
 * 
 */
public class ViewHolder {

    private SparseArray<View> mViews;   //存储ListView 的 item中的View
    private View convertView;                  //存放convertView
    private int position;               //游标
    private Context mContext;            //Context上下文

    //构造方法，完成相关初始化
    private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
        mViews = new SparseArray<View>();
        this.mContext = context;
        View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        convertView.setTag(this);
        this.convertView = convertView;
    }

    //绑定ViewHolder与item
    public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                  int layoutRes, int position) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutRes);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.convertView = convertView;
        }
        holder.position = position;
        return holder;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t == null) {
            t = (T) convertView.findViewById(id);
            mViews.put(id, t);
        }
        return t;
    }


    /**
     * 获取当前条目
     */
    public View getConvertView() {
        return convertView;
    }

    /**
     * 获取条目位置
     */
    public int getItemPosition() {
        return position;
    }

    /**
     * 设置文字
     */
    public ViewHolder setText(int id, CharSequence text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    /**
     * 设置图片
     */
    public ViewHolder setImageResource(int id, int drawableRes) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        } else {
            view.setBackgroundResource(drawableRes);
        }
        return this;
    }


    /**
     * 设置点击监听
     */
    public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见
     */
    public ViewHolder setVisibility(int id, int visible) {
        getView(id).setVisibility(visible);
        return this;
    }

    /**
     * 设置标签
     */
    public ViewHolder setTag(int id, Object obj) {
        getView(id).setTag(obj);
        return this;
    }

    //其他方法可自行扩展

}