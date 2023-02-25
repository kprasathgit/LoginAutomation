package ECommerce.AddCart_PlaceOrder;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class E_Kart {

	@Test
	@Parameters({ "DriverKey", "DriverLocation", "SiteUrl" })
	public static void PurchaseItems(String dk, String dl, String url) throws Exception {
		// TODO Auto-generated method stub

		System.setProperty(dk, dl);
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(url);

		driver.manage().window().maximize();

// LIST OF ITEMS NEED TO PURCHASE:
		String[] itemlist = { "Walnuts", "Brocolli", "Cucumber", "Pomegranate", "Beans", "Tomato", "Cashews" };

// CONVERTING ARRAY TO ARRAYLIST in order to access arraylist methods.
		List<String> purchaselist = Arrays.asList(itemlist);

// COLLECTING ALL THE ITEM AVAILABLE TO PURCHASE IN WEBSITE TO A LIST.		
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

//Selecting items to purchase one by one using loop.

		for (int j = 0; j < products.size(); j++) {

			String temp1 = products.get(j).getText();

			String[] temp2 = temp1.split(" ");

			String name = temp2[0].trim();

			if (purchaselist.contains(name)) 
			{
				// driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(j).click();
				// Thread.sleep(5000);
			
				driver.findElements(By.cssSelector(".product-action button")).get(j).click();
				Thread.sleep(1000);
			}
		}
		Thread.sleep(1500);

// Adding selected items to cart.
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		Thread.sleep(1500);

//Proceed to check out the items selected  and price to pay.
		driver.findElement(By.cssSelector("div.action-block")).click();
		Thread.sleep(2200);

// Placing order.		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		Thread.sleep(1500);

// Selecting which country should deliver.		
		Select sdd = new Select(driver.findElement(By.cssSelector("select[style='width: 200px;']")));
		sdd.selectByValue("India");
		Thread.sleep(1500);

// Agreeing terms and conditions upon delivery		
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		Thread.sleep(1500);

// Confirming order		
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
	}

}
