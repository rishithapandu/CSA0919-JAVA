import java.awt.*; import java.awt.event.*;
class FeeService {
    String pay(String reg, double amount) {
        if (reg == null || reg.trim().isEmpty()) throw new IllegalArgumentException("Register number required");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        return "Payment recorded for " + reg + " : ₹" + String.format("%.2f", amount);
    }
}
public class FeeWindow extends Frame {
    TextField reg = new TextField(14), amount = new TextField(10);
    Label status = new Label("Enter details"); FeeService service = new FeeService();
    public FeeWindow() {
        super("Student Fee Payment"); setLayout(new GridLayout(4,2,8,8));
        add(new Label("Register No:")); add(reg); add(new Label("Amount:")); add(amount);
        Button pay=new Button("Pay"), clear=new Button("Clear"), receipt=new Button("Receipt");
        add(pay); add(clear); add(receipt); add(status);
        pay.addActionListener(e -> { try { status.setText(service.pay(reg.getText(), Double.parseDouble(amount.getText()))); } catch(Exception ex){ status.setText("Error: "+ex.getMessage()); }});
        clear.addActionListener(e -> {reg.setText(""); amount.setText(""); status.setText("Cleared");});
        receipt.addActionListener(e -> status.setText("Receipt: " + reg.getText()));
        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}});
        setSize(420,180); setVisible(true);
    }
    public static void main(String[] args){new FeeWindow();}
}
