package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyDoUong extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyDoUong frame = new QuanLyDoUong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void disDoUong() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str =  "SELECT * FROM douong";
			PreparedStatement pst =  con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	String Name;
	/**
	 * Create the frame.
	 */
	public QuanLyDoUong() {};
	public QuanLyDoUong(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý đồ uống");
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/drink.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(0, 11, 1030, 61);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
				quanLyChung.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(36, 69, 160, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đồ uống");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(59, 161, 147, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblGiTin = new JLabel("Giá tiền");
		lblGiTin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGiTin.setBounds(59, 209, 147, 36);
		contentPane.add(lblGiTin);
		
		JLabel lblNgyKhuynMi = new JLabel("Ngày khuyến mãi");
		lblNgyKhuynMi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgyKhuynMi.setBounds(59, 256, 147, 41);
		contentPane.add(lblNgyKhuynMi);
		
		JLabel lblNgyHtKhuyn = new JLabel("Ngày hết khuyến mãi");
		lblNgyHtKhuyn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgyHtKhuyn.setBounds(59, 316, 131, 25);
		contentPane.add(lblNgyHtKhuyn);
		
		JButton btnNewButton_1 = new JButton("Thêm đồ uống");
		btnNewButton_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = con.createStatement();
					String sql =  "insert into douong (TenDoUong, GiaTien, NgayKhuyenMai, NgayHetKhuyenMai) " + " values (?, ?, ?, ?)";
					
					PreparedStatement ps = con.prepareStatement(sql);
					
					
					ps.setString(1, textField.getText());
					ps.setInt(2, Integer.parseInt(textField_1.getText()));
					ps.setString(3, textField_2.getText());
					ps.setString(4, textField_3.getText());
					ps.executeUpdate();
					disDoUong();
					
					String sql2 =  "insert into hoadon (TenDoUong, Gia) " + " values (?, ?)";
					PreparedStatement ps2 = con.prepareStatement(sql2);
					ps2.setString(1, textField.getText());
					ps2.setInt(2, Integer.parseInt(textField_1.getText()));
					ps2.executeUpdate();
		
					con.close();
				} catch (Exception e1) {
					System.out.print(e1);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 417, 180, 77);
		contentPane.add(btnNewButton_1);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);
				    
				    String sql = "update douong set TenDoUong = ?, GiaTien = ?, NgayKhuyenMai = ?, NgayHetKhuyenMai =? where TenDoUong = ?";
				    PreparedStatement ps = connection.prepareStatement(sql);
				    
				    ps.setString(1, textField.getText());
				    ps.setInt(2, Integer.parseInt(textField_1.getText()));	
					ps.setString(3, textField_2.getText());
					ps.setString(4, textField_3.getText());
					ps.setString(5, Name);
					ps.executeUpdate();

					
					String sql2 = "update hoadon set TenDoUong = ?, Gia = ? where TenDoUong = ?";
				    PreparedStatement ps2 = connection.prepareStatement(sql2);
				    
				    ps2.setString(1, textField.getText());
				    ps2.setInt(2, Integer.parseInt(textField_1.getText()));					
					ps2.setString(3, Name);
					
					ps2.executeUpdate();
					
				    
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				disDoUong();
			}
		});
		btnSa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSa.setBounds(377, 417, 152, 77);
		contentPane.add(btnSa);
		
		JButton btnXaUng = new JButton("Xóa đồ uống");
		btnXaUng.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		btnXaUng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				    String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";

				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);

				    String sql = "DELETE FROM douong WHERE TenDoUong= '"+Name+"'";
				    PreparedStatement statement = connection.prepareStatement(sql);
				    statement.execute();
				    
				    String sql2 = "DELETE FROM chitiethoadon WHERE TenDoUong= '"+Name+"'";
				    PreparedStatement statement2 = connection.prepareStatement(sql2);
				    statement2.execute();

				    
				    JOptionPane.showMessageDialog(null, "Xóa thành công!");	    
				    connection.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}		
			disDoUong();	
			}
		});
		btnXaUng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXaUng.setBounds(196, 415, 171, 77);
		contentPane.add(btnXaUng);
		
		textField = new JTextField();
		textField.setBounds(220, 156, 239, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(216, 212, 243, 34);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 261, 239, 34);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 307, 239, 34);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(539, 83, 491, 522);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textField.setText(model.getValueAt(selectedRowIndex, 0).toString());
				textField_1.setText(model.getValueAt(selectedRowIndex, 1).toString());
				textField_2.setText(model.getValueAt(selectedRowIndex, 4).toString());
				textField_3.setText(model.getValueAt(selectedRowIndex, 3).toString());
				
				Name = model.getValueAt(selectedRowIndex, 0).toString();
			}
		});
		disDoUong();
		scrollPane.setViewportView(table);
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}

}
