package com.qa.Quantic_sample.Pages;


import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.Quantic_sample.Utility.Common_functions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.ArrayList;

import static com.gemini.generic.ui.utils.DriverAction.takeSnapShot;

public class Amazon {
    public static void ValidatingUrl() throws IOException {
        boolean flag;
        DriverAction.waitSec(3);
        String s = "";
        s = DriverAction.getTitle(DriverAction.getCurrentURL());
        if (s.contains("Amazon.in")) {
            GemTestReporter.addTestStep("Validating URL", "Expected: Amazon.in<br>Current: Amazon.in", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validating URL", "Expected: Amazon.in<br>Current: " + DriverAction.getTitle(DriverAction.getCurrentURL()), STATUS.FAIL, takeSnapShot());
        }
        flag = DriverAction.minimizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Minimize browser", STATUS.PASS);
        DriverAction.waitSec(2);
        flag = DriverAction.maximizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Maximize browser", STATUS.PASS);
        GemTestReporter.addTestStep("Size of browser", DriverAction.getBrowserSize().toString(), STATUS.PASS);
        flag = DriverAction.setBrowserSize(1200, 644, true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Browser Location", DriverAction.getBrowserLocation().toString(), STATUS.PASS);
    }

    public static void SignIn(String email, String pass) throws IOException {
        Common_functions.signInAmazon(email, pass);

    }

    public static void firstResultPrice(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag = DriverAction.click(Amazon_locators.first_result, "First result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Title of first Result", DriverAction.getElementText(Amazon_locators.tittle), STATUS.PASS, takeSnapShot());
        GemTestReporter.addTestStep("Price of first Result", DriverAction.getElementText(Amazon_locators.price1), STATUS.PASS);
        GemTestReporter.addTestStep("Action", "Closing the Current Tab", STATUS.PASS);
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Action", "Control back to Previous Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void lowToHigh(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(100);
        DriverAction.setScriptTimeOut(100);
        DriverAction.setPageLoadTimeOut(100);
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.low_high, "low to high");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "First result");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.second_result, "Second result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(2), true);
        if (!flag) {
            return;
        }
        int price2 = Integer.parseInt(DriverAction.getElementText(Amazon_locators.price));
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        int price1 = Integer.parseInt(DriverAction.getElementText(Amazon_locators.price));
        GemTestReporter.addTestStep("second result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
        if (price2 < price1) {
            GemTestReporter.addTestStep("Validate low to high", "Successful as " + price2 + " < " + price1, STATUS.PASS);
        } else if (price2 == price1) {
            GemTestReporter.addTestStep("Validate low to high", "Successful as " + price2 + " = " + price1, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validate low to high", "Unsuccessful", STATUS.FAIL);
        }
    }

    public static void highToLow(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.high_low, "high to low");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "First result");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.second_result, "Second result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(2), true);
        if (!flag) {
            return;
        }
        String temp = DriverAction.getElementText(Amazon_locators.price);
        String price = temp.replace(",", "");
        int price2 = Integer.parseInt(price);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        String temp1 = DriverAction.getElementText(Amazon_locators.price);
        String pricee = temp1.replace(",", "");
        int price1 = Integer.parseInt(pricee);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
        if (price2 > price1) {
            GemTestReporter.addTestStep("Validate high to low", "Successful as " + price2 + " > " + price1, STATUS.PASS);
        } else if (price2 == price1) {
            GemTestReporter.addTestStep("Validate high to low", "Successful as " + price2 + " = " + price1, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validate low to high", "Unsuccessful", STATUS.FAIL);
        }
    }

    public static void validateHyperlink(By link, String item) throws IOException {
        boolean flag;
       flag= Common_functions.hyperLinkValidation(link, item);
        if (!flag) {
            return;
        }
        flag = DriverAction.minimizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Minimize browser", STATUS.PASS);
        DriverAction.waitSec(2);
        flag = DriverAction.maximizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Maximize browser", STATUS.PASS);
        GemTestReporter.addTestStep("Size of browser", DriverAction.getBrowserSize().toString(), STATUS.PASS);
        flag = DriverAction.setBrowserSize(1200, 644, true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.setBrowserSize(1200, 644, true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Browser Location", DriverAction.getBrowserLocation().toString(), STATUS.PASS);
    }

    public static void maxPrice(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.high_low, "high to low");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.waitSec(2);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Control transfer to previous tab", "Successful", STATUS.PASS);
        DriverAction.waitSec(2);
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void minPrice(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.low_high, "low to high");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "result");
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Control transfer to previous tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void diffMaxMin(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.high_low, "high to low");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "First result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        String temp = DriverAction.getElementText(Amazon_locators.price);
        String price = temp.replace(",", "");
        int high = Integer.parseInt(price);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.low_high, "low to high");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "First result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb1 = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb1.get(1), true);
        if (!flag) {
            return;
        }
        String temp1 = DriverAction.getElementText(Amazon_locators.price);
        String price1 = temp1.replace(",", "");
        int low = Integer.parseInt(price1);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb1.get(0), true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Difference", " " + (high - low), STATUS.PASS);

    }

    public static void validateLanguage(String lang) throws IOException {
        boolean flag;
        flag = DriverAction.click(Amazon_locators.lang_button, "language");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        if (lang.equals("hi")) {
            flag = DriverAction.click(Amazon_locators.hindi, "Hindi");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String hindi = DriverAction.getCurrentURL();
            if (hindi.contains("hi")) {
                GemTestReporter.addTestStep("Validation", "Current page in Hindi Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Hindi Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("ta")) {
            flag = DriverAction.click(Amazon_locators.tamil, "Tamil");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("ta")) {
                GemTestReporter.addTestStep("Validation", "Current page in Tamil Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Tamil Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("te")) {
            flag = DriverAction.click(Amazon_locators.telgu, "Telgu");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("te")) {
                GemTestReporter.addTestStep("Validation", "Current page in Telgu Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Telgu Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("kn")) {
            flag = DriverAction.click(Amazon_locators.Kannda, "Kannada");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("kn")) {
                GemTestReporter.addTestStep("Validation", "Current page in Kannada Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Kannada Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("ml")) {
            flag = DriverAction.click(Amazon_locators.malyalam, "Malyalam");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("ml")) {
                GemTestReporter.addTestStep("Validation", "Current page in Malyalam Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Malyalam Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("bn")) {
            flag = DriverAction.click(Amazon_locators.bangla, "Bangla");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("bn")) {
                GemTestReporter.addTestStep("Validation", "Current page in Bangla Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Bangla Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("mr")) {
            flag = DriverAction.click(Amazon_locators.marathi, "Marathi");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("mr")) {
                GemTestReporter.addTestStep("Validation", "Current page in Marathi Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Marathi Language", STATUS.FAIL, takeSnapShot());
            }
        } else if (lang.equals("en")) {
            flag = DriverAction.click(Amazon_locators.English, "English");
            if (!flag) {
                return;
            }
            flag = DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            if (!flag) {
                return;
            }
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("en")) {
                GemTestReporter.addTestStep("Validation", "Current page in English Language<br>", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in English Language", STATUS.FAIL, takeSnapShot());
            }
        }

    }

    public static void alexaDot() throws IOException {
        boolean flag;
        flag = DriverAction.click(Amazon_locators.all, "All");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.echodot, "Echo & Alexa");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.item, "Echo Dot");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Title", DriverAction.getElementText(Amazon_locators.tittle), STATUS.PASS);
        GemTestReporter.addTestStep("Price", DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
    }

    public static void cartAfterAdding(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        flag = DriverAction.click(Amazon_locators.first_result, "first result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp1 = DriverAction.getElementText(Amazon_locators.tittle);
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(3);
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.cart_icon, "Cart");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        String temp2 = DriverAction.getElementText(Amazon_locators.cartTitle);
        String a = temp1.substring(0, 30);
        String b = temp2.substring(0, 30);
        if (a.equals(b)) {
            GemTestReporter.addTestStep("Validation", temp1 + "added Successfully to Cart", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validation", "Unsuccessful", STATUS.FAIL, takeSnapShot());
        }
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Action", "Control back to Previous Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void locationValidation(String pincode) throws IOException {
        boolean flag;
        flag = DriverAction.click(Amazon_locators.locationButton, "Location Button");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(3);
        flag = DriverAction.typeText(Amazon_locators.locationText, pincode, "Location");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.locationSubmit, "Apply");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(3);
        String temp = DriverAction.getElementText(Amazon_locators.locationValidate);
        if (temp.contains(pincode)) {
            GemTestReporter.addTestStep("Validate location", temp, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate location", temp, STATUS.FAIL, takeSnapShot());
        }
    }

    public static void countryValidation(String verify, By xpath, String name) throws IOException {
        boolean flag;
        flag = DriverAction.click(xpath, name);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String s = DriverAction.getCurrentURL();
        if (s.contains(verify)) {
            GemTestReporter.addTestStep("Validate Country", name, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validate Country ", name, STATUS.FAIL, takeSnapShot());
            return;
        }
        flag = DriverAction.minimizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Minimize browser", STATUS.PASS);
        DriverAction.waitSec(2);
        flag = DriverAction.maximizeBrowser(true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("action", "Maximize browser", STATUS.PASS);
        GemTestReporter.addTestStep("Size of browser", DriverAction.getBrowserSize().toString(), STATUS.PASS);
        flag = DriverAction.setBrowserSize(1200, 644, true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Browser Location", DriverAction.getBrowserLocation().toString(), STATUS.PASS);
    }

    public static void priceFilter(String item, String low, String high) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.waitSec(2);
        flag = DriverAction.typeText(Amazon_locators.lowPrice, low, "Minimum Price");
        if (!flag) {
            return;
        }
        flag = DriverAction.typeText(Amazon_locators.highPrice, high+(Keys.TAB)+(Keys.ENTER), "Maximum Price");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        int temp1 = Integer.parseInt(low);
        int temp2 = Integer.parseInt(high);
        flag = DriverAction.click(Amazon_locators.first_result, "first result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String price = DriverAction.getElementText(Amazon_locators.price);
        GemTestReporter.addTestStep("Result", "Item : " + DriverAction.getElementText(Amazon_locators.tittle)+"<br>Price: "+price, STATUS.PASS, takeSnapShot());
        price = price.replace(",", "");
        int pcs = Integer.parseInt(price);
        if (pcs > temp1 && pcs < temp2) {
            GemTestReporter.addTestStep("Validation Successful", "Price of " +DriverAction.getElementText(Amazon_locators.tittle) + " is between " + low + "-" + high, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validation Failed", "Price of " + DriverAction.getElementText(Amazon_locators.tittle) + " is not between " + low + "-" + high, STATUS.FAIL, takeSnapShot());
            return;
        }
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }

    }

    public static void addItemRemove(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        DriverAction.waitSec(3);
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.cart_icon, "Cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.cartDel, "Delete");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        String s = DriverAction.getElementText(Amazon_locators.cartCount);
        if (s.equals("0")) {
            GemTestReporter.addTestStep("Validate", "Cart is Empty", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate", "Cart is not Empty", STATUS.FAIL, takeSnapShot());
        }
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void cartValidateAfterNavigate(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        flag = DriverAction.click(Amazon_locators.first_result, "first result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp1 = DriverAction.getElementText(Amazon_locators.tittle);
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.cart_icon, "Cart");
        if (!flag) {
            return;
        }
        String temp2 = DriverAction.getElementText(Amazon_locators.cartTitle);
        String a = temp1.substring(0, 30);
        String b = temp2.substring(0, 30);
        String verify = "";
        if (a.equals(b)) {
            GemTestReporter.addTestStep("Validation", temp1 + " added Successfully to Cart", STATUS.PASS, takeSnapShot());
            verify = temp1;
        } else {
            GemTestReporter.addTestStep("Validation", "Unsuccessful to add", STATUS.FAIL, takeSnapShot());
        }
        flag = DriverAction.navigateToUrl("https://www.google.com/", true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.navigateBack(true);
        if (!flag) {
            return;
        }
        String temp11 = verify.substring(0, 30);
        String temp22 = DriverAction.getElementText(Amazon_locators.cartTitle);
        String temp222 = temp22.substring(0, 30);
        if (temp11.equals(temp222)) {
            GemTestReporter.addTestStep("Validation", verify + " is present in Cart", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validation", verify + " is not present in Cart", STATUS.FAIL, takeSnapShot());
        }
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void picodeValidation(String pincode) throws IOException {
        boolean flag;
        DriverAction.setScriptTimeOut(100);
        DriverAction.setPageLoadTimeOut(100);
        DriverAction.setImplicitTimeOut(100);
        locationValidation(pincode);
        flag = DriverAction.navigateToUrl("https://www.google.com/",true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.navigateBack(true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp1 = DriverAction.getElementText(Amazon_locators.locationValidate);
        if (temp1.contains(pincode)) {
            GemTestReporter.addTestStep("Validate location after navigating", temp1, STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate location after navigating", temp1, STATUS.FAIL, takeSnapShot());
        }
    }

    public static void validateCount(String item, String item2) throws IOException {
        boolean flag;
        Common_functions.search(item);
        flag = DriverAction.click(Amazon_locators.first_result, "first result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp1 = DriverAction.getElementText(Amazon_locators.tittle);
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Action", temp1 + " Successfully added in Cart", STATUS.PASS);
        flag = DriverAction.click(Amazon_locators.cart_icon, "Cart");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }
        flag = DriverAction.navigateBack(true);
        if (!flag) {
            return;
        }
        Common_functions.search(item2);
        flag = DriverAction.click(Amazon_locators.first_result, "first result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb1 = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        flag = DriverAction.switchToWindow(newTb1.get(1), true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp2 = DriverAction.getElementText(Amazon_locators.tittle);
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Action", temp2 + " Successfully added in Cart", STATUS.PASS);
        flag = DriverAction.click(Amazon_locators.cart_icon, "Cart");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        String count = DriverAction.getElementText(Amazon_locators.cartCount);
        GemTestReporter.addTestStep("Total items present in Cart", "1: " + temp1 + "<br>" + "2: " + temp2 + "<br>" + "Count: " + count, STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb1.get(0), true);
        if (!flag) {
            return;
        }
    }

    public static void totalCountSameItem(String item) throws IOException {
        boolean flag;
        Common_functions.search(item);
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.first_result, "result");
        if (!flag) {
            return;
        }
        ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        flag = DriverAction.switchToWindow(newTb.get(1), true);
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.addToCart, "Add to cart");
        if (!flag) {
            return;
        }
        flag = DriverAction.navigateBack(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.click(Amazon_locators.cart_icon, "cart");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String temp2 = DriverAction.getElementText(Amazon_locators.cartTitle);
        flag = DriverAction.click(Amazon_locators.cartDrpDwn, "drop-down");
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        flag = DriverAction.click(Amazon_locators.Quantity, "2");
        if (!flag) {
            return;
        }
        flag = DriverAction.refresh(true);
        if (!flag) {
            return;
        }
        DriverAction.waitSec(2);
        String count = DriverAction.getElementText(Amazon_locators.cartCount);
        GemTestReporter.addTestStep("Total items present in Cart", temp2 + "<br>" + count, STATUS.PASS, takeSnapShot());
        flag = DriverAction.closeCurrentTab(true);
        if (!flag) {
            return;
        }
        flag = DriverAction.switchToWindow(newTb.get(0), true);
        if (!flag) {
            return;
        }

    }

    public static void backTop() throws IOException {

        if(!(DriverAction.click(Amazon_locators.backToTop, "Back To Top"))){
            return;
        }
    }

    public static void newReleaseClick() throws IOException {
      boolean flag;
       flag= DriverAction.click(Amazon_locators.Bestsellers, "Bestsellers");
        if(!flag){
            return;
        }
       flag= DriverAction.click(Amazon_locators.newRelease, "New Release");
        if(!flag){
            return;
        }
        flag= DriverAction.click(Amazon_locators.toprelease, "#1");
        if(!flag){
            return;
        }
        GemTestReporter.addTestStep("Product Title", DriverAction.getElementText(Amazon_locators.tittle) + "<br>" + DriverAction.getElementText(Amazon_locators.price1), STATUS.PASS, takeSnapShot());
    }

    public static void bestSellerClick() throws IOException {
     boolean flag;
        flag=   DriverAction.click(Amazon_locators.Bestsellers, "Bestsellers");
        if(!flag){
            return;
        }
     flag=   DriverAction.click(Amazon_locators.toprelease, "#1");
        if(!flag){
            return;
        }
        GemTestReporter.addTestStep("Product Title", DriverAction.getElementText(Amazon_locators.tittle) + "<br>" + DriverAction.getElementText(Amazon_locators.price1), STATUS.PASS, takeSnapShot());
    }


}
