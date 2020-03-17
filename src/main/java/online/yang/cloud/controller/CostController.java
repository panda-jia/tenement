package online.yang.cloud.controller;

import online.yang.cloud.model.Cost;
import online.yang.cloud.service.CostService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author .
 * created in 2020/2/4 9:14
 */

@RestController
@RequestMapping(value = "/cost")
public class CostController {

    private CostService costServiceImpl;

    public CostController(CostService costServiceImpl) {
        this.costServiceImpl = costServiceImpl;
    }

    @PostMapping(value = "/addCost")
    public int addCost(Cost cost) {
        return costServiceImpl.add(cost);
    }

    @PostMapping(value = "/updateCost")
    public int updateCost(Cost cost) {
        return costServiceImpl.update(cost);
    }

    @PostMapping(value = "/delCost/{id}")
    public int delCost(@PathVariable("id") String id) {
        return costServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Cost> findAll(PageInfo<Cost> info, Cost cost) {
        return costServiceImpl.findAll(info, cost);
    }

    @GetMapping(value = "/findById/{id}")
    public Cost findById(@PathVariable("id") String id) {
        return costServiceImpl.findById(id);
    }

    @GetMapping(value = "/findByTypeAndOwnerNo")
    public Cost findByTypeAndOwnerNo(Cost cost) {
        return costServiceImpl.findByTypeAndOwnerNo(cost.getCostType(), cost.getCostOwnerNo());
    }

}
