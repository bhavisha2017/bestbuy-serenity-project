package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends TestBase {

     String name = "Amazone5" + TestUtils.getRandomValue();
     String type = "Battery";
     Double price = 5.99;
     Double shipping = 1.99;
     String description = "Amazone Basic Home Battery1" + TestUtils.getRandomValue();
     String model = "Amazon Basic 1.0" + TestUtils.getRandomValue();
     String manufacturer = "Amazon China";
     String upc = "Text";
     String url = "http://www.amazon.co.uk/battery";
     String image = "http://www.amazon.co.uk/battery/imag";
     static int id;

     @Steps
     ProductSteps productSteps;

    @Title("Creating a new product.")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Amazone5";
        ValidatableResponse response = productSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productSteps.deleteProduct(id).statusCode(200);
        productSteps.getProduct(id).statusCode(404);
    }
}
