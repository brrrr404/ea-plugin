package su.serviceit.ea.setting;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class AppSettingComponent {
    private final JPanel myMainPanel;
    private final JBTextField ip = new JBTextField();
    private final JBTextField port = new JBTextField();
    private final JBTextField databaseName = new JBTextField();

    public AppSettingComponent() {
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("EA ip: "), ip, 1, false)
                .addLabeledComponent(new JBLabel("EA port: "), port, 1, false)
                .addLabeledComponent(new JBLabel("EA database name: "), databaseName, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return ip;
    }

    public String getIp() {
        return ip.getText();
    }

    public void setIp(String newText) {
        ip.setText(newText);
    }

    public String getPort() {
        return port.getText();
    }

    public void setPort(String newText) {
        port.setText(newText);
    }

    public String getDatabaseName() {
        return databaseName.getText();
    }

    public void setDatabaseName(String newText) {
        databaseName.setText(newText);
    }
}
