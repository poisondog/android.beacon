package poisondog.android.beacon.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import poisondog.android.beacon.TransmitBeacon;

public class MainActivity extends Activity {
	private DialogInterface.OnClickListener mListener;
	private EditText mUUID;
	private EditText mMajor;
	private EditText mMinor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUUID = (EditText) findViewById(R.id.uuid);
		mMajor = (EditText) findViewById(R.id.major);
		mMinor = (EditText) findViewById(R.id.minor);
		mUUID.setText("1234");
		mMajor.setText("234");
		mMinor.setText("34");
	}

	public void alertClick(View view) {
		System.out.println("Hi!!!");

		String uuid = mUUID.getText().toString();
		String major = mMajor.getText().toString();
		String minor = mMinor.getText().toString();
		TransmitBeacon.Parameter para = new TransmitBeacon.Parameter(this, uuid, major, minor);

		TransmitBeacon beacon = new TransmitBeacon();
		beacon.execute(para).startAdvertising();
	}

}
