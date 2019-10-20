package com.mycompany.webretail.data.users;

import com.mycompany.webretail.data.items.Item;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Builder
public class User {

  private int userId;
  private String username;
  private UserType userType;
  private final Map<Item, Integer> userCart = new HashMap<>();

  public void addToCart(Item item, int amount) {
    userCart.put(item, amount);
  }

  public Map<Item, Integer> getUserCart() {
    return Collections.unmodifiableMap(userCart);
  }

  public UserType getUserType() {
    return this.userType;
  }
}
