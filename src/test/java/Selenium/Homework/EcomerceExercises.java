package Selenium.Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class EcomerceExercises {
    Random random = new Random();
    String emailValue = "neitozemail" + String.valueOf(random.nextInt(1, 100)) + "@abv.bg";
    String passValue = "AutoPass123";
    String dateOfBirthDayValue = "5";
    String dateOfBirthMonthValue = "August";
    String dateOfBirthYearValue = "1998";
    String companyNameValue = "TestCompany" + String.valueOf(random.nextInt(1, 100));
    String address1Value = "Test address street, " + String.valueOf(random.nextInt(1, 300));
    String additionalInfoValue = "This is automation test №: " + String.valueOf(random.nextInt(1, 1000));
    String mobilePhoneValue = "0889" + String.valueOf(random.nextInt(100000, 999999));

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    private static final String url = "http://automationpractice.com/index.php";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void Test_RegisterUser() {

//        1. Test Case - Automate User Registration process of e-commerce website.
//                Steps to Automate:
//        1. Open this url  http://automationpractice.com/index.php
//        2. Click on sign in link.
//        3. Enter your email address in 'Create and account' section.
//        4. Click on Create an Account button.
//        5. Enter your Personal Information, Address and Contact info.
//        6. Click on Register button.
//        7. Validate that user is created.

        driver.navigate().to(url);

        var loginBtn = driver.findElement(By.linkText("Sign in"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();


        var emailCreate = driver.findElement(By.cssSelector("input#email_create"));
        emailCreate.clear();
        emailCreate.sendKeys(emailValue);

        var createAnAccountBtn = driver.findElement(By.cssSelector("button#SubmitCreate "));
        createAnAccountBtn.click();

        var accountCreationForm = driver.findElement(By.xpath("/html//form[@id='account-creation_form']")).isDisplayed();
        Assert.assertTrue(accountCreationForm);

        // Reg as a male
        var maleGender = driver.findElement(By.xpath("//input[@id='id_gender1']"));
        maleGender.click();

//        // Reg as female
//        var wmnGender = driver.findElement(By.xpath("//input[@id='id_gender2']"));
//        wmnGender.click();

        var firstName = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
        firstName.clear();
        firstName.sendKeys("FirstnameTest");

        var lastName = driver.findElement(By.cssSelector("input#customer_lastname"));
        lastName.clear();
        lastName.sendKeys("LastnameTest");

        var email = driver.findElement(By.xpath("//input[@id='email']")).isDisplayed();
        Assert.assertNotEquals(email, " ");

        var password = driver.findElement(By.cssSelector("input#passwd"));
        password.clear();
        password.sendKeys(passValue);

        var dateOfBirthDay = driver.findElement(By.cssSelector("select#days"));
        dateOfBirthDay.sendKeys(dateOfBirthDayValue);

        var dateOfBirthMonth = driver.findElement(By.xpath("//select[@id='months']"));
        dateOfBirthMonth.sendKeys(dateOfBirthMonthValue);

        var dateOfBirthYear = driver.findElement(By.cssSelector("select#years"));
        dateOfBirthYear.sendKeys(dateOfBirthYearValue);

        var newsletterSingUp = driver.findElement(By.xpath("//label[.='Sign up for our newsletter!']"));
        //Optional
        // newsletterSingUp.Click();
        Assert.assertTrue(newsletterSingUp.isDisplayed());

        var specialOffersSubscribe = driver.findElement(By.xpath("//label[.='Receive special offers from our partners!']"));
//        Optional
//        specialOffersSubscribe.click();
        Assert.assertTrue(specialOffersSubscribe.isDisplayed());

        var companyName = driver.findElement(By.cssSelector("input#company"));
        companyName.clear();
        companyName.sendKeys(companyNameValue);

        var address1 = driver.findElement(By.cssSelector("input[name='address1']"));
        address1.clear();
        address1.sendKeys(address1Value);

        var city = driver.findElement(By.xpath("//input[@id='city']"));
        city.clear();
        city.sendKeys("Varna");

        var state = driver.findElement(By.cssSelector("select#id_state"));
        state.sendKeys("Alaska");

        var postCodeZip = driver.findElement(By.xpath("//input[@id='postcode']"));
        postCodeZip.sendKeys(String.valueOf(random.nextInt(10000, 99999)));

        var country = driver.findElement(By.cssSelector("select#id_country"));
        country.sendKeys("United States");

        var additionalInfo = driver.findElement(By.xpath("//textarea[@id='other']"));
        additionalInfo.clear();
        additionalInfo.sendKeys(additionalInfoValue);

        var mobilePhone = driver.findElement(By.cssSelector("input#phone_mobile"));
        mobilePhone.clear();
        mobilePhone.sendKeys(mobilePhoneValue);

        var registerBtn = driver.findElement(By.xpath("//button[@id='submitAccount']"));
        registerBtn.click();

        var myAccount = driver.findElement(By.xpath("//span[@class='navigation_page']"));

        Assert.assertTrue(myAccount.isDisplayed());

        var myPersonalInfo = driver.findElement(By.xpath("//span[.='My personal information']"));
        Assert.assertTrue(myPersonalInfo.isDisplayed());


    }

    @Test
    public void test_invalidEmail() {

//        2. Test Case - Verify invalid email address error.
//        Steps to Automate:
//        1. Open this url  http://automationpractice.com/index.php
//        2. Click on sign in link.
//        3. Enter invalid email address in the email box and click enter.
//        4. Validate that an error message is displaying saying "Invalid email address."


        driver.navigate().to(url);

        var loginBtn = driver.findElement(By.linkText("Sign in"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();


        var emailCreate = driver.findElement(By.cssSelector("input#email_create"));
        emailCreate.clear();
        emailCreate.sendKeys("invalidemaiasldjnadsh");

        var createAnAccountBtn = driver.findElement(By.cssSelector("button#SubmitCreate "));
        createAnAccountBtn.click();

        var errorMsgEmail = driver.findElement(By.xpath("//li[.='Invalid email address.']"));
        Assert.assertTrue(errorMsgEmail.isDisplayed());
    }

    @Test
    public void test_MandatoryFields() {
        //    3. Test Case - Verify error messages for mandatory fields.
//    Steps to Automate:
//            1. Open this url  http://automationpractice.com/index.php
//            2. Click on sign in link.
//            3. Enter email address and click Register button.
//            4. Leave the mandatory fields (marked with *) blank and click Register button.
//            5. Verify that error has been displayed for the mandatory fields.


        driver.navigate().to(url);

        var loginBtn = driver.findElement(By.linkText("Sign in"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));

        loginBtn.click();

        var emailCreate = driver.findElement(By.cssSelector("input#email_create"));
        emailCreate.clear();
        emailCreate.sendKeys(emailValue);

        var createAnAccountBtn = driver.findElement(By.cssSelector("button#SubmitCreate "));
        createAnAccountBtn.click();

        var accountCreationForm = driver.findElement(By.xpath("/html//form[@id='account-creation_form']")).isDisplayed();
        Assert.assertTrue(accountCreationForm);

        // Reg as a male
        var maleGender = driver.findElement(By.xpath("//input[@id='id_gender1']"));
        maleGender.click();


//        // Reg as female
//        var wmnGender = driver.findElement(By.xpath("//input[@id='id_gender2']"));
//        wmnGender.click();

        var firstName = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
        firstName.clear();


        var lastName = driver.findElement(By.cssSelector("input#customer_lastname"));
        lastName.clear();


        var email = driver.findElement(By.xpath("//input[@id='email']"));
        email.clear();


        var address1 = driver.findElement(By.cssSelector("input[name='address1']"));
        address1.clear();


        var city = driver.findElement(By.xpath("//input[@id='city']"));
        city.clear();


        var postCodeZip = driver.findElement(By.xpath("//input[@id='postcode']"));
        postCodeZip.clear();


        var additionalInfo = driver.findElement(By.xpath("//textarea[@id='other']"));
        additionalInfo.clear();
        additionalInfo.sendKeys(additionalInfoValue);

        var mobilePhone = driver.findElement(By.cssSelector("input#phone_mobile"));
        mobilePhone.clear();

        var assignAddress = driver.findElement(By.xpath("//input[@id='alias']"));
        assignAddress.clear();

        var registerBtn = driver.findElement(By.xpath("//button[@id='submitAccount']"));
        registerBtn.click();


        var atLeast1Phone = driver.findElement(By.xpath("//li[.='You must register at least one phone number.']"));
        Assert.assertTrue(atLeast1Phone.isDisplayed());

        var lastnameRequired = driver.findElement(By.xpath("//ol/li[2]"));
        Assert.assertTrue(lastnameRequired.isDisplayed());

        var firstnameRequired = driver.findElement(By.xpath("//ol/li[3]"));
        Assert.assertTrue(firstnameRequired.isDisplayed());

        var emailRequired = driver.findElement(By.xpath("//ol/li[4]"));
        Assert.assertTrue(emailRequired.isDisplayed());

        var passwdRequired = driver.findElement(By.xpath("//ol/li[5]"));
        Assert.assertTrue(passwdRequired.isDisplayed());

        var countryRequired = driver.findElement(By.xpath("//ol/li[6]"));
        Assert.assertTrue(countryRequired.isDisplayed());

        var aliasRequired = driver.findElement(By.xpath("//ol/li[7]"));
        Assert.assertTrue(aliasRequired.isDisplayed());

        var addressRequired = driver.findElement(By.xpath("//ol/li[8]"));
        Assert.assertTrue(addressRequired.isDisplayed());

        var cityRequired = driver.findElement(By.xpath("//ol/li[9]"));
        Assert.assertTrue(cityRequired.isDisplayed());

        var countryCannotBeLoaded = driver.findElement(By.xpath("//ol/li[10]"));
        Assert.assertTrue(countryCannotBeLoaded.isDisplayed());


    }






}
