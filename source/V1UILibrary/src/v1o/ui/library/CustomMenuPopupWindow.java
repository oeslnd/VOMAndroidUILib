package v1o.ui.library;

import v1o.ui.library.adapter.MenuBodyAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

/**
 * 自定义弹出menu
 * 可以在使用界面确定menu的item个数
 * @author v1onway
 */
public class CustomMenuPopupWindow extends PopupWindow {
	
	private View menuView;
	private GridView menuItemGridView;
	
    private int itemCount;
    private OnItemClickListener bodyClick;
    
    private int screenHeight;
//    private int colorBgTabMenu;
    
    public CustomMenuPopupWindow(Context context, int itemCount, 
    		OnItemClickListener bodyClick, int screenHeight) {
        super(context);
 
//        this.context = context;
        this.itemCount = itemCount;
        this.bodyClick = bodyClick;
        this.screenHeight = screenHeight;
        
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		menuView = inflater.inflate(R.layout.custom_menupopupwindow, null);
        
        setGridView();
        setMenu();
    }
 
    /**
     * 设置gridview
     */
    private void setGridView() {
    	menuItemGridView = (GridView) menuView.findViewById(R.id.menuItemGridView);
    	menuItemGridView.setNumColumns(itemCount);
    	menuItemGridView.setOnItemClickListener(bodyClick);
    }
    
    /**
     * 设置menu默认
     */
    private void setMenu() {
    	//设置view 
        this.setContentView(menuView); 
        
        //设置弹出窗体的宽  
        this.setWidth(LayoutParams.FILL_PARENT);  
        
        //设置弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);
        
        //设置弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimBottom);  
        
        //设置弹出窗体的背景  
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  
        
        //menu菜单获得焦点
        this.setFocusable(true);
        this.update();
        
        this.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getY() < screenHeight - menuItemGridView.getHeight()) {
					dismiss();
				}
//				Log.d("-----点击的自定义menuY坐标是-----", event.getY() + "");				
//				Log.d("----menu的网格视图布局高度是-----", menuItemGridView.getHeight() + "");
				return false;
			}
		});
        
//        menuView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				int[] location = new int[2];
//				v.getLocationInWindow(location);
//				
//			}
//		});
        
        
        //解决menu按键无响应
        menuView.setFocusableInTouchMode(true);
        menuView.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_MENU) && (isShowing())) {  
		            dismiss();
		            return true;  
		        }  
				return false;
			}
		});
    }
    
    public void setBodySelect(int index, int colorSelBody) {
        int count = menuItemGridView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != index)
                ((LinearLayout) menuItemGridView.getChildAt(i))
                        .setBackgroundColor(Color.TRANSPARENT);
        }
        ((LinearLayout) menuItemGridView.getChildAt(index))
                .setBackgroundColor(colorSelBody);
    }
 
    public void setBodyAdapter(MenuBodyAdapter bodyAdapter) {
    	menuItemGridView.setAdapter(bodyAdapter);
    }
}
