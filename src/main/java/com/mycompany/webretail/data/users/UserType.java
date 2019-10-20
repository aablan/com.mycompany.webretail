package com.mycompany.webretail.data.users;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {

  EMPLOYEE(BigDecimal.valueOf(0.30)),
  AFFILIATE(BigDecimal.valueOf(0.10)),
  PREMIUM(BigDecimal.valueOf(0.05)),
  NORMAL(BigDecimal.valueOf(0));

  private final BigDecimal discountPercent;

  public BigDecimal discountPercent(){
    return discountPercent;
  }
}
