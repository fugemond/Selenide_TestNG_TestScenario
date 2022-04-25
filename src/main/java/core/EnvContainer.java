package core;

import core.annotations.Name;
import lombok.SneakyThrows;
import utility.AnnotationScanner;

import java.util.Arrays;

public class EnvContainer {

    private PagesContainer pages = new PagesContainer();

    public PagesContainer getPages() {
        return pages;
    }

    public BasePage getPage(String name) {
        return pages.get(name);
    }

    public EnvContainer(){
        initPages();
    }

    @SneakyThrows
    private void initPages() {
        new AnnotationScanner().getClassesAnnotatedWith(Name.class)
                .stream()
                .map(it -> {
                    if (BasePage.class.isAssignableFrom(it)) {
                        return (Class<? extends BasePage>) it;
                    } else {
                        throw new IllegalStateException("Class " + it.getName() + " should extends CorePage");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    private String getClassAnnotationValue(Class<?> c) {
        return Arrays.stream(c.getAnnotationsByType(Name.class))
                .findAny()
                .map(Name::value)
                .orElseThrow(
                        () -> new AssertionError(
                                "CorePage.Name annotation cannot be found in class " + c.getClass().getName()));
    }
}
