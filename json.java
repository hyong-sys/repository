package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import okhttp3.OkHttpClient;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		//haoyong UPDATE
		SpringApplication.run(DemoApplication.class, args);
		//获取json的网址	haoyong Update
		String url = "http://123.56.191.196:8080/a/a/d/list_slide_1_0.json";
		//调用方法，返回string类型的json并输出
		String json = loadJson(url);
		System.out.println(json);
		//利用阿里json包转成list<map>
		List<Map<String,String>> listObjectFir = (List<Map<String,String>>) JSONArray.parse(json);
		//forEach循环输出
		listObjectFir.forEach(bean->{
			System.out.println(bean.get("title"));
			System.out.println(bean.get("photo"));
			System.out.println(bean.get("contentUrl"));
			System.out.println(bean.get("doc_type"));
			try {
				downPicture(bean.get("photo"),bean.get("title"),"C:\\Users\\Administrator\\Desktop\\img");//C:\Users\Administrator\Desktop\img
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private static void downPicture(String photo, String title,String savePath) throws Exception{

		// 构造URL
		URL url = new URL(photo);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(5*1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf=new File(savePath);
		if(!sf.exists()){
			sf.mkdirs();
		}
    	// 获取图片的扩展名
		String extensionName = photo.substring(photo.lastIndexOf(".") +1);
		int  number= (int)(Math.random()*10);
		// 新的图片文件名 = 编号 +"."图片扩展名
		String newFileName = number + "." +  extensionName;
		OutputStream os = new FileOutputStream(sf.getPath()+"\\"+title);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();

	}

	/*private static void downPicture(String photo, String title) {
		URL url = null;
		int imageNumber = 0;

		try {
			url = new URL(photo);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());

			String imageName =  "C:/Users/Administrator/Desktop/img/zxing.png";//C:\Users\Administrator\Desktop\img

			FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			byte[] context=output.toByteArray();
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/


	public static String loadJson (String url) {
		StringBuilder json = new StringBuilder();
		try {
			//获取url
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}






	}




