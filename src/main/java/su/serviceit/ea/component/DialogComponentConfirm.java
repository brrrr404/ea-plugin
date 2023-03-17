package su.serviceit.ea.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.GridBag;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;

import javax.swing.*;
import java.awt.*;

public class DialogComponentConfirm extends DialogWrapper {

    private final DialogComponentCommon dialogComponentCommon = new DialogComponentCommon();

    JPanel panel = new JPanel(new GridBagLayout());

    public DialogComponentConfirm() {
        super(false);

        this.init();
        this.setTitle("Confirm");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gb = dialogComponentCommon.createGridBag();

        panel.setPreferredSize(new Dimension(300, 100));

        panel.add(createLabel("Are you sure you want to change alias?"),
                gb.nextLine().next().weightx(0.2));

        return panel;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }
}
