package br.com.sintaxerror.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sintaxerror.dao.ConsultaDAO;
import br.com.sintaxerror.dao.DentistaDAO;
import br.com.sintaxerror.dao.PacienteDAO;
import br.com.sintaxerror.dao.PagamentoDAO;
import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.model.Pagamento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.TextArea;
import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaDentista extends JFrame {

	private JPanel contentPane;
	private JLabel lblCadastroDentista;
	private JLabel lblCodigo;
	private JTextField txtCod;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEspecialidade;
	private JComboBox cbmEsp;
	private TextArea txtMostrar;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnListar;
	private JButton btnLimpar;
	private Dentista dentista;
	private DentistaDAO dentistadao;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDentista frame = new TelaDentista();
					frame.setVisible(true);
					frame.setTitle("Dentista");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDentista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCadastroDentista = new JLabel("Cadastro Dentista");
		lblCadastroDentista.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDentista.setBounds(10, 11, 136, 14);
		contentPane.add(lblCadastroDentista);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 40, 46, 14);
		contentPane.add(lblCodigo);
		
		txtCod = new JTextField();
		txtCod.addKeyListener(new KeyAdapter() {
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
		txtCod.setBounds(58, 36, 86, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(153, 40, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(197, 37, 344, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(10, 65, 86, 14);
		contentPane.add(lblEspecialidade);
		
		cbmEsp = new JComboBox();
		cbmEsp.setModel(new DefaultComboBoxModel(new String[] {"Escolha uma op\u00E7\u00E3o", "Clareamento dental\t", "Implante dent\u00E1rio", "Dor de dente", "Ortodontia", "Endodontia", "Pr\u00F3tese dentaria", "Aparelho ortod\u00F4ntico", "Periodontia", "Tratamento de canal", "Odontopediatria", "Aparelho dent\u00E1rio", "Pr\u00F3tese", "Dentadura"}));
		cbmEsp.setBounds(95, 61, 446, 22);
		contentPane.add(cbmEsp);
		
		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFocusable(false);
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setBounds(10, 92, 528, 131);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFocusTraversalKeysEnabled(false);
		table.setFocusable(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome Dentista", "Especialidade"
			}
		));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		
		/*txtMostrar = new TextArea();
		txtMostrar.setBounds(10, 89, 531, 160);
		contentPane.add(txtMostrar);*/
		
		btnCadastrar = new JButton();
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCod.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um Codigo válido!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um nome válido!");
						return;
					}
			
					if (cbmEsp.getSelectedIndex() == 0){
						JOptionPane.showMessageDialog(null, "Escolha a especialidade válida!");
					}
				
					dentistadao  = new DentistaDAO();
					if (dentistadao.consultar(Integer.parseInt(txtCod.getText())) !=null) {
				JOptionPane.showMessageDialog(null, "Código cadastrado, insira um novo. ");
					}
				
					dentista = new Dentista ();
					dentista.setCodDentista(Integer.parseInt(txtCod.getText()));
					dentista.setNomeDentista(txtNome.getText());
					dentista.setEspecialidade(String.valueOf(cbmEsp.getSelectedItem()));
				
					dentistadao = new DentistaDAO();
					dentistadao.salvar(dentista);
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar o dentista!\n" + e.getMessage());
				}
			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		ImageIcon iconSalvar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/salvar.png"));
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(50, 50, 50));
		btnCadastrar.setIcon(iconSalvar);
		btnCadastrar.setBounds(5, 255, 83, 74);
		contentPane.add(btnCadastrar);
		
		btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtCod.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um Codigo válido!");
						return;
					}
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Instira um nome válido!");
						return;
					}
			
					if (cbmEsp.getSelectedIndex() == 0){
						JOptionPane.showMessageDialog(null, "Escolha a especialidade válida!");
					}
				
					dentista = new Dentista ();
					dentista.setCodDentista(Integer.parseInt(txtCod.getText()));
					dentista.setNomeDentista(txtNome.getText());
					dentista.setEspecialidade(String.valueOf(cbmEsp.getSelectedItem()));
				
					dentistadao = new DentistaDAO();
					dentistadao.alterar(dentista);
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar o dentista!\n" + e.getMessage());
				}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon iconAlterar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/alterar.png"));
		iconAlterar.setImage(iconAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconAlterar);
		btnAlterar.setBounds(98, 255, 83, 74);
		contentPane.add(btnAlterar);
		
		btnConsultar = new JButton();
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dentista = new Dentista();
					dentistadao = new DentistaDAO();
					dentista = dentistadao.consultar(Integer.parseInt(txtCod.getText()));
					txtNome.setText(dentista.getNomeDentista());
					cbmEsp.setSelectedItem(dentista.getEspecialidade());
					
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta!");
				}
					
			}
		});
		btnConsultar.setToolTipText("Consultar");
		ImageIcon iconConsultar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/consultar.png"));
		iconConsultar.setImage(iconConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconConsultar);
		btnConsultar.setBounds(188, 255, 83, 74);
		contentPane.add(btnConsultar);
		
		btnExcluir = new JButton();
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dentistadao = new DentistaDAO();
					dentistadao.excluir(Integer.parseInt(txtCod.getText()));
					JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!!!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao excluir paciente!!!\n" + e1.getMessage());

				}
			}
		});
		btnExcluir.setToolTipText("Excluir");
		ImageIcon iconExcluir = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/excluir.png"));
		iconExcluir.setImage(iconExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconExcluir);
		btnExcluir.setBounds(278, 255, 83, 74);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton();
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					
					List<Dentista> lista = new ArrayList<Dentista>();
					dentistadao = new DentistaDAO();
					lista = dentistadao.listarTodos();
					if (lista != null) {
						DefaultTableModel model = (DefaultTableModel) table.getModel(); 
						for (Dentista dentista : lista) {
							
							
							model.addRow(new Object[] {
									
									dentista.getCodDentista(),
									dentista.getNomeDentista(),
									dentista.getEspecialidade()									
							});
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Nenum Pagamento Encontrado!");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar!\n" + e1.getMessage());
				}
			}
		});
		btnListar.setToolTipText("Listar");
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/listar.png"));
		iconListar.setImage(iconListar.getImage().getScaledInstance(50, 50, 50));
		btnListar.setIcon(iconListar);
		btnListar.setBounds(368, 255, 83, 74);
		contentPane.add(btnListar);
		
		btnLimpar = new JButton();
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCod.setText(null);
				txtNome.setText(null);
				cbmEsp.setSelectedIndex(0);
				DefaultTableModel model = (DefaultTableModel)table.getModel(); model.setNumRows(0);	
			}
		});
		btnLimpar.setToolTipText("Limpar");
		ImageIcon iconLimpar = new ImageIcon(getClass().getResource("/br/com/sintaxerror/img/limpar.png"));
		iconLimpar.setImage(iconLimpar.getImage().getScaledInstance(50, 50, 50));
		btnLimpar.setIcon(iconLimpar);
		btnLimpar.setBounds(458, 255, 83, 74);
		contentPane.add(btnLimpar);
	}
	
	public void consultaCodigo() {
		if (txtCod.getText().equals("")) {
			try {
				dentistadao = new DentistaDAO();
				int id = dentistadao.lastId();
				if (id != 0) {
					id++;
					txtCod.setText(String.valueOf(id));
					txtNome.requestFocus();
				} else {
					txtCod.setText("1");
					txtNome.requestFocus();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar Ultimo ID!\n" + e1.getMessage());
			}
		} else {
			try {
				dentistadao = new DentistaDAO();
				dentista = new Dentista();
				dentista = dentistadao.consultar(Integer.parseInt(txtCod.getText()));
				if (dentista != null) {
					JOptionPane.showMessageDialog(null, "Codigo do dentista ja Cadastrado!");
					txtCod.setText(null);
					txtCod.requestFocus();

				}else {
					txtNome.requestFocus();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Erro ao Consultar Codigo do dentista ja Existente!\n" + e1.getMessage());
			}
		}
	}
	
}
