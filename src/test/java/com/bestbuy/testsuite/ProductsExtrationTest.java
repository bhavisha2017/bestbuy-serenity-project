package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtrationTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //    21. Extract the limit
    @Test
    public void test001()
    {
        int limit =response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test002()
    {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test003()
    {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 5th Store name is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test004()
    {
        List<String> allProducts = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Products is : " + allProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test005()
    {
        List<Integer> allProductsId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products : " + allProductsId);
        System.out.println("------------------End of Test---------------------------");
    }


    //26. Print the size of the data list
    @Test
    public void test006()
    {
        List<Integer> allProductsId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Print the size of the data list: " + allProductsId.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007()
    {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the store St Cloud: " + values);
        System.out.println("------------------End of Test---------------------------");
    }


    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008()
    {
        List<HashMap<String, ?>> productListMap = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        HashMap<String, ?> productMap = productListMap.get(0);
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) : " + productMap );
        System.out.println("------------------End of Test---------------------------");
    }


//29. Get all the categories of 8th products

    @Test
    public void test009()
    {
        //data[7].categories
        List<HashMap<String, ?>> categories  = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the categories of 8th products : " + categories );
        System.out.println("------------------End of Test---------------------------");

    }

//30. Get categories of the store where product id = 150115

    @Test
    public void test010()
    {
        List<HashMap<String, ?>> categoriesproduct    = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 : " + categoriesproduct );
        System.out.println("------------------End of Test---------------------------");

    }


    //31. Get all the descriptions of all the products
    @Test
    public void test011()
    {
        List<String> allProducts = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the descriptions of all the products : " + allProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test012()
    {
        //data[*].categories[*].id
        List<String> allproductsId = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the descriptions of all the products : " + allproductsId);
        System.out.println("------------------End of Test---------------------------");
    }


//33. Find the product names Where type = HardGood

    @Test
    public void test013()
    {
        List<?> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all products of Type HardGood are : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014()
    {
        List<?> productNames = response.extract().path("data[1].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack): " + productNames.size());
        System.out.println("------------------End of Test---------------------------");
    }



    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015()
    {
        List<?> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all products whose price < 5.49 : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }



//        36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”

    @Test
    public void test016()
    {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”: " + values);
        System.out.println("------------------End of Test---------------------------");
    }


    //        37. Find the manufacturer of all the products
    @Test
    public void test017()
    {
        List<HashMap<String, ?>> values = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products : " + values);
        System.out.println("------------------End of Test---------------------------");
    }


    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018()
    {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the product image: " + image);
        System.out.println("------------------End of Test---------------------------");

    }


//39. Find the createdAt for all categories products whose price > 5.99

    @Test
    public void test019()
    {
        List<?> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all categories products whose price > 5.99: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }


//        40. Find the uri of all the product

    @Test
    public void test040()
    {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the URL of all product name : " + url);
        System.out.println("------------------End of Test---------------------------");
    }

}
