package ilion.email.negocio.sendgrid;

public class SendGridVH {
	
	private String email;
	
	private String apiKey;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "SendGridVH [email=" + email + ", apiKey=" + apiKey + "]";
	}

	
}