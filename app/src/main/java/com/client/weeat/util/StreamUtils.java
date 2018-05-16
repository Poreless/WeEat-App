package com.client.weeat.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {


	public static String readStream(InputStream is){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while(( len = is.read(buffer))!=-1){
				baos.write(buffer, 0, len);            //一次读取字节
			}
			is.close();
			String result = baos.toString();
			Log.e("test",result);
			if(result.contains("gb2312")){
				return baos.toString("gb2312");
			}else{
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Bitmap readBitmap(InputStream is){
		return BitmapFactory.decodeStream(is);
	}
}
