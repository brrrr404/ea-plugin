package su.serviceit.ea.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;

import javax.swing.*;
import java.awt.*;

public class DialogComponentSuccessfully extends DialogWrapper {

    private final DialogComponentCommon dialogComponentCommon;

    private JPanel panel = new JPanel(new GridBagLayout());
    private String text;

    public DialogComponentSuccessfully(String text) {
        super(false);
        this.dialogComponentCommon = new DialogComponentCommon();

        this.text = text;

        this.init();
        this.setTitle("Successfully");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(100, 100));

        panel.add(createLabel("Successfully!!\n " + text),
                gb.nextLine().next().weightx(0.2));

        return panel;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }
}
