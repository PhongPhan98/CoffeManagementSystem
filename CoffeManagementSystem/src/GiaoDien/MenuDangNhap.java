package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StringContent;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;

import java.sql.*;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class MenuDangNhap extends JFrame {
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	public  String tenHienThi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuDangNhap frame = new MenuDangNhap();
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
	public MenuDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 515, 458);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblngNhpH = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblngNhpH.setBackground(Color.GRAY);
		lblngNhpH.setHorizontalAlignment(SwingConstants.CENTER);
		lblngNhpH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblngNhpH.setBounds(10, 28, 448, 41);
		contentPane.add(lblngNhpH);
		
		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(81, 186, 85, 32);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
								Statement stmtStatement = con.createStatement();
								String sql = "Select * from taikhoan where UserName ='"+textField.getText()+"' and PassWord ='"+passwordField.getText().toString()+"'";
								ResultSet rs = stmtStatement.executeQuery(sql);
								if (rs.next()) {				
									JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");	
									tenHienThi = rs.getString("HienThi");
									QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
									quanLyChung.setVisible(true);	
									dispose();
								}
								else {
									JOptionPane.showMessageDialog(null, "Kiểm tra lại tên đăng nhập và mật khẩu");
								}		
							con.close();
								
							} catch (Exception e1) {
								System.out.print(e1);
							}
				}
			}
		});
		passwordField.setBounds(210, 183, 213, 42);
		contentPane.add(passwordField);
		
		JLabel lblTnngNhp = new JLabel("Tên Đăng Nhập");
		lblTnngNhp.setBackground(Color.WHITE);
		lblTnngNhp.setToolTipText("");
		lblTnngNhp.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnngNhp.setForeground(Color.BLACK);
		lblTnngNhp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTnngNhp.setBounds(78, 116, 122, 32);
		contentPane.add(lblTnngNhp);
				
		JButton btnngNhp = new JButton("Đăng Nhập");
		btnngNhp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
								Statement stmtStatement = con.createStatement();
								String sql = "Select * from taikhoan where UserName ='"+textField.getText()+"' and PassWord ='"+passwordField.getText().toString()+"'";
								ResultSet rs = stmtStatement.executeQuery(sql);
								if (rs.next()) {				
									JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");	
									tenHienThi = rs.getString("HienThi");
									QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
									quanLyChung.setVisible(true);	
									dispose();
								}
								else {
									JOptionPane.showMessageDialog(null, "Kiểm tra lại tên đăng nhập và mật khẩu");
								}		
							con.close();
								
							} catch (Exception e1) {
								System.out.print(e1);
							}
				}
				
			}
		});
		btnngNhp.setHorizontalAlignment(SwingConstants.RIGHT);
		btnngNhp.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/dangnhap.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnngNhp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnngNhp.setBounds(174, 300, 184, 55);
		contentPane.add(btnngNhp);
		
		textField = new JTextField();
		textField.setBounds(210, 114, 213, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		


		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/login.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(10, 100, 58, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/pass.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_2.setBounds(10, 170, 58, 55);
		contentPane.add(lblNewLabel_2);
		
		btnngNhp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
							Statement stmtStatement = con.createStatement();
							String sql = "Select * from taikhoan where UserName ='"+textField.getText()+"' and PassWord ='"+passwordField.getText().toString()+"'";
							ResultSet rs = stmtStatement.executeQuery(sql);
							if (rs.next()) {				
								JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");	
								tenHienThi = rs.getString("HienThi");
								QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
								quanLyChung.setVisible(true);	
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "Kiểm tra lại tên đăng nhập và mật khẩu");
							}		
						con.close();
							
						} catch (Exception e) {
							System.out.print(e);
						}
					}
		});
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
