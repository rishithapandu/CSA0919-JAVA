import javax.swing.*; import java.awt.*;
public class AppointmentDesk extends JFrame {
    JTextField patient=new JTextField(12); JComboBox<String> dept=new JComboBox<>(new String[]{"Cardiology","Orthopedics","Dermatology"});
    JComboBox<String> doctor=new JComboBox<>(); JComboBox<String> slot=new JComboBox<>(new String[]{"09:00","10:00","11:00","14:00"}); boolean booking=false;
    public AppointmentDesk(){super("Outpatient Appointment Desk"); setLayout(new GridLayout(5,2,8,8));
      add(new JLabel("Patient ID"));add(patient);add(new JLabel("Department"));add(dept);add(new JLabel("Doctor"));add(doctor);add(new JLabel("Time slot"));add(slot);
      JButton book=new JButton("Book");add(book);add(new JLabel(""));
      dept.addActionListener(e->refreshDoctors()); book.addActionListener(e->book()); refreshDoctors();
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); addWindowListener(new java.awt.event.WindowAdapter(){public void windowClosing(java.awt.event.WindowEvent e){if(booking){if(JOptionPane.showConfirmDialog(AppointmentDesk.this,"Booking in progress. Close?","Confirm",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;}dispose();}});
      setSize(450,240);setLocationRelativeTo(null);
    }
    void refreshDoctors(){doctor.removeAllItems();String d=(String)dept.getSelectedItem(); if("Cardiology".equals(d)){doctor.addItem("Dr Rao");doctor.addItem("Dr Kumar");} else if("Orthopedics".equals(d)){doctor.addItem("Dr Mehta");doctor.addItem("Dr Iyer");} else {doctor.addItem("Dr Shah");doctor.addItem("Dr Priya");}}
    void book(){if(patient.getText().trim().isEmpty()){JOptionPane.showMessageDialog(this,"Enter patient ID");return;} booking=true; JOptionPane.showMessageDialog(this,"Appointment booked: "+patient.getText()+" / "+doctor.getSelectedItem()+" / "+slot.getSelectedItem());booking=false;}
    public static void main(String[]a){SwingUtilities.invokeLater(()->new AppointmentDesk().setVisible(true));}
}
