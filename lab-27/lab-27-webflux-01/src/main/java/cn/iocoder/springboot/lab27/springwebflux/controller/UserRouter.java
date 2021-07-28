package cn.iocoder.springboot.lab27.springwebflux.controller;

import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;
import static org.springframework.web.reactive.function.BodyInserters.*;

/**
 * 用户 Router，采用的是java 8 stream 和 router 的编程模式
 * 参考：https://www.baeldung.com/spring-5-functional-web
 * https://zetcode.com/springboot/routerfunction/
 */
@Configuration
public class UserRouter {

    // 路由函数
    @Bean
    public RouterFunction<ServerResponse> userListRouterFunction() {
        // 定义一个路由
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"),
                // 处理器函数
                new HandlerFunction<ServerResponse>() {

                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        // 查询列表
                        List<UserVO> result = new ArrayList<>();
                        result.add(new UserVO().setId(1).setUsername("yudaoyuanma"));
                        result.add(new UserVO().setId(2).setUsername("woshiyutou"));
                        result.add(new UserVO().setId(3).setUsername("chifanshuijiao"));
                        // 返回列表
                        return ServerResponse.ok().bodyValue(result);
                    }

                });
    }

    @Bean
    public RouterFunction<ServerResponse> userGetRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/get"),
                new HandlerFunction<ServerResponse>() {

                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        // 获得编号
                        Integer id = request.queryParam("id")
                                .map(s -> StringUtils.isEmpty(s) ? null : Integer.valueOf(s)).get();
                        // 查询用户
                        UserVO user = new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
                        // 返回列表
                        return ServerResponse.ok().bodyValue(user);
                    }

                });
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction() {
        return route(GET("/users2/demo"), request -> ok().bodyValue("demo"));
    }

    @Bean
    RouterFunction<ServerResponse> home() {
        return route(GET("/"), request -> ok().body(fromValue("Home page")));
    }

//    @Test
//    public void test_home_page() {
//
//        client.get().uri("/").exchange().expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("Home page");
//    }

    // 单个资源
//    @Bean
//    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
//        return route(GET("/employees/{id}"),
//                req -> ok().body(
//                        employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
//    }
    // 多个资源
//    @Bean
//    RouterFunction<ServerResponse> getAllEmployeesRoute() {
//        return route(GET("/employees"),
//                req -> ok().body(
//                        employeeRepository().findAllEmployees(), Employee.class));
//    }

      // 更新单个资源
//    @Bean
//    RouterFunction<ServerResponse> updateEmployeeRoute() {
//        return route(POST("/employees/update"),
//                req -> req.body(toMono(Employee.class))
//                        .doOnNext(employeeRepository()::updateEmployee)
//                        .then(ok().build()));
//    }
      // 组合路由
//      @Bean
//      RouterFunction<ServerResponse> composedRoutes() {
//          return
//                  route(GET("/employees"),
//                          req -> ok().body(
//                                  employeeRepository().findAllEmployees(), Employee.class))
//
//                          .and(route(GET("/employees/{id}"),
//                                  req -> ok().body(
//                                          employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class)))
//
//                          .and(route(POST("/employees/update"),
//                                  req -> req.body(toMono(Employee.class))
//                                          .doOnNext(employeeRepository()::updateEmployee)
//                                          .then(ok().build())));
//      }
    // 单元测试
//      @Test
//      public void givenEmployeeId_whenGetEmployeeById_thenCorrectEmployee() {
//          WebTestClient client = WebTestClient
//                  .bindToRouterFunction(config.getEmployeeByIdRoute())
//                  .build();
//
//          Employee employee = new Employee("1", "Employee 1");
//
//          given(employeeRepository.findEmployeeById("1")).willReturn(Mono.just(employee));
//
//          client.get()
//                  .uri("/employees/1")
//                  .exchange()
//                  .expectStatus()
//                  .isOk()
//                  .expectBody(Employee.class)
//                  .isEqualTo(employee);
//      }

//    @Test
//    public void whenGetAllEmployees_thenCorrectEmployees() {
//        WebTestClient client = WebTestClient
//                .bindToRouterFunction(config.getAllEmployeesRoute())
//                .build();
//
//        List<Employee> employees = Arrays.asList(
//                new Employee("1", "Employee 1"),
//                new Employee("2", "Employee 2"));
//
//        Flux<Employee> employeeFlux = Flux.fromIterable(employees);
//        given(employeeRepository.findAllEmployees()).willReturn(employeeFlux);
//
//        client.get()
//                .uri("/employees")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(Employee.class)
//                .isEqualTo(employees);
//    }

//    @Test
//    public void whenUpdateEmployee_thenEmployeeUpdated() {
//        WebTestClient client = WebTestClient
//                .bindToRouterFunction(config.updateEmployeeRoute())
//                .build();
//
//        Employee employee = new Employee("1", "Employee 1 Updated");
//
//        client.post()
//                .uri("/employees/update")
//                .body(Mono.just(employee), Employee.class)
//                .exchange()
//                .expectStatus()
//                .isOk();
//
//        verify(employeeRepository).updateEmployee(employee);
//    }

}
