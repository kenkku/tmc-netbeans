package fi.helsinki.cs.tmc.actions;

import fi.helsinki.cs.tmc.data.Exercise;
import fi.helsinki.cs.tmc.model.CourseDb;
import fi.helsinki.cs.tmc.model.ProjectMediator;
import fi.helsinki.cs.tmc.model.TmcProjectInfo;
import fi.helsinki.cs.tmc.model.TmcSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.ImageUtilities;

public class CheckForUnopenedExercises implements ActionListener {
    public static boolean shouldRunOnStartup() {
        return TmcSettings.getDefault().isCheckingForUnopenedAtStartup();
    }
    
    private ProjectMediator projects;
    private CourseDb courseDb;
    private NotificationDisplayer notifier;

    public CheckForUnopenedExercises() {
        this.projects = ProjectMediator.getInstance();
        this.courseDb = CourseDb.getInstance();
        this.notifier = NotificationDisplayer.getDefault();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        run();
    }
    
    public void run() {
        projects.callWhenProjectsCompletelyOpened(new Runnable() {
            @Override
            public void run() {
                List<Exercise> unopenedExercises = new ArrayList<Exercise>();
                for (Exercise ex : courseDb.getCurrentCourseExercises()) {
                    TmcProjectInfo project = projects.tryGetProjectForExercise(ex);
                    if (project != null && !projects.isProjectOpen(project)) {
                        unopenedExercises.add(ex);
                    }
                }

                if (!unopenedExercises.isEmpty()) {
                    showNotification(unopenedExercises);
                }
            }
        });
    }

    private void showNotification(List<Exercise> unopenedExercises) {
System.out.println(unopenedExercises);
        int count = unopenedExercises.size();
        String msg;
        String prompt;
        if (count == 1) {
            msg = "There is one exercise that is downloaded but not opened.";
            prompt = "Click here to open it.";
        } else {
            msg = "There are " + count + " exercises that are downloaded but not opened.";
            prompt = "Click here to open them.";
        }
        notifier.notify(msg, getNotificationIcon(), prompt, openAction(unopenedExercises), NotificationDisplayer.Priority.LOW);
    }
    
    private ActionListener openAction(final List<Exercise> exercises) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Exercise ex : exercises) {
                    TmcProjectInfo project = projects.tryGetProjectForExercise(ex);
                    if (project != null && !projects.isProjectOpen(project)) {
                        projects.openProject(project);
                    }
                }
            }
        };
    }
    
    private Icon getNotificationIcon() {
        return ImageUtilities.loadImageIcon("fi/helsinki/cs/tmc/smile.gif", false);
    }
}
