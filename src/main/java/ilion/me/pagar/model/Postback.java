package ilion.me.pagar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "retorno")
public class Postback extends PagarMeModel<String>  implements Serializable{

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "retorno_codigo_seq")
	  @SequenceGenerator(name = "retorno_codigo_seq", sequenceName = "retorno_codigo_seq", allocationSize = 1)
	  private Long codigo;
	
	
	@Expose(serialize = false)
	private String object;
	 
	@Expose(serialize = false)
	private String id;
	
    @Expose(serialize = false)
    private Integer retries;

    @Expose(serialize = false)
    private String headers;

    @Expose(serialize = false)
    private String model;

    @Expose(serialize = false)
    @SerializedName("model_id")
    private String modelId;

    @Expose(serialize = false)
    @SerializedName("next_retry")
    private String nextRetry; // ???

    @Expose(serialize = false)
    private String payload;

    @Expose(serialize = false)
    @SerializedName("request_url")
    private String requestUrl;

    @Expose(serialize = false)
    private String signature;

    @Expose(serialize = false)
    @SerializedName("date_updated")
    private DateTime updatedAt;

    @Expose(serialize = false)
    @SerializedName("date_created")
    private DateTime updatedCreated;

    @Expose(serialize = false)
    private String status;

    @Transient
    private List<PostbackDelivery> deliveries;
    
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getNextRetry() {
		return nextRetry;
	}

	public void setNextRetry(String nextRetry) {
		this.nextRetry = nextRetry;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public DateTime getUpdatedCreated() {
		return updatedCreated;
	}

	public void setUpdatedCreated(DateTime updatedCreated) {
		this.updatedCreated = updatedCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<PostbackDelivery> getDeliveries() {
		if (deliveries == null) {
			deliveries = new ArrayList<PostbackDelivery>();
		}
		return deliveries;
	}

	public void setDeliveries(List<PostbackDelivery> deliveries) {
		this.deliveries = deliveries;
	}

	
}
