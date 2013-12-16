package azzam.imad.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import azzam.imad.demo.util.IabHelper;
import azzam.imad.demo.util.IabResult;
import azzam.imad.demo.util.Inventory;
import azzam.imad.demo.util.Purchase;
import azzam.imad.demo.util.SkuDetails;

public class InAppBillingActivity extends Activity
		implements IabHelper.OnIabSetupFinishedListener, IabHelper.QueryInventoryFinishedListener,
		IabHelper.OnConsumeFinishedListener, IabHelper.OnIabPurchaseFinishedListener {

	private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAllmyg8BK2x95W+pj+G9YX0ldtPp2NzjNH4oGo64mi3KqJRNuDg4sNilFD21dQ1k2OS4zg4jYO17AIrt5X6fCqb7ldRn9WqKRxHjxpBuL+YKNTLuHQPyWZYSFoQHbbN6A9dFBwdECgtu1nWdfnp9YTqNKKr3q1yqVdo8qXUyJk7R3AXe2ZCRDbTIZaOw88oeZOIAczin4mXVA5CjD9y2ADpqbz8u9wLC1cMvzXEGpD902iXK/VcB1sfZ5LTzaENufr1JwgJ7ivg4LO1sNsr3O8/yA9g0gjcmElny6hBKqNrVn5jo2quTtf/UCcJ6jGovP1rH5UGdfbxm6IUUNl4aD4QIDAQAB";
	IabHelper _helper;
	
	private static final String DEMO_PRODUCT_1 = "demo_product_1";
	
	private Purchase _purchase = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_app_billing);
		
		// Create the in-app billing helper
		_helper = new IabHelper(this, LICENSE_KEY);
		
	}

	@Override
	public void onDestroy() {
	   super.onDestroy();
	   if (_helper != null) {
		   _helper.dispose();
	   }
	   _helper = null;
	}
	
	// This is where we initially connect to the Google Play service
	public void startSetup(View v) {
		v.setVisibility(View.GONE);
		_helper.startSetup(this);
	}
	// This is the method called when the initial setup is done.
	@Override
	public void onIabSetupFinished(IabResult result) {
		if(result.isSuccess()) {
			displayFeedback("Setup worked!");
			findViewById(R.id.query_inventory_button).setVisibility(View.VISIBLE);
			findViewById(R.id.purchase_button).setVisibility(View.VISIBLE);
			findViewById(R.id.consume_button).setVisibility(View.VISIBLE);
		} else {
			displayFeedback("Setup error! " + result.getMessage());
			findViewById(R.id.start_setup_button).setVisibility(View.VISIBLE);
		}
	}
	
	// This methods queries the information about all products and their details
	public void queryInventory(View v) {
		findViewById(R.id.query_inventory_button).setVisibility(View.GONE);
		
		ArrayList<String> skuList = new ArrayList<String>();
		skuList.add(DEMO_PRODUCT_1);
		
		_helper.queryInventoryAsync(true, skuList, this);
	}
	// This is the method called when the query is done
	@Override
	public void onQueryInventoryFinished(IabResult result, Inventory inv) {
		if(result.isSuccess()) {
			SkuDetails sd = inv.getSkuDetails(DEMO_PRODUCT_1);
			String details = sd.getTitle() + "\n" +
							 sd.getPrice() + "\n" +
							 sd.getDescription() + "\n" +
							 (inv.hasPurchase(DEMO_PRODUCT_1) ? "Purchased" : "Not purchased");
			
			displayFeedback(details);
		} else {
			displayFeedback("Inventory error! " + result.getMessage());
		}
		findViewById(R.id.query_inventory_button).setVisibility(View.VISIBLE);
	}
	
	// This is the method to call to purchase the product
	public void purchase(View v) {
		_helper.launchPurchaseFlow(this, DEMO_PRODUCT_1, 12345, this);
	}
	@Override
	public void onIabPurchaseFinished(IabResult result, Purchase info) {
		if(result.isSuccess()) {
			displayFeedback("Purchase worked!");
			_purchase = info;
		} else {
			displayFeedback("Purchase error! " + result.getMessage());
		}
	}
	// IMPORTANT: this should be implemented so that purchase results are passed to the IabHelper
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		_helper.handleActivityResult(requestCode, resultCode, data);
	}
	
	// This is the method to call to consume the purchase product, to enable re-purchase
	public void consume(View v) {
		if(_purchase == null) {
			displayFeedback("Purchase is null!");
		} else {
			_helper.consumeAsync(_purchase, this);
		}
	}
	@Override
	public void onConsumeFinished(Purchase purchase, IabResult result) {
		if(result.isSuccess()) {
			displayFeedback("Consume worked!");
		} else {
			displayFeedback("Consume error! " + result.getMessage());
		}
	}	
	
	void displayFeedback(String s) {
		TextView tv = (TextView)findViewById(R.id.feedback_textview);
		tv.setText(s);
	}
}
