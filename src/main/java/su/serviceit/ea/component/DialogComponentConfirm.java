package su.serviceit.ea.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;

import javax.swing.*;
import java.awt.*;

public class DialogComponentConfirm extends DialogWrapper {

    private final DialogComponentCommon dialogComponentCommon;

    JPanel panel = new JPanel(new GridBagLayout());

    public DialogComponentConfirm() {
        super(false);
        this.dialogComponentCommon = new DialogComponentCommon();

        this.init();
        this.setTitle("Confirm");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(300, 100));

        panel.add(createLabel("Are you sure you want to change alias?"),
                gb.nextLine().next().weightx(0.2));

        return panel;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }
}
