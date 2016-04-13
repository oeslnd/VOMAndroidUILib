package v1o.ui.library.adapter;

import v1o.ui.library.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuBodyAdapter extends BaseAdapter {

	private Context mContext;
	
	/**
	 * 资源id数字
	 */
    private int[] resID;

    /**
     * 说明字符串数组
     */
    private String[] introStrArray;
    
    /**
     * 设置TabMenu的分页主体
     * 
     * @param context
     *            调用方的上下文
     * @param resID
     */
    public MenuBodyAdapter(Context context, int[] resID, String[] introStrArray) {
        this.mContext = context;
        this.resID = resID;
        this.introStrArray = introStrArray;
    }

    @Override
    public int getCount() {
        return resID.length;
    }

    public Object getItem(int position) {
        return resID[position];
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * menu的每项
     */
    public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_menu, parent, false);
			holder = new ViewHolder();
			holder.menuItemTextView = (TextView) convertView.findViewById(R.id.menuItemTextView); 
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		int imgInt = resID[position];
		
		Drawable drawable = mContext.getResources().getDrawable(imgInt); 
		//这一步必须要做,否则不会显示
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); 
		holder.menuItemTextView.setCompoundDrawables(null, drawable, null, null);
		holder.menuItemTextView.setText(introStrArray[position]);
		
		return convertView;
	}

	static class ViewHolder {
		private TextView menuItemTextView;
	}
}
