package petStore;

import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.platform.commons.logging.Logger;

public class BaseTest {
    protected RequestSpecification specForRequestCTJson = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    protected RequestSpecification specForRequestCTFormData = new RequestSpecBuilder()
            .setContentType(ContentType.URLENC)
            .log(LogDetail.ALL)
            .build();
    protected ResponseSpecification specForResponse = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectContentType(ContentType.JSON)
            .build();
}
