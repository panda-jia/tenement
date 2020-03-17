package online.yang.cloud.controller;

import online.yang.cloud.model.House;
import online.yang.cloud.service.HouseService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author .
 * created in 2020/2/2 20:54
 */

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    private HouseService houseServiceImpl;

    public HouseController(HouseService houseServiceImpl) {
        this.houseServiceImpl = houseServiceImpl;
    }

    @PostMapping(value = "/addHouse")
    public int addHouse(House house) {
        return houseServiceImpl.add(house);
    }

    @PostMapping(value = "/updateHouse")
    public int updateHouse(House house) {
        return houseServiceImpl.update(house);
    }

    @PostMapping(value = "/delHouse/{id}")
    public int delHouse(@PathVariable("id") String id) {
        return houseServiceImpl.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public House findById(@PathVariable("id") String id) {
        return houseServiceImpl.findById(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<House> findAll(PageInfo<House> info) {
        return houseServiceImpl.findAll(info);
    }

    @GetMapping(value = "/findAllByNoAndUnitAndBuilding")
    public House findAllByNoAndUnitAndBuilding(House house) {
        return houseServiceImpl.findByNoAndUnit(house);
    }

    @GetMapping(value = "/findByBuilding/{building}")
    public int findByBuilding(@PathVariable("building") String building) {
        return houseServiceImpl.findByBuilding(building);
    }

}
