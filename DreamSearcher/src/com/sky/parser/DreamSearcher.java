package com.sky.parser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JFileChooser;

import com.dream.data.Document;
import com.dream.service.DocIndexCreater;

public class DreamSearcher {

	public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException{
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile(); //获取用户选择的文件或者文件夹
			
			DocIndexCreater docIndexCreater=new DocIndexCreater(file.getAbsolutePath());
			List<Document> documentList=docIndexCreater.getDocumentList(); //获取用户选择的文件夹下的所有文件信息
			
			File outputFile=new File("document.data"); //将文件存入到本地磁盘中，以便下次读取
			ObjectOutputStream output=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
			output.writeObject(documentList);
			output.close();
			
			
			ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(new FileInputStream(outputFile)));
			List<Document> docListCopy=(List<Document>)input.readObject();
			input.close();
		}
	}
}
