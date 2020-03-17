package online.yang.cloud.controller;

import online.yang.cloud.model.Notice;
import online.yang.cloud.service.NoticeService;
import online.yang.cloud.utils.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author 孙嘉
 * created in 2020/2/4 18:32
 */

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    private NoticeService noticeServiceImpl;

    public NoticeController(NoticeService noticeServiceImpl) {
        this.noticeServiceImpl = noticeServiceImpl;
    }

    @PostMapping(value = "/addNotice")
    public int addNotice(Notice notice) {
        return noticeServiceImpl.add(notice);
    }

    @PostMapping(value = "/updateNotice")
    public int updateNotice(Notice notice) {
        return noticeServiceImpl.update(notice);
    }

    @PostMapping(value = "/delNotice/{id}")
    public int delNotice(@PathVariable("id") String id) {
        return noticeServiceImpl.delete(id);
    }

    @GetMapping(value = "/findAll")
    public PageInfo<Notice> findAll(PageInfo<Notice> info) {
        return noticeServiceImpl.findAll(info);
    }

    @GetMapping(value = "/findLastNotice")
    public Notice findLastNotice() {
        return noticeServiceImpl.findLastNotice();
    }

}
