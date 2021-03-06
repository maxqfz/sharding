package net.maxqfz.sharding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.maxqfz.sharding.model.dto.CustomerDTO;
import net.maxqfz.sharding.model.dto.CustomerInfoDTO;
import net.maxqfz.sharding.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Управление пользователями")
@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation("Получить список пользователей")
    public List<CustomerDTO> getUsers() {
        return customerService.getUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить пользователя по его идентификатору")
    public CustomerInfoDTO getUserInfo(@ApiParam("Идентификатор пользователя") @PathVariable Long id) {
        return customerService.getUserById(id);
    }

    @PostMapping
    @ApiOperation("Создать пользователя")
    public CustomerDTO createUser(@ApiParam("Тело запроса") @RequestBody CustomerDTO customerDto) {
        return customerService.createUser(customerDto);
    }
}