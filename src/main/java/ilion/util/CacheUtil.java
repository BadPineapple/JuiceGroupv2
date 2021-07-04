package ilion.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import ilion.SpringApplicationContext;

@Service
public class CacheUtil {
	
	public static CacheUtil getInstance() {
		
		CacheUtil cacheUtil = SpringApplicationContext.getBean(CacheUtil.class);
		
		return cacheUtil;
	}
	
	@CacheEvict(value={
			"arquivos.first.result.url",
			"arquivos.image.urls",
			}, allEntries=true)
	public void resetAllCaches() {
	}
	
}
