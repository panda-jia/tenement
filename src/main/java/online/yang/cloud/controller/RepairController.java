package online.yang.cloud.controller;

import online.yang.cloud.model.Repair;
import online.yang.cloud.service.RepairService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author 孙嘉
 * created in 2020/2/4 21:55
 */

@RestController
@RequestMapping(value = "/repair")
public class RepairController {

    private RepairService repairServiceImpl;

    public RepairController(RepairService repairServiceImpl) {
        this.repairServiceImpl = repairServiceImpl;
    }

    @PostMapping(value = "/addRepair")
    public int addRepair(Repair repair) {
        return repairServiceImpl.add(repair);
    }

    @PostMapping(value = "/updateRepair")
    public int updateRepair(Repair repair) {
        return repairServiceImpl.update(repair);
    }

    @PostMapping(value = "/delRepair/{id}")
    public int delRepair(@PathVariable("id") String id) {
        return repairServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Repair> findAll(PageInfo<Repair> info) {
        return repairServiceImpl.findAll(info);
    }

    @GetMapping(value = "/findById/{id}")
    public Repair findById(@PathVariable("id") String id) {
        return repairServiceImpl.findById(id);
    }

}
