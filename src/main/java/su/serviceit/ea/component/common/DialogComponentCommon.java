package su.serviceit.ea.component.common;

import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;

public class DialogComponentCommon {

    public JComponent createLabel(String text) {
        JBLabel label = new JBLabel(text);
        label.setFontColor(UIUtil.FontColor.BRIGHTER);
        label.setComponentStyle(UIUtil.ComponentStyle.SMALL);
        label.setBorder(JBUI.Borders.empty(0, 5, 2, 0));
        return label;
    }

    public GridBag createGridBag() {
        return new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);
    }
}
