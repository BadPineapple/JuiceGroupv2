package ilion.me.pagar.model.filter;

import java.util.Map;

import ilion.me.pagar.model.PagarmeRelatable;

public interface QueriableFields extends PagarmeRelatable {

    public Map<String, Object> toMap();
}
