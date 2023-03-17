package su.serviceit.ea.component.exception;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.GridBag;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;

import javax.swing.*;
import java.awt.*;

public class DialogComponentException extends DialogWrapper {

    private final DialogComponentCommon dialogComponentCommon;

    JPanel panel = new JPanel(new GridBagLayout());

    String exceptionMessage;

    public DialogComponentException(String message) {
        super(true);
        this.dialogComponentCommon = new DialogComponentCommon();
        exceptionMessage = message;


        this.init();
        this.setTitle("Error");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gb = dialogComponentCommon.createGridBag();

        panel.setPreferredSize(new Dimension(300, 100));

        panel.add(createLabel(exceptionMessage),
                gb.nextLine().next().weightx(0.2));

        return panel;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }
}
