package ilion.me.pagar.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ilion.me.pagar.util.JSONUtils;

import javax.ws.rs.HttpMethod;

public class Balance {

    @Expose(serialize = false)
    @SerializedName("waiting_funds")
    private Summary waitingFunds;

    @Expose(serialize = false)
    private Summary available;

    @Expose(serialize = false)
    private Summary transferred;

    public Summary getWaitingFunds() {
        return waitingFunds;
    }

    public Summary getAvailable() {
        return available;
    }

    public Summary getTransferred() {
        return transferred;
    }

    public Balance refresh() throws PagarMeException {
        final PagarMeRequest request = new PagarMeRequest(HttpMethod.GET, String.format("/%s", getClass().getName()));
        final Balance other = JSONUtils.getAsObject((JsonObject) request.execute(), Balance.class);
        copy(other);
        return other;
    }

    private void copy(Balance other) {
        this.waitingFunds = other.waitingFunds;
        this.available = other.available;
        this.transferred = other.transferred;
    }

    public class Summary {

        @Expose(serialize = false)
        private Integer amount;

        public Integer getAmount() {
            return amount;
        }

    }
}
