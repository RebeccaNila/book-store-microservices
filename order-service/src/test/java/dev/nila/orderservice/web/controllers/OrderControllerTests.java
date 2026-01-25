package dev.nila.orderservice.web.controllers;

import dev.nila.orderservice.AbstractIT;
// for every test method it will run this sql  script
// cause after each method test changes will truncate and add new record for another testing

// @Sql("test-orders.sql")
public class OrderControllerTests extends AbstractIT {

    //    @Nested
    //    class CreateOrderTests {
    //        @Test
    //        void shouldCreateOrderSuccessfully() {
    //            // instead of actual call to catalog server, mock get product with expected response
    ////            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
    //            var payload =
    //                    """
    //                        {
    //                            "customer" : {
    //                                "name": "Siva",
    //                                "email": "siva@gmail.com",
    //                                "phone": "999999999"
    //                            },
    //                            "deliveryAddress" : {
    //                                "addressLine1": "HNO 123",
    //                                "addressLine2": "Kukatpally",
    //                                "city": "Hyderabad",
    //                                "state": "Telangana",
    //                                "zipCode": "500072",
    //                                "country": "India"
    //                            },
    //                            "items": [
    //                                {
    //                                    "code": "P100",
    //                                    "name": "Product 1",
    //                                    "price": 25.50,
    //                                    "quantity": 1
    //                                }
    //                            ]
    //                        }
    //                    """;
    //            given().contentType(ContentType.JSON)
    //                    //                    .header("Authorization", "Bearer " + getToken())
    //                    .body(payload) // in payload we can pass the json or java bean, behind the scenes, it can
    // convert
    //                    // java object to json
    //                    .when()
    //                    .post("/api/orders")
    //                    .then()
    //                    .statusCode(HttpStatus.CREATED.value())
    //                    .body("orderNumber", notNullValue());
    //        }
    //
    //        @Test
    //        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
    //            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
    //            given().contentType(ContentType.JSON)
    //                    //                    .header("Authorization", "Bearer " + getToken())
    //                    .body(payload)
    //                    .when()
    //                    .post("/api/orders")
    //                    .then()
    //                    .statusCode(HttpStatus.BAD_REQUEST.value());
    //        }
    //    }
    //
    //    @Nested
    //    class GetOrdersTests {
    //        @Test
    //        void shouldGetOrdersSuccessfully() {
    //            List<OrderSummary> orderSummaries = given().when()
    //                    //                    .header("Authorization", "Bearer " + getToken())
    //                    .get("/api/orders")
    //                    .then()
    //                    .statusCode(200)
    //                    .extract()
    //                    .body()
    //                    .as(new TypeRef<>() {});
    //            System.out.println(orderSummaries);
    //            assertThat(orderSummaries).hasSize(2);
    //        }
    //    }
    //
    //    @Nested
    //    class GetOrderByOrderNumberTests {
    //        String orderNumber = "order-123";
    //
    //        @Test
    //        void shouldGetOrderSuccessfully() {
    //            given().when()
    //                    //                    .header("Authorization", "Bearer " + getToken())
    //                    .get("/api/orders/{orderNumber}", orderNumber)
    //                    .then()
    //                    .statusCode(200)
    //                    .body("orderNumber", is(orderNumber))
    //                    .body("items.size()", is(2));
    //        }
    //    }
}
