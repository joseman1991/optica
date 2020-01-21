package vistasInicio;

public class VentanaInicial extends javax.swing.JFrame {

    public VentanaInicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        estadoLogin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        Usuario = new javax.swing.JButton();
        articulo = new javax.swing.JButton();
        listaart = new javax.swing.JButton();
        ingreso = new javax.swing.JButton();
        responsable = new javax.swing.JButton();
        egreso = new javax.swing.JButton();
        historial = new javax.swing.JButton();
        escritorio = new javax.swing.JDesktopPane();
        menBusPro = new javax.swing.JMenuBar();
        menArchivo = new javax.swing.JMenu();
        config = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        menUsuario = new javax.swing.JMenu();
        menRegistrar = new javax.swing.JMenuItem();
        menActualizar = new javax.swing.JMenuItem();
        menElimi = new javax.swing.JMenuItem();
        menCamb = new javax.swing.JMenuItem();
        menArticulo = new javax.swing.JMenu();
        menuInsArt = new javax.swing.JMenuItem();
        menActArt = new javax.swing.JMenuItem();
        add_palabra = new javax.swing.JMenuItem();
        menResponsable = new javax.swing.JMenu();
        menAñadirRes = new javax.swing.JMenuItem();
        menActuaRes = new javax.swing.JMenuItem();
        menElimiRes = new javax.swing.JMenuItem();
        menMovimiento = new javax.swing.JMenu();
        menIngreso = new javax.swing.JMenuItem();
        menEgreso = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de registro de optica");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        estadoLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estadoLogin.setForeground(new java.awt.Color(0, 102, 0));
        estadoLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estadoLogin.setText("Entrar al sistema");
        estadoLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sistema de Registro de Óptica");
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        Usuario.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        Usuario.setText("Añadir Usuario");
        Usuario.setToolTipText("Añadir Usuario");
        Usuario.setEnabled(false);
        Usuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Usuario.setIconTextGap(-3);
        Usuario.setName("Usuario"); // NOI18N
        Usuario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Usuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(Usuario);

        articulo.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        articulo.setText("Añadir Lentes");
        articulo.setToolTipText("Añadir Artículo");
        articulo.setEnabled(false);
        articulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        articulo.setIconTextGap(-3);
        articulo.setName("articulo"); // NOI18N
        articulo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        articulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(articulo);

        listaart.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        listaart.setText("Lista de Lentes");
        listaart.setToolTipText("Lista de Artículos");
        listaart.setEnabled(false);
        listaart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        listaart.setIconTextGap(-3);
        listaart.setName("listaart"); // NOI18N
        listaart.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        listaart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(listaart);

        ingreso.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        ingreso.setText("Actualizar Stock");
        ingreso.setToolTipText("Ingresar Stock");
        ingreso.setEnabled(false);
        ingreso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ingreso.setIconTextGap(-3);
        ingreso.setName("ingreso"); // NOI18N
        ingreso.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ingreso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(ingreso);

        responsable.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        responsable.setText("Proveedor o Cliente");
        responsable.setToolTipText("Ingresar Stock");
        responsable.setEnabled(false);
        responsable.setFocusable(false);
        responsable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsable.setIconTextGap(-3);
        responsable.setName("responsable"); // NOI18N
        responsable.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        responsable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(responsable);

        egreso.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        egreso.setText("Ventas");
        egreso.setToolTipText("Ingresar Stock");
        egreso.setEnabled(false);
        egreso.setFocusable(false);
        egreso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        egreso.setIconTextGap(-3);
        egreso.setName("egreso"); // NOI18N
        egreso.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        egreso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(egreso);

        historial.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        historial.setText("Historial deVentas");
        historial.setToolTipText("Ingresar Stock");
        historial.setEnabled(false);
        historial.setFocusable(false);
        historial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        historial.setIconTextGap(-3);
        historial.setName("historial"); // NOI18N
        historial.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        historial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(historial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(estadoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        escritorio.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        escritorio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        escritorio.setAutoscrolls(true);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        menArchivo.setText("Archivo");

        config.setText("Configurar conexión");
        menArchivo.add(config);

        salir.setText("Salir");
        menArchivo.add(salir);

        menBusPro.add(menArchivo);

        menUsuario.setText("Usuarios");

        menRegistrar.setText("Registrar");
        menUsuario.add(menRegistrar);

        menActualizar.setText("Actualizar");
        menUsuario.add(menActualizar);

        menElimi.setText("Eliminar");
        menUsuario.add(menElimi);

        menCamb.setText("Cambiar clave");
        menUsuario.add(menCamb);

        menBusPro.add(menUsuario);

        menArticulo.setText("Artículos");

        menuInsArt.setText("Insertar Artículo");
        menArticulo.add(menuInsArt);

        menActArt.setText("Actualizar Artículo");
        menArticulo.add(menActArt);

        add_palabra.setText("Agregar Balance Inicial");
        menArticulo.add(add_palabra);

        menBusPro.add(menArticulo);

        menResponsable.setText("Responsables");

        menAñadirRes.setText("Añadir");
        menResponsable.add(menAñadirRes);

        menActuaRes.setText("Actualizar");
        menResponsable.add(menActuaRes);

        menElimiRes.setText("Eliminar");
        menResponsable.add(menElimiRes);

        menBusPro.add(menResponsable);

        menMovimiento.setText("Movimientos");

        menIngreso.setText("Ingreso");
        menMovimiento.add(menIngreso);

        menEgreso.setText("Ventas");
        menMovimiento.add(menEgreso);

        menBusPro.add(menMovimiento);

        setJMenuBar(menBusPro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Usuario;
    public javax.swing.JMenuItem add_palabra;
    public javax.swing.JButton articulo;
    public javax.swing.JMenuItem config;
    public javax.swing.JButton egreso;
    public javax.swing.JDesktopPane escritorio;
    public javax.swing.JLabel estadoLogin;
    public javax.swing.JButton historial;
    public javax.swing.JButton ingreso;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JButton listaart;
    public javax.swing.JMenuItem menActArt;
    public javax.swing.JMenuItem menActuaRes;
    public javax.swing.JMenuItem menActualizar;
    public javax.swing.JMenu menArchivo;
    public javax.swing.JMenu menArticulo;
    public javax.swing.JMenuItem menAñadirRes;
    private javax.swing.JMenuBar menBusPro;
    public javax.swing.JMenuItem menCamb;
    public javax.swing.JMenuItem menEgreso;
    public javax.swing.JMenuItem menElimi;
    public javax.swing.JMenuItem menElimiRes;
    public javax.swing.JMenuItem menIngreso;
    public javax.swing.JMenu menMovimiento;
    public javax.swing.JMenuItem menRegistrar;
    public javax.swing.JMenu menResponsable;
    public javax.swing.JMenu menUsuario;
    public javax.swing.JMenuItem menuInsArt;
    public javax.swing.JButton responsable;
    public javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
