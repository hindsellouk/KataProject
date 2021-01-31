package tax.calculation.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "products-data-source")
public class ProductsDataSource {

	private String dataFileName;

}
