
package view;

import InventoryConnectionProvider.inventoryConnection;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

//inheritance
public class ManageProd extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageProd.class.getName());

    
    public ManageProd() {
        initComponents();
        loadCategories(); 
        loadProducts();
        
        
    }
   
     private void loadProducts() {
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.setRowCount(0); // clear table before loading

        try (Connection conn = inventoryConnection.getCon()) {
            String sql = "SELECT * FROM product";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

           while (rs.next()) {
        int id = rs.getInt("product_id");
        String name = rs.getString("product_name");
        String category = rs.getString("category_name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        Timestamp updated = rs.getTimestamp("last_updated");

        String formattedDate = (updated != null)
            ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updated)
            : "Not yet updated";

    model.addRow(new Object[]{id, name, quantity, price, category, formattedDate});
}
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database error while loading: " + e.getMessage());
            e.printStackTrace();
        }
    
    }
     private void loadCategories() {
    try (Connection conn = inventoryConnection.getCon()) {
        String sql = "SELECT category_name FROM category";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        jComboBoxCategory.removeAllItems(); // clear old items

        while (rs.next()) {
            jComboBoxCategory.addItem(rs.getString("category_name"));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading categories: " + e.getMessage());
        e.printStackTrace();
    }
       jtable.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = jtable.getSelectedRow();
        if (selectedRow != -1) {
            // Correct column indexes based on your table model
            String productName = jtable.getValueAt(selectedRow, 1).toString(); // Product Name
            String quantity = jtable.getValueAt(selectedRow, 2).toString();   // Quantity
            String price = jtable.getValueAt(selectedRow, 3).toString();      // Price
            String category = jtable.getValueAt(selectedRow, 4).toString();   // Category

            // 🟢 Fill the fields automatically
            txtname.setText(productName);
            txtprice.setText(price);
            txtquantity.setText(quantity);

            // 🟢 Set combo box category automatically
            jComboBoxCategory.setSelectedItem(category);
        }
    }
});
}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnorder = new javax.swing.JButton();
        btnrestockhis = new javax.swing.JButton();
        btnsalesrecord = new javax.swing.JButton();
        BTNCLOSE = new javax.swing.JButton();
        btnaddstock1 = new javax.swing.JButton();
        btnarchived = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxCategory = new javax.swing.JComboBox<>();
        btnadd = new javax.swing.JButton();
        btnaddstock = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jlabelsearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MANAGE PRODUCT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(126, 189, 210));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome, Admin!");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Downloads\\Inventory Management System Images & Icon\\images\\category.png")); // NOI18N
        jButton2.setText(" Manage Product");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnorder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Orders.png"))); // NOI18N
        btnorder.setText("Order");
        btnorder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnorderActionPerformed(evt);
            }
        });

        btnrestockhis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnrestockhis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/View-orders.png"))); // NOI18N
        btnrestockhis.setText("Restock History");
        btnrestockhis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnrestockhis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrestockhisActionPerformed(evt);
            }
        });

        btnsalesrecord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsalesrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/customers.png"))); // NOI18N
        btnsalesrecord.setText("Sales Record");
        btnsalesrecord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsalesrecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalesrecordActionPerformed(evt);
            }
        });

        BTNCLOSE.setBackground(new java.awt.Color(255, 102, 102));
        BTNCLOSE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNCLOSE.setForeground(new java.awt.Color(255, 255, 255));
        BTNCLOSE.setText("Close");
        BTNCLOSE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTNCLOSE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCLOSEActionPerformed(evt);
            }
        });

        btnaddstock1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnaddstock1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/product.png"))); // NOI18N
        btnaddstock1.setText("Add Stock");
        btnaddstock1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnaddstock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddstock1ActionPerformed(evt);
            }
        });

        btnarchived.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnarchived.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/save.png"))); // NOI18N
        btnarchived.setText("Archived Record");
        btnarchived.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnarchived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnarchivedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnrestockhis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnsalesrecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnaddstock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnarchived, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnaddstock1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnorder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnsalesrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnrestockhis, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnarchived, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(BTNCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Product Name");

        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Quantity");

        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Price");

        txtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpriceActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Category");

        jComboBoxCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CannedGoods", "Snacks&candies", "Personal Care", "Beverages", "Household Items" }));
        jComboBoxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoryActionPerformed(evt);
            }
        });

        btnadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnadd.setText("Add Item");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnaddstock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnaddstock.setText("Update");
        btnaddstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddstockActionPerformed(evt);
            }
        });

        btnremove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnremove.setText("Archived");
        btnremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveActionPerformed(evt);
            }
        });

        jtable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Name", "Quantity", "Price (₱)", "Category", "Update"
            }
        ));
        jScrollPane1.setViewportView(jtable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Search:");

        jlabelsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlabelsearchActionPerformed(evt);
            }
        });
        jlabelsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlabelsearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnadd)
                                        .addGap(74, 74, 74)
                                        .addComponent(btnaddstock, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnremove))
                                    .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabelsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnaddstock, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnremove, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ManageProd().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderActionPerformed
        new Purchase().setVisible(true);
    }//GEN-LAST:event_btnorderActionPerformed

    private void btnrestockhisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrestockhisActionPerformed
        new RestockHistory().setVisible(true);
    }//GEN-LAST:event_btnrestockhisActionPerformed

    private void btnsalesrecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalesrecordActionPerformed
        new Sales().setVisible(true);
    }//GEN-LAST:event_btnsalesrecordActionPerformed

    private void BTNCLOSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCLOSEActionPerformed

        setVisible(false);
    }//GEN-LAST:event_BTNCLOSEActionPerformed

    private void txtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceActionPerformed

    private void jComboBoxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoryActionPerformed


    }//GEN-LAST:event_jComboBoxCategoryActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
                                      
    String name = txtname.getText().trim();
    String quantity = txtquantity.getText().trim();
    String price = txtprice.getText().trim();
    String category = jComboBoxCategory.getSelectedItem().toString();

    if (!name.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(this, "Product name must contain letters only.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection con = inventoryConnection.getCon()) {

        // ✅ Step 1: Check for duplicate product name (case-insensitive)
        String checkSql = "SELECT COUNT(*) FROM product WHERE LOWER(product_name) = LOWER(?)";
        PreparedStatement checkPs = con.prepareStatement(checkSql);
        checkPs.setString(1, name);
        ResultSet rsCheck = checkPs.executeQuery();

        if (rsCheck.next() && rsCheck.getInt(1) > 0) {
            JOptionPane.showMessageDialog(this, 
                "A product with the same name already exists!", 
                "Duplicate Product", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Step 2: Continue inserting new product
        String sql = "INSERT INTO product (product_name, category_name, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, category);
        ps.setInt(3, Integer.parseInt(quantity));
        ps.setDouble(4, Double.parseDouble(price));
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) id = rs.getInt(1);

        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.addRow(new Object[]{id, name, quantity, price, category});

        JOptionPane.showMessageDialog(this, "Product added successfully!");
        clearFields(); 

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        e.printStackTrace();
    }



    }//GEN-LAST:event_btnaddActionPerformed

    private void btnaddstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddstockActionPerformed
                                     
    int selectedRow = jtable.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select a product to update.", "Warning", JOptionPane.WARNING_MESSAGE);
    return;
}

// Get input values
String newName = txtname.getText().trim();
String newCategory = (String) jComboBoxCategory.getSelectedItem();
String priceText = txtprice.getText().trim();
String quantityText = txtquantity.getText().trim();

if (newName.isEmpty() || newCategory == null || priceText.isEmpty() || quantityText.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

try {
    double newPrice = Double.parseDouble(priceText);
    int newQuantity = Integer.parseInt(quantityText);

    if (newPrice < 0 || newQuantity < 0) {
        JOptionPane.showMessageDialog(this, "Price and Quantity cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get the product ID from the selected table row
    int productId = (int) jtable.getValueAt(selectedRow, 0);

    // Update the product in the database
    try (Connection con = inventoryConnection.getCon()) {
        String sql = "UPDATE product SET product_name = ?, category_name = ?, price = ?, quantity = ?, last_updated = NOW() WHERE product_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, newName);
        ps.setString(2, newCategory);
        ps.setDouble(3, newPrice);
        ps.setInt(4, newQuantity);
        ps.setInt(5, productId);

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Product updated successfully!");

            // ✅ Clear text fields after update
            txtname.setText("");
            txtprice.setText("");
            txtquantity.setText("");
            jComboBoxCategory.setSelectedIndex(-1); // Clears category selection

            loadProducts(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "No product found with that ID.");
        }
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error updating product: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    e.printStackTrace();
}



    }//GEN-LAST:event_btnaddstockActionPerformed

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
    DefaultTableModel model = (DefaultTableModel) jtable.getModel();
    int selectedRow = jtable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product to archive.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int productId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
    String productName = model.getValueAt(selectedRow, 1).toString();
    int quantity = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
    double price = Double.parseDouble(model.getValueAt(selectedRow, 3).toString());
    String category = model.getValueAt(selectedRow, 4).toString();

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to archive this product?", 
        "Confirm Archive", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection con = inventoryConnection.getCon()) {
            
            // Step 1: Insert the product into the archive table
            String archiveSql = "INSERT INTO archive (product_id, product_name, category_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement archivePs = con.prepareStatement(archiveSql);
            archivePs.setInt(1, productId);
            archivePs.setString(2, productName);
            archivePs.setString(3, category);
            archivePs.setInt(4, quantity);
            archivePs.setDouble(5, price);
            archivePs.executeUpdate();

            // Step 2: Delete it from the main product table
            String deleteSql = "DELETE FROM product WHERE product_id = ?";
            PreparedStatement deletePs = con.prepareStatement(deleteSql);
            deletePs.setInt(1, productId);
            deletePs.executeUpdate();

            // Step 3: Remove row from table model
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Product archived successfully!");
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error archiving product: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    }

    private void clearFields() {
        txtname.setText("");
        txtquantity.setText("");
        txtprice.setText("");
        jComboBoxCategory.setSelectedIndex(-1);


    }//GEN-LAST:event_btnremoveActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed

    }//GEN-LAST:event_txtquantityActionPerformed

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void jlabelsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlabelsearchActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jlabelsearchActionPerformed

    private void jlabelsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlabelsearchKeyReleased
        
        DefaultTableModel ob = (DefaultTableModel) jtable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jtable.setRowSorter(obj);

        // Make the search case-insensitive
        String searchText = jlabelsearch.getText().trim();
        obj.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }//GEN-LAST:event_jlabelsearchKeyReleased

    private void btnaddstock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddstock1ActionPerformed
        new Addstock().setVisible(true);
    }//GEN-LAST:event_btnaddstock1ActionPerformed

    private void btnarchivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarchivedActionPerformed
        new Archived().setVisible(true);
    }//GEN-LAST:event_btnarchivedActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManageProd().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCLOSE;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnaddstock;
    private javax.swing.JButton btnaddstock1;
    private javax.swing.JButton btnarchived;
    private javax.swing.JButton btnorder;
    private javax.swing.JButton btnremove;
    private javax.swing.JButton btnrestockhis;
    private javax.swing.JButton btnsalesrecord;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jlabelsearch;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
