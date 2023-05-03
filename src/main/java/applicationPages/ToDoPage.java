package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToDoPage {
    WebDriver driver;
    By byCreateNewToDoListXpath = By.xpath("");
    By byAddTitleXpath = By.xpath("");
    By byAddDescriptionXpath = By.xpath("");
    By byDoneXpath = By.xpath("");
    By byAddItemXpath = By.xpath("");
    By byAddItemDescriptionXpath = By.xpath("");
    By byDeleteOptionXpath = By.xpath("");
    By byEditOptionXpath = By.xpath("");
    By byEditOptionForAItemXpath = By.xpath("");

    public ToDoPage(WebDriver driver) {
        this.driver = driver;
    }

    public ToDoPage userSelectsCreateNewToDo() {
        driver.findElement(byCreateNewToDoListXpath).click();
        return this;
    }

    public ToDoPage userAddDTitle(String title) {
        driver.findElement(byAddTitleXpath).sendKeys(title);
        return this;

    }

    public ToDoPage userAddDescription(String description) {
        driver.findElement(byAddDescriptionXpath).sendKeys(description);
        return this;
    }

    public boolean isCreatedToDoListVisible(String description) {
        return isElementPresent("Pass xpath here ");
    }

    public ToDoPage userSelectAddItemOption() {
        driver.findElement(byAddItemXpath).click();
        return this;
    }

    public ToDoPage userMarkToDoItemAsCompleted() {
        driver.findElement(By.xpath()).click();
        return this;
    }


    public ToDoPage userAddNewItemTitle(String description) {
        driver.findElement(byAddItemDescriptionXpath).sendKeys(description);
        return this;
    }

    public ToDoPage userSelectedDoneOption() {
        driver.findElement(byDoneXpath).click();
        return this;
    }

    public boolean isNewItemAddedToToDoList(String new_item_description) {
        return isElementPresent("Pass xpath here ");

    }

    public ToDoPage userMarksItemAsCompleted(String itemToBeMarked) {
        driver.findElement(By.xpath()).click();

    }

    public boolean isItemMarkedCompleted(String new_item_description) {
        return driver.findElement(By.xpath()).isSelected();
    }

    public ToDoPage userSelectsDeletedMarkedItem() {
        driver.findElement(byDeleteOptionXpath).click();
    }

    public boolean isItemDeleted() {
        return !isElementPresent("Pass xpath here ");

    }

    public ToDoPage userSelectsEditOption() {
        driver.findElement(byEditOptionXpath).click();
        return this;
    }

    public boolean isToDoDescriptionVisible(String new_toDo_description) {
        return !isElementPresent("Pass xpath here ");
    }

    public ToDoPage userSelectsEditOptionForAItem(String toDoItemTitle) {
        driver.findElement(byEditOptionForAItemXpath).click();
        return this;
    }

    public ToDoPage userAddnewTitleForToDoItem(String newTodoItemTitle) {
        driver.findElement(byAddItemDescriptionXpath).sendKeys(newTodoItemTitle);
        return this;
    }
    private boolean isElementPresent(String xpathOfElement) {
        try {
            return driver.findElement(By.xpath(xpathOfElement)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}