package net.maxqfz.sharding.service;

import net.maxqfz.sharding.model.converter.CustomerConverter;
import net.maxqfz.sharding.model.converter.CustomerInfoConverter;
import net.maxqfz.sharding.model.dto.CustomerDto;
import net.maxqfz.sharding.model.dto.CustomerInfoDto;
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
    public List<CustomerDto> getUsers() {
        return customerRepository.findAll().stream()
                .map(customerConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerInfoDto getUserById(long id) {
        var userEntity = customerRepository.findByIdWithOutgoingPayments(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return customerInfoConverter.convertEntityToDto(userEntity);
    }

    @Transactional
    public CustomerDto createUser(CustomerDto customerDto) {
        var userEntity = customerConverter.convertDtoToEntity(customerDto);
        userEntity = customerRepository.save(userEntity);
        return customerConverter.convertEntityToDto(userEntity);
    }
}