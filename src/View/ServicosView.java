/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AreaDAO;
import DAO.ServicoDAO;
import Model.Area;
import Model.Funcionario;
import Model.Servico;
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
public class ServicosView extends javax.swing.JInternalFrame {

    Servico servico = new Servico();
    ServicoDAO servicodao = new ServicoDAO();
    List<Servico> listaservico = new ArrayList<>();
    
    Area area = new Area();
    AreaDAO areadao = new AreaDAO();
    List<Area> listaarea = new ArrayList<>();
    
    public ServicosView(Funcionario funci){
        initComponents();   
        jDArea.setSize(721,311);
        PainelServico.setUI(new  MetalTabbedPaneUI());
        BtnSair1.setUI(new WindowsButtonUI());
        BtnAlterar.setUI(new WindowsButtonUI());
        BtnCancelar.setUI(new WindowsButtonUI());
        BtnExcluir.setUI(new WindowsButtonUI());
        BtnNovo.setUI(new WindowsButtonUI());
        BtnSair.setUI(new WindowsButtonUI());
        BtnSalvar.setUI(new WindowsButtonUI());
        BtnNovaArea.setUI(new WindowsButtonUI());
        TblServico.setUI(new BasicTableUI());
        
        if(funci.getNivel() == false){
            BtnExcluir.setVisible(false);
        }
        
        TxtId.setVisible(false);
        PainelServico.setSelectedIndex(1);
        atualizaTabelaServico();
        DesativarCampos();
        PrepararCancelarSalvar();
        try {
            preencherCombo();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //jDlog
        BtnAlterarJD.setUI(new WindowsButtonUI());
        BtnCancelarJD.setUI(new WindowsButtonUI());
        BtnExcluirJD.setUI(new WindowsButtonUI());
        BtnNovoJD.setUI(new WindowsButtonUI());
        BtnSalvarJD.setUI(new WindowsButtonUI());
        TblArea.setUI(new BasicTableUI());
        TxtIdArea.setVisible(false);
        DesativarCampo();
        PrepararCancelarSalvarArea();
        atualizaTableArea();
    }
    
    public void preencherCombo() throws SQLException{
        
        for(Area a: areadao.listaTodos()){
            ComboArea.addItem(a);
        }
        
    }
    public void preencherComboSelecionado(Servico area) throws SQLException{
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
    
    public void atualizaTableArea(){
        area = new Area();
        
        try {
            listaarea = areadao.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String dados[][] = new String[listaarea.size()][2];
            int i = 0;
            for (Area area : listaarea) {
                dados[i][0] = String.valueOf(area.getId());
                dados[i][1] = area.getNome();                     
                i++;
            }
        
        String tituloColuna[] = {"Id", "Área"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblArea.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            
            TblArea.getColumnModel().getColumn(0).setMaxWidth(0);
            TblArea.getColumnModel().getColumn(0).setMinWidth(0);
            TblArea.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblArea.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            TblArea.setRowHeight(35);
            TblArea.updateUI();
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
    
    public void atualizaTabelaServicoBusca(){
        servico = new Servico();
       
        
        String dados[][] = new String[listaservico.size()][4];
            int i = 0;
            for (Servico servico : listaservico) {
                dados[i][0] = String.valueOf(servico.getId());
                dados[i][1] = servico.getDescricao();
                for(Area area: listaarea){    
                    if(servico.getIdArea() == area.getId()){
                         dados[i][2] = String.valueOf(area.getNome());
                    }}
                dados[i][3] = servico.getTempo();
                i++;
            }
            
            String tituloColuna[] = {"Id", "Descrição", "Área", "Tempo estimado"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            TblServico.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            TblServico.getColumnModel().getColumn(0).setMaxWidth(0);
            TblServico.getColumnModel().getColumn(0).setMinWidth(0);
            TblServico.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            TblServico.getColumnModel().getColumn(1).setPreferredWidth(350);
            TblServico.getColumnModel().getColumn(2).setPreferredWidth(150);
            TblServico.getColumnModel().getColumn(3).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            TblServico.getColumnModel().getColumn(3).setCellRenderer(centralizado);
            TblServico.setRowHeight(35);
            TblServico.updateUI();
    }
    
    public void DesativarCampo(){
        TxtNomeArea.setEnabled(false);
    }
    
    public void DesativarCampos(){
       TxtDescricao.setEnabled(false);
       ComboArea.setEnabled(false);
       TxtTempo.setEnabled(false);
    }
    
    public void AtivarCampo(){
        TxtNomeArea.setEnabled(true);
    }
    
    public void AtivarCampos(){
       TxtDescricao.setEnabled(true);
       ComboArea.setEnabled(true);
       TxtTempo.setEnabled(true);
    }
    
    public void PrepararNovoArea(){
        BtnAlterarJD.setEnabled(false);
        BtnCancelarJD.setEnabled(true);
        BtnExcluirJD.setEnabled(false);
        BtnSalvarJD.setEnabled(true);
        BtnNovoJD.setEnabled(false);
        TblArea.setEnabled(false);
        TblArea.clearSelection();
    }
    
    public void PrepararNovo(){
        BtnSair.setEnabled(false);
        BtnSair1.setEnabled(false);
        BtnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
        BtnNovo.setEnabled(false);
        BtnCancelar.setEnabled(true);
        BtnSalvar.setEnabled(true);
        BtnNovaArea.setEnabled(false);
        TblServico.setEnabled(false);
        TblServico.clearSelection();
    }
    
    public void PrepararCancelarSalvarArea(){
        BtnAlterarJD.setEnabled(false);
        BtnCancelarJD.setEnabled(false);
        BtnExcluirJD.setEnabled(false);
        BtnSalvarJD.setEnabled(false);
        BtnNovoJD.setEnabled(true);  
        TblArea.setEnabled(true);
    }
    
    public void PrepararCancelarSalvar(){
        BtnNovo.setEnabled(true);
        BtnAlterar.setEnabled(false);
        BtnCancelar.setEnabled(false);
        BtnSalvar.setEnabled(false);
        TblServico.setEnabled(true);
        BtnExcluir.setEnabled(false);
        BtnSair.setEnabled(true);
        BtnSair1.setEnabled(true);
        BtnNovaArea.setEnabled(true);
    }
    
    public void LimparCampo(){
        TxtNomeArea.setText("");
    }
    
    public void LimparCampos(){
        TxtDescricao.setText("");
        ComboArea.setSelectedIndex(0);
        TxtTempo.setText("");
        TxtId.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDArea = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblArea = new javax.swing.JTable();
        BtnAlterarJD = new javax.swing.JButton();
        BtnSalvarJD = new javax.swing.JButton();
        BtnExcluirJD = new javax.swing.JButton();
        BtnCancelarJD = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtNomeArea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BtnNovoJD = new javax.swing.JButton();
        TxtIdArea = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PainelServico = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtTempo = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        ComboArea = new javax.swing.JComboBox<>();
        BtnNovaArea = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnSalvar = new javax.swing.JButton();
        BtnNovo = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblServico = new javax.swing.JTable();
        BtnExcluir = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TxtPesquisa = new javax.swing.JTextField();
        BtnSair1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jDArea.setBackground(new java.awt.Color(255, 255, 255));
        jDArea.setMinimumSize(new java.awt.Dimension(721, 311));
        jDArea.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel6.setMaximumSize(new java.awt.Dimension(721, 311));
        jPanel6.setMinimumSize(new java.awt.Dimension(721, 311));
        jPanel6.setPreferredSize(new java.awt.Dimension(721, 311));

        TblArea.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        TblArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Título 1"
            }
        ));
        TblArea.setRowHeight(24);
        TblArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblAreaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblArea);

        BtnAlterarJD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnAlterarJD.setText("ALTERAR");
        BtnAlterarJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarJDActionPerformed(evt);
            }
        });

        BtnSalvarJD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnSalvarJD.setText("SALVAR");
        BtnSalvarJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarJDActionPerformed(evt);
            }
        });

        BtnExcluirJD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnExcluirJD.setText("EXCLUIR");
        BtnExcluirJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirJDActionPerformed(evt);
            }
        });

        BtnCancelarJD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnCancelarJD.setText("CANCELAR");
        BtnCancelarJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarJDActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");

        TxtNomeArea.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel5.setText("Área");

        BtnNovoJD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnNovoJD.setText("NOVO");
        BtnNovoJD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoJDActionPerformed(evt);
            }
        });

        TxtIdArea.setEnabled(false);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/fechar.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(TxtNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(BtnNovoJD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCancelarJD)
                                .addGap(18, 18, 18)
                                .addComponent(BtnSalvarJD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(BtnExcluirJD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAlterarJD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(TxtIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(TxtIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnNovoJD)
                                .addComponent(BtnCancelarJD)
                                .addComponent(BtnSalvarJD))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnAlterarJD)
                                .addComponent(BtnExcluirJD))))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(TxtNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDAreaLayout = new javax.swing.GroupLayout(jDArea.getContentPane());
        jDArea.getContentPane().setLayout(jDAreaLayout);
        jDAreaLayout.setHorizontalGroup(
            jDAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDAreaLayout.setVerticalGroup(
            jDAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        PainelServico.setBackground(new java.awt.Color(255, 255, 255));
        PainelServico.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do serviço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Descrição:");

        TxtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Tempo estimado:");

        try {
            TxtTempo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTempo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Área:");

        TxtId.setEnabled(false);

        ComboArea.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        BtnNovaArea.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BtnNovaArea.setText("Adicionar nova Área");
        BtnNovaArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovaAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(TxtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319)
                        .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(ComboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNovaArea, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtDescricao))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ComboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnNovaArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSair)
                    .addComponent(BtnNovo)
                    .addComponent(BtnSalvar)
                    .addComponent(BtnCancelar))
                .addGap(10, 10, 10))
        );

        PainelServico.addTab("    Cadastrar    ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        TblServico.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
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
        TblServico.getTableHeader().setReorderingAllowed(false);
        TblServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblServicoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblServico);

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

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Descrição:");

        TxtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        TxtPesquisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtPesquisaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(17, Short.MAX_VALUE))
        );

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(BtnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSair1)
                    .addComponent(BtnAlterar)
                    .addComponent(BtnExcluir))
                .addGap(29, 29, 29))
        );

        PainelServico.addTab("    Consultar    ", jPanel3);

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel4.setText("SERVIÇOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PainelServico))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(PainelServico, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
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

    private void BtnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSair1ActionPerformed

    private void TxtPesquisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtPesquisaCaretUpdate
        listaservico = null;
        if(TxtPesquisa.getText().equals("")){
            atualizaTabelaServico();
        }
        else{
            try {
                listaservico = servicodao.BuscarDescricao(TxtPesquisa.getText());
                if(listaservico == null){
                    JOptionPane.showMessageDialog(null, "Nenhum serviço encontrado!","Aviso", JOptionPane.INFORMATION_MESSAGE);
                    atualizaTabelaServico();
                }else{
                    atualizaTabelaServicoBusca();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TxtPesquisaCaretUpdate

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        AtivarCampos();
        PrepararNovo();
        PainelServico.setSelectedIndex(0);
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
        servico = new Servico();

        if(TxtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um dado para exlui-lo!","Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            servico.setId(Integer.parseInt(TxtId.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ TxtDescricao.getText());
            if(confirma == 0){

                try {
                    servicodao.Excluir(servico);
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    LimparCampos();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }
                atualizaTabelaServico();
                BtnExcluir.setEnabled(false);
                BtnAlterar.setEnabled(false);
            }
        }

    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void TblServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblServicoMouseClicked
        LimparCampos();
        
        servico = new Servico();
        servico.setIdArea(Integer.parseInt(TblServico.getValueAt(TblServico.getSelectedRow(),4).toString()));
         
        try {
            preencherComboSelecionado(servico);
        } catch (SQLException ex) {
            Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TxtId.setText(TblServico.getValueAt(TblServico.getSelectedRow(),0).toString());
        TxtDescricao.setText(TblServico.getValueAt(TblServico.getSelectedRow(),1).toString());
        TxtTempo.setText(TblServico.getValueAt(TblServico.getSelectedRow(),3).toString());
        
        BtnAlterar.setEnabled(true);
        BtnExcluir.setEnabled(true);
    }//GEN-LAST:event_TblServicoMouseClicked

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
        try {
            ComboArea.removeAllItems();
            preencherCombo();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        PrepararNovo();
        AtivarCampos();
        LimparCampos();
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        servico = new Servico();
        if(TxtId.getText().isEmpty()){
            //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            servico.setDescricao(TxtDescricao.getText());
            Area area = (Area) ComboArea.getSelectedItem();
            servico.setIdArea(area.getId());
            servico.setTempo(TxtTempo.getText());

            try {
                servicodao.Salvar(servico);
                JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
            servico.setId(Integer.parseInt(TxtId.getText()));
            servico.setDescricao(TxtDescricao.getText());
            Area area = (Area) ComboArea.getSelectedItem();
            servico.setIdArea(area.getId());
            servico.setTempo(TxtTempo.getText());

            try {
                servicodao.Alterar(servico);
                JOptionPane.showMessageDialog(null, "Serviço alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        atualizaTabelaServico();
        DesativarCampos();
        LimparCampos();
        PrepararCancelarSalvar();

    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro ? Isso irá apagar todos os dados ja inseridos. ");
        if(confirma == 0){
            LimparCampos();
            DesativarCampos();
            PrepararCancelarSalvar();
        }    
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnSalvarJDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarJDActionPerformed
       area = new Area();
       
       if(TxtIdArea.getText().isEmpty()){
            area.setNome(TxtNomeArea.getText());

             try {
                 areadao.Salvar(area);
                 JOptionPane.showMessageDialog(null, "Área cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
             } catch (SQLException ex) {
                 Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
       else{
           area.setId(Integer.parseInt(TxtIdArea.getText()));
           area.setNome(TxtNomeArea.getText());

             try {
                 areadao.Alterar(area);
                 JOptionPane.showMessageDialog(null, "Área alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
             } catch (SQLException ex) {
                 Logger.getLogger(ServicosView.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
       
        atualizaTableArea();
        DesativarCampo();
        LimparCampo();
        PrepararCancelarSalvarArea();
    }//GEN-LAST:event_BtnSalvarJDActionPerformed

    private void BtnNovaAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovaAreaActionPerformed
        LimparCampo();
        DesativarCampo();
        PrepararCancelarSalvarArea();
        jDArea.setVisible(true);
        jDArea.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnNovaAreaActionPerformed

    private void BtnNovoJDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoJDActionPerformed
         PrepararNovoArea();
         LimparCampo();
         AtivarCampo();
    }//GEN-LAST:event_BtnNovoJDActionPerformed

    private void BtnCancelarJDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarJDActionPerformed
        LimparCampo();
        DesativarCampo();
        PrepararCancelarSalvarArea();
    }//GEN-LAST:event_BtnCancelarJDActionPerformed

    private void BtnExcluirJDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirJDActionPerformed
         area = new Area();

        if(TxtIdArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um dado para exlui-lo!","Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            area.setId(Integer.parseInt(TxtIdArea.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ TxtDescricao.getText());
            if(confirma == 0){

                try {
                    areadao.Excluir(area);
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    LimparCampo();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }
                LimparCampo();
                atualizaTableArea();
                BtnExcluirJD.setEnabled(false);
                BtnAlterarJD.setEnabled(false);
                
            }
        }
    }//GEN-LAST:event_BtnExcluirJDActionPerformed

    private void TblAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblAreaMouseClicked
        LimparCampo();

        TxtIdArea.setText(TblArea.getValueAt(TblArea.getSelectedRow(),0).toString());
        TxtNomeArea.setText(TblArea.getValueAt(TblArea.getSelectedRow(),1).toString());
        
        BtnAlterarJD.setEnabled(true);
        BtnExcluirJD.setEnabled(true);
    }//GEN-LAST:event_TblAreaMouseClicked

    private void BtnAlterarJDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarJDActionPerformed
        AtivarCampo();
        PrepararNovoArea();
    }//GEN-LAST:event_BtnAlterarJDActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
         jDArea.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAlterarJD;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCancelarJD;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExcluirJD;
    private javax.swing.JButton BtnNovaArea;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnNovoJD;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSair1;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JButton BtnSalvarJD;
    private javax.swing.JComboBox<Object> ComboArea;
    private javax.swing.JTabbedPane PainelServico;
    private javax.swing.JTable TblArea;
    private javax.swing.JTable TblServico;
    private javax.swing.JTextField TxtDescricao;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtIdArea;
    private javax.swing.JTextField TxtNomeArea;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JFormattedTextField TxtTempo;
    private javax.swing.JDialog jDArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
