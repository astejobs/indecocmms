package com.aste.lsme.domain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.global.Base64Coder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeGenerator {
	public static void generateQrcode(String qrCode,String path) throws IOException, WriterException {

		// String filePath = Constants.PATH + qrCode;
		//String filePath = Constants.QR_IMAGES_PATH + qrCode;
		String filePath = path + qrCode;
		// int size = 500;
		String fileType = "png";
		File myFile = new File(filePath);

		Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		// Now with zxing version 3.2.1 you could change border size (white
		// border size to just 1)
		hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCode, BarcodeFormat.QR_CODE, 500, 500, hintMap);

		int CrunchifyWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < CrunchifyWidth; i++) {
			for (int j = 0; j < CrunchifyWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}

		}
		if (!new File(Constants.QR_IMAGES_PATH).exists())
			;
		new File(Constants.QR_IMAGES_PATH).mkdirs();
		ImageIO.write(image, fileType, myFile);

	}

	@RequestMapping(value = "/getimage/{imageName:.+}", method = RequestMethod.GET)
	public static void getImage(@PathVariable("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) throws IOException, WriterException {

		try {
			InputStream is = new FileInputStream(Constants.PATH + imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}

	}

	public static boolean uploadImage(MultipartFile pimage, RedirectAttributes redirectAttributes, String imageName) {
		if (!pimage.isEmpty()) {
			if (!new File(Constants.PATH).exists())
				;
			new File(Constants.PATH).mkdirs();

			if (!checkExtension(pimage.getContentType())) {
				redirectAttributes.addFlashAttribute("fail", "Allowed Image Type (png/jpg/gif)");
				return false;
			}
		}
		try {
			pimage.transferTo(new File(Constants.PATH + imageName));

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "Image Cannot be uploaded");
			return false;
		}
		return true;
	}

	public static Boolean checkExtension(String extension) {
		if (extension.equals("image/jpeg"))
			return true;
		else if (extension.equals("image/gif"))
			return true;
		else if (extension.equals("image/jpg"))
			return true;
		else if (extension.equals("image/png"))
			return true;

		else
			return false;
	}

	public static String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "image/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}

	/*
	 * public static String generateCode(String type){
	 * 
	 * String imageName =
	 * type+System.currentTimeMillis()+ThreadLocalRandom.current().nextInt(100,
	 * 1000); return imageName; }
	 */

	@SuppressWarnings("resource")
	public static boolean saveImage(String bImage, String imageName) {
		System.out.println("gujraattttttt--------bImage" + bImage + "-----------imageName" + imageName);
		String beforeImage = getImageData(bImage);
		byte[] bArray = Base64Coder.decode(beforeImage);
		File folder = new File(Constants.PATH);
		if (!folder.exists())
			folder.mkdirs();
		try {
			new FileOutputStream(Constants.PATH + imageName).write(bArray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String getImageData(String beforeImage) {
		System.err.println("-------------" + beforeImage + "-----------------------");
		return beforeImage.split("@")[1];
	}

}
