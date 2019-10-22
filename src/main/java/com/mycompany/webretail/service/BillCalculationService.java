package com.mycompany.webretail.service;

import com.mycompany.webretail.data.items.ItemCategory;
import com.mycompany.webretail.data.users.User;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;

/**
 * This Service class will operate on top of the user shopping cart to calculate the net payable amount
 * taking into consideration all applicable discounts.
 */
@RequiredArgsConstructor
public class BillCalculationService {

  private final User user;
  private static final BigDecimal FIXED_DISCOUNT_AMOUNT = BigDecimal.valueOf(5);
  private static final BigDecimal APPLICABLE_DISCOUNT_AMOUNT = BigDecimal.valueOf(100);


  public BigDecimal netPayableAmount() {
    return totalAmountPreDiscount()
        .subtract(fixedAmountDiscount())
        .subtract(percentAmountDiscount())
        .setScale(2, BigDecimal.ROUND_DOWN);
  }

  /**
   * Calculate the percentage discount amount applicable as per the user type.
   */
  private BigDecimal percentAmountDiscount() {
    return user.getUserCart()
        .entrySet()
        .stream()
        .filter(itemIntegerEntry ->
            !itemIntegerEntry
                .getKey()
                .getItemCategory()
                .equals(ItemCategory.GROCERY)
        )
        .map(item -> item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO)
        .multiply(user.getUserType().discountPercent());
  }

  /**
   * Calculates the fixed discount amount if applicable
   */
  private BigDecimal fixedAmountDiscount() {
    return totalAmountPreDiscount()
        .divide(APPLICABLE_DISCOUNT_AMOUNT, 0, RoundingMode.FLOOR)
        .multiply(FIXED_DISCOUNT_AMOUNT);
  }


  /**
   * Calculates the total bill amount by accumulating the items in the user shopping cart before applying any discounts.
   */
  private BigDecimal totalAmountPreDiscount() {
    return user.getUserCart()
        .entrySet()
        .stream()
        .map(item -> item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }
}
