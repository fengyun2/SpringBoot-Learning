package com.win.springbootspringdocopenapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootspringdocopenapi.entity.param.AddressParam;

@RestController
@RequestMapping("/address")
public class AddressController {
  /**
   * http://localhost:3003/address/add
   * @param addressParam
   * @return address
   */
  @PostMapping("add")
  public ResponseEntity<String> add(AddressParam addressParam) {
    return ResponseEntity.ok("success");
  }
}
