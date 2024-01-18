package com.win.springboot.easyexcel.read;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcel;
import com.win.springboot.easyexcel.util.FileUtil;


@RestController
@RequestMapping("/indexOrName/read")
public class IndexOrNameRead {
  /**
   * http://localhost:8080/indexOrName/read
   * 指定列的下标或者列名
   *
   * <p>
   * 1. 创建excel对应的实体对象，并使用{@link ExcelProperty}注解，参照{@link IndexOrNameData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参考{@link IndexOrNameDataListener}
   * <p>
   * 3. 直接读取即可
   */
  @RequestMapping("")
  public void read() {
    String fileName = FileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
    // 这里默认读取第一个sheet
    EasyExcel.read(fileName, IndexOrNameData.class, new IndexOrNameDataListener()).sheet().doRead();
  }
}
