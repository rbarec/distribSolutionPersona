package persona.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheDomains {

	INFORMATION_SERVICE("informationService"),
	SETTING("setting"),
	SOURCE("source");

	private final String domainName;
}
