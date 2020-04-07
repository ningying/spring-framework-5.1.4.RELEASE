package org.springframework.core.io.support;

import org.junit.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesLoaderUtilsTest {

	@Test
	public void loadAllProperties() throws IOException {
		String mappingPath = "org/springframework/beans/factory/xml/support/nonExistent.properties";
		Properties mappings =
				PropertiesLoaderUtils.loadAllProperties(mappingPath, getClass().getClassLoader());
		Assert.notNull(mappings);
	}
}