package com.example.demo;

import com.example.demo.lib.GamePlayIH;
import com.example.demo.lib.GamePlayer;
import com.example.demo.lib.IGamePlayer;
import com.example.demo.thread.Clerk;
import com.example.demo.thread.Constomer;
import com.example.demo.thread.Product;
import com.example.demo.thread.Window;
import com.example.demo.util.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//查看ss盘
		Sting roots = Arrays.toString(File.listRoots());
		//查看是否有要查找的盘
		if (roots.contains("E:\\")) {
			System.out.println("有E盘，正在执行遍历操作");
		}
		File file = new File("E:\\");
		recursion(file);
	}
	public static void recursion(File file){
		if (file.exists()) {
			//返回目录下所有文件和目录的绝对路径
			File[] files = file.listFiles();
			if (files==null ) {
				System.out.println("文件夹下无目录（空）!");
			} else {
				for (File file1 : files) {
					if (file1.isDirectory()) {
						System.out.println("文件夹:" + file1.getAbsolutePath());
						//递归
						recursion(file1);
					} else {
						System.out.println("文件:" + file1.getAbsolutePath());
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}





	}
