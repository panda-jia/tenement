package online.yang.cloud.controller;

import online.yang.cloud.model.Parking;
import online.yang.cloud.service.ParkingService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author 孙嘉
 * created in 2020/2/5 14:22
 */

@RestController
@RequestMapping(value = "/parking")
public class ParkingController {

    private ParkingService parkingServiceImpl;

    public ParkingController(ParkingService parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
    }

    @PostMapping(value = "/addParking")
    public int addParking(Parking parking) {
        return parkingServiceImpl.add(parking);
    }

    @PostMapping(value = "/updateParking")
    public int updateParking(Parking parking) {
        return parkingServiceImpl.update(parking);
    }

    @PostMapping(value = "/delParking/{id}")
    public int delParking(@PathVariable("id") String id) {
        return parkingServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Parking> findAll(PageInfo<Parking> info, Parking parking) {
        return parkingServiceImpl.findAll(info, parking.getParkOwner());
    }

    @GetMapping(value = "/findByCondition")
    public Parking findByCondition(Parking parking) {
        return parkingServiceImpl.findByCondition(parking);
    }

}
