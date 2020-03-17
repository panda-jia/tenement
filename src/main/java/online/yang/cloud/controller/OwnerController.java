package online.yang.cloud.controller;

import online.yang.cloud.model.Owner;
import online.yang.cloud.service.OwnerService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author .
 * created in 2020/1/31 18:25
 */

@RestController
@RequestMapping(value = "/owner")
public class OwnerController {

    private OwnerService ownerServiceImpl;

    public OwnerController(OwnerService ownerServiceImpl) {
        this.ownerServiceImpl = ownerServiceImpl;
    }

    @PostMapping(value = "/addOwner")
    public int addOwner(Owner owner) {
        return ownerServiceImpl.add(owner);
    }

    @PostMapping(value = "/updateOwner")
    public int updateOwner(Owner owner) {
        return ownerServiceImpl.update(owner);
    }

    @PostMapping(value = "/delOwner/{id}")
    public int delOwner(@PathVariable("id") String id) {
        return ownerServiceImpl.delete(id);
    }

    @GetMapping(value = "/selectById/{id}")
    public Owner selectByPrimaryKey(@PathVariable("id") String id) {
        return ownerServiceImpl.findById(id);
    }

    @GetMapping(value = "/findAllByPage")
    public PageInfo<Owner> findAllByPage(PageInfo<Owner> info, Owner owner) {
        return ownerServiceImpl.findAllByPage(info, owner);
    }

    @GetMapping(value = "/findByOwnerNo/{ownerNo}")
    public Owner findByOwnerNo(@PathVariable("ownerNo") String ownerNo) {
        return ownerServiceImpl.findByOwnerNo(ownerNo);
    }

    @GetMapping(value = "/getEchartsMap")
    public Map<String, Object> getEchartsMap() {
        return ownerServiceImpl.getEchartsMap();
    }

}
