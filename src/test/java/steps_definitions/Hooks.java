package steps_definitions;

import framework.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        testContext.getDriverManager().getDriver();
    }

    @After
    public void tearDown() {
        testContext.getDriverManager().quitDriver();
    }
}
