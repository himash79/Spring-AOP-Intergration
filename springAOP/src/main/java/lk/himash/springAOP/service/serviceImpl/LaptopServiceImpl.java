package lk.himash.springAOP.service.serviceImpl;

import lk.himash.springAOP.entity.Laptop;
import lk.himash.springAOP.repository.LaptopRepository;
import lk.himash.springAOP.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository lapRepo;

    @Override
    public ResponseEntity<?> getAllLaptopDetails() {
        List<Laptop> laptops = lapRepo.findAll();
        System.out.println("laptops : " + laptops);
//        throw new NullPointerException("ERROR"); // For After Throwing in AOP
        return new ResponseEntity<>(laptops, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<?> getLaptop(String id) {
		Laptop laptop = lapRepo.findById(id).get();
		return new ResponseEntity<>(laptop, HttpStatus.OK);
	}
    
    
}
