package cn.e3mall.service;

import common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;




public interface PictureService {

	public PictureResult uploadPicture(MultipartFile uploadFile);
}
