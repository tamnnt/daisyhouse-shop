package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Size;
import LifeLeopard.TeamShop.Responsibility.SizeReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    SizeReps sizeReps;
    public List<Size> getAllSize(){
        return sizeReps.findAll();
    }
}
