package com.springboottutorial.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoansAPIContactInfo(String message, Map<String, String> contactDetails, List<String> supportNumbers) {
}
