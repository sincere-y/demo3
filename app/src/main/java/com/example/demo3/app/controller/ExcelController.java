package com.example.demo3.app.controller;

import org.springframework.core.io.InputStreamResource;
import com.alibaba.excel.EasyExcel;
import com.example.demo3.module.entity.Category;
import com.example.demo3.module.entity.Gun;
import com.example.demo3.module.entity.UserData;
import com.example.demo3.module.service.CategoryService;
import com.example.demo3.module.service.GunService;
import com.example.demo3.module.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ExcelController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GunService gunService;

    @RequestMapping("/excel/upload")
    public Response uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Response(4006);
        }

        handleFile(file);

        return  new Response(1001);
    }

    @Async
    public Response handleFile(MultipartFile file) {

        List<UserData> dataList = null;
        try {
            dataList = EasyExcel.read(file.getInputStream())
                    .head(UserData.class)
                    .sheet()
                    .doReadSync();
        } catch (IOException e) {
            return  new Response(4004);
        }

        return  new Response(1001,dataList);



    }

    @RequestMapping("/categoryExport")
    public Response categoryExport() {

        exportCategoryFile();

        return new Response(1001);

    }

    @Async
    public void   exportCategoryFile() {

        List<Category> categories = categoryService.getAllInfo();
        String fileName = "D:\\Program Files (x86)\\test\\01.xlsx";
        try {
            EasyExcel.write(fileName, Category.class).sheet("分类信息")
                    .doWrite(categories);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/exportZip")
    public ResponseEntity<InputStreamResource> exportZip() throws IOException {
        // 生成ZIP文件的字节流
        ByteArrayOutputStream zipStream = new ByteArrayOutputStream();


            // 提交异步任务生成 Excel 数据
            CompletableFuture<byte[]> categoryFuture = generateExcelAsync(
                    categoryService.getAllInfo(), Category.class, "Category.xlsx"
            );
            CompletableFuture<byte[]> gunFuture = generateExcelAsync(
                    gunService.getAllInfo(), Gun.class, "Gun.xlsx"
            );
        byte[] categoryBytes = null;
        try {
            categoryBytes = categoryFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        byte[] gunBytes = null;
        try {
            gunBytes = gunFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        // 导出Category表到ZIP
        try (ZipOutputStream zipOut = new ZipOutputStream(zipStream)) {
            addToZip(zipOut, "Category.xlsx", categoryBytes);

            // 导出Gun表到ZIP
            addToZip(zipOut, "Gun.xlsx", gunBytes);
        }

        // 构建响应
        ByteArrayInputStream inputStream = new ByteArrayInputStream(zipStream.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=tables.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(new InputStreamResource(inputStream));
    }
    @Async
    public CompletableFuture<byte[]> generateExcelAsync(List<?> dataList, Class<?> clazz, String fileName) {
        try (ByteArrayOutputStream excelStream = new ByteArrayOutputStream()) {
            EasyExcel.write(excelStream, clazz)
                    .sheet("Sheet1")
                    .doWrite(dataList);
            return CompletableFuture.completedFuture(excelStream.toByteArray());
        } catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
    }


    /**
     * 将单个表的数据写入ZIP
     */

    public  <T> void addToZip(ZipOutputStream zipOut, String fileName, byte[] data) throws IOException {

        // 添加到ZIP
        zipOut.putNextEntry(new ZipEntry(fileName));
        zipOut.write(data);
        zipOut.closeEntry();
    }




}
