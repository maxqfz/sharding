package net.maxqfz.sharding.service;

import net.maxqfz.sharding.model.converter.PaymentConverter;
import net.maxqfz.sharding.model.dto.PaymentDTO;
import net.maxqfz.sharding.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentConverter paymentConverter;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentConverter paymentConverter,
                          PaymentRepository paymentRepository) {
        this.paymentConverter = paymentConverter;
        this.paymentRepository = paymentRepository;
    }

    public PaymentDTO getPaymentById(long id) {
        var paymentEntity = paymentRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return paymentConverter.convertEntityToDto(paymentEntity);
    }

    public List<PaymentDTO> getPaymentsByPayerId(long payerId) {
        return paymentRepository.findAllByPayerId(payerId).stream()
                .map(paymentConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public PaymentDTO createPayment(PaymentDTO paymentDto) {
        var paymentEntity = paymentConverter.convertDtoToEntity(paymentDto);
        paymentEntity = paymentRepository.save(paymentEntity);
        return paymentConverter.convertEntityToDto(paymentEntity);
    }

    public void uploadPayments(List<PaymentDTO> paymentDTOList) {
        var paymentEntities = paymentDTOList.stream()
                .map(paymentConverter::convertDtoToEntity)
                .collect(Collectors.toList());
        paymentRepository.saveAll(paymentEntities);
    }
}