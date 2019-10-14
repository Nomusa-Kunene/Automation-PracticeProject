package com.pageObjects;

import com.testcases.Constant;
import com.testcases.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.FileInputStream;

public class SignIn {
    //WebElements
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    private WebElement signIn;
    private WebElement email;
    @FindBy(how = How.XPATH, using = "//*[@id=\"passwd\"]")
    private WebElement passwd;
    private WebElement SubmitLogin;
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
    private WebElement signOut;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div[1]/p")
    private WebElement formError;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/h1")
    private WebElement myAccount;

    public String userEmail;
    public String userPassword;
    private WebDriver driver = null;

    private static XSSFSheet mySheet;
    private static XSSFWorkbook myWorkbook;

    public void mySignIn() throws Exception{

        //open excel file
        FileInputStream ExcelFile = new FileInputStream(Constant.pathTestData + Constant.fileTestData);

        //access required data
        myWorkbook = new XSSFWorkbook(ExcelFile);
        mySheet = myWorkbook.getSheet("Sheet1");

        System.out.println("" + mySheet.getLastRowNum());

        try {
            //for loop to read through through the file and write in results.
            for (int i = 1; i <= mySheet.getLastRowNum(); i++) {
                userEmail = ExcelUtils.getCellData(i, 1);
                userPassword = ExcelUtils.getCellData(i, 2);

                email.clear();
                email.sendKeys(userEmail);

                passwd.clear();
                passwd.sendKeys(userPassword);

                SubmitLogin.click();

                ExcelUtils.setCellData("PASSED", i, 3);

                signOut.click();

            }
        } catch (Exception e){
            throw (e);
        }
    }

    public void SignInLink(){
        signIn.click();
    }

    /*public void setEmail(){
        email.clear();
        email.sendKeys(userEmail);
    }

    public void setPasswd(){
        passwd.clear();
        passwd.sendKeys(userPassword);
    }

    public void setSubmitLogin(){

        SubmitLogin.click();
    }

    public void SignOut(){
        signOut.click();
    }
    */
}
