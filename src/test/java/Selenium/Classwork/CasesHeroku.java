package Selenium.Classwork;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.datatransfer.Transferable;
import java.time.Duration;
import java.util.List;

public class CasesHeroku {
    WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    String url = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver.manage().window().maximize();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //init actions;
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Test_AddRemoveElements() {
        driver.navigate().to(url + "/add_remove_elements/");

        var addElementBtn = driver.findElement(By.cssSelector(".example> button"));

        By listElements = By.xpath("//div[@id='elements']/button");
        List<WebElement> listDeletes = driver.findElements(listElements);

        Assert.assertEquals(listDeletes.size(), 0);

        addElementBtn.click();
        addElementBtn.click();

        listDeletes = driver.findElements(listElements);
        Assert.assertEquals(listDeletes.size(), 2);

        listDeletes.get(0).click();

        listDeletes = driver.findElements(listElements);
        Assert.assertEquals(listDeletes.size(), 1);

    }

    @Test
    public void test_BasicAuth() throws InterruptedException {
        driver.navigate().to("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        //   Thread.sleep(2000);

        var basicAuthTitle = driver.findElement(By.cssSelector(".example > h3"));
        var congratzMsg = driver.findElement(By.cssSelector(".example > p")).isDisplayed();
        Assert.assertTrue(congratzMsg);
        Assert.assertTrue(basicAuthTitle.isDisplayed());
    }

    @Test
    public void test_Checkboxes() {
        driver.navigate().to(url + "/checkboxes");

        By checkboxes = By.cssSelector("#checkboxes input");
        var checkbox1 = driver.findElements(checkboxes).get(0);
        var checkbox2 = driver.findElements(checkboxes).get(1);

        Assert.assertTrue(checkbox2.isSelected());
        Assert.assertFalse(checkbox1.isSelected());


        checkbox1.click();
        checkbox2.click();

        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertFalse(checkbox2.isSelected());
    }

    @Test
    public void test_ContextMenu() {
        driver.navigate().to(url + "/context_menu");

        var divHotspot = driver.findElement(By.cssSelector("div#hot-spot"));

        actions.contextClick(divHotspot).perform();
        Alert alert = driver.switchTo().alert();


        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();

        driver.switchTo().defaultContent();
        Assert.assertTrue(divHotspot.isDisplayed());

    }

    @Test
    public void test_DragNDrop() {
        driver.navigate().to("https://jqueryui.com/droppable/");

        var iFrame = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iFrame);

        var draggable = driver.findElement(By.cssSelector("div#draggable"));
        var droppable = driver.findElement(By.cssSelector("div#droppable"));
        var droppedText = driver.findElement(By.xpath("//div[@id='droppable']/p"));

        Assert.assertEquals(droppedText.getText(), "Drop here");
        actions.dragAndDrop(draggable, droppable).perform();
        Assert.assertEquals(droppedText.getText(), "Dropped!");

        driver.switchTo().parentFrame();
    }

    @Test
    public void test_DisappearingElements() {

        driver.navigate().to(url + "/disappearing_elements");


        var gallery = driver.findElement(By.xpath("//a[@href='/gallery/']"));
        gallery.sendKeys(Keys.F5);
        // driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(gallery));

        Assert.assertTrue(gallery.isDisplayed());

     /*   gallery.sendKeys(Keys.F5);
        wait.until(ExpectedConditions.invisibilityOf(gallery));
        Assert.assertFalse(gallery.isDisplayed());*/

    }

    @Test
    public void test_DropdownList() {
        driver.navigate().to(url + "/dropdown");

        var pageTitle = driver.findElement(By.cssSelector("h3"));
        Assert.assertEquals(pageTitle.getText(), "Dropdown List");

        driver.findElement(By.id("dropdown")).click();

        var dropdown = driver.findElement(By.id("dropdown"));
        var option1 = dropdown.findElement(By.xpath("//option[. = 'Option 1']"));
        option1.click();
        Assert.assertEquals(option1.getText(), "Option 1");

        driver.findElement(By.id("dropdown")).click();

        var option2 = dropdown.findElement(By.xpath("//option[. = 'Option 2']"));
        option2.click();
        Assert.assertEquals(option2.getText(), "Option 2");

    }

    @Test
    public void test_DynamicContent() {
        driver.navigate().to(url + "/dynamic_content");

        var dynamicContentTitle = driver.findElement(By.cssSelector("h3"));
        Assert.assertEquals(dynamicContentTitle.getText(), "Dynamic Content");

        var subTitle = driver.findElement(By.xpath("//div[@id='content']/div/p[1]"));
        Assert.assertEquals(subTitle.getText(), "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.");

        var text3Before = driver.findElement(By.xpath("//div[3]/div[2]")).getText();

        var clickHere = driver.findElement(By.xpath("//a[@href='/dynamic_content?with_content=static']"));

        clickHere.click();

        var text3After = driver.findElement(By.xpath("//div[3]/div[2]")).getText();

        Assert.assertNotEquals(text3After, text3Before);

    }

    @Test
    public void test_DynamicControls() throws InterruptedException {

        driver.navigate().to(url + "/dynamic_controls");
        var dynamicControls = driver.findElement(By.xpath("//h4[.='Dynamic Controls']"));
        Assert.assertEquals(dynamicControls.getText(), "Dynamic Controls");

        // Remove/Add section
        var checkbox = driver.findElement(By.cssSelector("div#checkbox"));
        Assert.assertTrue(checkbox.isDisplayed());

        var buttonRemoveAdd = driver.findElement(By.xpath("//form[@id='checkbox-example']/button[@type='button']"));
        Assert.assertEquals(buttonRemoveAdd.getText(), "Remove");

        buttonRemoveAdd.click();
        Assert.assertFalse(checkbox.isSelected());
        Thread.sleep(3000);
        Assert.assertEquals(buttonRemoveAdd.getText(), "Add");

        var message = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message.getText(), ("It's gone!"));

        buttonRemoveAdd.click();
        Thread.sleep(3000);
        Assert.assertEquals(buttonRemoveAdd.getText(), "Remove");

        var checkboxAfter = driver.findElement(By.cssSelector("input#checkbox"));
        var messageAfter = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(checkboxAfter.isDisplayed());
        // Thread.sleep(3000);
        Assert.assertTrue(messageAfter.isDisplayed());
        Assert.assertEquals(messageAfter.getText(), ("It's back!"));

        // Enable/disable section

        var textField = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertTrue(textField.isDisplayed());

        var buttonEnableDisable = driver.findElement(By.xpath("//form[@id='input-example']/button[@type='button']"));
        Assert.assertEquals(buttonEnableDisable.getText(), "Enable");

        buttonEnableDisable.click();
        Thread.sleep(3000);
        Assert.assertEquals(buttonEnableDisable.getText(), "Disable");


    }

    @Test
    public void test_DynamicLoading() throws InterruptedException {

        driver.navigate().to(url + "/dynamic_loading");

        var example1 = driver.findElement(By.xpath("//a[@href='/dynamic_loading/1']"));
        example1.click();

        Thread.sleep(3000);

        var buttonStart = driver.findElement(By.cssSelector("button"));
        buttonStart.click();
        Thread.sleep(3000);


        var loadingImg = driver.findElement(By.cssSelector("#loading > img"));
        Assert.assertTrue(loadingImg.isDisplayed());
        Thread.sleep(3000);
        var afterLoading = driver.findElement(By.cssSelector("div#finish > h4"));
        Assert.assertTrue(afterLoading.isDisplayed());


        driver.navigate().to(url + "/dynamic_loading");
        var example2 = driver.findElement(By.xpath("//a[@href='/dynamic_loading/2']"));
        example2.click();

        var btnStart = driver.findElement(By.cssSelector("div#start > button"));
        btnStart.click();
        Thread.sleep(3000);

        var loading = driver.findElement(By.xpath("/html//div[@id='loading']"));
        Assert.assertTrue(loading.isDisplayed());
        Thread.sleep(3000);
        var helloWorld = driver.findElement(By.cssSelector("div#finish > h4"));
        Assert.assertTrue(helloWorld.isDisplayed());


    }

    @Test
    public void test_Hovers() {
        driver.navigate().to(url + "/hovers");

        var user1 = driver.findElement(By.xpath("//div[1]/img[@alt='User Avatar']"));
        actions.moveToElement(user1).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//h5[.='name: user1']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/users/1']")).isDisplayed());

        var user2 = driver.findElement(By.xpath("//div[2]/img[@alt='User Avatar']"));
        actions.moveToElement(user2).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//h5[.='name: user2']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/users/2']")).isDisplayed());

        var user3 = driver.findElement(By.xpath("//div[3]/img[@alt='User Avatar']"));
        actions.moveToElement(user3).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//h5[.='name: user3']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/users/3']")).isDisplayed());

    }

}






