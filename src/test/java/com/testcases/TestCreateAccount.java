package com.testcases;

import com.pageObjects.CreateAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestCreateAccount {

    WebDriver driver = new ChromeDriver();
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        ExcelUtils.setExcelFile(Constant.pathTestData + Constant.fileTestData, "Sheet2");
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = "http://automationpractice.com/index.php";
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void testCreateAccount() throws Exception {
        CreateAccount createAccount = PageFactory.initElements(driver, CreateAccount.class);

        WebDriverWait wait = new WebDriverWait(driver, 30);

        createAccount.SignIn();

//        createAccount.setEmail_create("automationtest3@test.com");
//        createAccount.SubmitCreate();

        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id=\"submitAccount\"]")));

        createAccount.CreateAccount();

        Select drpDays = new Select(driver.findElement(By.id("days")));
        drpDays.selectByValue("7");
        Select drpMonths = new Select(driver.findElement(By.id("months")));
        drpMonths.selectByValue("10");
        Select drpYears = new Select(driver.findElement(By.id("years")));
        drpYears.selectByValue("2000");
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByValue("47");
        Select country = new Select(driver.findElement(By.id("id_country")));
        country.selectByValue("21");
/*
        createAccount.firstName("Bob");
        createAccount.lastName("Sponge");
        createAccount.Password("spong3B0b@");
        Select drpDays = new Select(driver.findElement(By.id("days")));
        drpDays.selectByValue("7");
        Select drpMonths = new Select(driver.findElement(By.id("months")));
        drpMonths.selectByValue("10");
        Select drpYears = new Select(driver.findElement(By.id("years")));
        drpYears.selectByValue("2000");
        createAccount.checkBoxes();
        createAccount.firstName("Bob");
        createAccount.lastName("Sponge");
        createAccount.Company("Expleo");
        createAccount.addressInfo("29 Salisbury Avenue", "Technology House, First Floor", "Westville", "98002");
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByValue("47");
        Select country = new Select(driver.findElement(By.id("id_country")));
        country.selectByValue("21");
        createAccount.setPhone("0311234569", "0794561237");
        createAccount.aliasEmail("automationtest2@test.com");
        createAccount.setSubmitAccount();
        createAccount.SignOut();*/
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
