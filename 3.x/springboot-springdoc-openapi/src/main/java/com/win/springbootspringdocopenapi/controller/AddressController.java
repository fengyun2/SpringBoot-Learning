package com.win.springbootspringdocopenapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootspringdocopenapi.entity.param.AddressParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "地址模块")
@RestController
@RequestMapping("/address")
public class AddressController {
  /**
   * http://localhost:3003/address/add
   * http://localhost:3003/swagger-ui/index.html
   * @param addressParam
   * @return address
   */
  @Operation(summary = "添加地址", description = "根据地址参数添加地址")
  @Parameters({
    @Parameter(name = "city",description = "城市编码", in = ParameterIn.QUERY, required = true),
    @Parameter(name = "zipCode",description = "城市编码", in = ParameterIn.QUERY, required = true)
  })
  @PostMapping("add")
  public ResponseEntity<String> add(AddressParam addressParam) {
    return ResponseEntity.ok("success");
  }
}
