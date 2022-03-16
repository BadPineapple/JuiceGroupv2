package ilion.me.pagar.model;

public class ResponsePaymentTransaction {

	private String transactionType;
	
	private String resultPaymentUrl;
	
	private String pixQRCode;

	public String getTransactionType() {
		return transactionType;
	}

	
	public ResponsePaymentTransaction(String transactionType, String resultPaymentUrl, String pixQRCode) {
		super();
		this.transactionType = transactionType;
		this.resultPaymentUrl = resultPaymentUrl;
		this.pixQRCode= pixQRCode;
	}


	public ResponsePaymentTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getResultPaymentUrl() {
		return resultPaymentUrl;
	}

	public void setResultPaymentUrl(String resultPaymentUrl) {
		this.resultPaymentUrl = resultPaymentUrl;
	}


	public String getPixQRCode() {
		return pixQRCode;
	}


	public void setPixQRCode(String pixQRCode) {
		this.pixQRCode = pixQRCode;
	}
	

}
