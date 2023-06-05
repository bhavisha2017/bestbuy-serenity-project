package com.bestbuy.bestbuyinfo;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class StoreCRUDTestWithSteps extends TestBase {
     String name = "Minnetonka" + TestUtils.getRandomValue();
     String type = "BigBox" +TestUtils.getRandomValue();
     String address = "13513 Ridgedale Dr" +TestUtils.getRandomValue();
     String address2 = "";
     String city = "Hopkins" + TestUtils.getRandomValue();
     String state = "MN" + TestUtils.getRandomValue();
     String zip = "55305" +TestUtils.getRandomValue();
     Double lat = 44.969658;
     Double lng = -93.449539;
     String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    StorePojo.Services services;
     static int id;

    @Steps
    StoreSteps storeSteps;

    @Title("Creating store and verifying the creation.")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createStores(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));

    }

    @Title("Getting store information by id.")
    @Test
    public void test002() {
        ValidatableResponse response = storeSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));
    }

    @Title("Updating store information by id.")
    @Test
    public void test003() {
        name = name + "-Updated";
        storeSteps.updateStores(id, name, type, address, address2, city, state, zip, lat, lng, hours, services);
        ValidatableResponse response = storeSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Deleting the store and verifying the deletion.")
    @Test
    public void test004() {
        storeSteps.deleteStores(id).statusCode(200);
        storeSteps.getStores(id).statusCode(404);
    }
}
