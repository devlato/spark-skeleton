/**
 * Created by d.tokarev on 02/12/15.
 */


package com.moneytapp.restapi.infrastructure.spark;


import com.moneytapp.restapi.app.configuration.DevelopmentConfiguration;
import com.moneytapp.restapi.app.configuration.ProductionConfiguration;
import com.moneytapp.restapi.app.configuration.TestConfiguration;
import java.util.HashMap;
import java.util.Map;


public class ConfigurationFactory {

    private static Map<Environments, Configuration> configurations = new HashMap<>();


    public static Configuration getConfiguration(
            Environments environment
    ) throws ConfigurationFactoryException {
        if (configurations.get(environment) == null) {
            configurations.put(environment, newConfiguration(environment));
        }

        return configurations.get(environment);
    }


    public static Configuration newConfiguration(
            Environments environment
    ) throws ConfigurationFactoryException {
        switch (environment) {
            case DEVELOPMENT:
                return new DevelopmentConfiguration();
            case PRODUCTION:
                return new ProductionConfiguration();
            case TEST:
                return new TestConfiguration();
            default:
                throw new ConfigurationFactoryException("Unsupported environment", environment);
        }
    }
}
