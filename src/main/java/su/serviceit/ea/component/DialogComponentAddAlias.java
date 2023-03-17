package su.serviceit.ea.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.impl.source.PsiMethodImpl;
import com.intellij.util.ui.GridBag;
import org.jetbrains.annotations.Nullable;
import su.serviceit.ea.component.common.DialogComponentCommon;
import su.serviceit.ea.model.IdAliasDto;
import su.serviceit.ea.repository.EnterpriseArchitectRepository;
import su.serviceit.ea.service.AliasService;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DialogComponentAddAlias extends DialogWrapper {


    private final EnterpriseArchitectRepository enterpriseArchitectRepository = new EnterpriseArchitectRepository();
    private final DialogComponentCommon dialogComponentCommon = new DialogComponentCommon();
    private final AliasService aliasService = new AliasService();

    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JTextField guid = new JTextField();
    private final JTextField newAlias = new JTextField();
    private final JTextField alias = new JTextField();
    private final JButton getButton = new JButton("Get actual alias");
    private final JButton deleteAlias = new JButton("Delete alias");
    private IdAliasDto dto;

    public DialogComponentAddAlias(AnActionEvent event) {
        super(true);
        this.setModal(false);
        this.setOKActionEnabled(false);

        this.init();
        this.setTitle("Create New Alias");

        String urlMethod = getUrlMethod(event);
        setNewAlias(urlMethod);

        getButton.addActionListener(actionEvent -> {
            updateAliasData();
        });

        deleteAlias.addActionListener(actionEvent -> {
            deleteAlias();
        });
    }

    private void updateAliasData() {
        dto = aliasService.getAlias(getGuid());

        if (Objects.nonNull(dto)) {
            setAlias(dto.getAlias());
            newAlias.setEditable(true);
            this.setOKActionEnabled(true);
        }
    }

    private void deleteAlias() {
        boolean success = aliasService.deleteAlias(dto);

        if (success) {
            setAlias("");
            newAlias.setEditable(true);
            new DialogComponentSuccessfully("Alias was deleted").showAndGet();
        }
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        alias.setEditable(false);
        newAlias.setEditable(false);

        GridBag gb = dialogComponentCommon.createGridBag();

        panel.setPreferredSize(new Dimension(600, 200));

        panel.add(createLabel("guid"), gb.nextLine().next().weightx(0.2));
        panel.add(guid, gb.next().weightx(0.7));
        panel.add(getButton, gb.next().weightx(0.1));

        panel.add(createLabel("alias"), gb.nextLine().nextLine().nextLine().next().weightx(0.2));
        panel.add(alias, gb.next().weightx(0.8));
        panel.add(deleteAlias, gb.next().weightx(0.1));

        panel.add(createLabel("new alias"), gb.nextLine().next().weightx(0.2));
        panel.add(newAlias, gb.next().weightx(0.8));

        return panel;
    }

    @Override
    protected void doOKAction() {
        if (validNewAlias()) {
            enterpriseArchitectRepository.createAlias(dto.getId(), getNewAlias());
            setAlias(getNewAlias());
            new DialogComponentSuccessfully("Alias was updated").showAndGet();
        }
    }

    private boolean validNewAlias() {
        return Objects.nonNull(getNewAlias())
               && !getNewAlias().isBlank()
               && (getAlias().equals("N/A")
                   || new DialogComponentConfirm().showAndGet());
    }

    private JComponent createLabel(String text) {
        return dialogComponentCommon.createLabel(text);
    }

    private String getUrlMethod(AnActionEvent event) {

        PsiElement element = event.getData(PlatformDataKeys.PSI_ELEMENT);

        if (Objects.nonNull(element)) {
            String methodName = ((PsiMethodImpl) element).getName();
            String className = ((PsiMethodImpl) element).getContainingClass().getName();
            String pathName = ((PsiJavaFile) element.getContainingFile()).getPackageName();


            if (Objects.equals(((PsiMethodImpl) element).getNode().getElementType().getDebugName(), "METHOD")) {
                return pathName + "." + className + "#" + methodName;
            }
            return pathName + "." + className;
        }
        return "";
    }

    public String getGuid() {
        return guid.getText();
    }

    public String getNewAlias() {
        return newAlias.getText();
    }

    public String getAlias() {
        return alias.getText();
    }

    public void setGuid(String newText) {
        guid.setText(newText);
    }

    public void setAlias(String newText) {
        alias.setText(newText);
    }

    public void setNewAlias(String newText) {
        newAlias.setText(newText);
    }
}
