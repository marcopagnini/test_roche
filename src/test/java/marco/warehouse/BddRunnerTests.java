package marco.warehouse;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/scenarios.feature"}, glue = {"marco.warehouse.bdd"})
public class BddRunnerTests {}
