package persona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheManagementService {


	@Autowired
	@Qualifier("metadataCacheManager")
	private CacheManager metadataCacheManager;

	public void clearCache() {
		System.out.println(" :::: LIBERANDO CACHE...");
		for (String cache : metadataCacheManager.getCacheNames()) {
			this.metadataCacheManager.getCache(cache).clear();
		}
		System.out.println(" :::: CACHE LIBERADO...");
	}

	public void clearCache(String domain) {
		System.out.println(" :::: LIBERANDO CACHE POR DOMINIO ({})..."+ domain);
		this.metadataCacheManager.getCache(domain).clear();
		System.out.println(" :::: CACHE LIBERADO POR DOMINIO...");
	}

	public void clearCache(String domain, String key) {
		System.out.println(" :::: LIBERANDO CACHE POR DOMINIO ({}) Y KEY ({})..."+ domain+ key);
		this.metadataCacheManager.getCache(domain).evict(key);
		System.out.println(" :::: CACHE LIBERADO POR DOMINIO...");
	}

	public void putItem(String domain, String key, Object object) {
		this.metadataCacheManager.getCache(domain).put(key, object);
	}

}
