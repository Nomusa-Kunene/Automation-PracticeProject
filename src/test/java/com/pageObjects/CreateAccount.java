package com.pageObjects;

import com.testcases.Constant;
import com.testcases.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.FileInputStream;

public class CreateAccount {

    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    private WebElement signIn;

    //YOUR PERSONAL INFORMATION
    private WebElement email_create;
    private WebElement SubmitCreate;
    private WebElement customer_firstname;
    private WebElement customer_lastname;
    private WebElement passwd;
    @FindBy(how = How.ID_OR_NAME, using = "newsletter")
    private WebElement newsletter;
    @FindBy(how = How.ID_OR_NAME, using = "optin")
    private WebElement optin;

    //YOUR ADDRESS
    private WebElement company;
    private WebElement address1;
    private WebElement address2;
    private WebElement city;
    private WebElement postcode;

    private WebElement phone;
    private WebElement phone_mobile;
    private WebElement alias;
    private WebElement submitAccount;

    //creating an account with an email address that already exists
    @FindBy(how = How.XPATH, using = "//*[@id=\"create_account_error\"]/ol/li")
    private WebElement accountExists;

    //SIGN OUT
    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
    private WebElement signOut;

    private static XSSFSheet mySheet;
    private static XSSFWorkbook myWorkbook;

    public String newEmail, fname, lname, Passwrd;
    public String CompanyName, streetAddress, buildingAddr, cityAddress, addressCode, Tel, Mobile, altEmail;

    public void SignIn(){
        signIn.click();
    }

    public void CreateAccount() throws Exception{

        FileInputStream ExcelFile = new FileInputStream(Constant.pathTestData + Constant.fileTestData);

        //access required data
        myWorkbook = new XSSFWorkbook(ExcelFile);
        mySheet = myWorkbook.getSheet("Sheet1");

        try{
            for(int i = 1; i <= mySheet.getLastRowNum(); i++){

                newEmail = ExcelUtils.getCellData(i, 1);
                email_create.clear();
                email_create.sendKeys(newEmail);

                SubmitCreate.click();

                Thread.sleep(3000);

                fname = ExcelUtils.getCellData(i, 2);
                customer_firstname.clear();
                customer_firstname.sendKeys(fname);

                lname = ExcelUtils.getCellData(i, 3);
                customer_lastname.clear();
                customer_lastname.sendKeys(lname);

                Passwrd = ExcelUtils.getCellData(i, 4);
                passwd.clear();
                passwd.sendKeys(Passwrd);

                CompanyName = ExcelUtils.getCellData(i, 5);
                company.clear();
                company.sendKeys(CompanyName);

                streetAddress = ExcelUtils.getCellData(i, 6);
                address1.clear();
                address1.sendKeys(streetAddress);

                buildingAddr = ExcelUtils.getCellData(i, 7);
                address2.clear();
                address2.sendKeys(buildingAddr);

                cityAddress = ExcelUtils.getCellData(i, 8);
                city.clear();
                city.sendKeys(cityAddress);

                addressCode = ExcelUtils.getCellData(i, 9);
                postcode.clear();
                postcode.sendKeys(addressCode);

                Tel = ExcelUtils.getCellData(i, 10);
                phone.clear();
                phone.sendKeys(Tel);

                Mobile = ExcelUtils.getCellData(i, 11);
                phone_mobile.clear();
                phone_mobile.sendKeys(Mobile);

                altEmail = ExcelUtils.getCellData(i, 12);
                alias.clear();
                alias.sendKeys(altEmail);

                submitAccount.click();
                ExcelUtils.setCellData("PASSED", i, 13);

                signOut.click();
            }

        } catch (Exception e){
            throw(e);
        }
    }

    /*public void setEmail_create(String emailText){

        email_create.clear();
        email_create.sendKeys(emailText);
    }

    public void SubmitCreate(){
        SubmitCreate.click();
    }

    *//*public void accountError() throws InterruptedException {
        for (int i = 1; i <= 3; i++) {
            Thread.sleep(3000);
            if (accountExists.isDisplayed()) {
                email_create.clear();
                email_create.sendKeys("automationtest" + i + "@test.com");
                System.out.println("automationtest" + i + "@test.com");
                SubmitCreate.click();
            }
            else{
                break;
            }
        }
    }*//*

    public void firstName(String text){
        customer_firstname.clear();
        customer_firstname.sendKeys(text);
    }

    public void lastName(String text){
        customer_lastname.clear();
        customer_lastname.sendKeys(text);
    }

    public void Password(String text){
        passwd.clear();
        passwd.sendKeys(text);
    }

    public void checkBoxes(){
        newsletter.click();
        optin.click();
    }

    public void Company(String text){
        company.clear();
        company.sendKeys(text);
    }

    public void addressInfo(String addr1, String addr2, String City, String postCode){
        address1.clear();
        address1.sendKeys(addr1);

        address2.clear();
        address2.sendKeys(addr2);

        city.clear();
        city.sendKeys(City);

        postcode.clear();
        postcode.sendKeys(postCode);
    }

    public void setPhone(String phone1, String phone2){
        phone.clear();
        phone.sendKeys(phone1);

        phone_mobile.clear();
        phone_mobile.sendKeys(phone2);
    }

    public void aliasEmail(String alias_address){
        alias.clear();
        alias.sendKeys(alias_address);
    }

    public void RegisterAccount(){
        registerAccount.click();
    }

    public void SignOut(){
        signOut.click();
    }*/
}
