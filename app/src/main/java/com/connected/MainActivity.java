package com.connected;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final EditText account=(EditText)findViewById(R.id.editText1);
		final EditText password=(EditText)findViewById(R.id.editText2);
		Button launch=(Button)findViewById(R.id.button1);
		launch.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				new HttpClientThread("http://192.168.43.124:8080/web/MyServlet",
						account.getText().toString(),password.getText().toString(),MainActivity.this).start();
				Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
			}
		});
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


