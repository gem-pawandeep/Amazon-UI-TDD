package com.qa.Amazon.Utility;


import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.Amazon.Objects.Amazon_locators;
import org.openqa.selenium.By;
import java.io.IOException;


public class Common_functions {
    public static void search(String item) throws IOException {
      STATUS flag;
       if(DriverAction.isExist(Amazon_locators.search_Box)){
         DriverAction.typeText(Amazon_locators.search_Box, item, "Element to be search");
       }
       DriverAction.click(Amazon_locators.search_Button, "Search");
    }

    public static void signInAmazon(String email, String pass) throws IOException {
        STATUS flag;
        flag=DriverAction.click(Amazon_locators.sign_in_button, "Sign in");
        if(flag!=STATUS.PASS){
            return;
        }
        flag=DriverAction.typeText(Amazon_locators.user_name_box, email, "Email");
        if(flag!=STATUS.PASS){
            return;
        }
        flag=DriverAction.click(Amazon_locators.Continue_button, "Continue");
        if(flag!=STATUS.PASS){
            return;
        }
        GemTestReporter.addTestStep("Typing Password", "********", STATUS.PASS);
        flag=DriverAction.typeText(Amazon_locators.user_password_box, pass);
        if(flag!=STATUS.PASS){
            return;
        }
        DriverAction.click(Amazon_locators.Continue_button, "Sign in");
    }

    public static STATUS hyperLinkValidation(By link, String item) throws IOException {
       STATUS flag;
        flag=DriverAction.click(link, item);
        if(flag!=STATUS.PASS){
            return STATUS.PASS;
        }
        String verify = DriverAction.getCurrentURL();
        if (verify.contains(item)) {
            GemTestReporter.addTestStep("Validation Successful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.PASS,DriverAction.takeSnapShot());
        return STATUS.FAIL;
        } else {
            GemTestReporter.addTestStep("Validation Unsuccessful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.FAIL,DriverAction.takeSnapShot());
       return STATUS.FAIL;
        }
    }
}
