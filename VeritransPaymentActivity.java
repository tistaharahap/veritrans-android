package com.payments.veritrans;

import com.urbanesia.naracap.R;
import com.urbanesia.naracap.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class VeritransPaymentActivity extends Activity {
	
	private WebView webview;
	private Veritrans vt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.veritrans_webview);
		
		vt = Veritrans.getInstance();
		if(vt == null) {
			Veritrans.initialize("T100000000000001000281", "630e61e69b94c8d6c4a54afc0a2b716248f68bcba8ddf837db8824f07d574441");
			vt = Veritrans.getInstance();
		}

		vt.setFinishPaymentURL("http://www.urbanesia.com");
		vt.setErrorPaymentURL("http://www.urbanesia.com");
		vt.setUnfinishPaymentURL("http://www.urbanesia.com");
		vt.setCustomerSpecificationFlag(true);
		vt.setGrossAmount("200000");
		vt.setLanguage("in");
		vt.setOrderID("ORDERWAKU001");
		vt.setSessionID("WAaksjdhqo12093asdn12kj");
		vt.setSettlementType("01");
		vt.setShippingRequired(false);
		vt.setShowLanguageOptions(false);
		vt.setFirstName("Batista");
		vt.setLastName("Harahap");
		
		Commodity comm = new Commodity();
		comm.setCommodityID("WAKU001");
		comm.setCommodityName("Test VT Client Android");
		comm.setCommodityNameENG("Test VT Client Android");
		comm.setCommodityQuantity("1");
		comm.setCommodityUnitPrice("200000");
		vt.addCommodity(comm);
		
		String post = vt.getRequestTokensPostRequestString();
		Log.v(Utils.TAG, post);
		webview = (WebView) findViewById(R.id.vtWebview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.postUrl(Veritrans.URL_REQUEST_TOKEN, post.getBytes());
	}
	
}

