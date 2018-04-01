package cn.e3mall.controller;

import cn.e3mall.service.PictureService;
import common.pojo.PictureResult;
import common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String upload(MultipartFile uploadFile) {
        PictureResult result = pictureService.uploadPicture(uploadFile);
        return JsonUtils.objectToJson(result);
    }
}
