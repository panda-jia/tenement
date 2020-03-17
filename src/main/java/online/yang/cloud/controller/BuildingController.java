package online.yang.cloud.controller;

import online.yang.cloud.model.Building;
import online.yang.cloud.service.BuildingService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author .
 * created in 2020/2/2 9:56
 */

@RestController
@RequestMapping(value = "/building")
public class BuildingController {

    private BuildingService buildingServiceImpl;

    public BuildingController(BuildingService buildingServiceImpl) {
        this.buildingServiceImpl = buildingServiceImpl;
    }

    @PostMapping(value = "/addBuilding")
    public int addBuilding(Building building) {
        return buildingServiceImpl.add(building);
    }

    @PostMapping(value = "/updateBuilding")
    public int updateBuilding(Building building) {
        return buildingServiceImpl.update(building);
    }

    @PostMapping(value = "/delBuilding/{id}")
    public int delBuilding(@PathVariable("id") String id) {
        return buildingServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Building> findAll(PageInfo<Building> info, Building building) {
        return buildingServiceImpl.findAll(info, building);
    }

    @GetMapping(value = "/findById/{id}")
    public Building findById(@PathVariable("id") String id) {
        return buildingServiceImpl.findById(id);
    }

    @GetMapping(value = "/findByBuildName/{name}")
    public Building findByBuildName(@PathVariable("name") String name) {
        return buildingServiceImpl.findByBuildName(name);
    }

    @GetMapping(value = "/findAllByNoPage")
    public List<Building> findAllByNoPage() {
        return buildingServiceImpl.findAllByNoPage();
    }

}
