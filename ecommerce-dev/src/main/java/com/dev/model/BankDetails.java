package com.dev.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BankDetails {

    private String accountNumber;
    private String accountHolderName;
//  private String bankName;
    private String ifscCode;

}
