package su.serviceit.ea.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.util.ui.GridBag;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;
import su.serviceit.ea.exception.NotFoundException;
import su.serviceit.ea.exception.component.DialogComponentException;
import su.serviceit.ea.model.IdAliasDto;
import su.serviceit.ea.repository.EnterpriseArchitectRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class DialogComponentAddAlias extends DialogWrapper {


    private final EnterpriseArchitectRepository enterpriseArchitectRepository;
    private final DialogComponentCommon dialogComponentCommon;

    JPanel panel = new JPanel(new GridBagLayout());
    JTextField guid = new JTextField();
    JTextField newAlias = new JTextField();
    JTextField alias = new JTextField();
    JButton button = new JButton("get actual alias");
    String guidStr;
    IdAliasDto dto;

    public DialogComponentAddAlias() {
        super(true);
        this.enterpriseArchitectRepository = new EnterpriseArchitectRepository();
        this.dialogComponentCommon = new DialogComponentCommon();

        this.init();
        this.setTitle("Create New Alias");

        button.addActionListener(actionEvent -> {
            guidStr = guid.getText();
            dto = enterpriseArchitectRepository.getAliasByGuid(guidStr);

            if (Objects.isNull(dto)) {
                return;
            }
            alias.setText(getAliasStr(dto));
            newAlias.setEditable(true);
        });
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        alias.setEditable(false);
        newAlias.setEditable(false);

        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
                .setDefaultWeightX(1.0)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.setPreferredSize(new Dimension(600, 200));

        panel.add(createLabel("guid"), gb.nextLine().next().weightx(0.2));
        panel.add(guid, gb.next().weightx(0.7));
        panel.add(button, gb.next().weightx(0.1));

        panel.add(createLabel("alias"), gb.nextLine().nextLine().nextLine().next().weightx(0.2));
        panel.add(alias, gb.next().weightx(0.8));

        panel.add(createLabel("new alias"), gb.nextLine().next().weightx(0.2));
        panel.add(newAlias, gb.next().weightx(0.8));

        return panel;
    }

    @Override
    protected void doOKAction() {

        if (validNewAlias()) {
                enterpriseArchitectRepository.createAlias(dto.getId(), newAlias.getText());
                alias.setText(getAliasStr(guidStr));
                new DialogComponentSuccessfully().showAndGet();
        }
    }

    private boolean validNewAlias() {
        return Objects.nonNull(newAlias.getText())
               && !newAlias.getText().isBlank()
               && (alias.getText().equals("N/A")
               || new DialogComponentConfirm().showAndGet());
    }

    @NotNull
    private static String getAliasStr(IdAliasDto dto) {
        String aliasStr;
        if (Objects.isNull(dto.getAlias()) || dto.getAlias().equals("")) {
            aliasStr = "N/A";
        } else {
            aliasStr = dto.getAlias();
        }
        return aliasStr;
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }

    private String getAliasStr(String guidStr) {

        dto = enterpriseArchitectRepository.getAliasByGuid(guidStr);

        if (Objects.isNull(dto.getAlias())) {
            return "";
        }
        return dto.getAlias();
    }
}
