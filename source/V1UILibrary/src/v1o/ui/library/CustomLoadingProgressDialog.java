package v1o.ui.library;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 自定义加载进度条对话框
 * @author v1onway
 */
public class CustomLoadingProgressDialog extends AlertDialog {
	
//	private ProgressBar loadingProgress;
	private TextView loadingProgressMessageTextView;
	
	private CharSequence message;
	
	public CustomLoadingProgressDialog(Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_loading_progressdiglog);
		loadingProgressMessageTextView = (TextView) findViewById(R.id.loading_progress_message_textView);
		
		if(message != null) {
			setMessage(message);
		}
	}
	
	@Override
	public void setMessage(CharSequence message) {
		if (loadingProgressMessageTextView != null) {
			loadingProgressMessageTextView.setText(message);
		} else {
			this.message = message;
		}
	}
}
