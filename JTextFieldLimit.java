import javax.swing.text.*;

public class JTextFieldLimit extends PlainDocument {
    private final int limite;

    public JTextFieldLimit(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) return;
        if ((getLength() + str.length()) <= limite) {
            super.insertString(offs, str, a);
        }
    }
}
