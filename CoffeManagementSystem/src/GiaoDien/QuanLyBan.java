package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyBan extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyBan frame = new QuanLyBan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	String Name;
	int MaBan;
	/**
	 * Create the frame.
	 */
	public QuanLyBan() {};
	public QuanLyBan(String tenHienThi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQunLBn = new JLabel("Quản lý bàn");
		lblQunLBn.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblQunLBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLBn.setBounds(10, 11, 845, 52);
		contentPane.add(lblQunLBn);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuanLyChung quanLyChung = new QuanLyChung(tenHienThi);
				quanLyChung.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(60, 62, 160, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Số bàn ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(107, 161, 77, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ghi chú");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(107, 222, 77, 27);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(216, 147, 245, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(216, 208, 245, 41);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("Thêm bàn");
		btnNewButton_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = con.createStatement();
					
					String sql =  "insert into ban (TenBan, GhiChu) " + " values (?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.executeUpdate();
					disBan();
					
					textField.setText("");
					textField_1.setText("");
					con.close();
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 345, 160, 99);
		contentPane.add(btnNewButton_1);
		
		JButton btnXaBn = new JButton("Xóa bàn");
		btnXaBn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnXaBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String url = "jdbc:mysql://localhost/test";
				    String username = "root";
				    String password = "";

				    Class.forName("com.mysql.jdbc.Driver");
				    Connection connection = DriverManager.getConnection(url, username, password);

				    String sql = "DELETE FROM ban WHERE TenBan= '"+Name+"'";
				    PreparedStatement statement = connection.prepareStatement(sql);
				    statement.executeUpdate();
				    
				    String sql1 = "DELETE FROM hoadon WHERE MaBan= '"+MaBan+"'";
				    PreparedStatement statement1 = connection.prepareStatement(sql1);
				    statement1.executeUpdate();
				    
				    JOptionPane.showMessageDialog(null, "Xóa thành công!");	  
				    textField.setText("");
				    textField_1.setText("");
				    connection.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}		
			disBan();	
			}
		});
		btnXaBn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXaBn.setBounds(192, 345, 145, 99);
		contentPane.add(btnXaBn);
		
		JButton btnSaBn = new JButton("Đặt bàn");
		btnSaBn.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnSaBn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSaBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = con.createStatement();
					
					String sql =  "update ban set GhiChu = ?, TrangThai = ?  where TenBan = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, textField_1.getText());
					ps.setString(2, "Da dat");
					ps.setString(3, textField.getText());
					ps.execute();
// Sau khi chuyển trạng thái Bàn sang " đã đặt " thì tiến hành phát sinh " hóa đơn "  cho bàn ấy với trạng thái " chưa thanh toán "  					
					String sql2 =  "insert into hoadon (MaBan,TrangThai) " + " values (?, ?)";
					PreparedStatement ps2 = con.prepareStatement(sql2);
					ps2.setInt(1, MaBan);
					ps2.setString(2, "Chua Thanh Toan");
					ps2.executeUpdate();
					disBan();
					textField.setText("");
					textField_1.setText("");
					con.close();
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		btnSaBn.setBounds(360, 345, 160, 99);
		contentPane.add(btnSaBn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(546, 105, 309, 335);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textField.setText(model.getValueAt(selectedRowIndex, 1).toString());
				textField_1.setText(model.getValueAt(selectedRowIndex, 3).toString());
				
				Name = model.getValueAt(selectedRowIndex, 1).toString();
				MaBan =  Integer.parseInt(model.getValueAt(selectedRowIndex,0).toString());
			}
		});
		disBan();
		scrollPane.setViewportView(table);
		
		Toolkit toolkit = getToolkit();
		Dimension size =  toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}
