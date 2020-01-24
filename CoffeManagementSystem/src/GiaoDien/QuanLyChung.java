package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import GiaoDien.MenuDangNhap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
public class QuanLyChung extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyChung frame = new QuanLyChung();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					  frame.setLocationRelativeTo(null);
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
	public QuanLyChung() {};
	public QuanLyChung(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lý Chung");
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlychung.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 53, 653, 97);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Bàn");
		btnNewButton_2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/ban.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyBan quanLyBan = new QuanLyBan(tenHienThi);
				quanLyBan.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(105, 190, 192, 88);
		contentPane.add(btnNewButton_2);
		
		JButton btnOrders = new JButton("Thanh toán");
		btnOrders.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/order.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyThucDon quanLyThucDon= new QuanLyThucDon(tenHienThi);
				quanLyThucDon.setVisible(true);
				dispose();
			}
		});
		btnOrders.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnOrders.setBounds(384, 190, 192, 88);
		contentPane.add(btnOrders);
		
		JButton btnThngK = new JButton("Thống kê");
		btnThngK.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/thongke.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnThngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyThongKe quanLyThongKe = new QuanLyThongKe(tenHienThi);
				quanLyThongKe.setVisible(true);
				dispose();
			}
		});
		btnThngK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnThngK.setBounds(105, 334, 192, 88);
		contentPane.add(btnThngK);
		
		JButton btnUng = new JButton("Đồ uống");
		btnUng.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drink.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnUng.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyDoUong quanLyDoUong = new QuanLyDoUong(tenHienThi);
				quanLyDoUong.setVisible(true);
				dispose();
			}
		});
		btnUng.setBounds(384, 334, 192, 88);
		contentPane.add(btnUng);
		
		JLabel lblNewLabel_1 = new JLabel("Xin Chào " + tenHienThi);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(486, 11, 177, 31);
		contentPane.add(lblNewLabel_1);
		
		
		MenuDangNhap menuDangNhap = new MenuDangNhap();		
		JMenu mnTiKhon = new JMenu("Tài khoản");
		mnTiKhon.setBounds(10, 11, 107, 22);

		
		JMenuItem btnNewButton4 = new JMenuItem("Quản lý nhân viên");
		btnNewButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien(tenHienThi);
				quanLyNhanVien.setVisible(true);
				dispose();
			}
		});
		
		
		JMenuItem btnNewButton1 = new JMenuItem("Thêm tài khoản");
		
		//cai dat su kien khi nhan them tai khoan 
		btnNewButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				if(tenHienThi.equals("Admin")) {
				ThemTaiKhoan themTaiKhoan = new ThemTaiKhoan(tenHienThi);
				themTaiKhoan.setVisible(true);
				dispose();
			
		}
		});
		if(tenHienThi.equals("Admin")) {mnTiKhon.add(btnNewButton1);mnTiKhon.add(btnNewButton4);}
	
		
		JMenuItem btnNewButton2 = new JMenuItem("Đổi mật khẩu");
		//cai dat su kien khi nhan doi mat khau 
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau doiMatKhau = new DoiMatKhau(tenHienThi);
				doiMatKhau.setVisible(true);
				dispose();
			}
		});

		
		

		
		mnTiKhon.add(btnNewButton2);
		
		JMenuItem btnNewButton3 = new JMenuItem("Đăng xuất");
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuDangNhap menuDangNhap = new MenuDangNhap();
				menuDangNhap.setVisible(true);
				dispose();
			}
		});
		
		mnTiKhon.add(btnNewButton3);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(mnTiKhon);
		this.setJMenuBar(menuBar);
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		
	}
}
