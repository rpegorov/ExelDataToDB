package com.rpegorov.exeldatatobd.services.interf;

import com.rpegorov.exeldatatobd.models.entity.Product;

import java.util.List;

public interface IExcelDataService {

    List<Product> getExcelDataAsList();

    int saveExcelData(List<Product> products);
}
