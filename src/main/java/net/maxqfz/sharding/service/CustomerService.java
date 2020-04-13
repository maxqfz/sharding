package net.maxqfz.sharding.service;

import net.maxqfz.sharding.model.converter.CustomerConverter;
import net.maxqfz.sharding.model.converter.CustomerInfoConverter;
import net.maxqfz.sharding.model.dto.CustomerDTO;
import net.maxqfz.sharding.model.dto.CustomerInfoDTO;
import net.maxqfz.sharding.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerConverter customerConverter;
    private final CustomerInfoConverter customerInfoConverter;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerConverter customerConverter,
                           CustomerInfoConverter customerInfoConverter,
                           CustomerRepository customerRepository) {
        this.customerConverter = customerConverter;
        this.customerInfoConverter = customerInfoConverter;
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> getUsers() {
        return customerRepository.findAll().stream()
                .map(customerConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerInfoDTO getUserById(long id) {
        var userEntity = customerRepository.findByIdWithOutgoingPayments(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return customerInfoConverter.convertEntityToDto(userEntity);
    }

    @Transactional
    public CustomerDTO createUser(CustomerDTO customerDto) {
        var userEntity = customerConverter.convertDtoToEntity(customerDto);
        userEntity = customerRepository.save(userEntity);
        return customerConverter.convertEntityToDto(userEntity);
    }
}