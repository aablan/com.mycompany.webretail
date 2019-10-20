package com.mycompany.webretail.data.items;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public class Item {

  private int itemId;
  private String itemDescription;
  private ItemCategory itemCategory;
  private BigDecimal price;


  public ItemCategory getItemCategory() {
    return itemCategory;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
