package site.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PagesNaming {
    HOME_PAGE("Home page"),
    LOGIN_PAGE("Login page"),
    MAIN_PAGE("Main page");

    final String pageName;
}
