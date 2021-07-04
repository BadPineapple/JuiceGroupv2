package ilion.util.json;

public class APIResponse {
	
	public static APIResponse success;
	
	static {
		success = new APIResponse();
		success.status = "success";
		success.message = "";
	}
	
	private String status;
	private String message;
	private Object data;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static APIResponse errorWithMessage(String msg) {
		APIResponse rs = new APIResponse();
		
		rs.status = "error";
		rs.message = msg;
		
		return rs;
	}
	
	public static APIResponse errorWithException(Exception e) {
		APIResponse rs = new APIResponse();
		
		rs.status = "error";
		rs.message = e.getMessage();
		
		return rs;
	}
	
	public static APIResponse successComMensagem(String msg) {
		APIResponse rs = new APIResponse();
		
		rs.status = "success";
		rs.message = msg;
		
		return rs;
	}
	
	public static APIResponse success(Object data) {
		
		APIResponse rs = new APIResponse();
		
		rs.status = "success";
		rs.message = "";
		rs.data = data;
		
		return rs;
	}
}