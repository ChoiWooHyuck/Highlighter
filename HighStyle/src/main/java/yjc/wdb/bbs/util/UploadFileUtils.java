package yjc.wdb.bbs.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	public static String uploadFile(String originalName, String uploadPath, byte[] fileData) throws Exception {
		
		/*
		 * originalName = rose.jpg
		 * ==>
		 * 
		 * rose_wnfoqwnpfq.jpg
		 */
		UUID uid = UUID.randomUUID();
		int extIdx = originalName.lastIndexOf(".");
		String nameWithoutExtension = originalName.substring(0, extIdx);
		String savedName = nameWithoutExtension + "_" + uid.toString();
		savedName += originalName.substring(extIdx);
		
		String savedPath = calculatePath(uploadPath);
		
		File target = new File(uploadPath+savedPath, savedName); //uploadPath ��ΰ� �������������� ��������
//		if(target.getParentFile().exists()==false){
//			target.getParentFile().mkdirs(); // mkdir ���� ���� ����  & mkdirs resource ���ϰ� ���� ���� ����
//		}
		System.out.println(target.getAbsolutePath());
		FileCopyUtils.copy(fileData, target); // fileData�� �������?
		
		// �̹��� �����̸� thumbnail ����
		// �ƴϸ� icon ����
		String thumbNailName = "";
		String fileExtension = originalName.substring(extIdx+1);
		
		if(MediaUtils.getMediaType(fileExtension.toLowerCase()) != null) {
			thumbNailName = makeThumbNail(uploadPath, savedPath, savedName); // ������ġ �������Ͼ���� �˷������
		} else {
			thumbNailName = makeIcon(uploadPath, savedPath, savedName); 
		}
		
		return thumbNailName;
	}
	private static String makeIcon(String uploadPath, String path,
			String fileName) throws Exception{
		String iconName=uploadPath+path+File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar,'/');
	}
	
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbNailName = uploadPath + path + File.separator + "thumbNail_" + fileName; // /resources/upload/2017/05/12/thumbNail_rose_XXXX.jpg
		
		File newFile = new File(thumbNailName);
		
		String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, fileExtension.toLowerCase(), newFile);
		
		thumbNailName=thumbNailName.substring(uploadPath.length());
		thumbNailName=thumbNailName.replace(File.separatorChar,'/');
		return thumbNailName;
	}
	
	private static String calculatePath(String uploadPath){
		Calendar cal = Calendar.getInstance();

		/*	/2017	*/
//		String yearPath = "/" + cal.get(Calendar.YEAR);
		String yearPath = File.separator + cal.get(Calendar.YEAR); // OS�� ���� /�� \�� �°� ��������
		
		DecimalFormat df = new DecimalFormat("00");
		
		String monthPath = df.format(cal.get(Calendar.MONTH)+1);
		/*	/2017/05	*/
		monthPath = yearPath + File.separator + monthPath;
		
		/*	/2017/05/12	*/
		String datePath = File.separator + cal.get(Calendar.DATE);
		
		datePath = monthPath + datePath;
		
		File folder = new File(uploadPath+datePath);
		if(folder.exists()==false){
			folder.mkdirs();
		}
		
		return datePath;
		
	}
}
