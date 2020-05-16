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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
	private JButton btnLimpar;
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private MaskFormatter ftmCel;// Atributo formatador para Celular
	private Paciente paciente;
	private PacienteDAO pacientedao;
	private JTextField txtNumero;
	private JLabel lblNumero;
	private SimpleDateFormat formatDate;
	private SimpleDateFormat formatDateSql;
	private JScrollPane scrollPane;
	private JTable table;

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
	 * 
	 * @throws ParseException
	 */
	public TelaPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 493);
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
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					pacientedao = new PacienteDAO();
					if(pacientedao.consultar(txtCPF.getText()) != null) {
						JOptionPane.showMessageDialog(null, "Paciente com este CPF ja Cadastrado!");
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao consultar se o Paciente \nja esta cadastrado!");
				}
			}
		});
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtCPF.getText().equals("   .   .   -  ")) {
					txtNome.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCPF.setBounds(83, 24, 125, 20);
		contentPane.add(txtCPF);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtNome.getText().equals("")) {
					cbmSexo.requestFocus();
				}
			}
		});
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
		cbmSexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER) {
					txtRua.requestFocus();
				}
			}
		});
		cbmSexo.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		cbmSexo.setBounds(585, 24, 46, 20);
		contentPane.add(cbmSexo);

		lblRua = new JLabel("            Rua:");
		lblRua.setBounds(10, 52, 70, 14);
		contentPane.add(lblRua);

		txtRua = new JTextField();
		txtRua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtRua.getText().equals("")) {
					txtNumero.requestFocus();
				}
			}
		});
		txtRua.setBounds(83, 49, 239, 20);
		contentPane.add(txtRua);
		txtRua.setColumns(10);

		lblCidade = new JLabel("       Cidade:");
		lblCidade.setBounds(10, 77, 70, 14);
		contentPane.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtCidade.getText().equals("")) {
					cbmUF.requestFocus();
				}
			}
		});
		txtCidade.setBounds(83, 74, 200, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		lblComplemento = new JLabel("Comple:");
		lblComplemento.setBounds(489, 52, 53, 14);
		contentPane.add(lblComplemento);

		txtComple = new JTextField();
		txtComple.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER) {
					txtCidade.requestFocus();
				}
			}
		});
		txtComple.setBounds(543, 49, 86, 20);
		contentPane.add(txtComple);
		txtComple.setColumns(10);

		lblUf = new JLabel("     UF:");
		lblUf.setBounds(283, 77, 39, 14);
		contentPane.add(lblUf);

		cbmUF = new JComboBox();
		cbmUF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && cbmUF.getSelectedIndex() != 0) {
					txtBairro.requestFocus();
				}
			}
		});
		cbmUF.setModel(new DefaultComboBoxModel(new String[] { "UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
				"MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbmUF.setBounds(327, 74, 46, 20);
		contentPane.add(cbmUF);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(391, 77, 46, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtBairro.getText().equals("")) {
					txtDta.requestFocus();
				}
			}
		});
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
		txtDta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtDta.getText().equals("  /  /    ")) {
					txtCelular.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
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
		txtCelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtCelular.getText().equals("(  )     -    ")) {
					txtEmail.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCelular.setBounds(440, 99, 119, 20);
		contentPane.add(txtCelular);

		lblEmail = new JLabel("        E-mail:");
		lblEmail.setBounds(10, 127, 70, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtEmail.getText().equals("")) {
					txtPass.requestFocus();
				}
			}
		});
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
				// ########################################
				try {
					if (txtCPF.getText().equals("   .   .   -  ")) {
						JOptionPane.showMessageDialog(null, "Preencher CPF Válido!!!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher NOME Válido!!!");
						return;
					}
					if (txtRua.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Nome da Rua Válido!!!");
						return;
					}
					if (cbmUF.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Preencher UF Válido!!!");
						return;
					}
					if (txtCidade.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher CIDADE Válida!!");
						return;
					}
					if (txtBairro.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher BAIRRO Válido!!!");
						return;
					}
					if (txtDta.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Preencher Data de Nascimento Válida!!!");
						return;
					}
					if (txtCelular.getText().equals("(  )     -    ")) {
						JOptionPane.showMessageDialog(null, "Preencher CELULAR Válido!!!");
						return;
					}
					if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher E-MAIL Válido!!!");
						return;
					}
					if (txtPass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher SENHA Válida!!!");
						return;
					}
					if (txtNumero.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher NÚMERO Válido!!!");
						return;
					}
					
					pacientedao = new PacienteDAO();
					if(pacientedao.consultar(txtCPF.getText()) != null) {
						JOptionPane.showMessageDialog(null, "Paciente com este CPF ja Cadastrado!!!");
						return;
					}
					paciente = new Paciente();
					paciente.setCpf(txtCPF.getText());
					paciente.setNome(txtNome.getText());
					paciente.setRua(txtRua.getText());
					paciente.setComplemento(txtComple.getText());
					paciente.setUf(String.valueOf(cbmUF.getSelectedItem()));
					paciente.setCidade(txtCidade.getText());
					paciente.setBairro(txtBairro.getText());
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtDta.getText());
					paciente.setDataNasc(formatDateSql.format(date));
					paciente.setCelular(txtCelular.getText());
					paciente.setEmail(txtEmail.getText());
					paciente.setSenha(txtPass.getText());
					paciente.setNumero(txtNumero.getText());
					paciente.setSexo(String.valueOf(cbmSexo.getSelectedItem()));

					pacientedao = new PacienteDAO();
					pacientedao.salvar(paciente);
					JOptionPane.showMessageDialog(null, "Gravado com Sucesso!!!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar paciente!!\n" + e.getMessage());

				}
				// ########################################

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
					if (txtCPF.getText().equals("   .   .   -  ")) {
						JOptionPane.showMessageDialog(null, "Preencher CPF Válido!!!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher NOME Válido!!!");
						return;
					}
					if (txtRua.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Nome da Rua Válido!!!");
						return;
					}
					if (cbmUF.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Preencher UF Válido!!!");
						return;
					}
					if (txtCidade.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher CIDADE Válida!!");
						return;
					}
					if (txtBairro.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher BAIRRO Válido!!!");
						return;
					}
					if (txtDta.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Preencher Data de Nascimento Válida!!!");
						return;
					}
					if (txtCelular.getText().equals("(  )     -    ")) {
						JOptionPane.showMessageDialog(null, "Preencher CELULAR Válido!!!");
						return;
					}
					if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher E-MAIL Válido!!!");
						return;
					}
					if (txtPass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher SENHA Válida!!!");
						return;
					}
					if (txtNumero.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher NÚMERO Válido!!!");
						return;
					}
					paciente = new Paciente();
					paciente.setCpf(txtCPF.getText());
					paciente.setNome(txtNome.getText());
					paciente.setRua(txtRua.getText());
					paciente.setComplemento(txtComple.getText());
					paciente.setUf(String.valueOf(cbmUF.getSelectedItem()));
					paciente.setCidade(txtCidade.getText());
					paciente.setBairro(txtBairro.getText());
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtDta.getText());
					paciente.setDataNasc(formatDateSql.format(date));
					paciente.setCelular(txtCelular.getText());
					paciente.setEmail(txtEmail.getText());
					paciente.setSenha(txtPass.getText());
					paciente.setNumero(txtNumero.getText());
					paciente.setSexo(String.valueOf(cbmSexo.getSelectedItem()));

					pacientedao = new PacienteDAO();
					pacientedao.alterar(paciente);
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");
				} catch (Exception e1) {
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
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ============================================

				try {
					paciente = new Paciente();
					pacientedao = new PacienteDAO();
					paciente = pacientedao.consultar(txtCPF.getText());
					txtNome.setText(paciente.getNome());
					cbmSexo.setSelectedItem(paciente.getSexo());
					txtRua.setText(paciente.getRua());
					txtNumero.setText(paciente.getNumero());
					txtComple.setText(paciente.getComplemento());
					txtCidade.setText(paciente.getCidade());
					cbmUF.setSelectedItem(paciente.getUf());
					txtBairro.setText(paciente.getBairro());
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDateSql.parse(paciente.getDataNasc());
					txtDta.setText(formatDate.format(date));
					txtCelular.setText(paciente.getCelular());
					txtEmail.setText(paciente.getEmail());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar Consulta");
				}

				// ============================================
			}
		});
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(228, 351, 89, 87);
		contentPane.add(btnConsultar);

		btnExcluir = new JButton();
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ========================================

				try {
					pacientedao = new PacienteDAO();
					pacientedao.excluir(txtCPF.getText());
					JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!!!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao excluir paciente!!!\n" + e.getMessage());

				}

				// ========================================
			}
		});
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(327, 351, 89, 87);
		contentPane.add(btnExcluir);

		btnListar = new JButton();
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ===========================================
				try {
					List<Paciente> lista = new ArrayList<Paciente>();
					pacientedao = new PacienteDAO();
					lista = pacientedao.listarTodos();
					if (lista != null) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for (Paciente paciente : lista) {
							formatDate = new SimpleDateFormat("dd/MM/yyyy");
							formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
							Date date = formatDateSql.parse(paciente.getDataNasc());
							model.addRow(new Object[] { paciente.getCpf(), paciente.getNome(), paciente.getSexo(),
									paciente.getRua(), paciente.getNumero(), paciente.getComplemento(),
									paciente.getCidade(), paciente.getUf(), paciente.getBairro(),
									formatDate.format(date), paciente.getCelular(), paciente.getEmail() });
						}

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar na Tela!!!\n" + e.getMessage());

				}

				// ===========================================
			}
		});
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(426, 351, 89, 87);
		contentPane.add(btnListar);

		btnLimpar = new JButton();
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// =============================================
				String t = null;
				txtCPF.setText(t);
				txtNome.setText(t);
				cbmSexo.setSelectedIndex(0);
				txtRua.setText(t);
				txtNumero.setText(t);
				txtComple.setText(t);
				txtCidade.setText(t);
				cbmUF.setSelectedIndex(0);
				txtBairro.setText(t);
				txtDta.setText(t);
				txtCelular.setText(t);
				txtEmail.setText(t);
				txtPass.setText(t);

				// =============================================
			}
		});
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(525, 351, 89, 87);
		contentPane.add(btnLimpar);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c== KeyEvent.VK_ENTER && !txtNumero.getText().equals("")) {
					txtComple.requestFocus();
				}
			}
		});
		txtNumero.setBounds(393, 49, 86, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(335, 52, 63, 14);
		contentPane.add(lblNumero);

		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 152, 629, 187);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Sexo", "Rua", "Numero", "Complemento", "Cidade", "UF", "Bairro", "Data Nasc", "Celular", "E-mail"
			}
		));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

	}
}
