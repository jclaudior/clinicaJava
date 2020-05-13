package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sintaxerror.dao.ConsultaDAO;
import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JLabel lblConsulta;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblCpfPaciente;
	private JLabel lblPaciente;
	private JLabel lblCodDentista;
	private JTextField txtCodDent;
	private JLabel lblDentista;
	private JFormattedTextField txtCPF;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private JLabel lblHora;
	private JFormattedTextField txtHora;
	private JLabel lblObeservaes;
	private TextArea txtObs;
	private TextArea txtMostrar;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;
	private JButton btnLimpar;
	private Paciente paciente;
	private Dentista dentista;
	private Consulta consulta;
	private PacienteDAO pacienteDAO;
	private DentistaDAO dentistaDAO;
	private ConsultaDAO consultaDAO;
	private SimpleDateFormat formatDate;
	private SimpleDateFormat formatDateSql;
	private MaskFormatter ftmData;// Atributo formatador para data
	private MaskFormatter ftmCpf;// Atributo formatador para cpf
	private MaskFormatter ftmHora;// Atributo formatador para hora

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta frame = new TelaConsulta();
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
	public TelaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblConsulta = new JLabel("Consulta");
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsulta.setBounds(10, 0, 98, 20);
		contentPane.add(lblConsulta);

		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(44, 42, 46, 14);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					consultaCodigo();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCodigo.setText(null);
				txtData.setText(null);
				txtHora.setText(null);
				txtCPF.setText(null);
				txtCodDent.setText(null);
				lblPaciente.setText(null);
				lblDentista.setText(null);
				txtObs.setText(null);
			}
		});
		txtCodigo.setBounds(92, 39, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		lblCpfPaciente = new JLabel("CPF Paciente:");
		lblCpfPaciente.setBounds(10, 67, 80, 14);
		contentPane.add(lblCpfPaciente);

		lblPaciente = new JLabel("");
		lblPaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblPaciente.setBounds(222, 64, 272, 20);
		contentPane.add(lblPaciente);

		lblCodDentista = new JLabel("Cod Dentista:");
		lblCodDentista.setBounds(10, 92, 80, 14);
		contentPane.add(lblCodDentista);

		txtCodDent = new JTextField();
		txtCodDent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_ENTER && !txtCodDent.getText().equals("")) {
					txtObs.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCodDent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				mostrarDentista();
			}
		});
		txtCodDent.setBounds(92, 89, 120, 20);
		contentPane.add(txtCodDent);
		txtCodDent.setColumns(10);

		lblDentista = new JLabel("");
		lblDentista.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		lblDentista.setBounds(222, 89, 272, 20);
		contentPane.add(lblDentista);

		try {
			ftmCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara CPF");
		}
		txtCPF = new JFormattedTextField(ftmCpf);
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_ENTER && !txtCPF.getText().equals("   .   .   -  ")) {
					txtCodDent.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				mostrarPaciente();
			}
		});
		txtCPF.setBounds(92, 64, 120, 20);
		contentPane.add(txtCPF);

		lblData = new JLabel("Data:");
		lblData.setBounds(222, 42, 34, 14);
		contentPane.add(lblData);

		try {
			ftmData = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara DATA");
		}
		txtData = new JFormattedTextField(ftmData);
		txtData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				validaData();
			}
		});
		txtData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_ENTER && !txtData.getText().equals("  /  /    ")) {
					txtHora.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtData.setBounds(266, 39, 120, 20);
		contentPane.add(txtData);

		lblHora = new JLabel("Hora:");
		lblHora.setBounds(396, 42, 46, 14);
		contentPane.add(lblHora);

		try {
			ftmHora = new MaskFormatter("##:##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro mascara Hora");
		}
		txtHora = new JFormattedTextField(ftmHora);
		txtHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_ENTER && !txtHora.getText().equals("  :  ")) {
					txtCPF.requestFocus();
				}
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtHora.setBounds(438, 39, 55, 20);
		contentPane.add(txtHora);

		lblObeservaes = new JLabel("Obeserva\u00E7\u00F5es:");
		lblObeservaes.setBounds(10, 117, 108, 14);
		contentPane.add(lblObeservaes);

		txtObs = new TextArea();
		txtObs.setBounds(6, 137, 487, 67);
		contentPane.add(txtObs);

		txtMostrar = new TextArea();
		txtMostrar.setFocusable(false);
		txtMostrar.setFocusTraversalKeysEnabled(false);
		txtMostrar.setEditable(false);
		txtMostrar.setBounds(500, 33, 405, 171);
		contentPane.add(txtMostrar);

		btnCadastrar = new JButton();
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCPF.getText().equals("   .   .   -  ")) {
						JOptionPane.showMessageDialog(null, "Preencher CPF Paciente!");
						return;
					}
					if (txtCodigo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Codigo Consulta!");
						return;
					}
					if (txtData.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Preencher Data Consulta!");
						return;
					}
					if (txtData.getText().equals("00/00/0000")) {
						JOptionPane.showMessageDialog(null, "Data Invalida!");
						return;
					}
					if (txtData.getText().equals("  :  ")) {
						JOptionPane.showMessageDialog(null, "Preencher Hora Consulta!");
						return;
					}
					if (txtCodDent.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Codigo Dentista!");
						return;
					}

					Date dateNow = new Date(System.currentTimeMillis());

					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtData.getText());

					if (date.before(dateNow)) {
						JOptionPane.showMessageDialog(null, "Data deve ser maior que Atual!!");
						return;
					}
					paciente = new Paciente();
					pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());
					if(paciente == null) {
						JOptionPane.showMessageDialog(null, "Paciente Não Cadastrado!");
						return;
					}
					dentista = new Dentista();
					dentistaDAO = new DentistaDAO();
					dentista = dentistaDAO.consultar(Integer.parseInt(txtCodDent.getText()));
					if(dentista == null) {
						JOptionPane.showMessageDialog(null, "Dentista Não Cadastrado!");
						return;
					}
					consulta = new Consulta();
					consulta.setPaciente(paciente);
					consulta.setDentista(dentista);
					consulta.setCodConsulta(Integer.parseInt(txtCodigo.getText()));

					consulta.setDia(formatDateSql.format(date));
					consulta.setHorario(txtHora.getText());
					consulta.setObs(txtObs.getText());

					consultaDAO = new ConsultaDAO();
					if (consultaDAO.consultar(Integer.parseInt(txtCodigo.getText())) != null) {
						JOptionPane.showMessageDialog(null, "Codigo de Consulta Ja cadastrado!");
						return;
					}
					consultaDAO.salvar(consulta);
					JOptionPane.showMessageDialog(null, "Consulta gravada com sucesso!");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar consulta!\n" + e.getMessage());
				}

			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(201, 224, 83, 74);
		contentPane.add(btnCadastrar);

		btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCPF.getText().equals("   .   .   -  ")) {
						JOptionPane.showMessageDialog(null, "Preencher CPF Paciente!");
						return;
					}
					if (txtCodigo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Codigo Consulta!");
						return;
					}
					if (txtData.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Preencher Data Consulta!");
						return;
					}
					if (txtData.getText().equals("  :  ")) {
						JOptionPane.showMessageDialog(null, "Preencher Hora Consulta!");
						return;
					}
					if (txtCodDent.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencher Codigo Dentista!");
						return;
					}
					Date dateNow = new Date(System.currentTimeMillis());

					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDate.parse(txtData.getText());

					if (date.before(dateNow)) {
						JOptionPane.showMessageDialog(null, "Data deve ser maior que Atual!");
						return;
					}
					paciente = new Paciente();
					pacienteDAO = new PacienteDAO();
					paciente = pacienteDAO.consultar(txtCPF.getText());

					dentista = new Dentista();
					dentistaDAO = new DentistaDAO();
					dentista = dentistaDAO.consultar(Integer.parseInt(txtCodDent.getText()));

					consulta = new Consulta();
					consulta.setPaciente(paciente);
					consulta.setDentista(dentista);
					consulta.setCodConsulta(Integer.parseInt(txtCodigo.getText()));

					consulta.setDia(formatDateSql.format(date));
					consulta.setHorario(txtHora.getText());
					consulta.setObs(txtObs.getText());
					consultaDAO = new ConsultaDAO();
					consultaDAO.salvar(consulta);
					JOptionPane.showMessageDialog(null, "Consulta Alterada com sucesso!");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterada consulta!\n" + e.getMessage());
				}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(294, 224, 83, 74);
		contentPane.add(btnAlterar);

		btnConsultar = new JButton();
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCodigo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preenche codigo da Consulta para Pesquisa!");
						return;
					}
					consultaDAO = new ConsultaDAO();
					consulta = new Consulta();
					consulta = consultaDAO.consultar(Integer.parseInt(txtCodigo.getText()));

					formatDate = new SimpleDateFormat("dd/MM/yyyy");
					formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatDateSql.parse(consulta.getDia());
					txtData.setText(formatDate.format(date));
					txtHora.setText(consulta.getHorario());
					txtCPF.setText(consulta.getPaciente().getCpf());
					lblPaciente.setText(consulta.getPaciente().getNome());
					txtCodDent.setText(String.valueOf(consulta.getDentista().getCodDentista()));
					lblDentista.setText(consulta.getDentista().getNomeDentista());
					txtObs.setText(consulta.getObs());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao consultar consulta\n" + e.getMessage());
				}

			}
		});
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(384, 224, 83, 74);
		contentPane.add(btnConsultar);

		btnExcluir = new JButton();
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCodigo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preenche codigo da Consulta para Exclusão!");
						return;
					}
					consultaDAO = new ConsultaDAO();
					consultaDAO.excluir(Integer.parseInt(txtCodigo.getText()));
					JOptionPane.showMessageDialog(null, "Consulta Excluida com Sucesso!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir consulta!\n" + e.getMessage());
				}
			}
		});
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(474, 224, 83, 74);
		contentPane.add(btnExcluir);

		btnListar = new JButton();
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtMostrar.setText(null);
					List<Consulta> lista = new ArrayList<Consulta>();
					consultaDAO = new ConsultaDAO();
					lista = consultaDAO.listarTodos();
					if (lista != null) {
						for (Consulta consulta : lista) {
							formatDate = new SimpleDateFormat("dd/MM/yyyy");
							formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
							Date date = formatDateSql.parse(consulta.getDia());

							txtMostrar.append("Cod.: " + consulta.getCodConsulta() + " ");
							txtMostrar.append("Data: " + formatDate.format(date) + " ");
							txtMostrar.append("Hora: " + consulta.getHorario() + " ");
							txtMostrar.append("Paciente CPF: " + consulta.getPaciente().getCpf() + " ");
							txtMostrar.append("Paciente NOME: " + consulta.getPaciente().getNome() + " ");
							txtMostrar.append("Dentista Codigo: " + consulta.getDentista().getCodDentista() + " ");
							txtMostrar.append("Dentista Nome: " + consulta.getDentista().getNomeDentista() + " ");
							txtMostrar.append("Obeservações: " + consulta.getObs() + "\n");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Nenuma Consulta Encontrada!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar Consultas!\n" + e1.getMessage());
				}
			}
		});
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(564, 224, 83, 74);
		contentPane.add(btnListar);

		btnLimpar = new JButton();
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setText(null);
				txtData.setText(null);
				txtHora.setText(null);
				txtCPF.setText(null);
				txtCodDent.setText(null);
				lblPaciente.setText(null);
				lblDentista.setText(null);
				txtObs.setText(null);
				txtMostrar.setText(null);
				txtCodigo.requestFocus();
			}
		});
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(654, 224, 83, 74);
		contentPane.add(btnLimpar);
	}

	public void mostrarPaciente() {
		try {
			paciente = new Paciente();
			pacienteDAO = new PacienteDAO();
			paciente = pacienteDAO.consultar(txtCPF.getText());
			lblPaciente.setText(paciente.getNome());

		} catch (Exception e) {
			txtCPF.setText(null);
			lblPaciente.setText(null);
			JOptionPane.showMessageDialog(null, "Paciente não cadastrado!");
		}
	}

	public void mostrarDentista() {
		try {
			dentista = new Dentista();
			dentistaDAO = new DentistaDAO();
			dentista = dentistaDAO.consultar(Integer.parseInt(txtCodDent.getText()));
			lblDentista.setText(dentista.getNomeDentista());

		} catch (Exception e) {
			txtCodDent.setText(null);
			lblDentista.setText(null);
			JOptionPane.showMessageDialog(null, "Dentista não cadastrado!");
		}
	}
	public void consultaCodigo() {
		if (txtCodigo.getText().equals("")) {
			try {
				consultaDAO = new ConsultaDAO();
				int id = consultaDAO.lastId();
				if (id != 0) {
					id++;
					txtCodigo.setText(String.valueOf(id));
					txtData.requestFocus();
				} else {
					txtCodigo.setText("1");
					txtData.requestFocus();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar Ultimo ID!\n" + e1.getMessage());
			}
		} else {
			try {
				consultaDAO = new ConsultaDAO();
				consulta = new Consulta();
				consulta = consultaDAO.consultar(Integer.parseInt(txtCodigo.getText()));
				if (consulta != null) {
					JOptionPane.showMessageDialog(null, "Codigo de consulta ja Cadastrado!");
					txtCodigo.setText(null);
					txtCodigo.requestFocus();

				}else {
					txtData.requestFocus();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Erro ao Consultar Codigo de consulta ja Existente!\n" + e1.getMessage());
			}
		}
	}
	
	public void validaData() {
		try {
			/*if(txtData.getText().equals("  /  /    ") ) {
				JOptionPane.showMessageDialog(null, "Preencher Data!");
				txtData.requestFocus();
			}*/
			if (!txtData.getText().equals("  /  /    ")) {
				Date dateNow = new Date(System.currentTimeMillis());

				formatDate = new SimpleDateFormat("dd/MM/yyyy");
				formatDateSql = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatDate.parse(txtData.getText());

				if (date.before(dateNow)) {
					JOptionPane.showMessageDialog(null, "Data deve ser maior que Atual!");
					txtData.setText(null);
					txtData.requestFocus();
				}else {
					txtHora.requestFocus();
				}
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro ao validar data!\n" + e1.getMessage());
			txtData.requestFocus();
		}
	}
}
