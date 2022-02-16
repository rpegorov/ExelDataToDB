package com.rpegorov.exeldatatobd.controllers;

import com.rpegorov.exeldatatobd.models.entity.Product;
import com.rpegorov.exeldatatobd.services.interf.CompanyServices;
import com.rpegorov.exeldatatobd.services.interf.IExcelDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductControllers {

    private IFileUploaderService fileUploaderService;
    private IExcelDataService excelService;

    @GetMapping("/")
    public String index() {
        return "uploadPage";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileUploaderService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
                "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/saveData")
    public String saveExcelData(Model model) {

        List<Product> excelDataAsList = excelService.getExcelDataAsList();
        int noOfRecords = excelService.saveExcelData(excelDataAsList);
        model.addAttribute("noOfRecords",noOfRecords);
        return "success";
    }
}