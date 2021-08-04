package ilion.me.pagar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

public class PostbackDelivery extends PagarMeModel<String> {

    @Expose(serialize = false)
    @SerializedName("response_time")
    private Integer responseTime;

    @Expose(serialize = false)
    @SerializedName("response_body")
    private String responseBody;

    @Expose(serialize = false)
    @SerializedName("response_headers")
    private String responseHeaders;

    @Expose(serialize = false)
    @SerializedName("status_code")
    private String statusCode;

    @Expose(serialize = false)
    @SerializedName("status_reason")
    private String statusReason;

    @Expose(serialize = false)
    @SerializedName("date_updated")
    private DateTime updatedAt;

    @Expose(serialize = false)
    private String status;
    
    @Expose(serialize = false)
	private String id;
    
    public Integer getResponseTime() {
        return responseTime;
    }

    public String getResponseBody() {
        if (responseBody == null) {
        	responseBody = "";
		}
    	return responseBody;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }
    
    public String getId() {
		return id;
	}

}
