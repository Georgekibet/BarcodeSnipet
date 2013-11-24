package com.snipet.barcodesnipet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial:
 * - Using Barcode Scanning in Android Apps
 * 
 * Sue Smith
 * May 2013
 *
 */
public class MainActivity extends Activity implements OnClickListener {

	//UI instance variables
	private Button scanBtn1,scanBtn2;
	private TextView formatTxt1,formatTxt2, contentTxt1,contentTxt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//instantiate UI items
		scanBtn1 = (Button)findViewById(R.id.scan_button);
		scanBtn2 = (Button)findViewById(R.id.scan_button_prod2);
		formatTxt1 = (TextView)findViewById(R.id.scan_format);
		contentTxt1 = (TextView)findViewById(R.id.scan_content);
		formatTxt2 = (TextView)findViewById(R.id.scan_format_prod2);
		contentTxt2 = (TextView)findViewById(R.id.scan_content_prod2);

		//listen for clicks
		scanBtn1.setOnClickListener(this);
		//scanBtn2.setOnClickListener(this);
	}

	public void onClick(View v){
		//check for scan button
		if(v.getId()==R.id.scan_button){
			//instantiate ZXing integration class
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			//start scanning
			scanIntegrator.initiateScan();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			String scanFormat = scanningResult.getFormatName();
			//output to UI
			formatTxt1.setText("FORMAT: "+scanFormat);
			contentTxt1.setText("CONTENT: "+scanContent);
			
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
