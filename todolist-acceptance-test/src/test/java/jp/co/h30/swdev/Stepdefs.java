package jp.co.h30.swdev;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {
	private static final String REGISTER_URL = "http://localhost:8080/todolist/register";
	private static final String LIST_URL = "http://localhost:8080/todolist/";
	private static final String DELETE_URL = "http://localhost:8080/todolist/delete";

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private WebDriver driver;

	@Before
	public void setupBrowser() {
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NUL");
		driver = new FirefoxDriver();
	}

	protected WebElement findElement(String testId) {
		return driver.findElement(By.cssSelector("[data-test-id=" + testId + "]"));
	}

	protected WebElement findElement(String testId, WebElement parent) {
		return parent.findElement(By.cssSelector("[data-test-id=" + testId + "]"));
	}

	protected List<WebElement> findElements(String testId) {
		return driver.findElements(By.cssSelector("[data-test-id=" + testId + "]"));
	}

	protected List<WebElement> findElements(String testId, WebElement parent) {
		return parent.findElements(By.cssSelector("[data-test-id=" + testId + "]"));
	}

	@Given("^一覧ページを表示する$")
	public void 一覧ページを表示する() throws Exception {
		driver.get(LIST_URL);
	}

	@Given("^登録ページを表示する$")
	public void 登録ページを表示する() throws Exception {
		driver.get(REGISTER_URL);
	}

	@Given("^Todoアイテムは登録されていない$")
	public void todoアイテムは登録されていない() throws Exception {
		driver.get(DELETE_URL);
		List<WebElement> elements = driver.findElements(By.cssSelector("[data-test-id=todo]"));
		assertEquals(0, elements.size());
	}
	
	@Given("タイトル：\"([^\\\"]*)\", 説明：\"([^\\\"]*)\", 期限：\"([^\\\"]*)\"のTodoアイテムを登録済み")
	public void タイトル_説明_期限_のTodoアイテムを登録済み(String arg1, String arg2, String arg3) throws Exception {
		driver.get(REGISTER_URL);
		
		WebElement title = findElement("title");
		title.sendKeys(arg1);
		
		WebElement detail = findElement("detail");
		detail.sendKeys(arg2);
		
		WebElement deadline = findElement("deadline");
		deadline.sendKeys(arg3);
		
		WebElement btnSubmit = findElement("btn-submit");
		btnSubmit.click();
	}

	@When("^タイトルに\"([^\"]*)\"と入力する$")
	public void タイトルに_と入力する(String arg1) throws Exception {
		WebElement title = findElement("title");
		title.sendKeys(arg1);
	}

	@When("^説明に\"([^\"]*)\"と入力する$")
	public void 説明に_と入力する(String arg1) throws Exception {
		WebElement detail = findElement("detail");
		detail.sendKeys(arg1);
	}

	@When("^期限に\"([^\"]*)\"と入力する$")
	public void 期限に_と入力する(String arg1) throws Exception {
		WebElement deadline = findElement("deadline");
		deadline.sendKeys(arg1);
	}

	@When("^登録ボタンをクリックする$")
	public void 登録ボタンをクリックする() throws Exception {
		WebElement btnSubmit = findElement("btn-submit");
		btnSubmit.click();
	}

	@Then("^一覧ページが表示される$")
	public void 一覧ページが表示される() throws Exception {
		assertEquals(LIST_URL, driver.getCurrentUrl());
	}

	@Then("^Todoアイテムが(\\d+)件表示される$")
	public void todoアイテムが_件表示される(int arg1) throws Exception {
		List<WebElement> todos = driver.findElements(By.cssSelector("[data-test-id=todo]"));
		assertEquals(arg1, todos.size());
	}

	@Then("^(\\d+)件目のタイトルが\"([^\"]*)\"である$")
	public void 件目のタイトルが_である(int arg1, String arg2) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = findElement("title", todo).getText();
		assertEquals(arg2, actual);
	}

	@Then("^(\\d+)件目の説明が\"([^\"]*)\"である$")
	public void 件目の説明が_である(int arg1, String arg2) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = findElement("detail", todo).getText();
		assertEquals(arg2, actual);
	}

	@Then("^(\\d+)件目の期限が\"([^\"]*)\"である$")
	public void 件目の期限が_である(int arg1, String arg2) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = findElement("deadline", todo).getText();
		assertEquals(arg2, actual);
	}

	@Then("^(\\d+)件目の作成日が今日である$")
	public void 件目の作成日が今日である(int arg1) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = findElement("created-date", todo).getText();
		String today = LocalDate.now().format(dateFormatter);
		assertEquals(today, actual);
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
}