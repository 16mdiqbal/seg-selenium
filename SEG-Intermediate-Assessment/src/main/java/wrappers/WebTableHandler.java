package wrappers;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import logger.LoggerHandler;

public class WebTableHandler {

	private static final Logger log = LoggerHandler.getLogger(WebTableHandler.class);
	private WebDriver driver;

	public WebTableHandler(WebDriver driver) {
		this.driver = driver;
		log.info("WebTableHandler : " + this.driver.hashCode());
	}

	public String getCellData(WebElement table, int rowNum, int colNum) {

		String cellData = "";
		int rowCounter = 1;
		int colCounter = 1;
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (WebElement eachrow : rows) {
			if (rowCounter==rowNum) {
				List<WebElement> cells = eachrow.findElements(By.tagName("td"));
				for (WebElement eachCell : cells) {
					if (colCounter == colNum) {
						cellData = eachCell.getText();
						break;
					}
					colCounter++;
				}
				break;
			}
			rowCounter++;
		}
		return cellData;
	}
}
