package com.testcases;

import com.pageObjects.SignIn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestSignIn {

    WebDriver driver = new ChromeDriver();
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        ExcelUtils.setExcelFile(Constant.pathTestData + Constant.fileTestData, "Sheet1");
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = "http://automationpractice.com/index.php";
        driver.get(baseUrl);
        driver.manage().window().maximize();

    }

    @Test
    public void testSignIn() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Page object for signing in with the account created above.
        SignIn signIn = PageFactory.initElements(driver, SignIn.class);

        signIn.SignInLink();
        /*signIn.setEmail();
        signIn.setPasswd();
        signIn.setSubmitLogin();*/

        signIn.mySignIn();
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
