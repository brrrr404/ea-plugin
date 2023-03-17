package su.serviceit.ea.setting;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Function;

public class AppSettingConfigurable implements Configurable {

    private AppSettingComponent mySettingsComponent;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Настройка плагина";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Override
    public @Nullable JComponent createComponent() {
        mySettingsComponent = new AppSettingComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingState settings = AppSettingState.getInstance();
        boolean modified = !Objects.equals(mySettingsComponent.getIp(), settings.ip);
        modified |= !Objects.equals(mySettingsComponent.getPort(), settings.port);
        modified |= !Objects.equals(mySettingsComponent.getDatabaseName(), settings.databaseName);
        modified |= !Objects.equals(mySettingsComponent.getLogin(), settings.login);
        modified |= !Objects.equals(mySettingsComponent.getPassword(), settings.password);
        return modified;
    }

    @Override
    public void apply() {
        AppSettingState settings = AppSettingState.getInstance();
        settings.ip = mySettingsComponent.getIp();
        settings.port = mySettingsComponent.getPort();
        settings.databaseName = mySettingsComponent.getDatabaseName();
        settings.login = mySettingsComponent.getLogin();
        settings.password= mySettingsComponent.getPassword();
    }

    @Override
    public void reset() {
        AppSettingState settings = AppSettingState.getInstance();

        mySettingsComponent.setIp(settings.ip);
        mySettingsComponent.setPort(settings.port);
        mySettingsComponent.setDatabaseName(settings.databaseName);
        mySettingsComponent.setLogin(settings.login);
        mySettingsComponent.setPassword(settings.password);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }
}
