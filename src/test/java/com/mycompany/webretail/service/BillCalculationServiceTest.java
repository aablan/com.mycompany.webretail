package com.mycompany.webretail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mycompany.webretail.data.items.Item;
import com.mycompany.webretail.data.items.ItemCategory;
import com.mycompany.webretail.data.users.User;
import com.mycompany.webretail.data.users.UserType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BillCalculationServiceTest {


  @Test
  void testBillCalculation_WhenUserTypeNormal_AndAmountLessThanHundred_ThenNoDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.NORMAL)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Kids Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        3
    );
    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(77.52),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypeEmployee_AndAmountLessThanHundred_ThenPercentDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.EMPLOYEE)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Kids Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        3
    );
    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(54.26),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypeNormal_AndAmountMoreThanHundred_ThenFixedDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.NORMAL)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Kids Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        3
    );
    user.addToCart(
        Item.builder()
            .itemId(2)
            .itemDescription("Dish Washer")
            .itemCategory(ItemCategory.HOME_APPLIANCES)
            .price(BigDecimal.valueOf(500))
            .build(),
        1
    );

    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(552.52),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypePremium_AndAmountMoreThanHundred_ThenPercentAndFixedDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.PREMIUM)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        1
    );
    user.addToCart(
        Item.builder()
            .itemId(2)
            .itemDescription("iphone 8")
            .itemCategory(ItemCategory.MOBILES_TABLETS)
            .price(BigDecimal.valueOf(800))
            .build(),
        1
    );

    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(744.54),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypeAffiliate_AndAmountMoreThanHundred_ThenPercentAndFixedDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.AFFILIATE)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(2)
            .itemDescription("Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        1
    );
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("iphone 8")
            .itemCategory(ItemCategory.MOBILES_TABLETS)
            .price(BigDecimal.valueOf(800))
            .build(),
        1
    );

    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(703.25),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypeEmployee_AndAmountMoreThanHundred_ThenPercentAndFixedDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.EMPLOYEE)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        1
    );
    user.addToCart(
        Item.builder()
            .itemId(2)
            .itemDescription("iphone 8")
            .itemCategory(ItemCategory.MOBILES_TABLETS)
            .price(BigDecimal.valueOf(800))
            .build(),
        1
    );

    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(538.08),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenUserTypeEmployee_AndAmountMoreThanHundred_AndContainsGrocery_ThenPercentAndFixedDiscount() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.EMPLOYEE)
        .build();
    user.addToCart(
        Item.builder()
            .itemId(1)
            .itemDescription("Shampoo")
            .itemCategory(ItemCategory.HEALTH_BEAUTY_PERFUMES)
            .price(BigDecimal.valueOf(25.84))
            .build(),
        1
    );
    user.addToCart(
        Item.builder()
            .itemId(2)
            .itemDescription("Trousers, t-shirts, and baby clothes")
            .itemCategory(ItemCategory.CLOTHING)
            .price(BigDecimal.valueOf(800))
            .build(),
        1
    );
    user.addToCart(
        Item.builder()
            .itemId(3)
            .itemDescription("Rice, Bread, Sugar, ...")
            .itemCategory(ItemCategory.GROCERY)
            .price(BigDecimal.valueOf(100))
            .build(),
        1
    );

    val calculationService = new BillCalculationService(user);
    assertEquals(
        BigDecimal.valueOf(633.08),
        calculationService.netPayableAmount()
    );
  }

  @Test
  void testBillCalculation_WhenShoppingCartEmpty() {
    val user = User.builder()
        .userId(1)
        .userType(UserType.EMPLOYEE)
        .build();
    val calculationService = new BillCalculationService(user);
    assertEquals(
        new BigDecimal("0.00"),
        calculationService.netPayableAmount()
    );
  }
}
