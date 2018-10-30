/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AreaDAO;
import DAO.ProfissionalDAO;
import Model.Area;
import Model.Funcionario;
import Model.Profissional;
import Valida.LimitaDigitos;
import Valida.ValidaCPF;
import Valida.ValidaData;
import Valida.ValidaEmail;
import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dc
 */
public class ProfissionaisView extends javax.swing.JInternalFrame {

    Profissional profissional = new Profissional();
    ProfissionalDAO profissionaldao = new ProfissionalDAO();
    List<Profissional> listaprofissional = new ArrayList<>();
      
    Area area = new Area();
    AreaDAO areadao = new AreaDAO();
    List<Area> listaarea = new ArrayList<>();
    
    public ProfissionaisView(Funcionario funci) {
        initComponents();
        PainelProfissionais.setUI(new  MetalTabbedPaneUI());
        BtnSair1.setUI(new WindowsButtonUI());
        BtnAlterar.setUI(new WindowsButtonUI());
        BtnCancelar.setUI(new WindowsButtonUI());
        BtnExcluir.setUI(new WindowsButtonUI());
        BtnNovo.setUI(new WindowsButtonUI());
        BtnSair.setUI(new WindowsButtonUI());
        BtnSalvar.setUI(new WindowsButtonUI());
        TblProfissionais.setUI(new BasicTableUI());
        
        TxtEndereco.setDocument(new LimitaDigitos(100));
        TxtEmail.setDocument(new LimitaDigitos(100));
        TxtNomeCompleto.setDocument(new LimitaDigitos(100));
        TxtRG.setDocument(new LimitaDigitos(45));
        TxtBairro.setDocument(new LimitaDigitos(100));
        TxtCidade.setDocument(new LimitaDigitos(50));
        TxtFormacao.setDocument(new LimitaDigitos(75));
        
        if(funci.getNivel() == false){
            BtnExcluir.setVisible(false);
        }
        
        TxtId.setVisible(false);
        PainelProfissionais.setSelectedIndex(1);
        atualizaTabelaProfissional();
        DesativarCampos();
        PrepararCancelarSalvar();
        try {
            preencherCombo();
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionaisView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void preencherCombo() throws SQLException{
        
        for(Area a: areadao.listaTodos()){
            ComboArea.addItem(a);
        }   
    }
    public void preencherComboSelecionado(Profissional area) throws SQLException{
        int i = 0;
        String aux;
        for(Area a: areadao.listaTodos()){
            ComboArea.addItem(a);
            aux = a.getNome();
            
                if(a.getId()== area.getIdArea()){
                    ComboArea.setSelectedIndex(i);              
                }
          
            i++;
        }   
    }
    
    public void atualizaTabelaProfissional(){
        profissional = new Profissional();
        try {
            listaprofissional = profissionaldao.listaTodos();
            listaarea    = areadao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaprofissional.size()][7];
            int i = 0;
            for (Profissional profissional : listaprofissional) {
                dados[i][0] = String.valueOf(profissional.getId());
                dados[i][1] = profissional.getNome();
                dados[i][2] = profissional.getRg();
                dados[i][3] = profissional.getCpf();
                dados[i][4] = profissional.getNascimento();
                dados[i][5] = profissional.getTelefone1();
                for(Area area: listaarea){    
                    if(profissional.getIdArea() == area.getId()){
                         dados[i][6] = String.valueOf(area.getId());
                    }}
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Nascimento","Contato","Area"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblProfissionais.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblProfissionais.getColumnModel().getColumn(0).setMaxWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setMinWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setPreferredWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setMaxWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setMinWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setPreferredWidth(0);
            
            TblProfissionais.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblProfissionais.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblProfissionais.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblProfissionais.setRowHeight(35);
            TblProfissionais.updateUI();
    }
    
    public void atualizaTabelaProfissionalBusca(){
        profissional = new Profissional();
        
        String dados[][] = new String[listaprofissional.size()][7];
            int i = 0;
            for (Profissional profissional : listaprofissional) {
                dados[i][0] = String.valueOf(profissional.getId());
                dados[i][1] = profissional.getNome();
                dados[i][2] = profissional.getRg();
                dados[i][3] = profissional.getCpf();
                dados[i][4] = profissional.getNascimento();
                dados[i][5] = profissional.getTelefone1();
                for(Area area: listaarea){    
                    if(profissional.getIdArea() == area.getId()){
                         dados[i][6] = String.valueOf(area.getId());
                    }}
           
                i++;
            }
            String tituloColuna[] = {"Id", "Nome", "RG", "CPF","Nascimento","Contato","Area"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblProfissionais.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblProfissionais.getColumnModel().getColumn(0).setMaxWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setMinWidth(0);
            TblProfissionais.getColumnModel().getColumn(0).setPreferredWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setMaxWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setMinWidth(0);
            TblProfissionais.getColumnModel().getColumn(6).setPreferredWidth(0);
            
            TblProfissionais.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblProfissionais.getColumnModel().getColumn(2).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(3).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(4).setPreferredWidth(100);
            TblProfissionais.getColumnModel().getColumn(5).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblProfissionais.getColumnModel().getColumn(4).setCellRenderer(centralizado);
            TblProfissionais.setRowHeight(35);
            TblProfissionais.updateUI();
    }
    
    public void DesativarCampos(){
        TxtNomeCompleto.setEnabled(false);
        TxtRG.setEnabled(false);
        TxtCPF.setEnabled(false);
        TxtNascimento.setEnabled(false);
        TxtTelefone1.setEnabled(false);
        TxtTelefone2.setEnabled(false);
        TxtEmail.setEnabled(false);
        TxtCep.setEnabled(false);
        TxtCidade.setEnabled(false);
        TxtBairro.setEnabled(false);
        TxtEndereco.setEnabled(false);
        TxtNumero.setEnabled(false);
        ComboArea.setEnabled(false);
        TxtFormacao.setEnabled(false);
    }
    
    public void AtivarCampos(){
        TxtNomeCompleto.setEnabled(true);
        TxtRG.setEnabled(true);
        TxtCPF.setEnabled(true);
        TxtNascimento.setEnabled(true);
        TxtTelefone1.setEnabled(true);
        TxtTelefone2.setEnabled(true);
        TxtEmail.setEnabled(true);
        TxtCep.setEnabled(true);
        TxtCidade.setEnabled(true);
        TxtBairro.setEnabled(true);
        TxtEndereco.setEnabled(true);
        TxtNumero.setEnabled(true);
        ComboArea.setEnabled(true);
        TxtFormacao.setEnabled(true);
    }
    
    public void PrepararNovo(){
        BtnSair.setEnabled(false);
        BtnSair1.setEnabled(false);
        BtnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
        BtnNovo.setEnabled(false);
        BtnCancelar.setEnabled(true);
        BtnSalvar.setEnabled(true);
        TblProfissionais.setEnabled(false);
        TblProfissionais.clearSelection();
    }
    
    public void PrepararCancelarSalvar(){
        BtnNovo.setEnabled(true);
        BtnAlterar.setEnabled(false);
        BtnCancelar.setEnabled(false);
        BtnSalvar.setEnabled(false);
        TblProfissionais.setEnabled(true);
        BtnExcluir.setEnabled(false);
        BtnSair.setEnabled(true);
        BtnSair1.setEnabled(true);
    }
    
    public void LimparCampos(){
        TxtId.setText("");
        TxtNomeCompleto.setText("");
        TxtRG.setText("");
        TxtCPF.setValue("");
        TxtNascimento.setValue("");
        TxtTelefone1.setValue("");
        TxtTelefone2.setValue("");
        TxtEmail.setText("");
        TxtCep.setValue("");
        TxtCidade.setText("");
        TxtNumero.setText("");
        TxtEndereco.setText("");
        TxtBairro.setText("");
        ComboArea.setSelectedIndex(0);
        TxtFormacao.setText("");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PainelProfissionais = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        TxtNomeCompleto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TxtRG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TxtTelefone1 = new javax.swing.JFormattedTextField();
        TxtTelefone2 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        TxtEmail = new javax.swing.JTextField();
        TxtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtFormacao = new javax.swing.JTextField();
        TxtCPF = new javax.swing.JFormattedTextField();
        TxtNascimento = new javax.swing.JFormattedTextField();
        ComboArea = new javax.swing.JComboBox<>();
        BtnCancelar = new javax.swing.JButton();
        BtnSalvar = new javax.swing.JButton();
        BtnNovo = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        TxtCidade = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        TxtCep = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        TxtBairro = new javax.swing.JTextField();
        BtnSair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        TxtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProfissionais = new javax.swing.JTable();
        BtnExcluir = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        BtnSair1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        PainelProfissionais.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        TxtNomeCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Nome Completo: ");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("RG: ");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("CPF: ");

        TxtRG.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Data de nascimento:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setText("Contato Principal:");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setText("Contato:");

        try {
            javax.swing.text.MaskFormatter mascTelefone = new javax.swing.text.MaskFormatter("(**)* ####-####");
            mascTelefone.setValidCharacters("0123456789 ");
            TxtTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascTelefone));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefone1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            javax.swing.text.MaskFormatter mascTelefone = new javax.swing.text.MaskFormatter("(**)* ####-####");
            mascTelefone.setValidCharacters("0123456789 ");
            TxtTelefone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascTelefone));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefone2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setText("E-mail:");

        TxtEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        TxtId.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Formação:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Área:");

        TxtFormacao.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            TxtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCPF.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        try {
            TxtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtNascimento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        ComboArea.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(TxtFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11)
                                            .addComponent(TxtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel2)
                                            .addComponent(TxtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(ComboArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)))
                                    .addComponent(jLabel16)
                                    .addComponent(TxtEmail)
                                    .addComponent(TxtNomeCompleto))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnSalvar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSalvar.setText("SALVAR");
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnNovo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnNovo.setText("NOVO");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Complementares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setText("Endereço:");

        TxtEndereco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel20.setText("Cidade: ");

        TxtCidade.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel21.setText("CEP:");

        try {
            TxtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCep.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCepActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel22.setText("Número:");

        TxtNumero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel24.setText("Bairro:");

        TxtBairro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(263, 263, 263))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(TxtCidade)
                                .addGap(18, 18, 18)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnSair.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair.setText("SAIR");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar)
                    .addComponent(BtnSalvar)
                    .addComponent(BtnNovo)
                    .addComponent(BtnSair))
                .addContainerGap())
        );

        PainelProfissionais.addTab("    Cadastrar    ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(250, 250, 250));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setText("Nome:");

        TxtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtPesquisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtPesquisaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel18)
                .addGap(45, 45, 45)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        TblProfissionais.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
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
        TblProfissionais.getTableHeader().setReorderingAllowed(false);
        TblProfissionais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProfissionaisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblProfissionais);

        BtnExcluir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnExcluir.setText("EXCLUIR");
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        BtnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnAlterar.setText("ALTERAR");
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        BtnSair1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSair1.setText("SAIR");
        BtnSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSair1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(BtnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSair1)
                    .addComponent(BtnExcluir)
                    .addComponent(BtnAlterar))
                .addContainerGap())
        );

        PainelProfissionais.addTab("    Consultar    ", jPanel3);

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel8.setText("PROFISSIONAIS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PainelProfissionais, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelProfissionais, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void TxtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCepActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
       AtivarCampos();
       LimparCampos();
       PrepararNovo();
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro ? Isso irá apagar todos os dados ja inseridos. ");
        if(confirma == 0){
            DesativarCampos();
            LimparCampos();
            PrepararCancelarSalvar();
        }    
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSair1ActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
       profissional = new Profissional();
        
       // Verificações de campos obrigatorios
        if(TxtNomeCompleto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campo Nome Completo obrigatorio !","Aviso", JOptionPane.INFORMATION_MESSAGE);
            TxtNomeCompleto.requestFocus();
            return;
        }
        if(TxtNascimento.getText().equals("  /  /    ")){
            JOptionPane.showMessageDialog(null, "Campo Data de nascimento obrigatorio !","Aviso", JOptionPane.INFORMATION_MESSAGE);
            TxtNascimento.requestFocus();
            return;
        }
        if(TxtRG.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campo RG obrigatorio !","Aviso", JOptionPane.INFORMATION_MESSAGE);
            TxtRG.requestFocus();
            return;
        }
        if(TxtCPF.getText().equals("   .   .   -  ")){
            JOptionPane.showMessageDialog(null, "Campo CPF obrigatorio !","Aviso", JOptionPane.INFORMATION_MESSAGE);
            TxtCPF.requestFocus();
            return;
        }
        if(TxtTelefone1.getText().equals("(  )     -    ")){
            JOptionPane.showMessageDialog(null, "Necessário um telefone de contato !","Aviso", JOptionPane.INFORMATION_MESSAGE);
            TxtTelefone1.requestFocus();
            return;
        }
        
        //Validação do CPF
        Boolean ValidacaoCPF = ValidaCPF.isCPF(TxtCPF.getText());
        if(ValidacaoCPF == false){
            JOptionPane.showMessageDialog(null, "CPF invalido !","Erro", JOptionPane.WARNING_MESSAGE);
            TxtCPF.requestFocus();
            return;
        }
        //Iniciação da validão de data verdadeira;
        Boolean ValidacaoData = ValidaData.ValidarData(TxtNascimento.getText());
        if(ValidacaoData == false){
            JOptionPane.showMessageDialog(null, "Data Invalida. Digite uma data verdadeira!","Erro",JOptionPane.WARNING_MESSAGE);
            TxtNascimento.requestFocus();
            return;
        }
           
        if(!TxtEmail.getText().isEmpty()){
             //Validao Email
            Boolean ValidaoEmail = ValidaEmail.ValidarEmail(TxtEmail.getText());
            if(ValidaoEmail == false){
               JOptionPane.showMessageDialog(null, "Email invalido!","Erro",JOptionPane.WARNING_MESSAGE);
               TxtEmail.requestFocus();
               return;
            }
        }
        
       if(TxtId.getText().isEmpty()){
           //Pega os dados do combobox e salva no objeto
           Area area = (Area) ComboArea.getSelectedItem();
           //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
           profissional.setNome(TxtNomeCompleto.getText());
           profissional.setNascimento(TxtNascimento.getText());
           profissional.setRg(TxtRG.getText());
           profissional.setCpf(TxtCPF.getText());
           profissional.setTelefone1(TxtTelefone1.getText());
           profissional.setTelefone2(TxtTelefone2.getText());
           profissional.setFormacao(TxtFormacao.getText());
           profissional.setIdArea(area.getId());
           profissional.setEmail(TxtEmail.getText());
           profissional.setCep(TxtCep.getText());
           profissional.setCidade(TxtCidade.getText());
           profissional.setBairro(TxtBairro.getText());
           profissional.setRua(TxtEndereco.getText());
           profissional.setNumero(TxtNumero.getText());
           
            try {
                profissionaldao.Salvar(profissional);
                JOptionPane.showMessageDialog(null, "Profissional cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ProfissionaisView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
       }else{
           //Pega os dados do combobox e salva no objeto
           Area area = (Area) ComboArea.getSelectedItem();
           //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
           profissional.setId(Integer.parseInt(TxtId.getText()));
           profissional.setNome(TxtNomeCompleto.getText());
           profissional.setNascimento(TxtNascimento.getText());
           profissional.setRg(TxtRG.getText());
           profissional.setCpf(TxtCPF.getText());
           profissional.setTelefone1(TxtTelefone1.getText());
           profissional.setTelefone2(TxtTelefone2.getText());
           profissional.setFormacao(TxtFormacao.getText());
           profissional.setIdArea(area.getId());
           profissional.setEmail(TxtEmail.getText());
           profissional.setCep(TxtCep.getText());
           profissional.setCidade(TxtCidade.getText());
           profissional.setBairro(TxtBairro.getText());
           profissional.setRua(TxtEndereco.getText());
           profissional.setNumero(TxtNumero.getText());
           
           try {
               profissionaldao.Alterar(profissional);
               JOptionPane.showMessageDialog(null, "Profissional alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           } catch (SQLException ex) {
               Logger.getLogger(ProfissionaisView.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       } 
        atualizaTabelaProfissional();
        DesativarCampos();
        LimparCampos();
        PrepararCancelarSalvar();
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void TblProfissionaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProfissionaisMouseClicked
        LimparCampos();
        profissional = new Profissional();
       
        profissional.setIdArea(Integer.parseInt(TblProfissionais.getValueAt(TblProfissionais.getSelectedRow(),6).toString()));
      
        try {
            ComboArea.removeAllItems();
            preencherComboSelecionado(profissional);
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionaisView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TxtId.setText(TblProfissionais.getValueAt(TblProfissionais.getSelectedRow(),0).toString());
        String identificador = TxtId.getText();
        int id = Integer.parseInt(identificador);
        
        try {
            profissional = profissionaldao.Buscar(id);
        } catch (SQLException ex) {
            Logger.getLogger(ProfissionaisView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TblProfissionais.getTableHeader().setReorderingAllowed(false);
        TxtNomeCompleto.setText(profissional.getNome());
        TxtNascimento.setText(profissional.getNascimento());
        TxtRG.setText(profissional.getRg());
        TxtCPF.setText(profissional.getCpf());
        TxtTelefone1.setText(profissional.getTelefone1());
        TxtTelefone2.setText(profissional.getTelefone2());
        TxtFormacao.setText(profissional.getFormacao());
        
        TxtEmail.setText(profissional.getEmail());
        TxtCep.setText(profissional.getCep());
        TxtCidade.setText(profissional.getCidade());
        TxtBairro.setText(profissional.getBairro());
        TxtEndereco.setText(profissional.getRua());
        TxtNumero.setText(profissional.getNumero());
        
        BtnAlterar.setEnabled(true);
        BtnExcluir.setEnabled(true);
        
    }//GEN-LAST:event_TblProfissionaisMouseClicked

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AtivarCampos();
        PrepararNovo();
        PainelProfissionais.setSelectedIndex(0);
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
       if(TxtId.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Selecione um dado para exlui-lo!","Aviso", JOptionPane.INFORMATION_MESSAGE);
       }
       else{
           profissional.setId(Integer.parseInt(TxtId.getText()));
           int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ TxtNomeCompleto.getText());
           if(confirma == 0){
               
               try {
                   profissionaldao.Excluir(profissional);
                   JOptionPane.showMessageDialog(null, "Profissional excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                   LimparCampos();
               } catch (SQLException ex) {
                   Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
               }
                 atualizaTabelaProfissional();
                BtnExcluir.setEnabled(false);
                BtnAlterar.setEnabled(false);
           }
       }
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void TxtPesquisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtPesquisaCaretUpdate
        listaprofissional = null;
        if(TxtPesquisa.getText().equals("")){
            atualizaTabelaProfissional();
        }
        else{
            try {
                listaprofissional = profissionaldao.BuscarNome(TxtPesquisa.getText());
                if(listaprofissional == null){
                    JOptionPane.showMessageDialog(null, "Nenhum profissional encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaProfissional();
                }else{
                    atualizaTabelaProfissionalBusca();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_TxtPesquisaCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSair1;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JComboBox<Object> ComboArea;
    private javax.swing.JTabbedPane PainelProfissionais;
    private javax.swing.JTable TblProfissionais;
    private javax.swing.JTextField TxtBairro;
    private javax.swing.JFormattedTextField TxtCPF;
    private javax.swing.JFormattedTextField TxtCep;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtFormacao;
    private javax.swing.JTextField TxtId;
    private javax.swing.JFormattedTextField TxtNascimento;
    private javax.swing.JTextField TxtNomeCompleto;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtRG;
    private javax.swing.JFormattedTextField TxtTelefone1;
    private javax.swing.JFormattedTextField TxtTelefone2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
