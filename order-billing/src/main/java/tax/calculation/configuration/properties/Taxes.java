package tax.calculation.configuration.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "taxes")
public class Taxes {

	private Map<String, Float> taxPerProductType;
	private float importationTax;

}
