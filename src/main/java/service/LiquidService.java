package service;

import validator.CreateLiquidValidator;

public class LiquidService {
  public static final LiquidService INSTANCE = new LiquidService();
  private final CreateLiquidValidator createLiquidValidator = CreateLiquidValidator.getInstance();



  public static LiquidService getInstance() {
  return INSTANCE;
  }
}
