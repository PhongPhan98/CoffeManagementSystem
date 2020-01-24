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
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class QuanLyNhanVien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVien frame = new QuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	public void disNhanVien() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String str =  "SELECT *  FROM nhanvien";
			PreparedStatement pst =  con.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	int sttNhanVien;
	/**
	 * Create the frame.
	 */
	public QuanLyNhanVien() {};
	public QuanLyNhanVien(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lý Nhân Viên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 28, 825, 46);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Quay Lại");
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
				quanLyChung.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		btnNewButton.setBounds(10, 85, 173, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Số thứ tự");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 173, 130, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTnNhnVin.setBounds(10, 219, 130, 44);
		contentPane.add(lblTnNhnVin);
		
		JLabel lblQuQun = new JLabel("Quê quán");
		lblQuQun.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuQun.setBounds(10, 273, 130, 46);
		contentPane.add(lblQuQun);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSinThoi.setBounds(10, 337, 130, 35);
		contentPane.add(lblSinThoi);
		
		JLabel lblLng = new JLabel("Lương ");
		lblLng.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLng.setBounds(10, 395, 130, 35);
		contentPane.add(lblLng);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 85, 428, 470);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textField.setText(model.getValueAt(selectedRowIndex,0 ).toString());
				textField_1.setText(model.getValueAt(selectedRowIndex, 1).toString());
				textField_2.setText(model.getValueAt(selectedRowIndex, 2).toString());
				textField_3.setText(model.getValueAt(selectedRowIndex, 3).toString());
				textField_4.setText(model.getValueAt(selectedRowIndex, 4).toString());
				
				sttNhanVien = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
			}
		});
		scrollPane.setViewportView(table);
		disNhanVien();
		textField = new JTextField();
		textField.setBounds(150, 173, 195, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 226, 195, 35);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 284, 195, 35);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 339, 195, 35);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(150, 397, 195, 35);
		contentPane.add(textField_4);
		
		JButton btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = con.createStatement();
					
					String sql =  "insert into nhanvien values (?, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4, textField_3.getText());
					ps.setString(5, textField_4.getText());
					ps.executeUpdate();
					disNhanVien();
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");					
					con.close();
				} catch (Exception e2) {
					System.out.print(e2);
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 472, 133, 83);
		contentPane.add(btnNewButton_1);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	btnXa.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String url = "jdbc:mysql://localhost/test";
			    String username = "root";
			    String password = "";

			    Class.forName("com.mysql.jdbc.Driver");
			    Connection connection = DriverManager.getConnection(url, username, password);
			    
			    String sql = "DELETE FROM nhanvien WHERE Stt= '"+sttNhanVien+"'";
			    PreparedStatement statement = connection.prepareStatement(sql);
			    statement.executeUpdate();

			    JOptionPane.showMessageDialog(null, "Xóa thành công!");	
			    disNhanVien();
			    textField.setText("");
			    textField_1.setText("");
			    textField_2.setText("");
			    textField_3.setText("");
			    textField_4.setText("");
			    connection.close();
				
			} catch (Exception e2) {
				System.out.print(e2);
			}
			disNhanVien();
		}
	});
		btnXa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXa.setBounds(150, 472, 118, 83);
		contentPane.add(btnXa);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		btnSa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);
				    
				    String sql = "update nhanvien set Stt = ?, TenNhanVien = ?, QueQuan = ?, SoDienThoai = ?, Luong = ? where Stt = ?";
				    PreparedStatement ps = connection.prepareStatement(sql);
				    
				    ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4,textField_3.getText());
					ps.setString(5,textField_4.getText());
					ps.setInt(6, sttNhanVien );
					
					ps.executeUpdate();
					disNhanVien();;
					
				} catch (Exception e3) {
					System.out.print(e3);
				}
				
			}
		});
		btnSa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSa.setBounds(278, 472, 119, 83);
		contentPane.add(btnSa);
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
