package lk.himash.springAOP.controller;

import lk.himash.springAOP.aop.ExceptionCustomAop;
import lk.himash.springAOP.aop.LoggingCustomAop;
import lk.himash.springAOP.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lapService")
public class LaptopController {

    @Autowired
    private LaptopService lapService;

    @LoggingCustomAop
    @GetMapping("/getAllLapDetails")
    public ResponseEntity<?> getAllLaptopDetails() {
        return lapService.getAllLaptopDetails();
    }
    
    @ExceptionCustomAop
    @GetMapping("/getLaptop/{id}")
    public ResponseEntity<?> getLaptop(@PathVariable String id) {
    	return lapService.getLaptop(id);
    }
    
}
