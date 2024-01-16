package com.win.springboot.easyexcel.read;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.win.springboot.easyexcel.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 最简单的读
 * <p>
 * 1. 创建excel对应的实体对象 参照{@link DemoData}
 * <p>
 * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参考{@link DemoDataListener}
 * <p>
 * 3. 直接读即可
 */
@Slf4j
@RestController
@RequestMapping("/simple/read")
public class SimpleRead {
  /**
   * http://localhost:8080/simple/read/{type}
   * type: 1 写法 1, 2 写法，3，写法 3
   */
  @GetMapping("/{type}")
  public void read(@PathVariable String type) {
    if ("1".equals(type)) {
      // 写法 1：JDK8+，不用额外写一个DemoDataListener
      String fileName = FileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
      // 这里需要指定用哪个class去读，然后读取第一个sheet文件流就会自动关闭
      // 这里默认每次会读取100条数据，然后返回过来 直接调用使用数据就行
      // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
      EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
        for(DemoData demoData: dataList) {
          log.info("读取到一条数据{}", JSON.toJSONString(demoData));
        }
      })).sheet().doRead();
    } else if ("2".equals(type)) {
      // 写法 2：
      // 匿名内部类，不用额外写一个 DemoDataListener
      String fileName = FileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
      // 这里需要指定读用哪个class去读，然后读取第一个sheet文件流就会自动关闭
      EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {
        /**
         * 单次缓存的数据量
         */
        public static final int BATCH_COUNT = 100;
        /**
         * 临时存储
         */
        private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

        @Override
        public void invoke(DemoData data, AnalysisContext context) {
          cachedDataList.add(data);
          if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
          }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
          // 读完最后一行数据后，存储数据到数据库
          saveData();
        }

        /**
         * 加上存储数据库
         */
        private void saveData() {
          log.info("{}条数据，开始存储数据库！", cachedDataList.size());
          log.info("存储数据库成功！");
        }
      }).sheet().doRead();
    } else if ("3".equals(type)) {
      // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
      // 写法3：
      String fileName = FileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
      // 这里需要指定读用哪个class去读，然后读取第一个sheet文件流就会自动关闭
      EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    } else if ("4".equals(type)) {
      // 写法4：
      String fileName = FileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
      // 一个文件一个reader
      try {
        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        // 构建一个sheet 这里可以指定名称或no
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        // 读取一个sheet
        excelReader.read(readSheet);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }
}
