package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.StudentDaoImpl;
import modal.student;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;

public class studentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField chi;
	private JTextField eng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentUI frame = new studentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public studentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(6, 6, 438, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("學員管理系統");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(175, 6, 90, 28);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(6, 46, 438, 102);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setBounds(31, 29, 41, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("國文：");
		lblNewLabel_1_1.setBounds(31, 56, 39, 16);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("英文：");
		lblNewLabel_1_1_1.setBounds(154, 56, 39, 16);
		panel_1.add(lblNewLabel_1_1_1);
		
		name = new JTextField();
		name.setBounds(72, 24, 60, 26);
		panel_1.add(name);
		name.setColumns(10);
		
		chi = new JTextField();
		chi.setColumns(10);
		chi.setBounds(72, 51, 60, 26);
		panel_1.add(chi);
		
		eng = new JTextField();
		eng.setColumns(10);
		eng.setBounds(195, 51, 60, 26);
		panel_1.add(eng);
		
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setBounds(6, 152, 438, 114);
		contentPane.add(panel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 12, 247, 92);
		panel_1_1.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);
		output.setBackground(SystemColor.window);
		
		JButton search = new JButton("查詢");
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				output.setText(new StudentDaoImpl().queryAll1());
			}
		});
		search.setBounds(303, 18, 117, 34);
		panel_1_1.add(search);
		
		JButton btnlist = new JButton("查詢(List)");
		btnlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.List-->queryAll2();
				 * 2.show:String;
				 * 
				 */
				List<student> l=new StudentDaoImpl().queryAll2();
				String show="";
				int sum=0;
				int i=0;
				for(student o:l)
				{
					i++;
					sum=sum+o.getChi()+o.getEng();
					
					show=show+"id="+o.getId()+
							  "\t姓名："+o.getName()+
							  "\t國文："+o.getChi()+
							  "\t英文："+o.getEng()+
							  "\t總分："+(o.getChi()+o.getChi())+"\n";
				}
				show=show+"\n合計總分："+sum+"\t平均總分："+(sum/i);
				output.setText(show);
			}
		});
		btnlist.setBounds(303, 64, 117, 34);
		panel_1_1.add(btnlist);
		
		JButton add = new JButton("新增");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.擷取 name,chi,eng getText()
				 * 2.name,chi-->轉整數
				 * 3.new student(name,chi,eng);
				 * 4.add(s);
				 */
				String Name=name.getText();
				int Chi=Integer.parseInt(chi.getText());
				int Eng=Integer.parseInt(eng.getText());
				
				student s=new student(Name,Chi,Eng);
				new StudentDaoImpl().add(s);
			}
		});
		
		add.setBounds(303, 23, 117, 60);
		panel_1.add(add);
	}
}
