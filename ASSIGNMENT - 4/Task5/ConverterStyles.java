import javax.swing.*;import java.awt.*;import java.awt.event.*;
public class ConverterStyles extends JFrame {JTextField amount=new JTextField(9);JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});JLabel result=new JLabel("Result");
 double convertValue(){double v=Double.parseDouble(amount.getText());return v*(target.getSelectedItem().equals("USD")?0.012:0.011);}
 void convert(){try{result.setText(String.format("%.2f %s",convertValue(),target.getSelectedItem()));}catch(NumberFormatException ex){result.setText("Invalid amount");JOptionPane.showMessageDialog(this,"Invalid amount");}}
 ConverterStyles(){super("Converter – event styles");setLayout(new FlowLayout());add(new JLabel("INR"));add(amount);add(target);JButton b=new JButton("Convert");add(b);add(result);
 // (a) Frame-style listener (separate instance demonstration)
 b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){convert();}});
 // (b) Named inner class example method available for alternate button
 JButton named=new JButton("Named handler");named.addActionListener(new NamedHandler());add(named);
 // (c) Anonymous listener example for Enter key
 amount.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){convert();}});
 // (d) Lambda shared listener on target selection
 target.addActionListener(e->{});
 setDefaultCloseOperation(EXIT_ON_CLOSE);pack();setLocationRelativeTo(null);}
 class NamedHandler implements ActionListener{public void actionPerformed(ActionEvent e){convert();}}
 public static void main(String[]a){SwingUtilities.invokeLater(()->new ConverterStyles().setVisible(true));}}
