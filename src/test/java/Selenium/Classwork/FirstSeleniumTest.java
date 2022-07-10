package Selenium.Classwork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class FirstSeleniumTest {


    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    String usernameValue = "vesuryalokin";
    String passWordValue = "N789855#";

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


        WebDriverManager.chromedriver().setup();

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testDemo() {

        driver.get("http://training.skillo-bg.com/");


        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        Assert.assertTrue(loginLink.isDisplayed());

        List<WebElement> listPost = driver.findElements(By.xpath("//div[@class='row post-list-container']//app-post-detail"));

        Assert.assertEquals(listPost.size(), 3);

        loginLink.click();

        var signInTxt = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInTxt.isDisplayed());
    }

    @Test
    public void testLoginPage() {

        driver.get("http://training.skillo-bg.com/users/login");

        WebElement userName = driver.findElement(By.cssSelector("input#defaultLoginFormUsername"));
        userName.clear();
        userName.sendKeys(usernameValue);

       /* actions.click(userName)
                .sendKeys(usernameValue)
                .perform();*/

        WebElement password = driver.findElement(By.cssSelector("input#defaultLoginFormPassword"));
        password.clear();
        password.sendKeys(passWordValue);
       /* actions.click(password)
                .sendKeys(passWordValue)
                .perform();*/


        WebElement signInBtn = driver.findElement(By.cssSelector("button#sign-in-button"));
        signInBtn.click();


        WebElement profileTab = driver.findElement(By.cssSelector("a#nav-link-profile"));
        WebElement newPostTab = driver.findElement(By.linkText("New post"));
        WebElement logOutBtn = driver.findElement(By.cssSelector(".fa-lg.fa-sign-out-alt.fas"));

        Assert.assertTrue(profileTab.isDisplayed());
        Assert.assertTrue(newPostTab.isDisplayed());
        Assert.assertTrue(logOutBtn.isDisplayed());


    }

    @Test()
    public void testMobileBG() {

        String carMarka = "Mini";
        String carModel = "Cooper";

        driver.get("https://www.mobile.bg/pcgi/mobile.cgi");

        By marka = By.xpath("//select[@name='marka']");
        By model = By.xpath("//select[@name='model']");
        By searchBtn = By.xpath("//input[@id='button2']");
        By toTheSite = By.xpath("//p[text()='Към сайта']");

        driver.findElement(toTheSite).click();

        Select markaDropdown = new Select(driver.findElement(marka));
        markaDropdown.selectByVisibleText(carMarka);

        Select modelDropdown = new Select(driver.findElement(model));
        modelDropdown.selectByVisibleText(carModel);

        driver.findElement(searchBtn).click();

        List<WebElement> listAd = driver.findElements(By.xpath("//form[@name='search']//a[@class='mmm']"));

        listAd.forEach(add -> {
            Assert.assertTrue(add.getText().contains(carMarka + " " + carModel));
        });

    }

}
