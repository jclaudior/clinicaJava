package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.model.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.TextArea;
import java.text.ParseException;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPaciente extends JFrame {

	private JPanel contentPane;
	private JLabel lblCpf;
	private JLabel lblNome;
	private JFormattedTextField txtCPF;
	private JTextField txtNome;
	private JLabel lblCadastroDePacientes;
	private JLabel lblSexo;
	private JComboBox cbmSexo;
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
	private JFormattedTextField txtCelular;
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
	private JButton btnLimpar;
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private MaskFormatter ftmCel;// Atributo formatador para Celular
	private Paciente paciente;
	private PacienteDAO pacientedao;
	private JTextField txtNumero;
	private JLabel lblNumero;

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
	 * @throws ParseException 
	 */
	public TelaPaciente(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCpf = new JLabel("            CPF:");
		lblCpf.setBounds(10, 27, 70, 14);
		contentPane.add(lblCpf);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(228, 27, 46, 14);
		contentPane.add(lblNome);
		
		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro na mascara CPF\n");
		}
		txtCPF = new JFormattedTextField(ftmCpf);
		txtCPF.setBounds(83, 24, 125, 20);
		contentPane.add(txtCPF);
		
		txtNome = new JTextField();
		txtNome.setBounds(274, 24, 252, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblCadastroDePacientes = new JLabel("Cadastro de Pacientes");
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDePacientes.setBounds(10, 2, 200, 20);
		contentPane.add(lblCadastroDePacientes);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(543, 27, 39, 14);
		contentPane.add(lblSexo);
		
		cbmSexo = new JComboBox();
		cbmSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		cbmSexo.setBounds(585, 24, 46, 20);
		contentPane.add(cbmSexo);
		
		lblRua = new JLabel("            Rua:");
		lblRua.setBounds(10, 52, 70, 14);
		contentPane.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setBounds(83, 49, 239, 20);
		contentPane.add(txtRua);
		txtRua.setColumns(10);
		
		lblCidade = new JLabel("       Cidade:");
		lblCidade.setBounds(10, 77, 70, 14);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(83, 74, 200, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblComplemento = new JLabel("Comple:");
		lblComplemento.setBounds(489,52, 53, 14);
		contentPane.add(lblComplemento);
		
		txtComple = new JTextField();
		txtComple.setBounds(543, 49,86, 20);
		contentPane.add(txtComple);
		txtComple.setColumns(10);
		
		lblUf = new JLabel("     UF:");
		lblUf.setBounds(283, 77, 39, 14);
		contentPane.add(lblUf);
		
		cbmUF = new JComboBox();
		cbmUF.setModel(new DefaultComboBoxModel(new String[] {"UF"}));
		cbmUF.setBounds(327, 74, 46, 20);
		contentPane.add(cbmUF);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(391, 77, 46, 14);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(440, 74, 191, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		
		lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(10, 102, 70, 14);
		contentPane.add(lblDataNasc);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro na mascara Data Nascimento\n");
		}
		txtDta = new JFormattedTextField(ftmData);
		txtDta.setBounds(82, 99, 82, 20);
		contentPane.add(txtDta);
		
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(385, 102, 52, 14);
		contentPane.add(lblCelular);
		
		try {
			ftmCel = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro na mascara Celular\n");
		}
		txtCelular = new JFormattedTextField(ftmCel);
		txtCelular.setBounds(440, 99, 119, 20);
		contentPane.add(txtCelular);
		
		lblEmail = new JLabel("        E-mail:");
		lblEmail.setBounds(10, 127, 70, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(82, 124, 296, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblSenha = new JLabel(" Senha:");
		lblSenha.setBounds(385, 127, 45, 14);
		contentPane.add(lblSenha);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(440, 124, 191, 20);
		contentPane.add(txtPass);
		
		btnCadastrar = new JButton();
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//########################################
				try {
				if(txtCPF.getText().equals("   .   .   -  "))	{
					JOptionPane.showMessageDialog(null,"Preencher CPF Válido!!!");
					return;
				}
				if(txtNome.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher NOME Válido!!!");
					return;
				}
				if(txtRua.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher Nome da Rua Válido!!!");
					return;
				}
				if(cbmUF.getSelectedIndex()==0)	{
					JOptionPane.showMessageDialog(null,"Preencher UF Válido!!!");
					return;
				}
				if(txtCidade.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher CIDADE Válida!!");
					return;
				}
				if(txtBairro.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher BAIRRO Válido!!!");
					return;
				}
				if(txtDta.getText().equals("  /  /    "))	{
					JOptionPane.showMessageDialog(null,"Preencher Data de Nascimento Válida!!!");
					return;
				}
				if(txtCelular.getText().equals("(  )     -    "))	{
					JOptionPane.showMessageDialog(null,"Preencher CELULAR Válido!!!");
					return;
				}
				if(txtEmail.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher E-MAIL Válido!!!");
					return;
				}
				if(txtPass.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher SENHA Válida!!!");
					return;
				}
				if(txtNumero.getText().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencher NÚMERO Válido!!!");
					return;
				}
				paciente = new Paciente ();
				paciente.setCpf(txtCPF.getText());
				paciente.setNome(txtNome.getText());
				paciente.setRua(txtRua.getText());
				paciente.setComplemento(txtComple.getText());
				paciente.setUf(String.valueOf(cbmUF.getSelectedItem()));
				paciente.setCidade(txtCidade.getText());
				paciente.setBairro(txtBairro.getText());
				paciente.setDataNasc(txtDta.getText());
				paciente.setCelular(txtCelular.getText());
				paciente.setEmail(txtEmail.getText());
				paciente.setSenha(txtPass.getText());
				paciente.setNumero(txtNumero.getText());
				paciente.setSexo(String.valueOf(cbmSexo.getSelectedItem()));
				
				pacientedao= new PacienteDAO ();
				pacientedao.salvar(paciente);
				JOptionPane.showMessageDialog(null,"Gravado com Sucesso!!!");
				}
				catch ( Exception e ) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar paciente!!\n" + e.getMessage());
					
				
				}
				//########################################
				
			}
			
		});
		btnCadastrar.setToolTipText("Cadastrar");
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(30, 351, 89, 87);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtCPF.getText().equals("   .   .   -  "))	{
						JOptionPane.showMessageDialog(null,"Preencher CPF Válido!!!");
						return;
					}
					if(txtNome.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher NOME Válido!!!");
						return;
					}
					if(txtRua.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher Nome da Rua Válido!!!");
						return;
					}
					if(cbmUF.getSelectedIndex()==0)	{
						JOptionPane.showMessageDialog(null,"Preencher UF Válido!!!");
						return;
					}
					if(txtCidade.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher CIDADE Válida!!");
						return;
					}
					if(txtBairro.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher BAIRRO Válido!!!");
						return;
					}
					if(txtDta.getText().equals("  /  /    "))	{
						JOptionPane.showMessageDialog(null,"Preencher Data de Nascimento Válida!!!");
						return;
					}
					if(txtCelular.getText().equals("(  )     -    "))	{
						JOptionPane.showMessageDialog(null,"Preencher CELULAR Válido!!!");
						return;
					}
					if(txtEmail.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher E-MAIL Válido!!!");
						return;
					}
					if(txtPass.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher SENHA Válida!!!");
						return;
					}
					if(txtNumero.getText().equals(""))	{
						JOptionPane.showMessageDialog(null,"Preencher NÚMERO Válido!!!");
						return;
					}
					paciente = new Paciente ();
					paciente.setCpf(txtCPF.getText());
					paciente.setNome(txtNome.getText());
					paciente.setRua(txtRua.getText());
					paciente.setComplemento(txtComple.getText());
					paciente.setUf(String.valueOf(cbmUF.getSelectedItem()));
					paciente.setCidade(txtCidade.getText());
					paciente.setBairro(txtBairro.getText());
					paciente.setDataNasc(txtDta.getText());
					paciente.setCelular(txtCelular.getText());
					paciente.setEmail(txtEmail.getText());
					paciente.setSenha(txtPass.getText());
					paciente.setNumero(txtNumero.getText());
					paciente.setSexo(String.valueOf(cbmSexo.getSelectedItem()));
					
					pacientedao= new PacienteDAO ();
					pacientedao.alterar(paciente);
					JOptionPane.showMessageDialog(null,"Alterado com Sucesso!!!");
					}
					catch ( Exception e1 ) {
						JOptionPane.showMessageDialog(null, "Erro ao alterar paciente!!\n" + e1.getMessage());
						
					
					}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(129, 351, 89, 87);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton();
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(228, 351, 89, 87);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(327, 351, 89, 87);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton();
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(426, 351, 89, 87);
		contentPane.add(btnListar);
		
		txtMostar = new TextArea();
		txtMostar.setBounds(10, 153, 621, 192);
		contentPane.add(txtMostar);
		
		btnLimpar = new JButton();
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(525, 351, 89, 87);
		contentPane.add(btnLimpar);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(393, 49, 86, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(335, 52, 63, 14);
		contentPane.add(lblNumero);
		
		
	}
}
