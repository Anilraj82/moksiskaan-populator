package edu.university.pathwaycommons.PathwayCommons;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class pathwayCommonTest {
	/**
	 * Instance of pathwayCommon
	 */
	pathwayCommon pCommon = new pathwayCommon();
	
	/**
	 * test arrayOfKeys
	 */
	@Test
	public void testGetArrayOfKeys()
	{
		String[] keys = pCommon.getArrayOfKeys();
		assertEquals(5, keys.length);
		assertEquals("browser.download.folderList", keys[0]);
	}
	
	/**
	 * Test arrayOfValues
	 */
	@Test
	public void testgetArrayOfValues()
	{
		String[] keys = pCommon.getArrayOfValues();
		assertEquals(5, keys.length);
		assertEquals("2", keys[0]);
	}
	
	/**
	 * Test getUrls
	 */
	@Test
	public void testgetUrls()
	{
		String[] urls = pCommon.getUrls();
		assertEquals(5, urls.length);
		assertEquals("/v9/", urls[0]);
	}
	
	/**
	 * Test getLinks
	 */
	@Test
	public void testgetLinks()
	{
		String[] links = pCommon.getLinks();
		assertEquals(5, links.length);
		assertEquals("hgnc.sif.gz", links[0]);
	}
	
	/**
	 * Test setFirefoxOption
	 */
	@Test
	public void testsetFirefoxOption()
	{
		FirefoxOptions option = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		pCommon.setFirefoxOption(option, profile);
		assertEquals(profile, option.getProfile());
	}
	
	/**
	 * Test setPreferenceToProfile
	 */
	@Test
	public void setPreferenceToProfile()
	{
		String[] keys = pCommon.getArrayOfKeys();
		String[] values = pCommon.getArrayOfValues();
		FirefoxProfile profile = new FirefoxProfile();
		pCommon.setPreferenceToProfile(profile, keys, values);
		assertEquals(2, profile.getIntegerPreference("browser.download.folderList", 1));
	}
	
	/**
	 * Test executeDriver
	 */
	@Test
	public void testExecuteDriver() {
		System.setProperty("webdriver.gecko.driver",
		System.getProperty("user.home") + "/eclipse/driversandjars/geckodriver");

		String[] keys = pCommon.getArrayOfKeys();
		String[] values = pCommon.getArrayOfValues();

		// Create a new instance of the Firefox profile
		FirefoxProfile profile = new FirefoxProfile();
		// setting preferences to profile
		pCommon.setPreferenceToProfile(profile, keys, values);

		// Create a new instance of the Firefox options
		FirefoxOptions option = new FirefoxOptions();
		pCommon.setFirefoxOption(option, profile);
		
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver(option);
		// driver.manage().window().fullscreen();
		
		String[] urls = pCommon.getUrls();
		String[] links = pCommon.getLinks();
		for (int i = 0; i < urls.length; i++) {
			pCommon.executeDriver(driver, urls[0], links[0]);
			assertNotNull(driver);
		}
		
		
		driver.quit();
	}

}