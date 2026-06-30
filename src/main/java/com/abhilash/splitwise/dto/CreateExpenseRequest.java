package com.abhilash.splitwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CreateExpenseRequest {

    @Positive
    private double amount;

    @NotBlank
    private String paidByUserId;

    private List<SplitRequest> splits;

}