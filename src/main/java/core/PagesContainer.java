package core;

import com.codeborne.selenide.Selenide;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.Map;

public class PagesContainer {

    private final Map<String, BasePage> pages;

    private BasePage currentPage;

    public PagesContainer() {
        pages = Maps.newHashMap();
    }

    public void setCurrentPage(BasePage page) {
        this.currentPage = page;
    }

    public BasePage getCurrentPage() {
        if (currentPage == null) {
            throw new IllegalStateException("Page is not defined");
        }
        return currentPage.initializeElementsOnThePage();
    }

    private Map<String, BasePage> getPageMapInstanceInternal() {
        return pages;
    }

    public BasePage get(String pageName) {
        return Selenide.page(getPageFromPagesByName(pageName)).initializeElementsOnThePage();
    }

    private BasePage getPageFromPagesByName(String pageName) throws IllegalArgumentException {
        BasePage page = getPageMapInstanceInternal().get(pageName);
        if (page == null) {
            throw new IllegalArgumentException(pageName + " page is not declared in a list of available pages");
        }
        return page;
    }

    @SneakyThrows
    public void put(String pageName, Class<? extends BasePage> page) {
        if (page == null) {
            throw new IllegalArgumentException("The page is empty");
        }
        Constructor<? extends BasePage> constructor = page.getDeclaredConstructor();
        constructor.setAccessible(true);
        BasePage p = page.getDeclaredConstructor().newInstance();
        pages.put(pageName, p);
    }

    public <T extends BasePage> void put(String pageName, T page){
        pages.put(pageName,page);
    }


}
