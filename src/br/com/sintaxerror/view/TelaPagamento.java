package br.com.sintaxerror.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.sintaxerror.dao.ConsultaDAO;
import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.dao.PagamentoDAO;
import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.model.Pagamento;

public class TelaPagamento extends JFrame {

	private JPanel contentPane;
	private JLabel lblPagamentos;
	private JLabel lblCodigo;
	private JTextField txtCod;
	private JLabel lblCodPaciente;
	private JLabel lblNome;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblData;
	private JFormattedTextField txtDta;
	private JLabel lblFormaPag;
	private JComboBox cbmFormPag;
	private TextArea textArea;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;
	private SimpleDateFormat formatDate;
	private SimpleDateFormat formatDateSql;
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private JFormattedTextField txtCPF;
	private JButton btnLimpar;
	private Pagamento pagamento;// Inports Dao
	private PagamentoDAO pagamentoDAO;// Inports Dao
	private JScrollPane scrollPane;
	private JTable table;
	
	
	// Abrindo aplicação
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPagamento frame = new TelaPagamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Criando frame
	
	public TelaPagamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPagamentos = new JLabel("Pagamentos");
		lblPagamentos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPagamentos.setBounds(10, 11, 121, 26);
		contentPane.add(lblPagamentos);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 48, 46, 14);
		contentPane.add(lblCodigo);
		
		lblCodPaciente = new JLabel("CPF Paciente:");
		lblCodPaciente.setBounds(153, 48, 86, 14);
		contentPane.add(lblCodPaciente);
		
		lblNome = new JLabel("");
		lblNome.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblNome.setBounds(10, 73, 327, 26);
		contentPane.add(lblNome);
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(193, 110, 46, 14);
		contentPane.add(lblValor);
		
		lblData = new JLabel("Data:");
		lblData.setBounds(10, 113, 36, 14);
		contentPane.add(lblData);
		
		lblFormaPag = new JLabel("Forma Pag:");
		lblFormaPag.setBounds(10, 152, 73, 14);
		contentPane.add(lblFormaPag);
		
		txtCod = new JTextField();
		txtCod.setBounds(57, 45, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(239, 107, 98, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Erro na mascara Data Nascimento\n");
		}
		txtDta = new JFormattedTextField(ftmData);
		txtDta.setBounds(44, 110, 106, 20);
		contentPane.add(txtDta);
		
		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro na mascara CPF\n");
		}
		txtCPF = new JFormattedTextField(ftmCpf);
		txtCPF.setBounds(239, 48, 98, 17);
		contentPane.add(txtCPF);
		
		cbmFormPag = new JComboBox();
		cbmFormPag.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Opçao", "Dinheiro", "Cartao"}));
		cbmFormPag.setBounds(78, 148, 259, 22);
		contentPane.add(cbmFormPag);
		
		// PANEL
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFocusable(false);
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setBounds(350, 11, 380, 160);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFocusTraversalKeysEnabled(false);
		table.setFocusable(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "CPF", "Nome", "Data", "Valor", "Forma de Pagamento"
			}
		));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		/*textArea = new TextArea();
		textArea.setFocusable(false);
		textArea.setFocusTraversalKeysEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(353, 11, 383, 160);
		contentPane.add(textArea);*/
		
		//BTN CADASTRAR
		btnCadastrar = new JButton();
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					pagamento = new Pagamento();
					
					Paciente paciente = new Paciente();
					PacienteDAO pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());
					
					pagamento.setCodPagamento(Integer.parseInt(txtCod.getText()));
					
					Date dateNow = new Date(System.currentTimeMillis());
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtDta.getText());
					
					pagamento.setDia(formatDateSql.format(date));
					pagamento.setValor(Double.parseDouble(txtValor.getText()));
					pagamento.setFormaPagamento((String)cbmFormPag.getSelectedItem());
					
					pagamento.setPaciente(paciente);
					pagamentoDAO = new PagamentoDAO();
					pagamentoDAO.salvar(pagamento);
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!\n" + e.getMessage());
				}
			}
		});
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(78, 181, 91, 81);
		contentPane.add(btnCadastrar);
		
		
		//BTN ALTERAR
		btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Paciente paciente = new Paciente();
					PacienteDAO pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());
					
					JOptionPane.showMessageDialog(null, "Debug 1");
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtDta.getText());
					
					JOptionPane.showMessageDialog(null, "Debug 2");
					pagamento = new Pagamento();
					pagamento.setCodPagamento(Integer.parseInt(txtCod.getText()));
					JOptionPane.showMessageDialog(null, "Debug 3");
					
					pagamento.setDia(formatDateSql.format(date));
					pagamento.setValor(Double.parseDouble(txtValor.getText()));
					pagamento.setFormaPagamento((String)cbmFormPag.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Debug 4");
					
					pagamento.setPaciente(paciente);
					pagamentoDAO.alterar(pagamento);
					
					JOptionPane.showMessageDialog(null, "Debug 5");
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar!\n" + e.getMessage());
				}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(179, 181, 91, 81);
		contentPane.add(btnAlterar);
		
		//BTN CONSULTAR
		btnConsultar = new JButton();
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					pagamento = new Pagamento();
					pagamentoDAO = new PagamentoDAO();
					pagamento = pagamentoDAO.consultar(Integer.parseInt(txtCod.getText()));
					
					lblNome.setText(pagamento.getPaciente().getNome());
					txtCPF.setText(pagamento.getPaciente().getCpf());
					
					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDateSql.parse(pagamento.getDia());
					txtDta.setText(formatDate.format(date));
					
					txtValor.setText(String.valueOf(pagamento.getValor()));
					cbmFormPag.setSelectedItem(pagamento.getFormaPagamento());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Consultar!\n" + e.getMessage());
				}
			}
		});
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(280, 181, 91, 81);
		contentPane.add(btnConsultar);
		
		//BTN EXCLUIR
		btnExcluir = new JButton();
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if (txtCod.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preenche codigo da Consulta para Exclusão!");
						return;
					}
					pagamentoDAO = new PagamentoDAO();
					pagamentoDAO.excluir(Integer.parseInt(txtCod.getText()));
					
					JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir!\n" + e.getMessage());
				}
			}
		});
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(381, 181, 91, 81);
		contentPane.add(btnExcluir);
		
		//BTN LISTAR
		btnListar = new JButton();
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					List<Pagamento> lista = new ArrayList<Pagamento>();
					pagamentoDAO = new PagamentoDAO();
					lista = pagamentoDAO.listarTodos();
					if (lista != null) {
						DefaultTableModel model = (DefaultTableModel) table.getModel(); 
						for (Pagamento pagamento : lista) {
							formatDate = new SimpleDateFormat("dd/MM/yyyy");
							formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
							Date date = formatDateSql.parse(pagamento.getDia());
							
							model.addRow(new Object[] {
									
									pagamento.getCodPagamento(),
									pagamento.getPaciente().getCpf(),
									pagamento.getPaciente().getNome(),
									formatDate.format(date),
									pagamento.getValor(),
									pagamento.getFormaPagamento()
									
							});
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Nenum Pagamento Encontrado!");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar!\n" + e.getMessage());
				}
			}
		});
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(483, 181, 91, 81);
		contentPane.add(btnListar);
		
		//BTN LIMPAR
		btnLimpar = new JButton();
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCod.setText(null);
				txtCPF.setText(null);
				txtDta.setText(null);
				txtValor.setText(null);
				lblNome.setText(null);
				cbmFormPag.setSelectedIndex(0);
				DefaultTableModel model = (DefaultTableModel)table.getModel(); model.setNumRows(0);
				txtCod.requestFocus();
			}
		});
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(584, 181, 91, 81);
		contentPane.add(btnLimpar);
	}
}
