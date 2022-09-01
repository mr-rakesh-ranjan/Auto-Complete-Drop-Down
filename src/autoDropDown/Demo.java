package autoDropDown;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Demo {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                List<String> country = new ArrayList<>();
                country.add("India");
                country.add("Indonesia");
                country.add("Iraq");
                country.add("Iran");
                country.add("USA");
                country.add("UAE");
                country.add("Pakistan");
                country.add("Japan");
                country.add("England");
                country.add("Hong Kong");

                StringSearchable searchable = new StringSearchable(country);
                AutocompleteJComboBox combo = new AutocompleteJComboBox(searchable);
                JFrame frame = new JFrame();
                frame.add(combo);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);


            }
        });

    }

}
