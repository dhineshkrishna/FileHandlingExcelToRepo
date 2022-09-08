package com.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.helper.ExcelHelper;
import com.file.model.Userexcel;
import com.file.repo.UserRepository;

@Service
public class ExcelService {
	
  @Autowired
  UserRepository repository;
  
  public void save(MultipartFile file) {
    try {
      List<Userexcel> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
  public List<Userexcel> getAllTutorials() {
    return repository.findAll();
  }
}