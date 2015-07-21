package com.dream.parser;

import java.awt.FileDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.dream.data.Document;
import com.dream.service.DocIndexCreater;
import com.dream.service.DreamControler;


public class DreamSearcher {

	protected Shell shell;
	private Text text;
	private Button button;
	private Table table;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private DirectoryDialog fileDialog;
	private String filePath;
	private TableColumn tableColumn_3;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DreamSearcher window = new DreamSearcher();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(690, 460);
		shell.setText("Dream Searcher");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(49, 10, 74, 17);
		label.setText("查找关键字:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(129, 7, 520, 23);
		
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(filePath==null || text.getText()==null){
					MessageBox dialog=new MessageBox(shell,SWT.ERROR_INVALID_ARGUMENT);
					dialog.setText("[参数错误]");
					dialog.setMessage("请输入合法的【文件夹或文件的路径】以及【查询关键词】");
					dialog.open();
					return;
				}
				
				//在哪个文件里找
				File file=new File(filePath);
				//关键字列表
				List<String> queryList=new ArrayList<String>();
				queryList.addAll(Arrays.asList(text.getText().split(" ")));
				
				DocIndexCreater docIndexCreater=new DocIndexCreater(file.getAbsolutePath());
				
				if(docIndexCreater.getDocumentList()==null 
						|| docIndexCreater.getDocumentList().isEmpty())
				{
					MessageBox dialog=new MessageBox(shell,SWT.ERROR_INVALID_ARGUMENT);
					dialog.setText("[参数错误]");
					dialog.setMessage("请输入合法的【文件夹或文件的路径】");
					dialog.open();
					return;
				}
				
				List<Document> documentList=docIndexCreater.getDocumentList(); //获取用户选择的文件夹下的所有文件信息
				
				DreamControler controller=DreamControler.getInstance();
				controller.setDocList(documentList);
				controller.setQueryList(queryList);
				controller.run();
				List<Document> result=controller.getResult();
				//清空表格
				table.removeAll();
				if(result!=null && !result.isEmpty()){
					for(int i=0;i<result.size();i++){
						TableItem item=new TableItem(table,SWT.NULL);
						item.setText(0,result.get(i).getFileName());
						item.setText(1,result.get(i).getFilePath());
						item.setText(2, result.get(i).getFileTime());
						item.setText(3,result.get(i).getFileType());
					}
				}
			}
		});
		button.setBounds(569, 36, 80, 27);
		button.setText("给我搜");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(19, 69, 645, 341);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(140);
		tableColumn.setText("文件名");
		
		tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(254);
		tableColumn_1.setText("文件地址");
		
		tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(114);
		tableColumn_3.setText("文件时间");
		
		tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(313);
		tableColumn_2.setText("文件类型");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				fileDialog=new DirectoryDialog(shell);
				fileDialog.setMessage("请选择需要被搜索的文件或者文件夹");
				filePath=fileDialog.open();
				text_1.setText(filePath);
			}
		});
		button_1.setBounds(33, 36, 120, 27);
		button_1.setText("选择文件或文件夹");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(164, 36, 399, 23);
		
	}
}
