package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.omg.CORBA.PUBLIC_MEMBER;

import net.proteanit.sql.DbUtils;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThemTaiKhoan extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	public JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemTaiKhoan frame = new ThemTaiKhoan();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String Name;
	public void distable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str =  "SELECT * FROM taikhoan";
			PreparedStatement pst =  con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	
	
	/**
	 * Create the frame.
	 */
	public ThemTaiKhoan() {};
	public ThemTaiKhoan(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 959, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textField.setText(model.getValueAt(selectedRowIndex, 0).toString());
				textField_1.setText(model.getValueAt(selectedRowIndex, 1).toString());
				passwordField.setText(model.getValueAt(selectedRowIndex, 2).toString());
				
				
				Name = model.getValueAt(selectedRowIndex, 1).toString();
				
			}
		});
		scrollPane.setBounds(558, 71, 374, 417);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		distable();
		
		
		JLabel lblNewLabel = new JLabel("Quản Lý Tài Khoản");
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/login.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 922, 49);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Thêm mới");
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] mk1  = passwordField.getPassword() ;
				char[] mk2  = passwordField_1.getPassword();
					String matkhau1  = new String(mk1);
					String matkhau2  = new String(mk2);
				if (matkhau1.equals(matkhau2)) 
				{
							try {
									Class.forName("com.mysql.jdbc.Driver");
									Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
									Statement stmt = con.createStatement();
									PreparedStatement ps = con.prepareStatement("insert into taikhoan values(?,?,?)");
									
									ps.setString(1, textField.getText());
									ps.setString(2, textField_1.getText());
									ps.setString(3, passwordField.getText().toString());
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null,"Thêm mới thành công !");
									distable();
								con.close();
								}
							catch (Exception e1) 
							{
								System.out.print(e1);
							}
				}	
				else
					{
						JOptionPane.showMessageDialog(null,"Kiểm tra nhập lại mật khẩu");
						passwordField_1.setText("");			
					}
			}
		});
		btnNewButton.setBounds(20, 373, 146, 97);
		contentPane.add(btnNewButton);
		
		JButton btnXaTiKhon = new JButton("Xóa tài khoản ");
		btnXaTiKhon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnXaTiKhon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				    String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";

				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);

				    String sql = "DELETE FROM taikhoan WHERE UserName= '"+Name+"'";

				    
				    PreparedStatement statement = connection.prepareStatement(sql);
				    statement.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Xóa thành công!");	    
				    connection.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				distable();
			}
		});
		btnXaTiKhon.setBounds(181, 373, 176, 97);
		contentPane.add(btnXaTiKhon);
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);
				    
				    String sql = "update taikhoan set HienThi = ?, UserName = ?, PassWord = ? where UserName = ?";
				    PreparedStatement ps = connection.prepareStatement(sql);
				    
				    ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, passwordField.getText().toString());
					ps.setString(4, Name );
					
					ps.executeUpdate();
					distable();
				    
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnCpNht.setBounds(374, 372, 146, 99);
		contentPane.add(btnCpNht);
		
		JButton btnQuau = new JButton("Quay Lại");
		btnQuau.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnQuau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
				quanLyChung.setVisible(true);
				dispose();
			}
		});
		btnQuau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnQuau.setBounds(20, 51, 160, 41);
		contentPane.add(btnQuau);
		
		JLabel lblNewLabel_1 = new JLabel("Tên hiển thị ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(62, 127, 89, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMtKhu.setBounds(62, 212, 89, 31);
		contentPane.add(lblMtKhu);
		
		JLabel lblTiKhon = new JLabel("Tài khoản ");
		lblTiKhon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTiKhon.setBounds(62, 169, 89, 31);
		contentPane.add(lblTiKhon);
		
		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu");
		lblXcNhnMt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblXcNhnMt.setBounds(62, 258, 118, 31);
		contentPane.add(lblXcNhnMt);
		
		textField = new JTextField();
		textField.setBounds(199, 127, 173, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(199, 171, 173, 31);
		contentPane.add(textField_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 214, 173, 31);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(199, 260, 173, 31);
		contentPane.add(passwordField_1);
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
