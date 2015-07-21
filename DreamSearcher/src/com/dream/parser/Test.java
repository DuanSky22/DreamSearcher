package com.dream.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dream.data.Document;
import com.dream.service.DocIndexCreater;
import com.dream.service.DreamControler;

public class Test {

	public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException{
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile(); //获取用户选择的文件或者文件夹
			
			String query="自然语言";
			List<String> queryList=new ArrayList<String>();
			queryList.add(query);
			
			DocIndexCreater docIndexCreater=new DocIndexCreater(file.getAbsolutePath());
			List<Document> documentList=docIndexCreater.getDocumentList(); //获取用户选择的文件夹下的所有文件信息
			
			DreamControler controller=DreamControler.getInstance();
			controller.setDocList(documentList);
			controller.setQueryList(queryList);
			controller.run();
			List<Document> result=controller.getResult();
			int i=0;
			System.out.println(i);
			
		}
	}
}
