package core;

import site.pages.BasePage;

public final class TestScenario {

    private static final TestScenario scenario = new TestScenario();

    private static final ThreadLocal<EnvContainer> environment = new ThreadLocal<>();

    public static TestScenario getTestScenario() {
        return scenario;
    }

    public BasePage getCurrentPage() {
        return environment.get().getPages().getCurrentPage();
    }

    public void setCurrentPage(BasePage page) {
        if (page == null) {
            throw new IllegalArgumentException("The page not found " +
                    "Please check annotation @Name for target pages");
        }
        environment.get().getPages().setCurrentPage(page);
    }

    public EnvContainer getEnvironment() {
        return environment.get();
    }

    public void removeEnvironment() {
        environment.remove();
    }

    public void setEnvironment(EnvContainer coreEnvironment) {
        environment.set(coreEnvironment);
    }

    public BasePage getPage(String name) {
        return this.getEnvironment().getPage(name);
    }
}
