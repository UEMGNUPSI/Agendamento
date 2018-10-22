/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AgendamentoDAO;
import DAO.AreaDAO;
import DAO.ClienteDAO;
import DAO.ProfissionalDAO;
import DAO.ServicoDAO;
import Model.Agendamento;
import Model.Area;
import Model.Cliente;
import Model.Profissional;
import Model.Servico;
import Valida.ValidaData;
import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dc
 */
public class AgendamentoView extends javax.swing.JInternalFrame {

    Agendamento agendamento = new Agendamento();
    AgendamentoDAO agendamentodao = new AgendamentoDAO();
    List<Agendamento> listaagendamento = new ArrayList<>();
    
    Cliente cliente = new Cliente();
    ClienteDAO clientedao = new ClienteDAO();
    List<Cliente> listacliente = new ArrayList<>();
    
    Servico servico = new Servico();
    ServicoDAO servicodao = new ServicoDAO();
    List<Servico> listaservico = new ArrayList<>();
    
    Area area = new Area();
    AreaDAO areadao = new AreaDAO();
    List<Area> listaarea = new ArrayList<>();
    
    Profissional profissional = new Profissional();
    ProfissionalDAO profissionaldao = new ProfissionalDAO();
    List<Profissional> listaprofissional = new ArrayList<>();
    
    public AgendamentoView() {
        initComponents();
        
        BtnAgendar.setUI(new  WindowsButtonUI());
        BtnCancelar.setUI(new  WindowsButtonUI());
        BtnRemarcar.setUI(new  WindowsButtonUI());
        BtnSair.setUI(new  WindowsButtonUI());
        BtnSair1.setUI(new  WindowsButtonUI());
        BtnSalvar.setUI(new  WindowsButtonUI());
        BtnSelecionarC.setUI(new  WindowsButtonUI());
        BtnSelecionarP.setUI(new  WindowsButtonUI());
        BtnSelecionarS.setUI(new  WindowsButtonUI());
        BtnPesquisa.setUI(new WindowsButtonUI());
        BtnSelecionarC.setUI(new WindowsButtonUI());
        BtnSelecionarP.setUI(new WindowsButtonUI());
        BtnSelecionarS.setUI(new WindowsButtonUI());
        BtnCancelar1.setUI(new WindowsButtonUI());
        BtnCancelar2.setUI(new WindowsButtonUI());
        BtnCancelar3.setUI(new WindowsButtonUI());
        
        TxtPesquisaData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        TxtIdCliente.setVisible(false);
        TxtIdProfissional.setVisible(false);
        TxtIdServico.setVisible(false);
        TxtIdAgendamento.setVisible(false);
        TxtId.setVisible(false);
        
        jDAgendar.setSize(511,394);
        jDProfissional.setSize(704, 512);
        jDCliente.setSize(720, 528);
        jDServicos.setSize(696, 510);
        atualizaTabelaAgendamento();
        atualizaTabelaServico();
        atualizaTabelaCliente();
        atualizaTabelaProfissional();
        PrepararBotoesInicio();
    }
    
    public void atualizaTabelaAgendamento(){
        agendamento = new Agendamento();
        try {
            listaagendamento = agendamentodao.listaTodos(TxtPesquisaData.getText());
            listacliente = clientedao.listaTodos();
            listaprofissional = profissionaldao.listaTodos();
            listaservico = servicodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaagendamento.size()][6];
            int i = 0;
            for (Agendamento agendamento : listaagendamento) {
                
                dados[i][0] = String.valueOf(agendamento.getId());
                for(Cliente cliente : listacliente){
                    if(cliente.getId() == agendamento.getIdcliente()){
                        dados[i][1] = cliente.getNome();
                    }
                }
                for(Profissional profissional : listaprofissional){
                    if(profissional.getId() == agendamento.getIdprofissional()){
                        dados[i][2] = profissional.getNome();
                    }
                }
                for(Servico servico : listaservico){
                    if(servico.getId() == agendamento.getIdserviço()){
                        dados[i][3] = servico.getDescricao();
                    }
                }

                dados[i][4] = String.valueOf(agendamento.getDataAgendamento());
                dados[i][5] = String.valueOf(agendamento.getHorarioAgendamento());
           
                i++;
            }
            String tituloColuna[] = {"Id", "Cliente", "Profissional", "Serviço","Data de agendamento","Horario de agendamento"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblAgendamento.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblAgendamento.getColumnModel().getColumn(0).setMaxWidth(0);
            TblAgendamento.getColumnModel().getColumn(0).setMinWidth(0);
            TblAgendamento.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblAgendamento.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblAgendamento.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblAgendamento.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblAgendamento.getColumnModel().getColumn(5).setCellRenderer(centralizado);
            TblAgendamento.setRowHeight(35);
            TblAgendamento.updateUI();
    }
    
    public void atualizaTabelaCliente(){
        cliente = new Cliente();
        try {
            listacliente = clientedao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listacliente.size()][5];
            int i = 0;
            for (Cliente cliente : listacliente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getRg();
                dados[i][3] = cliente.getCpf();
                dados[i][4] = cliente.getTelefone1();
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Fone Principal"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblCliente.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblCliente.getColumnModel().getColumn(0).setMaxWidth(0);
            TblCliente.getColumnModel().getColumn(0).setMinWidth(0);
            TblCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblCliente.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblCliente.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblCliente.setRowHeight(35);
            TblCliente.updateUI();
    }
    
    public void atualizaTabelaServico(){
        servico = new Servico();
        try {
            listaservico = servicodao.listaTodos();
            listaarea    = areadao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaservico.size()][5];
            int i = 0;
            for (Servico servico : listaservico) {
                dados[i][0] = String.valueOf(servico.getId());
                dados[i][1] = servico.getDescricao();              
                for(Area area: listaarea){    
                    if(servico.getIdArea() == area.getId()){
                         dados[i][2] = String.valueOf(area.getNome());
                    }}
                dados[i][3] = servico.getTempo();
                dados[i][4] = String.valueOf(servico.getIdArea());
                i++;
            }
            
            String tituloColuna[] = {"Id", "Descrição", "Área", "Tempo estimado","Idarea"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblServico.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false,false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblServico.getColumnModel().getColumn(0).setMaxWidth(0);
            TblServico.getColumnModel().getColumn(0).setMinWidth(0);
            TblServico.getColumnModel().getColumn(0).setPreferredWidth(0);
            TblServico.getColumnModel().getColumn(4).setMaxWidth(0);
            TblServico.getColumnModel().getColumn(4).setMinWidth(0);
            TblServico.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            TblServico.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblServico.getColumnModel().getColumn(2).setPreferredWidth(150);
            TblServico.getColumnModel().getColumn(3).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblServico.getColumnModel().getColumn(3).setCellRenderer(centralizado);
            TblServico.setRowHeight(35);
            TblServico.updateUI();
    }
    
    public void atualizaTabelaProfissional(){
        profissional = new Profissional();
        try {
            listaprofissional = profissionaldao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaprofissional.size()][5];
            int i = 0;
            for (Profissional profissional : listaprofissional) {
                dados[i][0] = String.valueOf(profissional.getId());
                dados[i][1] = profissional.getNome();
                dados[i][2] = profissional.getRg();
                dados[i][3] = profissional.getCpf();
                dados[i][4] = profissional.getTelefone1();
            
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Fone Principal"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblProfissionais.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblProfissionais.getColumnModel().getColumn(0).setMaxWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setMinWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblProfissionais.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblProfissionais.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblProfissionais.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblProfissionais.setRowHeight(35);
            TblProfissionais.updateUI();
    }
    
    public void atualizaTabelaAgendamentoBusca(){
        agendamento = new Agendamento();
        try {
            listacliente = clientedao.listaTodos();
            listaprofissional = profissionaldao.listaTodos();
            listaservico = servicodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaagendamento.size()][6];
            int i = 0;
            for (Agendamento agendamento : listaagendamento) {
                
                dados[i][0] = String.valueOf(agendamento.getId());
                for(Cliente cliente : listacliente){
                    if(cliente.getId() == agendamento.getIdcliente()){
                        dados[i][1] = cliente.getNome();
                    }
                }
                for(Profissional profissional : listaprofissional){
                    if(profissional.getId() == agendamento.getIdprofissional()){
                        dados[i][2] = profissional.getNome();
                    }
                }
                for(Servico servico : listaservico){
                    if(servico.getId() == agendamento.getIdserviço()){
                        dados[i][3] = servico.getDescricao();
                    }
                }

                dados[i][4] = String.valueOf(agendamento.getDataAgendamento());
                dados[i][5] = String.valueOf(agendamento.getHorarioAgendamento());
           
                i++;
            }
            String tituloColuna[] = {"Id", "Cliente", "Profissional", "Serviço","Data de agendamento","Horario de agendamento"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblAgendamento.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblAgendamento.getColumnModel().getColumn(0).setMaxWidth(0);
            TblAgendamento.getColumnModel().getColumn(0).setMinWidth(0);
            TblAgendamento.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblAgendamento.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblAgendamento.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblAgendamento.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblAgendamento.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblAgendamento.getColumnModel().getColumn(5).setCellRenderer(centralizado);
            TblAgendamento.setRowHeight(35);
            TblAgendamento.updateUI();
    }
    
    public void LimpaCampos(){
        TxtCliente.setText("");
        TxtProfissional.setText("");
        TxtServico.setText("");
        TxtIdCliente.setText("");
        TxtIdProfissional.setText("");
        TxtIdServico.setText("");
        TxtIdAgendamento.setText("");
        TxtHorario.setText("");
        TxtData.setText("");
        TxtId.setText("");
    }
    
    public void PrepararBotoesInicio(){
        TxtPesquisaNome.requestFocus();
        BtnCancelar.setEnabled(false);
        BtnRemarcar.setEnabled(false);
        TblAgendamento.clearSelection();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDAgendar = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TxtCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtProfissional = new javax.swing.JTextField();
        TxtServico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtHorario = new javax.swing.JFormattedTextField();
        TxtData = new javax.swing.JFormattedTextField();
        BtnSalvar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        TxtIdCliente = new javax.swing.JTextField();
        TxtIdServico = new javax.swing.JTextField();
        TxtIdProfissional = new javax.swing.JTextField();
        BtnSair1 = new javax.swing.JButton();
        TxtIdAgendamento = new javax.swing.JTextField();
        CalendarioAgenda = new com.toedter.calendar.JDateChooser();
        jDCliente = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblCliente = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        TxtPesquisaCliente = new javax.swing.JTextField();
        BtnSelecionarC = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        BtnCancelar1 = new javax.swing.JButton();
        jDProfissional = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblProfissionais = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TxtPesquisaProfissional = new javax.swing.JTextField();
        BtnSelecionarP = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        BtnCancelar2 = new javax.swing.JButton();
        jDServicos = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblServico = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TxtNomeServicos = new javax.swing.JTextField();
        BtnSelecionarS = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        BtnCancelar3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtPesquisaNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtPesquisaData = new javax.swing.JFormattedTextField();
        BtnPesquisa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ComboFiltro = new javax.swing.JComboBox<>();
        CalendarioPesquisa = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblAgendamento = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        BtnAgendar = new javax.swing.JButton();
        BtnRemarcar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        TxtId = new javax.swing.JTextField();

        jDAgendar.setMinimumSize(new java.awt.Dimension(511, 394));
        jDAgendar.setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setMaximumSize(new java.awt.Dimension(511, 394));
        jPanel2.setMinimumSize(new java.awt.Dimension(511, 394));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("Cliente:");

        TxtCliente.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        TxtCliente.setEnabled(false);
        TxtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtClienteMouseClicked(evt);
            }
        });
        TxtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtClienteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Profissional:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Serviço:");

        TxtProfissional.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtProfissional.setEnabled(false);
        TxtProfissional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProfissionalMouseClicked(evt);
            }
        });

        TxtServico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtServico.setEnabled(false);
        TxtServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtServicoMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Data:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Horário:");

        try {
            TxtHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtHorario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            TxtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtData.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        BtnSalvar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSalvar.setText("SALVAR");
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel10.setText("AGENDAR");

        TxtIdCliente.setEnabled(false);

        TxtIdServico.setEnabled(false);
        TxtIdServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIdServicoActionPerformed(evt);
            }
        });

        TxtIdProfissional.setEnabled(false);

        BtnSair1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair1.setText("SAIR");
        BtnSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSair1ActionPerformed(evt);
            }
        });

        TxtIdAgendamento.setEnabled(false);

        CalendarioAgenda.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioAgendaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TxtServico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(12, 12, 12)
                            .addComponent(TxtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CalendarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(TxtProfissional, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BtnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 71, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TxtIdAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtIdProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(TxtIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CalendarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)
                        .addComponent(TxtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSalvar)
                    .addComponent(BtnSair1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDAgendarLayout = new javax.swing.GroupLayout(jDAgendar.getContentPane());
        jDAgendar.getContentPane().setLayout(jDAgendarLayout);
        jDAgendarLayout.setHorizontalGroup(
            jDAgendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDAgendarLayout.setVerticalGroup(
            jDAgendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDCliente.setMinimumSize(new java.awt.Dimension(720, 520));
        jDCliente.setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel3.setMaximumSize(new java.awt.Dimension(720, 528));
        jPanel3.setMinimumSize(new java.awt.Dimension(720, 528));

        TblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblCliente.setRowHeight(24);
        TblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblCliente);

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel11.setText("Nome:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(TxtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        BtnSelecionarC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSelecionarC.setText("SELECIONAR");
        BtnSelecionarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarCActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("CLIENTE");

        BtnCancelar1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar1.setText("CANCELAR");
        BtnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BtnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSelecionarC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel12)
                .addGap(30, 30, 30)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSelecionarC)
                    .addComponent(BtnCancelar1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDClienteLayout = new javax.swing.GroupLayout(jDCliente.getContentPane());
        jDCliente.getContentPane().setLayout(jDClienteLayout);
        jDClienteLayout.setHorizontalGroup(
            jDClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDClienteLayout.setVerticalGroup(
            jDClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDProfissional.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        TblProfissionais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblProfissionais.setRowHeight(24);
        TblProfissionais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProfissionaisMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TblProfissionais);

        jPanel7.setBackground(new java.awt.Color(250, 250, 250));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel13.setText("Nome:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(TxtPesquisaProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtPesquisaProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        BtnSelecionarP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSelecionarP.setText("SELECIONAR");
        BtnSelecionarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarPActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel14.setText("PROFISSIONAL");

        BtnCancelar2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar2.setText("CANCELAR");
        BtnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSelecionarP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSelecionarP)
                    .addComponent(BtnCancelar2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDProfissionalLayout = new javax.swing.GroupLayout(jDProfissional.getContentPane());
        jDProfissional.getContentPane().setLayout(jDProfissionalLayout);
        jDProfissionalLayout.setHorizontalGroup(
            jDProfissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDProfissionalLayout.setVerticalGroup(
            jDProfissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDServicos.setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel8.setMaximumSize(new java.awt.Dimension(694, 508));
        jPanel8.setMinimumSize(new java.awt.Dimension(694, 508));

        TblServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblServico.setRowHeight(24);
        TblServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblServicoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TblServico);

        jPanel9.setBackground(new java.awt.Color(250, 250, 250));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel15.setText("Nome:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(TxtNomeServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(TxtNomeServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        BtnSelecionarS.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSelecionarS.setText("SELECIONAR");
        BtnSelecionarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarSActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel16.setText("SERVIÇOS");

        BtnCancelar3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar3.setText("CANCELAR");
        BtnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(BtnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSelecionarS, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSelecionarS)
                    .addComponent(BtnCancelar3))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jDServicosLayout = new javax.swing.GroupLayout(jDServicos.getContentPane());
        jDServicos.getContentPane().setLayout(jDServicosLayout);
        jDServicosLayout.setHorizontalGroup(
            jDServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDServicosLayout.setVerticalGroup(
            jDServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        TxtPesquisaNome.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Data:");

        try {
            TxtPesquisaData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtPesquisaData.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        BtnPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnPesquisa.setText("PESQUISAR");
        BtnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Filtro:");

        ComboFiltro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ComboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Profissional" }));

        CalendarioPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalendarioPesquisaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CalendarioPesquisaMousePressed(evt);
            }
        });
        CalendarioPesquisa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioPesquisaPropertyChange(evt);
            }
        });
        CalendarioPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CalendarioPesquisaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtPesquisaData, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(CalendarioPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnPesquisa)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CalendarioPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(TxtPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(TxtPesquisaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPesquisa)
                        .addComponent(jLabel4)
                        .addComponent(ComboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        TblAgendamento.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        TblAgendamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblAgendamento.setRowHeight(24);
        TblAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblAgendamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblAgendamento);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel3.setText("AGENDAMENTO");

        BtnAgendar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnAgendar.setText("AGENDAR");
        BtnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgendarActionPerformed(evt);
            }
        });

        BtnRemarcar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnRemarcar.setText("REMARCAR");
        BtnRemarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemarcarActionPerformed(evt);
            }
        });

        BtnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnSair.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair.setText("SAIR");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        TxtId.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TxtId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnRemarcar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgendar)
                    .addComponent(BtnRemarcar)
                    .addComponent(BtnCancelar)
                    .addComponent(BtnSair)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtClienteMouseClicked
        jDCliente.setVisible(true);
        jDCliente.setLocationRelativeTo(null);
    }//GEN-LAST:event_TxtClienteMouseClicked

    private void BtnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgendarActionPerformed
       LimpaCampos();
       jDAgendar.setVisible(true);
       jDAgendar.setLocationRelativeTo(null);
      
    }//GEN-LAST:event_BtnAgendarActionPerformed

    private void BtnSelecionarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarCActionPerformed
        
        if(!TxtIdCliente.getText().isEmpty()){
            jDCliente.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Selecione um Cliente", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_BtnSelecionarCActionPerformed

    private void TxtServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtServicoMouseClicked
        jDServicos.setVisible(true);
        jDServicos.setLocationRelativeTo(null);
    }//GEN-LAST:event_TxtServicoMouseClicked

    private void TxtProfissionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProfissionalMouseClicked
        jDProfissional.setVisible(true);
        jDProfissional.setLocationRelativeTo(null);
    }//GEN-LAST:event_TxtProfissionalMouseClicked

    private void TblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblClienteMouseClicked
        if(evt.getClickCount() >= 2){
            TxtIdCliente.setText(TblCliente.getValueAt(TblCliente.getSelectedRow(),0).toString());
            TxtCliente.setText(TblCliente.getValueAt(TblCliente.getSelectedRow(),1).toString());
        
            if(!TxtIdCliente.getText().isEmpty()){
            jDCliente.dispose();
            }
        }
    }//GEN-LAST:event_TblClienteMouseClicked

    private void TblProfissionaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProfissionaisMouseClicked
        if(evt.getClickCount() >= 2){ 
            TxtIdProfissional.setText(TblProfissionais.getValueAt(TblProfissionais.getSelectedRow(),0).toString());
            TxtProfissional.setText(TblProfissionais.getValueAt(TblProfissionais.getSelectedRow(),1).toString());
            if(!TxtIdProfissional.getText().isEmpty()){
            jDProfissional.dispose();
            }
        }
    }//GEN-LAST:event_TblProfissionaisMouseClicked

    private void BtnSelecionarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarPActionPerformed
       if(!TxtIdProfissional.getText().isEmpty()){
            jDProfissional.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Selecione um Profissional", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnSelecionarPActionPerformed

    private void TblServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblServicoMouseClicked
        if(evt.getClickCount() >= 2){ 
            TxtIdServico.setText(TblServico.getValueAt(TblServico.getSelectedRow(),0).toString());
            TxtServico.setText(TblServico.getValueAt(TblServico.getSelectedRow(),1).toString());
            if(!TxtIdServico.getText().isEmpty()){
            jDServicos.dispose();
            }
        }
    }//GEN-LAST:event_TblServicoMouseClicked

    private void BtnSelecionarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarSActionPerformed
       if(!TxtIdServico.getText().isEmpty()){
            jDServicos.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Selecione um Serviço", "Erro", JOptionPane.WARNING_MESSAGE);
        }   
    }//GEN-LAST:event_BtnSelecionarSActionPerformed

    private void BtnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSair1ActionPerformed
       jDAgendar.dispose();
       jDCliente.dispose();
       jDProfissional.dispose();
       jDServicos.dispose();
       LimpaCampos();
    }//GEN-LAST:event_BtnSair1ActionPerformed

    private void TxtIdServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIdServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIdServicoActionPerformed

    private void TxtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtClienteActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        String[] ValidaHora = TxtHorario.getText().split(":");
        Boolean ValidacaoData = ValidaData.ValidarData(TxtData.getText());
        
        if(TxtIdCliente.getText().isEmpty() || TxtIdProfissional.getText().isEmpty() || TxtIdServico.getText().isEmpty()){        
            JOptionPane.showMessageDialog(null, "Selecione todos os dados", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else if(Integer.parseInt(ValidaHora[0]) >= 24 || Integer.parseInt(ValidaHora[0]) < 0 || Integer.parseInt(ValidaHora[1])>= 60 || Integer.parseInt(ValidaHora[1]) < 0 ){
            JOptionPane.showMessageDialog(null, "Horario inválido", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if(ValidacaoData == false){
            JOptionPane.showMessageDialog(null, "Data Invalida. Digite uma data verdadeira!");
            TxtData.requestFocus();
            return;
        }
        else{
            agendamento = new Agendamento();

            try {
                cliente = clientedao.Buscar(Integer.parseInt(TxtIdCliente.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                profissional = profissionaldao.Buscar(Integer.parseInt(TxtIdProfissional.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                servico = servicodao.Buscar(Integer.parseInt(TxtIdServico.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //recebe a data e hora atual
            String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
            String horaAtual = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
            
           //faz os calculos para o horario presvito de saida conforme o horario de atendimento e horario que o serviço demanda
            String[] horarioAtendimento = TxtHorario.getText().split(":");
            String[] horarioServico = servico.getTempo().split(":");
            
            int HorarioAtendimento = Integer.parseInt(horarioAtendimento[0]) + Integer.parseInt(horarioAtendimento[1]);
            
            int hora ;
            int minutos ;
            
                hora  = (Integer.parseInt(horarioAtendimento[0]) + Integer.parseInt(horarioServico[0])) ;
                minutos  = (Integer.parseInt(horarioAtendimento[1]) + Integer.parseInt(horarioServico[1])) - 1;

                if(minutos==60){
                    hora = hora+1;
                    minutos = 0;
                }
                if(minutos==-1){
                    minutos = 59;
                    hora = hora-01;
                }
                String horafinal = Integer.toString(hora)+":"+Integer.toString(minutos);
                
                hora  = Integer.parseInt(horarioAtendimento[0]) ;
                minutos  = Integer.parseInt(horarioAtendimento[1]);
                if(minutos==60){
                    hora = hora+1;
                    minutos = 0;
                }
  
                String horainicio = Integer.toString(hora)+":"+Integer.toString(minutos);
                
                
                
                hora  = (Integer.parseInt(horarioAtendimento[0]) + Integer.parseInt(horarioServico[0])) ;
                minutos  = (Integer.parseInt(horarioAtendimento[1]) + Integer.parseInt(horarioServico[1])) ;

                String horafinal1 = Integer.toString(hora)+":"+Integer.toString(minutos);
                

            try {
                listaagendamento = agendamentodao.verificarVaga(TxtIdProfissional.getText(),horainicio, horafinal,TxtData.getText());
                listaagendamento.size();
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(listaagendamento.isEmpty() || listaagendamento.size()<=1){
                if(TxtIdAgendamento.getText().isEmpty()){ 
                    //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados 
                    agendamento.setIdcliente(cliente.getId());
                    agendamento.setIdprofissional(profissional.getId());
                    agendamento.setIdserviço(servico.getId());
                    agendamento.setDataAgendamento(TxtData.getText());
                    agendamento.setHorarioAgendamento(TxtHorario.getText());
                    agendamento.setHorarioPrevistoTermino(horafinal1);
                    agendamento.setDataAtendimento(dataAtual);
                    agendamento.setHorarioAtendimento(horaAtual);


                    try {
                        agendamentodao.Salvar(agendamento);
                        JOptionPane.showMessageDialog(null, "Agendado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        atualizaTabelaAgendamento();
                        jDAgendar.dispose();
                        jDCliente.dispose();
                        jDProfissional.dispose();
                        jDServicos.dispose();
                        LimpaCampos();
                        PrepararBotoesInicio();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
                else{
                     //Salva tudo digitado no campo de texto para o objeto e faz update no banco de dados 
                    agendamento.setId(Integer.parseInt(TxtIdAgendamento.getText()));
                    agendamento.setIdcliente(cliente.getId());
                    agendamento.setIdprofissional(profissional.getId());
                    agendamento.setIdserviço(servico.getId());
                    agendamento.setDataAgendamento(TxtData.getText());
                    agendamento.setHorarioAgendamento(TxtHorario.getText());
                    agendamento.setHorarioPrevistoTermino(horafinal1);
                     
                    try {
                        agendamentodao.Remarcar(agendamento);
                        JOptionPane.showMessageDialog(null, "Remarcado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); 
                        atualizaTabelaAgendamento();
                        jDAgendar.dispose();
                        jDCliente.dispose();
                        jDProfissional.dispose();
                        jDServicos.dispose();
                        LimpaCampos();
                        PrepararBotoesInicio();
                    } catch (SQLException ex) {
                        Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
            else if(!listaagendamento.isEmpty()){
                JOptionPane.showMessageDialog(null, "Houve um choque de horario!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
            
           
         }
            
        
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        if(TxtId.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Selecione um dado para cancela-lo!","Erro", JOptionPane.WARNING_MESSAGE);
       }
       else{
           cliente.setId(Integer.parseInt(TxtId.getText()));
           int confirma = JOptionPane.showConfirmDialog(null, "Deseja cancelar: "+ cliente.getNome());
           if(confirma == 0){
               
               try {
                   agendamentodao.Cancelamento(Integer.parseInt(TxtId.getText()));
                   JOptionPane.showMessageDialog(null, "Cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                   
               } catch (SQLException ex) {
                   Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
               }
                atualizaTabelaAgendamento();
                LimpaCampos();
                PrepararBotoesInicio();
                TblAgendamento.clearSelection();
           }
       }
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void TblAgendamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblAgendamentoMouseClicked
        cliente = new Cliente();
        agendamento = new Agendamento();
        TxtId.setText(TblAgendamento.getValueAt(TblAgendamento.getSelectedRow(),0).toString());
        cliente.setNome(TblAgendamento.getValueAt(TblAgendamento.getSelectedRow(),1).toString());
        
        TxtCliente.setText(TblAgendamento.getValueAt(TblAgendamento.getSelectedRow(),1).toString());
        TxtProfissional.setText(TblAgendamento.getValueAt(TblAgendamento.getSelectedRow(),2).toString());
        TxtServico.setText(TblAgendamento.getValueAt(TblAgendamento.getSelectedRow(),3).toString());
        
        try {
            agendamento = agendamentodao.Buscar(Integer.parseInt(TxtId.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TxtIdAgendamento.setText(Integer.toString(agendamento.getId()));
        TxtIdCliente.setText(Integer.toString(agendamento.getIdcliente()));
        TxtIdProfissional.setText(Integer.toString(agendamento.getIdprofissional()));
        TxtIdServico.setText(Integer.toString(agendamento.getIdserviço()));
        TxtHorario.setText(agendamento.getHorarioAgendamento());
        TxtData.setText(agendamento.getDataAgendamento());
        
        BtnRemarcar.setEnabled(true);
        BtnCancelar.setEnabled(true);
        
    }//GEN-LAST:event_TblAgendamentoMouseClicked

    private void BtnRemarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemarcarActionPerformed
        jDAgendar.setVisible(true);
        jDAgendar.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnRemarcarActionPerformed

    private void BtnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPesquisaActionPerformed
        if(TxtPesquisaData.getText().equals("  /  /    ")){
            JOptionPane.showMessageDialog(null, "Obrigatorio ter uma data", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String filtro = null;

            if(ComboFiltro.getSelectedItem().toString() == "Cliente"){
                filtro = "cliente";
            }
            else if(ComboFiltro.getSelectedItem().toString() == "Profissional"){
                filtro = "profissionais";
            }           
            
            try {
                listaagendamento = agendamentodao.BuscarNome(filtro,TxtPesquisaNome.getText(),TxtPesquisaData.getText());
                if(listaagendamento == null){
                    JOptionPane.showMessageDialog(null, "Nenhum "+ComboFiltro.getSelectedItem().toString()+" encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaAgendamento();
                }else{
                    atualizaTabelaAgendamentoBusca();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AgendamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnPesquisaActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
         this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void CalendarioPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarioPesquisaMouseClicked
       
        
        
    }//GEN-LAST:event_CalendarioPesquisaMouseClicked

    private void CalendarioPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CalendarioPesquisaKeyPressed
       TxtPesquisaNome.setText(CalendarioPesquisa.getDate().toString());
    }//GEN-LAST:event_CalendarioPesquisaKeyPressed

    private void CalendarioPesquisaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarioPesquisaMousePressed
       
        
    }//GEN-LAST:event_CalendarioPesquisaMousePressed

    private void CalendarioPesquisaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarioPesquisaPropertyChange
        Date date = new Date();
        if(CalendarioPesquisa.getDate() == null){
            return;
        }
        else{
            date = CalendarioPesquisa.getDate();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            TxtPesquisaData.setText(df.format(date));
        }
    }//GEN-LAST:event_CalendarioPesquisaPropertyChange

    private void CalendarioAgendaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarioAgendaPropertyChange
       Date date = new Date();
        if(CalendarioAgenda.getDate() == null){
            return;
        }
        else{
            date = CalendarioAgenda.getDate();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            TxtData.setText(df.format(date));
        }
    }//GEN-LAST:event_CalendarioAgendaPropertyChange

    private void BtnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar1ActionPerformed
        jDCliente.dispose();
    }//GEN-LAST:event_BtnCancelar1ActionPerformed

    private void BtnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar2ActionPerformed
      jDProfissional.dispose();
    }//GEN-LAST:event_BtnCancelar2ActionPerformed

    private void BtnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar3ActionPerformed
       jDServicos.dispose();
    }//GEN-LAST:event_BtnCancelar3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgendar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCancelar1;
    private javax.swing.JButton BtnCancelar2;
    private javax.swing.JButton BtnCancelar3;
    private javax.swing.JButton BtnPesquisa;
    private javax.swing.JButton BtnRemarcar;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSair1;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JButton BtnSelecionarC;
    private javax.swing.JButton BtnSelecionarP;
    private javax.swing.JButton BtnSelecionarS;
    private com.toedter.calendar.JDateChooser CalendarioAgenda;
    private com.toedter.calendar.JDateChooser CalendarioPesquisa;
    private javax.swing.JComboBox<String> ComboFiltro;
    private javax.swing.JTable TblAgendamento;
    private javax.swing.JTable TblCliente;
    private javax.swing.JTable TblProfissionais;
    private javax.swing.JTable TblServico;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JFormattedTextField TxtData;
    private javax.swing.JFormattedTextField TxtHorario;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtIdAgendamento;
    private javax.swing.JTextField TxtIdCliente;
    private javax.swing.JTextField TxtIdProfissional;
    private javax.swing.JTextField TxtIdServico;
    private javax.swing.JTextField TxtNomeServicos;
    private javax.swing.JTextField TxtPesquisaCliente;
    private javax.swing.JFormattedTextField TxtPesquisaData;
    private javax.swing.JTextField TxtPesquisaNome;
    private javax.swing.JTextField TxtPesquisaProfissional;
    private javax.swing.JTextField TxtProfissional;
    private javax.swing.JTextField TxtServico;
    private javax.swing.JDialog jDAgendar;
    private javax.swing.JDialog jDCliente;
    private javax.swing.JDialog jDProfissional;
    private javax.swing.JDialog jDServicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
