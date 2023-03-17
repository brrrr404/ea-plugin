package su.serviceit.ea.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.GridBag;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;

import javax.swing.*;
import java.awt.*;

public class DialogComponentSuccessfully extends DialogWrapper {

    private final DialogComponentCommon dialogComponentCommon = new DialogComponentCommon();

    private JPanel panel = new JPanel(new GridBagLayout());
    private String text;

    public DialogComponentSuccessfully(String text) {
        super(false);

        this.text = text;

        this.init();
        this.setTitle("Successfully");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gb = dialogComponentCommon.createGridBag();

        panel.setPreferredSize(new Dimension(100, 100));

        panel.add(createLabel("Successfully!!\n " + text),
                gb.nextLine().next().weightx(0.2));

        return panel;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }
}
