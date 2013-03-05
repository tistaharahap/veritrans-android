package com.payments.veritrans;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Veritrans {
	
	protected static final String
		URL_REQUEST_TOKEN = "https://payments.veritrans.co.id/web1/commodityRegist.action";
	
	private static String 
		MERCHANT_ID = "",
		MERCHANTHASH = "";
	
	private String
		orderID						= "",
		sessionID					= "",
		grossAmount					= "",
		customerSpecificationFlag	= "",
		shippingRequired			= "0",
		finishPaymentURL			= "",
		unfinishPaymentURL			= "",
		errorPaymentURL				= "",
		settlementType 				= "01",
		showLanguageOptions			= "0",
		language					= "in",
		firstName					= "",
		lastName					= "",
		address1					= "",
		address2					= "",
		city						= "",
		countryCode					= "",
		postalCode					= "",
		phone						= "",
		email						= "";
	
	private static boolean isInitialized = false;
	
	private List<Commodity> commodities;
	
	public static void initialize(String merchantID, String merchantHash) {
		MERCHANT_ID = merchantID;
		MERCHANTHASH = merchantHash;
		isInitialized = true;
	}
	
	public static Veritrans getInstance() {
		return isInitialized ? new Veritrans() : null;
	}
	
	public Veritrans() {
		commodities = new ArrayList<Commodity>();
	}
	
	public String getRequestTokensPostRequestString() {
		String post = "MERCHANT_ID=%s&MERCHANTHASH=%s&ORDER_ID=%s&SESSION_ID=%s&FINISH_PAYMENT_RETURN_URL=%s&";
		post += "UNFINISH_PAYMENT_RETURN_URL=%s&ERROR_PAYMENT_RETURN_URL=%s&SETTLEMENT_TYPE=%s&";
		post += "GROSS_AMOUNT=%s&CUSTOMER_SPECIFICATION_FLAG=%s&FIRST_NAME=%s&LAST_NAME=%s&";
		post += "ADDRESS1=%s&ADDRESS2=%s&CITY=%s&COUNTRY_CODE=%s&POSTAL_CODE=%s&PHONE=%s&";
		post += "EMAIL=%s&SHIPPING_FLAG=%s&LANG_ENABLE_FLAG=%s&LANG=%s";
		
		post = String.format(post, 
				MERCHANT_ID, MERCHANTHASH, orderID, sessionID, finishPaymentURL,
				unfinishPaymentURL, errorPaymentURL, settlementType,
				grossAmount, customerSpecificationFlag, firstName, lastName,
				address1, address2, city, countryCode, postalCode, phone,
				email, shippingRequired, showLanguageOptions, language);
		
		String cm = "COMMODITY_ID=%s&COMMODITY_UNIT=%s&COMMODITY_NUM=%s&COMMODITY_NAME1=%s&COMMODITY_NAME2=%s";
		for(Commodity comm : commodities) {
			post = String.format("%s&"+cm, post, 
					comm.getCommodityID(), comm.getCommodityUnitPrice(), comm.getCommodityQuantity(),
					comm.getCommodityName(), comm.getCommodityNameENG());
		}
		post = String.format("%s&REPEAT_LINE=%s", post, commodities.size());
		try {
			post = encodeQueryStringValues(post);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return post;
	}
	
	private static String encodeQueryStringValues(String q) throws UnsupportedEncodingException, NullPointerException {
		String[] arr = q.split("&");
		String ret = "";
		for(String piece : arr) {
			String[] kv = piece.split("=", 2);
			if(ret.length() == 0)
				ret = String.format("%s=%s", kv[0], URLEncoder.encode(kv[1], "UTF-8"));
			else {
				ret = String.format("%s&%s=%s", ret, kv[0], URLEncoder.encode(kv[1], "UTF-8"));
				ret = ret.replace("+", "%20");
			}
		}
		return ret;
	}
	
	public void addCommodity(Commodity commodity) {
		commodities.add(commodity);
	}
	
	public int getCommodityCount() {
		return commodities.size();
	}

	public static String getMerchantID() {
		return MERCHANT_ID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getCustomerSpecificationFlag() {
		return customerSpecificationFlag;
	}

	public void setCustomerSpecificationFlag(boolean customerSpecificationFlag) {
		this.customerSpecificationFlag = customerSpecificationFlag ? "1" : "0";
	}

	public String getShippingRequired() {
		return shippingRequired;
	}

	public void setShippingRequired(boolean shippingRequired) {
		this.shippingRequired = shippingRequired ? "1" : "0";
	}

	public String getFinishPaymentURL() {
		return finishPaymentURL;
	}

	public void setFinishPaymentURL(String finishPaymentURL) {
		this.finishPaymentURL = finishPaymentURL;
	}

	public String getUnfinishPaymentURL() {
		return unfinishPaymentURL;
	}

	public void setUnfinishPaymentURL(String unfinishPaymentURL) {
		this.unfinishPaymentURL = unfinishPaymentURL;
	}

	public String getErrorPaymentURL() {
		return errorPaymentURL;
	}

	public void setErrorPaymentURL(String errorPaymentURL) {
		this.errorPaymentURL = errorPaymentURL;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getShowLanguageOptions() {
		return showLanguageOptions;
	}

	public void setShowLanguageOptions(boolean showLanguageOptions) {
		this.showLanguageOptions = showLanguageOptions ? "1" : "0";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTotalCommoditiesInCart() {
		return String.valueOf(commodities.size());
	}

	public static String getMerchantHash() {
		return MERCHANTHASH;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

