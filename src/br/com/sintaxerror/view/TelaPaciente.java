package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.TextArea;

public class TelaPaciente extends JFrame {

	private JPanel contentPane;
	private JLabel lblCpf;
	private JLabel lblNome;
	private JFormattedTextField txtCPF;
	private JTextField txtNome;
	private JLabel lblCadastroDePacientes;
	private JLabel lblSexo;
	private JComboBox comboBox;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblComplemento;
	private JTextField txtComple;
	private JLabel lblUf;
	private JComboBox cbmUF;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblDataNasc;
	private JFormattedTextField txtDta;
	private JLabel lblCelular;
	private JFormattedTextField formattedTextField;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblSenha;
	private JPasswordField txtPass;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;
	private TextArea txtMostar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPaciente frame = new TelaPaciente();
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
	public TelaPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 27, 33, 14);
		contentPane.add(lblCpf);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(148, 27, 46, 14);
		contentPane.add(lblNome);
		
		txtCPF = new JFormattedTextField();
		txtCPF.setBounds(39, 24, 105, 20);
		contentPane.add(txtCPF);
		
		txtNome = new JTextField();
		txtNome.setBounds(191, 24, 252, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblCadastroDePacientes = new JLabel("Cadastro de Pacientes");
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDePacientes.setBounds(10, 2, 200, 20);
		contentPane.add(lblCadastroDePacientes);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(453, 27, 39, 14);
		contentPane.add(lblSexo);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(491, 24, 39, 20);
		contentPane.add(comboBox);
		
		lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 52, 33, 14);
		contentPane.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setBounds(39, 49, 303, 20);
		contentPane.add(txtRua);
		txtRua.setColumns(10);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 77, 46, 14);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(58, 74, 151, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(361, 52, 82, 14);
		contentPane.add(lblComplemento);
		
		txtComple = new JTextField();
		txtComple.setBounds(444, 49, 86, 20);
		contentPane.add(txtComple);
		txtComple.setColumns(10);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(219, 77, 26, 14);
		contentPane.add(lblUf);
		
		cbmUF = new JComboBox();
		cbmUF.setModel(new DefaultComboBoxModel(new String[] {"UF"}));
		cbmUF.setBounds(241, 74, 46, 20);
		contentPane.add(cbmUF);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(297, 77, 39, 14);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(339, 74, 191, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(10, 102, 70, 14);
		contentPane.add(lblDataNasc);
		
		txtDta = new JFormattedTextField();
		txtDta.setBounds(82, 99, 82, 20);
		contentPane.add(txtDta);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(184, 102, 46, 14);
		contentPane.add(lblCelular);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(229, 99, 119, 20);
		contentPane.add(formattedTextField);
		
		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 127, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(52, 124, 296, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(359, 127, 46, 14);
		contentPane.add(lblSenha);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(400, 124, 130, 20);
		contentPane.add(txtPass);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(30, 351, 89, 87);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(129, 351, 89, 87);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(228, 351, 89, 87);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(327, 351, 89, 87);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(426, 351, 89, 87);
		contentPane.add(btnListar);
		
		txtMostar = new TextArea();
		txtMostar.setBounds(10, 153, 520, 192);
		contentPane.add(txtMostar);
		
		
	}
}
