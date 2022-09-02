package autoDropDown;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

@SuppressWarnings("rawtypes")
public class AutocompleteJComboBox extends JComboBox{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static final long serialVersionId = 432142L;
    private final Searchable<String, String> searchable;

    public AutocompleteJComboBox(Searchable<String,String> s) {
        super();
        this.searchable = s;
        setEditable(true);
        Component c = getEditor().getEditorComponent();
        if(c instanceof JTextComponent) {
            final JTextComponent tc = (JTextComponent)c;
            tc.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update();

                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    update();

                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

                public void update() {
                    SwingUtilities.invokeLater(new Runnable() {

                        @SuppressWarnings("unchecked")
                        @Override
                        public void run() {
                            List<String> founds = new ArrayList<String>(searchable.search(tc.getText()));
                            Set<String> foundSet = new HashSet<String>();
                            for(String s : founds) {
                                foundSet.add(s.toLowerCase());
                            }
                            Collections.sort(founds);

                            setEditable(false);
                            removeAllItems();

                            if(!foundSet.contains(tc.getText().toLowerCase())) {
                                addItem(tc.getText() );
                            }

                            for(String s: founds) {
                                addItem(s);
                            }
                            setEditable(true);
                            setPopupVisible(true);
                        }
                    });
                }
            });
            tc.addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    if(tc.getText().length() > 0) {
                        setPopupVisible(true);
                    }

                }

                @Override
                public void focusGained(FocusEvent e) {

                }
            });
        } else {
            throw new IllegalStateException("Editing component is not JTextComponent!");
        }
    }

}
