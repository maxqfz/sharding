package net.maxqfz.sharding.controller;

import net.maxqfz.sharding.model.dto.PaymentDto;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentDto> getAllByPayerId(@RequestParam("payer_id") long payerId) {
        return paymentService.getPaymentsByPayerId(payerId);
    }

    @GetMapping("/{id}")
    public PaymentDto getPayment(@PathVariable long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public PaymentDto createPayment(@RequestBody @Valid PaymentDto payment) {
        return paymentService.createPayment(payment);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadPayments(@RequestBody List<PaymentDto> payments) {
        paymentService.uploadPayments(payments);
    }
}