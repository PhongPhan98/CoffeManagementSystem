package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Scrollbar;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.tools.Diagnostic;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import java.text.*;
import java.awt.print.*;

public class QuanLyThucDon extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JTextField textField_2;
	private JTable table;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyThucDon frame = new QuanLyThucDon(null);
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
	String TenDoUong;
	int MaBan;
	String TenBan;
	int MaHD;
	int Gia;
	int SoLuong;
	int ThanhTien;
	int TongTien;
	String NgayThanhToan;
	
	private void fillComboBox() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str ="select * from douong";
			PreparedStatement pst = con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				String TenDoUong = rs.getString("TenDoUong");
				comboBox.addItem(TenDoUong);
				}
	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	
	public void disBan() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str =  "SELECT * FROM ban";
			PreparedStatement pst =  con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	
	public void disChiTietHoaDon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str =  "SELECT TenDoUong, Gia, SoLuong, ThanhTien FROM chitiethoadon where MaHD = '"+MaHD+"'";
			PreparedStatement pst =  con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public QuanLyThucDon() {}
	public QuanLyThucDon(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Quản lý bán hàng ");
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/order.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 998, 55);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
				quanLyChung.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(64, 48, 160, 41);
		contentPane.add(btnNewButton);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 152, 306, 331);
		disBan();
		
		JLabel lblSLng = new JLabel("Chọn số lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSLng.setBounds(347, 183, 102, 35);
		contentPane.add(lblSLng);
		
		textField = new JTextField();
		textField.setBounds(459, 183, 140, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(347, 245, 432, 239);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		disChiTietHoaDon();
		scrollPane_1.setViewportView(table_1);
		
		
		JButton btnNewButton_1 = new JButton("Thêm đồ uống");
		btnNewButton_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");				
					SoLuong = Integer.parseInt(textField.getText());
											
					String sql = "select * from douong where TenDoUong = '" +TenDoUong+ "'";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs  = ps.executeQuery();
					while(rs.next()){
						Gia = Integer.parseInt(rs.getString(2));
					}
					
					ThanhTien = Gia*SoLuong;
					
					String sql2 = "insert into chitiethoadon(MaHD, TenDoUong, Gia, SoLuong, ThanhTien) values ('"+MaHD+"','"+TenDoUong+"','"+Gia+"','"+SoLuong+"','"+ThanhTien+"')";
					Statement ps2 = con.createStatement();
					ps2.executeUpdate(sql2);
									
					JOptionPane.showMessageDialog(null,"Gọi món thành công !");
					
					disChiTietHoaDon();
					con.close();
					
					
				} catch (Exception e1) {
					System.out.print(e1);
				}

				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(621, 134, 176, 84);
		contentPane.add(btnNewButton_1);
		
		
		 LocalDateTime localDate = LocalDateTime.now();
		 NgayThanhToan = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDate);
		
		JButton btnNewButton_2 = new JButton("Thanh toán");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					
					String sql = "delete from ban where MaBan = '"+MaBan+"'";
					 PreparedStatement statement = con.prepareStatement(sql);
					  statement.executeUpdate();
					    
					  String sql1 = "delete from chitiethoadon where MaHD = '"+MaHD+"'";
						 PreparedStatement statement1 = con.prepareStatement(sql1);
						  statement1.executeUpdate(); 
							  
							  String sql3 = "update hoadon set TongTien = '"+TongTien+"' where MaHD = '"+MaHD+"'";
								 PreparedStatement statement3 = con.prepareStatement(sql3);
								  statement3.executeUpdate();
								  
								  String sql4 = "update hoadon set TenBan = '"+TenBan+"' where MaHD = '"+MaHD+"'";
									 PreparedStatement statement4 = con.prepareStatement(sql4);
									  statement4.executeUpdate();
									  
									  String sql5 = "update hoadon set NgayThanhToan = '"+NgayThanhToan+"' where MaHD = '"+MaHD+"'";
										 PreparedStatement statement5 = con.prepareStatement(sql5);
										  statement5.executeUpdate();
										  
										  String checkThanhToan = "Da thanh toan";
										  String sql6 = "update hoadon set TrangThai = '"+checkThanhToan+"' where MaHD = '"+MaHD+"'";
											 PreparedStatement statement6 = con.prepareStatement(sql6);
											  statement6.executeUpdate();
											  
											  String sql7 = "update hoadon set NhanVien = '"+tenHienThi+"' where MaHD = '"+MaHD+"'";
												 PreparedStatement statement7 = con.prepareStatement(sql7);
												  statement7.executeUpdate();
						    
					disBan();
					textField_2.setText("");
					disChiTietHoaDon();
					
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setBounds(807, 457, 152, 61);
		contentPane.add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_2.setForeground(Color.RED);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(807, 315, 181, 41);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if( arg0.getStateChange() == ItemEvent.SELECTED ) {
					TenDoUong = comboBox.getSelectedItem().toString();
				}
				
			}
		});
	
		comboBox.setToolTipText("---Chọn đồ uống ---");
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(459, 134, 140, 37);
		contentPane.add(comboBox);
		
		JLabel lblChnUng = new JLabel("Chọn đồ uống");
		lblChnUng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChnUng.setBounds(347, 135, 102, 35);
		contentPane.add(lblChnUng);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setBounds(807, 145, 144, 61);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 134, 317, 408);
		contentPane.add(scrollPane_2);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textField_1.setText(model.getValueAt(selectedRowIndex, 1).toString());
				textField_2.setText("");
				
				MaBan =  Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					
					String sql1 = "SELECT * FROM hoadon WHERE MaBan = '"+ MaBan+"'";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ResultSet rs1 = ps1.executeQuery();
					while (rs1.next()) {
						MaHD = Integer.parseInt(rs1.getString(1));						
					}
					
					String sql = "SELECT * FROM ban WHERE MaBan = '"+ MaBan+"'";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						TenBan = rs.getString(2);						
					}
					
					
				} catch (Exception e2) {
					System.out.print(e2);
				}
				disChiTietHoaDon();
			}
		});
		scrollPane_2.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("Tổng Tiền ");
		btnNewButton_3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/thongke.jpg")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");	
					String sql = "SELECT SUM(ThanhTien) as TongTien FROM chitiethoadon WHERE MaHD = '"+MaHD+"' ";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs =  ps.executeQuery();
					while(rs.next()){
						TongTien = Integer.parseInt(rs.getString(1));
					}
					
					textField_2.setText(Integer.toString(TongTien));
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3.setBounds(807, 245, 181, 59);
		contentPane.add(btnNewButton_3);
		
		
		
		
		
		
		
		JButton btnNewButton_4 = new JButton("In hóa đơn");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MessageFormat header = new MessageFormat("Hóa đơn "+TenBan);
				MessageFormat footer = new MessageFormat("Page{0,number, integer}");
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					
					table_1.print(JTable.PrintMode.NORMAL, header, footer);

				
				
				} catch (Exception e) {
					System.err.format("Cannot print %$%n",e.getMessage());
				}
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_4.setBounds(807, 385, 152, 41);
		contentPane.add(btnNewButton_4);
		disBan();	
		fillComboBox();
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
