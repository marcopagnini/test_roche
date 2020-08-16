package marco.warehouse.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.*;
import org.junit.Assert;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.IOException;
public class StepDefinition {

    private String postEndpoint;
    private String putEndpoint;
    private String getEndpoint;
    private String deleteEndpoint;
    private HttpHeaders headers;
    private OkHttpClient client = new OkHttpClient();
    private Response response;



    @Given("^I Set POST product service api endpoint$")
    public void i_Set_POST_product_service_api_endpoint(){
        postEndpoint = "http://localhost:8080/product/add";
    }

    @Given("^I Set PUT product service api endpoint$")
    public void i_Set_PUT_product_service_api_endpoint(){
        putEndpoint = "http://localhost:8080/product/update/5";
    }

    @Given("^I Set GET product service api endpoint$")
    public void i_Set_GET_product_service_api_endpoint(){
        getEndpoint = "http://localhost:8080/product/show/5";
    }

    @Given("^I Set DELETE product service api endpoint$")
    public void i_Set_DELETE_product_service_api_endpoint(){
        deleteEndpoint = "http://localhost:8080/product/delete/5";
    }


    @When("^I Set request HEADER$")
    public void i_set_request_HEADER(){
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "text/html");
    }

    @When("^I Send a POST HTTP request$")
    public void i_Send_POST_HTTP_request() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("productName", "Shoes 1")
                .add("price", "150.00")
                .build();
        Request request = new Request.Builder()
                //.url(postEndpoint)
                .url(postEndpoint + "?name=Shoes 1&price=150")
                .method("POST", formBody)
                .addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "text/plain")
                .build();
        response = client.newCall(request).execute();
    }

    @When("^I Send a PUT HTTP request$")
    public void i_Send_PUT_HTTP_request() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("productName", "Shoes 2")
                .add("price", "250.00")
                .build();
        Request request = new Request.Builder()
                .url(putEndpoint + "?name=Shoes 2&price=250")
                .put(formBody)
                .addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "text/plain")
                .build();
        response = client.newCall(request).execute();
    }

    @When("^I Send a GET HTTP request$")
    public void i_Send_GET_HTTP_request() throws IOException {
        Request request = new Request.Builder()
                .url(getEndpoint)
                .addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "text/plain")
                .build();

        response = client.newCall(request).execute();
    }

    @When("^I Send a DELETE HTTP request$")
    public void i_Send_DELETE_HTTP_request() throws IOException {
        Request request = new Request.Builder()
                .url(deleteEndpoint)
                .delete()
                .addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "text/plain")
                .build();

        response = client.newCall(request).execute();
    }

    @Then("^I receive valid HTTP response code 200$")
    public void i_receive_valid_HTTP_response_code(){
        // Check if the status code is 200
        Assert.assertEquals(response.code(), HttpStatus.OK.value());
    }


}
