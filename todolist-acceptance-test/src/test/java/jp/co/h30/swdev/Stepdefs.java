package jp.co.h30.swdev;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.ObjectUtils;
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
	private static final String ADJUST_URL = "http://localhost:8080/todolist/adjust";

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private WebDriver driver;

	@Before
	public void setupBrowser() {
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NUL");
		driver = new FirefoxDriver();
		
		// サーバの基準日をクリアする
		driver.get(ADJUST_URL);
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
	
	@Given("^サーバの基準日は\"([^\\\"]*)\"である$")
	public void サーバの基準日は_である(String arg1) {
		String parameterizedAdjustUrl = ADJUST_URL + "?criteriaDate=" + arg1;
		driver.get(parameterizedAdjustUrl);
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

	@When("^期限に現在の日付の(\\d+)年前を入力する$")
	public void 期限に現在の日付の_年前を入力する(int amount) throws Exception {
		Calendar inputDate = Calendar.getInstance();
		inputDate.add(Calendar.YEAR, amount * -1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = format.format(inputDate.getTime());
		WebElement deadline = findElement("deadline");
		deadline.sendKeys(dateStr);
	}

	@When("^期限に現在の日付の(\\d+)日前を入力する$")
	public void 期限に現在の日付の_日前を入力する(int amount) throws Exception {
		Calendar inputDate = Calendar.getInstance();
		inputDate.add(Calendar.DATE, amount * -1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = format.format(inputDate.getTime());
		WebElement deadline = findElement("deadline");
		deadline.sendKeys(dateStr);
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

	@When("^登録リンクをクリックする$")
	public void 登録リンクをクリックする() throws Exception {
		WebElement btnRegister = findElement("btn-register");
		btnRegister.click();
	}

	@When("^(\\d+)件目の完了ボタンをクリックする$")
	public void _件目の完了ボタンをクリックする(int index) throws Throwable {
		WebElement btnComplete = findElements("btn-complete").get(index - 1);
		btnComplete.click();
	}

	@When("^期限に(\\d+)日後を入力する$")
	public void 期限に_日後を入力する(int arg1) throws Exception {
		WebElement deadline = findElement("deadline");
		Date d = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, arg1);
		deadline.sendKeys(fmt.format(calendar.getTime()));
  }
  
	@When("^タイトルが\"([^\"]*)\"である最初のTodoアイテムを完了する$")
	public void タイトルが_である最初のTodoアイテムを完了する(String title) throws Throwable {
		List<WebElement> todos = findElements("todo");
		for (WebElement todo : todos) {
			String actual = findElement("title", todo).getText();
			if (actual.equals(title)) {
				WebElement btnComplete = findElement("btn-complete", todo);
				btnComplete.click();
				break;
			}
		}


	@Then("^一覧ページが表示される$")
	public void 一覧ページが表示される() throws Exception {
		assertEquals(LIST_URL, driver.getCurrentUrl());
	}

	@Then("^登録ページが表示される$")
	public void 登録ページが表示される() throws Exception {
		assertTrue(driver.getCurrentUrl().equals(REGISTER_URL) || driver.getCurrentUrl().equals(REGISTER_URL + ".jsp"));
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

	@Then("^メッセージに\"([^\"]*)\"と表示される$")
	public void メッセージに_と表示される(String arg) throws Exception {
		List<WebElement> messages = findElements("message");
		for (WebElement message : messages) {
			if (message.getText().equals(arg)) {
				return;
			}
		}
		fail();
	}

	@Then("^(\\d+)件目が強調表示である$")
	public void 件目が強調表示である(int arg1) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = todo.getAttribute("class");
		assertEquals("closing", actual);
	}

	@Then("^(\\d+)件目が強調表示でない$")
	public void 件目が強調表示でない(int arg1) throws Exception {
		WebElement todo = findElements("todo").get(arg1 - 1);
		String actual = todo.getAttribute("class");
		assertEquals("", actual);
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
}
