package com.xiaofei.face;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 * 图片人脸识别
 */
public class identificate {

	public static void main(String[] args) {
		
		// 指定读出的图片路径和输出的文件
	    String inputImagePath = identificate.class.getClassLoader().getResource("hf.jpg").getPath().substring(1);
	    String outputImageFile = "identificate.png";
	    
	    String xmlPath = identificate.class.getClassLoader().getResource("cascade_storage.xml").getPath().substring(1);
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    CascadeClassifier faceDetector = new CascadeClassifier(xmlPath);
	    Mat image = Highgui.imread(inputImagePath);
	    MatOfRect faceDetections = new MatOfRect();
	    faceDetector.detectMultiScale(image, faceDetections);
	    
	    // 画出脸的位置
	    for (Rect rect : faceDetections.toArray()) {
	        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
	    }

	    // 写入到文件
	    Highgui.imwrite(outputImageFile, image);
	    
	    System.out.print("\nOK!");
	}

}
