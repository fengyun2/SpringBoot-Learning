package com.win.springbootspringdocopenapi.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressVo {
  private String city;
  private String zipcode;
}
