package com.win.springbootspringdocopenapi.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVo {
  private String name;
  private int age;
  private AddressVo address;
}
