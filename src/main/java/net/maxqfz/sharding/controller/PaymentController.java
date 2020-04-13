package net.maxqfz.sharding.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.maxqfz.sharding.model.dto.PaymentDTO;
import net.maxqfz.sharding.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Управление платежами")
@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @ApiOperation("Получить все платежи по ID плательщика")
    public List<PaymentDTO> getAllByPayerId(@ApiParam("Идентификатор плательщика") @RequestParam long payerId) {
        return paymentService.getPaymentsByPayerId(payerId);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить платеж по его идентификатору")
    public PaymentDTO getPayment(@ApiParam("Идентификатор платежа") @PathVariable long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    @ApiOperation("Создать платеж")
    public PaymentDTO createPayment(@ApiParam("Тело запроса") @RequestBody PaymentDTO payment) {
        return paymentService.createPayment(payment);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создать несколько платежей")
    public void uploadPayments(@ApiParam("Тело запроса") @RequestBody List<PaymentDTO> payments) {
        paymentService.uploadPayments(payments);
    }
}