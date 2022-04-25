package core;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import core.annotations.Name;
import utility.ReflectionLocal;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


/**
 * 
 * @
 */
public abstract class BasePage extends ElementsContainer {

    private Map<String, Object> elementsOnThePage;

    public BasePage() {
        super();
    }

    public BasePage initializeElementsOnThePage() {
        elementsOnThePage = readNamedElements();
        return this;
    }

    private Map<String, Object> readNamedElements() {

        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .peek(this::checkFieldType)
                .collect(toMap(f -> f.getDeclaredAnnotation(Name.class).value(), this::extractFieldValueViaReflection));
    }

    private void checkFieldType(Field f) {
        if (!SelenideElement.class.isAssignableFrom(f.getType())
                && !BasePage.class.isAssignableFrom(f.getType())) {
            checkCollectionFieldType(f);
        }
    }
    private void checkCollectionFieldType(Field f) {
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
                        "If it describes block, is should be in the class that extends CorePage.\n" +
                        "Has found filed with the type %s", f.getType()));
    }

    private Object extractFieldValueViaReflection(Field field) {
        return ReflectionLocal.extractFieldValue(field, this);
    }

    public SelenideElement getElement(String elementName) {
        return (SelenideElement) ofNullable(elementsOnThePage.get(elementName))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Element " + elementName + " is not declared on " + this.getClass().getName()));
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
        Stream<Object> s = ((List) value).stream();
        return s.map(BasePage::castToSelenideElement).collect(toList());
    }
}
