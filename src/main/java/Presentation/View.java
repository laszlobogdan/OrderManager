package Presentation;
import DAO.*;
import Model.Bill;
import Model.Client;
import Model.Product;
import Model.This_Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class View extends JFrame{
       public View() {
           setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           setSize(300, 200);
           JPanel panel = new JPanel();
           panel.setLayout(new FlowLayout());
           JButton clients = new JButton("Clients");
           JButton product = new JButton("Products");
           JButton order = new JButton("Orders");
           JButton bill = new JButton("Bill");
           clients.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFrame newFrame = new JFrame("CLIENTS");
                   JPanel panelClient = new JPanel();
                   newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   newFrame.setSize(700, 400);
                   ClientDAO c = new ClientDAO();
                   DefaultTableModel model = new DefaultTableModel();
                   c.FillTable(model);
                   JTable table = new JTable(model);
                   JScrollPane scrollPane = new JScrollPane(table);
                   JButton add = new JButton("Add");
                   add.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame addFrame = new JFrame("Add Client");
                           addFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel name = new JLabel("NAME");
                           JLabel adrr = new JLabel("Address");
                           JLabel email = new JLabel("EMAIL");
                           JLabel age = new JLabel("AGE");
                           JTextField name_ = new JTextField(10);
                           JTextField adrr_ = new JTextField(10);
                           JTextField email_ = new JTextField(10);
                           JTextField age_ = new JTextField(10);
                           JButton addClient = new JButton("ADD");
                           addClient.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ClientDAO clientDAO = new ClientDAO();
                                   Client newClient = new Client(name_.getText(), adrr_.getText(), email_.getText(), Integer.parseInt(age_.getText()));
                                   clientDAO.insert(newClient);
                               }
                           });
                           jPanel.add(name);
                           jPanel.add(name_);
                           jPanel.add(adrr);
                           jPanel.add(adrr_);
                           jPanel.add(email);
                           jPanel.add(email_);
                           jPanel.add(age);
                           jPanel.add(age_);
                           jPanel.add(addClient);
                           addFrame.add(jPanel);
                           addFrame.setLocationRelativeTo(null);
                           addFrame.setVisible(true);
                       }
                   });
                   JButton update = new JButton("Update");
                   update.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame updateFrame = new JFrame("Update Client");
                           updateFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel id = new JLabel("ID");
                           JLabel name = new JLabel("NAME");
                           JLabel adrr = new JLabel("Address");
                           JLabel email = new JLabel("EMAIL");
                           JLabel age = new JLabel("AGE");
                           JTextField id_ = new JTextField(10);
                           JTextField name_ = new JTextField(10);
                           JTextField adrr_ = new JTextField(10);
                           JTextField email_ = new JTextField(10);
                           JTextField age_ = new JTextField(10);
                           JButton updateClient = new JButton("UPDATE");
                           updateClient.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ClientDAO clientDAO = new ClientDAO();
                                   Client client = new Client(name_.getText(), adrr_.getText(), email_.getText(), Integer.parseInt(age_.getText()));
                                   clientDAO.update(Integer.parseInt(id_.getText()), client);
                               }
                           });
                           jPanel.add(id);
                           jPanel.add(id_);
                           jPanel.add(name);
                           jPanel.add(name_);
                           jPanel.add(adrr);
                           jPanel.add(adrr_);
                           jPanel.add(email);
                           jPanel.add(email_);
                           jPanel.add(age);
                           jPanel.add(age_);
                           jPanel.add(updateClient);
                           updateFrame.add(jPanel);
                           updateFrame.setLocationRelativeTo(null);
                           updateFrame.setVisible(true);
                       }
                   });
                   JButton delete = new JButton("Delete");
                   delete.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame deleteFrame = new JFrame("Delete Client");
                           deleteFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel id = new JLabel("ID");
                           JTextField id_ = new JTextField(10);
                           JButton deleteClient = new JButton("DELETE");
                           deleteClient.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ClientDAO clientDAO = new ClientDAO();
                                   clientDAO.delete(Integer.parseInt(id_.getText()));
                               }
                           });
                           jPanel.add(id);
                           jPanel.add(id_);
                           jPanel.add(deleteClient);
                           deleteFrame.add(jPanel);
                           deleteFrame.setLocationRelativeTo(null);
                           deleteFrame.setVisible(true);
                       }
                   });
                   panelClient.add(scrollPane);
                   panelClient.add(add);
                   panelClient.add(update);
                   panelClient.add(delete);
                   newFrame.add(panelClient);
                   newFrame.setLocationRelativeTo(null);
                   newFrame.setVisible(true);
               }
           });

           product.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFrame newFrame = new JFrame("PRODUCTS");
                   JPanel panelProduct = new JPanel();
                   newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   newFrame.setSize(700, 400);
                   ProductDAO p = new ProductDAO();
                   DefaultTableModel model = new DefaultTableModel();
                   p.FillTable(model);
                   JTable table = new JTable(model);
                   JScrollPane scrollPane = new JScrollPane(table);
                   JButton add = new JButton("Add");
                   add.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame addFrame = new JFrame("Add Product");
                           addFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel name = new JLabel("NAME");
                           JLabel quantity = new JLabel("QUANTITY");
                           JLabel price = new JLabel("PRICE");
                           JTextField name_ = new JTextField(10);
                           JTextField quantity_ = new JTextField(10);
                           JTextField price_ = new JTextField(10);
                           JButton addProduct = new JButton("ADD");
                           addProduct.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ProductDAO productDAO = new ProductDAO();
                                   Product newProduct = new Product(name_.getText(), Integer.parseInt(quantity_.getText()), Integer.parseInt(price_.getText()));
                                   productDAO.insert(newProduct);
                               }
                           });
                           jPanel.add(name);
                           jPanel.add(name_);
                           jPanel.add(quantity);
                           jPanel.add(quantity_);
                           jPanel.add(price);
                           jPanel.add(price_);
                           jPanel.add(addProduct);
                           addFrame.add(jPanel);
                           addFrame.setLocationRelativeTo(null);
                           addFrame.setVisible(true);
                       }
                   });
                   JButton update = new JButton("Update");
                   update.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame updateFrame = new JFrame("Update Product");
                           updateFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel id = new JLabel("ID");
                           JLabel name = new JLabel("NAME");
                           JLabel quantity = new JLabel("QUANTITY");
                           JLabel price = new JLabel("PRICE");
                           JTextField id_ = new JTextField(10);
                           JTextField name_ = new JTextField(10);
                           JTextField quantity_ = new JTextField(10);
                           JTextField price_ = new JTextField(10);
                           JButton updateProduct = new JButton("UPDATE");
                           updateProduct.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ProductDAO productDAO = new ProductDAO();
                                   Product product = new Product(name_.getText(), Integer.parseInt(quantity_.getText()), Integer.parseInt(price_.getText()));
                                   productDAO.update(Integer.parseInt(id_.getText()), product);
                               }
                           });
                           jPanel.add(id);
                           jPanel.add(id_);
                           jPanel.add(name);
                           jPanel.add(name_);
                           jPanel.add(quantity);
                           jPanel.add(quantity_);
                           jPanel.add(price);
                           jPanel.add(price_);
                           jPanel.add(updateProduct);
                           updateFrame.add(jPanel);
                           updateFrame.setLocationRelativeTo(null);
                           updateFrame.setVisible(true);
                       }
                   });
                   JButton delete = new JButton("Delete");
                   delete.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame deleteFrame = new JFrame("Delete Product");
                           deleteFrame.setSize(500, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel id = new JLabel("ID");
                           JTextField id_ = new JTextField(10);
                           JButton deleteProduct = new JButton("DELETE");
                           deleteProduct.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   ProductDAO productDAO = new ProductDAO();
                                   productDAO.delete(Integer.parseInt(id_.getText()));
                               }
                           });
                           jPanel.add(id);
                           jPanel.add(id_);
                           jPanel.add(deleteProduct);
                           deleteFrame.add(jPanel);
                           deleteFrame.setLocationRelativeTo(null);
                           deleteFrame.setVisible(true);
                       }
                   });
                   panelProduct.add(scrollPane);
                   panelProduct.add(add);
                   panelProduct.add(update);
                   panelProduct.add(delete);
                   newFrame.add(panelProduct);
                   newFrame.setLocationRelativeTo(null);
                   newFrame.setVisible(true);
               }
           });
           order.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFrame newFrame = new JFrame("ORDERS");
                   JPanel panelOrder = new JPanel();
                   newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   newFrame.setSize(700, 400);
                   This_OrderDAO o = new This_OrderDAO();
                   DefaultTableModel model = new DefaultTableModel();
                   o.FillTable(model);
                   JTable table = new JTable(model);
                   JScrollPane scrollPane = new JScrollPane(table);
                   JButton order = new JButton("Order");
                   order.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           JFrame orderFrame = new JFrame("Order Product");
                           orderFrame.setSize(550, 300);
                           JPanel jPanel = new JPanel(new FlowLayout());
                           JLabel idClient = new JLabel("ID_CLIENT");
                           JLabel idProdus = new JLabel("ID_PRODUS");
                           JLabel quantity = new JLabel("QUANTITY");
                           JComboBox<Client> idClient_ = new JComboBox<>(new ClientDAO().findAll().toArray(new Client[0]));
                           JComboBox<Product> idProdus_ = new JComboBox<>(new ProductDAO().findAll().toArray(new Product[0]));
                           JTextField quantity_ = new JTextField(10);
                           JButton orderProduct = new JButton("ORDER");
                           orderProduct.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   This_OrderDAO orderDAO = new This_OrderDAO();
                                   Client client = (Client) idClient_.getSelectedItem();
                                   Product product = (Product) idProdus_.getSelectedItem();
                                   if ((product.getQuantity() - Integer.parseInt(quantity_.getText())) >= 0) {
                                       int total = product.getPrice() * Integer.parseInt(quantity_.getText());
                                       This_Order thisOrder = new This_Order(client.getId(), product.getId(), total, Integer.parseInt(quantity_.getText()));
                                       orderDAO.insert(thisOrder);
                                       ProductDAO productDAO = new ProductDAO();
                                       product.setQuantity(product.getQuantity() - Integer.parseInt(quantity_.getText()));
                                       productDAO.update(product.getId(), product);
                                       BillDAO billDAO = new BillDAO();
                                       Bill bill = new Bill(thisOrder.getId(), thisOrder.getTotal());
                                       billDAO.insert(bill);
                                   } else {
                                       JFrame errFrame = new JFrame();
                                       errFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                       errFrame.setSize(200, 100);
                                       JPanel errPanel = new JPanel();
                                       JLabel err = new JLabel("NOT ENOUGH STOCK");
                                       errPanel.add(err);
                                       errFrame.add(errPanel);
                                       errFrame.setLocationRelativeTo(null);
                                       errFrame.setVisible(true);
                                   }
                               }
                           });
                           jPanel.add(idClient);
                           jPanel.add(idClient_);
                           jPanel.add(idProdus);
                           jPanel.add(idProdus_);
                           jPanel.add(quantity);
                           jPanel.add(quantity_);
                           jPanel.add(orderProduct);
                           orderFrame.add(jPanel);
                           orderFrame.setLocationRelativeTo(null);
                           orderFrame.setVisible(true);
                       }
                   });
                   panelOrder.add(scrollPane);
                   panelOrder.add(order);
                   newFrame.add(panelOrder);
                   newFrame.setLocationRelativeTo(null);
                   newFrame.setVisible(true);
               }
           });
           bill.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFrame newFrame = new JFrame("BILL");
                   newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   newFrame.setSize(700, 400);
                   BillDAO b = new BillDAO();
                   DefaultTableModel model = new DefaultTableModel();
                   b.FillTable(model);
                   JTable table = new JTable(model);
                   JScrollPane scrollPane = new JScrollPane(table);
                   newFrame.add(scrollPane);
                   newFrame.setLocationRelativeTo(null);
                   newFrame.setVisible(true);
               }
           });
           panel.add(clients);
           panel.add(product);
           panel.add(order);
           panel.add(bill);
           add(panel);
           setLocationRelativeTo(null);
           setVisible(true);
       }
}