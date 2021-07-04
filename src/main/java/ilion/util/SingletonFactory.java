package ilion.util;

import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {

    private static SingletonFactory instance;

    public static SingletonFactory getInstance() {
        if (instance == null) {
            instance = new SingletonFactory();
        }
        return instance;
    }
    
    private Map<String, Object> instancias = new HashMap<String, Object>();
    
	public synchronized <T> T getObject(Class<T> classe) {
        if (instancias.containsKey(classe.getName())) {
            return (T) instancias.get(classe.getName());
        } else {
            T object;
            try {
                object = (T) classe.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            instancias.put(classe.getName(), object);
            return object;
        }
    }
}