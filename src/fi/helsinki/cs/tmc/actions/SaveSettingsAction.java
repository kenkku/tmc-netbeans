package fi.helsinki.cs.tmc.actions;

import fi.helsinki.cs.tmc.model.CourseDb;
import fi.helsinki.cs.tmc.model.ProjectMediator;
import fi.helsinki.cs.tmc.model.ServerAccess;
import fi.helsinki.cs.tmc.ui.PreferencesUI;
import fi.helsinki.cs.tmc.ui.ConvenientDialogDisplayer;
import fi.helsinki.cs.tmc.ui.ExerciseIconAnnotator;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import org.openide.util.Lookup;

public class SaveSettingsAction extends AbstractAction {

    private ServerAccess serverAccess;
    private CourseDb courseDb;
    private ProjectMediator projectMediator;
    private ConvenientDialogDisplayer dialogs;
    private OpenExercisesAction openExercisesAction;
    private ExerciseIconAnnotator iconAnnotator;
    
    public SaveSettingsAction() {
        this(ServerAccess.getDefault(),
                CourseDb.getInstance(),
                ProjectMediator.getInstance(),
                ConvenientDialogDisplayer.getDefault(),
                new OpenExercisesAction(),
                Lookup.getDefault().lookup(ExerciseIconAnnotator.class));
    }

    public SaveSettingsAction(
            ServerAccess serverAccess,
            CourseDb courseDb,
            ProjectMediator projectMediator,
            ConvenientDialogDisplayer dialogs,
            OpenExercisesAction openExercisesAction,
            ExerciseIconAnnotator iconAnnotator) {
        this.serverAccess = serverAccess;
        this.courseDb = courseDb;
        this.projectMediator = projectMediator;
        this.dialogs = dialogs;
        this.openExercisesAction = openExercisesAction;
        this.iconAnnotator = iconAnnotator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(e.getSource() instanceof PreferencesUI)) {
            throw new IllegalArgumentException(
                    SaveSettingsAction.class.getSimpleName()
                    + " expected event source to be a "
                    + PreferencesUI.class.getSimpleName());
        }

        PreferencesUI prefUi = (PreferencesUI) e.getSource();

        serverAccess.setUsername(prefUi.getUsername());
        serverAccess.setBaseUrl(prefUi.getServerBaseUrl());
        projectMediator.setProjectRootDir(prefUi.getProjectDir());
        if (prefUi.getSelectedCourse() != null) {
            String courseName = prefUi.getSelectedCourse().getName();
            courseDb.setCurrentCourseName(courseName);
            promptOpeningExercises();
        } else {
            courseDb.setCurrentCourseName(null);
        }
        iconAnnotator.updateAllIcons();
    }

    private void promptOpeningExercises() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (dialogs.askYesNo("Open latest exercises now?", "Open exercises?")) {
                    openExercisesAction.actionPerformed(null);
                }
            }
        });
    }
}
