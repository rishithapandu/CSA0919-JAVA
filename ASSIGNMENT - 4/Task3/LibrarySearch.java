import javax.swing.*; import java.awt.*;
public class LibrarySearch extends JFrame {
 public LibrarySearch(){super("Campus Library Search");setLayout(new BorderLayout(8,8));
  JLabel title=new JLabel("Campus Library",SwingConstants.CENTER); title.setFont(new Font("SansSerif",Font.BOLD,20)); add(title,BorderLayout.NORTH);
  JPanel search=new JPanel(new FlowLayout(FlowLayout.LEFT)); search.add(new JLabel("Search:")); search.add(new JTextField(18)); Choice category=new Choice(); category.add("All");category.add("Book");category.add("Author");search.add(category);search.add(new Button("Search"));
  JPanel center=new JPanel(new BorderLayout());center.add(search,BorderLayout.NORTH);center.add(new JScrollPane(new JTextArea("Search results appear here...",12,40)),BorderLayout.CENTER);add(center,BorderLayout.CENTER);
  JPanel bottom=new JPanel(new FlowLayout());bottom.add(new Button("Prev"));bottom.add(new Button("Next"));bottom.add(new Button("Reserve"));add(bottom,BorderLayout.SOUTH);setSize(600,400);setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);}
 public static void main(String[]a){EventQueue.invokeLater(()->new LibrarySearch().setVisible(true));}
}
