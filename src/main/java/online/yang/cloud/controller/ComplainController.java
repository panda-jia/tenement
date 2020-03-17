package online.yang.cloud.controller;

import online.yang.cloud.model.Complain;
import online.yang.cloud.service.ComplainService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 孙嘉
 * created in 2020/2/5 10:15
 */

@RestController
@RequestMapping(value = "/complain")
public class ComplainController {

    private ComplainService complainServiceImpl;

    public ComplainController(ComplainService complainServiceImpl) {
        this.complainServiceImpl = complainServiceImpl;
    }

    @PostMapping(value = "/addComplain")
    public int addComplain(Complain complain) {
        return complainServiceImpl.add(complain);
    }

    @PostMapping(value = "/updateComplain")
    public int updateComplain(Complain complain) {
        return complainServiceImpl.update(complain);
    }

    @PostMapping(value = "/delComplain/{id}")
    public int delComplain(@PathVariable("id") String id) {
        return complainServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Complain> findAll(PageInfo<Complain> info) {
        return complainServiceImpl.findAll(info);
    }

    @GetMapping(value = "/findLastTwo")
    public List<Complain> findLastTwo() {
        return complainServiceImpl.findLastTwo();
    }

}
