import applicationPages.ToDoPage;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class ToDoTest {
    WebDriver driver;
    JSONParser jsonParser = new JSONParser();
    FileReader reader = new FileReader("src/main/resources/testData.json");
    //Read JSON file
    Object obj = jsonParser.parse(reader);
    JSONArray testDataArray = (JSONArray) obj;
    JSONObject testData = (JSONObject) testDataArray.get(0);


    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.browserstack.com/");
    }

    @Test
    public void userShouldBeAbleToCreateAToDoList() {
        ToDoPage toDoPage = new ToDoPage(driver);
        boolean isNewToDoListCreated = toDoPage
                .userSelectsCreateNewToDo()
                .userAddDescription((String) testData.get("description"))
                .userAddDTitle((String) testData.get("title"))
                .userSelectedDoneOption()
                .isCreatedToDoListVisible((String) testData.get("title"));

        Assert.isTrue(isNewToDoListCreated, "New ToDo list is not created");
    }

    @Test
    public void userShouldBeAbleToCreatesAndEnterItemToToDoList() {
        ToDoPage toDoPage = new ToDoPage(driver);
        userShouldBeAbleToCreateAToDoList();
        boolean isNewItemAddedToToDoList = toDoPage
                .userSelectAddItemOption()
                .userAddNewItemTitle((String) testData.get("toDoItemTitle"))
                .userSelectedDoneOption()
                .isNewItemAddedToToDoList((String) testData.get("toDoItemTitle"));

        Assert.isTrue(isNewItemAddedToToDoList, "New Item not added to Todo list");

    }

    @Test
    public void userShouldBeAbleToMarkAndDeleteAToDOItem() {
        ToDoPage toDoPage = new ToDoPage(driver);
        userShouldBeAbleToCreatesAndEnterItemToToDoList();
        boolean isItemMarkedCompleted = toDoPage
                .userMarksItemAsCompleted((String) testData.get("toDoItemTitle"))
                .isItemMarkedCompleted((String) testData.get("toDoItemTitle"));

        Assert.isTrue(isItemMarkedCompleted, "Item in todo list is  not marked as completed");

        boolean isItemDeleted = toDoPage
                .userSelectsDeletedMarkedItem()
                .isItemDeleted();
        Assert.isTrue(isItemDeleted, "Item in todo list is  not deleted");
    }

    @Test
    public void userShouldBeAbleToEditToDoTitleAndDescription() {
        ToDoPage toDoPage = new ToDoPage(driver);
        userShouldBeAbleToCreateAToDoList();

        // Edit description and title  and check New Description
        boolean isToDoListDescriptionEdited = toDoPage
                .userSelectsEditOption()
                .userAddDescription((String) testData.get("newDescription"))
                .userAddDTitle((String) testData.get("newTitle"))
                .userSelectedDoneOption()
                .isToDoDescriptionVisible((String) testData.get("newTitle"));

        Assert.isTrue(isToDoListDescriptionEdited, "New Description not as expected");

        //      Check new title
        boolean isToDoListTitleEdited = toDoPage.isCreatedToDoListVisible((String) testData.get("toDoItemTitle"));
        Assert.isTrue(isToDoListTitleEdited, "New title not as expected");
    }


    @Test
    public void userShouldBeAbleToEditAToDoItem() {
        ToDoPage toDoPage = new ToDoPage(driver);
        userShouldBeAbleToCreatesAndEnterItemToToDoList();

        // Edit title  and check New title of a todo item
        boolean isToDoItemTitleEdited = toDoPage
                .userSelectsEditOptionForAItem((String) testData.get("toDoItemTitle"))
                .userAddnewTitleForToDoItem((String) testData.get("newTodoItemTitle"))
                .userSelectedDoneOption()
                .isNewItemAddedToToDoList((String) testData.get("newTodoItemTitle"));
        Assert.isTrue(isToDoItemTitleEdited, "New title not as expected");

    }

    @AfterTest
    public void afterScenario() {
        driver.close();
        driver.quit();
    }
}
