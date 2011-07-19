package fi.helsinki.cs.tmc.ui.swingPanels;

import java.awt.event.KeyEvent;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import fi.helsinki.cs.tmc.data.Course;
import fi.helsinki.cs.tmc.data.CourseCollection;
import fi.helsinki.cs.tmc.settings.Settings;
import fi.helsinki.cs.tmc.utilities.LocalCourseCache;
import fi.helsinki.cs.tmc.utilities.ModalDialogDisplayer;
import fi.helsinki.cs.tmc.utilities.json.updaters.ICourseListUpdateListener;
import fi.helsinki.cs.tmc.utilities.json.updaters.JSONCourseListUpdater;

/**
 * This panel is used to display preferences to the user. The user can then
 * change these settings.
 * @author knordman
 */
public class PreferencesPanel extends javax.swing.JPanel implements ICourseListUpdateListener {

    /**
     * This object holds the setting information.
     */
    private Settings settings;
    /**
     * This is used to update the course list.
     */
    private JSONCourseListUpdater jsonCourseListUpdater;

    /** Creates new form PreferencesPanel */
    public PreferencesPanel(Settings settings) {
        initComponents();

        if (settings == null) {
            throw new NullPointerException("settings was null at PreferencesPanel.Constructor");
        }

        this.settings = settings;

        studentNumberTextField.setText(settings.getStudentID());
        hostAddressTextField.setText(settings.getHostAddress());
        projectFolderTextField.setText(settings.getDefaultFolder());
        projectFolderTextField.setToolTipText(settings.getDefaultFolder());


        coursesComboBox.removeAllItems();
        
        //disable paste:
        studentNumberTextField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "none");
        studentNumberTextField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, KeyEvent.SHIFT_MASK), "none");
        

        /* Adds course objects to coursesComboBox.
         * Should not crash here but display an empty comboBox instead.
         */

        CourseCollection courses = null;
        try {
            courses = LocalCourseCache.getInstance().getCourses();
        } catch (Exception ex) {
            //FIXME!
        }

        if (courses != null) {
            Course selectedCourse = courses.getCourseByName(settings.getSelectedCourse());


            int i = -1;
            int selectedCourseIndex = -1;

            if (selectedCourse != null) {
                for (Course course : courses) {
                    i++;
                    coursesComboBox.addItem(course);

                    if (course.getName().equals(selectedCourse.getName())) {
                        selectedCourseIndex = i;
                    }
                }

            }
            coursesComboBox.setSelectedIndex(selectedCourseIndex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentNumberLabel = new javax.swing.JLabel();
        studentNumberTextField = new javax.swing.JTextField();
        hostAddressLabel = new javax.swing.JLabel();
        hostAddressTextField = new javax.swing.JTextField();
        defaultProjectFolderLabel = new javax.swing.JLabel();
        projectFolderTextField = new javax.swing.JTextField();
        folderChooserBtn = new javax.swing.JButton();
        refreshCoursesBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        coursesComboBox = new javax.swing.JComboBox();

        studentNumberLabel.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.studentNumberLabel.text")); // NOI18N

        studentNumberTextField.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.studentNumberTextField.text")); // NOI18N
        studentNumberTextField.setMinimumSize(new java.awt.Dimension(150, 27));
        studentNumberTextField.setPreferredSize(new java.awt.Dimension(150, 27));
        studentNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentNumberTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentNumberTextFieldKeyTyped(evt);
            }
        });

        hostAddressLabel.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.hostAddressLabel.text")); // NOI18N

        hostAddressTextField.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.hostAddressTextField.text")); // NOI18N
        hostAddressTextField.setMinimumSize(new java.awt.Dimension(250, 27));
        hostAddressTextField.setPreferredSize(new java.awt.Dimension(250, 27));
        hostAddressTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hostAddressTextFieldKeyReleased(evt);
            }
        });

        defaultProjectFolderLabel.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.defaultProjectFolderLabel.text")); // NOI18N

        projectFolderTextField.setEditable(false);
        projectFolderTextField.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.projectFolderTextField.text")); // NOI18N
        projectFolderTextField.setEnabled(false);
        projectFolderTextField.setPreferredSize(new java.awt.Dimension(250, 27));

        folderChooserBtn.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.folderChooserBtn.text")); // NOI18N
        folderChooserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderChooserBtnActionPerformed(evt);
            }
        });

        refreshCoursesBtn.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.refreshCoursesBtn.text")); // NOI18N
        refreshCoursesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshCoursesBtnActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PreferencesPanel.class, "PreferencesPanel.jLabel1.text")); // NOI18N

        coursesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        coursesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentNumberLabel)
                    .addComponent(hostAddressLabel)
                    .addComponent(defaultProjectFolderLabel)
                    .addComponent(jLabel1))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hostAddressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(studentNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(projectFolderTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                            .addComponent(coursesComboBox, 0, 361, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(refreshCoursesBtn)
                            .addComponent(folderChooserBtn))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentNumberLabel)
                    .addComponent(studentNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostAddressLabel)
                    .addComponent(hostAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultProjectFolderLabel)
                    .addComponent(projectFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(folderChooserBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshCoursesBtn)
                    .addComponent(coursesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The method is used to select the default save folder for downloaded exercises.
     * It is called when the user presses the "Browse" button on the preferences window.
     * @param evt 
     */
    private void folderChooserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderChooserBtnActionPerformed

        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = folderChooser.showOpenDialog(this);
        if (choice == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File projectDefaultFolder = folderChooser.getSelectedFile();

        projectFolderTextField.setText(projectDefaultFolder.getAbsolutePath());
        projectFolderTextField.setToolTipText(projectDefaultFolder.getAbsolutePath());

        settings.setDefaultFolder(projectFolderTextField.getText());

    }//GEN-LAST:event_folderChooserBtnActionPerformed

    

    private void studentNumberTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNumberTextFieldKeyReleased


    }//GEN-LAST:event_studentNumberTextFieldKeyReleased


    /**
     * This method is called whenever a key is pressed on the keyboard while writing to the host address field.
     * The method sends all the text in the field to the settings-object for saving.
     * @param evt 
     */
    private void hostAddressTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hostAddressTextFieldKeyReleased
        // TODO add your handling code here:
        settings.setHostAddress(hostAddressTextField.getText());
    }//GEN-LAST:event_hostAddressTextFieldKeyReleased

    /**
     * Method reads course objects to coursesComboBox e.g in a situation where user changes hostAddress or 
     * writes it for the first time.
     * @param evt 
     */
    private void refreshCoursesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshCoursesBtnActionPerformed
        // TODO add your handling code here: 


        if (!settings.isValid()) {
            ModalDialogDisplayer.getDefault().displayNotification("Please fill all fields and select default folder before fetching courses.", "Required data missing");
            return;
        }

        try {
            refreshCoursesBtn.setEnabled(false);
            coursesComboBox.setEnabled(false);
            jsonCourseListUpdater = new JSONCourseListUpdater(settings.getHostAddress(), this);
        } catch (Exception e) {
            ModalDialogDisplayer.getDefault().displayError("Failed to download course list. Check server address.");
            refreshCoursesBtn.setEnabled(true);
            coursesComboBox.setEnabled(true);
            return;
        }

        coursesComboBox.removeAllItems();
        jsonCourseListUpdater.downloadCourseList();
    }//GEN-LAST:event_refreshCoursesBtnActionPerformed

    /**
     * This method is called whenever the user chooses something from the
     * combobox. It takes the chosen course and sets it as the selected course.
     * @param evt 
     */
    private void coursesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesComboBoxActionPerformed
        Course course = (Course) coursesComboBox.getSelectedItem();

        if (course != null) {
            settings.setSelectedCourse(course.getName());
        }
    }//GEN-LAST:event_coursesComboBoxActionPerformed

    
    
    /**
     * This method is called whenever a key is pressed on the keyboard while writing to the studen number field.
     * The method sends all the text in the field to the settings-object for saving.
     * @param evt 
     */
    private void studentNumberTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNumberTextFieldKeyTyped
        // TODO add your handling code here:
    
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
                return;            
        }        
        
        boolean isValid = false;
        char c = evt.getKeyChar();

        if(Character.isLetterOrDigit(c)) isValid = true;            
        String allowedChars = " @-_.,:;öäåÖÄÅ";

        if(allowedChars.indexOf(c) != -1) isValid = true;

        if(!isValid || studentNumberTextField.getText().length() > 40) 
        {
            evt.consume();                                       
            return;
        }
             
        
        String text = studentNumberTextField.getText();
        if(isValid) text += c;
        
                      
        settings.setStudentID(text);
        
        
    }//GEN-LAST:event_studentNumberTextFieldKeyTyped

    private void studentNumberTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNumberTextFieldKeyPressed

         
        
    }//GEN-LAST:event_studentNumberTextFieldKeyPressed
    /**
     * The method sends the selected course from coursesComboBox to the settings-object for saving.
     * @param evt 
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox coursesComboBox;
    private javax.swing.JLabel defaultProjectFolderLabel;
    private javax.swing.JButton folderChooserBtn;
    private javax.swing.JLabel hostAddressLabel;
    private javax.swing.JTextField hostAddressTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField projectFolderTextField;
    private javax.swing.JButton refreshCoursesBtn;
    private javax.swing.JLabel studentNumberLabel;
    private javax.swing.JTextField studentNumberTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * Called by the CourseListUpdater. This method updates the comboBox that
     * holds all the courses in it.
     */
    @Override
    public void courseListDownloadComplete() {

        /* If we fail to get the courses, we should display a reason for this madness. */
        CourseCollection courses = null;
        try {
            courses = LocalCourseCache.getInstance().getCourses();
        } catch (Exception ex) {
            ModalDialogDisplayer.getDefault().displayError(ex);
        }

        if (courses != null) {
            for (Course course : courses) {
                coursesComboBox.addItem(course);
            }
            refreshCoursesBtn.setEnabled(true);
            coursesComboBox.setEnabled(true);
        }

        jsonCourseListUpdater = null;
    }

    /**
     * Called when CourseListUpdater fails to update the list to inform the user.
     * @param errorMessage 
     */
    @Override
    public void courseListDownloadFailed(String errorMessage) {
        ModalDialogDisplayer.getDefault().displayError("Failed to download course list. Check server address.");
        refreshCoursesBtn.setEnabled(true);
        coursesComboBox.setEnabled(true);
        jsonCourseListUpdater = null;
    }

    /**
     * This method can be called to stop the course list updating.
     */
    public void interruptCourseListUpdate() {
        if (jsonCourseListUpdater != null) {
            jsonCourseListUpdater.cancel();
        }

    }
}
