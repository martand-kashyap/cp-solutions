package designpatterns.structural.decorator;

import java.math.BigDecimal;

abstract class Pizza {
  protected String description;

  public String getDescription() {
    return this.description;
  }

  public abstract BigDecimal getPrice();
}
