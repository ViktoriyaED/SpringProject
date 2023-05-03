package com.example.SpringProject;

import com.example.SpringProject.entity.Employee;
import community.redrover.mercuryit.MercuryIT;
import community.redrover.mercuryit.MercuryITHttp;
import community.redrover.mercuryit.MercuryITHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringProjectApplicationTests {
    private static String getUrl(String endPoint) {
        return "http://localhost:8088/api/employees" + endPoint;
    }

    @Test
    public void testCreateEmployee() {
        Employee expectedEmployee = new Employee(1L, "Pavel", "QA");
        MercuryIT.request(MercuryITHttp.class)
                .uri(getUrl("/create"))
                .body(expectedEmployee)
                .post()
                .assertion(MercuryITHttpResponse::getCode).equalsTo(200)
                .assertion(MercuryITHttpResponse::getBody).equalsTo("{\"id\":1,\"name\":\"Pavel\",\"jobTitle\":\"QA\"}");
    }

    @Test
    public void testDeleteEmployee() {
        MercuryIT.request(MercuryITHttp.class)
                .uri(getUrl("/delete/1"))
                .delete()
                .assertion(MercuryITHttpResponse::getCode).equalsTo(200)
                .assertion(MercuryITHttpResponse::getBody).equalsTo("");
    }
}
