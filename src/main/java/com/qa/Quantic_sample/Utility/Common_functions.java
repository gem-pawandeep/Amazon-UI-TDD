package com.qa.Quantic_sample.Utility;


import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import org.openqa.selenium.By;
import java.io.IOException;


public class Common_functions {
    public static void search(String item) throws IOException {
        boolean flag;
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag=DriverAction.typeText(Amazon_locators.search_Box, item, "Element to be search");
        if(!flag){
            return;
        }
       flag= DriverAction.click(Amazon_locators.search_Button, "Search");
        if(!flag){
            return;
        }

    }

    public static void signInAmazon(String email, String pass) throws IOException {
        boolean flag;
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag=DriverAction.click(Amazon_locators.sign_in_button, "Sign in");
        if(!flag){
            return;
        }
        flag=DriverAction.typeText(Amazon_locators.user_name_box, email, "Email");
        if(!flag){
            return;
        }
        flag=DriverAction.click(Amazon_locators.Continue_button, "Continue");
        if(!flag){
            return;
        }
        GemTestReporter.addTestStep("Typing Password", "********", STATUS.PASS);
        flag=DriverAction.typeText(Amazon_locators.user_password_box, pass);
        if(!flag){
            return;
        }
        flag=DriverAction.click(Amazon_locators.Continue_button, "Sign in");
        if(!flag){
            return;
        }
    }

    public static boolean hyperLinkValidation(By link, String item) throws IOException {
       boolean flag;
        flag=DriverAction.click(link, item);
        if(!flag){
            return false;
        }
        String verify = DriverAction.getCurrentURL();
        if (verify.contains(item)) {
            GemTestReporter.addTestStep("Validation Successful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.PASS,DriverAction.takeSnapShot());
        return true;
        } else {
            GemTestReporter.addTestStep("Validation Unsuccessful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.FAIL,DriverAction.takeSnapShot());
       return false;
        }
    }
}
