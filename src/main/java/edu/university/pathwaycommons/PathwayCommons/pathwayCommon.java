package edu.university.pathwaycommons.PathwayCommons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * This class automates the process of downloading the links present in the archive of Pathways Commons.
 * The url link looks like  http://www.pathwaycommons.org/archives/PC2/v9/
 * Each url link contains several links to downloadeable files. 
 */
public class pathwayCommon {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.home") + "/eclipse/driversandjars/geckodriver");

		String[] keys = getArrayOfKeys();
		String[] values = getArrayOfValues();

		// Create a new instance of the Firefox profile
		FirefoxProfile profile = new FirefoxProfile();
		// setting preferences to profile
		setPreferenceToProfile(profile, keys, values);

		// Create a new instance of the Firefox options
		FirefoxOptions option = new FirefoxOptions();
		setFirefoxOption(option, profile);

		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver(option);
		// driver.manage().window().fullscreen();

		String[] urls = getUrls();
		String[] links = getLinks();

		for (int i = 0; i < urls.length; i++) {
			executeDriver(driver, urls[i], links[i]);
			System.out.println("Thats all to be printed.");
			
			extract extractObj = new extract();
			extractObj.extractFile();

			deleteFile deleteObj = new deleteFile();
			deleteObj.delete();
		}
		//quit firefox driver
		driver.quit();
	}

	/**
	 * 
	 * @return Array of keys
	 */
	public static void setFirefoxOption(FirefoxOptions option, FirefoxProfile profile) {
		option.setLogLevel(FirefoxDriverLogLevel.ERROR);
		option.setProfile(profile);
	}

	/**
	 * 
	 * @param profile
	 * @param preferences
	 * @param values
	 */
	public static void setPreferenceToProfile(FirefoxProfile profile, String[] preferences, String[] values) {
		for (int i = 0; i < preferences.length; i++) {
			if (values[i] == "false") {
				profile.setPreference(preferences[i], false);
			} else if (values[i] == "2") {
				profile.setPreference(preferences[i], 2);
			} else {
				profile.setPreference(preferences[i], values[i]);
			}
		}
	}

	/**
	 * 
	 * @return Array of keys
	 */
	public static String[] getArrayOfKeys() {
		String[] keys = {
				"browser.download.folderList",
				"browser.download.dir",
				"browser.helperApps.neverAsk.saveToDisk",
				"browser.download.manager.showWhenStarting",
				"marionette.logging"
				};

		return keys;
	}

	/**
	 * 
	 * @return Array of values
	 */
	public static String[] getArrayOfValues() {
		String[] values = {
				"2",
				System.getProperty("user.home") + "/eclipse/SeleniumDownloads/",
				"application/x-compressed;" + "application/x-gzip;",
				"false",
				"ERROR"
				};
		return values;
	}

	/**
	 * 
	 * @return Array of urls
	 */
	public static String[] getUrls() {
		String[] urls = {"/v9/", "/v8/", "/v7/", "/v6/", "/v5/"};
		return urls;
	}

	/**
	 * 
	 * @return Array of links
	 */
	public static String[] getLinks() {
		String[] links = {
				"hgnc.sif.gz",
				"BINARY_SIF.hgnc.txt.sif.gz",
				"BINARY_SIF.hgnc.sif.gz",
				"BINARY_SIF.tsv.gz",
				"BINARY_SIF.tsv.gz"
				};
		return links;
	}

	/**
	 * 
	 * @param driver
	 * @param url
	 * @param link
	 */
	public static void executeDriver(WebDriver driver, String url, String link) {
		// And now use this to locate PathwaysCommons
		driver.get("http://www.pathwaycommons.org/archives/PC2/" + url);

		// Find the text input element by pertial links text
		List<WebElement> links = driver.findElements(By.partialLinkText(link));

		System.out.println("The size(number) of links on this page " + url + " is " + links.size());
		System.out.println("-------------------------------------------");

		for (int j = 0; j < links.size(); j++) {
			System.out.print("Now its clicking : " + (j + 1) + " ");
			System.out.println(links.get(j).getText());
			links.get(j).click();
		}
		//implicitely waits for 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

}
