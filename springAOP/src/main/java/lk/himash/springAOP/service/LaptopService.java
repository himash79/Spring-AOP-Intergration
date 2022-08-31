package lk.himash.springAOP.service;

import org.springframework.http.ResponseEntity;

public interface LaptopService {

    public ResponseEntity<?> getAllLaptopDetails();
    public ResponseEntity<?> getLaptop(String id);
}
