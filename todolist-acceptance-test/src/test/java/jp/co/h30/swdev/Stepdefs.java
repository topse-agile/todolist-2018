package jp.co.h30.swdev;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Stepdefs {
	private WebDriver driver;
	
	@Given("^登録ページを表示する$")
	public void 登録ページを表示する() throws Exception {
	    driver = new FirefoxDriver();
	    driver.get("http://localhost:8080/todolist/");
	}

	@Given("^Todoアイテムは登録されていない$")
	public void todoアイテムは登録されていない() throws Exception {
	    WebElement content = driver.findElement(By.tagName("h2"));
	    assertTrue(content.getText().contains("Hello World!"));
	}

	@When("^　タイトルに\"([^\"]*)\"と入力する$")
	public void タイトルに_と入力する(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@When("^説明に\"([^\"]*)\"と入力する$")
	public void 説明に_と入力する(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@When("^期限に\"([^\"]*)\"と入力する$")
	public void 期限に_と入力する(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@When("^登録ボタンをクリックする$")
	public void 登録ボタンをクリックする() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^一覧ページを表示する$")
	public void 一覧ページを表示する() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^Todoアイテムが(\\d+)件表示される$")
	public void todoアイテムが_件表示される(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^(\\d+)件目のタイトルが\"([^\"]*)\"である$")
	public void 件目のタイトルが_である(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^(\\d+)件目の説明が\"([^\"]*)\"である$")
	public void 件目の説明が_である(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^(\\d+)件目の期限が\"([^\"]*)\"である$")
	public void 件目の期限が_である(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^(\\d+)件目の作成日が今日である$")
	public void 件目の作成日が今日である(int arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}
}