package su.serviceit.ea.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "su.serviceit.ea.setting.AppSettingState",
        storages = @Storage("EaPlugin.xml")
)
public class AppSettingState implements PersistentStateComponent<AppSettingState> {

    public String ip;
    public String port;
    public String databaseName;

    @Override
    public @Nullable AppSettingState getState() {
        return this;
    }

    public static AppSettingState getInstance() {
        return ProjectManager.getInstance().getDefaultProject().getService(AppSettingState.class);
    }

    @Override
    public void loadState(@NotNull AppSettingState state) {
        this.ip = state.ip;
        this.port = state.port;
        this.databaseName = state.databaseName;
    }


}
