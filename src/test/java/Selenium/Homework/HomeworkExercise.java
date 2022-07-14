package Selenium.Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class HomeworkExercise {

    Random random = new Random();
    String usernameValue = "auto" + String.valueOf(random.nextInt(1, 99));
    String captionValue = "autoCaption" + String.valueOf(random.nextInt(1, 99));
    String emailValue = "auto" + String.valueOf(random.nextInt(1, 99)) + "@test.com";
    String passwordValue = "Auto1234";

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    private static final String url = "http://training.skillo-bg.com/";

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebDriverManager.chromedriver().setup();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test_createAccount() {
        driver.navigate().to(url);

        var loginBtn = driver.findElement(By.cssSelector("a#nav-link-login"));
        loginBtn.click();

        var registerBtn = driver.findElement(By.linkText("Register"));

        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        Assert.assertTrue(registerBtn.isDisplayed());

        registerBtn.click();

        var signUph4 = driver.findElement(By.xpath("//h4[.='Sign up']"));

        Assert.assertTrue(signUph4.isDisplayed());

        var usernameField = driver.findElement(By.cssSelector("input[name='username']"));
        usernameField.sendKeys(usernameValue);

        var emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.sendKeys(emailValue);

        var passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(passwordValue);

        var confirmPasswordField = driver.findElement(By.xpath("//input[@name='verify-password']"));
        confirmPasswordField.sendKeys(passwordValue);

        var signInBtn = driver.findElement(By.xpath("//button[@id='sign-in-button']"));
        signInBtn.click();

        var profileNav = driver.findElement(By.linkText("Profile"));
        wait.until(ExpectedConditions.visibilityOf(profileNav));

        Assert.assertTrue(profileNav.isDisplayed());

    }

    @Test
    public void test_newPost() {
        driver.navigate().to(url);

        var loginNav = driver.findElement(By.linkText("Login"));
        loginNav.click();


        var signUph4 = driver.findElement(By.xpath("//p[@class='h4 mb-4']"));

        Assert.assertTrue(signUph4.isDisplayed());

        var usernameField = driver.findElement(By.xpath("//input[@id='defaultLoginFormUsername']"));
        usernameField.sendKeys("nikobrrratski1");

        var passwordField = driver.findElement(By.xpath("//input[@id='defaultLoginFormPassword']"));
        passwordField.sendKeys("neitozipass");

        var signInBtn = driver.findElement(By.cssSelector("button#sign-in-button"));
        signInBtn.click();

        var newPostNav = driver.findElement(By.linkText("New post"));
        wait.until(ExpectedConditions.visibilityOf(newPostNav));

        newPostNav.click();

        var newPostFormTitle = driver.findElement(By.xpath("//form/h3[@class='text-center']"));
        Assert.assertTrue(newPostFormTitle.isDisplayed());

        var browseBtn = driver.findElement(By.xpath("//button[@id='choose-file']"));
        browseBtn.sendKeys("C:\\Users\\Brrratski-PC\\Desktop\\corgi.jpg");

        var uploadImg = driver.findElement(By.xpath("//div[@class='uploadfilecontainer']"));
        uploadImg.sendKeys("C:\\Users\\Brrratski-PC\\Desktop\\corgi.jpg");

//        var dragged = driver.findElement(By.cssSelector("img#homeIcon"));
//        var dropped = driver.findElement(By.xpath("//div[@class='uploadfilecontainer']"));
//
//        actions.dragAndDrop(dragged, dropped)
//        .build()
//        .perform();

        var captionField = driver.findElement(By.xpath("//input[@name='caption']"));
        captionField.sendKeys(captionValue);

        //Create Public post
        var publicPost = driver.findElement(By.xpath("//label[.='Public']"));

        //Create Private post
//        var privatePost = driver.findElement(By.xpath("//label[.='Public']"));
//        privatePost.click();


        var submitBtn = driver.findElement(By.cssSelector("button#create-post"));
        submitBtn.click();


    }


    @Test
    public void test_EditProfileDetails() {

        driver.navigate().to(url);

        var loginNav = driver.findElement(By.linkText("Login"));
        loginNav.click();


        var signUph4 = driver.findElement(By.xpath("//p[@class='h4 mb-4']"));

        Assert.assertTrue(signUph4.isDisplayed());

        var usernameField = driver.findElement(By.xpath("//input[@id='defaultLoginFormUsername']"));
        usernameField.sendKeys("vesuryalokin");

        var passwordField = driver.findElement(By.xpath("//input[@id='defaultLoginFormPassword']"));
        passwordField.sendKeys("N789855#");

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//button[@id='sign-in-button']")).click();

        var profileNav = driver.findElement(By.linkText("Profile"));
        profileNav.click();

        var profileSettings = driver.findElement(By.xpath("//div[@class='col-12 col-lg-6 profile-user-settings']/i"));
        profileSettings.click();

        var profileSettingsForm = driver.findElement(By.xpath("//h4[.='Modify Your Profile']"));
        Assert.assertTrue(profileSettingsForm.isDisplayed());

        var editName = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
        editName.clear();
        editName.sendKeys("vesuryalokin");

        var editEmail = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
        editEmail.clear();
        editEmail.sendKeys("auto" + String.valueOf(random.nextInt(1,50)) + "@test.com");

        var editPassword = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
        editPassword.clear();
        editPassword.sendKeys("N789855#");

        var confirmPass = driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']"));
        confirmPass.clear();
        confirmPass.sendKeys("N789855#");

        var publicInfo = driver.findElement(By.xpath("//textarea[@formcontrolname='publicInfo']"));
        publicInfo.clear();
        publicInfo.sendKeys("This is automation test ");

        var saveBtn  = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        saveBtn.click();

        var description = driver.findElement(By.xpath("//p/text()")).getText();
        Assert.assertEquals(description,"This is automation test ");




    }
}
