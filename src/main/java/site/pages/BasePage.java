package site.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import core.annotations.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utility.ReflectionLocal;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


/**
 * @BasePage is Base class for all Pages in the project
 */
public abstract class BasePage extends ElementsContainer {

    /**
     * This Map is containing all elements in the Page which where marked with "@Name" annotation
     */
    private Map<String, Object> elementsOnThePage;

    protected BasePage() {
        super();
    }

    /**
     * Method uses to initialize elements on the page
     *
     * @return instance of class BasePage
     */
    public BasePage initializeElementsOnThePage() {
        elementsOnThePage = readNamedElements();
        return this;
    }

    private Map<String, Object> readNamedElements() {
        Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .forEach(this::checkFieldType);

        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .collect(toMap(f -> f.getDeclaredAnnotation(Name.class).value(), this::extractFieldValueViaReflection));
    }

    private void checkFieldType(Field f) {
        if (!SelenideElement.class.isAssignableFrom(f.getType())
                && !BasePage.class.isAssignableFrom(f.getType())) {
            if (ElementsCollection.class.isAssignableFrom(f.getType())) {
                return;
            } else if (List.class.isAssignableFrom(f.getType())) {
                ParameterizedType listType = (ParameterizedType) f.getGenericType();
                Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                if (SelenideElement.class.isAssignableFrom(listClass) || BasePage.class.isAssignableFrom(listClass)) {
                    return;
                }
            }
            throw new IllegalStateException(
                    format("Annotated with @Name field should have type SelenideElement or List<SelenideElement>.\n" +
                            "If it describes block, is should be in the class that extends BasePage.\n" +
                            "Has found filed with the type %s", f.getType()));
        }
    }

    private Object extractFieldValueViaReflection(Field field) {
        return ReflectionLocal.extractFieldValue(field, this);
    }

    public SelenideElement getElement(String elementName) {
        return (SelenideElement) ofNullable(elementsOnThePage.get(elementName))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Element " + elementName + " is not declared on " + this.getClass().getName()));
    }

    public SelenideElement getElementWithParam(String elementName, String param) {
        Field field = Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class).value().equals(elementName)).findFirst()
                .orElseThrow(() -> new AssertionError(format("Element with name %s is not declared on the page %s", elementName, this.getClass())));
        if (field.getAnnotation(FindBy.class).css().isEmpty()) {
            return $(By.xpath(format(field.getAnnotation(FindBy.class).xpath(), param)));
        } else {
            return $(By.cssSelector(format(field.getAnnotation(FindBy.class).css(), param)));
        }

    }

    private static SelenideElement castToSelenideElement(Object object) {
        if (object instanceof SelenideElement) {
            return (SelenideElement) object;
        }
        return null;
    }

    public List<SelenideElement> getElementsList(String listName) {
        Object value = elementsOnThePage.get(listName);
        if (!(value instanceof List)) {
            throw new IllegalArgumentException(
                    "The List " + listName + " is not declared on " + this.getClass().getName());
        }
        Stream<Object> s = ((List<Object>) value).stream();
        return s.map(BasePage::castToSelenideElement).collect(toList());
    }
}
