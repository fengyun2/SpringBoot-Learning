package com.win.springbootspringdocopenapi.entity.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserParam {
  private String name;
  private int age;
  private AddressParam address;
}
